[![CircleCI](https://circleci.com/gh/hanswesterbeek/ciapp.svg?style=svg)](https://circleci.com/gh/hanswesterbeek/ciapp)
[![codecov](https://codecov.io/gh/hanswesterbeek/ciapp/branch/master/graph/badge.svg)](https://codecov.io/gh/hanswesterbeek/ciapp)

# Intro
Sample app to demo and play with some ci/cd stuff.

# cheatsheet

To build docker image : `./build.sh`

To start everything: 
```bash

docker-compose up -d -e APP_VER=$(git rev-parse --short HEAD)

````

See all processes: `docker ps`

Watch lb status: http://0.0.0.0:1936

Go to app frontend: [http://localhost]

Node health: http://localhost/actuator/health

Info: http://localhost/actuator/info