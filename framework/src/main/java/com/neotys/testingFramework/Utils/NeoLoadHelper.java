package com.neotys.testingFramework.Utils;

import com.neotys.converters.datamodel.repository.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hrexed on 11/07/18.
 */
public final class NeoLoadHelper {

    public static Server getServerFromList(HashMap<String,Server> serverlist, String serverName)
    {
        return serverlist.get(serverName);
    }

    public static Variable getVariableFromList(HashMap<String,Variable> variablelist, String variablerName)
    {
        return variablelist.get(variablerName);
    }

    public static Server createServer(String host, String port)
    {
        Server server = ImmutableServer.builder().name(host).host(host).port(port).build();
        return server;
    }



    public static Server createServer(String host, int port)
    {
        Server server = ImmutableServer.builder().name(host).host(host).port(Integer.toString(port)).build();
        return server;
    }
    public static Server createServer(ConstantVariable host, ConstantVariable port)
    {
        Server server = ImmutableServer.builder().name(host.getName()).host("${"+host.getName()+"}").port("${"+port.getName()+"}").build();
        return server;
    }

    public static Server createServer(Variable host, Variable port)
    {
        Server server = ImmutableServer.builder()
                .name(host.getName())
                .host("${"+host.getName()+"}")
                .port("${"+port.getName()+"}")
                .build();
        return server;
    }

    public static String getVariableAndColumnFromFileVariable(FileVariable file, String columnname)
    {
       List<String> columnNames=file.getColumnsNames();
       String variableName=file.getName();
       for(String name:columnNames)
       {
           if(columnname.equalsIgnoreCase(name))
               return variableName+"."+name;
       }
        return null;
    }
    public static ConstantVariable createConstantVariable(String variableName, String description, String value)
    {
        ConstantVariable constant= ImmutableConstantVariable.builder().constantValue(value).description(description)
                .name(variableName)
                .constantValue(value)
                .policy(Variable.VariablePolicy.EACH_VUSER)
                .scope(Variable.VariableScope.GLOBAL)
                .build();

        return constant;
    }

    public static RandomNumberVariable createRandomNumberVariable(String name, String description, Variable.VariablePolicy changePolicy, int min, int max)
    {
        RandomNumberVariable random;
        random= ImmutableRandomNumberVariable.builder().description(description)
                .name(name)
                .minValue(min)
                .maxValue(max)
                .policy(changePolicy)
                .build();
        return random;
    }

    public static CounterNumberVariable createCounterVariable(String name, String descritpion, Variable.VariablePolicy changePolicy, int startvalue, int max, int incremet, Variable.VariableScope Scope, Variable.VariableNoValuesLeftBehavior NoValueLeft)
    {
        CounterNumberVariable counter;
        counter= ImmutableCounterNumberVariable.builder().description(descritpion)
                .increment(incremet)
                .name(name)
                .maxValue(max)
                .startValue(startvalue)
                .scope(Scope)
                .noValuesLeftBehavior(NoValueLeft)
                .policy(changePolicy)
                .build();
        return counter;


    }

    public static FileVariable createFileVariable(String name, String description, String pathFileName, boolean firstLineIsColumnName, String colomnDelimiter, Variable.VariableScope scope, Variable.VariableNoValuesLeftBehavior noValueLeft, Variable.VariableOrder order, Variable.VariablePolicy changePolicy, int numOfFirstRowData)
    {
        FileVariable file;
        file=ImmutableFileVariable.builder().description(description)
                .name(name)
                .fileName(pathFileName)
                .firstLineIsColumnName(firstLineIsColumnName)
                .policy(changePolicy)
                .columnsDelimiter(colomnDelimiter)
                .scope(scope)
                .numOfFirstRowData(numOfFirstRowData)
                .noValuesLeftBehavior(noValueLeft)
                .order(order)
                .build();
        return file;


    }

    public static FileVariable createFileVariable(String name, String description, String[][] data, boolean firstLineIsColumnName, String colomnDelimiter, Variable.VariableScope scope, Variable.VariableNoValuesLeftBehavior noValueLeft, Variable.VariableOrder order, Variable.VariablePolicy changePolicy, int numOfFirstRowData)
    {

        FileVariable file;
        file=ImmutableFileVariable.builder().description(description)
                .name(name)
                .data(data)
                .firstLineIsColumnName(firstLineIsColumnName)
                .policy(changePolicy)
                .columnsDelimiter(colomnDelimiter)
                .scope(scope)
                .numOfFirstRowData(numOfFirstRowData)
                .noValuesLeftBehavior(noValueLeft)
                .order(order)
                .build();
        return file;



    }
}
