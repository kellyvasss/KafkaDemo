# Apache Kafka
### NEED

* MySQL installed
* Apache Kafka version 3.5.1 installed
* Atleast Java 11 installed
* JDK 17
* Internet connection

* First, download Apache Kafka. Instructions on how to do it you can find on youtube.
* Make sure you first start up zookeeper and then kafka-server.
* Once zookeeper and kafka server is running, you can start the project as normal.
* Edit the "application.properties"-file, fill in this:
##### DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
##### spring.datasource.url=(usually -> "jdbc:mysql://localhost:3306/{YOUR DATABASE NAME}")
##### spring.datasource.username=YOUR USERNAME HERE
##### spring.datasource.password=YOUR PASSWORD HERE



