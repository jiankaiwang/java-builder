# Java Archive (JAR)

The JAR is the special format for Java. It is the archive for all of the required resources. You will need at least two types of resource for archiving, they are `MANIFEST.MF` and `compled.class`.

Now let's take a look at the structure of project first.

```text
+ jar
  + libs
    - Cal.java
  + src
    - Main.java
  - MANIFEST.MF
```

Next, you have to compile the Java source code before archiving. 

```sh
cd ./jar

# compiling the library
javac -d . ./libs/*.java

# compiling the entry
javac -d . ./src/*.java

# test if running is successful
# the entry `src.Main` is also the same to the Main-Class in MANIFEST.MF
java src.Main
```

You now can create the `MANIFEST.MF`. The `MANIFEST.MF` is the definition file for the entry and other meta information. The following is the minimum requirements for `MANIFEST.MF`. The `Main-Class` defines where the entry is. The `src.Main` is the same definition to the entry for java.

```conf
Main-Class: src.Main
```

Use the following commands to archive the Java source code.

```sh
# the below creates the JAR
# -c: create a archive
# -v: verbose output
# -f: the classes file for archiving
# -m: the manifest file
jar -cvfm Main.jar MANIFEST.MF src/Main.class libs/Cal.class

# you can also the following command to create the JAR without MANIFEST.MF
# -e: the main class
jar -cvfe Main.jar src.Main src/Main.class libs/Cal.class

# the below command lists the content of the archive
# -t: list the table of content for the archive
# -v: verbose output
jar tvf Main.jar
```

The running concept of JAR is to uncompress JAR to the same structure while archiving the resource. Second running the entry. You can use `java -jar` to run the archive.

```sh
java -jar ./Main.jar
```