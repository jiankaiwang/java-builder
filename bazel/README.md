# Bazel

`Bazel` is a making tool for lots of programming languages, for example, C++, Java, Android, and even iOS, etc. It is created and hosted by Google, and was released at 2015. It is also used in building lots of open-source projects, like Tensorflow, Angular, etc. Its parallel building brings with high performance, especially for those large projects. Fortunately it is easy for horizontal scaling if you switch to another programming language that bazel supports. The same logics on the `BUILD` file bring you with a stone of stepping to the new field quickly.

For using bazel as the building tool, there are two necessary files, they are `WORKSPACE` and `BUILD`. The `WORKSPACE` defines the external resources and `BUILD` defines how to build the internal source code. The two common types defined in `BUILD` are `library` and `binary`. 

Before building the Java source code, the following is the project structure.

```text
+ bazel
  + libs
    - BUILD
    - Cal.java    # the building target for the library called by Main
  + src
    - BUILD
    - Main.java   # the building target for the executable binary
  - WORKSPACE
```

In `BUILD`, you have to define what the types of resources are. The `java_library` defines the resource as the library with its recognized name (`name`) and the source file (`src`). The item `visibility` defines the visibility attributes or the scope in the project level. The flag `//src:__pkg__` reveals the library is visible for the whole src package.

```conf
load("@rules_java//java:defs.bzl", "java_library")

java_library(
  name = "libs",
  srcs = ["Cal.java"],
  visibility = ["//src:__pkg__"]
)
```

To the executable binary, the following is the example in `src/Main.java`. In the following `BUILD` file, the `java_binary` defines the resources for executable binary. The `main_class` is the necessary key for the entry. The `deps` key defines the dependencies to the executable binary. The `//libs:libs` defines how to import the `libs` resources in the folder `libs`.

```conf
load("@rules_java//java:defs.bzl", "java_binary")

java_binary(
  name = "SimpleCal",
  srcs = ["Main.java"],
  main_class = "src.Main",
  deps = ["//libs:libs"]
)
```

Next, you can build the executable binary.

```sh
cd ./bazel

# build the source code
bazel build //src:SimpleCal

# run the executable binary
./bazel-bin/src/SimpleCal
```

In addition, in Java there is a convenient way of porting service by JAR. To bazel, you can build the JAR file by simply adding `_deploy.jar` to the end of the name.

```sh
# build the JAR 
bazel build //src:SimpleCal_deploy.jar

# run the JAR
java -jar ./bazel-bin/src/SimpleCal_deploy.jar
```