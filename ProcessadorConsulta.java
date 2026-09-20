import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessadorConsulta {
    private static Set<String> universoDeArquivos = new HashSet<>();

    /*
    * Registra um arquivo no universo de arquivos indexados.
    */
    public static void registrarArquivo(String nomeArquivo) {
        universoDeArquivos.add(nomeArquivo);
    }

    /*
    * Processa uma consulta com operadores E, OU e NAO.
    * Respeita precedência: NAO > E > OU
    */
    public static Set<String> processarConsulta(String consulta, ArvoreDigitalTernaria arvore) {
        if (consulta == null || consulta.trim().isEmpty()) {
            return new HashSet<>();
        }

        if (!validarConsulta(consulta)) {
            System.out.println("Consulta malformada.");
            return new HashSet<>();
        }

        List<String> tokens = tokenizar(consulta);

        if (tokens.isEmpty()) {
            return new HashSet<>();
        }

        int[] posicao = {0};

        return avaliarOU(tokens, posicao, arvore);
    }

    /*
    * Tokeniza a consulta em palavras e operadores.
    * Normaliza palavras e valida operadores (E, OU, NAO).
    */
    private static List<String> tokenizar(String consulta) {
        List<String> tokens = new ArrayList<>();
        String[] partes = consulta.trim().split("\\s+");
        for (String parte : partes) {
            if (parte.isEmpty()) {
                continue;
            }
            String upper = parte.toUpperCase();
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
    * Avalia a precedência do operador OU (menor precedência).
    * Combina resultados com união (addAll).
    */
    private static Set<String> avaliarOU(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        Set<String> resultado = avaliarE(tokens, posicao, arvore);

        while (posicao[0] < tokens.size() && tokens.get(posicao[0]).equals("OU")) {
            posicao[0]++;
            Set<String> direito = avaliarE(tokens, posicao, arvore);
            resultado.addAll(direito);
        }
        return resultado;
    }

    /*
    * Avalia a precedência do operador E (precedência média).
    * Combina resultados com interseção (retainAll).
    */
    private static Set<String> avaliarE(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        Set<String> resultado = avaliarNao(tokens, posicao, arvore);

        while (posicao[0] < tokens.size() && tokens.get(posicao[0]).equals("E")) {
            posicao[0]++; 
            Set<String> direito = avaliarNao(tokens, posicao, arvore);
            resultado.retainAll(direito); 
        }
        return resultado;
    }

    /*
    * Avalia a precedência do operador NAO (maior precedência).
    * Retorna complemento: todos arquivos menos aqueles que contêm a palavra.
    */
    private static Set<String> avaliarNao(List<String> tokens, int[] posicao, ArvoreDigitalTernaria arvore) {
        if (posicao[0] >= tokens.size()) {
            return new HashSet<>();
        }

        String token = tokens.get(posicao[0]);

        if (token.equals("NAO")) {
            posicao[0]++;
            if (posicao[0] >= tokens.size()) {
                return new HashSet<>();
            }
            String palavra = tokens.get(posicao[0]);
            posicao[0]++;

            List<String> contemPalavra = arvore.buscar(palavra);
            Set<String> complemento = new HashSet<>(universoDeArquivos);
            complemento.removeAll(contemPalavra);
            return complemento;
        } else {
            posicao[0]++;
            List<String> arquivos = arvore.buscar(token);
            return new HashSet<>(arquivos);
        }
    }

    /*
    * Valida se a consulta possui sintaxe correta.
    * Rejeita operadores duplicados ou mal posicionados.
    */
    private static boolean validarConsulta(String consulta) {
        String[] tokens = consulta.trim().split("\\s+");
        for (int i = 0; i < tokens.length; i++) {
            String upper = tokens[i].toUpperCase();
            if ((upper.equals("E") || upper.equals("OU") || upper.equals("NAO")) && i == tokens.length - 1) return false;
            if ((upper.equals("E") || upper.equals("OU")) && i > 0) {
                String prev = tokens[i-1].toUpperCase();
                if (prev.equals("E") || prev.equals("OU") || prev.equals("NAO")) return false;
            }
        }
        return true;
    }
}
