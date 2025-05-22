
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrincipalConversorDeMonedas {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        int opcionDelMenu = 0;
        while (opcionDelMenu != 7) {

            String conversion = null;
            String conversor = null;
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .setPrettyPrinting()
                    .create();

            do {
                MenuConversor.mostrarMenu();

                opcionDelMenu = Integer.parseInt(scanner.nextLine());
                            
                switch (opcionDelMenu) {
                    case 1:
                        conversion = "ARS";
                        conversor = "USD";
                        break;
                    case 2:
                        conversion = "USD";
                        conversor = "ARS";
                        break;
                    case 3:
                        conversion = "BRL";
                        conversor = "USD";
                        break;
                    case 4:
                        conversion = "USD";
                        conversor = "BRL";
                        break;
                    case 5:
                        conversion = "MXN";
                        conversor = "USD";
                        break;
                    case 6:
                        conversion = "USD";
                        conversor = "MXN";
                        break;
                    case 7:
                        System.out.println("El programa ha finalizado correctamente");
                        break;
                    default:
                        System.out.println(opcionDelMenu + " No es un número válido, por favor ingrese un número válido");
                        break;
                }
                
            } while (opcionDelMenu > 7 || opcionDelMenu < 1);

            if (opcionDelMenu == 7) {
                break;
            }

            System.out.println("Ingresa el valor que desee convertir: ");
            Double valorAntesDeConversion = Double.valueOf(scanner.nextLine());

            var token = "68c786184f166053b12caf95";
            var url = "https://v6.exchangerate-api.com/v6/"
                    + "/" + token + "/latest/" + conversion;
            //System.out.println("Mi url: " + url);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String miJson = response.body();

            MonedasExchangeRate miMonedaExchangeRate = gson.fromJson(miJson, MonedasExchangeRate.class);

            Monedas miMoneda = new Monedas(miMonedaExchangeRate, conversor, conversion, valorAntesDeConversion);
            System.out.println(miMoneda);

        } // Fin del while
    }
}
