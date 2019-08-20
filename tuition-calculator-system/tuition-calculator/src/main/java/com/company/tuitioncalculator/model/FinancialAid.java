package com.company.tuitioncalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialAid {
    private String studentId;
    private BigDecimal aidAmount;

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public BigDecimal getAidAmount() {
        return aidAmount;
    }
    public void setAidAmount(BigDecimal aidAmount) {
        this.aidAmount = aidAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialAid that = (FinancialAid) o;
        return Objects.equals(getStudentId(), that.getStudentId()) &&
                Objects.equals(getAidAmount(), that.getAidAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getAidAmount());
    }
}
