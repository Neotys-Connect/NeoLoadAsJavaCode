import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.repository.*;
import com.neotys.testing.framework.BaseNeoLoadDesign;
import com.neotys.testing.framework.BaseNeoLoadUserPath;

import java.util.List;

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

		final List<Parameter> postParameters = ImmutableList.of(
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
		final Request postRequest = postFormBuilder(server, "/api", emptyList(), postParameters).build();
		final Request getRequest = getBuilder(server, "/main", emptyList()).build();
		final Delay delay1 = delay(800);
		final Delay delay2 = thinkTime(1200);
		final Container actionsContainer = actionsContainerBuilder()
				.addChilds(container("Api", getRequest, delay1, postRequest, delay2))
				.build();

		return userPathBuilder("CreateReportAPI")
				.actionsContainer(actionsContainer)
				.build();
	}


}