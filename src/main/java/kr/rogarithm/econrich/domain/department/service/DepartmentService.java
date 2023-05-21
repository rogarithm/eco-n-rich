package kr.rogarithm.econrich.domain.department.service;

import java.util.List;
import kr.rogarithm.econrich.domain.department.dao.DepartmentMapper;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;

public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public Integer raiseSalaryOfDepartment(Long departmentId, Double raiseRate) {
        List<JobHistory> jobHistories = departmentMapper.selectJobHistories(departmentId);

        if (jobHistories.isEmpty()) {
            throw new DepartmentNotFoundException("입력한 부서 아이디 " + departmentId + "에 해당하는 부서를 찾을 수 없습니다");
        }

        return 0;
    }
}
