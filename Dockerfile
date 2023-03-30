## base-image
#FROM openjdk:11
#
## 변수설정 (빌드파일의 경로)
#ARG JAR_FILE=*.jar
#
## 빌드파일을 컨테이너로 복사
#COPY ${JAR_FILE} app.jar
#
## jar 파일 실행
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:11 as builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
