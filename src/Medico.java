import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {
    private String id;
    private String especialidade;
    private List<String> planosSaude;

    public Medico(String id, String nome, String senha, String especialidade) {
        super(nome, senha);
        this.id = id;
        this.especialidade = especialidade;
        this.planosSaude = new ArrayList<>();
    }

    public void adicionarPlanoSaude(String plano) {
        planosSaude.add(plano);
    }

    public String getId() {
        return id;
    }

    public void exibirInformacoes() {
        System.out.println("\nBem-vindo Dr(a). " + nome);
        System.out.println("ID: " + id);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Planos atendidos: " + planosSaude);
    }

}
