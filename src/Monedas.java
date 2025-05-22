
public class Monedas {
    private Double valor;
    private String acronimo;
    private String acronimoAConvertir;
    private Double valorAConvertir;
    private String valorFinal;
    
    public Monedas (Double valor) {
        this.valor = valor;
    }
    
    public Monedas (MonedasExchangeRate miMonedasExchangeRate, String clave, String acronimoAConvertir, Double valorAConvertir) {
        this.valor = miMonedasExchangeRate.conversionRates().get(clave);
        this.valorAConvertir = valorAConvertir;
        this.acronimo = clave;
        this.acronimoAConvertir = acronimoAConvertir;
        this.valorFinal = String.format("%.2f", (this.valorAConvertir / this.valor));
    }
    
    @Override
    public String toString() {
        return "El valor " + valorAConvertir + " " + acronimo + " es igual a " 
                + valorFinal + " " + acronimoAConvertir;
    }
    
}
