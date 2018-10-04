package stats;

import io.swagger.client.model.TestStatistics;

import java.util.HashMap;

public class NLmetric {

    private TestStatistics statistics;

    private static final String LABEL_NBUSERS="Total Number Virtual Users";
    private int nbVus;

    private static final String LABEL_AverageHits="Average request/s";
    private  float hits;

    private static final String LABEL_AverageThroughput="Average Download Bytes/s";
    private  float throughput;

    private static final String LABEL_AverageRequestResponsetime="Average request response time (ms)";
    private  float avereage_requestresponsetime;

    private static final String LABEL_AverageTransactionResponstime="Average Transaction response time (ms)";
    private  float average_transactionresponsetime;

    private static final String LABEL_NumberREquestFailed="Total request in error";
    private Long number_requestfailed;

    private static final String LABEL_NumberREquestSucess="Total request in sucess";
    private Long number_requestSucess;

    private static final String LABEL_NumberTransactionFailed="Total transaction in error";
    private Long number_transactionfailed;

    private static final String LABEL_NumberTransactionSucess="Total transaction in sucess";
    private Long number_TransactionSucess;

    private static final String LABEL_TotalNumberOfFailuer="Total number of errors";
    private Long number_ofFailure;

    public NLmetric(TestStatistics statistics)
    {
        this.statistics = statistics;
        //nbVus=this.statistics.getLastVirtualUserCount();
        hits=this.statistics.getTotalRequestCountPerSecond();
        throughput=this.statistics.getTotalGlobalDownloadedBytesPerSecond();
        avereage_requestresponsetime=this.statistics.getTotalRequestDurationAverage();
        average_transactionresponsetime=this.statistics.getTotalTransactionDurationAverage();
        number_requestfailed=this.statistics.getTotalRequestCountFailure();
        number_requestSucess=this.statistics.getTotalRequestCountSuccess();
        number_transactionfailed=this.statistics.getTotalTransactionCountFailure();
        number_TransactionSucess=this.statistics.getTotalTransactionCountSuccess();
        number_ofFailure=this.statistics.getTotalGlobalCountFailure();
    }

    public HashMap<String,String> getStatistics()
    {
        HashMap<String,String> result=new HashMap<>();

     //   result.put(LABEL_NBUSERS,String.valueOf(nbVus));
        result.put(LABEL_AverageHits,String.valueOf(hits));
        result.put(LABEL_AverageThroughput,String.valueOf(throughput));
        result.put(LABEL_AverageRequestResponsetime,String.valueOf(avereage_requestresponsetime));
        result.put(LABEL_AverageTransactionResponstime,String.valueOf(average_transactionresponsetime));
        result.put(LABEL_NumberREquestFailed,String.valueOf(number_requestfailed));
        result.put(LABEL_NumberREquestSucess,String.valueOf(number_requestSucess));
        result.put(LABEL_NumberTransactionFailed,String.valueOf(number_transactionfailed));
        result.put(LABEL_NumberTransactionSucess,String.valueOf(number_TransactionSucess));
        result.put(LABEL_TotalNumberOfFailuer,String.valueOf(number_ofFailure));
        return result;

    }

}

