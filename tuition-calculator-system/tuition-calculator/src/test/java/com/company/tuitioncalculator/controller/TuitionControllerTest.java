package com.company.tuitioncalculator.controller;

import com.company.tuitioncalculator.model.Calculator;
import com.company.tuitioncalculator.service.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TuitionController.class)
public class TuitionControllerTest {
    @MockBean
    CalculatorService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tuitionCalculator() throws Exception {
        Calculator expected = new Calculator();
        expected.setStudentId("1234");
        expected.setMajor("business");
        expected.setInState(true);
        expected.setUndergraduate(false);
        expected.setFinancialAid(BigDecimal.valueOf(500.00).setScale(2, RoundingMode.HALF_UP));
        expected.setTuition(BigDecimal.valueOf(12500.00).setScale(2, RoundingMode.HALF_UP));
        expected.setCost(expected.getTuition().subtract(expected.getFinancialAid()).setScale(2, RoundingMode.HALF_UP));

        String outputJson = mapper.writeValueAsString(expected);

        when(service.calculateTuition("1234", "business", true, false)).thenReturn(expected);

        mockMvc.perform(get("/cost/1234/business")
                .param("inState", "true")
                .param("undergraduate", "false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    @Test
    public void testfor404() throws Exception{

        String studentId = "38733";
        String major = "java";
        boolean instate = true;
        boolean underGrad = false;

        when(service.calculateTuition(studentId, major, instate,underGrad)).thenThrow(new NullPointerException("Null pointer exception"));
        this.mockMvc.perform(get("/cost/38733/java")
        .param("inState", "true")
        .param("undergraduate", "false"))
        .andDo(print()).andExpect(status().isNotFound());

    }
}