package hospedeAPI.example.hospedeAPI.services;

import hospedeAPI.example.hospedeAPI.models.CheckIn;
import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.repositories.CheckInRepository;
import hospedeAPI.example.hospedeAPI.repositories.HospedeRepository;
import hospedeAPI.example.hospedeAPI.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service @Transactional
public class CheckInService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private Utils utils;

    public CheckIn createCheckIn(String documento) {
        Hospede hospede = hospedeRepository.findByDocumento(documento);
        if (hospede == null) {
            throw new NullPointerException("Hospede não encontrado");
        }

        CheckIn checkIn = setCheckInFields(hospede);

        double totalValue = calculateTotalValue(checkIn);
        checkIn.setValorTotalEstadia(totalValue);

        return checkInRepository.save(checkIn);
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

    private double calculateTotalValue(CheckIn checkIn){
        double valorDiariaSemana = 120.00;
        double valorDiariaFimDeSemana = 150.00;
        double totalValue = 0.0;

        int days = utils.calculateDays(checkIn.getDataEntrada(), checkIn.getDataSaida());

        for (int i = 0; i < days; i++) {
            Date currentDate = utils.addDays(checkIn.getDataEntrada(), i);
            int dayOfWeek = utils.getDayOfWeek(currentDate);

            if (utils.isWeekday(dayOfWeek)) {
                totalValue += calculateDailyValue(valorDiariaSemana, checkIn.getAdicionalVeiculo());
            } else {
                totalValue += calculateDailyValue(valorDiariaFimDeSemana, checkIn.getAdicionalVeiculo());
            }

            if (utils.isAfterTime(currentDate, 16, 30)) {
                totalValue += calculateDailyValue(valorDiariaSemana, checkIn.getAdicionalVeiculo());
            }
        }

        return totalValue;
    }


    private double calculateDailyValue(double baseValue, boolean adicionalVeiculo) {
        double additionalFee = adicionalVeiculo ? 15.0 : 0.0;
        return baseValue + additionalFee;
    }

}
