package kr.rogarithm.econrich.domain.historyInfo.dao;

import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryInfoMapper {

    JobHistory selectJobHistoryById(Long employeeId);
}

