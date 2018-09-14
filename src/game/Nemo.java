package game;

import javax.swing.ImageIcon;

public class Nemo extends Objeto {

    protected ImageIcon nadando;
    protected ImageIcon parado;
    protected ImageIcon choque;

    public Nemo(int x, int y, int largura, int altura, int velocX, int velocY, boolean ativo) {
        super(x, y, largura, altura, velocX, velocY, ativo);
        this.altMin = 100;
        this.altMax = 465;

        //Inicializa��o das imagens
        this.nadando = new ImageIcon(getClass().getResource("/imagens/nemo_swimming.gif"));
        this.parado = new ImageIcon(getClass().getResource("/imagens/nemo_stopped.png"));
        this.choque = new ImageIcon(getClass().getResource("/imagens/nemo_shocking.gif"));
    }

    //Move e seta o Nemo inclinado para cima
    public void moveUp() {
        this.nadando = new ImageIcon(getClass().getResource("/imagens/nemo_up.gif"));
        super.moveUp();
    }

    public void moveLeft() {
        this.x -= 50;
    }

    //Move e seta o Nemo inclinado para baixo
    public void moveDown() {
        this.nadando = new ImageIcon(getClass().getResource("/imagens/nemo_down.gif"));
        super.moveDown();
    }

    //Retorna � inclina��o inicial do Nemo
    public void startingPosition() {
        this.nadando = new ImageIcon(getClass().getResource("/imagens/nemo_swimming.gif"));
    }
}
