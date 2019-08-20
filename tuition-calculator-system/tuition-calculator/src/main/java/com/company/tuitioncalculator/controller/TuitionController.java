package com.company.tuitioncalculator.controller;

import com.company.tuitioncalculator.model.Calculator;
import com.company.tuitioncalculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TuitionController {
    @Autowired
    CalculatorService service;

    @RequestMapping(value = "/cost/{studentId}/{major}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Calculator tuitionCalculator(@PathVariable("studentId") String studentId,
                                        @PathVariable("major") String major,
                                        @RequestParam(name = "inState") boolean inState,
                                        @RequestParam(name = "undergraduate") boolean undergrad) {


        return service.calculateTuition(studentId, major, inState, undergrad);
    }
}
