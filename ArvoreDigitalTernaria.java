import java.util.ArrayList;
import java.util.List;

public class ArvoreDigitalTernaria {

    private No raiz;

    public ArvoreDigitalTernaria() {
        this.raiz = null;
    }

    public void inserir(String palavra, String nomeArquivo) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }
        raiz = inserirRecursivo(raiz, palavra, 0, nomeArquivo);
    }

    private No inserirRecursivo(No no, String palavra, int indice, String nomeArquivo) {
        char caractereAtual = palavra.charAt(indice);
        if (no == null) {
            no = new No(caractereAtual);
        }
        if (caractereAtual < no.caractere) {
            no.esquerda = inserirRecursivo(no.esquerda, palavra, indice, nomeArquivo);
        } else if (caractereAtual > no.caractere) {
            no.direita = inserirRecursivo(no.direita, palavra, indice, nomeArquivo);
        } else {
            if (indice == palavra.length() - 1) {
                no.fimDaPalavra = true;
                if (!no.arquivosAssociados.contains(nomeArquivo)) {
                    no.arquivosAssociados.add(nomeArquivo);
                }
            } else {
                no.meio = inserirRecursivo(no.meio, palavra, indice + 1, nomeArquivo);
            }
        }
        return no;
    }

    public List<String> buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return new ArrayList<>();
        }

        No no = buscarNo(raiz, palavra, 0);

        if (no == null || !no.fimDaPalavra) {
            return new ArrayList<>();
        }
        return no.arquivosAssociados;
    }

    private No buscarNo(No no, String palavra, int indice) {
        if (no == null) {
            return null;
        }

        char caractereAtual = palavra.charAt(indice);
 
        if (caractereAtual < no.caractere) {
            return buscarNo(no.esquerda, palavra, indice);
        } else if (caractereAtual > no.caractere) {
            return buscarNo(no.direita, palavra, indice);
        } else {
            if (indice == palavra.length() - 1) {
                return no;
            }

            return buscarNo(no.meio, palavra, indice + 1);
        }
    }
}
