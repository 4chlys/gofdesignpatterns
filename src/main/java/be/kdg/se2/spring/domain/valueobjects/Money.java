package be.kdg.se2.spring.domain.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount), Currency.getInstance("EUR"));
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public Money multiply(double v) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(v)), currency);
    }
}
