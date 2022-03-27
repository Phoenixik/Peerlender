package domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  int amount;
    @ManyToOne
    private User borrower;
    private  Long repaymentTerm;
    private  double interestRate;
    private Status status;


    public LoanApplication(int amount, User borrower, Long repaymentTerm, double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTerm = repaymentTerm;
        this.interestRate = interestRate;
        this.status = status.ONGOING;
    }
    public Loan acceptLoanApplication(User lender) {
        lender.withdraw(getAmount());
        borrower.topUp(getAmount());
        status= status.COMPLETED;
        return new Loan(lender, this);
    }

    public LoanApplication() {
    }

    public long getId() {
        return id;
    }

    public Money getAmount() {
        return new Money(Currency.USD, amount);
    }

    public User getBorrower() {
        return borrower;
    }

    public Long getRepaymentTerm() {
        return repaymentTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTerm, that.repaymentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrower, repaymentTerm, interestRate);
    }

    @Override
    public String toString() {
        return "Loanrequest{" +
                "id=" + id +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTerm +
                ", interestRate=" + interestRate +
                '}';
    }
}
