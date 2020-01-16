# Projecto ES

## Setup MySQL
* Criar db com nome es_project
* sugestão: user e pass: es_project
* Carregar o es_project.dump

## Build
* mvn clean compile assembly:single test

## Run
* java -cp target/ESMaven-1.0-jar-with-dependencies.jar es_project.ProjectMain

