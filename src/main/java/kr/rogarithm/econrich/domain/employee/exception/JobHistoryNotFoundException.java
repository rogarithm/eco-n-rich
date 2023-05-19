package kr.rogarithm.econrich.domain.employee.exception;

public class JobHistoryNotFoundException extends RuntimeException {

    public JobHistoryNotFoundException(String message) {
        super(message);
    }
}
