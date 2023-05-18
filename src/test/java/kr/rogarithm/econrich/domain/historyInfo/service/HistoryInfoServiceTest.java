package kr.rogarithm.econrich.domain.historyInfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import kr.rogarithm.econrich.domain.historyInfo.dao.HistoryInfoMapper;
import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HistoryInfoServiceTest {

    @InjectMocks
    HistoryInfoService historyInfoService;

    @Mock
    HistoryInfoMapper historyInfoMapper;

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
        JobHistoryResponse jobHistoryResponse = historyInfoService.getJobHistoryById(id);

        // then
        verify(historyInfoMapper).selectJobHistoryById(id);
        assertNotNull(jobHistoryResponse);
        assertEquals(id, jobHistoryResponse.getId());
    }

}