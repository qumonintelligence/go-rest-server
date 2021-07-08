VERSION=1.0.6

package:
	mvn package -Dmaven.test.skip=true
	cp target/go-rest-server-openapi-generator-$(VERSION).jar ~/bin/

clean:
	mvn clean

run:
	java -cp target/go-rest-server-openapi-generator-$(VERSION).jar:openapi-generator-cli.jar \
		org.openapitools.codegen.OpenAPIGenerator || true
