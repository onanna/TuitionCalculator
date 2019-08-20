package com.company.tuitioncalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Calculator {
    private String studentId;
    private String major;
    private boolean inState;
    private boolean undergraduate;
    private BigDecimal financialAid;
    private BigDecimal tuition;
    private BigDecimal cost;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isInState() {
        return inState;
    }

    public void setInState(boolean inState) {
        this.inState = inState;
    }

    public boolean isUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(boolean undergraduate) {
        this.undergraduate = undergraduate;
    }

    public BigDecimal getFinancialAid() {
        return financialAid;
    }

    public void setFinancialAid(BigDecimal financialAid) {
        this.financialAid = financialAid;
    }

    public BigDecimal getTuition() {
        return tuition;
    }

    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return isInState() == that.isInState() &&
                isUndergraduate() == that.isUndergraduate() &&
                Objects.equals(getStudentId(), that.getStudentId()) &&
                Objects.equals(getMajor(), that.getMajor()) &&
                Objects.equals(getFinancialAid(), that.getFinancialAid()) &&
                Objects.equals(getTuition(), that.getTuition()) &&
                Objects.equals(getCost(), that.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getMajor(), isInState(), isUndergraduate(), getFinancialAid(), getTuition(), getCost());
    }
}
