import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.sun.tools.javac.Main;




public class Roleta {

// Declaração das instancias Globais
//----------------------------------------------------------------------------------------------------------------------------------//
    /*
     * Scanner global (Chamar = ler "Nome do Scanner")
     */
    public static Scanner ler = new Scanner(System.in);

    /*
     * Random global (Chamar = random "Nome do Random")
     */
    public static Random random = new Random();

    /**
     * Vetor criado para ser gerado um numero aleatorio de balas Dele sempre vai
     *  ser chamado um metodo para com que decida aonde vai estar essas bala Essas 
     * balas estarao armazenado em forma de "Strings" para um melhor entendimento do 
     * codigo a roleta vai estar separafa em V e B 
     * V para vazio V = Vazio "esta vazio" 
     * B para bala B = bala "esta cheio"
     */
    public static String roleta[] = new String[12];

/*
 * contador global serve apara as rodadas e os itens serem acessados por ele 
 */
    public static int contadorGlobal = 0;

/*
 * Variavel que serve como dano para os jogaodres podendo ser manipulada por funçoes 
 */
    public static int DanoGlobal = 1;

    public static int Chaveparainvetario = 0;

    public static String coraçãovida = ":heart:";
    public static String coraçãomorte = ":skull:";



//Variaveis Globais
//----------------------------------------------------------------------------------------------------------------------------------//
    /*
     * Declaração da vida gloval inicial dos players 
     */
    public static int VidaPlayer1 =5;
    public static int VidaPlayer2 =5;

    public static int jogador1 = 1;
    public static int jogador2 = 2;

    /*
     * Declaraçao de variavel global para armazena o nome dos players
     */
    public static String player1Nome = "";
    public static String player2Nome = "";

    /*
    *Variavel Global para definir a escolha de cada jogador
    */
    public static int escolhaplayer1 = 0;
    public static int escolhaplayer2 = 0;

    


    /**
    * Server para armazena o numero de balas em cada rodada 
    */
    public static int numerbalas = 0;

    /*
     * Variavel para dar os ites para o jogador 
     */
    public static String inventarioPlayer1[] = new String[8];
    public static String inventarioPlayer2[] = new String[8];

    /*
     * Variavel Global para a manipulaçao dos itens
     */
    public static String Lupa = "Lupa";
    public static String Potederemedio = "Pote de remedio";
    public static String Faca = "Faca";
    public static String cigarro = "Cigarro";
    public static String Algema  = "Algema";
    public static String Amuleto = "Amuleto";

    /*
     * Amuleto que impede o jogador de tomar um tiro quando esta no true
     */
    public static boolean AmuletoPlayer1 = false;
    public static boolean AmuletoPlayer2 = false;

    public static boolean Jogador1preso = false;
    public static boolean Jogador2preso = false;
//----------------------------------------------------------------------------------------------------------------------------------//


//----------------------------------------------------------------------------------------------------------------------------------//

    public static void main(String[] args) throws Exception {

        Thread tocar = new Thread(()->sombeta.som("Trilha.wav"));
                    tocar.start();

                   
                   menudeinteraçao();
     
}


