package kr.rogarithm.econrich.domain.historyInfo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HistoryInfoMapperTest {

    @Autowired
    HistoryInfoMapper historyInfoMapper;

    @Test
    public void selectJobHistoryByValidId() {
        JobHistory jobHistory = historyInfoMapper.selectJobHistoryById(102L);

        assertEquals(102L, jobHistory.getId());
    }

    @Test
    public void selectJobHistoryByInvalidId() {
        JobHistory jobHistory = historyInfoMapper.selectJobHistoryById(-1L);

        assertEquals(null, jobHistory);
    }
}