import java.awt.Color;

public class AnimatedText {

    public static void Animatext(String text, int pularlinhaantes, int pularLinhaDepois, int TempoDeTela, int espaco) {

        pularlinhaates(pularlinhaantes);
       
        int terminalWidth = espaco; // largura do terminal
        int padding = (terminalWidth - text.length()) / 2;

        // Espaço em branco antes da animação começar
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        // Animação de escrita com cores
        String[] colors = generateGradient("#FFA500", "#4B0082", text.length());
        for (int contador = 0; contador < text.length(); contador++) {
            System.out.print(colors[contador] + text.charAt(contador));
            try {
                Thread.sleep(TempoDeTela);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pularlinhadepois(pularLinhaDepois);
    }

       
    // Função para gerar um degradê entre duas cores em um determinado número de etapas
    public static String[] generateGradient(String color1, String color2, int quanti) {
        String[] degrade = new String[quanti];

        Color comecoColor = Color.decode(color1); // Color.decode transforma o "#FFA500" em cor no Java
        Color fimColor = Color.decode(color2);

        // Calcula o incremento de cor para cada etapa
        float corR = (float) (fimColor.getRed() - comecoColor.getRed()) / (quanti - 1);
        float corG = (float) (fimColor.getGreen() - comecoColor.getGreen()) / (quanti - 1);
        float corB = (float) (fimColor.getBlue() - comecoColor.getBlue()) / (quanti - 1);

        // Gera as cores intermediárias, fazendo o degradê das cores
        for (int i = 0; i < quanti; i++) {
            int r = comecoColor.getRed() + Math.round(corR * i);
            int g = comecoColor.getGreen() + Math.round(corG * i);
            int b = comecoColor.getBlue() + Math.round(corB * i);

            degrade[i] = "\u001B[38;2;" + r + ";" + g + ";" + b + "m"; // mistura as cores r g b formando cada cor do degradê
        }


        return degrade;
    }

    public static void pularlinhaates(int pularlinhaantes) {
        int cont = 0;

        while (cont < pularlinhaantes) {
            System.out.println("");
            cont++;
        }

    }

    public static void pularlinhadepois(int pularLinhaDepois) {
        int cont = 0;

        while (cont < pularLinhaDepois) {
            System.out.println("");
            cont++;
        }

    }





   

    
       


}
