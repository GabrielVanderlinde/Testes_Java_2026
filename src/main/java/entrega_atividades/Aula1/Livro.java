package entrega_atividades.Aula1;

public class Livro {
    private String titulo;
    private Double precoBase;
    private Integer estoque;
    private String categoria;

    public Livro(String titulo, Double precoBase, Integer estoque, String categoria) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio");
        }

        if (precoBase == null || precoBase <= 0) {
            throw new IllegalArgumentException("O preço inicial deve ser maior que zero");
        }

        if (estoque == null || estoque < 0) {
            throw new IllegalArgumentException("O estoque inicial não pode ser negativo");
        }

        if (categoria == null || categoria.isEmpty()){
            throw new IllegalArgumentException("A categoria não pode estar vazia");
        }

        this.titulo = titulo;
        this.precoBase = precoBase;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public void vender(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade da venda deve ser maior que zero");
        }

        if (quantidade > this.estoque) {
            throw new IllegalArgumentException("Estoque insuficiente para venda");
        }

        this.estoque -= quantidade;
    }

    public void reporEstoque(Integer quantidade){
        if (quantidade == null || quantidade<=0){
            throw new IllegalArgumentException("A quantidade de reposição deve ser maior que zero");
        }

        this.estoque+=quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public String getCategoria() {
        return categoria;
    }
}
