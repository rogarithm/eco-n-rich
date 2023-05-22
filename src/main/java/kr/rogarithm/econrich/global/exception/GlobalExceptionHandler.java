package kr.rogarithm.econrich.global.exception;

import kr.rogarithm.econrich.domain.department.exception.InvalidRaiseRateException;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.EmployeeNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.JobHistoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<Void> employeeNotFoundException(EmployeeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(JobHistoryNotFoundException.class)
    protected ResponseEntity<Void> jobHistoryNotFoundException(JobHistoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    protected ResponseEntity<Void> departmentNotFoundException(DepartmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(InvalidRaiseRateException.class)
    protected ResponseEntity<Void> invalidRaiseRateException(InvalidRaiseRateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
