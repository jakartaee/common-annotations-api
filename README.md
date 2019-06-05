# Jakarta Annotations [![Build Status](https://travis-ci.org/eclipse-ee4j/common-annotations-api.svg?branch=master)](https://travis-ci.org/eclipse-ee4j/common-annotations-api)

The intention of Java(TM) Common Annotations is to develop annotations for common semantic 
concepts in the Java SE and Java EE platforms that apply across a variety of individual 
technologies. It was envisioned that various JSRs would use annotations to enable a declarative
style of programming. It would be especially valuable to have consistency within the Java EE 
component JSRs, but it is also valuable to allow consistency between Java EE and Java SE.

This standalone release of Jakarta Annotations uses a
[Java Platform Module System](http://openjdk.java.net/projects/jigsaw/spec/)
"automatic" module name of `java.annotation`, to match the module name
used in JDK 9.  A future version will include full module metadata.
