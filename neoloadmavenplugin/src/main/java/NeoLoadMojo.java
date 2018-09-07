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

import java.io.*;

import static java.util.Arrays.asList;

/**
 * Created by hrexed on 29/06/18.
 */
@Mojo(name = "neoload", defaultPhase = LifecyclePhase.INTEGRATION_TEST)
@Execute(goal = "neoload")
public class NeoLoadMojo extends AbstractNeoLoadMojo {
	@Override
	public void execute() throws MojoExecutionException {
		String line;
		File ListofTest;
		JSONObject neoloadJsonObj;
		JSONArray ProjectList;
		JSONParser parser;
		String NLProjectPAth;
		Log log = this.getLog();
		log.info(" ");
		log.info(LINE_SEPARATOR);
		log.info("NEOLOAD P E R F O R M A N C E    T E S T S");
		log.info(LINE_SEPARATOR);
		try {

			if (!neoLoadInstallationDirectory.exists()) {
				getLog().info("<neoLoadInstallationDirectory>" + neoLoadInstallationDirectory.getAbsolutePath() + "</neoLoadInstallationDirectory> does not exist...");
				getLog().info("Performance tests are skipped.");
				return;
			}

			if (!neoLoadProjectDirectory.exists()) {
				getLog().info("<neoLoadProjectDirectory>" + neoLoadProjectDirectory.getAbsolutePath() + "</neoLoadProjectDirectory> does not exist...");
				getLog().info("Performance tests are skipped.");
				return;
			}

			ListofTest = new File(neoLoadProjectDirectory.getAbsolutePath() + "/" + NeoLoad_List_of_Test);
			if (!ListofTest.exists()) {
				getLog().info("<testFilesDirectory>" + neoLoadProjectDirectory.getAbsolutePath() + "/" + NeoLoad_List_of_Test + "</testFilesDirectory> does not exist...");
				getLog().info("Performance tests are skipped.");
				return;
			}


			//---open the Json File to get the List of project and scenarios to execute----

			try {

				parser = new JSONParser();
				neoloadJsonObj = (JSONObject) parser.parse(new FileReader(neoLoadProjectDirectory.getAbsolutePath() + "/" + NeoLoad_List_of_Test));
				ProjectList = (JSONArray) neoloadJsonObj.get("NeoLoadProjectList");


			} catch (IOException e) {
				getLog().error(e.getMessage());
				return;
			} catch (ParseException e) {
				getLog().error(e.getMessage());
				return;
			}
			for (int i = 0; i < ProjectList.size(); i++) {
				//----for each nl proejct---
				JSONObject NlProject = (JSONObject) ProjectList.get(i);
				NLProjectPAth = (String) NlProject.get("Project");
				JSONArray scenarioList = (JSONArray) NlProject.get("Scenarios");
				for (int j = 0; j < scenarioList.size(); j++) {
					String scenarioname = (String) scenarioList.get(j);
					log.info(LINE_SEPARATOR);
					log.info("Project : " + NLProjectPAth);
					log.info(LINE_SEPARATOR);
					log.info("Scenario Name : " + scenarioname);
					log.info(LINE_SEPARATOR);
					final String[] command = MojoUtiliy.generateCmd(NLProjectPAth,
							this.neoLoadInstallationDirectory.getAbsolutePath(),
							scenarioname,
							this.reportDirectory.getAbsolutePath(),
							this.neoLoadWebUrl.toString(),
							this.neoLoadWebAPIKey,
							this.neotysTeamServerURL,
							this.neotysTeamServerLogin,
							this.neotysTeamServerEncryptedPassword,
							this.neotysTeamServerLicenseID,
							this.neotysTeamMaxVuRequired,
							this.neotysTeamMaxTestDuration);
					log.info("Going to run NeoLoadCmd.");
					log.info(asList(command).toString());
					Process p = Runtime.getRuntime()
							.exec(command);
					BufferedReader bri = new BufferedReader
							(new InputStreamReader(p.getInputStream()));
					BufferedReader bre = new BufferedReader
							(new InputStreamReader(p.getErrorStream()));

					while ((line = bri.readLine()) != null) {
						log.info(line + "\n");
					}
					bri.close();
					while ((line = bre.readLine()) != null) {
						log.error(line + "\n");
					}
					bre.close();
					final int exitStatus = p.waitFor();
					if (exitStatus == 0)
						log.info("Test success.");
					else
						log.error("Test failure");
				}
			}


		} catch (NeoLoadException | IOException | InterruptedException e) {
			log.error(e.getMessage());
			throw new MojoExecutionException(e.getMessage());
		}

	}


}
