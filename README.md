# selenium-test-framework

Selenium Test Framework

Why this framework?

TestNG is widely used testing framework with Selenium WebDriver. TestNG has rich features for testing, such as parallel test execution, test listener, grouping test and so on. It can be said that TestNG is one of the most popular UI test framework for selenium UI test.

How to build the framework?

Install Apache Maven
1.Ensure JAVA_HOME environment variable is set and points to your JDK installation

2.Download maven, extract distribution archive in any directory
$ unzip apache-maven-3.3.9-bin.zip

or
$ tar xzvf apache-maven-3.3.9-bin.tar.gz

3.Add the bin directory of the created directory apache-maven-3.3.9 to the PATH environment variable

4.Confirm with mvn -v in a new shell. The result should look similar to

Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-11T00:41:47+08:00)
Maven home: /opt/apache-maven-3.3.9
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.11.6", arch: "x86_64", family: "mac"

Add selenium-server-standalone-2.52.0.jar to maven local repository
$ mvn install:install-file -Dfile=./lib/selenium-server-standalone-2.52.0.jar -DgroupId=org.seleniumhq.selenium -DartifactId=selenium-server-standalone -Dversion=2.52.0 -Dpackaging=jar

Compile this framework source and install it to maven local repository
$ mvn clean install
