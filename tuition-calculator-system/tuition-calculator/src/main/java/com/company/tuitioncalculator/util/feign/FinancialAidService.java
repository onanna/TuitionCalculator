package com.company.tuitioncalculator.util.feign;

import com.company.tuitioncalculator.model.FinancialAid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="finaid-service")
public interface FinancialAidService {
    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public FinancialAid getFinancialAidByStudent(@PathVariable String id);

}
