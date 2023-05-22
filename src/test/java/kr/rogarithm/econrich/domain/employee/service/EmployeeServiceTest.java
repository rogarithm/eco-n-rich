package kr.rogarithm.econrich.domain.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import kr.rogarithm.econrich.domain.employee.dao.EmployeeMapper;
import kr.rogarithm.econrich.domain.employee.domain.Department;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.domain.Location;
import kr.rogarithm.econrich.domain.employee.dto.DepartmentResponse;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.EmployeeNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.JobHistoryNotFoundException;
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
    public void invalidIdShouldThrowEmployeeNotFoundException() {
        // given
        Long id = -1L;

        // when
        when(employeeMapper.selectEmployeeById(id))
                .thenReturn(null);

        // then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(id));
        verify(employeeMapper).selectEmployeeById(id);
    }

    @Test
    public void validIdShouldGetJobHistory() {
        // given
        Long id = 101L;
        JobHistory jobHistory = JobHistory.builder()
                                          .employeeId(101L)
                                          .startDate(LocalDate.parse("1989-09-21"))
                                          .endDate(LocalDate.parse("1993-10-27"))
                                          .jobId("AC_ACCOUNT")
                                          .departmentId(110L)
                                          .build();

        // when
        when(employeeMapper.selectJobHistoryById(id))
                .thenReturn(jobHistory);
        JobHistoryResponse jobHistoryResponse = employeeService.getJobHistoryById(id);

        // then
        verify(employeeMapper).selectJobHistoryById(id);
        assertNotNull(jobHistoryResponse);
        assertEquals(id, jobHistoryResponse.getEmployeeId());
    }

    @Test
    public void invalidIdShouldThrowJobHistoryNotFoundException() {
        // given
        Long id = -1L;

        // when
        when(employeeMapper.selectJobHistoryById(id))
                .thenReturn(null);

        // then
        assertThrows(JobHistoryNotFoundException.class, () -> employeeService.getJobHistoryById(id));
        verify(employeeMapper).selectJobHistoryById(id);
    }

    @Test
    public void invalidIdShouldThrowDepartmentNotFoundException() {
        // given
        Long id = -1L;

        // when
        when(employeeMapper.selectDepartmentById(id))
                .thenReturn(null);

        // then
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.getDepartmentById(id));
        verify(employeeMapper).selectDepartmentById(id);
    }


    @Test
    public void validIdShouldGetDepartmentAndLocation() {
        // given
        Long employeeId = 201L;
        Long departmentId = 20L;
        Long locationId = 1800L;
        Department department = Department.builder()
                                          .id(departmentId)
                                          .departmentName("Marketing")
                                          .managerId(201L)
                                          .locationId(1800L)
                                          .build();
        Location location = Location.builder()
                                    .id(locationId)
                                    .streetAddress("147 Spadina Ave")
                                    .postalCode("M5V 2L7")
                                    .city("Toronto")
                                    .stateProvince("Ontario")
                                    .countryId("CA")
                                    .build();

        // when
        when(employeeMapper.selectDepartmentById(employeeId)).thenReturn(department);
        when(employeeMapper.selectLocationById(employeeId)).thenReturn(location);
        DepartmentResponse departmentAndLocation = employeeService.getDepartmentById(employeeId);

        // then
        verify(employeeMapper).selectDepartmentById(employeeId);
        verify(employeeMapper).selectLocationById(employeeId);
        assertNotNull(departmentAndLocation);
        assertEquals(departmentId, departmentAndLocation.getId());
    }
}