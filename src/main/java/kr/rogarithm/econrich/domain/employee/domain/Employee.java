package kr.rogarithm.econrich.domain.employee.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDate;
    private String jobId;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private Long managerId;
    private Long departmentId;
}
