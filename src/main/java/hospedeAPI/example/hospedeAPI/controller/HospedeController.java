package hospedeAPI.example.hospedeAPI.controller;

import hospedeAPI.example.hospedeAPI.models.Hospede;
import hospedeAPI.example.hospedeAPI.services.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hospedes")
@RestController
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;

    @PostMapping("/criar")
    public ResponseEntity<Hospede> salvarHospede(@RequestBody Hospede reqHospede){
        Hospede hospede = hospedeService.save(reqHospede);
        return new ResponseEntity<>(hospede, HttpStatus.CREATED);
    }
}
