package game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Musica extends Thread {

    private File mp3;
    private Player player;
    protected boolean loop;

    public Musica(File mp3) {
        this.mp3 = mp3;
    }

    @Override
    public void run() {
        do {
            try {
                FileInputStream fis = new FileInputStream(mp3);
                BufferedInputStream bis = new BufferedInputStream(fis);

                this.player = new Player(bis);
                this.player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (loop);
    }
}
