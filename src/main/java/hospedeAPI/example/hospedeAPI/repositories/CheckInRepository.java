package hospedeAPI.example.hospedeAPI.repositories;

import hospedeAPI.example.hospedeAPI.models.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInRepository  extends JpaRepository<CheckIn, Integer> {

    CheckIn findByHospedeDocumentoCPF(String documentoCPF);

    List<CheckIn> findAllByHospedeDocumentoCPF(String documentoCPF);

}
