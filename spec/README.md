Jakarta Annotations Specification
============================

This project generates the Jakarta Annotations Specification.

Building
--------

Prerequisites:

* JDK8+
* Maven 3.0.3+

Run the full build:

`mvn install`

Generate specification with given status (ex. "Final Release"):

`mvn clean install -Dstatus="Final Release"`

Locate the html files:
- `target/generated-docs/annotations-spec-<version>.html`

Locate the PDF files:
- `target/generated-docs/annotations-spec-<version>.pdf`
