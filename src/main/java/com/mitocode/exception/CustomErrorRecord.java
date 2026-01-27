package com.mitocode.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomErrorRecord(
        LocalDateTime datetime,
        String message,
        String details
) {
}
