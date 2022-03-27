package Repository;

import domain.model.Loan;
import domain.model.Status;
import domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByBorrowerAndStatus(User borrower, Status status);
    List<Loan> findAllByLenderAndStatus(User lender, Status status);
    Optional<Loan> findOneByIdAndBorrower(Long id, User borrower);
}