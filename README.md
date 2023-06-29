# Project: SimplecDatag (Simple Echo Data Aggregator)

This is a simple project for consuming the messages in a [Apache Kafka](https://kafka.apache.org/) topic published by another project called [SimplecService](https://github.com/HaidiChen/SimplecService) and store the messages (could also do some data manipulation if needed) in a local
[MySQL](https://dev.mysql.com/doc/refman/8.0/en/tutorial.html) server.

# Sequence Diagram

![Sequence Diagram](https://static.swimlanes.io/2129716d4b23be50d995bfd92c196d2c.png)
[source](https://swimlanes.io/d/njQkvDjz1)

This project is part of the Simplec Application:
![Simplec Application Diagram](https://static.swimlanes.io/e8161367c8de2a76ea839a6b8c7d25bc.png)
[source](https://swimlanes.io/d/1fIlD4u_9)

# Getting Started

This project involves:
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [Spring for Apache Kafka](https://docs.spring.io/spring-kafka/docs/current/reference/html/)
* [MySQL Connector/J 8.0](https://dev.mysql.com/doc/connector-j/8.0/en/)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/gradle-plugin/reference/html/#build-image)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#messaging.kafka)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/#streams-kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Guides
The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
