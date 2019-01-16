package com.carbonit.clm.kata.bank_account;

import java.time.LocalDateTime;

public interface DateService {
    default LocalDateTime now() {
        return LocalDateTime.now();
    }
}
