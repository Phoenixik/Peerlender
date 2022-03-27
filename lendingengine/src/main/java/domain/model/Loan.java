package domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;
    @ManyToOne
    @JoinColumn(name = "lender_id")
    private User lender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amount_id")
    private Money loanamount;
    private double interestRate;
    private LocalDate datelength;
    private LocalDate dateDue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amount_repayed_id")
    private Money amountRepayed;
    private Status status;

    public Money getAmountOwed() {
        return loanamount.times(1+interestRate/100).minus(amountRepayed);
    }

    public void repay(Money money) {
        borrower.withdraw(money);
        lender.topUp(money);
        amountRepayed = amountRepayed.add(money);

        if(getAmountOwed().equals(new Money(Currency.USD, 0))) {
            status = status.COMPLETED;
        }
    }

    public Money getAmountRepayed() {
        return amountRepayed;
    }

    public User getBorrower() {
        return borrower;
    }

    public User getLender() {
        return lender;
    }

    public Loan(User lender, LoanApplication loanApplication) {
        this.lender = lender;
        this.borrower = loanApplication.getBorrower();
        this.loanamount = loanApplication.getAmount();
        this.interestRate = loanApplication.getInterestRate();
        this.datelength = LocalDate.now();
        this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentTerm());
        this.amountRepayed = new Money(Currency.USD, 0);
        this.status = Status.ONGOING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan() {
    }

    public Money getLoanamount() {
        return loanamount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public LocalDate getDatelength() {
        return datelength;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrower=" + borrower +
                ", lender=" + lender +
                ", loanamount=" + loanamount +
                ", interestRate=" + interestRate +
                ", datelength=" + datelength +
                ", dateDue=" + dateDue +
                ", amountRepayed=" + amountRepayed +
                ", status=" + status +
                '}';
    }
}