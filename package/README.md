# Packge and Import in Java

`Package` is a mechanism for separating Java resources, for example, the same class name in different packages (pkgA.Cal vs pkgB.Cal). In Java, the package is also relative to the directory path. 

Compare to package, the `import` is used to import the class resources that are also relative to the directory paths like package. However, such importing in Java is more like naming the resource. If removing the declaration of import, you just need to write the full reosurce name.

```java
// ...
import libs.Cal;

// ...
Cal cal = new Cal();
```

You can change the script to the below.

```java
// ...
// import libs.Cal;

// ...
libs.Cal cal = new libs.Cal();
```

The following is the structure of the example.

```text
+ package
  + libs
    - Cal.java
  + src
    - Main.java
```

In `Cal.java`, the definition to package is also added.

```java
// the package defintion is relative to the directory path
package libs;
```

The same definition to `Main.java` is also added. In addition, the entry also imports the class resource.

```java
// the package defintion
package src;

// also importing the compiled class
import libs.Cal;
```

The following is the example of running the Java source code.

```sh
cd ./package

# compiling the library
javac -d . ./libs/*.java

# compiling the entry
javac -d . ./src/*.java

# running the Java class
java src.Main
```
