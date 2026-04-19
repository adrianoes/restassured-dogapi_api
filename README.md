
# restassured-dogapi_api

Automated API testing project for the public [Dog API](https://dog.ceo/dog-api/) using RestAssured, Cucumber, and JUnit. This repository provides concise examples and best practices for validating dog breeds and images endpoints, using real data from the Stanford Dogs Dataset as a reference.

# Pre-requirements:

| Requirement / Dependency                | Version       | Scope/Note       |
|:----------------------------------------|:--------------|:-----------------|
| Visual Studio Code                      | 1.115.0       |                  |
| JDK                                     | 17            |                  |
| Maven                                   | 3.9.12        |                  |
| Cucumber JVM                            | 7.13.0        |                  |
| Cucumber JVM: JUnit 4                   | 7.13.0        |                  |
| JUnit Jupiter                           | 5.9.2         |                  |
| JUnit Vintage Engine                    | 5.9.2         |                  |
| JSON In Java                            | 20251224      |                  |
| RestAssured                             | 5.4.0         |                  |
| Jackson Databind                        | 2.17.0        |                  |
| Maven Clean Plugin                      | 3.3.2         |                  |
| Maven Compiler Plugin                   | 3.13.0        |                  |
| Maven Surefire Plugin                   | 3.0.0         |                  |
| Maven Surefire Report                   | 3.0.0         |                  |
| Maven Cucumber Reporting                | 5.7.6         |                  |

# Installation:

- See [Visual Studio Code page](https://code.visualstudio.com/) and install the latest VSC stable version. Keep all the prefereced options as they are until you reach the possibility to check the checkboxes below: 
  - :white_check_mark: Add "Open with code" action to Windows Explorer file context menu. 
  - :white_check_mark: Add "Open with code" action to Windows Explorer directory context menu.
Check then both to add both options in context menu.
- See [Java SE 17 Archive Downloads](https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html), download the proper version for your OS and install it by keeping the preferenced options.
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called JAVA_HOME containing the path that leads to where the JDK software is located (e.g. C:\Program Files\Java\jdk-17).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %JAVA_HOME%\bin entry.
- See [Maven download page](https://maven.apache.org/download.cgi), download the xxxBinary zip archive and unzip it in a place of your preference (e.g. C:\Program Files\Maven\apache-maven-3.9.12).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, :point_right: **New** in System Variables frame and create a variable called MAVEN_HOME containing the path that leads to where the apache-maven software is located (e.g. C:\Program Files\Maven\apache-maven-3.9.12).
    - Right click :point_right: **My Computer** and select :point_right: **Properties**. On the :point_right: **Advanced** tab, select :point_right: **Environment Variables**, and then edit Path system variable with the new %MAVEN_HOME%\bin entry.
- Add the dependencies and plugins codes below in the `pom.xml` file right below the `properties` session, then hit :point_right: **Sync maven changes**. The source URL for each dependency/plugin is kept in comments so it is easy to update versions later. Your dependency/plugin in the `pom.xml` file should be like this:

  ```
    <dependencies>
            <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.17.0 -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.17.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java/7.13.0 -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.13.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit/7.13.0 -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.13.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/5.9.2 -->
        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.9.2 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.json/json/20251224 -->
        <!-- JSON -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20251224</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.4.0 -->
        <!-- RestAssured for API testing -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.4.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.junit.vintage/junit-vintage-engine/5.9.2 -->
        <!-- JUnit Vintage Engine for JUnit 4 runners (Cucumber) -->
        <dependency>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-clean-plugin/3.3.2 -->
            <!-- Clean reports before test -->
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-clean-plugin</artifactId>
                            <version>3.3.2</version>
                            <configuration>
                                <filesets>
                                    <fileset>
                                        <directory>${project.build.directory}/surefire-reports</directory>
                                    </fileset>
                                    <fileset>
                                        <directory>${project.build.directory}/surefire-report</directory>
                                    </fileset>
                                    <fileset>
                                        <directory>${project.build.directory}/cucumber-reports</directory>
                                    </fileset>
                                    <fileset>
                                        <directory>${project.build.directory}/test-results</directory>
                                    </fileset>
                                </filesets>
                            </configuration>
                        </plugin>
            <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.13.0 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.0.0 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <trimStackTrace>true</trimStackTrace>
                    <redirectTestOutputToFile>false</redirectTestOutputToFile>
                    <includes>
                        <include>**/*Tests.java</include>
                        <include>**/*Runner.java</include>
                    </includes>
                </configuration>
            </plugin>

            <!-- HTML report for JUnit/Tests -->
            <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-report-plugin/3.0.0 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-report-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <id>generate-consolidated-html-report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report-only</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/surefire-report</outputDirectory>
                            <outputName>index</outputName>
                            <showSuccess>true</showSuccess>
                            <alwaysGenerateSurefireReport>true</alwaysGenerateSurefireReport>
                            <reportsDirectories>
                                <reportsDirectory>${project.build.directory}/surefire-reports</reportsDirectory>
                            </reportsDirectories>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- https://mvnrepository.com/artifact/net.masterthought/maven-cucumber-reporting/5.7.6 -->
            <!-- Cucumber HTML Reports Plugin -->
            <plugin>
                <groupId>net.masterthought</groupId>
                <artifactId>maven-cucumber-reporting</artifactId>
                <version>5.7.6</version>
                <executions>
                    <execution>
                        <id>execution</id>
                        <phase>test</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <projectName>Dog API Tests</projectName>
                            <outputDirectory>${project.build.directory}/cucumber-reports</outputDirectory>
                            <inputDirectory>${project.build.directory}/cucumber-reports</inputDirectory>
                            <jsonFiles>
                                <param>**/*.json</param>
                            </jsonFiles>
                            <checkBuildResult>false</checkBuildResult>
                            <mergeFeaturesById>false</mergeFeaturesById>
                            <expandAllSteps>true</expandAllSteps>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
  ``` 

# Tests:

- `mvn test -Dtest=BreedsImageRandomRunner "-Dcucumber.filter.name=Single random image from all dogs collection"` to run a single scenario by name in a single file (Cucumber)
- `mvn test -Dtest=BreedsImageRandomRunner "-Dcucumber.filter.tags=@BREEDSIMAGERANDOM and @NEGATIVE"` to run negative tests in a single file (Cucumber)
- `mvn test -Dtest=BreedListAllRunner` to run a single suite (Cucumber)
- `mvn test "-Dcucumber.filter.tags=@NEGATIVE"` to run all negative tests in all three suites (Cucumber)
- `mvn test` to run all three suites at once (Cucumber)
- `mvn test -Dtest=tests.BreedsImageRandomTests#testSingleRandomImage` to run a single test method in a single file (JUnit only)
- `mvn test -Dtest=tests.BreedsImageRandomTests#testNegativeRandomImages51,testNegativeRandomImagesMinus1,testNegativeRandomImagesA,testNegativeRandomImages0,testNegativeRandomImagesAt` to run negative tests in a single file (JUnit only)
- `mvn test -Dtest=tests.BreedListAllTests` to run a single suite (JUnit only)
- `mvn test -Dtest="tests.BreedsImageRandomTests#testNegativeRandomImages51,testNegativeRandomImagesMinus1,testNegativeRandomImagesA,testNegativeRandomImages0,testNegativeRandomImagesAt,tests.BreedListAllTests#testNegativeBreedListAll,tests.BreedImagesTests#testNegativeBreedImages"` to run all negative tests in all three suites (JUnit only)
- `mvn test -Dtest="tests.BreedsImageRandomTests,tests.BreedListAllTests,tests.BreedImagesTests"` to run all three suites at once (JUnit only)

- Notes:
    - target/surefire-report/index.html - Find the consolidated execution report for runs without Cucumber.
    - target/cucumber-reports/cucumber-html-reports/overview-features.html - Find the consolidated execution report for runs with Cucumber.
    - target/test-results/ - Find individual test failure reports, screenshots and logs.
    - All test assertions use the data_sets/*.json files as the source of truth for expected breeds and images.

# Support:

- [Cucumber Java 7.33.0](https://mvnrepository.com/artifact/io.cucumber/cucumber-java/7.33.0)
- [Cucumber JUnit 7.33.0](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit/7.33.0)
- [Gherkin](https://plugins.jetbrains.com/plugin/9164-gherkin)
- [JUnit Jupiter 5.9.2](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/5.9.2)
- [JUnit Vintage Engine 5.9.2](https://mvnrepository.com/artifact/org.junit.vintage/junit-vintage-engine/5.9.2)
- [JSON In Java 20251224](https://mvnrepository.com/artifact/org.json/json/20251224)
- [Jackson Databind 2.17.0](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.17.0)
- [Maven Clean Plugin 3.3.2](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-clean-plugin/3.3.2)
- [Maven Compiler Plugin 3.13.0](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.13.0)
- [Maven Surefire Plugin 3.0.0](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.0.0)
- [Maven Surefire Report Plugin 3.0.0](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-report-plugin/3.0.0)
- [Maven Cucumber Reporting 5.7.6](https://mvnrepository.com/artifact/net.masterthought/maven-cucumber-reporting/5.7.6)
- [Tag Expressions](https://github.com/cucumber/tag-expressions)
- [Dog API Documentation](https://dog.ceo/dog-api/documentation/)
- [Stanford Dogs Dataset](http://vision.stanford.edu/aditya86/ImageNetDogs/)

# Tips:

- To check Maven version, run `mvn -version`.
- To compile the project without running tests, use `mvn clean compile`.

# Known bugs:

### 1. -1 random images from all dogs collection

**Endpoint Example:**
```http
GET /breeds/image/random/-1
```
**Response Obtained:**
```json
{
    "status": "success",
    "message": [/* array of images */]
}
```
**Obtained Result:** Status code 200, response contains images.
**Expected Result:** Status code 400, response should indicate error and not contain images.

### 2. 'a' random images from all dogs collection

**Endpoint Example:**
```http
GET /breeds/image/random/a
```
**Response Obtained:**
```json
{
    "status": "success",
    "message": [/* array of images */]
}
```
**Obtained Result:** Status code 200, response contains images.
**Expected Result:** Status code 400, response should indicate error and not contain images.

### 3. 0 random images from all dogs collection

**Endpoint Example:**
```http
GET /breeds/image/random/0
```
**Response Obtained:**
```json
{
    "status": "success",
    "message": [/* array of images */]
}
```
**Obtained Result:** Status code 200, response contains images.
**Expected Result:** Status code 400, response should indicate error and not contain images.

### 4. @ random images from all dogs collection

**Endpoint Example:**
```http
GET /breeds/image/random/@
```
**Response Obtained:**
```json
{
    "status": "success",
    "message": [/* array of images */]
}
```
**Obtained Result:** Status code 200, response contains images.
**Expected Result:** Status code 400, response should indicate error and not contain images.

### 5. a multiple random image from a breed

**Endpoint Example:**
```http
GET /breed/clumber/images/random/a
```
**Response Obtained:**
```json
{
    "status": "success",
    "message": [/* array of images */]
}
```

