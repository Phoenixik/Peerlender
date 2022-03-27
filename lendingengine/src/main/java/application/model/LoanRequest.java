package application.model;

import java.time.Duration;
import java.util.Objects;

public class LoanRequest {

    private int amount;
    private Long daysToRepay;
    private double interestRate;

    public LoanRequest(int amount, Long daysToRepay, double interestRate) {
        this.amount = amount;
        this.daysToRepay = daysToRepay;
        this.interestRate = interestRate;
    }

    public int getAmount() {
        return amount;
    }

    public Long getDaysToRepay() {
        return daysToRepay;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return amount == that.amount && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(daysToRepay, that.daysToRepay);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", daysToRepay=" + daysToRepay +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, daysToRepay, interestRate);
    }
}
