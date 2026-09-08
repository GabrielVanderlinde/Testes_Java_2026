package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaDigitalTest {

    @Test
    void verificarSaldoInicialZero(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void verificarDepositoDeveAumentarSaldo(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        contaDigital.depositar(100);

        assertEquals(100,contaDigital.getSaldo());
    }

    @Test
    void saqueValidoDeveDiminuirSaldo(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        contaDigital.depositar(100);

        contaDigital.sacar(50);

        assertEquals(50,contaDigital.getSaldo());
    }

    @Test
    void depositoZeroLancaExcecao(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.depositar(0)
        );

        assertEquals("O depósito deve ser maior que zero.",excecao.getMessage());

        assertEquals(0, contaDigital.getSaldo());
    }

    @Test
    void depositoNegativoLancaExcecao(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.depositar(-10)
        );

        assertEquals("O depósito deve ser maior que zero.",excecao.getMessage());

        assertEquals(0, contaDigital.getSaldo());
    }

    @Test
    void saqueZeroLancaExcecao(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.sacar(0)
        );
        assertEquals("O saque deve ser maior que zero.",excecao.getMessage());

        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void saqueNegativoLancaExcecao(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> contaDigital.sacar(-10)
        );

        assertEquals("O saque deve ser maior que zero.",excecao.getMessage());

        assertEquals(0,contaDigital.getSaldo());
    }

    @Test
    void saqueMaiorQueSaldoLancaExcecao(){
        ContaDigital contaDigital = new ContaDigital("Bento");

        contaDigital.depositar(100);

        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> contaDigital.sacar(101)
        );

        assertEquals("Saldo insuficiente.",excecao.getMessage());

        assertEquals(100, contaDigital.getSaldo());
    }
}
