package com.neotys.testing.framework.utils.datamodel;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class SLAConstants {
    public static final String PER_INTERVAL="per interval";
    public static final String PER_RUN="per test";

    public static final String PERCENTILE_PREFIX="perc";

    public static final List<String> TYPE_OF_THRESHOLD_SCOPE =
            ImmutableList.of(PER_INTERVAL,PER_RUN);
    public static final List<String> TYPE_OF_SEVERITY =
            ImmutableList.of("warn", "fail");
    public static final List<String> THRESHOLD_OPERATOR =
            ImmutableList.of("<=", ">=","==");
    public static final List<String> THRESHOLD_UNIT =
            ImmutableList.of("s", "ms","/s","Mbps","MB","%");
    public static final List<String> THRESHOLD_INDICATOR_PERINTERVAL=
            ImmutableList.of("avg-resp-time", "avg-elt-per-sec","avg-throughput-per-sec","errors-per-sec","error-rate");
    public static final List<String> THRESHOLD_INDICATOR_PERTEST =
            ImmutableList.of("avg-request-resp-time", "avg-page-resp-time","avg-transaction-resp-time","perc-transaction-resp-time","avg-request-per-sec","avg-throughput-per-sec","errors-count","count","throughput","error-rate");

}
