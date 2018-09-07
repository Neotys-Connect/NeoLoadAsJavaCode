import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.net.URL;

/**
 * Created by hrexed on 29/06/18.
 */
public abstract class AbstractNeoLoadMojo  extends AbstractMojo {
    protected static final String LINE_SEPARATOR = "-------------------------------------------------------";
    protected  static final String NeoLoad_List_of_Test="NeoLoad_List_Of_Test.json";
    /**
     * Project directory.
     */

    @Parameter( defaultValue = "c:\\Program Files\\NeoLoad", readonly = false )
    protected File neoLoadInstallationDirectory;

    @Parameter
    protected URL neoLoadWebUrl;

    @Parameter
    protected String neoLoadWebAPIKey;

    @Parameter(defaultValue = "${project.build.directory}/neoload")
    protected File neoLoadProjectDirectory;


    @Parameter(defaultValue = "${project.build.directory}/neoload/reports")
    protected File reportDirectory;

    @Parameter
    protected URL neotysTeamServerURL;

    @Parameter
    protected String neotysTeamServerLogin;

    @Parameter
    protected String neotysTeamServerEncryptedPassword;

    @Parameter
    protected String neotysTeamServerLicenseID;

    @Parameter
    protected int neotysTeamMaxVuRequired;

    @Parameter
    protected int neotysTeamMaxTestDuration;
}
