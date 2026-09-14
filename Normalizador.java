import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Normalizador {
    public static String normalizar(String palavra) {
        palavra = palavra.toLowerCase();
        palavra = palavra.replaceAll("^[\\p{Punct}]+|[\\p{Punct}]+$", "");
        return palavra;
    }

    public static List<String> extrairPalavras(String texto) {
        List<String> palavras = new ArrayList<>();

            String[] partes = texto.split("\\s+");

            for (String parte : partes) {
                String palavra = normalizar(parte);
                
                if (!palavra.isEmpty()) {
                    palavras.add(palavra);
                }
            }

        return palavras;
    }
}
