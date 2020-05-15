Joern Git Extension
===================

The Joern Git Extension is a minimal example of an extension for the
code analysis platform Joern. The extension reads a git history and
tags nodes of functions that have recently been modified. The main
purpose of this extension is to provide an example that shows how
Joern extensions can be developed and tested in the IntelliJ IDE.

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
```

Running
-------

On the joern shell, type `run.gitextension`
