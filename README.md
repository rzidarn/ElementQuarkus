## DOCUMENTATION

> **_DEV mode_**
```shell script
./mvnw quarkus:dev
```
> **_TEST mode_**
```shell script
./mvnw quarkus:test
```
> **_DASHBOARD:_** http://localhost:8082/q/dev/

> **_EXTENSIONS_**
```shell script
./mvnw quarkus:add-extension -D extensions=hibernate-reactive-panache,reactive-pg-client,io.quarkus:quarkus-elytron-security-common
```

48