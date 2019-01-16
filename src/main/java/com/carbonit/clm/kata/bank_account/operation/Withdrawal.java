package com.carbonit.clm.kata.bank_account.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Withdrawal extends Operation {
    public Withdrawal(BigDecimal amount, LocalDateTime date) {
        super(amount, date);
    }

    public BigDecimal performOperation(BigDecimal balance) {
        return balance.subtract(amount);
    }

    @Override
    public String getType() {
        return "withdrawal";
    }
}
