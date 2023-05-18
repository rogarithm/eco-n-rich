package kr.rogarithm.econrich.domain.historyInfo.service;

import kr.rogarithm.econrich.domain.historyInfo.dao.HistoryInfoMapper;
import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import kr.rogarithm.econrich.domain.historyInfo.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.historyInfo.exception.JobHistoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HistoryInfoService {

    private final HistoryInfoMapper historyInfoMapper;

    public HistoryInfoService(HistoryInfoMapper historyInfoMapper) {
        this.historyInfoMapper = historyInfoMapper;
    }

    public JobHistoryResponse getJobHistoryById(Long employeeId) {
        JobHistory jobHistory = historyInfoMapper.selectJobHistoryById(employeeId);

        if (jobHistory == null) {
            throw new JobHistoryNotFoundException("입력한 아이디 " + employeeId + "에 해당하는 직원 이력을 찾을 수 없습니다");
        }

        return JobHistoryResponse.of(jobHistory);
    }
}
