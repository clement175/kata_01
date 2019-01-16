package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.operation.Deposit;
import com.carbonit.clm.kata.bank_account.operation.formatter.OperationFormatter;
import com.carbonit.clm.kata.bank_account.operation.formatter.TypeAmountDateFormatter;
import com.carbonit.clm.kata.bank_account.operation.Withdrawal;
import com.carbonit.clm.kata.bank_account.printers.Printer;
import com.carbonit.clm.kata.bank_account.repository.InMemoryOperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

 class AccountTests {

   private Account account;
   private InMemoryOperationRepository operationRepository;
   private OperationFormatter operationFormatter;
   private LocalDateTime date;

    @BeforeEach
    void TestInitialisation(){
        this.operationRepository = new InMemoryOperationRepository();
        this.operationFormatter = new TypeAmountDateFormatter();
        DateService dateService = new DateService() {
            @Override
            public LocalDateTime now() {
                return LocalDateTime.of(2000, 1, 1 , 5 , 5);
            }
        };
        this.date = dateService.now();
        this.account = new Account(operationRepository, dateService);
    }


    @Test
    @DisplayName("Should add to balance sum of deposit in operation list")
    void should_add_to_balance_sum_of_deposit_in_operation_list() {
        final BigDecimal expected = new BigDecimal("80.27");

        operationRepository.add(new Deposit(new BigDecimal("50.12"), date));
        operationRepository.add(new Deposit(new BigDecimal("30.15"), date));

        Assertions.assertEquals(expected,account.getBalance() );
    }

    @Test
    @DisplayName("When a deposit is performed it should be added to the list")
    void when_a_deposit_is_performed_it_should_be_added_to_the_list() {
        Deposit expected = new Deposit(new BigDecimal(100), date);

        account.performDeposit(new BigDecimal(100));

        Assertions.assertEquals(expected, operationRepository.findAll(true).get(0) );
    }

    @Test
    @DisplayName("Should not perform withdrawal if balance is inferior to amount")
    void should_not_perform_withdrawal_if_balance_is_inferior_to_amount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.performWithdrawal(new BigDecimal(50)));
        Assertions.assertTrue(operationRepository.findAll(true).isEmpty());
    }


    @Test
    @DisplayName("When a withdrawal is performed it should be added to the list")
    void when_a_withdrawal_is_performed_it_should_be_added_to_the_list(){
        Deposit initialDeposit = new Deposit(new BigDecimal(100), date);
        operationRepository.add(initialDeposit);
        Withdrawal expected = new Withdrawal(new BigDecimal(50), date);

        account.performWithdrawal(new BigDecimal(50));

        Assertions.assertEquals(expected, operationRepository.findAll(true).get(1));
    }

    @Test
    @DisplayName("Should subtract from balance amounts of withdrawals in operation list")
    void should_subtract_from_balance_amounts_of_withdrawals_in_operation_list(){
        final BigDecimal expected = new BigDecimal("30.22");

        operationRepository.add(new Deposit(new BigDecimal("100.65"), date));
        operationRepository.add(new Withdrawal(new BigDecimal("50.31"), date));
        operationRepository.add(new Withdrawal(new BigDecimal("20.12"), date));

        final BigDecimal actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should display operations history from latest to earliest")
    void should_display_operation_history_from_latest_to_earliest(){
        Printer print = new BufferPrinter();
        operationRepository.add(new Deposit(new BigDecimal(300), date));
        operationRepository.add(new Withdrawal(new BigDecimal(100), date.plusMinutes(10)));
        operationRepository.add(new Withdrawal(new BigDecimal(100), date.plusMinutes(20)));
        String expectedTextOutput = "Operation: deposit\nAmount: 300.0\nDate: 01-01-2000 05:05:00\n\nOperation: withdrawal\nAmount: 100.0\nDate: 01-01-2000 05:15:00" +
                "\n\nOperation: withdrawal\nAmount: 100.0\nDate: 01-01-2000 05:25:00\n\n";

        account.viewHistory(print, operationFormatter, true);

        Assertions.assertEquals(expectedTextOutput, print.toString());
    }

    @Test
    @DisplayName("Should display operations history from earliest to latest")
    void should_display_operation_history_from_earliest_to_latest(){
        Printer print = new BufferPrinter();
        operationRepository.add(new Deposit(new BigDecimal(300), date));
        operationRepository.add(new Withdrawal(new BigDecimal(100), date.plusMinutes(10)));
        operationRepository.add(new Withdrawal(new BigDecimal(100), date.plusMinutes(20)));

        String expectedTextOutput ="Operation: withdrawal\nAmount: 100.0\nDate: 01-01-2000 05:25:00\n\nOperation: withdrawal\nAmount: 100.0\nDate: 01-01-2000 05:15:00" +
                "\n\nOperation: deposit\nAmount: 300.0\nDate: 01-01-2000 05:05:00\n\n";

        account.viewHistory(print, operationFormatter, false);

        Assertions.assertEquals(expectedTextOutput, print.toString());
    }










}
