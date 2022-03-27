package application.model;

import domain.model.Money;
import domain.model.User;

import java.util.Objects;

public class LoanApplicationDTO {

    private long id;
    private Money amount;
    private User borrower;
    private int repaymentTermInDays;
    private double interestRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplicationDTO that = (LoanApplicationDTO) o;
        return repaymentTermInDays == that.repaymentTermInDays && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, borrower, repaymentTermInDays, interestRate);
    }

    @Override
    public String toString() {
        return "LoanApplicationDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + borrower +
                ", repaymentTermInDays=" + repaymentTermInDays +
                ", interestRate=" + interestRate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public int getRepaymentTermInDays() {
        return repaymentTermInDays;
    }

    public void setRepaymentTermInDays(int repaymentTermInDays) {
        this.repaymentTermInDays = repaymentTermInDays;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
