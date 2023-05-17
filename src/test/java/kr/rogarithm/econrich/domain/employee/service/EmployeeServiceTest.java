package kr.rogarithm.econrich.domain.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import kr.rogarithm.econrich.domain.employee.dao.EmployeeMapper;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeMapper employeeMapper;

    @Test
    public void validIdShouldGetEmployeeInfo() {

        // given
        Long id = 100L;
        Employee employee = Employee.builder()
                                    .id(100L)
                                    .firstName("Steven")
                                    .lastName("King")
                                    .email("SKING")
                                    .phoneNumber("515.123.4567")
                                    .hireDate(LocalDate.parse("1987-06-17"))
                                    .jobId("AD_PRES")
                                    .salary(BigDecimal.valueOf(24000.00))
                                    .departmentId(90L)
                                    .build();

        // when
        when(employeeMapper.selectEmployeeById(id))
                .thenReturn(employee);
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

        // then
        verify(employeeMapper).selectEmployeeById(id);
        assertNotNull(employeeResponse);
        assertEquals(id, employeeResponse.getId());
    }

    @Test
    public void invalidIdShouldThrowException() {

        // given
        Long id = -1L;

        // when
        when(employeeMapper.selectEmployeeById(id))
                .thenReturn(null);

        // then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(id));
        verify(employeeMapper).selectEmployeeById(id);
    }
}