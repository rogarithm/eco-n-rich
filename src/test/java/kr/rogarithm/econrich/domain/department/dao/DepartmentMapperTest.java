package kr.rogarithm.econrich.domain.department.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DepartmentMapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void selectJobHistoryByInvalidId() {
        List<JobHistory> jobHistories = departmentMapper.selectJobHistories(-1L);

        assertTrue(jobHistories.isEmpty());
    }

    @Test
    public void selectJobHistoryWithOneJobIdByValidId() {
        List<JobHistory> jobHistories = departmentMapper.selectJobHistories(60L);

        assertEquals(60L, jobHistories.get(0).getDepartmentId());
    }

    @Test
    public void selectJobHistoryWithTwoJobIdByValidId() {
        List<JobHistory> jobHistories = departmentMapper.selectJobHistories(110L);

        for (JobHistory jobHistory : jobHistories) {
            assertEquals(110L, jobHistory.getDepartmentId());
        }
    }

    @Test
    public void updateSalaryOfOneEmployee() {
        BigDecimal salaryBeforeRaise = BigDecimal.valueOf(4400.00);
        Employee employee = Employee.builder()
                                    .id(200L)
                                    .firstName("Jennifer")
                                    .lastName("Whalen")
                                    .email("JWHALEN")
                                    .phoneNumber("515.123.4444")
                                    .hireDate(LocalDate.parse("1987-09-17"))
                                    .jobId("AD_ASST")
                                    .salary(salaryBeforeRaise)
                                    .commissionPct(BigDecimal.valueOf(101))
                                    .departmentId(10L)
                                    .build();

        Integer updatedRow = departmentMapper.updateEmployeeSalary(employee.getId(), 2.0);
        BigDecimal salaryAfterRaise = departmentMapper.selectEmployeeSalary(employee.getId());

        Assertions.assertThat(updatedRow).isEqualTo(1);
        Assertions.assertThat(salaryAfterRaise).isEqualTo(salaryBeforeRaise.multiply(BigDecimal.valueOf(2.0)));

        // rollback
        departmentMapper.updateEmployeeSalary(employee.getId(), 0.5);
    }
}