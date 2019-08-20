package com.company.tuitioncalculator.util.feign;

import com.company.tuitioncalculator.model.Tuition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="tuition-service")
public interface TuitionService {
    @RequestMapping(value = "/tuition/{major}", method = RequestMethod.GET)
    public Tuition getTuition(@PathVariable String major,
                              @RequestParam(defaultValue = "true") boolean inState,
                              @RequestParam(defaultValue = "true") boolean undergraduate);
}
