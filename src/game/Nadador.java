package game;

import javax.swing.ImageIcon;

public class Nadador extends Objeto {

    protected ImageIcon nadador;

    public Nadador(int x, int y, int largura, int altura, int velocX, int velocY, boolean ativo) {
        super(x, y, largura, altura, velocX, velocY, ativo);
        this.altMin = 165;
        this.altMax = 515;

        this.nadador = new ImageIcon(getClass().getResource("/imagens/nadador.gif"));
    }

    //Move e seta o Nadador inclinado para cima
    public void moveUp() {
        this.nadador = new ImageIcon(getClass().getResource("/imagens/nadador_up.gif"));
        super.moveUp();
    }

    //Move e seta o Nadador inclinado para baixo
    public void moveDown() {
        this.nadador = new ImageIcon(getClass().getResource("/imagens/nadador_down.gif"));
        super.moveDown();
    }

    //Retorna � inclina��o inicial do Nadador
    public void startingPosition() {
        this.nadador = new ImageIcon(getClass().getResource("/imagens/nadador.gif"));
    }

    public class Movimento extends Thread {
        public void run() {
            while (true) {
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
