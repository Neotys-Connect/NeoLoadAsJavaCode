import com.neotys.ascode.swagger.client.ApiClient;
import com.neotys.ascode.swagger.client.ApiException;
import com.neotys.ascode.swagger.client.api.ResultsApi;
import com.neotys.ascode.swagger.client.model.TestDefinition;
import com.neotys.ascode.swagger.client.model.TestStatistics;
import com.neotys.ascode.swagger.client.api.ResultsApi;
import com.neotys.ascode.swagger.client.model.TestStatistics;
import org.apache.maven.plugin.logging.Log;
import stats.NLmetric;

import java.util.HashMap;
import java.util.List;

public class NlWebStatistics {
    private ApiClient apiClient;
    private String apitoken;
    private String apiurl;
    private String testID;

    public NlWebStatistics(String apitoken, String apiurl, String testID) {
        this.apitoken = apitoken;
        this.apiurl = apiurl;
        this.testID = testID;
        this.apiClient=new ApiClient();
        apiClient.setApiKey(apitoken);
        apiClient.setBasePath(apiurl);

    }

    public HashMap<String,String> getStats() throws ApiException {
        ResultsApi resultsApi=new ResultsApi(this.apiClient);
        TestStatistics statistics=resultsApi.getTestStatistics(testID);
        NLmetric nLmetric=new NLmetric(statistics);


        return nLmetric.getStatistics();

    }

    public void writeStatsinOutput(Log log)
    {
        try {
            HashMap<String, String> stats = getStats();
            if(stats.size()>0)
            {
                log.info("-------------------------------------");
                log.info("-         Test Statistics           -");
                log.info("-------------------------------------");
                stats.forEach((k,v)->
                    log.info("- "+k+" : "+ v)
                );
                log.info("-------------------------------------");
            }
        }
        catch (ApiException e)
        {
            log.error("API error to retrieve statistics",e);
        }

    }
}
