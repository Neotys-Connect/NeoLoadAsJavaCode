package com.neotys.testing.framework.utils.datamodel;

import java.util.List;

public class SlaProfiles {
    List<SlaProfile> sla_profile;

    public SlaProfiles(List<SlaProfile> sla_profile) {
        this.sla_profile = sla_profile;
    }

    public List<SlaProfile> getSla_profile() {
        return sla_profile;
    }

    public void setSla_profile(List<SlaProfile> sla_profile) {
        this.sla_profile = sla_profile;
    }

    public void removeCleanSla()
    {
        this.sla_profile.forEach(profile->{
            profile.removeIncorrectThresholds();
        });
    }
}
