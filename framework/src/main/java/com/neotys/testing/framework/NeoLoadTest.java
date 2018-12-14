package com.neotys.testing.framework;

import com.google.common.collect.ImmutableMap;
import com.neotys.neoload.model.Project;
import com.neotys.neoload.model.repository.*;
import com.neotys.neoload.model.scenario.*;
import com.neotys.neoload.model.writers.neoload.NeoLoadWriter;
import com.neotys.testing.framework.plugin.apm.AppDynamicsIntegration;
import com.neotys.testing.framework.plugin.apm.DynatraceIntegration;
import com.neotys.testing.framework.plugin.apm.NewRelicIntegration;
import com.neotys.testing.framework.plugin.apm.sanityCheck.DynatraceSanityCheck;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hrexed on 03/07/18.
 */
public abstract class NeoLoadTest {

	private static final Path NEO_LOAD_TARGET_PATH = NeoloadFileUtils.getNeoLoadTargetPath();
	private final static String APPDYNAMICS="APPDYNAMICS";
	private final static String DYNATRACE="DYNATRACE";
	private final static String NEWRELIC="NEWRELIC";
	private final static String DYNATRACE_SANITYCHECK="DYNATRACE_SANITYCHECK";

	private BaseNeoLoadDesign design;
	private List<Population> populations;
	private List<Scenario> scenarios;
	private String projectName;

