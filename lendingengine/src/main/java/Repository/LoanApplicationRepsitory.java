package Repository;

import domain.model.LoanApplication;
import domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepsitory extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findAllByStatus(Status status);
}
