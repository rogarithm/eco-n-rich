package kr.rogarithm.econrich.domain.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import lombok.Builder;

@Builder
public class EmployeeResponse {

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

    public static EmployeeResponse of(Employee employee) {
        return EmployeeResponse.builder()
                               .id(employee.getId())
                               .firstName(employee.getFirstName())
                               .lastName(employee.getLastName())
                               .email(employee.getEmail())
                               .phoneNumber(employee.getPhoneNumber())
                               .hireDate(employee.getHireDate())
                               .jobId(employee.getJobId())
                               .salary(employee.getSalary())
                               .commissionPct(employee.getCommissionPct())
                               .managerId(employee.getManagerId())
                               .departmentId(employee.getDepartmentId())
                               .build();
    }
}
