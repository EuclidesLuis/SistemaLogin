import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GerenciadorLogin {
    protected static final String ARQUIVO_MEDICOS = "dados/medicos.txt";
    protected static final String ARQUIVO_PACIENTES = "dados/pacientes.txt";

    public Usuario verificarCredenciais(String identificador, String senha) {
        try {
            File file = new File(ARQUIVO_MEDICOS);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 4 && dados[0].equals(identificador)) {
                    String id = dados[0].trim();
                    String nome = dados[1].trim();
                    String senhaArquivo = dados[2].trim();
                    String especialidade = dados[3].trim();

                    if (senhaArquivo.equals(senha)) {
                        Medico medico = new Medico(id, nome, senhaArquivo, especialidade);

                        if (dados.length > 4) {
                            String[] planos = dados[4].split(",");
                            for (String plano : planos) {
                                medico.adicionarPlanoSaude(plano.trim());
                            }
                        }
                        br.close();
                        return medico;
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro no arquivo de médicos: " + e.getMessage());
            System.err.println("Caminho tentado: " + new File(ARQUIVO_MEDICOS).getAbsolutePath());
        }

        try {
            File file = new File(ARQUIVO_PACIENTES);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                String[] dados = linha.split(";");
                if (dados.length >= 4 && dados[0].equals(identificador)) {
                    String nome = dados[0].trim();
                    String senhaArquivo = dados[1].trim();
                    String idadeStr = dados[2].trim();
                    String plano = dados[3].trim();

                    if (senhaArquivo.equals(senha)) {
                        try {
                            int idade = Integer.parseInt(idadeStr);
                            br.close();
                            return new Paciente(nome, senhaArquivo, idade, plano);
                        } catch (NumberFormatException e) {
                            System.err.println("Erro na idade do paciente: " + linha);
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro no arquivo de pacientes: " + e.getMessage());
            System.err.println("Caminho tentado: " + new File(ARQUIVO_PACIENTES).getAbsolutePath());
        }

        return null;
    }
    }
