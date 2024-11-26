package ai.qumon.codegen;

import java.util.Objects;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.SupportingFile;
import org.openapitools.codegen.languages.GoGinServerCodegen;

public class GoRestServerGenerator extends GoGinServerCodegen implements CodegenConfig {

  // source folder where to write the files
  protected String sourceFolder = "src";
  protected String apiVersion = "1.0.2";

  /**
   * Configures the type of generator.
   *
   * @return the CodegenType for this generator
   * @see org.openapitools.codegen.CodegenType
   */
  @Override
  public CodegenType getTag() {
    return CodegenType.OTHER;
  }

  /**
   * Configures a friendly name for the generator. This will be used by the generator to select the
   * library with the -g flag.
   *
   * @return the friendly name for the generator
   */
  @Override
  public String getName() {
    return "go-rest-server";
  }


  /**
   * Returns human-friendly help for the generator. Provide the consumer with help tips, parameters
   * here
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

    /*
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
    // Don't generate the go.mod to restore original pre 5.x behavior.
    supportingFiles.removeIf(file -> Objects.equals(file.getDestinationFilename(), "go.mod"));
    supportingFiles.add(new SupportingFile("static.index.html", "api", "index.html"));
    supportingFiles.add(new SupportingFile("configure.mustache", apiPath, "configure.go").doNotOverwrite());
    // supportingFiles.add(new SupportingFile("auth.mustache", apiPath, "auth.go").doNotOverwrite());
    supportingFiles.add(new SupportingFile("auth.mustache", apiPath, "auth.go")
                .doNotOverwrite());
  }

  @Override
  public String toModelName(String name) {

    if (name.equalsIgnoreCase("ip")) {
      return "IP";
    }

    name = super.toModelName(name);

    // make ID instead of Id
    if (name.endsWith("Ip")) {
      name = name.substring(0, name.length() - 2) + "IP";
    }

    return name;
  }

  @Override
  public String toVarName(String name) {

    name = super.toVarName(name);

    if (name.equalsIgnoreCase("id")) {
      return "ID";
    }

    if (name.equalsIgnoreCase("ip")) {
      return "IP";
    }

    // make ID instead of Id
    if (name.endsWith("Id")) {
      name = name.substring(0, name.length() - 2) + "ID";
    }

    // make ID instead of Id
    if (name.endsWith("Ip")) {
      name = name.substring(0, name.length() - 2) + "IP";
    }

    return name;
  }

  @Override
  public String toParamName(String name) {
    name = super.toParamName(name);

    if (name.equalsIgnoreCase("id")) {
      return "ID";
    }

    if (name.equalsIgnoreCase("ip")) {
      return "IP";
    }

    // make ID instead of Id
    if (name.endsWith("Ip")) {
      name = name.substring(0, name.length() - 2) + "IP";
    }

    return name;
  }
}