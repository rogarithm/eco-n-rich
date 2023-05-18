package kr.rogarithm.econrich.domain.historyInfo.domain;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public class JobHistory {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private Long departmentId;
}