    public static void embaralha_roleta() {
        int numeroAsergeradoaleatorio = 0;

        for (int cont = 0; cont < 12; cont++) {
            numeroAsergeradoaleatorio = random.nextInt(2) + 1;

            if (numeroAsergeradoaleatorio == 1) {
                roleta[cont] = "B";
            } else if (numeroAsergeradoaleatorio == 2) {
                roleta[cont] = "V";
            } else {
                System.err.println("Erro: Número inválido gerado!");
                return;
            }
        }
    }

    
    public static void menudeinteraçao() {
        
        Limparterminal();

    

        logo(); 

        AnimatedText.Animatext("Menu Principal", 4, 1,100,150);

        AnimatedText.Animatext("1. Jogar", 1, 1,100,150);

        AnimatedText.Animatext(" 2. Regras", 1, 1,100,150);

        AnimatedText.Animatext("3. Sair", 1, 1,100,150);


        AnimatedText.Animatext("Escolha: ", 1, 0,10,140);
        int Opcãoparainiciarojogo = ler.nextInt();


        tocarclick();

        Limparterminal();

        switch (Opcãoparainiciarojogo) {
            case 1:
                
            
                AnimatedText.Animatext("Qual o nome do Player 1?", 4, 1,40,150);  

                AnimatedText.Animatext("Nome: ", 1, 0,10,150);
                player1Nome = ler.next();
                tocarclick();
                Limparterminal();
                
                AnimatedText.Animatext("Qual o nome do Player 2?", 4, 1,40,150);  

                AnimatedText.Animatext("Nome: ", 1, 0,10,150);
                player2Nome = ler.next();
                tocarclick();
                Limparterminal();
                
                iniciarJogo();
                
                break;

                case 2:
                int verRegras;
                do {
                    System.out.print(
                            "1. Preparação\n2. Turno de jogador\n3. Vidas\n4. Itens\n5. Rodadas\n6. Objetivo\n7. Voltar\n\n");
    
                    System.out.print("Escolha: ");
                    verRegras = ler.nextInt();
                    switch (verRegras) {
                    case 1:
                        System.out.println(
                                "Os jogadores são designados como Player1 e Player 2. (Podendo ser o nome do Player)\r\n"
                                        + "A arma é colocada no centro do tabuleiro virtual, acessível para ambos os jogadores.\r\n"
                                        + "Cada jogador começa com 5 vidas.\n");
    
                        break;
                    case 2:
                        System.out.println("Cada jogador tem um turno durante o qual pode escolher uma ação.\r\n" + "\r\n"
                                + "O jogador pode escolher entre:\r\n"
                                + "a) Atirar em si mesmo para recarregar a arma e obter um novo turno.\r\n"
                                + "b) Atirar no oponente para causar dano.\r\n"
                                + "Se o jogador escolher atirar no oponente e acertar, o oponente perde uma vida.\r\n"
                                + "Se o jogador escolher atirar no oponente e errar, o turno passa para o oponente.\n");
                        break;
                    case 3:
                        System.out.println("Cada jogador começa com 5 vidas.\r\n"
                                + "O objetivo é reduzir as vidas do oponente a zero.\n");
                        break;
                    case 4:
                        System.out.println(
                                "A partir da segunda rodada, os jogadores recebem de 2 a 4 itens aleatórios por turno.\r\n"
                                        + "Os itens são exibidos na tela e podem ser utilizados pelo jogador.\r\n"
                                        + "Os itens podem incluir a lupa, potinho de remédio, faca, cigarro, algemas, amuleto e modo frenesi.\n");
                        break;
                    case 5:
                        System.out.println(
                                "As rodadas são definidas pelo número de balas no tambor da arma (máximo de 12).\r\n"
                                        + "Quando todas as balas forem utilizadas, a arma é recarregada e uma nova rodada começa.\n");
                        break;
                    
                    case 6:
                        System.out.println(
                                "O jogador que eliminar o oponente reduzindo suas vidas a zero é declarado vencedor.\n");
                    case 7:
                        menudeinteraçao();
                        break;
                    }
                    if (verRegras < 1 || verRegras > 7) {
                        System.err.println("Número digitado inválido");
                        menudeinteraçao();
                    }
                } while (verRegras < 7);
                break;
                
                case 3:
                AnimatedText.Animatext("|  Obrigado por jogar  |", 4, 5, 50,150);
                return;
                
                default:
                System.err.printf("Erro: Opção inválida\n\n Precione qualquer tecla para continuar...");
                String qualquercoisa = ler.next();

                    if ( qualquercoisa.equals(qualquercoisa)){
                        menudeinteraçao();
                    }

                break;
            }
        }

    
    public static void numeroDebalas() {
        numerbalas = 0; // Resetar o contador
        for (int cont = 0; cont < roleta.length; cont++) {
            if (roleta[cont].equals("B")) {
                numerbalas++;
            }
        }
    }
    
    
    public static void rodada(int jogador) {

        if (contadorGlobal == 2){

            AnimatedText.Animatext("Recarregando Arma...", 3, 2, 150, 150);

            iniciarJogo();

        } else if (jogador == 1) {

            if (Jogador1preso == true){

                AnimatedText.Animatext("Putz o " + player1Nome + " está preso, vez do jogador 2!", 5, 2, 100, 150);
                Limparterminal();
                Jogador1preso = false;
                rodada(jogador2);
            }


            imprimirInventario(jogador1);
            AnimatedText.Animatext("Escolha: ", 1, 0, 20, 150);
            escolhaplayer1 = ler.nextInt();
            escolhaDoJogador(jogador1, escolhaplayer1);
           

        } else if (jogador == 2) {

            if (Jogador2preso == true){
                AnimatedText.Animatext("Putz o " + player2Nome + " está preso, vez do jogador 1!", 5, 2, 100, 150);
                Jogador2preso = false;
                rodada(jogador1);
            }
            

            imprimirInventario(jogador2);
            AnimatedText.Animatext("Escolha: ", 1, 0, 20, 150);
            escolhaplayer2 = ler.nextInt();
            escolhaDoJogador(jogador2, escolhaplayer2);
           

        } else {
            System.out.println("Número de jogador inválido");
        }
    }
    

