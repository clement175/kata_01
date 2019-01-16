package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.operation.formatter.OperationFormatter;
import com.carbonit.clm.kata.bank_account.operation.formatter.TypeAmountDateFormatter;
import com.carbonit.clm.kata.bank_account.printers.ConsolePrinter;
import com.carbonit.clm.kata.bank_account.printers.Printer;
import com.carbonit.clm.kata.bank_account.repository.InMemoryOperationRepository;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        InMemoryOperationRepository operationRepository = new InMemoryOperationRepository();
        InMemoryOperationRepository operationRepository2 = new InMemoryOperationRepository();
        OperationFormatter operationFormatter = new TypeAmountDateFormatter();
        Account account =  new Account(operationRepository, new DateService() {});
        final Account account2 =  new Account(operationRepository2, new DateService() {});
        Printer print = new ConsolePrinter();

        account.performDeposit(new BigDecimal(100));
        account2.performDeposit(new BigDecimal(200));
        System.out.println("current balance: "+account.getBalance().doubleValue());
        account.viewHistory(print, operationFormatter, true);
    }


}
