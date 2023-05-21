package kr.rogarithm.econrich.domain.department.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kr.rogarithm.econrich.domain.department.exception.InvalidRaiseRateException;
import kr.rogarithm.econrich.domain.department.service.DepartmentService;
import kr.rogarithm.econrich.domain.employee.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    DepartmentController departmentController;

    @MockBean
    DepartmentService departmentService;

    @Test
    public void raiseRateOfDepartment() throws Exception {
        Long id = 100L;
        Double raiseRate = 1.1;

        when(departmentService.raiseSalaryOfDepartment(id, raiseRate)).thenReturn(1);

        this.mockMvc
                .perform(put("/departments")
                        .param("departmentId", id.toString())
                        .param("raiseRate", raiseRate.toString()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(departmentService).raiseSalaryOfDepartment(id, raiseRate);
    }

    @Test
    public void raiseRateOfNonExistingDepartment() throws Exception {
        Long id = -1L;
        Double raiseRate = 1.1;

        when(departmentService.raiseSalaryOfDepartment(id, raiseRate)).thenThrow(DepartmentNotFoundException.class);

        this.mockMvc
                .perform(put("/departments")
                        .param("departmentId", id.toString())
                        .param("raiseRate", raiseRate.toString()))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(departmentService).raiseSalaryOfDepartment(id, raiseRate);
    }

    @Test
    public void raiseRateOfInvalidRate() throws Exception {
        Long id = 100L;
        Double raiseRate = -1.1;

        when(departmentService.raiseSalaryOfDepartment(id, raiseRate)).thenThrow(InvalidRaiseRateException.class);

        this.mockMvc
                .perform(put("/departments")
                        .param("departmentId", id.toString())
                        .param("raiseRate", raiseRate.toString()))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(departmentService).raiseSalaryOfDepartment(id, raiseRate);
    }
}