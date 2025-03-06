
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class AppLabirintoBase {

    // Matriz princiapal
    public static char matrizPrincipal [][];

    // Vetor de dicionario dos valores em hexadecimal
    public static char[] vetorHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    // Vetor de dicionario dos valores em binario 
    public static String[] vetorBin = {
        "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
        "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
    };

    // Vetor para simbolos de cada area, nao eh realmente util nesse codigo, eh mais utilizado no outro
    public static char[] vetorSimbolos = {  
                                            '#', '$', '%', '&', '*', '!', '@', '+', '=', '?', '~',
                                            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
                                            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                                            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y' , 'z' 
                                        };

    // Variavel de contagem das areas
    public static int contadorAreas = 0;

    // Variaveis de linhas e colunas da matriz
    public static int linhasTotais;
    public static int colunasTotais;

    // Variavel de contagem de operacoes
    public static int cont = 0;

    // Variaveis de contagem para cada bichinho em específico
    public static int contadorAnoes = 0;
    public static int contadorBruxas = 0;
    public static int contadorCavaleiros = 0;
    public static int contadorDuendes = 0;
    public static int contadorElfos = 0;
    public static int contadorFeijoes = 0;

    // Vetor onde armazenam os valores correspondentes usados para determinar o bichinho mais frequente de cada area
    public static int[] vetorFrequenciaBichinhos;

    // Vetor que armazena valor do bichinho mais frequente de cada area
    public static int[] vetorBichinhoMaisFrequente;

    public static void main(String[] args) throws IOException {

        vetorFrequenciaBichinhos = new int[1000];
        vetorBichinhoMaisFrequente = new int[1000];

        try {
            //File arquivo = new File("casos/caso40_a.txt");
            //File arquivo = new File("casos/caso80_a.txt");
            File arquivo = new File("casos/caso100_a.txt");
            //File arquivo = new File("casos/caso120_a.txt");
            //File arquivo = new File("casos/caso150_a.txt");
            //File arquivo = new File("casos/caso180_a.txt");
            //File arquivo = new File("casos/caso200_a.txt");
            //File arquivo = new File("casos/caso250_a.txt");

            Scanner scanner = new Scanner(arquivo);

            linhasTotais = scanner.nextInt();
            colunasTotais = scanner.nextInt();
            scanner.nextLine(); 

            matrizPrincipal = new char[linhasTotais][colunasTotais];

            // Preenche a matriz com os dados do arquivo
            for (int i = 0; i < linhasTotais; i++) {
                for (int j = 0; j < colunasTotais; j++) {
                    if (scanner.hasNext()) {
                        matrizPrincipal[i][j] = scanner.next().charAt(0);
                    }
                }
            }

            // Percorre a matriz fazendo a recursao do labirinto
            for (int i = 0; i < linhasTotais; i++) {
                for (int j = 0; j < colunasTotais; j++) {
                    for (int k = 0; k < vetorHex.length; k++) {
                        if (Character.toLowerCase(matrizPrincipal[i][j]) == Character.toLowerCase(vetorHex[k])) {
                            percorreLabirinto(matrizPrincipal, i, j);
                            verificaMaiorBichinho();
                            contadorAreas++;
                        }
                        else {
                            continue;
                        }
                    }
                }
            }

            scanner.close();

            System.out.println("\nTeste com o arquivo: " + arquivo);

            System.out.println("\nNumero de areas: " + contadorAreas);

            System.out.print("Bichinho mais frequente em uma area: ");
            imprimePos();

            System.out.println("\nNumero de operacoes: " + cont + "\n");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }

    public static void verificaMaiorBichinho() {

        if (contadorAnoes > contadorBruxas  && contadorAnoes > contadorCavaleiros   && contadorAnoes > contadorDuendes  &&
            contadorAnoes > contadorElfos   && contadorAnoes > contadorFeijoes) {
                vetorFrequenciaBichinhos[contadorAreas] = 0;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorAnoes;
        }
        else if (   contadorBruxas > contadorCavaleiros     && contadorBruxas > contadorDuendes &&
                    contadorBruxas > contadorElfos          && contadorBruxas > contadorFeijoes) {
                vetorFrequenciaBichinhos[contadorAreas] = 1;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorBruxas;
        }
        else if (   contadorCavaleiros > contadorDuendes    && contadorCavaleiros > contadorElfos && contadorCavaleiros > contadorFeijoes) {
                vetorFrequenciaBichinhos[contadorAreas] = 2;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorCavaleiros;
        }
        else if (   contadorDuendes > contadorElfos         && contadorDuendes > contadorFeijoes) {
                vetorFrequenciaBichinhos[contadorAreas] = 3;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorDuendes;
        }
        else if (   contadorElfos > contadorFeijoes) {
                vetorFrequenciaBichinhos[contadorAreas] = 4;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorElfos;
        }
        else {
                vetorFrequenciaBichinhos[contadorAreas] = 5;
                vetorBichinhoMaisFrequente[contadorAreas] = contadorFeijoes;
        }
        contadorAnoes = 0;
        contadorBruxas = 0;
        contadorCavaleiros = 0;
        contadorDuendes = 0;
        contadorElfos = 0;
        contadorFeijoes = 0;
    }

    public static void imprimePos() {
        int maior = vetorBichinhoMaisFrequente[0];
        int pos = 0;
        for(int i = 0; i < contadorAreas; i++) {
            if (vetorBichinhoMaisFrequente[i] > maior) {
                maior = vetorBichinhoMaisFrequente[i];
                pos = i;
            }
        }
        int bichinho = vetorFrequenciaBichinhos[pos];

        switch (bichinho) {
            case 0:
                    System.out.println("A: " + vetorBichinhoMaisFrequente[pos]);
                    break;
                case 1:
                    System.out.println("B: " + vetorBichinhoMaisFrequente[pos]);
                    break;
                case 2:
                    System.out.println("C: " + vetorBichinhoMaisFrequente[pos]);
                    break;
                case 3:
                    System.out.println("D: " + vetorBichinhoMaisFrequente[pos]);
                    break;
                case 4:
                    System.out.println("E: " + vetorBichinhoMaisFrequente[pos]);
                    break;
                case 5:
                    System.out.println("F: " + vetorBichinhoMaisFrequente[pos]);
                    break;
        }
    }

    // Recursao da matriz
    public static void percorreLabirinto(char [][] matriz, int linha, int coluna) {
        
        cont++; // Contador do numero de operacoes
        char atualHex = matriz[linha][coluna];

        // Verifica se o caractere atual eh um bichinho e conta qual especifico
        if(atualHex == 'A') {
            contadorAnoes++;
        } 
        else if(atualHex == 'B') {
            contadorBruxas++;
        }
        else if(atualHex == 'C') {
            contadorCavaleiros++;
        }
        else if(atualHex == 'D') {
            contadorDuendes++;
        }
        else if(atualHex == 'E') {
            contadorElfos++;
        }
        else if(atualHex == 'F') {
            contadorFeijoes++;
        }

        String atualBin = "";

        for (int i = 0; i < vetorHex.length; i++) {
            if (Character.toLowerCase(atualHex) == Character.toLowerCase(vetorHex[i])) {
                atualBin = vetorBin[i];
            }
        }

        matriz[linha][coluna] = vetorSimbolos[contadorAreas % vetorSimbolos.length];

        // Boolean para verificar se o lado do labirinto que sera feita a recursao já nao esta marcado com símbolo
        boolean testeIf = true;

        // Testa se vai para cima
        if (atualBin.charAt(0) == '0') {
            testeIf = true;
            if ((linha - 1) >= 0) { // Verifica se não ultrapassa o topo da matriz
                for (int i = 0; i < vetorSimbolos.length; i++) {
                    if (matriz[linha - 1][coluna] == vetorSimbolos[i]) {
                        testeIf = false;
                    }
                }
                if (testeIf) {
                    percorreLabirinto(matriz, linha - 1, coluna);
                }
            }
        }

        // Testa se vai para a direita
        if (atualBin.charAt(1) == '0') {
            testeIf = true;
            if ((coluna + 1) < colunasTotais) { // Verifica se não ultrapassa o lado direito
                for (int i = 0; i < vetorSimbolos.length; i++) {
                    if (matriz[linha][coluna + 1] == vetorSimbolos[i]) {
                        testeIf = false;
                    }
                }
                if (testeIf) {
                    percorreLabirinto(matriz, linha, coluna + 1);
                }
            }
        }

        // Testa se vai para baixo
        if (atualBin.charAt(2) == '0') {
            testeIf = true;
            if ((linha + 1) < linhasTotais) { // Verifica se não ultrapassa o fundo da matriz
                for (int i = 0; i < vetorSimbolos.length; i++) {
                    if (matriz[linha + 1][coluna] == vetorSimbolos[i]) {
                        testeIf = false;
                    }
                }
                if (testeIf) {
                    percorreLabirinto(matriz, linha + 1, coluna);
                }
            }
        }

        // Testa se vai para a esquerda
        if (atualBin.charAt(3) == '0') {
            testeIf = true; 
            if ((coluna - 1) >= 0) { // Verifica se não ultrapassa o lado esquerdo da matriz
                for (int i = 0; i < vetorSimbolos.length; i++) {
                    if (matriz[linha][coluna - 1] == vetorSimbolos[i]) {
                        testeIf = false;
                    }
                }
                if (testeIf) {
                    percorreLabirinto(matriz, linha, coluna - 1);
                }
            }
        }
    }
}
