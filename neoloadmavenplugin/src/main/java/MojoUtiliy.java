import org.apache.commons.lang3.SystemUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.Instant;

/**
 * Created by hrexed on 29/06/18.
 */
final class MojoUtiliy {
	private static boolean IS_WINDOWS = SystemUtils.IS_OS_WINDOWS;
	private static String neoLoadCmd = "NeoLoadCmd";

	private MojoUtiliy() {
	}

	public static String[] generateCmd(String projectPath, String neoLoadPath, String nlscenario, String resultFolder, String nlWebUrl, String nlapikey, URL ntsUrl, String ntsLogin, String ntsPassword, String ntsLicenseID,int ntsmaxVu,int ntsmaxhour) throws NeoLoadException {

		final ArrayList<String> cmdArray = new ArrayList<>();

		StringBuilder result = new StringBuilder();
		String fileSeperator = "/";

		if (IS_WINDOWS) {
			result.append(neoLoadPath + "\\bin");
			neoLoadCmd += ".exe";
			fileSeperator = "\\";
		} else {
			result.append(neoLoadPath + "/bin");
		}


		result.append(fileSeperator + neoLoadCmd);

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
		String resultlastname = String.format("%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal);
		cmdArray.add("-description");
		cmdArray.add( projectPath + "_" + resultlastname);

		cmdArray.add("-report");
		cmdArray.add(resultFolder + fileSeperator + "report.xml," + resultFolder + fileSeperator + "report.pdf");

		return cmdArray.toArray(new String[0]);

	}
}
