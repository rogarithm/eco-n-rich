package kr.rogarithm.econrich.domain.department.dao;

import java.math.BigDecimal;
import java.util.List;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {

    List<JobHistory> selectJobHistories(Long departmentId);

    Integer updateEmployeeSalary(@Param("employeeId") Long employeeId, @Param("raiseRate") Double raiseRate);

    BigDecimal selectEmployeeSalary(Long employeeId);
}
