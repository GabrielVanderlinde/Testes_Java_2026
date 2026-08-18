package entrega_atividades.Aula1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LivroTest {

    private Livro livro;

    @BeforeEach
    public void preparar(){
        livro = new Livro("007",10.00,10,"Ação");
    }

    @Test
    public void testarObjetoDeLivro(){
        Livro livro = new Livro("Bella e a fera",
                15.00,10,"Trama");

        assertEquals("Bella e a fera",livro.getTitulo());
        assertEquals(15.00,livro.getPrecoBase());
        assertEquals(10,livro.getEstoque());
        assertEquals("Trama",livro.getCategoria());
    }

    @Test
    public void testarAcaoDeVenderUmLivro(){
        livro.vender(10);

        assertEquals(0,livro.getEstoque());

        System.out.println("Estoque atual: "+ livro.getEstoque());
    }

    @Test
    public void testarAcaoDeReporEstoqueDoLivro(){
        livro.reporEstoque(10);

        assertEquals(20,livro.getEstoque());

        System.out.println("Estoque atual: "+ livro.getEstoque());
    }

    @Test
    public void testarBloqueioDeVendaAcimaDoEstoque(){
        assertThrows(IllegalArgumentException.class, () -> {
            livro.vender(15);
        });
    }
}
