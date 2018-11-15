package com.neotys.testing.framework.utils;

import com.neotys.neoload.model.repository.*;
import com.neotys.neoload.model.repository.Variable.VariableNoValuesLeftBehavior;
import com.neotys.neoload.model.repository.Variable.VariableOrder;
import com.neotys.neoload.model.repository.Variable.VariablePolicy;
import com.neotys.neoload.model.repository.Variable.VariableScope;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.List;
import java.util.Map;

/**
 * Created by hrexed on 11/07/18.
 */
public final class NeoLoadHelper {

	public static Server getServerFromList(final Map<String, Server> serverByNames, final String serverName) {
		return serverByNames.get(serverName);
	}

	public static Variable getVariableFromList(final Map<String, Variable> variableByNames, final String variableName) {
		return variableByNames.get(variableName);
	}

	public static Server createServer(final String host, final String port) {
		return ImmutableServer.builder()
				.name(host)
				.host(host)
				.port(port)
				.build();
	}

	public static Server createServer(final String host, final int port) {
		return ImmutableServer.builder()
				.name(host)
				.host(host)
				.port(Integer.toString(port))
				.build();
	}

	public static Server createServer(final String serverName, final Variable host, final Variable port) {
		return ImmutableServer.builder()
				.name(serverName)
				.host(variabilize(host))
				.port(variabilize(port))
				.build();
	}

	public static String variabilize(final Variable var) {
		return "${" + var.getName() + "}";
	}

	public static String getVariableAndColumnFromFileVariable(final FileVariable file, final String columnName) {
		final List<String> columnNames = file.getColumnsNames();
		String variableName = file.getName();
		for (String name : columnNames) {
			if (columnName.equalsIgnoreCase(name))
				return variableName + "." + name;
		}
		return null;
	}

	public static ConstantVariable createConstantVariable(final String variableName, final String description, final String value) {
		return ImmutableConstantVariable.builder()
				.constantValue(value)
				.description(description)
				.name(variableName)
				.constantValue(value)
				.policy(VariablePolicy.EACH_VUSER)
				.scope(VariableScope.GLOBAL)
				.build();
	}

	public static ConstantVariable createConstantVariable(final String variableName, final String value) {
		return createConstantVariable(variableName, variableName, value);
	}

	public static RandomNumberVariable createRandomNumberVariable(final String name, final String description, final VariablePolicy changePolicy,
	                                                              final int min, final int max) {
		return  ImmutableRandomNumberVariable.builder()
				.description(description)
				.name(name)
				.minValue(min)
				.maxValue(max)
				.policy(changePolicy)
				.build();
	}

	public static CounterNumberVariable createCounterVariable(final String name, final String descritpion, final VariablePolicy changePolicy, final int startValue,
	                                                          final int max, final int increment, final VariableScope scope,
	                                                          final VariableNoValuesLeftBehavior noValueLeft) {

		return ImmutableCounterNumberVariable.builder()
				.description(descritpion)
				.increment(increment)
				.name(name)
				.maxValue(max)
				.startValue(startValue)
				.scope(scope)
				.noValuesLeftBehavior(noValueLeft)
				.policy(changePolicy)
				.build();
	}



	public static FileVariable createFileVariable(final String name, final String description, final String pathFileName, final boolean isFirstLineColumnName,
	                                              final String columnDelimiter, final VariableScope scope,
	                                              final VariableNoValuesLeftBehavior noValueLeft, final VariableOrder order,
	                                              final VariablePolicy changePolicy, final int numOfFirstRowData) {
		return ImmutableFileVariable.builder()
				.description(description)
				.name(name)
				.fileName(pathFileName)
				.firstLineIsColumnName(isFirstLineColumnName)
				.policy(changePolicy)
				.columnsDelimiter(columnDelimiter)
				.scope(scope)
				.numOfFirstRowData(numOfFirstRowData)
				.noValuesLeftBehavior(noValueLeft)
				.order(order)
				.build();
	}

	public static FileVariable createFileVariable(final String name, final String description, final String[][] data, final boolean isFirstLineColumnName,
	                                              final String columnDelimiter, final VariableScope scope, final VariableNoValuesLeftBehavior noValueLeft,
	                                              final VariableOrder order, final VariablePolicy changePolicy, final int numOfFirstRowData) {

		return ImmutableFileVariable.builder()
				.description(description)
				.name(name)
				.data(data)
				.firstLineIsColumnName(isFirstLineColumnName)
				.policy(changePolicy)
				.columnsDelimiter(columnDelimiter)
				.scope(scope)
				.numOfFirstRowData(numOfFirstRowData)
				.noValuesLeftBehavior(noValueLeft)
				.order(order)
				.build();
	}
}
