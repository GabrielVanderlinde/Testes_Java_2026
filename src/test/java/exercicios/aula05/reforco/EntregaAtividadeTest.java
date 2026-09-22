package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntregaAtividadeTest {

    @ParameterizedTest(name = "Atraso {0} min, Justificada: {1} = {2}")
    @CsvSource({
            "-1, true,  NO_PRAZO",
            "-1, false, NO_PRAZO",
            "0,  true,  NO_PRAZO",
            "0,  false, NO_PRAZO",
            "1,  true,  ATRASO_TOLERADO",
            "1,  false, ATRASO_TOLERADO",
            "5,  true,  ATRASO_TOLERADO",
            "5,  false, ATRASO_TOLERADO",
            "10, true,  ATRASO_TOLERADO",
            "10, false, ATRASO_TOLERADO",
            "11, true,  ANALISE_PROFESSOR",
            "11, false, ATRASADA",
            "15, true,  ANALISE_PROFESSOR",
            "15, false, ATRASADA",
            "30, true,  ANALISE_PROFESSOR",
            "30, false, ATRASADA",
            "60, true,  ANALISE_PROFESSOR",
            "60, false, ATRASADA"
    })
    void verificarDeveRetornarStatusCorreto(
            int minutosAtraso,
            boolean justificativa,
            String esperado) {

        String resultado = EntregaAtividade.verificar(minutosAtraso, justificativa);
        assertEquals(esperado, resultado);
    }
}
