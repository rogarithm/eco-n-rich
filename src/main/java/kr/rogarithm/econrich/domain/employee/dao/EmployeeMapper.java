package kr.rogarithm.econrich.domain.employee.dao;

import kr.rogarithm.econrich.domain.employee.domain.Department;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.domain.Location;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    Employee selectEmployeeById(Long employeeId);

    JobHistory selectJobHistoryById(Long employeeId);

    Department selectDepartmentById(Long employeeId);

    Location selectLocationById(Long departmentId);
}