    public static void distribuirItens() {
        String[] itensDisponiveis = {Lupa, Potederemedio, Faca, cigarro, Algema, Amuleto};
        Random random = new Random();
        int acessarintens1 =0;
        int acessarintens2 = 0;
        int limpar =0;
        int indexItem1 =0;
        int indexItem2 =0;

            for (int cont = 0; cont < 4; cont++) {

                if(acessarintens1 == 7 ){
                    acessarintens1 = 0;
                }
                
                if (inventarioPlayer1[acessarintens1] != null ){
                    while (inventarioPlayer1[acessarintens1] != null) {
                        acessarintens1++;
                            if (acessarintens1 == 7){
                                inventarioPlayer2[limpar] = null;
                            }
                    }
                    indexItem1 = random.nextInt(itensDisponiveis.length);

                }else {
                    indexItem1 = random.nextInt(itensDisponiveis.length);
                }
                inventarioPlayer1[acessarintens1] = itensDisponiveis[indexItem1];


            }

            for (int cont = 0; cont < 4; cont++) {

                if(acessarintens2 == 7 ){
                    acessarintens2 = 0;
                }
                
                if (inventarioPlayer2[acessarintens2] != null ){
                    while (inventarioPlayer2[acessarintens2] != null) {
                        acessarintens2++;
                            if (acessarintens2 == 7){
                                inventarioPlayer2[limpar] = null;
                            }
                    }
                    indexItem2 = random.nextInt(itensDisponiveis.length);

                }else {
                    indexItem2 = random.nextInt(itensDisponiveis.length);
                }
                inventarioPlayer2[acessarintens2] = itensDisponiveis[indexItem2];


            }
        
        }
    
    

