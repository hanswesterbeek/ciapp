#!/usr/bin/env bash
./mvnw clean package
docker build -t ciapp:$(git rev-parse --short HEAD) .