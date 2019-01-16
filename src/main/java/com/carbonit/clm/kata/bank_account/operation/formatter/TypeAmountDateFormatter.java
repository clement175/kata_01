package com.carbonit.clm.kata.bank_account.operation.formatter;

import com.carbonit.clm.kata.bank_account.operation.Operation;

import java.time.format.DateTimeFormatter;

public class TypeAmountDateFormatter implements OperationFormatter {

    private static final String TYPE_AMOUNT_DATE_TEMPLATE = "Operation: %s\nAmount: %s\nDate: %s\n";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String formatOperation(Operation operation) {
        String date = operation.getDate().format(DATE_FORMATTER);
        String amount = String.valueOf(operation.getAmount().doubleValue());
        return String.format(TYPE_AMOUNT_DATE_TEMPLATE, operation.getType(), amount, date);
    }
}
