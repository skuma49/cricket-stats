package com.sushavi.stats.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String message;
    private Date date;
}
