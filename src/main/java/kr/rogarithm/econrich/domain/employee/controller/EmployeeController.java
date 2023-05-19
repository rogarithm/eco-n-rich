package kr.rogarithm.econrich.domain.employee.controller;

import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.service.EmployeeService;
import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.historyInfo.service.HistoryInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final HistoryInfoService historyInfoService;

    public EmployeeController(EmployeeService employeeService, HistoryInfoService historyInfoService) {
        this.employeeService = employeeService;
        this.historyInfoService = historyInfoService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @GetMapping("/{employeeId}/historyInfo")
    public ResponseEntity<JobHistoryResponse> getHistoryInfo(@PathVariable Long employeeId) {

        JobHistoryResponse jobHistory = historyInfoService.getJobHistoryById(employeeId);
        return ResponseEntity.ok().body(jobHistory);
    }
}
