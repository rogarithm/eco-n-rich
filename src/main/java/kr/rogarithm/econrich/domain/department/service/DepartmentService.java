package kr.rogarithm.econrich.domain.department.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kr.rogarithm.econrich.domain.department.dao.DepartmentMapper;
import kr.rogarithm.econrich.domain.department.dto.RaiseDepartmentSalaryResponse;
import kr.rogarithm.econrich.domain.department.exception.InvalidRaiseRateException;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Transactional
    public List<RaiseDepartmentSalaryResponse> raiseSalaryOfDepartment(Long departmentId, Double raiseRate) {

        if (raiseRate < 0) {
            throw new InvalidRaiseRateException("유효하지 않은 급여 인상률 " + raiseRate + "을 입력하셨습니다. 급여 인상률은 0보다 커야 합니다");
        }

        List<JobHistory> jobHistories = departmentMapper.selectJobHistories(departmentId);

        if (jobHistories.isEmpty()) {
            throw new DepartmentNotFoundException("입력한 부서 아이디 " + departmentId + "에 해당하는 부서를 찾을 수 없습니다");
        }

        List<RaiseDepartmentSalaryResponse> result = new ArrayList<>();
        for (JobHistory jobHistory : jobHistories) {
            Long employeeId = jobHistory.getEmployeeId();
            BigDecimal salaryBefore = departmentMapper.selectEmployeeSalary(employeeId);

            departmentMapper.updateEmployeeSalary(employeeId, raiseRate);

            BigDecimal salaryAfter = departmentMapper.selectEmployeeSalary(employeeId);
            RaiseDepartmentSalaryResponse response = RaiseDepartmentSalaryResponse.builder()
                                                                                  .employeeId(employeeId)
                                                                                  .salaryBefore(salaryBefore)
                                                                                  .salaryAfter(salaryAfter)
                                                                                  .build();
            result.add(response);
        }

        return result;
    }
}
