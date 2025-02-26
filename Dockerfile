FROM ubuntu:latest
LABEL authors="damie"

ENTRYPOINT ["top", "-b"]