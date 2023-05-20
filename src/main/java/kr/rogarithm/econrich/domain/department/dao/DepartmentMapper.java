package kr.rogarithm.econrich.domain.department.dao;

import java.util.List;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {

    List<JobHistory> selectJobHistories(Long departmentId);
}
