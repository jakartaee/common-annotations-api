///////////////////////////////////////////////////////////////////////
NOTE TO WRITERS:
The following sections should be customized for the technology.
This text was originally from the JAX-RS TCK.  Most references
to JAX-RS have been parameterized to serve as a simple starting
point for customization.  There are still many details that will
need to be changed or removed.  The major sections 4.1, 4.2, and
4.3 should be preserved.  If their titles are changed, the links
at the top of config.adoc will need to be changed as well as well
as toc.adoc.
///////////////////////////////////////////////////////////////////////

[[GBFVU]][[configuring-your-environment-to-run-the-tck-against-the-reference-implementation]]

4.1 Configuring Your Environment to Run the TCK Against a Compatible Implementation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

After configuring your environment as described in this section,
continue with the instructions in link:using.html#GBFWO[Chapter 5, "Executing Tests."]


[NOTE]
=======================================================================

In these instructions, variables in angle brackets need to be expanded
for each platform. For example, `<JAVA_HOME>` becomes `$JAVA_HOME` on
Solaris/Linux and `%JAVA_HOME%` on Windows. In addition, the forward
slashes (`/`) used in all of the examples need to be replaced with
backslashes (`\`) for Windows. Finally, be sure to use the appropriate
separator for your operating system when specifying multiple path
entries (`;` on Windows, `:` on UNIX/Linux).

On Windows, you must escape any backslashes with an extra backslash in
path separators used in any of the following properties, or use forward
slashes as a path separator instead.

=======================================================================


1.  Set the following environment variables in your shell environment:
  a.  `JAVA_HOME` to the directory in which Java SE {SEversion} is installed
  b.  `M2_HOME` to the directory in which the Apache Maven build tool is installed.
  c.  `PATH` to include the following directories: `JAVA_HOME/bin`,
  and `M2_HOME/bin`
2.  Set the following System properties:
  a.  Set the `sigTestClassPath`
  property to point to a Jakarta Annotations CI classes/jars that contain the
  annotations being supported in Step link:#BABJHHAJ[c], below.
  b. Set the `jimage.dir` property to the location where JDK modules will be extracted.
  c.  [[BABJHHAJ]] Depending on the annotations that are supported or unsupported in your
  Jakarta Annotations implementation, the following properties
  should be set to `true` or `false` respectively. +
  By default, all of these annotations are supported in the Jakarta Annotations CI. +
[source,oac_no_warn]
----
#jakarta.annotation.Generated
ca.sig.generated=true

#jakarta.annotation.PostConstruct
ca.sig.postconstruct=true

#jakarta.annotation.PreDestroy
ca.sig.predestroy=true

#jakarta.annotation.Priorityca.sig.priority=true

#jakarta.annotation.Resource
ca.sig.resource=true

#jakarta.annotation.Resources
ca.sig.resources=true

#jakarta.annotation.security.DeclareRoles
ca.sig.securitydeclareroles=true

#jakarta.annotation.security.DenyAll
ca.sig.securitydenyall=true

#jakarta.annotation.security.PermitAll
ca.sig.securitypermitall=true

#jakarta.annotation.security.RolesAllowed
ca.sig.securityrolesallowed=true

#jakarta.annotation.security.RunAs
ca.sig.securityrunas=true

#jakarta.annotation.sql.DataSourceDefinition
ca.sig.sqldatasourcedefinition=true

#jakarta.annotation.sql.DataSourceDefinitions
ca.sig.sqldatasourcedefinitions=true
----
3. Set the below jars to the classpath
  a. JAR file for the {TechnologyShortName} {TechnologyVersion} API. +
  eg.  `${web.home}/modules/jakarta.annotations-api.jar`.
  b. JUnit 5 jars (5.10.2+)
  Maven cordinates :
+
[source,oac_no_warn]
----
       <dependency>
          <groupId>org.junit</groupId>
          <artifactId>junit-bom</artifactId>
          <version>5.10.2</version>
          <type>pom</type>
          <scope>import</scope>
       </dependency>
----
+
 c. sigtest-maven-plugin (2.2) to run the signature tests.
  Maven cordinates :
+
[source,oac_no_warn]
----
      <dependency>
            <groupId>jakarta.tck</groupId>
            <artifactId>sigtest-maven-plugin</artifactId>
            <version>2.2</version>
        </dependency>
----

[[GCLHU]][[configuring-your-environment-to-repackage-and-run-the-tck-against-the-vendor-implementation]]

4.2 Configuring Your Environment to Repackage and Run the TCK Against the Vendor Implementation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This is not required for the Jakarta Annotations TCK.

[[GHGDG]][[publishing-the-test-applications]]

4.3 Publishing the Test Applications
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This is not required for the Jakarta Annotations TCK.
