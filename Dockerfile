FROM hseeberger/scala-sbt
RUN apt-get update && apt-get install -y --no-install-recommends openjfx && rm -rf /var/lib/apt/lists/*
WORKDIR /heroes
ADD . /heroes
ENV DISPLAY=:0.0
CMD sbt run