package kr.rogarithm.econrich.domain.employee.domain;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobHistory {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private Long departmentId;
}