    public static void imprimirInventario(int jogador) {
        int publicaçaoDeitem = 3;

    if (jogador == 1) {

        AnimatedText.Animatext("╔═════════════════════════╗", 0, 1, 5,150);
        AnimatedText.Animatext("  Rodada de " + player1Nome + ": ", 0, 1, 50,150);
        AnimatedText.Animatext("╚═════════════════════════╝", 0, 8, 5,150);

        AnimatedText.Animatext("Status: " + player1Nome , 0, 2, 0,10 );
        AnimatedText.Animatext("Vida: " + VidaPlayer1, 0, 1, 0,10 );
        AnimatedText.Animatext("Balas na arma: " + numerbalas, 0, 1, 0,10 );
        AnimatedText.Animatext("Nao esta preso" , 0, 1, 0,10 );

            if (AmuletoPlayer1 == true){
                AnimatedText.Animatext("Amuleto ativo", 0, 3, 0,10 );

            }else {
                AnimatedText.Animatext("Alumeto quebrado ", 0, 3, 0,10 );

            }


        Animação.arma();

        
        AnimatedText.Animatext("", 5, 0, 0,10 );
        AnimatedText.Animatext("1- Atirar em si", 0, 0, 5,50 );
        AnimatedText.Animatext("2- Atirar ", 0, 0, 5,150 );



        AnimatedText.Animatext("Inventario", 15, 1, 5,150 );

        AnimatedText.Animatext("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════", 0, 1, 5,150);
        AnimatedText.Animatext("", 0, 0, 5,50 );

        for (String item : inventarioPlayer1) {
            if (item != null) {

                AnimatedText.Animatext( publicaçaoDeitem +"- " + item , 0, 0, 5,30);

            } else {
                AnimatedText.Animatext(" vazio", 0, 0, 5,20);
            }
            publicaçaoDeitem++;
        }

        AnimatedText.Animatext("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════", 1, 1, 5,150);



       



    } else if (jogador == 2) {

        AnimatedText.Animatext("╔═════════════════════════╗", 0, 1, 5,150);
        AnimatedText.Animatext("  Rodada de " + player2Nome + ": ", 0, 1, 50,150);
        AnimatedText.Animatext("╚═════════════════════════╝", 0, 8, 5,150);

        AnimatedText.Animatext("Status: " + player2Nome , 0, 2, 0,10 );
        AnimatedText.Animatext("Vida: " + VidaPlayer2, 0, 1, 0,10 );
        AnimatedText.Animatext("Balas na arma: " + numerbalas, 0, 1, 0,10 );
        AnimatedText.Animatext("Nao esta preso" , 0, 1, 0,10 );

            if (AmuletoPlayer2 == true){
                AnimatedText.Animatext("Amuleto ativo", 0, 3, 0,10 );

            }else {
                AnimatedText.Animatext("Alumeto quebrado " , 0, 3, 0,10 );

            }
        Animação.arma();


        AnimatedText.Animatext("", 5, 0, 0,10 );
        AnimatedText.Animatext("1- Atirar em si", 0, 0, 5,50 );
        AnimatedText.Animatext("2- Atirar ", 0, 0, 5,150 );
        
       
        AnimatedText.Animatext("Inventario", 15, 1, 5,150 );

        AnimatedText.Animatext("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════", 0, 1, 5,150);
        AnimatedText.Animatext("", 0, 0, 5,50 );

        for (String item : inventarioPlayer2) {
            if (item != null) {
                AnimatedText.Animatext( publicaçaoDeitem +"- " + item , 0, 0, 5,30);
            } else {
                AnimatedText.Animatext(" vazio", 0, 0, 5,30);
            }
            publicaçaoDeitem++;
        }

        AnimatedText.Animatext("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════", 1, 1, 5,150);



    } else {
        System.out.println("Número de jogador inválido");
    }
}    
    

    public static void usarPoteDeRemedio(int jogador) {
    Limparterminal();

    Animação.Animaçãoremedio();

        if (jogador == 1) {
            System.out.println("Jogador 1 usou o pote de remédio");
            VidaPlayer1++;
            System.out.println("Vida atualizada: Vida" + VidaPlayer1);
            rodada(jogador1);

        } else if (jogador == 2) {
            System.out.println("Jogador 2 usou o pote de remédio");
            VidaPlayer2++;
            System.out.println("Vida atualizada: Vida" + VidaPlayer2);
            rodada(jogador2);

        } else {
            System.out.println("Jogador inválido");
        }
    }
  

    public static void usarlupa(int jogador){
    Limparterminal();

    Animação.AnimaçãoLupa();
        System.out.println("Voce usou a lupa 🔍");

                if ( roleta[contadorGlobal].equals("V")){
                    System.out.println("Não Há balas no Cartucho");

                }else if ( roleta[contadorGlobal].equals("B")){
                    System.out.println("Há uma bala no cartucho");
                }else{
                    System.err.println("Erro");
                }

                if (jogador == 1) {
                    rodada(jogador1);

                }else if(jogador == 2){
                    rodada(jogador2);

                }else {
                    System.err.println("Erro");
                }
        }


    public static void faca(int jogador){
    Limparterminal();

    Animação.AnimaçãoFaca();

                if ( roleta[contadorGlobal].equals("V")){

                    AnimatedText.Animatext("Cano cortado", 2, 2, 20, 150);

                }else if ( roleta[contadorGlobal].equals("B")){
                    AnimatedText.Animatext("Cano cortado", 2, 2, 20, 150);
                    DanoGlobal = 2;
                }else{
                    System.err.println("Erro");
                }

                if (jogador == 1) {
                    rodada(jogador1);

                }else if(jogador == 2){
                    rodada(jogador2);

                }else {
                    System.err.println("Erro");
                }
        }


