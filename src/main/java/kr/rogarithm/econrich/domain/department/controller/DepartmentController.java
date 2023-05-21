package kr.rogarithm.econrich.domain.department.controller;

import kr.rogarithm.econrich.domain.department.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PutMapping("")
    public ResponseEntity<Void> raiseSalaryOfDepartment(
            @RequestParam Long departmentId,
            @RequestParam Double raiseRate) {
        departmentService.raiseSalaryOfDepartment(departmentId, raiseRate);
        return ResponseEntity.ok().build();
    }
}
