package kr.rogarithm.econrich.domain.department.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
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
}