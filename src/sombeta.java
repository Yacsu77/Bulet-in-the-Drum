
import javax.sound.sampled.*;
import java.io.File;

public class sombeta {
    public static Clip clip; // Mantenha referência ao Clip atual
    public static void main(String[] args) {
        // Main method não faz nada aqui, pode ser usado para teste
    }

    public static synchronized Clip som(String som) {
        pararSom(); // Pare qualquer som antes de iniciar um novo

        try {
            File file = new File(som);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Inicia a reprodução do áudio
        } catch (Exception ex) {
            ex.printStackTrace();
            clip = null; // Certifique-se de que o clip seja nulo em caso de falha
        }

        return clip;
    }

    public static Clip som1(String som) {

        try {
            File file = new File(som);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start(); // Inicia a reprodução do áudio
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clip;
    }

   
    public static synchronized void pararSom() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.stop();
            clip.close();
            clip = null;
        }
}
}