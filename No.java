import java.util.ArrayList;
import java.util.List;

public class No {

    char caractere;
    No esquerda;
    No meio;
    No direita;
    boolean fimDaPalavra;
    List<String> arquivosAssociados;

    public No(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.meio = null;
        this.direita = null;
        this.fimDaPalavra = false;
        this.arquivosAssociados = new ArrayList<>();
    }
}