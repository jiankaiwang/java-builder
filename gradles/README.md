# Simple Version of Using Gradle

For many cases as the following, you maybe have a Java-based project without `gradle init` at the beginning. 

* Migrated from other projects.
* The project started from a small scaled scope.
* The project runs for the prove of concept.
* ...

However, you are asked for building the project by using `gradle`. How do you start? In this example, we are going to demonstrate the basic building for such condition.

The following is the structure of the porject.

```text
+ gradles
  + app
    + src/main/java/gradles/app
      - Main.java
    - build.gradle
  + libs
    + src/main/java/gradles/libs
      - Cal.java
    - build.gradle
  - settings.gradle
```

**Notice that the paths for the structure for each `app` and `libs` are constraint to `<package>/src/main/java/<root-package-name>/<package>/<class.java>`. If the path doesn't meet the requirement, the classes wouldn't be compiled.**

In the `setting.gradle`, you simply setup the `rootProject.name` and `include`.

```gradle
rootProject.name = 'gradles'
include('libs', 'app')
```

For each library included in the `app`, the main entry, you can simply define the `plugins` in its `build.gradle`.

```gradle
plugins {
    id 'java'
}
```

For the main entry, there are three main compnents required for declaring. For the `plugin` section, you have to apply the type of `application`. For the `dependencies`, you have to declare the used `libraries`. For the `mainClassName`, you have to declare the main entry class name.

```gradle
apply plugin: 'application'

dependencies {
  implementation project(':libs')
}

mainClassName = 'gradles.app.Main'
```

After preparing the building environment, you can use the `gradle` command to build the project.

```sh
cd gradles
gradle build     # build the project

# run the executable binary
cd app/build/distributions
unzip app.zip
./app/bin/app

gradle clean     # clean the build
gradle run       # run the project
gradle projects  # list the project for building
```

You can also build the JAR file for the whole porject by creating a `build.gradle` on the scope of the whole project rather than each subproject.

```gradle
apply plugin: 'application'

dependencies {
  implementation project(':libs')
  implementation project(':app')
}

jar {
  manifest {
    attributes 'Main-Class': 'gradles.app.Main'
  }

  from 'app/build/classes/java/main'
  from 'libs/build/classes/java/main'
}
```

```sh
cd gradles
gradle build

# run the jar
java -jar ./build/libs/gradle.jar
```
