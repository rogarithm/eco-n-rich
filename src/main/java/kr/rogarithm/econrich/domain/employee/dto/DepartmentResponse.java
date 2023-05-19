package kr.rogarithm.econrich.domain.employee.dto;

import kr.rogarithm.econrich.domain.employee.domain.Department;
import kr.rogarithm.econrich.domain.employee.domain.Location;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DepartmentResponse {

    private Long id;
    private String departmentName;
    private Long managerId;
    private Long locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;

    public static DepartmentResponse of(Department department, Location location) {
        return DepartmentResponse.builder()
                                 .id(department.getId())
                                 .departmentName(department.getDepartmentName())
                                 .managerId(department.getManagerId())
                                 .locationId(department.getLocationId())
                                 .streetAddress(location.getStreetAddress())
                                 .postalCode(location.getPostalCode())
                                 .city(location.getCity())
                                 .stateProvince(location.getStateProvince())
                                 .countryId(location.getCountryId())
                                 .build();
    }
}
