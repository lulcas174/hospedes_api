package hospedeAPI.example.hospedeAPI.services;


import hospedeAPI.example.hospedeAPI.exceptions.HospedeExistentingException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidCPFException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidDateException;
import hospedeAPI.example.hospedeAPI.exceptions.InvalidTelefoneFormatException;
import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.repositories.HospedeRepository;
import hospedeAPI.example.hospedeAPI.validators.ValidatorCPF;
import hospedeAPI.example.hospedeAPI.validators.ValidatorDate;
import hospedeAPI.example.hospedeAPI.validators.ValidatorTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service @Transactional
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    public Hospede save(Hospede hospede){
        String telefoneNoSpecialCharacters = hospede.getTelefone().replaceAll("[^0-9]", "");
        hospede.setTelefone(telefoneNoSpecialCharacters);

        String cpf = hospede.getDocumentoCPF().replaceAll("[^0-9]", "");
        hospede.setDocumentoCPF(cpf);

        if (hospedeRepository.existsByDocumentoCPF(hospede.getDocumentoCPF())) {
            throw new HospedeExistentingException("Hospede já cadastrado");
        }
        if(hospedeRepository.existsByTelefone(hospede.getTelefone())){
            throw new HospedeExistentingException("Hospede com esse telefone já cadastrado");
        }
        if (!validateHospedFields(hospede)){
            throw new IllegalArgumentException("Hospede inválido");
        }
        return hospedeRepository.save(hospede);
    }
    public Hospede update(Integer id,Hospede hospedeUpdated){
        Hospede hospedeExisting = hospedeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hospede não encontrado"));

        if (hospedeRepository.existsByDocumentoCPF(hospedeUpdated.getDocumentoCPF())) {
            throw new HospedeExistentingException("Hospede já cadastrado");
        }
        if(hospedeRepository.existsByDocumentoCPF(hospedeUpdated.getTelefone())){
            throw new HospedeExistentingException("Hospede com esse telefone já cadastrado");
        }


        validateHospedFields(hospedeUpdated);
        updateHospedeFields(hospedeExisting, hospedeUpdated);

        return hospedeRepository.save(hospedeExisting);
    }


    private void updateHospedeFields(Hospede hospedeExisting, Hospede hospedeUpdated) {
        if (hospedeUpdated.getNome() != null) {
            hospedeExisting.setNome(hospedeUpdated.getNome());
        }

        if (hospedeUpdated.getTelefone() != null) {
            hospedeExisting.setTelefone(hospedeUpdated.getTelefone());
        }

        if (hospedeUpdated.getDocumentoCPF() != null) {
            hospedeExisting.setDocumentoCPF(hospedeUpdated.getDocumentoCPF());
        }

        if (hospedeUpdated.getDataNascimento() != null) {
            hospedeExisting.setDataNascimento(hospedeUpdated.getDataNascimento());
        }

        if (hospedeUpdated.getDataSaida() != null) {
            hospedeExisting.setDataSaida(hospedeUpdated.getDataSaida());
        }

        if(hospedeUpdated.getAdicionalVeiculo() != null){
            hospedeExisting.setAdicionalVeiculo(hospedeUpdated.getAdicionalVeiculo());
        }
    }


    private Boolean validateHospedFields(Hospede hospede){
        if(hospede.getDocumentoCPF() != null) {
            validateCPF(hospede.getDocumentoCPF());
        }

        if(hospede.getDataNascimento() != null) {
            validateBirthdayDate(hospede.getDataNascimento());

        }

        if(hospede.getDataSaida() != null){
            validateCheckoutDate(hospede.getDataSaida());
        }

        if(hospede.getTelefone() != null){
            validateTelefone(hospede.getTelefone());
        }

        return true;
    }


    private void validateCPF(String cpf){
        ValidatorCPF validatorCPF = new ValidatorCPF();
        Boolean validCPFHospede = validatorCPF.isValidCPF(cpf);

        if(!validCPFHospede){
            throw new InvalidCPFException("CPF inválido");
        }
    }


    private void validateBirthdayDate(String birthdayDate){
        ValidatorDate validatorDate = new ValidatorDate();
        Boolean validBirthdayDateHospede = validatorDate.isValidDateFormat(birthdayDate);

        if(!validBirthdayDateHospede){
            throw new InvalidDateException("Data de aniversário inválida. Utilize dd/mm/yyyy");
        }
    }


    private void validateCheckoutDate(String checkoutDate){
        ValidatorDate validatorDate = new ValidatorDate();
        Boolean validDateCheckout = validatorDate.isValidDateFormat(checkoutDate);

        if(!validDateCheckout){
            throw new InvalidDateException("Data de checkout inválida. Utilize dd/mm/yyyy");
        }
    }


    private void validateTelefone(String telefone){
        ValidatorTelefone validatorTelefone = new ValidatorTelefone();
        Boolean validTelefone = validatorTelefone.isValidTelefone(telefone);

        if(!validTelefone){
            throw new InvalidTelefoneFormatException("Formato de telefone inválido");
        }
    }
}

