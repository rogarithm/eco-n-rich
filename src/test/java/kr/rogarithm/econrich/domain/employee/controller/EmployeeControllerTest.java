package kr.rogarithm.econrich.domain.employee.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import kr.rogarithm.econrich.domain.employee.domain.Department;
import kr.rogarithm.econrich.domain.employee.domain.Employee;
import kr.rogarithm.econrich.domain.employee.domain.JobHistory;
import kr.rogarithm.econrich.domain.employee.domain.Location;
import kr.rogarithm.econrich.domain.employee.dto.DepartmentResponse;
import kr.rogarithm.econrich.domain.employee.dto.EmployeeResponse;
import kr.rogarithm.econrich.domain.employee.dto.JobHistoryResponse;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.EmployeeNotFoundException;
import kr.rogarithm.econrich.domain.employee.exception.JobHistoryNotFoundException;
import kr.rogarithm.econrich.domain.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    EmployeeController employeeController;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void getEmployeeWithInvalidId() throws Exception {
        Long id = -1L;

        when(employeeService.getEmployeeById(id)).thenThrow(EmployeeNotFoundException.class);

        this.mockMvc
                .perform(get("/employees/{employeeId}", id))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(employeeService).getEmployeeById(id);
    }

    @Test
    public void getEmployeeWithValidId() throws Exception {
        Long id = 100L;

        Employee employee = Employee.builder()
                                    .id(100L)
                                    .firstName("Steven")
                                    .lastName("King")
                                    .email("SKING")
                                    .phoneNumber("515.123.4567")
                                    .hireDate(LocalDate.parse("1987-06-17"))
                                    .jobId("AD_PRES")
                                    .salary(BigDecimal.valueOf(24000.00))
                                    .departmentId(90L)
                                    .build();

        when(employeeService.getEmployeeById(id)).thenReturn(EmployeeResponse.of(employee));

        this.mockMvc
                .perform(get("/employees/{employeeId}", id))
                .andDo(print())
                .andExpect(status().isOk());

        verify(employeeService).getEmployeeById(id);
    }

    @Test
    public void getJobHistoryWithInvalidId() throws Exception {
        Long id = -1L;

        when(employeeService.getJobHistoryById(id)).thenThrow(JobHistoryNotFoundException.class);

        this.mockMvc
                .perform(get("/employees/{employeeId}/history", id))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(employeeService).getJobHistoryById(id);
    }

    @Test
    public void getJobHistoryWithValidId() throws Exception {
        Long id = 101L;
        JobHistory jobHistory = JobHistory.builder()
                                          .id(101L)
                                          .startDate(LocalDate.parse("1989-09-21"))
                                          .endDate(LocalDate.parse("1993-10-27"))
                                          .jobId("AC_ACCOUNT")
                                          .departmentId(110L)
                                          .build();

        when(employeeService.getJobHistoryById(id)).thenReturn(JobHistoryResponse.of(jobHistory));

        this.mockMvc
                .perform(get("/employees/{employeeId}/history", id))
                .andDo(print())
                .andExpect(status().isOk());

        verify(employeeService).getJobHistoryById(id);
    }

    @Test
    public void getDepartmentAndLocationWithInvalidId() throws Exception {
        Long id = -1L;

        when(employeeService.getDepartmentById(id)).thenThrow(DepartmentNotFoundException.class);

        this.mockMvc
                .perform(get("/employees/{employeeId}/department", id))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(employeeService).getDepartmentById(id);
    }

    @Test
    public void getDepartmentAndLocationWithValidId() throws Exception {
        Long employeeId = 201L;
        Long departmentId = 20L;
        Long locationId = 1800L;
        Department department = Department.builder()
                                          .id(departmentId)
                                          .departmentName("Marketing")
                                          .managerId(201L)
                                          .locationId(1800L)
                                          .build();
        Location location = Location.builder()
                                    .id(locationId)
                                    .streetAddress("147 Spadina Ave")
                                    .postalCode("M5V 2L7")
                                    .city("Toronto")
                                    .stateProvince("Ontario")
                                    .countryId("CA")
                                    .build();

        when(employeeService.getDepartmentById(employeeId)).thenReturn(DepartmentResponse.of(department, location));

        this.mockMvc
                .perform(get("/employees/{employeeId}/department", employeeId))
                .andDo(print())
                .andExpect(status().isOk());

        verify(employeeService).getDepartmentById(employeeId);
    }

}