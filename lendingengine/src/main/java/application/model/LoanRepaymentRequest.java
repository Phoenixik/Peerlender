package application.model;

import domain.model.Currency;
import domain.model.Money;

import java.util.Objects;

public class LoanRepaymentRequest {
    private final double amount;
    private final Long LoanId;

    public LoanRepaymentRequest(double amount, Long loanId) {
        this.amount = amount;
        LoanId = loanId;
    }

    public Money getAmount() {
        return new Money(Currency.USD, amount);
    }

    public Long getLoanId() {
        return LoanId;
    }

    @Override
    public String toString() {
        return "LoanRepaymentRequest{" +
                "amount=" + amount +
                ", LoanId=" + LoanId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRepaymentRequest that = (LoanRepaymentRequest) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(LoanId, that.LoanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, LoanId);
    }
}
