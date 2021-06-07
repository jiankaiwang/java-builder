# Java Compiler (javac) and Java Virtual Machine (JVM)

The basic process to run the Java source code is composed of two steps, they are compiling and running on the JVM. After installing the JDK and setting up the Java running environment, you can find the `java` and `javac` tools. The `javac` is the tool for compiling the java source code, and the `java` is the tool for running the compiled class. 

The following is the simple structure example of Java source code.

```text
+ javac
  + bin
    - Main.class
    - Cal.class
  + src
    - Main.java
    - Cal.java
```

The following is the example to compile and run the java source code.

```sh
cd ./javac

# compiling java source code
# -d: the path for compiled classes
javac -d ./bin ./src/*.java

# running the classes
# -cp, -classpath: the path for classes
java -cp ./bin Main
```