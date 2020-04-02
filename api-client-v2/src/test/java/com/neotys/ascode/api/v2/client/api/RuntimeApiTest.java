/*
 * NeoLoad API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 2.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.neotys.ascode.api.v2.client.api;

import com.neotys.ascode.api.v2.client.ApiClient;
import com.neotys.ascode.api.v2.client.ApiException;

import java.io.File;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.neotys.ascode.api.v2.client.model.ProjectDefinition;
import com.neotys.ascode.api.v2.client.model.RunTestDefinition;
import com.neotys.ascode.api.v2.client.model.TestSettingsCreate;
import com.neotys.ascode.api.v2.client.model.TestSettingsCreated;
import com.neotys.ascode.api.v2.client.model.TestSettingsDefinition;
import com.neotys.ascode.api.v2.client.model.TestSettingsDefinitionList;
import com.neotys.ascode.api.v2.client.model.TestSettingsUpdate;
import org.junit.Test;
import org.junit.Ignore;

/**
 * API tests for RuntimeApi
 */
@Ignore
public class RuntimeApiTest {

    private final RuntimeApi api = new RuntimeApi();

    /**
     * Delete a test
     *
     * Delete the test with the specified id
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTestTest() throws ApiException {
        String testId = null;
        String deleteResults = null;
        TestSettingsDefinition response = api.deleteTest(testId, deleteResults);

        // TODO: test validations
    }

    @Test
    public void testUploadCreationofProject()
    {
       /* ApiClient client=new ApiClient();
        client.setApiKey("YOUAPAI");
        client.setBasePath("https://neoload-api.saas.neotys.com/v2");
        RuntimeApi runtimeApi=new RuntimeApi(client);
        TestSettingsCreate testSettingsCreate=new TestSettingsCreate();
        testSettingsCreate.setControllerZoneId("defaultzone");
        testSettingsCreate.setDescription("Super test v42222");
        testSettingsCreate.setName("Awesome testsdededed");
        testSettingsCreate.setTestResultNamingPattern("Test ApiV2  #${runID}");
        HashMap<String,Integer> lgzones= new HashMap<String,Integer>();
        lgzones.put("defaultzone",1);
        testSettingsCreate.setLgZoneIds(lgzones);
        try {
            TestSettingsCreated testSettingsCreated=runtimeApi.postCreateTest(testSettingsCreate);
            client.setBasePath("https://neoload-files.saas.neotys.com/v2");
            runtimeApi=new RuntimeApi(client);
            File project=new File("/home/hrexed/neoload_projects/v7.2/Sample_Project.zip");
            ProjectDefinition projectDefinition =  runtimeApi.postUploadProject(project,testSettingsCreated.getId());
            System.out.println(projectDefinition.getScenarios().stream().map(scenarioDefinition ->{ return scenarioDefinition.getScenarioName();}).collect(Collectors.joining(",")));
        } catch (ApiException e) {
            e.printStackTrace();
        }            */
    }
    /**
     * Get a test list
     *
     * Get the test list
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTestListTest() throws ApiException {
        TestSettingsDefinitionList response = api.getTestList();

        // TODO: test validations
    }
    /**
     * Get a test
     *
     * Get the test
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTestSettingsTest() throws ApiException {
        String testId = null;
        TestSettingsDefinition response = api.getTestSettings(testId);

        // TODO: test validations
    }
    /**
     * Starts a test
     *
     * Starts a test of the Account according to the method parameters.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTestsRunTest() throws ApiException {
        String testId = null;
        String testResultName = null;
        String testResultDescription = null;
        String asCode = null;
        String reservationId = null;
        Long reservationDuration = null;
        Integer reservationWebVUs = null;
        Integer reservationSAPVUs = null;
        Integer reservationCitrixVUs = null;
        Boolean publishTestResult = null;
        RunTestDefinition response = api.getTestsRun(testId, testResultName, testResultDescription, asCode, reservationId, reservationDuration, reservationWebVUs, reservationSAPVUs, reservationCitrixVUs, publishTestResult);

        // TODO: test validations
    }
    /**
     * Partially update a test
     *
     * Update only the specified fields of the test
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void patchTestTest() throws ApiException {
        String testId = null;
        TestSettingsUpdate body = null;
        TestSettingsDefinition response = api.patchTest(testId, body);

        // TODO: test validations
    }
    /**
     * Create a new test
     *
     * Create a new test with the specified name
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postCreateTestTest() throws ApiException {
        TestSettingsCreate body = null;
        TestSettingsCreated response = api.postCreateTest(body);

        // TODO: test validations
    }
    /**
     * Uploads a NeoLoad project zip file or a standalone as code file
     *
     * Uploads a NeoLoad project of the account corresponding to the parameters. The maximum size file is 250 MB
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postUploadProjectTest() throws ApiException {
        File file = null;
        String testId = null;
        ProjectDefinition response = api.postUploadProject(file, testId);

        // TODO: test validations
    }
    /**
     * Fully update a test
     *
     * Update all fields of the test
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void putTestTest() throws ApiException {
        TestSettingsUpdate body = null;
        String testId = null;
        TestSettingsDefinition response = api.putTest(body, testId);

        // TODO: test validations
    }
}
