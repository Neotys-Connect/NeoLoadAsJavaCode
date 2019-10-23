package com.neotys.testing.framework.utils.datamodel;

import com.neotys.neoload.model.v3.project.sla.SlaThreshold;

import java.util.Optional;

import static com.neotys.testing.framework.utils.datamodel.SLAConstants.*;

public class SLAThresholds {
    String indicator;
    String severity;
    String operator;
    Double value;
    String scope;
    Optional<Integer> percentile;

    public SLAThresholds(String indicator, String severity, String operator, Double value, String scope, Optional<Integer> percentile) {
        this.indicator = indicator;
        this.severity = severity;
        this.operator = operator;
        this.value = value;
        this.scope = scope;
        this.percentile = percentile;
    }

    public String getIndicator() {
        return indicator.toLowerCase();
    }

    public String getKpiName()
    {
        return indicator.toUpperCase().replace('-', '_');
    }

    public Optional<Integer> getPercentile() {
        return percentile;
    }

    public void setPercentile(Optional<Integer> percentile) {
        this.percentile = percentile;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator.toLowerCase();
    }

    public String getSeverity() {
        return severity.toLowerCase();
    }

    public void setSeverity(String severity) {
        this.severity = severity.toLowerCase();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }



    public String getScope() {
        return scope.toLowerCase();
    }

    public void setScope(String scope) {
        this.scope = scope.toLowerCase();
    }

    public boolean validateThreshold()
    {
        if(!TYPE_OF_THRESHOLD_SCOPE.contains(getScope()))
            return false;

        if(!TYPE_OF_SEVERITY.contains(getSeverity()))
            return false;

        if(!THRESHOLD_OPERATOR.contains(getOperator()))
            return false;

        if(getScope()==PER_INTERVAL)
        {
            if(!THRESHOLD_INDICATOR_PERINTERVAL.contains(getIndicator()))
                return false;
        }
        if(getScope()==PER_RUN)
        {
            if(!THRESHOLD_INDICATOR_PERTEST.contains(getIndicator()))
                return false;
        }

        if(getIndicator().toLowerCase().startsWith(PERCENTILE_PREFIX)) {
            if(!getPercentile().isPresent())
            {
                percentile=Optional.of(SlaThreshold.DEFAULT_PERCENT);
            }
        }
        return true;
    }
}
