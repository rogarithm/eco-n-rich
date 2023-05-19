package kr.rogarithm.econrich.domain.employee.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Department {

    private Long id;
    private String departmentName;
    private Long managerId;
    private Long locationId;
}
