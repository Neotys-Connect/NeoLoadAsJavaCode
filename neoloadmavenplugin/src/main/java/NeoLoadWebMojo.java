import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hrexed on 10/07/18.
 */
@Mojo(name = "neoloadweb", defaultPhase = LifecyclePhase.INTEGRATION_TEST)
@Execute(goal = "NeoLoadWebTest")
public class NeoLoadWebMojo extends AbstractNeoLoadMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String line;
        File listofTest;
        JSONObject neoloadJsonObj;
        JSONArray projectList;
        JSONParser parser;
        String nlprojectpath;
        Log log=this.getLog();
        log.info(" ");
        log.info(LINE_SEPARATOR);
        log.info("NEOLOAD P E R F O R M A N C E    T E S T S");
        log.info(LINE_SEPARATOR);
//        try {


            if (neoLoadWebUrl.toString().isEmpty()) {
                getLog().info("<neoLoadWebUrl>" + neoLoadWebUrl.toString() + "</neoLoadWebUrl> does not exist...");
                getLog().info("Performance tests are skipped.");
                return;
            }
            if (neoLoadWebAPIKey.isEmpty()) {
                getLog().info("<neoLoadWebAPIKey>" + neoLoadWebAPIKey + "</neoLoadWebAPIKey> does not exist...");
                getLog().info("Performance tests are skipped.");
                return;
            }
            listofTest=new File(neoLoadProjectDirectory.getAbsolutePath()+"/"+NeoLoad_List_of_Test);
            if(!listofTest.exists())
            {
                getLog().info("<testFilesDirectory>" + neoLoadProjectDirectory.getAbsolutePath()+"/"+NeoLoad_List_of_Test + "</testFilesDirectory> does not exist...");
                getLog().info("Performance tests are skipped.");
                return;
            }


            //---open the Json File to get the List of project and scenarios to execute----
//            ApiClient nlWebApiClient=new ApiClient();
//            // Configure API key authorization: NeoloadAuthorizer
//            nlWebApiClient.setBasePath(neoLoadWebUrl.toString());
//            nlWebApiClient.setApiKey(neoLoadWebAPIKey);
//            nlWebApiClient.p
            try {

                parser = new JSONParser();
                neoloadJsonObj = (JSONObject) parser.parse(new FileReader(neoLoadProjectDirectory.getAbsolutePath() + "/" + NeoLoad_List_of_Test));
                projectList = (JSONArray) neoloadJsonObj.get("NeoLoadProjectList");


            }
            catch(IOException e)
            {
                getLog().error(e.getMessage());
                return;
            }
            catch(ParseException e)
            {
                getLog().error(e.getMessage());
                return;
            }
            for(int i=0;i<projectList.size();i++)
            {
                //----for each nl proejct---
                JSONObject nlProject = (JSONObject) projectList.get(i);
                nlprojectpath= (String) nlProject.get("Project");
                JSONArray scenarioList= (JSONArray) nlProject.get("Scenarios");
                for(int j=0;j<scenarioList.size();j++)
                {
                    String scenarioname= (String) scenarioList.get(j);
                    log.info(LINE_SEPARATOR);
                    log.info("Project : " + nlprojectpath);
                    log.info(LINE_SEPARATOR);
                    log.info("Scenario Name : " + scenarioname);
                    log.info(LINE_SEPARATOR);



                }
            }


//        }
//        catch(NeoLoadException e) {
//            log.error(e.getMessage());
//            throw new MojoExecutionException(e.getMessage());
//        }
//        catch(InterruptedException e) {
//            log.error(e.getMessage());
//            throw new MojoExecutionException(e.getMessage());
//
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new MojoExecutionException(e.getMessage());
//
//        }
    }
}