    public static void Cigarro(int jogador){
    Limparterminal();

    Animação.Animaçãocigarro();


    
        int numeroAleatorio = random.nextInt(2) + 1;

            if (jogador == 1) {

                if (numeroAleatorio == 1){
                    VidaPlayer1--;
                    System.out.printf("Fumar não é legal\nVida: %d ", VidaPlayer1);

                }else if (numeroAleatorio == 2){
                    VidaPlayer1++;
                    System.out.printf("Esse cigarro estava bom de mais\nVida: %d", VidaPlayer1);

                }else {
                    System.out.println("Erro");
                }

            }else if (jogador == 2 ){

                if (numeroAleatorio == 1){
                    VidaPlayer2--;
                    System.out.printf("Fumar não é legal\nVida: %d ", VidaPlayer2);

                }else if (numeroAleatorio == 2){
                    VidaPlayer2++;
                    System.out.printf("Esse cigarro estava bom de mais\nVida: %d", VidaPlayer2);

                }else {
                    System.out.println("Erro");
                }

            }
            if (jogador == 1) {
                rodada(jogador1);

            }else if(jogador == 2){
                rodada(jogador2);

            }else {
                System.err.println("Erro");
            }
           
    }


   public static void Amuleto(int jogador){
    Limparterminal();

    Animação.Animaçãoaamuleto();

        if( jogador == 1){
            AmuletoPlayer1 = true;
            System.out.println("Voçê usou o amuleto.");
            rodada(jogador1);

        }else if ( jogador == 2){
            AmuletoPlayer2 = true;
            System.out.println("Voçê usou o amuleto.");
            rodada(jogador2);
        }

        
   }


   public static void Algema( int jogador){
    Limparterminal();

    Animação.Animaçãoalgema();

        if ( jogador == 1){
            Jogador2preso = true;
            
        }else if ( jogador == 2){
            Jogador1preso = true;
        }
   }

   public static void VerificaçãoItemUtilizado(int jogador, int acesso){

        if ( jogador == 1 ){
                
            if(inventarioPlayer1[acesso].equals(Potederemedio)){
                inventarioPlayer1[acesso] = null;
                usarPoteDeRemedio(jogador1);
            }
            else if (inventarioPlayer1[acesso].equals(Lupa)) {
                inventarioPlayer1[acesso] = null;
                usarlupa(jogador1);
            
            }
            else if(inventarioPlayer1[acesso].equals(Faca)){
                inventarioPlayer1[acesso] = null;
                faca(jogador1);
            }
            else if(inventarioPlayer1[acesso].equals(cigarro)){
                inventarioPlayer1[acesso] = null;
                Cigarro(jogador1);

            }
            else if(inventarioPlayer1[acesso].equals(Amuleto)){
                inventarioPlayer1[acesso] = null;
                Amuleto(jogador1);

            }
            else if (inventarioPlayer1[acesso].equals(Algema)) {
                inventarioPlayer1[acesso] = null;
                Algema(jogador1);

                
            }else if ( inventarioPlayer1[acesso].equals(null)){
                System.err.println("Parece que não tem nada aqui bocó");
                rodada(jogador1);

            }else {
                System.err.println("Erro");
            }
            
        }else if (jogador == 2) {

            if(inventarioPlayer2[acesso].equals(Potederemedio)){
                inventarioPlayer2[acesso] = null;
                usarPoteDeRemedio(jogador2);
            }
            else if (inventarioPlayer2[acesso].equals(Lupa)) {
                inventarioPlayer2[acesso] = null;
                usarlupa(jogador2);

            
            }
            else if(inventarioPlayer2[acesso].equals(Faca)){
                inventarioPlayer2[acesso] = null;
                faca(jogador2);

            }
            else if(inventarioPlayer2[acesso].equals(cigarro)){
                inventarioPlayer2[acesso] = null;
                Cigarro(jogador2);

            }
            else if(inventarioPlayer2[acesso].equals(Amuleto)){
                inventarioPlayer2[acesso] = null;
                Amuleto(jogador2);

            }
            else if (inventarioPlayer2[acesso].equals(Algema)) {
                inventarioPlayer2[acesso] = null;
                Algema(jogador2);

            }
            else if (inventarioPlayer2[acesso].equals(null)){
                System.err.println("Parece que não tem nada aqui bocó");
                rodada(jogador2);
            }
            else {
                System.err.println("Erro");
            }
        }
   }

