Joern Git Extension
===================

Joern/Ocular provide a runtime extension mechanism that allows you to
develop custom analyzers on top of the platform. Extensions are
programs for the JVM that can be written in Java, Scala or
Kotlin. They can access the code property graph via the query language
OcularQL and can create new nodes, edges and properties via the
DiffGraph API. Moreover, they can interface with Java libraries to, for
example, to access the network or file system.

The Joern Git Extension is a minimal example of an extension. It reads
a git history using a Java library and tags nodes of functions that
have recently been modified. The main purpose of this extension is to
provide an example that shows how Joern extensions can be developed
and tested in the IntelliJ IDE. The extension is written in Scala and
makes use of the Java library jgit.

Dependencies
------------
- the Scala Build Tool (SBT) - any version

Building
--------

```bash
	sbt stage
```

Installation
------------
Copy the resulting JAR file to Joern's `lib` directory. Next time you
start joern, type `run`. Your extension should be listed.


```bash
   cp target/universal/stage/lib/io.shiftleft.joern-sample-extension-*.jar $joern_install/lib/
    cp target/universal/stage/lib/org.eclipse.jgit.org.eclipse.jgit* $joern_install/lib/
```

where `$joern_install` is the directory where you installed Joern/Ocular.


Running
-------

On the joern shell, type `run.gitextension`. You can also
inspect/modify options passed to the plugin by typing
`opts.gitextension.<TAB>`.
