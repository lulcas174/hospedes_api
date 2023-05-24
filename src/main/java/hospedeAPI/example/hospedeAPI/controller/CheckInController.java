package hospedeAPI.example.hospedeAPI.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hospedeAPI.example.hospedeAPI.dto.CheckInDTO;
import hospedeAPI.example.hospedeAPI.models.CheckIn;
import hospedeAPI.example.hospedeAPI.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/checkin")
@RestController
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("/realizar-checkin")
    public CheckIn criarCheckIn(@RequestBody String reqDocument){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            CheckInDTO checkInDTO = objectMapper.readValue(reqDocument, CheckInDTO.class);
            String documento = checkInDTO.getDocumento();
            return checkInService.createCheckIn(documento);
        } catch (JsonProcessingException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro durante a deserialização do JSON", e);
        }
        return null;
    }
}
