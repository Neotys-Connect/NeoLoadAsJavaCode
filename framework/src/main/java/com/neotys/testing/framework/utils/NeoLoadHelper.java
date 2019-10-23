package com.neotys.testing.framework.utils;

import com.google.common.collect.ImmutableList;
import com.neotys.neoload.model.repository.CounterNumberVariable;
import com.neotys.neoload.model.repository.ImmutableCounterNumberVariable;
import com.neotys.neoload.model.v3.project.*;
import com.neotys.neoload.model.repository.Variable.VariableNoValuesLeftBehavior;
import com.neotys.neoload.model.repository.Variable.VariableOrder;
import com.neotys.neoload.model.repository.Variable.VariablePolicy;
import com.neotys.neoload.model.repository.Variable.VariableScope;
import com.neotys.neoload.model.v3.project.server.*;
import com.neotys.neoload.model.v3.project.variable.*;
import com.neotys.neoload.model.writers.RegExpUtils;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hrexed on 11/07/18.
 */
public final class NeoLoadHelper {

	public final static String NTML="NTML";
	public final static String BASIC="BASIC";
	public final static String NEGOCIATE="NEGOCIATE";

	public static Server getServerFromList(final Map<String, Server> serverByNames, final String serverName) {
		return serverByNames.get(serverName);
	}

	public static Variable getVariableFromList(final Map<String, Variable> variableByNames, final String variableName) {
		return variableByNames.get(variableName);
	}

	public static Server createServer(final String host, final String port) {
		return Server.builder()
				.name(host)
				.host(host)
				.port(port)
				.build();
	}

	public static Authentication createAuthentification(String type,Variable username,Variable pwd,Optional<String> domain)
	{
		switch(type)
		{
			case NTML:
				return NtlmAuthentication.builder()
						.domain(domain)
						.login(variabilize(username))
						.password(variabilize(pwd))
						.build();

			case BASIC:
				return BasicAuthentication.builder()
						.login(variabilize(username))
						.password(variabilize(pwd))
						.realm(domain)
						.build();
			case NEGOCIATE:
				return NegotiateAuthentication.builder()
						.login(variabilize(username))
						.password(variabilize(pwd))
						.domain(domain)
						.build();
			default:
				return null;

		}
	}


	public static Authentication createAuthentification(String type,String username,String pwd,Optional<String> domain)
	{
		switch(type)
		{
			case NTML:
				return NtlmAuthentication.builder()
						.domain(domain)
						.login(username)
						.password(pwd)
						.build();

			case BASIC:
				return BasicAuthentication.builder()
						.login(username)
						.password(pwd)
						.realm(domain)
						.build();
			case NEGOCIATE:
				 return NegotiateAuthentication.builder()
						 .login(username)
						 .password(pwd)
						 .domain(domain)
						 .build();
			 default:
			 	return null;

		}

	}

	public static Server createServer(final String servername, final String host, final int port, Server.Scheme scheme,Optional<Authentication> auth) {
		return Server.builder()
				.name(servername)
				.host(host)
				.scheme(scheme)
				.authentication(auth)
				.port(Integer.toString(port))
				.build();
	}

	public static Server createServer(final String serverName, final Variable host, final Variable port, Server.Scheme scheme,Optional<Authentication> auth) {
		return Server.builder()
				.name(serverName)
				.scheme(scheme)
				.authentication(auth)
				.host(variabilize(host))
				.port(variabilize(port))
				.build();
	}

	public static String variabilize(final Variable var) {
		return "${" + var.getName() + "}";
	}

	public static String getVariableAndColumnFromFileVariable(final FileVariable file, final String columnName) {
		final List<String> columnNames = file.getColumnNames();
		String variableName = file.getName();
		for (String name : columnNames) {
			if (columnName.equalsIgnoreCase(name))
				return variableName + "." + name;
		}
		return null;
	}



	public static ConstantVariable createConstantVariable(final String variableName, final String description, final String value) {
		return ConstantVariable.builder()
				.value(value)
				.description(description)
				.name(variableName)
				.changePolicy(Variable.ChangePolicy.EACH_USER)
				.scope(Variable.Scope.GLOBAL)
				.build();
	}

	public static ConstantVariable createConstantVariable(final String variableName, final String value) {
		return createConstantVariable(variableName, variableName, value);
	}

	public static RandomNumberVariable createRandomNumberVariable(final String name, final String description, final Variable.ChangePolicy changePolicy,
																  final int min, final int max) {
		return  RandomNumberVariable.builder()
				.description(description)
				.name(name)
				.min(min)
				.max(max)
				.changePolicy(changePolicy)
				.build();
	}

	public static CounterVariable createCounterVariable(final String name, final String descritpion, final Variable.ChangePolicy changePolicy, final int startValue,
															  final int max, final int increment, final Variable.Scope  scope,
															  final Variable.OutOfValue noValueLeft) {

		return CounterVariable.builder()
				.description(descritpion)
				.increment(increment)
				.name(name)
				.end(max)
				.start(startValue)
				.scope(scope)
				.outOfValue(noValueLeft)
				.changePolicy(changePolicy)
				.build();
	}


	static List<String> getColumnsFromFile(String fileName, String columnsDelimiter) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			return getColumsFromFirstLine(stream.findFirst(), columnsDelimiter);
		}
	}

	static List<String> getColumsFromFirstLine(Optional<String> firstLine, String columnsDelimiter) {
		if(!firstLine.isPresent()) return ImmutableList.of();
		return Arrays.stream(firstLine.get().split(RegExpUtils.escape(columnsDelimiter))).map(s -> s.trim()).collect(Collectors.toList());
	}

	public static FileVariable createFileVariable(final String name, final String description, final String pathFileName, final boolean isFirstLineColumnName,
	                                              final String columnDelimiter, final Variable.Scope scope,
	                                              final Variable.OutOfValue noValueLeft, final Variable.Order order,
	                                              final Variable.ChangePolicy changePolicy, final int numOfFirstRowData) {
		if(isFirstLineColumnName)
		{
			try {
				List<String> colunName = getColumnsFromFile(pathFileName, columnDelimiter);

				return FileVariable.builder()
						.description(description)
						.name(name)
						.path(pathFileName)
						.isFirstLineColumnNames(isFirstLineColumnName)
						.changePolicy(changePolicy)
						.delimiter(columnDelimiter)
						.addAllColumnNames(colunName)
						.scope(scope)
						.startFromLine(numOfFirstRowData)
						.outOfValue(noValueLeft)
						.order(order)
						.build();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else {
			return FileVariable.builder()
					.description(description)
					.name(name)
					.path(pathFileName)
					.isFirstLineColumnNames(isFirstLineColumnName)
					.changePolicy(changePolicy)
					.delimiter(columnDelimiter)
					.scope(scope)
					.startFromLine(numOfFirstRowData)
					.outOfValue(noValueLeft)
					.order(order)
					.build();
		}
		return null;
	}


}
