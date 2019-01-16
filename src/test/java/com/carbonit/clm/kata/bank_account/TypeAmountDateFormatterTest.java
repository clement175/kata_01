package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.operation.Deposit;
import com.carbonit.clm.kata.bank_account.operation.Withdrawal;
import com.carbonit.clm.kata.bank_account.operation.formatter.OperationFormatter;
import com.carbonit.clm.kata.bank_account.operation.formatter.TypeAmountDateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.carbonit.clm.kata.bank_account.OperationTest.NOW;

 class TypeAmountDateFormatterTest {

    @Test
    @DisplayName("Should build a string from a withdrawal operation using the type amount date template")
    void should_build_a_string_from_a_withdrawal_operation_using_the_type_amount_date_template(){
        Withdrawal  withdrawal = new Withdrawal(new BigDecimal(100), NOW);
        OperationFormatter operationFormatter = new TypeAmountDateFormatter();

        String expectedStringOutput = "Operation: withdrawal\nAmount: 100.0\nDate: 01-01-2000 05:05:00\n";
        Assertions.assertEquals(expectedStringOutput, operationFormatter.formatOperation(withdrawal));
    }

    @Test
    @DisplayName("Should build a string from a deposit operation using the type amount date template")
    void should_build_a_string_from_a_deposit_operation_using_the_type_amount_date_template(){

        Deposit deposit = new Deposit(new BigDecimal(100), NOW);
        OperationFormatter operationFormatter = new TypeAmountDateFormatter();

        String expectedStringOutput = "Operation: deposit\nAmount: 100.0\nDate: 01-01-2000 05:05:00\n";
        Assertions.assertEquals(expectedStringOutput, operationFormatter.formatOperation(deposit));
    }
}
