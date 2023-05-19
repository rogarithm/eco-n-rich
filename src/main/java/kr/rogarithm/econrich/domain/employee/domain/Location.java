package kr.rogarithm.econrich.domain.employee.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Location {

    private Long id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;
}
