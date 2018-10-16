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
    
  
