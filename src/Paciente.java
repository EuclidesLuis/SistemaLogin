public class Paciente extends Usuario {
    private int idade;
    private String planoSaude;

    public Paciente(String nome, String senha, int idade, String planoSaude) {
        super(nome, senha);
        this.idade = idade;
        this.planoSaude = (planoSaude == null || planoSaude.isBlank()) ? "não tenho" : planoSaude;
    }

    public void exibirInformacoes() {
        System.out.println("\nBem-vindo(a), " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Plano de saúde: " + planoSaude);
    }

}
