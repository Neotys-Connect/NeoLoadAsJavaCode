import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.v3.project.server.Server;
import com.neotys.neoload.model.v3.project.userpath.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Created by hrexed on 09/07/18.
 */
public class ApiUserPath extends BaseNeoLoadUserPath {

	ApiUserPath(final BaseNeoLoadDesign design) {
		super(design);
	}

	@Override
	public UserPath createVirtualUser(final BaseNeoLoadDesign design) {
		final String countryName = design.getVariableAndColumnFromFileVariable("location", "Pays");
		final String longitude = design.getVariableAndColumnFromFileVariable("location", "longitude");
		final String latitude = design.getVariableAndColumnFromFileVariable("location", "latitude");
		final Server server = design.getServerByName("sampledemo");

		final List<String> postParameters = ImmutableList.of(
				parameter("incident_ampm", "am"),
				parameter("resp", "json"),
				parameter("incident_description", "API testing"),
				parameter("location_name", variabilize(countryName)),
				parameter("incident_title", "API testing"),
				parameter("longitude", variabilize(longitude)),
				parameter("incident_hour", "11"),
				parameter("latitude", variabilize(latitude)),
				parameter("incident_category", "1"),
				parameter("incident_date", "07/10/2018"),
				parameter("task", "report"),
				parameter("incident_minute", "49")
		);

		final Request postRequest = postFormBuilder(server, "/api", emptyList(),postParameters,emptyList(),Optional.of("slaprofile_2")).build();
		final Request getRequest = getBuilder(server, "/main", emptyList(),emptyList(),Optional.of("slaprofile_2")).build();

		final Delay delay1 = delay(800);
		final ThinkTime delay2 = thinkTime(1200);
		final Container actionsContainer = actionsContainerBuilder()
				.name("API Workflow")
				.addSteps(container("Api", Optional.empty(), getRequest, delay1))
				.addSteps(container("Post",Optional.empty(),postRequest, delay2))
				.build();

		return userPathBuilder("CreateReportAPI")
				.init(Optional.empty())
				.actions(actionsContainer)
				.end(Optional.empty())
				.build();
	}


}