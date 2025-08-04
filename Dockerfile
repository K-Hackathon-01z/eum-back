# Java 17 슬림 이미지 사용
FROM openjdk:17-jdk-slim

# JAR 복사 (파일명 정확히 입력)
COPY build/libs/eum-0.0.1-SNAPSHOT.jar app.jar

# 앱 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
