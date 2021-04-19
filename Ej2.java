package ExpresionesRegular;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej2 {
  public static void main(String[] args) {

    if (args.length != 2) {
      System.err.println("Error en el número de parámetros"); 
      System.exit(1);
    }

    String url_parametro = args[0];
    String etiqueta = args[1];
    try {

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_parametro)).GET().build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

      Pattern patron = Pattern.compile("<" + etiqueta + ">(.*)</" + etiqueta + ">", Pattern.DOTALL);
      Matcher matcher = patron.matcher(response.body());

      int n = 0;
      while (matcher.find()) {
      System.out.println(matcher.group(1));
      ++n;
      }

      System.out.println("\nNúmero de etiquetas encontradas: " + n);

    } catch(IOException | InterruptedException e) {
      System.out.println("No se ha podido establecer conexion con la web.");
    }
  }
}
