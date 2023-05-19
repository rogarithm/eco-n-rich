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
import kr.rogarithm.econrich.domain.historyInfo.dao.HistoryInfoMapper;
import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.historyInfo.exception.JobHistoryNotFoundException;
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

    @Mock
    HistoryInfoMapper historyInfoMapper;

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
                                          .id(101L)
                                          .startDate(LocalDate.parse("1989-09-21"))
                                          .endDate(LocalDate.parse("1993-10-27"))
                                          .jobId("AC_ACCOUNT")
                                          .departmentId(110L)
                                          .build();

        // when
        when(historyInfoMapper.selectJobHistoryById(id))
                .thenReturn(jobHistory);
        JobHistoryResponse jobHistoryResponse = employeeService.getJobHistoryById(id);

        // then
        verify(historyInfoMapper).selectJobHistoryById(id);
        assertNotNull(jobHistoryResponse);
        assertEquals(id, jobHistoryResponse.getId());
    }

    @Test
    public void invalidIdShouldThrowJobHistoryNotFoundException() {

        // given
        Long id = -1L;

        // when
        when(historyInfoMapper.selectJobHistoryById(id))
                .thenReturn(null);

        // then
        assertThrows(JobHistoryNotFoundException.class, () -> employeeService.getJobHistoryById(id));
        verify(historyInfoMapper).selectJobHistoryById(id);
    }
}