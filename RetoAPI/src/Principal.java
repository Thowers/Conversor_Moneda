import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        MonedaHttpClient usuario = new MonedaHttpClient();

        System.out.println("Introduce la moneda de origen: ");
        String monedaOrigen = lector.next().toUpperCase();

        Moneda moneda = usuario.convertirMoneda(monedaOrigen);
        if (moneda == null){
            System.out.println("No se pudo obtener la tase de conversion.");
            return;
        }

        System.out.println("Divisas disponibles: " + moneda.conversion_rates().keySet());
        System.out.println("Introduce la moneda de destino: ");
        String monedaDestino = lector.next().toUpperCase();

        System.out.print("Introduce el monto a convertir: ");
        double valor = lector.nextDouble();

        Map<String, Double> tasasConversion = moneda.conversion_rates();
        Double tasa = tasasConversion.get(monedaDestino);
        if (tasa == null) {
            System.out.println("Moneda de destino no válida.");
        } else {
            double resultado = valor * tasa;
            System.out.printf("%.2f %s equivale a %.2f %s%n", valor, monedaOrigen, resultado, monedaDestino);
        }
    }
}
