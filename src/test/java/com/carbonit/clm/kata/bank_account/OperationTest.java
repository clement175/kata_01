package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.operation.Deposit;
import com.carbonit.clm.kata.bank_account.operation.Withdrawal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationTest {
    static final LocalDateTime NOW = LocalDateTime.of(2000, 1, 1 , 5 , 5);

    @Test
    @DisplayName("Should throw exception when a withdrawal is created with negative amount")
    void should_throw_exception_when_a_withdrawal_is_created_with_negative_amount(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Withdrawal(new BigDecimal(-5), NOW));
    }

    @Test
    @DisplayName("Should throw exception when a withdrawal is created with null amount")
    void should_throw_exception_when_a_withdrawal_is_created_with_null_amount(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Withdrawal(BigDecimal.ZERO, NOW));
    }

    @Test
    @DisplayName("Should throw exception when a deposit is created with negative amount")
    void should_throw_exception_when_a_deposit_is_created_with_negative_amount(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Deposit(new BigDecimal(-5), NOW));
    }

    @Test
    @DisplayName("Should throw exception when a deposit is created with null amount")
    void should_throw_exception_when_a_deposit_is_created_with_null_amount(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Deposit(BigDecimal.ZERO, NOW));
    }
}
