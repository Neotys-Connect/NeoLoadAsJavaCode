package com.neotys.testing.framework; /**
 * Created by hrexed on 02/07/18.
 */

import com.google.common.collect.ImmutableMap;
import com.neotys.neoload.model.repository.FileVariable;
import com.neotys.neoload.model.repository.Server;
import com.neotys.neoload.model.repository.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

public abstract class BaseNeoLoadDesign {

	private final Map<String, BaseNeoLoadUserPath> userPathByName;
	private final Map<String, Server> serverByNames;
	private final Map<String, Variable> variableByNames;

	protected BaseNeoLoadDesign() {
		serverByNames = new HashMap<>();
		userPathByName = new HashMap<>();
		variableByNames = new HashMap<>();
		init();
	}

	//---------------------------------------------------------
	//--  init() : Method to initialize variables and servers
	//-----------------------------------------------------
	private void init() {
		createVariables();
		createServers();
		createNeoLoadUserPaths();
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
		final List<String> columnNames = file.getColumnsNames();
		String variableName = file.getName();
		for (final String name : columnNames) {
			if (columnName.equalsIgnoreCase(name))
				return variableName + "." + name;
		}
		return null;
	}

}
