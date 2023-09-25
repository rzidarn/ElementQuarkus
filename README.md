## DOCUMENTATION

> **_DASHBOARD:_** http://localhost:8082/q/dev/

> **_DEV mode_**
```shell script
./mvnw quarkus:dev
```
> **_EXTENSIONS_**
```shell script
./mvnw quarkus:add-extension -D extensions=hibernate-reactive-panache,reactive-pg-client,io.quarkus:quarkus-elytron-security-common
```

> **_TESTS_**
```shell script
./mvnw clean test -D test=UserResourceTest
```
