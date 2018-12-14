package com.neotys.testing.framework.plugin.apm.data;

import java.util.List;

public class DynatraceAnomalie {
    List<DynatraceAnomalies> dynatraceAnomalieList;

    public DynatraceAnomalie(List<DynatraceAnomalies> dynatraceAnomalieList) {
        this.dynatraceAnomalieList = dynatraceAnomalieList;
    }

    public List<DynatraceAnomalies> getDynatraceAnomalieList() {
        return dynatraceAnomalieList;
    }

    public void setDynatraceAnomalieList(List<DynatraceAnomalies> dynatraceAnomalieList) {
        this.dynatraceAnomalieList = dynatraceAnomalieList;
    }
}
