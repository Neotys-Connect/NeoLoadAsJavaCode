package com.neotys.testing.framework.utils.datamodel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SlaProfile  {
    String name;
    Optional<String> description;
    List<SLAThresholds> thresholds;

    public SlaProfile(String name, Optional<String> description, List<SLAThresholds> thresholds) {
        this.name = name;
        this.description = description;
        this.thresholds = thresholds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public List<SLAThresholds> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<SLAThresholds> thresholds) {
        this.thresholds = thresholds;
    }

    public void removeIncorrectThresholds()
    {
        this.thresholds=this.thresholds.stream().filter(threshold ->
            threshold.validateThreshold()).collect(Collectors.toList());
    }


}
