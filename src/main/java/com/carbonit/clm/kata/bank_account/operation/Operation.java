package com.carbonit.clm.kata.bank_account.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class  Operation {

    protected final LocalDateTime date;
    protected final BigDecimal amount;

    public Operation(BigDecimal amount, LocalDateTime date)  {
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw (new IllegalArgumentException("Operation with negative or zero amount is impossible"));
        }
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public abstract BigDecimal performOperation(BigDecimal balance);

    public abstract String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(date, operation.date) &&
                Objects.equals(amount, operation.amount);
    }
    @Override
    public int hashCode() {
        return Objects.hash(date, amount);
    }
}
