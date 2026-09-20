import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Indexador {

    /*
    * Constrói o índice a partir de todos os arquivos .txt da pasta.
    * Lê cada arquivo, extrai palavras, normaliza e insere na árvore.
    */
    public static void construirIndice(File pastaArquivos, ArvoreDigitalTernaria arvore) throws IOException {
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
     Lê um arquivo .txt, extrai suas palavras e as insere na árvore.
    */
    private static void lerEIndexarArquivo(File arquivo, ArvoreDigitalTernaria arvore) throws IOException {
        String conteudo = Files.readString(arquivo.toPath());
        List<String> palavras = Normalizador.extrairPalavras(conteudo);

        for (String palavra : palavras) {
            arvore.inserir(palavra, arquivo.getName());
        }
    }

    /*
    * Registra todos os arquivos .txt da pasta no ProcessadorConsulta.
    */
    public static void registrarArquivos(File pastaArquivos) {
        File[] arquivos = pastaArquivos.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                    ProcessadorConsulta.registrarArquivo(arquivo.getName());
                }
            }
        }
    }
}
