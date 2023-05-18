package kr.rogarithm.econrich.domain.historyInfo.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.historyInfo.exception.JobHistoryNotFoundException;
import kr.rogarithm.econrich.domain.historyInfo.service.HistoryInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HistoryInfoController.class)
class HistoryInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    HistoryInfoController historyInfoController;

    @MockBean
    HistoryInfoService historyInfoService;

    @Test
    public void getJobHistoryWithInvalidId() throws Exception {
        Long id = -1L;

        when(historyInfoService.getJobHistoryById(id)).thenThrow(JobHistoryNotFoundException.class);

        this.mockMvc
                .perform(get("/historyInfo/{employeeId}", id))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(historyInfoService).getJobHistoryById(id);
    }

    @Test
    public void getJobHistoryWithValidId() throws Exception {
        Long id = 101L;
        JobHistory jobHistory = JobHistory.builder()
                                          .id(101L)
                                          .startDate(LocalDate.parse("1989-09-21"))
                                          .endDate(LocalDate.parse("1993-10-27"))
                                          .jobId("AC_ACCOUNT")
                                          .departmentId(110L)
                                          .build();

        when(historyInfoService.getJobHistoryById(id)).thenReturn(JobHistoryResponse.of(jobHistory));

        this.mockMvc
                .perform(get("/historyInfo/{employeeId}", id))
                .andDo(print())
                .andExpect(status().isOk());

        verify(historyInfoService).getJobHistoryById(id);
    }
}