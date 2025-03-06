import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class AppLabirintoImprimindo {

    // Valores de cores usadas para impressao colorida no terminal durante a execucao
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static char matrizPrincipal [][];

    // Vetor de dados usados para a impressao colorida no terminal
    public static String[] vetorCores = {   "\u001B[30m", "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m", 
                                            "\u001B[90m", "\u001B[91m", "\u001B[92m", "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m", "\u001B[97m"};

    // Vetor de dicionario dos valores em hexadecimal                                            
    public static char[] vetorHex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    // Vetor de dicionario dos valores em binario 
    public static String[] vetorBin = {
        "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
        "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
    };

    // Vetor de simbolos para representar as areas preenchidas
    public static char[] vetorSimbolos = {  '#', '$', '%', '&', '*', '!', '@', '+', '=', '?', '~', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
                                            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
                                            'r', 's', 't', 'u', 'v', 'w', 'x', 'y' , 'z'};


    // armazena o numero de bichinhos de cada area antes de guardar no vetor
    public static int contadorBichinhos = 0;

    public static char[] vetorBichinhos = {'A', 'B', 'C', 'D', 'E', 'F'};

    // conta areas que já foram completamente preenchidas
    public static int contadorAreas = 0;

    // linhas da matriz principal
    public static int linhasTotais;

    // colunas da matriz principal
    public static int colunasTotais;

    // contador de operacoes
    public static int cont = 0;

    // contador dos bichinhos
    public static int contadorAnoes = 0;
    public static int contadorBruxas = 0;
    public static int contadorCavaleiros = 0;
    public static int contadorDuendes = 0;
    public static int contadorElfos = 0;
    public static int contadorFeijoes = 0;

    // Boolean que define se a exeucao vai imprimir ou nao
    public static boolean imprimeExecucao = false;

    // Boolean que define se a matriz colorida vai imprimir ou nao
    public static boolean imprimeColorido = false;


    public static int[] vetorFrequenciaBichinhos;

    // Vetor que armazena numero de bichinhos por area
    public static int[] vetorContagemBichinhos;

    // Vetor que armazena valor do bichinho mais frequente de cada area
    public static int[] vetorBichinhoMaisFrequente;

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(System.in);

        vetorFrequenciaBichinhos = new int[1000];
        vetorContagemBichinhos = new int[1000];
        vetorBichinhoMaisFrequente = new int[1000];

        try {
            System.out.println("\nEscolha o arquivo para testar a partir de seu numero:\n");
            System.out.println("1 - caso40_a.txt");
            System.out.println("2 - caso80_a.txt");
            System.out.println("3 - caso100_a.txt");
            System.out.println("4 - caso120_a.txt");
            System.out.println("5 - caso150_a.txt");
            System.out.println("6 - caso180_a.txt");
            System.out.println("7 - caso200_a.txt");
            System.out.println("8 - caso250_a.txt\n");

            System.out.print("-> ");
            int escolha = s.nextInt();

            File arquivo = new File("casos/caso40_a.txt");

            System.out.println("\nDigite [1 para SIM / 0 para NÃO]\n");
            
            switch (escolha) {
                case 1: 
                    arquivo = new File("casos/caso40_a.txt");
                    System.out.println("Deseja ver a execucao da recursao? [1/0]");
                    System.out.print("-> ");
                    int exec = s.nextInt();
                    if (exec == 1) {
                        imprimeExecucao = true;
                    }
                    break;
                case 2:
                    arquivo = new File("casos/caso80_a.txt");
                    break;
                case 3:
                    arquivo = new File("casos/caso100_a.txt");
                    break;
                case 4:
                    arquivo = new File("casos/caso120_a.txt");
                    break;
                case 5:
                    arquivo = new File("casos/caso150_a.txt");
                    break;
                case 6:
                    arquivo = new File("casos/caso180_a.txt");
                    break;
                case 7:
                    arquivo = new File("casos/caso200_a.txt");
                    break;
                case 8:
                    arquivo = new File("casos/caso250_a.txt");
                    break;
            }
            
            System.out.println("\nDeseja ver a impressao do resultado da matriz? [1/0]");
            System.out.println("Para casor maiores, ajustar tamanho do terminal\n");
            System.out.print("-> ");
            int impr = s.nextInt();
            if (impr == 1) {
                imprimeColorido = true;
            }
            Scanner scanner = new Scanner(arquivo);

            linhasTotais = scanner.nextInt();
            colunasTotais = scanner.nextInt();
            scanner.nextLine(); 

            matrizPrincipal = new char[linhasTotais][colunasTotais];

            for (int i = 0; i < linhasTotais; i++) {
                for (int j = 0; j < colunasTotais; j++) {
                    if (scanner.hasNext()) {
                        matrizPrincipal[i][j] = scanner.next().charAt(0);
                    }
                }
            }

            for (int i = 0; i < linhasTotais; i++) {
                for (int j = 0; j < colunasTotais; j++) {
                    for (int k = 0; k < vetorHex.length - 1; k++) {
                        if (Character.toLowerCase(matrizPrincipal[i][j]) == Character.toLowerCase(vetorHex[k])) {
                            percorreLabirinto(matrizPrincipal, i, j);
                            vetorContagemBichinhos[contadorAreas] = contadorBichinhos;
                            contadorBichinhos = 0;
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
            
            System.out.println("\nLeitura do arquivo: " + arquivo);

            System.out.println("\nNumero de areas: " + contadorAreas);

            System.out.print("Bichinho mais frequente em uma area:");
            imprimePos();

            System.out.println("Numero de operacoes: " + cont + "\n");

            

            if (imprimeColorido) {
                imprimirMatrizColorida(matrizPrincipal, linhasTotais, colunasTotais);
            }
            
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
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
        System.out.println("\n");
    }

    public static void verificaMaiorBichinho() {
        vetorContagemBichinhos[contadorAreas] = contadorBichinhos;
        contadorBichinhos = 0;

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

    // Recursao da matriz
    public static void percorreLabirinto(char [][] matriz, int linha, int coluna) {
        
        try {

        cont++;

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
        
        // Teste usado na impressao da execucao do codigo
        boolean teste = false;

        // Impressao da execucao, utilizada apenas no caso40, nos outros casos fica ineficiente
        if (imprimeExecucao) {
        Thread.sleep(100);
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < linhasTotais; i++) {
            for (int j = 0; j < colunasTotais; j++) {
                
                teste = false;
                for (int k = 0; k < vetorSimbolos.length; k++) {
                    if(matrizPrincipal[i][j] == vetorSimbolos[k]) {
                        teste = true;
                    }
                    continue;
                }
                if (teste) {
                    for (int k = 0; k < vetorSimbolos.length; k++) {
                        if (matrizPrincipal[i][j] == vetorSimbolos[k]) {
                            System.out.print(vetorCores[k % vetorCores.length] + matrizPrincipal[i][j] + ANSI_RESET + " ");
                        }
                    }
                    continue;
                }
                if (i == linha && j == coluna) {
                    System.out.print(ANSI_GREEN + "X" + ANSI_RESET + " ");
                    continue;
                }
                System.out.print(ANSI_RED + matrizPrincipal[i][j] + ANSI_RESET + " ");
            }
            System.out.println();
        }
        }

        String atualBin = "";

        for (int i = 0; i < vetorHex.length; i++) {

            if (Character.toLowerCase(atualHex) == Character.toLowerCase(vetorHex[i])) {
                atualBin = vetorBin[i];
            }
        }

        // Marca a posicao atual da matriz na recursao com o simbolo correspondente da area
        matriz[linha][coluna] = vetorSimbolos[contadorAreas % vetorSimbolos.length];

        // Boolean para verificar se o lado do labirinto que sera feita a recursao já nao esta marcado com símbolo
        boolean testeIf = true;

        // Teste para cima
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

        // Teste para direita
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
        
        // Teste para baixo
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
        
        // Teste para esquerda
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

    } catch (InterruptedException e) {
        System.out.println("A execução foi interrompida.");
    }
    }

    // Funcao que imprime a matriz colorida
    public static void imprimirMatrizColorida(char[][] matriz, int linhas, int colunas) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                for (int k = 0; k < vetorSimbolos.length; k++) {
                    if (matriz[i][j] == vetorSimbolos[k]) {
                        System.out.print(vetorCores[k % vetorCores.length] + matrizPrincipal[i][j] + ANSI_RESET + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}
