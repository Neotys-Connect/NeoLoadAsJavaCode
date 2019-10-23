import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.NeoLoadTest;

import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * Created by hrexed on 10/07/18.
 */
public class ApiLoadTest extends NeoLoadTest {

	@Override
	protected BaseNeoLoadDesign design() {
		try {
			return new ApiTestDesign();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String projectName() {
		return "ApiLoad";
	}

	@Override
	public void createComplexPopulation() {

	}

	@Override
	public void createComplexScenario() {

	}

	@Override
	public void execute() {

		createSimpleRampupLoadScenario("API test", "CreateReportAPI", 90, 1, 1, Optional.empty(),5,Optional.of("slaprofile_1"));
	//	createSanityCheckScenario();
	}
}
