
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/*
 * Classe principal: constrói o índice a partir dos arquivos de uma pasta
 * e abre um loop de consultas no terminal, conforme o exemplo de
 * interface da seção 9 do enunciado.
 *
 * Responsável: Pessoa 3
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("========================================");
        System.out.println("           INDICE INVERTIDO");
        System.out.println("========================================");

        // Caminho da pasta com os arquivos de entrada.
        // Pode ser passado como argumento: java Main caminho/da/pasta
        File pastaArquivos;
            if (args.length > 0) {
                pastaArquivos = new File(args[0]);
            } else {
                pastaArquivos = new File("entrada");
            }

        if (!pastaArquivos.exists() || !pastaArquivos.isDirectory()) {
            System.out.println("Pasta de arquivos não encontrada: " + pastaArquivos.getAbsolutePath());
            System.out.println("Crie a pasta 'entrada' com arquivos .txt ou passe o caminho como argumento.");
            return;
        }

        ArvoreDigitalTernaria arvore = new ArvoreDigitalTernaria();

        System.out.println("Construindo indice...");
        Indexador.construirIndice(pastaArquivos, arvore);

        // Registra cada arquivo indexado no ProcessadorConsulta
        // (necessário para o operador NAO calcular o complemento corretamente)
        File[] arquivos = pastaArquivos.listFiles();
        int qtd = 0;
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                    ProcessadorConsulta.registrarArquivo(arquivo.getName());
                    qtd++;
                }
            }
        }
        System.out.println("Arquivos encontrados: " + qtd);
        System.out.println("Indice construido com sucesso.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Digite uma consulta:");
            System.out.print("> ");
            String consulta = scanner.nextLine();

            if (consulta.trim().equalsIgnoreCase("sair")) {
                break;
            }

            if (consulta.trim().isEmpty()) {
                continue;
            }

            Set<String> resultado = ProcessadorConsulta.processarConsulta(consulta, arvore);
            imprimirResultados(resultado);
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }

    /*
     * Imprime a lista de arquivos encontrados, um por linha, ou uma
     * mensagem indicando que nenhum arquivo foi encontrado.
     *
     * parametro arquivos conjunto de arquivos retornado pela consulta
     */
    private static void imprimirResultados(Set<String> arquivos) {
        System.out.println();
        if (arquivos == null || arquivos.isEmpty()) {
            System.out.println("Nenhum arquivo encontrado.");
        } else {
            System.out.println("Arquivos encontrados:");
            // Ordena para saída previsível e organizada
            arquivos.stream().sorted().forEach(System.out::println);
        }
    }
}
