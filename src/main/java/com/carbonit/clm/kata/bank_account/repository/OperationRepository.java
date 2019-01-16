package com.carbonit.clm.kata.bank_account.repository;

import com.carbonit.clm.kata.bank_account.operation.Operation;

import java.util.List;

public interface OperationRepository {
    void add(Operation operation);
    List<Operation> findAll(boolean ascendingDates);
}
