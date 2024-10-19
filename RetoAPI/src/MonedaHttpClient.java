import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MonedaHttpClient {
    public Moneda convertirMoneda(String tipoMoneda){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1b20beda20357aeb927a5738/latest/" + tipoMoneda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                return new Gson().fromJson(response.body(), Moneda.class);
            }else{
                throw new RuntimeException("Error en la respuesta de la api: " + response.statusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("No se ha encontrado la moneda elegid: "+e.getMessage());
        }
    }
}
