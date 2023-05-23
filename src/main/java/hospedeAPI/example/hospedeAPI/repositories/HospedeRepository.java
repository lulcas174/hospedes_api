package hospedeAPI.example.hospedeAPI.repositories;

import hospedeAPI.example.hospedeAPI.models.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer> {
}
