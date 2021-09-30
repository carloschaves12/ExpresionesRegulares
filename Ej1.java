package ExpresionesRegular;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej1 {
  
  private static String regexGenerator (String value) {

    switch (value) {
      case "DNI":
        return "([0-9]{8}[A-HJ-NP-TV-Z])";

      case "IP":
        return "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3})";

      case "MAT":
        return "[0-9]{4}[A-Z]{3}";

      case "HEX":
        return "#[0-9A-F]+";

      case "FEC":
        return "(\\d{1,2}/\\d{1,2}/\\d{4})";

      case "TWI":
        return "(@[a-zA-Z0-9-_]*)";  

      default:
    }
    return value;
  }
  
  public static void main(String[] args) {
    

    if (args.length != 2) {
      System.err.println("Error en el número de parmetros"); 
      System.exit(1);
    }

    try {
      String regexp = regexGenerator(args[1]);
      Pattern patron = Pattern.compile(regexp);
      List<String> lineas = Files.readAllLines(Paths.get(args[0]));
      for(String linea : lineas) {
        Matcher matcher = patron.matcher(linea);
        if (matcher.find()){
          for (int i = 0; i <= matcher.groupCount(); i++) {
            System.out.println(matcher.group());
          }
        }
      }
    } catch(IOException e) {
      System.out.println("No se ha podido abrir el fichero.");
    }
  }
}
