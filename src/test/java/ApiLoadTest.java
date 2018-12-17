import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.NeoLoadTest;

import java.util.Optional;

/**
 * Created by hrexed on 10/07/18.
 */
public class ApiLoadTest extends NeoLoadTest {

	@Override
	protected BaseNeoLoadDesign design() {
		return new ApiTestDesign();
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
		createSimpleRampupLoadScenario("API test", "CreateReportAPI", 90, 1, 1, Optional.empty(),5);
	}
}
