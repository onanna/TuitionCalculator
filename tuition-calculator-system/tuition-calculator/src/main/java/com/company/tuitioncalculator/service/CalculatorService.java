package com.company.tuitioncalculator.service;

import com.company.tuitioncalculator.model.Calculator;
import com.company.tuitioncalculator.model.FinancialAid;
import com.company.tuitioncalculator.model.Tuition;
import com.company.tuitioncalculator.util.feign.FinancialAidService;
import com.company.tuitioncalculator.util.feign.TuitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class CalculatorService {
    @Autowired
    FinancialAidService financialAidService;

    @Autowired
    TuitionService tuitionService;

    public CalculatorService(FinancialAidService financialAidService, TuitionService tuitionService) {
        this.financialAidService = financialAidService;
        this.tuitionService = tuitionService;
    }

    public Calculator calculateTuition(String studentId, String major, boolean inState, boolean undergrad) {
        Calculator cost = new Calculator();
        cost.setStudentId(studentId);
        cost.setMajor(major);
        cost.setInState(inState);
        cost.setUndergraduate(undergrad);

        Tuition tuition = tuitionService.getTuition(major, inState, undergrad);
        FinancialAid aid = financialAidService.getFinancialAidByStudent(studentId);

        cost.setTuition(tuition.getTuition());
        cost.setFinancialAid(aid.getAidAmount());

        cost.setCost(cost.getTuition().subtract(cost.getFinancialAid()).setScale(2, RoundingMode.HALF_UP));

        return cost;
    }
}
