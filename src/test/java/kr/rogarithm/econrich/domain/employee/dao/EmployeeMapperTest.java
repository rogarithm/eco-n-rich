package kr.rogarithm.econrich.domain.employee.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kr.rogarithm.econrich.domain.employee.domain.Department;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void selectEmployeeByInvalidId() {
        Employee employee = employeeMapper.selectEmployeeById(-1L);

        assertEquals(null, employee);
    }

    @Test
    public void selectEmployeeByValidId() {
        Employee employee = employeeMapper.selectEmployeeById(100L);

        assertEquals(100L, employee.getId());
    }

    @Test
    public void selectJobHistoryByInvalidId() {
        JobHistory jobHistory = employeeMapper.selectJobHistoryById(-1L);

        assertEquals(null, jobHistory);
    }

    @Test
    public void selectJobHistoryByValidId() {
        JobHistory jobHistory = employeeMapper.selectJobHistoryById(102L);

        assertEquals(102L, jobHistory.getId());
    }

    @Test
    public void selectDepartmentByInvalidId() {
        Department department = employeeMapper.selectDepartmentById(-1L);

        assertEquals(null, department);
    }

    @Test
    public void selectDepartmentByValidId() {
        Department department = employeeMapper.selectDepartmentById(200L);

        assertEquals(10L, department.getId());
    }
}