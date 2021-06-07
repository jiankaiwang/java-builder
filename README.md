# Java Builder

![](https://img.shields.io/badge/Language-Java-blue)
![](https://img.shields.io/badge/OpenJDK-≥_16-green)

The Java builder is a tutorial for demonstrating how to build the Java-based projects by different approaches. The repository uses `OpenJDK` as the building base.

The first step is to understand how to build the Java project by the java compiler.

* [`javac`](javac): The `javac` compiler is a tool compiling the java source code into the java class. The java class can be further executed on the java virtual machine (`JVM`) that is specified to different hardware platforms.

After understanding the basic concept of how to run the java source code, the next step is to understand how to port them.

* [`package`](package): After understanding the basic concept of compiling and running Java source code, the next step is to understand how the `package` and `import` work. The `import` mechanism in Java is different from other programming languages. These mechanisms are essential for the large project.

* [`jar`](jar): After compiling the java source code, you can archive all of them into an executable file. In Java, such the collection of executables are called Java ARchives (JAR). If packaging a JAR file, it is convenient to delive or port the jar to the production. 

However the JAR file is a convenient way of delivering the Java-based service, it still requires a making tool to effectively build the large Java project. Such building tools are more productive and powerful for different building conditions. The followings are several building tools.

* [`gradle`](gradle): The gradle is the Java's makefile. The gradle is a famous Java building tool, however it is also for lots of programming languages, like `Scala`, `Kotlin`, and even `Swift`, etc. In this example, we introduced how to build Java source code using `gradle init`.

  * [`Simple Version of Using Gradle`](gradles): You can also build the Java source code by `gradle` from an existing scripts. For many cases, you may have written Java source code already, and it is not suitable for you to initialize the project. In this example, we introduced how to build the project under the existing source code.

* [`bazel`](bazel): The bazel is a building tool created and released by Google. It is a building tool for lots of programming lanagugaes, like `C++`, `Java`, etc. Its parallel building capability brings with high performance on building a large project.