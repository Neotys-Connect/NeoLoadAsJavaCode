import io.swagger.client.ApiException;
import io.swagger.client.model.ProjectDefinition;
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
import java.util.List;

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



            if (neoLoadWebAPIUrl.toString().isEmpty()) {
                log.info("<neoLoadWebAPIUrl>" + neoLoadWebAPIUrl.toString() + "</neoLoadWebAPIUrl> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            if (neoLoadWebUrl.toString().isEmpty()) {
                log.info("<neoLoadWebUrl>" + neoLoadWebUrl.toString() + "</neoLoadWebUrl> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            if (neoLoadWebAPIKey.isEmpty()) {
                log.info("<neoLoadWebAPIKey>" + neoLoadWebAPIKey + "</neoLoadWebAPIKey> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            if (neoloadWebControllerID.isEmpty()) {
                log.info("<neoloadWebControllerID>" + neoloadWebControllerID + "</neoloadWebControllerID> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            if (neoloadWeblgZonneID.isEmpty()) {
                log.info("<neoloadWeblgZonneID>" + neoloadWeblgZonneID + "</neoloadWeblgZonneID> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            if (neoLoadWebAPIKey.isEmpty()) {
                log.info("<neoLoadWebAPIKey>" + neoLoadWebAPIKey + "</neoLoadWebAPIKey> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }
            listofTest=new File(neoLoadProjectDirectory.getAbsolutePath()+"/"+NeoLoad_List_of_Test);
            if(!listofTest.exists())
            {
                log.info("<testFilesDirectory>" + neoLoadProjectDirectory.getAbsolutePath()+"/"+NeoLoad_List_of_Test + "</testFilesDirectory> does not exist...");
                log.info("Performance tests are skipped.");
                return;
            }




            try {

                parser = new JSONParser();
                neoloadJsonObj = (JSONObject) parser.parse(new FileReader(neoLoadProjectDirectory.getAbsolutePath() + "/" + NeoLoad_List_of_Test));
                projectList = (JSONArray) neoloadJsonObj.get("NeoLoadProjectList");


            }
            catch(IOException e)
            {
                log.error(e.getMessage());
                return;
            }
            catch(ParseException e)
            {
                log.error(e.getMessage());
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


                    try {
                        List<String> nltesturl=MojoUtiliy.executeTestOnNLWEB(
                                neoLoadWebAPIKey,
                                neoLoadWebAPIUrl,
                                scenarioname,
                                nlprojectpath,
                                neoloadWebControllerID,
                                neoloadWeblgZonneID,
                                neoLoadWebUrl
                        );
                        if(nltesturl !=null)
                        {
                            if(nltesturl.size()>1)
                            {
                                log.info("Trending URL : " + nltesturl.get(0));
                                log.info("Testing result url : "+nltesturl.get(1));
                            }
                        }
                        else
                            throw new MojoExecutionException("Impossible to launche the scenario "+scenarioname+" on the project "+ nlprojectpath);

                    } catch (ApiException e) {
                        log.error("Error uploading the project ",e );
                        throw new MojoExecutionException("Error uploading the project "+e.getMessage());
                    } catch (NeoLoadException e) {
                        log.error("Issue with the upload of the project",e);
                        throw new MojoExecutionException("Error uploading the project "+e.getMessage());

                    }

                }
            }


    }
}
