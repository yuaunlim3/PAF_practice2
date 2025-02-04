FROM openjdk:23-jdk-oracle 

ARG COMPILE_DIR=/compiledir 

WORKDIR ${COMPILE_DIR}

# Copy Files 
COPY ./mvnw . 
COPY pom.xml .

#Copy directories
COPY .mvn .mvn
COPY src src

#Makes it executable
RUN chmod a+x ./mvnw &&./mvnw package -Dmaven.test.skip=true 

ENV SERVER_PORT=8080 

# Expose the port your application will run on
EXPOSE ${SERVER_PORT}


ENTRYPOINT java -jar target/bookings-0.0.1-SNAPSHOT.jar


