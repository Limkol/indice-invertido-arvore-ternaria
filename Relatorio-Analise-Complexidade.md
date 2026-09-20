# RELATÓRIO — ÍNDICE INVERTIDO COM ÁRVORE TERNÁRIA

## 1. Introdução

Sistema de busca de palavras em documentos utilizando um **Índice Invertido com Árvore Digital Ternária**, desenvolvido para a disciplina **Projeto e Análise de Algoritmos II (Mackenzie)**.

O sistema permite realizar consultas simples e complexas utilizando os operadores lógicos **E**, **OU** e **NAO**.

## 2. Estrutura de Dados

### Árvore Digital Ternária (Trie)

Cada nó da árvore contém:

- um caractere;
- três ponteiros: **esquerda**, **meio** e **direita**;
- uma flag `fimDaPalavra`;
- uma lista de arquivos nos quais a palavra aparece.

**Organização:** cada nó armazena um caractere da palavra. O ponteiro do meio é utilizado para avançar para o próximo caractere da palavra, enquanto os ponteiros esquerdo e direito são utilizados para navegar entre caracteres diferentes.

### Índice Invertido

O índice representa, de forma implícita, a relação:

`palavra → lista de arquivos`

Assim, cada palavra armazenada na árvore possui uma lista com os arquivos em que ela foi encontrada.

## 3. Análise de Complexidade

### 3.1 Operação: `INSERIR(palavra, nomeArquivo)`

- **Tempo:** `O(m × log k)`
- **Espaço:** `O(m)`

Onde:

- `m` = comprimento da palavra;
- `k` = número de caracteres distintos considerados durante a busca na árvore.

**Análise:** a operação percorre os `m` caracteres da palavra. Para cada caractere, é necessário navegar pelos ponteiros esquerdo, meio e direito até encontrar a posição correspondente.

Na prática, como cada nó possui apenas três possibilidades de navegação, o custo associado à busca entre os caracteres é limitado. Por isso, a operação pode se aproximar de `O(m)` em determinadas situações.

### 3.2 Operação: `BUSCAR(palavra)`

- **Tempo:** `O(m × log k)`
- **Espaço:** `O(1)`

A busca utiliza a mesma lógica da inserção, mas apenas realiza a leitura da estrutura.

Ao encontrar o nó que representa o último caractere da palavra, a operação retorna a lista de arquivos associados a ela.

### 3.3 Operador `E` (Interseção)

- **Tempo:** `O(|R1| + |R2|)`
- **Espaço:** `O(min(|R1|, |R2|))`

Onde `|R1|` e `|R2|` representam o tamanho dos resultados das duas buscas.

**Implementação:** utiliza `retainAll()`, que mantém apenas os elementos presentes nas duas listas.

### 3.4 Operador `OU` (União)

- **Tempo:** `O(|R1| + |R2|)`
- **Espaço:** `O(|R1| + |R2|)`

**Implementação:** utiliza `addAll()` para adicionar os elementos de `R2` ao resultado de `R1`.

### 3.5 Operador `NAO` (Complemento)

- **Tempo:** `O(|Universo| + |R|)`
- **Espaço:** `O(|Universo|)`

Onde:

- `|Universo|` = número total de arquivos indexados;
- `|R|` = quantidade de arquivos que contêm a palavra pesquisada.

**Implementação:** cria uma cópia do conjunto de todos os arquivos indexados e remove aqueles que contêm a palavra pesquisada.

### 3.6 Construção do Índice

- **Tempo:** `O(n × m × log k)`
- **Espaço:** `O(m × n)`

Onde:

- `n` = número total de palavras processadas em todos os arquivos;
- `m` = comprimento médio das palavras.

A construção do índice inclui a leitura dos arquivos, a normalização das palavras e a inserção de cada palavra na árvore.

### 3.7 Processamento de Consulta Complexa

- **Tempo:** `O(q × (m × log k + |R|))`
- **Espaço:** `O(|Universo|)` no pior caso, principalmente devido ao operador `NAO`.

Onde:

- `q` = número de operandos (palavras) presentes na consulta;
- `m` = comprimento das palavras;
- `|R|` = tamanho dos resultados intermediários.

A precedência dos operadores é:

**`NAO` > `E` > `OU`**

Essa ordem não altera a complexidade assintótica, mas garante que a expressão seja avaliada corretamente.

### 3.8 Normalização de Palavras

- **Tempo:** `O(m)`
- **Espaço:** `O(m)`

Processa cada caractere da palavra: converte para minúsculas e remove pontuação das extremidades.

## 4. Componentes

| Classe | Responsabilidade | Complexidade |
|---|---|---|
| `No` | Estrutura do nó, contendo caractere, ponteiros e lista de arquivos | `O(1)` |
| `ArvoreDigitalTernaria` | Inserção e busca de palavras | `O(m × log k)` |
| `Indexador` | Leitura dos arquivos e construção do índice | `O(n × m × log k)` |
| `Normalizador` | Normalização das palavras, como conversão para minúsculas e remoção de pontuação | `O(m)` |
| `ProcessadorConsulta` | Avaliação das expressões lógicas | `O(q × m × log k)` |
| `Main` | Interface e orquestração do sistema | — |
