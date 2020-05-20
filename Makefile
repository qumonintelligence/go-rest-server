package:
	mvn package -Dmaven.test.skip=true
	cp target/go-rest-server-openapi-generator-1.0.1.jar ~/Shared/Tools/code-generators

clean:
	mvn clean

run:
	java -cp target/go-rest-server-openapi-generator-1.0.1.jar:openapi-generator-cli.jar \
		org.openapitools.codegen.OpenAPIGenerator || true
