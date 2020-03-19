package:
	mvn package -Dmaven.test.skip=true

clean:
	mvn clean

run:
	java -cp go-rest-server-openapi-generator-1.0.0.jar:openapi-generator-cli.jar \
		org.openapitools.codegen.OpenAPIGenerator || true
