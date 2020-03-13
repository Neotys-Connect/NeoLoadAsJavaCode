
import com.neotys.ascode.swagger.client.ApiClient;
import com.neotys.ascode.swagger.client.ApiException;
import com.neotys.ascode.swagger.client.api.RuntimeApi;
import com.neotys.ascode.swagger.client.model.ProjectDefinition;
import com.neotys.ascode.swagger.client.model.RunTestDefinition;
import com.neotys.ascode.swagger.client.model.ScenarioDefinition;
import org.apache.commons.lang3.SystemUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.time.Instant;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hrexed on 29/06/18.
 */
final class MojoUtiliy {
	private static boolean IS_WINDOWS = SystemUtils.IS_OS_WINDOWS;
	private static final String TESTNAME="Maven Test";
	private static String neoLoadCmd = "NeoLoadCmd";
	private MojoUtiliy() {
	}

	public static NeoLoadWebTest executeTestOnNLWEB(String token, URL nLwebAPIURL, String scenarioName, String projectPath, String controllerID, String lgzoneID, URL nlwebURL,URL neoLoadWebUploadAPIUrl) throws ApiException, NeoLoadException, IOException {
		//---open the Json File to get the List of project and scenarios to execute----

		ApiClient nlWebApiClient=new ApiClient();
		// Configure API key authorization: NeoloadAuthorizer
		nlWebApiClient.setBasePath(neoLoadWebUploadAPIUrl.toString());
		nlWebApiClient.setApiKey(token);
		RuntimeApi runtimeApi=new RuntimeApi(nlWebApiClient);
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));

		// Create a filename from a format string.
		// ... Apply date formatting codes.
		String description = String.format("%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp", cal) ;
		///----start the upload of the project----

		File nlcompressedfile=new File(compressNLProject(projectPath));
		if(nlcompressedfile.exists()) {
			ProjectDefinition projectDefinition = runtimeApi.postUploadProject(nlcompressedfile);
			String nlWebScenario = getScenarioFromdefinition(projectDefinition, scenarioName);
			if (nlWebScenario != null)
			{
				nlWebApiClient.setBasePath(nLwebAPIURL.toString());
				nlWebApiClient.setApiKey(token);
				runtimeApi=new RuntimeApi(nlWebApiClient);

				RunTestDefinition runTestDefinition = runtimeApi.getTestsRun(TESTNAME + " " + scenarioName, projectDefinition.getProjectId(), nlWebScenario, TESTNAME + description,null,null,null,null,null, controllerID, lgzoneID,true,false);
				NeoLoadWebTest result=new NeoLoadWebTest(runTestDefinition.getTestId(),nlwebURL.toString() + "/#!trend/?scenario=" + nlWebScenario + "&limit=-1&project=" + projectDefinition.getProjectId(),nlwebURL.toString() + "/#!result/" + runTestDefinition.getTestId() + "/overview");
				return result;
			} else {
				throw new NeoLoadException(scenarioName + " is not found on the project upload on NLWEB");
			}
		}
		else
			throw new NeoLoadException(nlcompressedfile.getAbsolutePath() + " does not exists");

	}
	private static String getScenarioFromdefinition(ProjectDefinition projectDefinition,String scenarioname)
	{
		List<ScenarioDefinition> scenarioDefinitionList=projectDefinition.getScenarios();

		Optional<ScenarioDefinition> optionalScenarioDefinition=scenarioDefinitionList.stream().filter(scenarioDefinition -> scenarioDefinition.getScenarioName().equalsIgnoreCase(scenarioname)).findFirst();
		if(optionalScenarioDefinition.isPresent())
		{
			return optionalScenarioDefinition.get().getScenarioName();
		}
		else
			return null;
	}
	private static String compressNLProject(String projectPath) throws IOException {
		String sourcefolder=new File(projectPath).getParentFile().toString();
		String nameofZipfile=new File(projectPath).getParentFile().getName();
		String nameofZipfolder=new File(projectPath).getParentFile().getParentFile().toString();
		Path p;

		String dist=nameofZipfolder+"/"+nameofZipfile+".zip";

		try {
			p = Files.createFile(Paths.get(dist));
		}
		catch(FileAlreadyExistsException e)
		{
			p=Paths.get(dist);
		}
		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(sourcefolder);
			Files.walk(pp)
					.filter(path -> !Files.isDirectory(path))
					.forEach(path -> {
						ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
						try {
							zs.putNextEntry(zipEntry);
							Files.copy(path, zs);
							zs.closeEntry();
						} catch (IOException e) {
							System.err.println(e);
						}
					});
		}
		return dist;
	}
	public static String[] generateCmd(String projectPath, String neoLoadPath, String nlscenario, String resultFolder, String nlWebUrl, String nlapikey, URL ntsUrl, String ntsLogin, String ntsPassword, String ntsLicenseID,int ntsmaxVu,int ntsmaxhour) throws NeoLoadException {

		final ArrayList<String> cmdArray = new ArrayList<>();

		StringBuilder result = new StringBuilder();
		String fileSeperator = "/";
		String extension="";
		if (IS_WINDOWS) {
			result.append(neoLoadPath + "\\bin");
			extension += ".exe";

			fileSeperator = "\\";
		} else {
			result.append(neoLoadPath + "/bin");
		}


		result.append(fileSeperator + neoLoadCmd+extension);

		cmdArray.add(result.toString());
		cmdArray.add("-project");
		cmdArray.add(projectPath);
		cmdArray.add("-launch");
		cmdArray.add(nlscenario);
		cmdArray.add("-noGUI");

		if (nlWebUrl != null) {
			if (nlapikey == null) {
				throw new NeoLoadException("NLWEB Error : The API cannot be empty");
			}
			cmdArray.add("-nlweb");

			cmdArray.add("-nlwebAPIURL");
			cmdArray.add(nlWebUrl);

			cmdArray.add("-nlwebToken");
			cmdArray.add(nlapikey);

		}

		cmdArray.add("-SLAJUnitMapping");
		cmdArray.add("pass");

		cmdArray.add("-SLAJUnitResults");
		cmdArray.add( resultFolder + fileSeperator + "junit.xml");

		if(ntsUrl!= null)
		{
			//-----case of using NTS for storing the license-----------
			if(ntsLogin==null || ntsPassword==null || ntsLicenseID==null)
				throw new NeoLoadException("NTS error : Login , Password and LicenseID cannot be null");

			//cmd.addRaw("-leaseLicense "+"\""+this.NTSLicenseID+":"+NbVU+":"+Nbhour+"\"");

			cmdArray.add("-NTS");
			cmdArray.add(ntsUrl.toString());
			cmdArray.add("-NTSLogin");
			cmdArray.add(ntsLogin+":"+ntsPassword);
			cmdArray.add("-leaseLicense");
			cmdArray.add(ntsLicenseID+":"+String.valueOf(ntsmaxVu)+":"+String.valueOf(ntsmaxVu));

			//-----------------------------------------------------------
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));

		// Create a filename from a format string.
		// ... Apply date formatting codes.
		String resultlastname = String.format("%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal) ;
		cmdArray.add("-description");
		cmdArray.add( projectPath + "_" + resultlastname);


		cmdArray.add("-report");
		cmdArray.add(resultFolder + fileSeperator + "report.xml," + resultFolder + fileSeperator + "report.pdf");



		return cmdArray.toArray(new String[0]);

	}
}
