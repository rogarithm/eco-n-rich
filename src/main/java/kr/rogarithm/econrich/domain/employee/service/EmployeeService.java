package kr.rogarithm.econrich.domain.employee.service;

import kr.rogarithm.econrich.domain.employee.dao.EmployeeMapper;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeMapper.selectEmployeeById(employeeId);
        return EmployeeResponse.of(employee);
    }
}
