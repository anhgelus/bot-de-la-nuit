FROM azul/zulu-openjdk-alpine:17-latest as build

WORKDIR /app

COPY . /app

ENV TOKEN ""

RUN ./gradlew --no-daemon shadowJar

CMD ["java", "-jar", "/app/build/libs/bot-de-la-nuit.jar", "${TOKEN}"]
