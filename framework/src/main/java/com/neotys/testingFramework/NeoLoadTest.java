package com.neotys.testingFramework;

import com.neotys.converters.datamodel.ImmutableProject;
import com.neotys.converters.datamodel.Project;
import com.neotys.converters.datamodel.repository.*;
import com.neotys.converters.datamodel.scenario.*;
import com.neotys.converters.writers.neoload.NeoLoadWriter;
import com.neotys.testingFramework.Utils.NeoloadConfig;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hrexed on 03/07/18.
 */
public abstract class NeoLoadTest {
     BaseNeoLoadTest neoLoadVirtualUsers;
     List<Population> list_Of_Population;
     List<Scenario> list_Of_Sceanario;
     String outputFolder;
    private String projectName;
    enum TypeScenario {
        CONSTANT, RAMPUP, PEAK;
    }

    @Before
    public void init()
    {

        outputFolder = NeoloadConfig.neoLoadTargetDirectory;
        list_Of_Population =new ArrayList<Population>();
        list_Of_Sceanario =new ArrayList<Scenario>();
        defineNeoLoadDesign();
        generateDefaultPopulation();
    }
    public void defineProjectName(String name)
    {
        this.projectName =name;
    }

    public  abstract  void defineNeoLoadDesign();

    public void setBaseNeoLoadTest(BaseNeoLoadTest test)
    {
        neoLoadVirtualUsers =test;

    }

    public void generateDefaultPopulation()
    {
        for(Map.Entry<String,BaseNeoLoadUserPath> vu: neoLoadVirtualUsers.list_Of_Vu.entrySet())
        {
            Population tmp= ImmutablePopulation.builder().description(vu.getValue().getName())
                    .addSplits(ImmutablePopulationSplit.builder()
                            .userPath(vu.getValue().getName())
                            .percentage(100)
                            .build())
                    .build();
            addPopulation(tmp);
        }
    }
    public BaseNeoLoadUserPath getUserPathFromName(String name)
    {
        for(Map.Entry<String,BaseNeoLoadUserPath> vu: neoLoadVirtualUsers.list_Of_Vu.entrySet())
        {
            if(name.equalsIgnoreCase(vu.getValue().getName()))
                return vu.getValue();
        }
        return null;
    }
    public Population getPopulationfromName(String name)
    {
        for(Population p: list_Of_Population)
        {
            if(name.equalsIgnoreCase(p.getName()))
                return p;
        }
        return null;
    }

    public abstract void createComplexPopulation();

    public void addPopulation(Population p)
    {
        list_Of_Population.add(p);
    }




    public void addScenario(Scenario s)
    {
        list_Of_Sceanario.add(s);
    }
    public abstract void createComplexScenario();

    public void createSimpleConstantLoadScenario(String ScenarioName, String UserpathName, int duration, int MaxUser, int Rampuptime)
    {
        Population population= getPopulationfromName(UserpathName);
        if(population!=null)
        {
            Scenario scenario = ImmutableScenario.builder().name(ScenarioName)
                    .putPopulations(population.getName(), ImmutableScenarioPolicies.builder()
                            .durationPolicy(ImmutableTimeDurationPolicy.builder().duration(duration).build())
                            .loadPolicy(ImmutableConstantLoadPolicy.builder().load(MaxUser).build())
                            .build())
                    .build();

            list_Of_Sceanario.add(scenario);
        }


    }

    @Test
    public abstract void execute();

    @After
    public void GenerateNlProject()
    {
        Project project;
        ImmutableProject.Builder tmpProject=ImmutableProject.builder();
        tmpProject.name(this.projectName);
        String output;
        JSONObject json_project = new JSONObject();

        JSONArray json_scenario_list = new JSONArray();



        //--Add all the user Path in the Project
        for(Map.Entry<String, BaseNeoLoadUserPath> key : neoLoadVirtualUsers.list_Of_Vu.entrySet())
        {
            tmpProject.addUserPaths(key.getValue().virtualUser);
        }

        //---Add all the Variables IN the project--------
        for(Map.Entry<String,Variable> var: neoLoadVirtualUsers.List_Of_Variable.entrySet())
        {
            tmpProject.addVariables(var.getValue());
        }

        //--add all the servers in the project---------
        for(Map.Entry<String,Server> server: neoLoadVirtualUsers.List_Of_Server.entrySet())
        {
            tmpProject.addServers(server.getValue());
        }

        //----Add all the population in the project-----
        for(Population population: list_Of_Population)
        {
            tmpProject.addPopulations(population);
        }

        //---add all the scenarios-------
        for(Scenario scenario: list_Of_Sceanario)
        {
            tmpProject.addScenarios(scenario);
            json_scenario_list.add(scenario.getName());
        }

        project=tmpProject.build();

        output= getNeoLoadFolder();
        if(output!=null)
        {
          //-----creation of the project
            NeoLoadWriter writer = new NeoLoadWriter(project, output, null);
            writer.write();

          //------

            //---save the descirption file required for the maven plugin
            json_project.put("Project",writer.getOutputFolder()+"/"+ projectName +".nlp");
            json_project.put("Scenarios",json_scenario_list);
            try {
                writeJsonToFile(json_project,output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //-------------------------------
        }
    }

    private void writeJsonToFile(JSONObject obj, String outputFolder) throws IOException
    {

            String fileName=NeoloadConfig.retrieveJsonFileName();

            JSONObject previousJsonObj;
            JSONArray projectList;
            JSONParser parser;
            try {

                parser=new JSONParser();
                previousJsonObj = (JSONObject) parser.parse(new FileReader(outputFolder+"/"+fileName));
                projectList = (JSONArray) previousJsonObj.get("NeoLoadProjectList");
                projectList.add(obj);
                saveJsontoFile(previousJsonObj,outputFolder+"/"+fileName);

            }
            catch(FileNotFoundException e)
            {
                previousJsonObj=new JSONObject();
                projectList=new JSONArray();

                projectList.add(obj);
                previousJsonObj.put("NeoLoadProjectList",obj);
                saveJsontoFile(previousJsonObj,outputFolder+"/"+fileName);

            } catch (ParseException e) {
                e.printStackTrace();
            }


    }
    private void saveJsontoFile(JSONObject obj,String Path) throws IOException {
        FileWriter file = new FileWriter(Path);
        file.write(obj.toJSONString());
        file.flush();
        file.close();

    }
    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public String getNeoLoadFolder()
    {
        File theDir=new File (outputFolder);
        if(theDir.exists())
        {
            return outputFolder;
        }
        else
        {
            //----create output folder------
            try {
                theDir.mkdir();
                return outputFolder;
            }
            catch(SecurityException se){
                System.out.println("Impossible to create the NeoLoad Folder");
                return null;
            }

        }
    }
    public void CreateSimpleRampupLoadScenario(String ScenarioName,String UserpathName,int duration,int initialNbVU ,int IncrementNbVu,int IncrementTime)
    {
        Population population= getPopulationfromName(UserpathName);
        if(population!=null)
        {
            Scenario scenario = ImmutableScenario.builder().name(ScenarioName)
                    .putPopulations(population.getName(), ImmutableScenarioPolicies.builder()
                            .durationPolicy(ImmutableTimeDurationPolicy.builder().duration(duration).build())
                            .loadPolicy(ImmutableRampupLoadPolicy.builder().initialLoad(initialNbVU)
                                    .incrementLoad(IncrementNbVu)
                                    .incrementTime(IncrementTime)
                                    .build())
                            .build())
                    .build();

            list_Of_Sceanario.add(scenario);
        }

    }

}