   public static void escolhaDoJogador(int jogador, int escolha){

    if ( jogador == 1){
        switch (escolhaplayer1) {
            case 1:
            Limparterminal();
                if(roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == false){
             

                    Animação.Tomartiro();

                    AnimatedText.Animatext("Putz vc tomou o tiro", 4, 1, 5,150);  
                    VidaPlayer1 = VidaPlayer1 - DanoGlobal;
                    contadorGlobal++;
                    rodada(jogador1);

                }else if (roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == true){
                    System.out.println("Boa o amuleto te salvou desa!! Fique esperto.");
                    AmuletoPlayer1 = false;
                    contadorGlobal++;
                    rodada(jogador1);

                }else if ( roleta[contadorGlobal].equals("V")){

                    Animação.Noaotomartiro();
                    AnimatedText.Animatext("Boa, vc se livrou dessa!!", 4, 1, 5,150);
                        contadorGlobal++;
                        rodada(jogador1);
                }else {
                    System.err.println("Erro");
                }

            break;

            case 2:
            Limparterminal();
                if(roleta[contadorGlobal].equals("B") && AmuletoPlayer2 == false){

                    Animação.Acertartiro();

                    AnimatedText.Animatext("Boa vc acertou esse tiro", 1, 1, 20,150);

                    VidaPlayer2 = VidaPlayer2 - DanoGlobal;
                    contadorGlobal++;
                    rodada(jogador2);

                }else if (roleta[contadorGlobal].equals("B") && AmuletoPlayer2 == true){

                    AnimatedText.Animatext("O Player 2 tinha um amuleto e ele se salvou do seu tiro!!", 1, 1, 20,150);
                    AmuletoPlayer2 = false;
                    contadorGlobal++;
                    rodada(jogador2);

                }else if ( roleta[contadorGlobal].equals("V")){

                    Animação.Erratiro();

                    AnimatedText.Animatext("Ops!! vc errou.", 1, 1, 20,150);
                    contadorGlobal++;
                    rodada(jogador2);
                }else {
                    System.out.println("Erro");
                }

            break;

            case 3:
                Chaveparainvetario = 0;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break; 
                
            case 4:
                Chaveparainvetario = 1;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break;

            case 5:
                Chaveparainvetario = 2;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break; 
                
            case 6:
                Chaveparainvetario = 3;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break;

            case 7:
                Chaveparainvetario = 4;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break; 
                
            case 8:
                Chaveparainvetario = 5;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break;

            case 9:
                Chaveparainvetario = 6;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break; 
                
            case 10:
                Chaveparainvetario = 7;
                VerificaçãoItemUtilizado(jogador1, Chaveparainvetario);
            break;
        
            default:
                
                break;
        }
    }
    else if ( jogador == 2){

        switch (escolhaplayer2) {

            case 1:
            Limparterminal();
                if(roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == false){

                    Animação.Tomartiro();

                    AnimatedText.Animatext("Putz vc tomou um tiro...", 1, 1, 20,150);
                    VidaPlayer2 = VidaPlayer2 - DanoGlobal;
                    contadorGlobal++;
                    rodada(jogador2);

                }else if (roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == true){

                    AnimatedText.Animatext("Boa, o amuleto te salvou dessa!! Fique esperto.", 1, 1, 20,150);

                    AmuletoPlayer2 = false;
                    contadorGlobal++;
                    rodada(jogador2);

                }else if ( roleta[contadorGlobal].equals("V")){

                    Animação.Noaotomartiro();

                    AnimatedText.Animatext("Boa vc acertou esse tiro", 1, 1, 20,150);
                    contadorGlobal++;
                    rodada(jogador2);

                }else {
                    System.err.println("Erro");
                }

            break;

            case 2:
            Limparterminal();
                if(roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == false){

                    Animação.Acertartiro();

                    AnimatedText.Animatext("Boa vc acertou esse tiro", 1, 1, 20,150);
                    
                    VidaPlayer1 = VidaPlayer1 - DanoGlobal;
                    contadorGlobal++;
                    rodada(jogador1);

                }else if (roleta[contadorGlobal].equals("B") && AmuletoPlayer1 == true){

                    AnimatedText.Animatext("O Player 1 tinha um amuleto e ele se salvou do seu tiro!!", 1, 1, 20,150);

                    AmuletoPlayer1 = false;
                    contadorGlobal++;
                    rodada(jogador1);

                }else if ( roleta[contadorGlobal].equals("V")){

                    Animação.Erratiro();

                    AnimatedText.Animatext("Ops!! vc errou...", 1, 1, 20,150);

                    contadorGlobal++;
                    rodada(jogador1);
                }else {
                    System.out.println("Erro");
                }

            break;

            case 3:
                Chaveparainvetario = 0;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break; 
                
            case 4:
                Chaveparainvetario = 1;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break;

            case 5:
                Chaveparainvetario = 2;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break; 
                
            case 6:
                Chaveparainvetario = 3;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break;

            case 7:
                Chaveparainvetario = 4;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break; 
                
            case 8:
                Chaveparainvetario = 5;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break;

            case 9:
                Chaveparainvetario = 6;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break; 
                
            case 10:
                Chaveparainvetario = 7;
                VerificaçãoItemUtilizado(jogador2, Chaveparainvetario);
            break;
        
            default:
                
                break;
            }
        }
    }

