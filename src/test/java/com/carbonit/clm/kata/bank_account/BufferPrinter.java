package com.carbonit.clm.kata.bank_account;

import com.carbonit.clm.kata.bank_account.printers.Printer;

public class BufferPrinter implements Printer {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void print(String statement) {
        stringBuilder.append(statement);
        stringBuilder.append('\n');
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
