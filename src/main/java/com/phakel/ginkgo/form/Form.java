package com.phakel.ginkgo.form;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Form {
    private List<String> violations;

    public void setViolations(Set<? extends ConstraintViolation<?>> violations) {
        this.violations = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    public List<String> getViolations() {
        return violations;
    }
}
