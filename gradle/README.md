# Gradle

Gradle is a famous building tool for building multiple programming languages, for example, Java, Scala, C++, Android, and even Swift, etc. 

## Creating the application via the initialization tool

You can start a Gradle-based project using `gradle init`. The following are the processes of initializing the gradle-based project.

```text
Starting a Gradle Daemon (subsequent builds will be faster)

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Split functionality across multiple subprojects?:
  1: no - only one application project
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 2

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Project name (default: gradle): 
Source package (default: gradle): 

> Task :init
Get more help with your project: https://docs.gradle.org/7.0.2/samples/sample_building_java_applications_multi_project.html

BUILD SUCCESSFUL in 57s
2 actionable tasks: 2 executed
```

The `init` task generates the new project with the default structure. The project contains a simple example of demonstrating how to run the Java source code. The following shows the structure of demonstrating the `gradle` building tool.

```text
+ gradle
  + .gradle
  + app                (1)
  + buildSrc
  + gradle             (2)
  + libs               (3)
  - .gitattributes
  - .gitignore
  - gradlew            (4-1)
  - gradlew.bat        (4-2)
  - settings.gradle    (5)
```

(1) The folder `app` contains the scripts implementing the program entry. The `build.gradle` defines the resources for building the app, including the dependencies.

(2) The folder `gradle` was generated for `wrapper` files. The wrapper facilities installation of gradle, and makes sure the gradle version is available for the platform.

(3) The folder `libs` contains the scripts implementing the libraries using in the `app`.

(4) They are two `gradle wrapper` start scripts for different OS platforms.

(5) The setting files defining the build name and the subdirectory.

After writing some scripts, you now can build the Java project. You can use the `gradle` or `gradlew` to build the project.

```sh
# gradlew
./gradlew run       # run the application (1)
./gradlew build     # build the application (2)
./gradlew clean     # clean the building (3)
./gradlew projects  # list the projects for building
```

(1) If you first time to run the `gradlew`, there may be a delay while the specific version of `gradle` is downloaded to the `~/.gradle/wrapper/dists` folder.

(2) While building the application, the gradlew tool bundles the application with all its dependencies. The archive will also contain a script to start the application with a single command. Gradle will have produced the archive in two formats for you: `app/build/distributions/app.tar` and `app/build/distributions/app.zip`. You can further untar or unzip the package to get the executable binary.

(3) After running the clean command, the build folder would be deleted.

The similar operations to `gradlew` by using `gradle` are the below.

```sh
# gradle
gradle run
gradle build
gradle clean
gradle projects
```

## Creating the JAR

Before creating the JAR, you have to understand the `build.gradle` first. The following is the simple example.

```gradle
// buildscript (1)
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath 'org.apache.commons:commons-csv:1.0'
    }
}


// plugins (2)
plugins {
  id 'gradle.java-application-conventions' 
  id 'java-library'
}
apply plugin: 'java'

// configurations (3)
configurations {
  // declare a "configuration" named "someConfiguration"
  someConfiguration
}

configurations {
  // declare a configuration that is going to resolve the compile classpath of the application
  compileClasspath.extendsFrom(compileConfiguration)

  // declare a configuration that is going to resolve the runtime classpath of the application
  runtimeClasspath.extendsFrom(runtimeConfiguration)
}

// dependencies (4)
dependencies {
  // add a project dependency to the "someConfiguration" configuration
  someConfiguration project(":lib")
}

dependencies {
  // configurations are not necessary to dependencies
  implementation project(':libs')
  compile project(':libs')
}

// applications (5)
application {
    // Define the main class for the application.
    mainClass = 'gradle.app.App'
}

// java archive (6)
jar {
  manifest {
    attributes 'Main-Class': 'gradle.app.App'
  }
  from 'path/to/other/project'
}
```

(1) The `buildscript` declares the external resources for `gradle`. The resources are like dependencies, third-party plugins, and maven repositories. For example, if you are going to parse a CSV file, it is not necessary for you to rewrite a parser. Instead that, you can use the parser from a pre-implemented repository. The example is like the above buildscript.

(2) `Gradle` is a tool or more like the platform actually. All of the useful features, like the ability to compile Java code, are added by `plugins`. `Plugins` add new tasks (e.g. `JavaCompile`), domain objects (e.g. `SourceSet`), conventions (e.g Java source code located ar `src/main/java`) as well as extending core objects and objects from other plugins. In other words, applying a plugin to a project allows the plugin to extend the project's capabilities. There ate two types of `plugins`, they are `binary plugins` and `script plugins`.

(3) A `configuration` represents a group of artifacts and their dependencies. Configuration is an instance of a `FileCollection` that contains all dependencies.

(4) Software projects rarely work in isolation. The `dependency` is a collection for declaringm resolving and using dependencies required by the project in an automated fashion.

(5) The `application` section, comparing to the `library`, declares the building target an application with its `mainClass` entry.

(6) The `jar` section declares how to build a java archive (`jar`) with its `manifest` and other resources if needed. You have to keep it in mind that `gradle` only bundles the resources in the same project. In other words, they are co-located. If you are going to include other resources from other projects, it is necessary for you to add the included paths with the `from` command.

In `gradle`, the directory with a `build.gradle` file is considered a project. Each `build.gradle` defines how to build the resources of the project. The `setting.gradle` defines how to build multiple targets, like projects or libraries. 

```gradle
// setting.gradle
rootProject.name = 'gradle'
include('libs', 'app')
```

In general, you can create an application by `gradle init`. However, the command created several projects, or more like subprojects under a big project.

```text
+ gradle             <- big project
  + app              <- sub-project, dependency on libs
    - build.gradle
  + libs             <- sub-project
    - build.gradle
  - build.gradle     <- a created file
  - settings.gradle
```

It brings us with difficulties sharing resources across these projects, especially for creating a JAR. From the above, we know that the resources for a JAR must be co-located. Compare to other approaches of redefining each build.gradle, it is recommended to create a `build.gradle` file on the scope of big project. It was created for gathering the resources from different subprojects. After building, the build folder was also generated on the scope of big project. You can easily access the `build/libs/gradle.jar` and run it with `java -jar`.

```sh
cd ./gradle
./gradlew clean 
./gradlew build

# see the bundled resources inside the jar
jar tvf ./build/libs/gradle.jar

# run the jar
java -jar ./build/libs/gradle.jar
```