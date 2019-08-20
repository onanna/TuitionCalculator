package com.company.tuitioncalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tuition {
    private String major;
    private boolean inState;
    private boolean undergraduate;
    private BigDecimal tuition;
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
    public BigDecimal getTuition() {
        return tuition;
    }
    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuition tuition1 = (Tuition) o;
        return isInState() == tuition1.isInState() &&
                isUndergraduate() == tuition1.isUndergraduate() &&
                Objects.equals(getMajor(), tuition1.getMajor()) &&
                Objects.equals(getTuition(), tuition1.getTuition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMajor(), isInState(), isUndergraduate(), getTuition());
    }
}
