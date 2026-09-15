import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Interpreta consultas com os operadores E, OU e NAO, respeitando a
 * precedência NAO > E > OU (seção 8 do enunciado), e combina os
 * resultados obtidos da Árvore Digital Ternária usando operações de
 * conjunto (interseção, união e diferença).
 *
 * Nota: HashSet aqui é usado apenas como estrutura AUXILIAR para combinar
 * resultados de busca — a estrutura PRINCIPAL do índice continua sendo a
 * ArvoreDigitalTernaria. O enunciado permite estruturas auxiliares quando
 * justificadas (seção 11).
 *
 * Responsável: Pessoa 3
 */
public class ProcessadorConsulta {

    /* Conjunto com todos os nomes de arquivo já indexados (usado no NAO). */
    private static Set<String> universoDeArquivos = new HashSet<>();

    /*
     * Deve ser chamado pelo Indexador (ou pelo Main) para registrar todos
     * os arquivos indexados, permitindo calcular o complemento em consultas
     * com NAO.
     *
     * parametro nomeArquivo nome do arquivo a ser registrado no universo
     */
    public static void registrarArquivo(String nomeArquivo) {
        universoDeArquivos.add(nomeArquivo);
    }

    /*
     * Ponto de entrada principal. Interpreta a consulta completa e
     * retorna o conjunto de arquivos que a satisfazem.
     *
     * parametro consulta string digitada pelo usuário, ex.: "contrato E
     *                 responsabilidade NAO multa"
     * parametro arvore   árvore já construída, usada para buscar cada palavra
     * @return conjunto de nomes de arquivo que atendem à consulta
     */
    public static Set<String> processarConsulta(String consulta, ArvoreDigitalTernaria arvore) {
        if (consulta == null || consulta.trim().isEmpty()) {
            return new HashSet<>();
        }
        List<String> tokens = tokenizar(consulta);
        if (tokens.isEmpty()) {
            return new HashSet<>();
        }
        int[] posicao = {0};
        // OU tem a menor precedência, então ele "engloba" tudo.
        return avaliarOU(tokens, posicao, arvore);
    }

    /*
     * Separa a consulta em uma lista de tokens (palavras e operadores).
     *
     * parametro consulta string bruta digitada pelo usuário
     * @return lista de tokens, ex.: ["contrato", "E", "responsabilidade"]
     */
    private static List<String> tokenizar(String consulta) {
        List<String> tokens = new ArrayList<>();
        String[] partes = consulta.trim().split("\\s+");
        for (String parte : partes) {
            if (parte.isEmpty()) {
                continue;
            }
            String upper = parte.toUpperCase();
            // Operadores ficam em maiúsculas; palavras são normalizadas
            if (upper.equals("E") || upper.equals("OU") || upper.equals("NAO")) {
                tokens.add(upper);
            } else {
                String normalizada = Normalizador.normalizar(parte);
                if (!normalizada.isEmpty()) {
                    tokens.add(normalizada);
                }
            }
        }
        return tokens;
    }

    /*
     * Resolve o nível de precedência do OU: encadeia chamadas a
     * avaliarE(...) fazendo união sempre que encontrar o token "OU".
     *
     * parametro tokens   lista de tokens da consulta
     * parametro posicao  posição atual dentro de tokens (array de 1 posição,
     *                 usado para simular passagem por referência)
     * parametro arvore   árvore usada para buscar cada palavra
     * @return conjunto resultante da avaliação
     */
    private static Set<String> avaliarOU(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        Set<String> resultado = avaliarE(tokens, posicao, arvore);

        while (posicao[0] < tokens.size() && tokens.get(posicao[0]).equals("OU")) {
            posicao[0]++; // pular o "OU"
            Set<String> direito = avaliarE(tokens, posicao, arvore);
            resultado.addAll(direito); // união
        }
        return resultado;
    }

    /*
     * Resolve o nível de precedência do E: encadeia chamadas a
     * avaliarNao(...) fazendo interseção sempre que encontrar o token "E".
     *
     * parametro tokens  lista de tokens da consulta
     * parametro posicao posição atual dentro de tokens
     * parametro arvore  árvore usada para buscar cada palavra
     * @return conjunto resultante da avaliação
     */
    private static Set<String> avaliarE(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        Set<String> resultado = avaliarNao(tokens, posicao, arvore);

        while (posicao[0] < tokens.size() && tokens.get(posicao[0]).equals("E")) {
            posicao[0]++; // pular o "E"
            Set<String> direito = avaliarNao(tokens, posicao, arvore);
            resultado.retainAll(direito); // interseção
        }
        return resultado;
    }

    /*
     * Resolve o nível de maior precedência (NAO) ou busca simples de uma
     * palavra na árvore.
     *
     * parametro tokens  lista de tokens da consulta
     * parametro posicao posição atual dentro de tokens
     * parametro arvore  árvore usada para buscar cada palavra
     * @return conjunto resultante da avaliação
     */
    private static Set<String> avaliarNao(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        if (posicao[0] >= tokens.size()) {
            return new HashSet<>();
        }

        String token = tokens.get(posicao[0]);

        if (token.equals("NAO")) {
            posicao[0]++; // pular o "NAO"
            if (posicao[0] >= tokens.size()) {
                return new HashSet<>();
            }
            String palavra = tokens.get(posicao[0]);
            posicao[0]++; // pular a palavra

            // Complemento: universo − arquivos que contêm a palavra
            List<String> contemPalavra = arvore.buscar(palavra);
            Set<String> complemento = new HashSet<>(universoDeArquivos);
            complemento.removeAll(contemPalavra);
            return complemento;
        } else {
            // Token é uma palavra simples
            posicao[0]++; // pular a palavra
            List<String> arquivos = arvore.buscar(token);
            return new HashSet<>(arquivos);
        }
    }
}
