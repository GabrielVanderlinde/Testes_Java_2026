package exercicios.aula05.desafios;

public final class CalculadoraFrete {

    private CalculadoraFrete(){
    }

    public static double calcular(double pesoKg,boolean entregaExpressa){
        if (pesoKg<=0){
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }

        double valorFinal = 8.00 + (2.00 * pesoKg);

        if (entregaExpressa){
            double acrescimo = valorFinal * 50/100;
            valorFinal+=acrescimo;
        }

        return valorFinal;
    }
}
