![Image of NeoLoad](https://d28h099uturm62.cloudfront.net/wp-content/uploads/2016/12/Neotys-Corporate-Primary.png)

# NeoLoadAsCode

NeoLoadasCode has 3 repository:
  1. The Junit library 
  2. The maven plugin to execute tests in NeoLoad Gui and NeoLoad Web
  3. An example of using the neoload testing framework

See the CHANGELOG for change information.

NeoLoad maven plugin requires Maven >= 3.5.0 and is compatible with NeoLoad 6.6.0 and above

This plugin requires a JDK between 8 and 10. 

## Junit Library
### Add the Library in your project
Add the NeoLoad Junit library in the dependencies of your pom.xml file
```
        <dependency>
            <groupId>com.neotys.ascode</groupId>
            <artifactId>neoLoad-framework</artifactId>
            <version>${neoload-maven-plugin.version}</version>
            <scope>test</scope>
        </dependency>      
```

Building a test case requires in minimum 3 classes :
  1. `BaseNeoLoadUserPath` that defines a NeoLoad [UserPath](https://www.neotys.com/documents/doc/neoload/latest/en/html/#787.htm) ( equivalent of testing script)
  2. `BaseNeoLoadDesign` defines the lists of :
     - NeoLoad [Variables](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1057.htm)
     - NeoLoad [Servers](https://www.neotys.com/documents/doc/neoload/latest/en/html/#782.htm)
     - NeoLoad [UserPaths](https://www.neotys.com/documents/doc/neoload/latest/en/html/#787.htm)
  3. `NeoLoadTest` that define the test to run
    
 ### Building a UserPath
 
 Define your UserPath class that extends `BaseNeoLoadUserPath`
 
 ```java
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
 
 		//----define the list of parameters for the POST request
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
 		//---creates a POST request with a list of post parameters
 		final Request postRequest = postFormBuilder(server, "/api", emptyList(), postParameters,emptyList(),emptyList()).build();
 		
 		//---define a GET request
 		final Request getRequest = getBuilder(server, "/main", emptyList(),emptyList(),emptyList()).build();
 
 		final Delay delay1 = delay(800);
 		final Delay delay2 = thinkTime(1200);
 		
 		//---define the script worflow
 		final Container actionsContainer = actionsContainerBuilder()
 				.addChilds(container("Api", getRequest, delay1, postRequest, delay2))
 				.build();
 
 		//----create the UserPath named "CreateReportAPI"
 		return userPathBuilder("CreateReportAPI")
 				.actionsContainer(actionsContainer)
 				.build();
 	}
 
 
 }
```
Several Helpers are available to create :
- POST requests
    - `postFormBuilder` or `postFormBuilderWithHeaders` to build a POST request having POST parameters
        - Server
        - Url of the request on the server
        - List of URL parameters
        - List of Post parameters
        - List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
        - List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)
    - `postTextBuilder` or `postTextBuilderWithHeaders` to build a POST request having a payload
       - Server
       - Url of the request on the server
       - List of URL parameters
       - Payload in a String format
       - List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
       - List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)
   
 
- GET requests
    - `getBuilder` takes the following parameters
         - Server
         - Url of the request on the server
         - List of URL parameters
         - List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
         - List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)
    - `getBuilderWithHeaders` to generate a GET request having http headers :
         - Server
         - List of headers
         - Url of the request on the server
         - List of URL parameters
         - List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
         - List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)

 
 
### Defining the Design of the project
  
 
 Define your Design class that extends `BaseNeoLoadDesign`
 
 BaseNeoLoadDesign has 3 methods to be define :
 - `createNeoLoadUserPaths` define all the BaseNeoLoadUserPath required for the project
 - `createVariables` define the list of NeoLoad Variables
 - `createServers` define the list of Servers of the project

Several Helpers are available to :
- Create variables with of several types:
    - [File](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1058.htm) : `createFileVariable`
    - Counter : `createCounterVariable` 
    - Random Number : `createRandomNumberVariable`
    - Constant variable : `createConstantVariable`
- Create servers :
    - `createServer`

Example of a Design :
```java
public class ApiTestDesign extends BaseNeoLoadDesign {

	ApiTestDesign() {
		super();
	}

	@Override
	public void createNeoLoadUserPaths() {
		this.addVirtualUser(new ApiUserPath(this));
	}

	@Override
	public void createVariables() {
		final ConstantVariable server = createConstantVariable("sampledemo-host", "sampledemo.neotys.com");
		final ConstantVariable port = createConstantVariable("sampledemo-port", "80");
		final String pathFileName = Paths.get("src/test/resources/list_capital.csv").toAbsolutePath().toString();
		final FileVariable location = createFileVariable("location", "Data Set for the location", pathFileName, true, ";", Variable.VariableScope.GLOBAL, Variable.VariableNoValuesLeftBehavior.CYCLE, Variable.VariableOrder.RANDOM, Variable.VariablePolicy.EACH_ITERATION, 1);

		this.addVariables(server, port, location);
	}

	@Override
	public void createServers() {
		//use varname directly instead of getting var to use it
		final Variable server = getVariableByName("sampledemo-host");
		final Variable port = getVariableByName("sampledemo-port");
		this.addServer(createServer("sampledemo", server, port));
	}
}
```
### Defining the Test execution

