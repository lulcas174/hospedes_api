package hospedeAPI.example.hospedeAPI.repositories;

import hospedeAPI.example.hospedeAPI.models.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer> {

    @Transactional(readOnly = true)
     List<Hospede> findAll();

    void deleteById(Integer id);

    Hospede findByDocumento(String documento);

}
