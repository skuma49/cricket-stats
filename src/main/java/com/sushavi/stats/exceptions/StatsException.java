package com.sushavi.stats.exceptions;

public class StatsException extends RuntimeException{

        public StatsException(String message) {
            super(message);
        }

        public StatsException(String message, Throwable cause) {
            super(message, cause);
        }
}
