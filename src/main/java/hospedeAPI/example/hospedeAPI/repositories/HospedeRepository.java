package hospedeAPI.example.hospedeAPI.repositories;

import hospedeAPI.example.hospedeAPI.models.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer> {
    void deleteById(Integer id);

    Hospede findByDocumentoCPF(String documentoCPF);

    boolean existsByDocumentoCPF(String documentoCPF);

    boolean existsByTelefone(String telefone);

}
