package kr.rogarithm.econrich.domain.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import kr.rogarithm.econrich.domain.department.dao.DepartmentMapper;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    DepartmentService departmentService;

    @Mock
    DepartmentMapper departmentMapper;

    @Test
    public void updateSalaryOfOneEmployee() {
        // given
        Long departmentId = 90L;
        Double raiseRate = 1.1;

        JobHistory jobHistory = JobHistory.builder()
                                          .employeeId(100L)
                                          .startDate(LocalDate.parse("1987-09-17"))
                                          .endDate(LocalDate.parse("1993-06-17"))
                                          .jobId("AD_ASST")
                                          .departmentId(90L)
                                          .build();

        // when
        when(departmentMapper.selectJobHistories(departmentId)).thenReturn(List.of(jobHistory));
        when(departmentMapper.updateEmployeeSalary(jobHistory.getEmployeeId(), raiseRate)).thenReturn(1);
        Integer result = departmentService.raiseSalaryOfDepartment(departmentId, raiseRate);

        // then
        verify(departmentMapper).selectJobHistories(departmentId);
        verify(departmentMapper).updateEmployeeSalary(jobHistory.getEmployeeId(), raiseRate);
        assertEquals(1, result);
    }

    @Test
    public void updateSalaryOfTwoEmployee() {
        // given
        Long departmentId = 90L;
        Double raiseRate = 1.1;

        JobHistory jobHistory1 = JobHistory.builder()
                                           .employeeId(100L)
                                           .startDate(LocalDate.parse("1987-09-17"))
                                           .endDate(LocalDate.parse("1993-06-17"))
                                           .jobId("AD_ASST")
                                           .departmentId(90L)
                                           .build();
        JobHistory jobHistory2 = JobHistory.builder()
                                           .employeeId(101L)
                                           .startDate(LocalDate.parse("1994-07-01"))
                                           .endDate(LocalDate.parse("1998-12-31"))
                                           .jobId("AC_ACCOUNT")
                                           .departmentId(90L)
                                           .build();

        // when
        when(departmentMapper.selectJobHistories(departmentId)).thenReturn(List.of(jobHistory1, jobHistory2));
        when(departmentMapper.updateEmployeeSalary(jobHistory1.getEmployeeId(), raiseRate)).thenReturn(1);
        when(departmentMapper.updateEmployeeSalary(jobHistory2.getEmployeeId(), raiseRate)).thenReturn(1);
        Integer result = departmentService.raiseSalaryOfDepartment(departmentId, raiseRate);

        // then
        verify(departmentMapper).selectJobHistories(departmentId);
        verify(departmentMapper).updateEmployeeSalary(jobHistory1.getEmployeeId(), raiseRate);
        verify(departmentMapper).updateEmployeeSalary(jobHistory2.getEmployeeId(), raiseRate);
        assertEquals(2, result);
    }
}