package com.carbonit.clm.kata.bank_account.repository;

import com.carbonit.clm.kata.bank_account.operation.Operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InMemoryOperationRepository implements OperationRepository {
    private final List<Operation> operations = new ArrayList<>();

    public void add(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> findAll(boolean ascendingDates) {
        List<Operation> sortedList = new ArrayList<>(this.operations);
        sortedList.sort((o1, o2) -> (ascendingDates ? 1 : -1) * o1.getDate().compareTo(o2.getDate()));
        return Collections.unmodifiableList(sortedList);
    }
}
