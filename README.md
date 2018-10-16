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

Building a test case requires in minimum 3 classes :
  1. `BaseNeoLoadUserPath` that defines a NeoLoad [UserPath](https://www.neotys.com/documents/doc/neoload/latest/en/html/#787.htm) ( equivalent of testing script)
  2. `BaseNeoLoadDesign` defines the lists of :
     - NeoLoad [Variables](https://www.neotys.com/documents/doc/neoload/latest/en/html/#1057.htm)
     - NeoLoad [Servers](https://www.neotys.com/documents/doc/neoload/latest/en/html/#782.htm)
     - NeoLoad [UserPaths](https://www.neotys.com/documents/doc/neoload/latest/en/html/#787.htm)
  3. `NeoLoadTest` that define the test to run
    
 ### Building a UserPath
 
 Define your UserPath class that extends `BaseNeoLoadDesign`
 
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

`postFormBuilder` takes the following parameters
- Server
- Url of the request on the server
- List of URL parameters
- List of Post parameters
- List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
- List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)
 
 `getBuilder` takes the following parameters
 - Server
 - Url of the request on the server
 - List of URL parameters
 - List of [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm)
 - List of [Assertions](https://www.neotys.com/documents/doc/neoload/latest/en/html/#957.htm)
  
  
  ### Defining the Design of the project
  
 
