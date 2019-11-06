import com.neotys.neoload.model.v3.project.server.Server;
import com.neotys.neoload.model.v3.project.variable.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.plugin.apm.DynatraceIntegration;
import com.neotys.testing.framework.plugin.apm.sanityCheck.DynatraceSanityCheck;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Optional;

import static com.neotys.testing.framework.utils.NeoLoadHelper.*;

/**
 * Created by hrexed on 10/07/18.
 */
public class ApiTestDesign extends BaseNeoLoadDesign {

	ApiTestDesign() throws FileNotFoundException {
		super(Optional.of("src/test/resources/slaprofile.json"));
	}

	@Override
	public void createNeoLoadUserPaths() {
		this.addVirtualUser(new ApiUserPath(this));
		this.addVirtualUser(new DynatraceIntegration(this));
		this.addVirtualUser(new DynatraceSanityCheck(this));
	}

	@Override
	public void createVariables() {
		final ConstantVariable server = createConstantVariable("sampledemo-host", "sampledemo.neotys.com");
		final ConstantVariable port = createConstantVariable("sampledemo-port", "80");
		//TODO take care about file path, perhaps we should use a mechanism to copy the source file to the NeoLoad project folder ?
		final String pathFileName = Paths.get("src/test/resources/list_capital.csv").toAbsolutePath().toString();
		final FileVariable location = createFileVariable("location", "Data Set for the location", pathFileName, true, ";", Variable.Scope.GLOBAL, Variable.OutOfValue.CYCLE, Variable.Order.RANDOM, Variable.ChangePolicy.EACH_ITERATION, 1);

		this.addVariables(server, port, location);
	}

	@Override
	public void createServers() {
		//TODO if variable not initialized => error
		//use varname directly instead of getting var to use it
		final Variable server = getVariableByName("sampledemo-host");
		final Variable port = getVariableByName("sampledemo-port");
		this.addServer(createServer("sampledemo", server, port, Server.Scheme.HTTP,Optional.empty()));
	}
}
