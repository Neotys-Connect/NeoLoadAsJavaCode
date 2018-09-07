package com.neotys.testing.framework;

import com.neotys.neoload.model.ImmutableProject;
import com.neotys.neoload.model.Project;
import com.neotys.neoload.model.repository.*;
import com.neotys.neoload.model.scenario.*;
import com.neotys.neoload.model.writers.neoload.NeoLoadWriter;
import com.neotys.testing.framework.utils.NeoloadFileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hrexed on 03/07/18.
 */
public abstract class NeoLoadTest {

	private static final Path NEO_LOAD_TARGET_PATH = NeoloadFileUtils.getNeoLoadTargetPath();

	private BaseNeoLoadDesign design;
	private List<Population> populations;
	private List<Scenario> scenarios;
	private String projectName;

	@Before
	public void init() {
		populations = new ArrayList<>();
		scenarios = new ArrayList<>();
		this.design = design();
		this.projectName = projectName();
		generateDefaultPopulation();
	}

	protected abstract BaseNeoLoadDesign design();

	protected abstract String projectName();

	private void generateDefaultPopulation() {
		design.getUserPaths().forEach((name, userPath) -> {
			final ImmutablePopulation pop = ImmutablePopulation.builder()
					.name(defaultPopulationNameForUserPath(name))
					.description(name)
					.addSplits(ImmutablePopulationSplit.builder()
							.userPath(name)
							.percentage(100)
							.build())
					.build();
			addPopulation(pop);
		});
	}

	private String defaultPopulationNameForUserPath(final String name) {
		return "Population_" + name;
	}

	public BaseNeoLoadUserPath getUserPathFromName(String name) {
		for (Map.Entry<String, BaseNeoLoadUserPath> vu : design.getUserPaths().entrySet()) {
			if (name.equalsIgnoreCase(vu.getValue().getName()))
				return vu.getValue();
		}
		return null;
	}

	private Population getPopulationFromName(final String name) {
		for (final Population p : populations) {
			if (name.equalsIgnoreCase(p.getName()))
				return p;
		}
		return null;
	}

	public abstract void createComplexPopulation();

	private void addPopulation(final Population p) {
		populations.add(p);
	}

	public void addScenario(final Scenario s) {
		scenarios.add(s);
	}

	public abstract void createComplexScenario();

	public void createSimpleConstantLoadScenario(final String scenarioName, final String userPathName, final int duration, final int maxUser, final int rampupTime) {
		Population population = getPopulationFromName(defaultPopulationNameForUserPath(userPathName));
		if (population != null) {
			Scenario scenario = ImmutableScenario.builder().name(scenarioName)
					.putPopulations(population.getName(), ImmutableScenarioPolicies.builder()
							.durationPolicy(ImmutableTimeDurationPolicy.builder().duration(duration).build())
							.loadPolicy(ImmutableConstantLoadPolicy.builder().load(maxUser).build())
							.build())
					.build();

			scenarios.add(scenario);
		}
	}

	@Test
	public abstract void execute();

	@After
	public void generateNlProject() {
		final ImmutableProject.Builder projectBuilder = ImmutableProject.builder()
				.name(this.projectName);

		final JSONObject jsonProject = new JSONObject();
		final JSONArray jsonScenarios = new JSONArray();

		//-- Add all the user Path in the Project
		final List<UserPath> userPaths = design.getUserPaths().values()
				.stream()
				.map(BaseNeoLoadUserPath::getVirtualUser)
				.collect(Collectors.toList());
		projectBuilder.addAllUserPaths(userPaths);

		//---Add all the Variables IN the project--------
		projectBuilder.addAllVariables(design.getVariables().values());

		//--add all the servers in the project---------
		projectBuilder.addAllServers(design.getServers().values());

		//----Add all the population in the project-----
		for (Population population : populations) {
			projectBuilder.addPopulations(population);
		}

		//---add all the scenarios-------
		for (Scenario scenario : scenarios) {
			projectBuilder.addScenarios(scenario);
			jsonScenarios.add(scenario.getName());
		}

		final Project project = projectBuilder.build();

		final String output = getOrCreateProjectFolder(project);
		//-----creation of the project
		NeoLoadWriter writer = new NeoLoadWriter(project, output, null);
		writer.write(false);

		//------

		//---save the descirption file required for the maven plugin
		jsonProject.put("Project", writer.getNlProjectFolder() + "/" + projectName + ".nlp");
		jsonProject.put("Scenarios", jsonScenarios);
		try {
			writeJsonToFile(jsonProject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//-------------------------------
	}

	private void writeJsonToFile(final JSONObject obj) throws IOException {
		String fileName = NeoloadFileUtils.getJsonFileName();

		JSONObject previousJsonObj;
		JSONArray projectList;
		JSONParser parser;
		final String neoLoadJsonFilePath = NEO_LOAD_TARGET_PATH + "/" + fileName;
		try {

			parser = new JSONParser();
			previousJsonObj = (JSONObject) parser.parse(new FileReader(neoLoadJsonFilePath));
			projectList = (JSONArray) previousJsonObj.get("NeoLoadProjectList");
			projectList.add(obj);
			saveJsontoFile(previousJsonObj, neoLoadJsonFilePath);

		} catch (FileNotFoundException e) {
			previousJsonObj = new JSONObject();
			projectList = new JSONArray();

			projectList.add(obj);
			previousJsonObj.put("NeoLoadProjectList", projectList);
			saveJsontoFile(previousJsonObj, neoLoadJsonFilePath);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void saveJsontoFile(JSONObject obj, String Path) throws IOException {
		FileWriter file = new FileWriter(Path);
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	}

	private String getOrCreateProjectFolder(final Project project) {
		final File neoloadDir = NeoloadFileUtils.getNeoLoadProjectPath(project.getName()).toFile();
		if (!neoloadDir.exists()) {
			try {
				neoloadDir.mkdir();
			} catch (final Throwable t) {
				//TODO use logger
				System.out.println("Impossible to create the NeoLoad Folder");
				t.printStackTrace();
				throw t;
			}
		}
		return neoloadDir.getAbsolutePath();
	}

	protected void createSimpleRampupLoadScenario(final String scenarioName, final String userPathName, final int duration,
	                                              final int initialNbVU, final int incrementNbVu, final int incrementTime) {
		final Population population = getPopulationFromName(defaultPopulationNameForUserPath(userPathName));
		if (population != null) {
			final Scenario scenario = ImmutableScenario.builder()
					.name(scenarioName)
					.putPopulations(population.getName(), ImmutableScenarioPolicies.builder()
							.durationPolicy(ImmutableTimeDurationPolicy.builder().duration(duration).build())
							.loadPolicy(ImmutableRampupLoadPolicy.builder().initialLoad(initialNbVU)
									.incrementLoad(incrementNbVu)
									.incrementTime(incrementTime)
									.build())
							.build())
					.build();

			scenarios.add(scenario);
		}
	}

	enum TypeScenario {
		CONSTANT, RAMPUP, PEAK;
	}

}