Define your tests that extends  `NeoLoadTest`

`NeoLoadTest` will automatically define one [NeoLoad Population](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1049.htm) per UserPath.

It would be required to define :
- The project name with the help of the method `projectName`
- The NeoLaodDesign to use in the project with `design`
- The method `execute` is defined as `@Test`. This methods define the test to run.

2 optionals methods could be used to define complex :
- NeoLoad population : `createComplexPopulation`
- [NeoLoad Scenario](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1337.htm) : `createComplexScenario`

Several helpers are available to create test with :
- [constant load](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1340.htm) : `createSimpleConstantLoadScenario`
- [rampup load](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1340.htm) :`createSimpleRampupLoadScenario`

Example :
```java
public class ApiLoadTest extends NeoLoadTest {

	@Override
	protected BaseNeoLoadDesign design() {
		return new ApiTestDesign();
	}

	@Override
	protected String projectName() {
		return "ApiLoad";
	}

	@Override
	public void createComplexPopulation() {

	}
	@Override
	public void createComplexScenario() {

	}

	@Override
	public void execute() {
		createSimpleRampupLoadScenario("API test", "CreateReportAPI", 90, 1, 1, Optional.empty(),5);
	}
}

```

## Maven Plugin
The NeoLoad maven plugin allow you to run tests define from the NeoloadasCode Junit Library.
The plugin has too goals :
- `neoload`  will run tests on a local NeoLoad Gui
- `neoloadweb` will run the test from the NeoLoad Web Platform

### Add the Plugin to your project
Add the plugin to the build section of your pom's project :
```
<plugin>
    <groupId>com.neotys.neoload.mojo</groupId>
    <artifactId>neoload-maven-plugin</artifactId>
    <version>${neoload-maven-plugin.version}</version>
    <configuration>
        <neoLoadInstallationDirectory>/home/hrexed/Neoload6.6/</neoLoadInstallationDirectory>
        <neoLoadWebUrl>https://neoload.saas.neotys.com/</neoLoadWebUrl>
        <neoLoadWebAPIUrl>https://neoload-rest.saas.neotys.com/v1</neoLoadWebAPIUrl>
        <neoLoadWebUploadAPIUrl>https://neoload-files.saas.neotys.com/v1</neoLoadWebUploadAPIUrl>
        <neoloadWebControllerID>BdPRF</neoloadWebControllerID>
        <neoloadWeblgZonneID>BdPRF:1</neoloadWeblgZonneID>
        <neoLoadWebAPIKey>15304f743f34ca33c458927a40945b7424a2066b95563774</neoLoadWebAPIKey>
    </configuration>
</plugin>
```
### NeoLoad Gui Configuration
- NeoLoad Gui installation folder needs to be define on the parameter `<neoLoadInstallationDirectory>`
### NeoLoad Web Configuration
- NeoLoad Web Platform url `<neoLoadWebUrl>`. If using [NeoLoad SaaS](https://neoload.saas.neotys.com/) platform the default value is : https://neoload.saas.neotys.com/
- NeoLoad Web API url `<neoLoadWebUploadAPIUrl>`. If using NeoLoad Saas , the default value is : https://neoload-rest.saas.neotys.com/v1
- NeoLoad Web Upload API url `<neoLoadWebUploadAPIUrl>`. If using NeoLoad Saas, the default value is : https://neoload-files.saas.neotys.com/v1
- NeoLoad Web API key `<neoLoadWebAPIKey>`. To generate a NeoLoad API key follow the [online documentation](https://www.neotys.com/documents/doc/nlweb/latest/en/html/#24621.htm)
- Controller zoneId `<neoloadWebControllerID>`. This settings will define which controller zone will be use your the test. Here is a link to the [online documentation](https://www.neotys.com/documents/doc/nlweb/latest/en/html/#27521.htm#o27522)
- LoadGenerator zoneID `<neoloadWeblgZonneID>`. This parameter will define which Zone would be used for the test and the number of LoadGenerator required for the test. The value of this settings is zoneId:Number of LG. 
### Licensing configuration
The licensing configuration is optional and required if using NeoLoad Enterprise Licenses.
- Neotys Team Server Url `<neotysTeamServerURL>`
- Neotys Team Server Login `<neotysTeamServerLogin>`
- Neotys Team Server encrypted password `<neotysTeamServerEncryptedPassword>`. To encrypt the password it's required to use [NeoLoad's password scramber](https://www.neotys.com/documents/doc/neoload/latest/en/html/#6418.htm)
- LicenseId to use for this test `<neotysTeamServerLicenseID>`. The licenseID can be found in the [Neotys Team Server](https://www.neotys.com/documents/doc/nts/latest/en/html/#8728.htm)
- Number of maximum Users required for the test `<neotysTeamMaxVuRequired>`
- Maximum duration of the test `<neotysTeamMaxTestDuration>`
### Run a Test
#### NeoLoad GUI
`mvn neoload:neoload`
#### NeoLoad Web
`mvn neoload:neoloadweb`