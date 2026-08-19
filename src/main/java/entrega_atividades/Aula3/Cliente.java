package entrega_atividades.Aula3;

public class Cliente {
    private String nome;
    private String email;
    private boolean ativo;

    public Cliente(String nome, String email) {
        if (nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo");
        }

        if (email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email não pode ser vazio ou nulo");
        }

        this.nome = nome;
        this.email = email;
        this.ativo = true;
    }

    public Cliente() {
    }

    public void desativar(){
        if (!ativo){
            throw new IllegalStateException("Cliente não esta ativo");
        }

        ativo = false;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
