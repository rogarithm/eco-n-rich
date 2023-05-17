package kr.rogarithm.econrich.domain.employee.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kr.rogarithm.econrich.domain.employee.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void selectEmployeeByValidId() {
        Employee employee = employeeMapper.selectEmployeeById(100L);

        assertEquals(100L, employee.getId());
    }

    @Test
    public void selectEmployeeByInvalidId() {
        Employee employee = employeeMapper.selectEmployeeById(-1L);

        assertEquals(null, employee);
    }

}