package kr.rogarithm.econrich.domain.historyInfo.controller;

import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.historyInfo.service.HistoryInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historyInfo")
public class HistoryInfoController {

    private final HistoryInfoService historyInfoService;

    public HistoryInfoController(HistoryInfoService historyInfoService) {
        this.historyInfoService = historyInfoService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<JobHistoryResponse> getHistoryInfo(@PathVariable Long employeeId) {

        JobHistoryResponse jobHistory = historyInfoService.getJobHistoryById(employeeId);
        return ResponseEntity.ok().body(jobHistory);
    }
}
