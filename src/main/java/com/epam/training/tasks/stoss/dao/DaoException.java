package com.epam.training.tasks.stoss.dao;

public class DaoException extends Exception {
    public DaoException(Throwable cause) {
        super(cause);
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
