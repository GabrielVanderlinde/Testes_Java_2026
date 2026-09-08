package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaDigitalTest {

    @Test
    void verificarSaldoInicialZero(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar saldo inicial
        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void verificarDepositoDeveAumentarSaldo(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act: depositar valor
        contaDigital.depositar(100);

        // Assert: verificar aumento do saldo
        assertEquals(100,contaDigital.getSaldo());
    }

    @Test
    void saqueValidoDeveDiminuirSaldo(){
        // Arrange: criar conta com saldo
        ContaDigital contaDigital = new ContaDigital("Bento");
        contaDigital.depositar(100);

        // Act: realizar saque
        contaDigital.sacar(50);

        // Assert: verificar diminuição do saldo
        assertEquals(50,contaDigital.getSaldo());
    }

    @Test
    void depositoZeroLancaExcecao(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act & Assert: tentar depositar zero e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.depositar(0)
        );

        assertEquals("O depósito deve ser maior que zero.",excecao.getMessage());
        assertEquals(0, contaDigital.getSaldo());
    }

    @Test
    void depositoNegativoLancaExcecao(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act & Assert: tentar depositar valor negativo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.depositar(-10)
        );

        assertEquals("O depósito deve ser maior que zero.",excecao.getMessage());
        assertEquals(0, contaDigital.getSaldo());
    }

    @Test
    void saqueZeroLancaExcecao(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act & Assert: tentar sacar zero e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.sacar(0)
        );
        assertEquals("O saque deve ser maior que zero.",excecao.getMessage());
        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void saqueNegativoLancaExcecao(){
        // Arrange: criar conta digital
        ContaDigital contaDigital = new ContaDigital("Bento");

        // Act & Assert: tentar sacar valor negativo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.sacar(-10)
        );

        assertEquals("O saque deve ser maior que zero.",excecao.getMessage());
        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void saqueMaiorQueSaldoLancaExcecao(){
        // Arrange: criar conta com saldo de 100
        ContaDigital contaDigital = new ContaDigital("Bento");
        contaDigital.depositar(100);

        // Act & Assert: tentar sacar mais que o saldo e verificar exceção e mensagem
        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> contaDigital.sacar(101)
        );

        assertEquals("Saldo insuficiente.",excecao.getMessage());
        assertEquals(100, contaDigital.getSaldo());
    }
}
