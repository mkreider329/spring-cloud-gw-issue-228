# spring-cloud-gw-issue-228

## Building
```bash
mvn clean install
```

## From terminal 1 (sample MS listening on http and https)
```bash
cd apigw-test-ms
java -jar target/apigw-test-ms-exec.jar
```

## From terminal 2 (Spring Cloud GW with route to MS above)
```bash
cd gateway
jar java -jar target/gateway-0.0.1-SNAPSHOT.jar
```

## From terminal 3 (Test Client)
```bash
cd test-driver
java -jar target/test-driver-1.0.0.jar > /dev/null
```

Application running in terminal 3 will terminate when error occurs (typically takes less than two minutes)
