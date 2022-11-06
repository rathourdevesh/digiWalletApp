FROM maven:latest

WORKDIR /digiwallet
COPY . /digiwallet
RUN mvn clean install

CMD mvn spring-boot:run