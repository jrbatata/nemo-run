package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class AguaViva extends Objeto {

    protected ImageIcon aguaViva;

    public AguaViva(int x, int y, int largura, int altura, int velocX, int velocY, boolean ativo) {
        super(x, y, largura, altura, velocX, velocY, ativo);
        //Inicialização das imagens
        aguaViva = new ImageIcon(getClass().getResource("/imagens/agua_viva.gif"));
    }

    public void draw(Graphics g) {
            Image img = aguaViva.getImage();
            g.drawImage(img, this.x, this.y, null);
    }

    public void movimenta() {
        this.x -= velocX;
    }
}