    public static void Limparterminal(){

            try {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                System.out.println("Erro ao limpar o console: " + e.getMessage());
        }
    }
    
    public static void iniciarJogo(){

        contadorGlobal = 0;

        while (VidaPlayer1 > 0 || VidaPlayer2 > 0) {

            if ( VidaPlayer1 <= 0){
                System.out.println(player2Nome + "Ganhou");
                break;
            }else if ( VidaPlayer2 <= 0){
                System.out.println(player1Nome + "Ganhou");
                break;
            }else {

            embaralha_roleta();
            numeroDebalas();
            distribuirItens();
            contadorGlobal = 0; // reseta o contador 
            Limparterminal();

            AnimatedText.Animatext("|A Rodada Começou|", 4, 2,100,150);

            Gameplay();

            }


        }
   }

public static void Gameplay(){

AnimatedText.Animatext("Numero de balas:" + numerbalas, 3, 2, 150, 150);

Timer(1000);

AnimatedText.Animatext("Vida"+ player1Nome+": " + VidaPlayer1, 1, 0, 30, 10);

AnimatedText.Animatext("Vida"+ player2Nome+": " + VidaPlayer2, 1, 1, 30, 10);

Timer(3000);
Limparterminal();

rodada(jogador1);
        
   }

   public static void logo( ){

    
            AnimatedText.Animatext(" ________  ___  ___  ___       ___       _______  _________        ___  ________           _________  ___  ___  _______           ________  ________  ___  ___  _____ ______      ", 0, 1, 1,150);
            AnimatedText.Animatext("|\\   __  \\|\\  \\|\\  \\|\\  \\     |\\  \\     |\\  ___ \\|\\___   ___\\     |\\  \\|\\   ___  \\        |\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   ___ \\|\\   __  \\|\\  \\|\\  \\|\\   _ \\  _   \\    ", 0, 1, 1,150);
            AnimatedText.Animatext("\\ \\  \\|\\ /\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\    \\ \\   __/\\|___ \\  \\_|     \\ \\  \\ \\  \\\\ \\  \\       \\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\\\\\__\\ \\  \\   ", 0, 1, 1,150);
            AnimatedText.Animatext(" \\ \\   __  \\ \\  \\\\\\  \\ \\  \\    \\ \\  \\    \\ \\  \\_|/__  \\ \\  \\       \\ \\  \\ \\  \\\\ \\  \\           \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\  \\ \\\\ \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\\\|__| \\  \\  ", 0, 1, 1,150);
            AnimatedText.Animatext("  \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\____\\ \\  \\____\\ \\  \\_|\\ \\  \\ \\  \\       \\ \\  \\ \\  \\\\ \\  \\           \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\_\\\\ \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ ", 0, 1, 1,150);
            AnimatedText.Animatext("   \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\  \\ \\__\\       \\ \\__\\ \\__\\\\ \\__\\           \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\__\\\\ _\\\\ \\_______\\ \\__\\    \\ \\__\\", 0, 1, 1,150);
            AnimatedText.Animatext("    \\|_______|\\|_______|\\|_______|\\|_______|\\|_______|   \\|__|        \\|__|\\|__| \\|__|            \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|__|\\|__|\\|_______|\\|__|     \\|__|", 0, 3, 1,150);
        
            
        }
    
    
        public static void tocarclick(){
            Thread tocar = new Thread(()->sombeta.som1("click.wav"));
                    tocar.start();
        }
    
    public static void Timer(int timer){

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
    }
    

   






