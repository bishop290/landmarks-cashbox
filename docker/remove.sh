#!/bin/sh

docker ps --filter status=exited -q | xargs docker rm
docker images -q | xargs docker rmi
docker ps -a
docker images