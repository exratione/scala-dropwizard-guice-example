# A Dropwizard-Guice Example in Scala

This repository contains a simple example showing how to use Guice for
dependency injection in Dropwizard. Scala is the chosen language but note that
here this is very much "coffeescript for Java" rather than anything more
sophisticated or functional.

The creation of this example was prompted by a project in which the use of
[Scala][1] and an older version of [Dropwizard][2] were required. The
lightweight [Guice][3] framework was chosen for dependency injection but a
number of annoying issues were encountered, none of which were really all that
well covered by existing documentation and other example projects.

## Tools Used

The following packages are employed in the example Dropwizard application:

* [dropwizard][2]
* [dropwizard-guice][4]
* [guice][3]
* [scala-guice][5]

The dropwizard-guice package adds a bundle to the Dropwizard initialization that
automatically adds resource implementations to the environment. It also creates
a Guice injector that provides access to the configuration and environment
instances. This removes a lot of the boilerplate: it is quite possible to create
a Dropwizard application that has an empty `run` method in its service class.

## Building and Running the Example

To build and run the example application, pull down this repository, get set up
with Maven, and then:

```
cd dropwizard-6.2
mvn clean install

# Run the server application.
java \
  -cp target/sdgexample.jar \
  com.exratione.sdgexample.SdgExampleScalaService \
  server config/example.yml

# Run the command.
java \
  -cp target/sdgexample.jar \
  com.exratione.sdgexample.SdgExampleScalaService \
  sdg config/example.yml
```

## Potential Pain Points During Development

The following items are worthy of note.

### Ensure the Dependency Versions Match Up

The Dropwizard version used drives which version of dropwizard-guice can be
used. That in turn requires a specific version of Guice or you will start to run
into issues. Picking the latest for everything generally works, but if you have
to use an older version of Dropwizard then other packages much also use older
versions: you will probably have to look through the older version tags in their
repositories and inspect `pom.xml` files in order to find the versions needed
for their dependencies.

Since Guice is a dependency injection framework many errors due to version
mismatches (such as missing methods, changed method signatures, and the like)
won't show up during compilation, but rather arise at runtime.

### Consequences of Deploying Both a Service and a Command

If your Dropwizard application has a service and an `EnvironmentCommand`
implementation then you will have to do a little additional work in order to be
able to access the injector created by dropwizard-guice in both. The solution
here is to create a parent service class with a suitable signature.

### Issues with Large JAR Files in Java 1.7

Note that for large projects, packaging into a single JAR can produce an archive
that [cannot be read in Java 1.7][6] as of Q1 2015. Fortunately the bug in Java
at the root of all this, relating to the number of files in the JAR archive,
has a workaround. So while the following command will report the JAR file as
corrupt:

```
java -jar <path/to/jar> server <path/to/config>
```

A slightly different approach will still work:

```
java -cp <path/to/jar> <main.class.name> server <path/to/config>
```

### @Provides Methods Can't Be Overridden

You can't override an `@Provides` method in a Guice module class, which makes
building useful hierarchies for deploying mocks in tests a lot more verbose than
might otherwise be the case. One possible approach, demonstrated here, is to
break out each set of `@Provides` methods into a trait and then distribute the
traits as needed between mock module classs.

[1]: http://www.scala-lang.org/
[2]: http://dropwizard.io/
[3]: https://github.com/google/guice
[4]: https://github.com/HubSpot/dropwizard-guice
[5]: https://github.com/codingwell/scala-guice
[6]: http://stackoverflow.com/questions/13021423/invalid-or-corrupt-jar-file-built-by-maven-shade-plugin
