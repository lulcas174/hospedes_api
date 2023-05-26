package hospedeAPI.example.hospedeAPI.services;

import hospedeAPI.example.hospedeAPI.models.CheckIn;
import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.repositories.CheckInRepository;
import hospedeAPI.example.hospedeAPI.repositories.HospedeRepository;
import hospedeAPI.example.hospedeAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service @Transactional
public class CheckInService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private Utils utils;


    public CheckIn createCheckIn(String documentoCPF) {
        Hospede hospede = hospedeRepository.findByDocumentoCPF(documentoCPF);
        Date actualDate = new Date();
        List<CheckIn> allCheckin = checkInRepository.findAll();
        String dataEntradaConvert;
        String actualDateString;

        if (hospede == null) {
            throw new NullPointerException("Hóspede não encontrado");
        }


        List<CheckIn> hospedeCheckInExist = checkInRepository.findAllByHospedeDocumentoCPF(documentoCPF);
        for(CheckIn checkIn : hospedeCheckInExist){
            dataEntradaConvert = utils.convertDateToString(checkIn.getDataEntrada());
            actualDateString = utils.convertDateToString(actualDate);
            if(checkIn != null && dataEntradaConvert.equals(actualDateString)){
                throw new IllegalArgumentException("Hóspede já fez check-in");
            }
        }

        Double ultimoValorTotalEstadia = 0.00;
        for(CheckIn ch : allCheckin){
            Double valorTotalEstadia = ch.getValorTotalEstadia();
            if (valorTotalEstadia != null) {
                ultimoValorTotalEstadia = valorTotalEstadia;
            }
        }

        CheckIn checkIn = setCheckInFields(hospede);
        Double totalValue = calculateTotalValue(checkIn);
        Double valorTotalGasto = totalValue + ultimoValorTotalEstadia; ;
        checkIn.setValorTotalEstadia(totalValue);
        checkIn.setValorDaUltimaEstadia(ultimoValorTotalEstadia);
        checkIn.setValorTotalGastoHotel(valorTotalGasto);


        return checkInRepository.save(checkIn);
    }

    public List<CheckIn> getHospedesAtHotel() {
        List<CheckIn> hospedesAtHotel = new ArrayList<>();
        List<CheckIn> allCheckIn = checkInRepository.findAll();
        Date actualDate = new Date();

        for (CheckIn checkIn : allCheckIn) {
            Date dataSaidaConverted = utils.convertStringInDate(checkIn.getHospede().getDataSaida());
            if (dataSaidaConverted.after(actualDate)) {
                hospedesAtHotel.add(checkIn);
            }
        }

        return hospedesAtHotel;
    }

    public List<CheckIn> getCheckoutHospedeAtHotel() {
        List<CheckIn> hospedesAtHotel = new ArrayList<>();
        List<CheckIn> allCheckIn = checkInRepository.findAll();
        Date actualDate = new Date();

        for (CheckIn checkIn : allCheckIn) {
            Date dataSaidaConverted = utils.convertStringInDate(checkIn.getHospede().getDataSaida());
            if (dataSaidaConverted.before(actualDate)) {
                hospedesAtHotel.add(checkIn);
            }
        }

        return hospedesAtHotel;
    }

    private CheckIn setCheckInFields(Hospede hospede) {
        CheckIn checkIn = new CheckIn();
        Date actualDate = new Date();

        checkIn.setHospede(hospede);
        checkIn.setDataEntrada(actualDate);
        checkIn.setDataSaida(utils.convertStringInDate(hospede.getDataSaida()));
        checkIn.setAdicionalVeiculo(hospede.getAdicionalVeiculo());

        return checkIn;
    }


    private double calculateTotalValue(CheckIn checkIn) {
        double valorDiariaSemana = 120.00;
        double valorDiariaFimDeSemana = 150.00;
        double totalValue = 0.0;

        int days = utils.calculateDays(checkIn.getDataEntrada(), checkIn.getDataSaida());

        for (int i = 0; i < days; i++) {
            Date currentDate = utils.addDays(checkIn.getDataEntrada(), i);
            int dayOfWeek = utils.getDayOfWeek(currentDate);

            if (utils.isWeekday(dayOfWeek)) {
                totalValue += calculateDailyValue(valorDiariaSemana, checkIn.getAdicionalVeiculo(), currentDate);
            } else {
                totalValue += calculateDailyValue(valorDiariaFimDeSemana, checkIn.getAdicionalVeiculo(), currentDate);
            }
//            if (dayOfWeek == Calendar.FRIDAY && utils.isAfterTime(currentDate, 16, 30)) {
//                totalValue += calculateDailyValue(valorDiariaFimDeSemana, checkIn.getAdicionalVeiculo(), currentDate);
//            }
        }

        return totalValue;
    }


    private double calculateDailyValue(double baseValue, boolean adicionalVeiculo, Date currentDate) {
        double additionalFee = adicionalVeiculo ? (utils.isWeekend(currentDate) ? 20.0 : 15.0) : 0.0;
        return baseValue + additionalFee;
    }


}
