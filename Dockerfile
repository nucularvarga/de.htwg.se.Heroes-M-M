FROM hseeberger/scala-sbt:11.0.2_2.12.8_1.2.8
WORKDIR /heroes
ADD . /heroes
CMD sbt run