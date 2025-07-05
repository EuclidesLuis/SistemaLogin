abstract class Usuario {
    protected String nome;
    protected String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public abstract void exibirInformacoes();
}
