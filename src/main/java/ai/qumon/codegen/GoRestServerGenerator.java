package ai.qumon.codegen;

import org.openapitools.codegen.*;
import io.swagger.models.properties.*;
import org.openapitools.codegen.languages.GoGinServerCodegen;
import java.util.*;
import java.io.File;

public class GoRestServerGenerator extends GoGinServerCodegen implements CodegenConfig {

  // source folder where to write the files
  protected String sourceFolder = "src";
  protected String apiVersion = "1.0.0";

  /**
   * Configures the type of generator.
   * 
   * @return the CodegenType for this generator
   * @see org.openapitools.codegen.CodegenType
   */
  public CodegenType getTag() {
    return CodegenType.OTHER;
  }

  /**
   * Configures a friendly name for the generator. This will be used by the
   * generator to select the library with the -g flag.
   * 
   * @return the friendly name for the generator
   */
  public String getName() {
    return "go-rest-server";
  }

  /**
   * Provides an opportunity to inspect and modify operation data before the code
   * is generated.
   */
  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {

    // to try debugging your code generator:
    // set a break point on the next line.
    // then debug the JUnit test called LaunchGeneratorInDebugger

    Map<String, Object> results = super.postProcessOperationsWithModels(objs, allModels);

    Map<String, Object> ops = (Map<String, Object>) results.get("operations");
    ArrayList<CodegenOperation> opList = (ArrayList<CodegenOperation>) ops.get("operation");

    // iterate over the operation and perhaps modify something
    for (CodegenOperation co : opList) {
      // example:
      // co.httpMethod = co.httpMethod.toLowerCase();
    }

    return results;
  }

  /**
   * Returns human-friendly help for the generator. Provide the consumer with help
   * tips, parameters here
   *
   * @return A string value for the help message
   */
  @Override
  public String getHelp() {
    return "Generates a Go server library with the gin framework using OpenAPI-Generator."
        + "By default, it will also generate service classes.";
  }

  public GoRestServerGenerator() {
    super();

    // set the output folder here
    outputFolder = "generated-code/go";

    /**
     * Template Location. This is the location which templates will be read from.
     * The generator will use the resource stream to attempt to read the templates.
     */
    embeddedTemplateDir = templateDir = "go-rest-server";

    typeMapping.put("object", "lang.StringMap");
    defaultIncludes.add("github.com/qumonintelligence/go-common/v2/lang");
  }

  @Override
  public void processOpts() {
    super.processOpts();
    supportingFiles.add(new SupportingFile("static.index.html", "api", "index.html"));
    writeOptional(outputFolder, new SupportingFile("configure.mustache", apiPath, "configure.go"));
    writeOptional(outputFolder, new SupportingFile("auth.mustache", apiPath, "auth.go"));
  }

  @Override
  public String toVarName(String name) {

    name = super.toVarName(name);

    if (name.equalsIgnoreCase("id")) {
      return "ID";
    }

    // make ID instead of Id
    if (name.endsWith("Id")) {
      name = name.substring(0, name.length() - 2) + "ID";
    }

    return name;
  }

  @Override
  public String toParamName(String name) {
    name = super.toParamName(name);

    if (name.equalsIgnoreCase("id")) {
      return "ID";
    }

    // make ID instead of Id
    if (name.endsWith("Id")) {
      name = name.substring(0, name.length() - 2) + "ID";
    }

    return name;
  }
}