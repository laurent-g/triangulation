Java version :
+ Java 1.6

Maven dependencies :
+ apache commons-lang3 3.1
+ log4j 1.2.17
+ junit 4.11
+ hamcrest-core 1.3

Application console
+ eu.georget.triangulation.AppConsole

Tests :
+ Test suite : eu.georget.triangulation.TestSuite
+ Core part : eu.georget.triangulation.core.TestCore
+ IO part : eu.georget.triangulation.core.TestIO

Maven :
+ mvn clean install
+ mvn javadoc:javadoc
	
	

Import dans Eclipse 3.8 :

+ File / Import
+ Select an import source : Git / Projects from Git
+ Select a location of Git Repositories : URI
+ Source Git Repository
+ URI : https://github.com/laurent-g/triangulation.git
+ Branch selection : master
+ Local destination : a directory
+ Select a wizard.. : Import existing projects

	