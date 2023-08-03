# Base image with Java 17
FROM amazoncorretto:17

# Application environment variables
RUN mkdir /app
ENV APP_HOME=/app \
    APP_NAME=arpy-duel \
    APP_VERSION=0.1.0-SNAPSHOT \
    GRADLE_OPTS="-Dorg.gradle.daemon=false"

# Working directory
WORKDIR $APP_HOME

# Copy necessary Gradle files
COPY build.gradle settings.gradle gradlew gradlew.bat $APP_HOME/
COPY gradle $APP_HOME/gradle

# Copy application source code
COPY src $APP_HOME/src

# Build the application
RUN chmod +x gradlew && \
    ./gradlew build --stacktrace

# Copy the generated JAR to the application folder
RUN mv build/libs/$APP_NAME-$APP_VERSION.jar $APP_HOME/app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]