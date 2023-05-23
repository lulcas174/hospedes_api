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

    @Transactional(readOnly = true)
    public List<Hospede> findAll(){
        return hospedeRepository.findAll();
    }

    public Hospede save(Hospede hospede){
        hospede.setValorTotalEstadia(BigDecimal.ZERO);
        return hospedeRepository.save(hospede);
    }

    public Hospede update(Integer id,Hospede hospedeUpdated){
        if(!hospedeRepository.existsById(id)){
            throw new NoSuchElementException("Hospede não encontrado");
        }
        Hospede hospedeExisting = hospedeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hospede não encontrado"));

        hospedeExisting.setNome(hospedeUpdated.getNome());
        hospedeExisting.setTelefone(hospedeUpdated.getTelefone());
        hospedeExisting.setDocumento(hospedeUpdated.getDocumento());
        hospedeExisting.setAniversario(hospedeUpdated.getAniversario());
        hospedeExisting.setDataEntrada(hospedeUpdated.getDataEntrada());
        hospedeExisting.setDataSaida(hospedeUpdated.getDataSaida());

        return hospedeRepository.save(hospedeExisting);
    }


}

