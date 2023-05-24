package hospedeAPI.example.hospedeAPI.controller;

import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.repositories.HospedeRepository;
import hospedeAPI.example.hospedeAPI.services.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/hospedes")
@RestController
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;

    @Autowired
    private HospedeRepository hospedeRepository;

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Hospede>> listHospedes(){
        Iterable<Hospede> hospedes = hospedeRepository.findAll();
        return new ResponseEntity<>(hospedes, HttpStatus.OK);
    }
    @PostMapping("/criar")
    public ResponseEntity<Hospede> saveHospedes(@RequestBody Hospede reqHospede){
        Hospede hospede = hospedeService.save(reqHospede);
        return new ResponseEntity<>(hospede, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Hospede> updateHospedes(@PathVariable Integer id, @RequestBody Hospede reqHospede){
        Hospede hospede = hospedeService.update(id, reqHospede);
        return new ResponseEntity<>(hospede, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deleteHospedes(@PathVariable Integer id){
        hospedeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
