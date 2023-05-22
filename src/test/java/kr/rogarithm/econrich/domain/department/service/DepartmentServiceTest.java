package kr.rogarithm.econrich.domain.department.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import kr.rogarithm.econrich.domain.department.dao.DepartmentMapper;
import kr.rogarithm.econrich.domain.department.dto.RaiseDepartmentSalaryResponse;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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
        Double raiseRate = 2.0;
        Long employeeId = 100L;

        JobHistory jobHistory = JobHistory.builder()
                                          .employeeId(employeeId)
                                          .startDate(LocalDate.parse("1987-09-17"))
                                          .endDate(LocalDate.parse("1993-06-17"))
                                          .jobId("AD_ASST")
                                          .departmentId(90L)
                                          .build();
        List<RaiseDepartmentSalaryResponse> responses = new ArrayList<>();
        responses.add(RaiseDepartmentSalaryResponse.builder()
                                                   .employeeId(100L)
                                                   .salaryBefore(BigDecimal.valueOf(4400.00))
                                                   .salaryAfter(BigDecimal.valueOf(8800.00))
                                                   .build());
        // when
        when(departmentMapper.selectJobHistories(departmentId)).thenReturn(List.of(jobHistory));
        when(departmentMapper.updateEmployeeSalary(employeeId, raiseRate)).thenReturn(1);
        when(departmentMapper.selectEmployeeSalary(employeeId)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) {
                    return BigDecimal.valueOf(4400.00);
                } else {
                    return BigDecimal.valueOf(8800.00);
                }
            }
        });

        List<RaiseDepartmentSalaryResponse> result = departmentService.raiseSalaryOfDepartment(departmentId, raiseRate);

        // then
        verify(departmentMapper).selectJobHistories(departmentId);
        verify(departmentMapper).updateEmployeeSalary(employeeId, raiseRate);
        Assertions.assertThat(result.get(0).getEmployeeId()).isEqualTo(responses.get(0).getEmployeeId());
        Assertions.assertThat(result.get(0).getSalaryBefore()).isEqualTo(responses.get(0).getSalaryBefore());
        Assertions.assertThat(result.get(0).getSalaryAfter()).isEqualTo(responses.get(0).getSalaryAfter());
    }

    @Test
    public void updateSalaryOfTwoEmployee() {
        // given
        Long departmentId = 90L;
        Double raiseRate = 1.1;

        Long employeeOneId = 100L;
        JobHistory jobHistory1 = JobHistory.builder()
                                           .employeeId(employeeOneId)
                                           .startDate(LocalDate.parse("1987-09-17"))
                                           .endDate(LocalDate.parse("1993-06-17"))
                                           .jobId("AD_ASST")
                                           .departmentId(departmentId)
                                           .build();
        Long employeeTwoId = 101L;
        JobHistory jobHistory2 = JobHistory.builder()
                                           .employeeId(employeeTwoId)
                                           .startDate(LocalDate.parse("1994-07-01"))
                                           .endDate(LocalDate.parse("1998-12-31"))
                                           .jobId("AC_ACCOUNT")
                                           .departmentId(departmentId)
                                           .build();

        List<RaiseDepartmentSalaryResponse> responses = new ArrayList<>();
        responses.add(RaiseDepartmentSalaryResponse.builder()
                                                   .employeeId(employeeOneId)
                                                   .salaryBefore(BigDecimal.valueOf(4400.00))
                                                   .salaryAfter(BigDecimal.valueOf(8800.00))
                                                   .build());
        responses.add(RaiseDepartmentSalaryResponse.builder()
                                                   .employeeId(employeeTwoId)
                                                   .salaryBefore(BigDecimal.valueOf(4400.00))
                                                   .salaryAfter(BigDecimal.valueOf(8800.00))
                                                   .build());
        // when
        when(departmentMapper.selectJobHistories(departmentId)).thenReturn(List.of(jobHistory1, jobHistory2));
        when(departmentMapper.updateEmployeeSalary(jobHistory1.getEmployeeId(), raiseRate)).thenReturn(1);
        when(departmentMapper.updateEmployeeSalary(jobHistory2.getEmployeeId(), raiseRate)).thenReturn(1);
        when(departmentMapper.selectEmployeeSalary(employeeOneId)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) {
                    return BigDecimal.valueOf(4400.00);
                } else {
                    return BigDecimal.valueOf(8800.00);
                }
            }
        });
        when(departmentMapper.selectEmployeeSalary(employeeTwoId)).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (++count == 1) {
                    return BigDecimal.valueOf(4400.00);
                } else {
                    return BigDecimal.valueOf(8800.00);
                }
            }
        });

        List<RaiseDepartmentSalaryResponse> result = departmentService.raiseSalaryOfDepartment(departmentId,
                raiseRate);

        // then
        verify(departmentMapper).selectJobHistories(departmentId);
        verify(departmentMapper).updateEmployeeSalary(jobHistory1.getEmployeeId(), raiseRate);
        verify(departmentMapper).updateEmployeeSalary(jobHistory2.getEmployeeId(), raiseRate);
        Assertions.assertThat(result.get(0).getEmployeeId()).isEqualTo(responses.get(0).getEmployeeId());
        Assertions.assertThat(result.get(0).getSalaryBefore()).isEqualTo(responses.get(0).getSalaryBefore());
        Assertions.assertThat(result.get(0).getSalaryAfter()).isEqualTo(responses.get(0).getSalaryAfter());
        Assertions.assertThat(result.get(1).getEmployeeId()).isEqualTo(responses.get(1).getEmployeeId());
        Assertions.assertThat(result.get(1).getSalaryBefore()).isEqualTo(responses.get(1).getSalaryBefore());
        Assertions.assertThat(result.get(1).getSalaryAfter()).isEqualTo(responses.get(1).getSalaryAfter());
    }
}