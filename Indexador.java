import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/*
 * Responsável por ler os arquivos de texto de uma pasta e alimentar a
 * Árvore Digital Ternária com as palavras encontradas em cada um.
 *
 * Responsável: Pessoa 2
 */
public class Indexador {

    /*
     * Constrói o índice invertido a partir de todos os arquivos de texto
     * dentro de uma pasta.
     *
     * parametro pastaArquivos pasta contendo os arquivos .txt a serem indexados
     * parametro arvore        árvore onde as palavras serão inseridas
     * @throws IOException se houver erro na leitura de algum arquivo
     */
    public static void construirIndice(File pastaArquivos, ArvoreDigitalTernaria arvore) throws IOException {
        // TODO:
        // 1. Listar os arquivos da pasta: pastaArquivos.listFiles()
        // 2. Para cada arquivo (filtrando só .txt, se necessário),
        //    chamar lerEIndexarArquivo(arquivo, arvore).
        File[] arquivos = pastaArquivos.listFiles();
        if (arquivos == null) {
            return;
        }
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                lerEIndexarArquivo(arquivo, arvore);
            }
        }
    }

    /*
     * Lê o conteúdo de um único arquivo, normaliza suas palavras e as
     * insere na árvore, associadas ao nome desse arquivo.
     *
     * parametro arquivo arquivo de texto a ser lido
     * parametro arvore  árvore onde as palavras serão inseridas
     * @throws IOException se houver erro na leitura do arquivo
     */
    private static void lerEIndexarArquivo(File arquivo, ArvoreDigitalTernaria arvore) throws IOException {
        // TODO:
        // 1. String conteudo = Files.readString(arquivo.toPath());
        // 2. List<String> palavras = Normalizador.extrairPalavras(conteudo);
        // 3. Para cada palavra, chamar arvore.inserir(palavra, arquivo.getName());
    }
}
