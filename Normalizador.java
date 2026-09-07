import java.util.ArrayList;
import java.util.List;

/*
 * Classe utilitária responsável por padronizar palavras, garantindo que
 * "Venda", "VENDA" e "venda," sejam todas tratadas como "venda".
 *
 * Usada tanto na indexação (Indexador) quanto no processamento de
 * consultas (ProcessadorConsulta) — a normalização precisa ser idêntica
 * nos dois casos, senão a busca nunca vai encontrar as palavras indexadas.
 *
 * Responsável: Pessoa 2
 */
public class Normalizador {

    /*
     * Normaliza uma única palavra: converte para minúsculas e remove
     * pontuação do início e do fim (vírgula, ponto, ponto e vírgula, etc.).
     *
     * parametro palavra palavra bruta, como veio do texto original
     * @return palavra normalizada (pode ser string vazia se a palavra era
     *         composta só de pontuação)
     */
    public static String normalizar(String palavra) {
        // TODO:
        // 1. palavra = palavra.toLowerCase();
        // 2. Remover pontuação do início/fim, por exemplo com:
        //    palavra.replaceAll("^[\\p{Punct}]+|[\\p{Punct}]+$", "");
        // 3. Retornar o resultado (usar .trim() se necessário).
        return palavra;
    }

    /*
     * Divide um texto bruto em uma lista de palavras já normalizadas,
     * descartando strings vazias resultantes da normalização.
     *
     * parametro texto conteúdo bruto de um arquivo de texto
     * @return lista de palavras normalizadas, prontas para indexação
     */
    public static List<String> extrairPalavras(String texto) {
        List<String> palavras = new ArrayList<>();
        // TODO:
        // 1. Separar o texto em tokens brutos, ex.: texto.split("\\s+")
        // 2. Para cada token, aplicar normalizar(token).
        // 3. Se o resultado não for vazio, adicionar em "palavras".
        return palavras;
    }
}
