package kr.rogarithm.econrich.domain.employee.controller;

import kr.rogarithm.econrich.domain.employee.dto.DepartmentResponse;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employeeResponse);
    }

    @GetMapping("/{employeeId}/history")
    public ResponseEntity<JobHistoryResponse> getHistory(@PathVariable Long employeeId) {
        JobHistoryResponse jobHistory = employeeService.getJobHistoryById(employeeId);
        return ResponseEntity.ok().body(jobHistory);
    }

    @GetMapping("/{employeeId}/department")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable Long employeeId) {
        DepartmentResponse department = employeeService.getDepartmentById(employeeId);
        return ResponseEntity.ok().body(department);
    }

}
