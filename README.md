# dante-scala-spark-streaming
Example project in SCALA to count the words in the Divine Comedy using Apache Spark and simulating a streaming of data
IntelliJ project files

## usage
- sbt package: to compile the project *or* select "sbt shell" from the bottom of the screen and write the "package" command
- spark-submit --class Dante target/.../dante....jar
- ./sender.sh | nc -l localhost -p 9999
