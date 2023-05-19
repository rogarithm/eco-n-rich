package kr.rogarithm.econrich.domain.employee.dto;

import java.time.LocalDate;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobHistoryResponse {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private Long departmentId;

    public static JobHistoryResponse of(JobHistory jobHistory) {
        return JobHistoryResponse.builder()
                                 .id(jobHistory.getId())
                                 .startDate(jobHistory.getStartDate())
                                 .endDate(jobHistory.getEndDate())
                                 .jobId(jobHistory.getJobId())
                                 .departmentId(jobHistory.getDepartmentId())
                                 .build();
    }
}
