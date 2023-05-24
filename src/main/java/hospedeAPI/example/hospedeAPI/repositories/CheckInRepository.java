package hospedeAPI.example.hospedeAPI.repositories;

import hospedeAPI.example.hospedeAPI.models.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CheckInRepository  extends JpaRepository<CheckIn, Integer> {
}
