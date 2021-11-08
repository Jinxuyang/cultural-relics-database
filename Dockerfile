FROM java:8

EXPOSE 8889

ADD target/*.jar /app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]