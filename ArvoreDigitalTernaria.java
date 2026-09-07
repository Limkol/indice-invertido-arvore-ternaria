import java.util.ArrayList;
import java.util.List;

/*
 * Árvore Digital Ternária (Ternary Search Tree).
 *
 * Implementa a inserção de palavras associadas a arquivos, e a busca de
 * quais arquivos contêm uma determinada palavra.
 *
 * Lógica de navegação (ver seção 6.1 do enunciado):
 * - Compara a letra atual da palavra com o caractere do nó atual.
 * - Se for menor: desce pela esquerda (sem avançar de letra).
 * - Se for maior: desce pela direita (sem avançar de letra).
 * - Se for igual: avança para a próxima letra e desce pelo meio.
 *
 * Responsável: Pessoa 1
 */
public class ArvoreDigitalTernaria {

    private No raiz;

    public ArvoreDigitalTernaria() {
        this.raiz = null;
    }

    /*
     * Insere uma palavra (já normalizada) na árvore, associando o arquivo
     * informado ao nó final dessa palavra.
     *
     * parametro palavra     palavra normalizada a ser inserida
     * parametro nomeArquivo nome do arquivo em que a palavra aparece
     */
    public void inserir(String palavra, String nomeArquivo) {
        // TODO: chamar inserirRecursivo a partir da raiz e reatribuir o
        // resultado a "raiz" (o método recursivo cria a raiz se ela for nula).
        raiz = inserirRecursivo(raiz, palavra, 0, nomeArquivo);
    }

    /*
     * Insere a palavra caractere a caractere, criando nós conforme
     * necessário.
     *
     * parametro no          nó atual da recursão (pode ser null)
     * parametro palavra     palavra sendo inserida
     * parametro indice      índice do caractere atual dentro da palavra
     * parametro nomeArquivo nome do arquivo associado à palavra
     * @return o nó atualizado (para ser religado pelo nível acima da
     *         recursão)
     */
    private No inserirRecursivo(No no, String palavra, int indice, String nomeArquivo) {
        // TODO:
        // 1. Se "no" for null, criar um novo No(palavra.charAt(indice)).
        // 2. Comparar palavra.charAt(indice) com no.caractere:
        //    - menor  -> no.esquerda = inserirRecursivo(no.esquerda, palavra, indice, nomeArquivo);
        //    - maior  -> no.direita  = inserirRecursivo(no.direita, palavra, indice, nomeArquivo);
        //    - igual  -> se indice for o último caractere da palavra:
        //                  marcar no.fimDaPalavra = true
        //                  adicionar nomeArquivo em no.arquivosAssociados (evitando duplicata)
        //                senão:
        //                  no.meio = inserirRecursivo(no.meio, palavra, indice + 1, nomeArquivo);
        // 3. Retornar "no".
        return no;
    }

    /*
     * Busca uma palavra (já normalizada) na árvore.
     *
     * parametro palavra palavra normalizada a ser buscada
     * @return lista de arquivos em que a palavra aparece, ou uma lista
     *         vazia caso a palavra não exista no índice
     */
    public List<String> buscar(String palavra) {
        // TODO: chamar buscarNo a partir da raiz; se o nó retornado for
        // null ou fimDaPalavra for falso, retornar new ArrayList<>();
        // caso contrário, retornar no.arquivosAssociados.
        No no = buscarNo(raiz, palavra, 0);
        if (no == null || !no.fimDaPalavra) {
            return new ArrayList<>();
        }
        return no.arquivosAssociados;
    }

    /*
     * Percorre a árvore procurando o nó final de uma palavra, sem criar
     * novos nós.
     *
     * parametro no      nó atual da recursão
     * parametro palavra palavra sendo buscada
     * parametro indice  índice do caractere atual dentro da palavra
     * @return o nó final da palavra, ou null se o caminho for interrompido
     *         antes do fim da palavra
     */
    private No buscarNo(No no, String palavra, int indice) {
        // TODO:
        // 1. Se "no" for null, retornar null.
        // 2. Comparar palavra.charAt(indice) com no.caractere:
        //    - menor -> return buscarNo(no.esquerda, palavra, indice);
        //    - maior -> return buscarNo(no.direita, palavra, indice);
        //    - igual -> se indice for o último caractere, retornar "no";
        //               senão, return buscarNo(no.meio, palavra, indice + 1);
        return null;
    }
}
