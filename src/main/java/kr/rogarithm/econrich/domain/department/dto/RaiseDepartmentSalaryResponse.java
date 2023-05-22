package kr.rogarithm.econrich.domain.department.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RaiseDepartmentSalaryResponse {

    Long employeeId;
    BigDecimal salaryBefore;
    BigDecimal salaryAfter;
}
