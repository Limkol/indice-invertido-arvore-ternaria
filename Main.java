// Projeto: Busca de palavras em textos
// Disciplina: Estrutura de Dados II
// Alunos
// Nome: Gustavo Francisco Toito RA: 10438660
// Nome: Guilherme Longo RA: 10736785
// Nome: Helen Teixeira RA: 10742524

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    /*
    * Ponto de entrada do programa. Constrói o índice e abre loop de consultas.
    */
    public static void main(String[] args) throws IOException {
        System.out.println("========================================");
        System.out.println("           INDICE INVERTIDO");
        System.out.println("========================================");

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

        Indexador.registrarArquivos(pastaArquivos);
        File[] arquivos = pastaArquivos.listFiles();
        int qtd = (arquivos != null) ? (int) java.util.Arrays.stream(arquivos).filter(f -> f.isFile() && f.getName().endsWith(".txt")).count() : 0;
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
    * Imprime os arquivos encontrados de forma organizada e ordenada.
    */
    private static void imprimirResultados(Set<String> arquivos) {
        System.out.println();
        if (arquivos == null || arquivos.isEmpty()) {
            System.out.println("Nenhum arquivo encontrado.");
        } else {
            System.out.println("Arquivos encontrados:");
            arquivos.stream().sorted().forEach(System.out::println);
        }
    }
}
