package com.carbonit.clm.kata.bank_account.printers;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String statement) {
        System.out.println(statement);
    }
}
