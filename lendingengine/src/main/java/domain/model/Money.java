package domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
public class Money {



//    @ManyToOne
//    public static final Money ZERO = new Money(Currency.USD,0 );

   @Id @GeneratedValue
    private long id;

    private Currency currency;
    private BigDecimal amount;

//    public Money getZERO() {
//        return ZERO;
//    }

    public Money() {
    }

    public Money(Currency currency, double amount) {
        this.currency = currency;
        this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_DOWN);
    }
    public Money times(double multiplier) {
        return new Money(currency, amount.doubleValue()*multiplier);
    }
    public Money add(Money money) {
        if (currency != money.getCurrency()) {
            throw new IllegalArgumentException();
        } else {
            return new Money(currency, amount.doubleValue() + money.getAmount());
        }
    }
    public Money minus(Money money) {
        if(currency != money.getCurrency() || amount.doubleValue() < money.getAmount()) {
            throw new IllegalArgumentException();
        }else{
            return new Money(currency, amount.doubleValue() - money.getAmount());
        }
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return currency == money.currency && Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount.doubleValue();
    }
}
