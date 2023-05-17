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

        if (employee == null) {
            throw new IllegalArgumentException("입력한 아이디 " + employeeId + "에 해당하는 직원 정보를 찾을 수 없습니다");
        }

        return EmployeeResponse.of(employee);
    }
}
