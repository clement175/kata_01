package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.operation.Deposit;
import com.carbonit.clm.kata.bank_account.operation.Operation;
import com.carbonit.clm.kata.bank_account.operation.formatter.OperationFormatter;
import com.carbonit.clm.kata.bank_account.operation.Withdrawal;
import com.carbonit.clm.kata.bank_account.printers.Printer;
import com.carbonit.clm.kata.bank_account.repository.OperationRepository;

import java.math.BigDecimal;

class Account {
    private final OperationRepository operationRepository;
    private final DateService dateService;

    Account(OperationRepository operationRepository, DateService dateService) {
        this.operationRepository = operationRepository;
        this.dateService = dateService;
    }

    BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (Operation operation : operationRepository.findAll(true)) {
            balance = operation.performOperation(balance);
        }
        return balance;
    }

    void performDeposit(BigDecimal amount) {
        operationRepository.add(new Deposit(amount, dateService.now()));

    }

    void performWithdrawal(BigDecimal amount) {
        if (getBalance().compareTo(amount) >= 0) {
            operationRepository.add(new Withdrawal(amount, dateService.now()));
        } else {
            throw (new IllegalArgumentException("Insufficient balance"));
        }
    }

    void viewHistory(Printer printer, OperationFormatter operationFormatter, boolean ascendingDates) {

        this.operationRepository.findAll(ascendingDates)
                .forEach(operation -> printer.print(operationFormatter.formatOperation(operation)));
    }
}
