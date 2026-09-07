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

        // TODO: ajustar o caminho da pasta com os arquivos de entrada.
        File pastaArquivos = new File("entrada");

        ArvoreDigitalTernaria arvore = new ArvoreDigitalTernaria();

        System.out.println("Construindo indice...");
        // TODO: chamar Indexador.construirIndice(pastaArquivos, arvore);
        // TODO: registrar cada arquivo indexado em
        // ProcessadorConsulta.registrarArquivo(nomeArquivo) (necessário
        // para o operador NAO funcionar corretamente).
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

            // TODO: Set<String> resultado = ProcessadorConsulta.processarConsulta(consulta, arvore);
            Set<String> resultado = null;
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
        // TODO:
        // se arquivos for null ou vazio, imprimir algo como
        // "Nenhum arquivo encontrado.";
        // senão, imprimir "Arquivos encontrados:" seguido de um nome de
        // arquivo por linha.
        System.out.println("Arquivos encontrados:");
    }
}
