package kr.rogarithm.econrich.domain.employee.service;

import kr.rogarithm.econrich.domain.employee.dao.EmployeeMapper;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.employee.exception.EmployeeNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.JobHistoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeMapper.selectEmployeeById(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException("입력한 아이디 " + employeeId + "에 해당하는 직원 정보를 찾을 수 없습니다");
        }

        return EmployeeResponse.of(employee);
    }

    public JobHistoryResponse getJobHistoryById(Long employeeId) {
        JobHistory jobHistory = employeeMapper.selectJobHistoryById(employeeId);

        if (jobHistory == null) {
            throw new JobHistoryNotFoundException("입력한 아이디 " + employeeId + "에 해당하는 직원 이력을 찾을 수 없습니다");
        }

        return JobHistoryResponse.of(jobHistory);
    }
}
