package exercicios.aula05;

public final class CalculadoraFrete {
    // Construtor privado: a classe oferece apenas métodos estáticos.
    private CalculadoraFrete(){
    }

    // Calcula o valor do frete baseado no peso e na modalidade de entrega
    public static double calcular(double pesoKg,boolean entregaExpressa){
        // Valida se o peso informado é válido
        if (pesoKg<=0){
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }

        // Cálculo base: taxa fixa de R$ 8,00 + R$ 2,00 por quilo
        double valorFinal = 8.00 + (2.00 * pesoKg);

        // Aplica 50% de acréscimo caso seja entrega expressa
        if (entregaExpressa){
            double acrescimo = valorFinal * 50/100;
            valorFinal+=acrescimo;
        }

        return valorFinal;
    }
}
