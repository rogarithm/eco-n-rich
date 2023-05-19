package kr.rogarithm.econrich.domain.employee.dao;

import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.historyInfo.domain.JobHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    Employee selectEmployeeById(Long employeeId);

    JobHistory selectJobHistoryById(Long employeeId);
}
