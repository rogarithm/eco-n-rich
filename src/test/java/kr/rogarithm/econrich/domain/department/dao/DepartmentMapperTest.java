package kr.rogarithm.econrich.domain.department.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        JobHistory jobHistory = departmentMapper.selectJobHistoryById(-1L);

        assertEquals(null, jobHistory);
    }

    @Test
    public void selectJobHistoryWithOneJobIdByValidId() {
        JobHistory jobHistory = departmentMapper.selectJobHistoryById(60L);

        assertEquals(60L, jobHistory.getDepartmentId());
    }
}