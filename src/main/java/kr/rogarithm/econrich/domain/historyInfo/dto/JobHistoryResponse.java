package kr.rogarithm.econrich.domain.historyInfo.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class JobHistoryResponse {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private Long departmentId;
}
