package hospedeAPI.example.hospedeAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hospedeAPI.example.hospedeAPI.dto.CheckInDTO;
import hospedeAPI.example.hospedeAPI.models.CheckIn;
import hospedeAPI.example.hospedeAPI.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping("/api/v1/checkin")
@RestController
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("/realizar-novo-checkin")
    public CheckIn criarCheckIn(@RequestBody String reqDocument){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            CheckInDTO checkInDTO = objectMapper.readValue(reqDocument, CheckInDTO.class);
            String documentoCPF = checkInDTO.getDocumentoCPF();
            return checkInService.createCheckIn(documentoCPF);
        } catch (JsonProcessingException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro durante a deserialização do JSON", e);
        }
        return null;
    }

    @GetMapping("/hospedes-no-hotel")
    public List<CheckIn> getHospedeAtHotel(){
        return checkInService.getHospedesAtHotel();
    }

    @GetMapping("/hospedes-checkout")
    public List<CheckIn> getHospedeCheckout(){
        return checkInService.getCheckoutHospedeAtHotel();
    }


}
