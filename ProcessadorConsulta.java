import java.util.ArrayList;
import java.util.Arrays;
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

public class ProcessadorConsulta{

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
        List<String> tokens = tokenizar(consulta);
        int[] posicao = {0};
        // TODO: OU tem a menor precedência, então ele "engloba" tudo.
        return avaliarOU(tokens, posicao, arvore);
    }

    /*
     * Separa a consulta em uma lista de tokens (palavras e operadores).
     *
     * parametro consulta string bruta digitada pelo usuário
     * @return lista de tokens, ex.: ["contrato", "E", "responsabilidade"]
     */
    private static List<String> tokenizar(String consulta) {
        // TODO: usar consulta.trim().split("\\s+") e normalizar cada
        // palavra (não normalizar os operadores E/OU/NAO).
        return new ArrayList<>(Arrays.asList(consulta.trim().split("\\s+")));
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
        // TODO:
        // Set<String> resultado = avaliarE(tokens, posicao, arvore);
        // enquanto houver token seguinte e ele for "OU":
        //   avançar posicao[0] (pular o "OU")
        //   resultado.addAll(avaliarE(tokens, posicao, arvore));
        // retornar resultado;
        return new HashSet<>();
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
        // TODO:
        // Set<String> resultado = avaliarNao(tokens, posicao, arvore);
        // enquanto houver token seguinte e ele for "E":
        //   avançar posicao[0] (pular o "E")
        //   resultado.retainAll(avaliarNao(tokens, posicao, arvore));
        // retornar resultado;
        return new HashSet<>();
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
        // TODO:
        // se o token atual for "NAO":
        //   avançar posicao[0] (pular o "NAO")
        //   buscar a próxima palavra na árvore
        //   retornar o COMPLEMENTO desse resultado em relação a universoDeArquivos
        // senão:
        //   buscar a palavra atual na árvore (arvore.buscar(palavra))
        //   avançar posicao[0]
        //   retornar o resultado como new HashSet<>(listaDeArquivos)
        return new HashSet<>();
    }
}