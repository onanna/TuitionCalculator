package com.company.tuitioncalculator.service;

import com.company.tuitioncalculator.model.Calculator;
import com.company.tuitioncalculator.model.FinancialAid;
import com.company.tuitioncalculator.model.Tuition;
import com.company.tuitioncalculator.util.feign.FinancialAidService;
import com.company.tuitioncalculator.util.feign.TuitionService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CalculatorServiceTest {
    FinancialAidService financialAidService;
    TuitionService tuitionService;

    CalculatorService service;

    @Before
    public void setUp() throws Exception {
        setUpFinancialAidMock();;
        setUpTuitionMock();

        service = new CalculatorService(financialAidService, tuitionService);
    }

    @Test
    public void calculateTuition() {
        Calculator expected = new Calculator();
        expected.setStudentId("1234");
        expected.setMajor("business");
        expected.setInState(true);
        expected.setUndergraduate(false);
        expected.setFinancialAid(BigDecimal.valueOf(500.00).setScale(2, RoundingMode.HALF_UP));
        expected.setTuition(BigDecimal.valueOf(12500.00).setScale(2, RoundingMode.HALF_UP));
        expected.setCost(expected.getTuition().subtract(expected.getFinancialAid()).setScale(2, RoundingMode.HALF_UP));

        Calculator fromService = service.calculateTuition("1234", "business", true, false);

        assertEquals(expected, fromService);
    }


    private void setUpFinancialAidMock() {
        financialAidService = mock(FinancialAidService.class);

        FinancialAid aid = new FinancialAid();
        aid.setStudentId("1234");
        aid.setAidAmount(BigDecimal.valueOf(500.00).setScale(2, RoundingMode.HALF_UP));

        doReturn(aid).when(financialAidService).getFinancialAidByStudent(aid.getStudentId());
    }
    private void setUpTuitionMock() {
        tuitionService = mock(TuitionService.class);

        Tuition tuition = new Tuition();
        tuition.setMajor("business");
        tuition.setInState(true);
        tuition.setUndergraduate(false);
        tuition.setTuition(BigDecimal.valueOf(12500.00).setScale(2, RoundingMode.HALF_UP));

        doReturn(tuition).when(tuitionService).getTuition(tuition.getMajor(), tuition.isInState(), tuition.isUndergraduate());
    }
}