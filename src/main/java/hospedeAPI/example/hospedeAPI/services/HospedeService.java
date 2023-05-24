package hospedeAPI.example.hospedeAPI.services;


import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.repositories.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service @Transactional
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    public Hospede save(Hospede hospede){
        return hospedeRepository.save(hospede);
    }

    public Hospede update(Integer id,Hospede hospedeUpdated){
        if(!hospedeRepository.existsById(id)){
            throw new NoSuchElementException("Hospede não encontrado");
        }
        Hospede hospedeExisting = hospedeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hospede não encontrado"));

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

        if (hospedeUpdated.getDocumento() != null) {
            hospedeExisting.setDocumento(hospedeUpdated.getDocumento());
        }

        if (hospedeUpdated.getAniversario() != null) {
            hospedeExisting.setAniversario(hospedeUpdated.getAniversario());
        }

        if (hospedeUpdated.getDataSaida() != null) {
            hospedeExisting.setDataSaida(hospedeUpdated.getDataSaida());
        }

        if(hospedeUpdated.getAdicionalVeiculo() != null){
            hospedeExisting.setAdicionalVeiculo(hospedeUpdated.getAdicionalVeiculo());
        }
    }
}