	@Before
	public void init() {
		this.populations = new ArrayList<>();
		this.scenarios = new ArrayList<>();
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

	public Population getAPMpopulation()
	{
		//---test for dynatrace
		Population population = getPopulationFromName(defaultPopulationNameForUserPath(DynatraceIntegration.DYNATRACE_USERPATH_NAME));
		if(population !=null)
			return population;
		else
		{
			//----test for newrelic
			population=getPopulationFromName(defaultPopulationNameForUserPath(NewRelicIntegration.NEWRELIC_USERPATH_NAME));
			if(population!=null)
				return population;
			else
			{
				//----test for Appd
				getPopulationFromName(defaultPopulationNameForUserPath(AppDynamicsIntegration.APPD_USERPATH_NAME));
				if(population!=null)
					return population;

			}
		}
		return null;
	}
	public Population getSanityCheckpopulation()
	{
		//---test for dynatrace
		Population population = getPopulationFromName(defaultPopulationNameForUserPath(DynatraceSanityCheck.DYNATRACE_USERPATH_NAME));
		if(population !=null)
			return population;
		else
		{
			//----test for other type of sanityCheck

		}
		return null;
	}
	public void createSanityCheckScenario()
	{
		Population sanitycheck=getSanityCheckpopulation();
		if(sanitycheck!=null)
		{
			createSimpleConstantIterationScenario(DYNATRACE_SANITYCHECK,DynatraceSanityCheck.DYNATRACE_USERPATH_NAME,1,1,0);
		}
	}
	public String getAPMProductsUsed()
	{
		Population apm=getAPMpopulation();
		if(apm!=null)
		{
			if(apm.getName().contains(DynatraceIntegration.DYNATRACE_USERPATH_NAME))
				return DYNATRACE;
			else
			{
				if(apm.getName().contains(NewRelicIntegration.NEWRELIC_USERPATH_NAME))
					return NEWRELIC;

				if(apm.getName().contains(AppDynamicsIntegration.APPD_USERPATH_NAME))
					return APPDYNAMICS;
			}
		}


		return null;

	}
	public String getSanityCheckProductsUsed()
	{
		Population senitycheck=getSanityCheckpopulation();
		if(senitycheck!=null)
		{
			if(senitycheck.getName().contains(DynatraceSanityCheck.DYNATRACE_USERPATH_NAME))
				return DYNATRACE;
			else
			{
				//----test for other sanitycheck
			}
		}


		return null;

	}

	public void createSimpleConstantLoadScenario(final String scenarioName, final String userPathName, final int duration, final int maxUser, final int rampupTime) {
		Population population = getPopulationFromName(defaultPopulationNameForUserPath(userPathName));
		Population apm;
		if (population != null) {
			apm=getAPMpopulation();
			Scenario.Builder scenario = Scenario.builder().name(scenarioName)
					.addPopulations(PopulationPolicy.builder()
							.name(population.getName())
							.loadPolicy(ConstantLoadPolicy.builder()
									.duration(Duration.builder().type(Duration.Type.TIME).value(duration).build())
									.users(maxUser)
									.rampup(rampupTime)
									.build()
							)
							.build()
					);
			if(apm!=null)
			{
				scenario.addPopulations(PopulationPolicy.builder()
				.name(apm.getName())
				.loadPolicy(ConstantLoadPolicy.builder()
					.duration(Duration.builder().type(Duration.Type.TIME).value(duration).build())
					.users(1)
					.rampup(0)
					.build())
				.build()
				);
			}


			scenarios.add(scenario.build());
		}
	}

	private void addAPMSettings(final Project.Builder project)
	{
		String apm;
		HashMap<String,String> apmsettings=new HashMap<>();
		apm=getAPMProductsUsed();
		if(apm!=null)
		{
			switch (apm)
			{
				case DYNATRACE:
					apmsettings.put("dynatrace.enabled","true");
					apmsettings.put("dynatrace.logicalnames.enabled","true");
					break;
				case APPDYNAMICS:
					apmsettings.put("appdynamics.enabled","true");
					//apmsettings.put("dynatrace.logicalnames.enabled","true");

					break;
			}
		}

		if(!apmsettings.isEmpty())
		{
			project.projectSettings(apmsettings);
		}


	}

	@Test
	public abstract void execute();

	@After
	public void generateNlProject() {
		final Project.Builder projectBuilder = Project.builder()
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

		final List<File> files = design.getVariables().values().stream().filter(v -> v instanceof FileVariable)
				.map(v -> (FileVariable) v)
				.map(FileVariable::getFileName)
				.map(path -> new File(path.get()))
				.collect(Collectors.toList());


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
		//---add the project settings required for the apm integration
		addAPMSettings(projectBuilder);
		final Project project = projectBuilder.build();

		final String output = getOrCreateProjectFolder(project);
		//-----creation of the project
		NeoLoadWriter writer = new NeoLoadWriter(project, output, ImmutableMap.of("variables", files));
		writer.write(true);

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

	protected void createSimpleConstantIterationScenario(final String scenarioName, final String userPathName, final int incremenNumber, final int maxUser, final int rampupTime) {
		final Population population = getPopulationFromName(defaultPopulationNameForUserPath(userPathName));
		Population apm;

		if (population != null) {
			apm = getAPMpopulation();

			final Scenario.Builder scenario;

			scenario = Scenario.builder()
					.name(scenarioName)
					.addPopulations(PopulationPolicy.builder()
							.name(population.getName())
							.loadPolicy(ConstantLoadPolicy.builder()
									.duration(Duration.builder().type(Duration.Type.ITERATION).value(incremenNumber).build())
									.users(maxUser)
									.rampup(rampupTime)
									.build()
							)
							.build()
					);


			if (apm != null) {
				scenario.addPopulations(PopulationPolicy.builder()
						.name(apm.getName())
						.loadPolicy(ConstantLoadPolicy.builder()
								.duration(Duration.builder().type(Duration.Type.ITERATION).value(incremenNumber).build())
								.users(1)
								.rampup(0)
								.build())
						.build()
				);
			}
			scenarios.add(scenario.build());
		}
	}
	protected void createSimpleRampupLoadScenario(final String scenarioName, final String userPathName, final int duration,
												  final int initialNbVU, final int incrementNbVu, final Optional<Integer> maxvu, final int incrementTime) {
		final Population population = getPopulationFromName(defaultPopulationNameForUserPath(userPathName));
		Population apm;

		if (population != null) {
			apm=getAPMpopulation();

			final Scenario.Builder scenario ;
			if(maxvu.isPresent())
			{
				scenario = Scenario.builder()
						.name(scenarioName)
						.addPopulations(PopulationPolicy.builder()
								.name(population.getName())
								.loadPolicy(RampupLoadPolicy.builder()
										.duration(Duration.builder().type(Duration.Type.TIME).value(duration).build())
										.incrementUsers(incrementNbVu)
										.incrementEvery(Duration.builder().type(Duration.Type.TIME).value(incrementTime).build())
										.minUsers(initialNbVU)
										.maxUsers(maxvu.get())
										.build()
								)
								.build()
						);
			}
			else {
				scenario = Scenario.builder()
						.name(scenarioName)
						.addPopulations(PopulationPolicy.builder()
								.name(population.getName())
								.loadPolicy(RampupLoadPolicy.builder()
										.duration(Duration.builder().type(Duration.Type.TIME).value(duration).build())
										.incrementUsers(incrementNbVu)
										.incrementEvery(Duration.builder().type(Duration.Type.TIME).value(incrementTime).build())
										.minUsers(initialNbVU)
										.build()
								)
								.build()
						);
			}
			if(apm!=null)
			{
				scenario.addPopulations(PopulationPolicy.builder()
				.name(apm.getName())
				.loadPolicy(ConstantLoadPolicy.builder()
						.duration(Duration.builder().type(Duration.Type.TIME).value(duration).build())
						.users(1)
						.rampup(0)
						.build())
				.build()
				);
			}
			scenarios.add(scenario.build());
		}
	}

	enum TypeScenario {
		CONSTANT, RAMPUP, PEAK;
	}

}