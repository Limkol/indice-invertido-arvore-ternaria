import java.util.ArrayList;
import java.util.List;

public class No {

    char caractere;
    No esquerda;
    No meio;
    No direita;
    boolean fimDaPalavra;
    List<String> arquivosAssociados;

    /*
    * Construtor do nó. Inicializa caractere e deixa ponteiros como null.
    */
    public No(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.meio = null;
        this.direita = null;
        this.fimDaPalavra = false;
        this.arquivosAssociados = new ArrayList<>();
    }
}