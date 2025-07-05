import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SistemaLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorLogin sistema = new GerenciadorLogin();

        System.out.println("=== Sistema de Login Médico ===");
        System.out.println("Diretório atual: " + System.getProperty("user.dir"));
        System.out.println("Caminho médicos: " + new File(GerenciadorLogin.ARQUIVO_MEDICOS).getAbsolutePath());
        System.out.println("Caminho pacientes: " + new File(GerenciadorLogin.ARQUIVO_PACIENTES).getAbsolutePath());
        System.out.println("===============================");

        File arqMedicos = new File(GerenciadorLogin.ARQUIVO_MEDICOS);
        File arqPacientes = new File(GerenciadorLogin.ARQUIVO_PACIENTES);

        if (!arqMedicos.exists()) {
            System.err.println("ERRO: Arquivo de médicos não encontrado!");
            System.err.println("Local esperado: " + arqMedicos.getAbsolutePath());

            criarArquivosExemplo();
        }

        if (!arqPacientes.exists()) {
            System.err.println("ERRO: Arquivo de pacientes não encontrado!");
            System.err.println("Local esperado: " + arqPacientes.getAbsolutePath());

            criarArquivosExemplo();
        }

        while (true) {
            System.out.println("\n[OPÇÕES]");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Sair");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("2")) {
                System.out.println("Encerrando sistema...");
                break;
            }

            if (opcao.equals("1")) {
                System.out.print("\nIdentificação (ID médico ou Nome paciente): ");
                String identificador = scanner.nextLine();

                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                Usuario usuario = sistema.verificarCredenciais(identificador, senha);

                if (usuario != null) {
                    System.out.println("\n>> Login realizado com sucesso! <<");
                    usuario.exibirInformacoes();
                } else {
                    System.out.println("\n>> Credenciais inválidas! <<");
                }
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void criarArquivosExemplo() {
        try {
            new File("dados").mkdirs();
            
            java.io.FileWriter medicoWriter = new java.io.FileWriter("dados/medicos.txt");
            medicoWriter.write("MED-001;Dr. Silva;cardio123;Cardiologia;Amil, Bradesco Saúde\n");
            medicoWriter.write("MED-002;Dra. Costa;orto456;Ortopedia;SulAmérica\n");
            medicoWriter.close();

            java.io.FileWriter pacienteWriter = new java.io.FileWriter("dados/pacientes.txt");
            pacienteWriter.write("Carlos;senha789;35;Amil\n");
            pacienteWriter.write("Ana;abc123;28;\n");
            pacienteWriter.write("Pedro;pedro2024;42;Unimed\n");
            pacienteWriter.close();

            System.out.println("\nArquivos de exemplo criados com sucesso em dados/!");
            System.out.println("Você pode usar as seguintes credenciais para testar:");
            System.out.println("- Médico: ID: MED-001, Senha: cardio123");
            System.out.println("- Paciente: Nome: Ana, Senha: abc123");
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivos: " + e.getMessage());
            System.err.println("Por favor, crie manualmente a pasta 'dados' e os arquivos dentro dela.");
        }
    }
    }
