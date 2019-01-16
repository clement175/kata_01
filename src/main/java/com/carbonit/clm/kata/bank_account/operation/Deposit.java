package com.carbonit.clm.kata.bank_account.operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Deposit extends Operation {
    public Deposit(BigDecimal amount, LocalDateTime date) {
        super(amount, date);
    }

    public BigDecimal performOperation(BigDecimal balance) {
        return balance.add(amount);
    }

    @Override
    public String getType() {
        return "deposit";
    }
}
