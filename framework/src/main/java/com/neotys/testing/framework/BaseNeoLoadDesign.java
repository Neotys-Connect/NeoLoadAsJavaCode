package com.neotys.testing.framework; /**
 * Created by hrexed on 02/07/18.
 */

import com.google.common.collect.ImmutableMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.neotys.neoload.model.v3.project.server.*;
import com.neotys.neoload.model.v3.project.sla.SlaProfile;
import com.neotys.neoload.model.v3.project.sla.SlaThreshold;
import com.neotys.neoload.model.v3.project.sla.SlaThresholdCondition;
import com.neotys.neoload.model.v3.project.variable.*;
import com.neotys.testing.framework.utils.datamodel.SlaProfiles;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

public abstract class BaseNeoLoadDesign {

	private final Map<String, BaseNeoLoadUserPath> userPathByName;
	private final Map<String, Server> serverByNames;
	private final Map<String, Variable> variableByNames;
	private List<SlaProfile> slaProfiles;
	private final Optional<String> jsonSLAProfile;
	protected BaseNeoLoadDesign(Optional<String> jsonSLAProfile) throws FileNotFoundException {
		this.jsonSLAProfile = jsonSLAProfile;
		serverByNames = new HashMap<>();
		userPathByName = new HashMap<>();
		variableByNames = new HashMap<>();
		init();
	}

	//---------------------------------------------------------
	//--  init() : Method to initialize variables and servers
	//-----------------------------------------------------
	private void init() throws FileNotFoundException {
		createVariables();
		createServers();
		createNeoLoadUserPaths();
		slaProfiles = new ArrayList<>();
		if(jsonSLAProfile.isPresent())
		{
			createSLAProfiles(jsonSLAProfile.get());
		}
	}

	public abstract void createNeoLoadUserPaths();

	public abstract void createVariables();

	public abstract void createServers();

	public void addServer(final Server server) {
		serverByNames.put(server.getName(), server);
	}

	public void addServers(final Server... servers) {
		for (final Server server : servers) {
			addServer(server);
		}
	}

	public void addVariable(final Variable variable) {
		variableByNames.put(variable.getName(), variable);
	}

	public void addVariables(final Variable... variables) {
		for (final Variable variable : variables) {
			addVariable(variable);
		}
	}

	public List<SlaProfile> getSlaProfiles() {
		return slaProfiles;
	}


	public void addVirtualUser(final BaseNeoLoadUserPath userPath) {
		userPathByName.put(userPath.getName(), userPath);
	}

	public Map<String, BaseNeoLoadUserPath> getUserPaths() {
		return ImmutableMap.copyOf(userPathByName);
	}

	public Map<String, Server> getServers() {
		return ImmutableMap.copyOf(serverByNames);
	}

	public Map<String, Variable> getVariables() {
		return ImmutableMap.copyOf(variableByNames);
	}

	public Variable getVariableByName(final String name) {
		return variableByNames.get(name);
	}

	public Server getServerByName(final String name) {
		return serverByNames.get(name);
	}

	public BaseNeoLoadUserPath getUserPathByName(final String name) {
		return userPathByName.get(name);
	}

	public String getVariableAndColumnFromFileVariable(final String fileVariableName, final String columnName) {
		final Variable var = getVariableByName(fileVariableName);
		checkState(var != null, "Variable %s is not defined in this design.", fileVariableName);
		checkState(var instanceof FileVariable, "Variable %s is not a FileVariable.", fileVariableName);
		final FileVariable file = (FileVariable) var;
		final List<String> columnNames = file.getColumnNames();
		String variableName = file.getName();
		for (final String name : columnNames) {
			if (columnName.equalsIgnoreCase(name))
				return variableName + "." + name;
		}
		return null;
	}


	//---json file for SLAprofileDefinition----
	//format :
	//  sla_profile:
	//[
	//	{
	//		name: ,
	//      description :
	//		thresholds:
	//		[
	//			{
	//				indicators:,
	//				severity: ,
	//				operator: ,
	//				value: ,
	//				unit: ,
	//				scope: ,
	//			},
	//			{
	//
	//			}
	//	}
	//]
	//------------------------------------------
	protected void createSLAProfiles(String jsonFile) throws FileNotFoundException {
		SlaProfiles slaNeoLoadProfile;
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();


		JsonReader reader = new JsonReader(new FileReader(jsonFile));
		slaNeoLoadProfile = gson.fromJson(reader, SlaProfiles.class);

		//----remove sla having a wrong definition-----
		slaNeoLoadProfile.removeCleanSla();
		//---------------------------------------

		slaNeoLoadProfile.getSla_profile().stream().forEach(profile -> {
			slaProfiles.add(SlaProfile.builder()
					.name(profile.getName())
					.description(profile.getDescription())
					.addAllThresholds(profile.getThresholds().stream().map(threshold -> {
						return SlaThreshold.builder()
								.scope(SlaThreshold.Scope.of(threshold.getScope()))
								.addConditions(SlaThresholdCondition.builder()
										.operator(SlaThresholdCondition.Operator.of(threshold.getOperator()))
										.value(threshold.getValue())
										.severity(SlaThresholdCondition.Severity.of(threshold.getSeverity()))
										.build()
								).kpi(SlaThreshold.KPI.valueOf(threshold.getKpiName()))
								.percent(threshold.getPercentile() == null ? Optional.empty() : threshold.getPercentile())
								.build();
					}).collect(Collectors.toList()))
					.build());

		});
	}
}
