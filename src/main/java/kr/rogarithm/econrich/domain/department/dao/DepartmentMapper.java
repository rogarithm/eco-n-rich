package kr.rogarithm.econrich.domain.department.dao;

import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {

    JobHistory selectJobHistoryById(Long departmentId);
}
