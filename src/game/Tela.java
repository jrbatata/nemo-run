package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tela extends JPanel {

    protected int xP, xA; // xPrincipal e xAuxiliar
    protected int x, y, control;
    protected int velocX, velocY;
    protected int largura, altura;
    protected int score;
    protected int bestScore;
    protected int fTime;
    protected int delay;
    protected int vidas, level, atualXp;
    protected int numAguas;
    protected boolean reinicia;
    protected boolean stopGame;
    protected boolean pausaTecla;

    protected ImageIcon background;
    protected ImageIcon life;
    protected ImageIcon game_over;
    protected ImageIcon[] xpBar;
    protected ImageIcon[] stars;
    protected Nemo nemo;
    protected Nadador nadador;
    protected AguaViva agua_viva;
    protected ArrayList<AguaViva> aguas_vivas;
    protected ArrayList<AguaViva> aguas_vivas2;
    protected Marlin marlin;
    protected Opcoes sets;

    public Tela(Principal principal) {

        // Inicializacao de objetos
        nemo = new Nemo(50, 200, 105, 65, 3, 5, true);
        nadador = new Nadador(-500, 130, 428, 200, 3, 5, true);
        marlin = new Marlin(900, 200, 4, 4, nemo.velocX, nemo.velocY, true);
        background = new ImageIcon(getClass().getResource("/imagens/loop_background.png"));
        life = new ImageIcon(getClass().getResource("/imagens/life_icon.png"));
        game_over = new ImageIcon(getClass().getResource("/imagens/game_over.gif"));

        aguas_vivas = new ArrayList<>();
        aguas_vivas2 = new ArrayList<>();
        bestScore = 0;
        control = Util.MAIN; // Controla o numero de vezes que o enter foi pressionado
        inicializaComponentes();
        stopGame = false;
    }

    public void inicializaComponentes() {
        // Inicializacao de posicoes
        this.x = 0;
        this.y = 0;
        this.velocX = 4;
        this.nemo.x = 50;
        this.nemo.y = 200;
        this.nadador.x = -500;
        this.nadador.y = 130;

        // Inicializacao de contadores
        this.score = 20000;
        this.xP = 0; // Posicao x inicial do primeiro fundo
        this.xA = background.getIconWidth(); // Posicao x inicial do segundo fundo
        this.fTime = 0;
        this.delay = Util.DELAY;
        this.vidas = 4;
        this.level = Util.LEVEL_ONE;
        this.atualXp = 0;
        this.numAguas = 35;
        this.reinicia = true;
        pausaTecla = false;

        if (aguas_vivas.size() > 0) {
            aguas_vivas.clear();
            aguas_vivas2.clear();

        }
        //Inicializacao da barra de nivel
        this.xpBar = new ImageIcon[Util.MAX_XP];
        for (int i = 0; i < Util.MAX_XP; i++) {
            xpBar[i] = new ImageIcon(getClass().getResource("/progress_bar/xp_" + i + ".png"));
        }

        this.stars = new ImageIcon[Util.LEVELS];
        for (int i = 0; i < Util.LEVELS; i++) {
            stars[i] = new ImageIcon(getClass().getResource("/imagens/level_" + i + "_icon.png"));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img, img2, img3, img4;
        img = background.getImage();
        img2 = nadador.nadador.getImage();
        img4 = life.getImage();

        g.drawImage(img, xP, 0, null);
        g.drawImage(img, xA, 0, null);

        if (control >= Util.PLAYING && control < Util.GAME_OVER) {
            img3 = nemo.nadando.getImage();
            if (stopGame != true) {
                // Verifica a posicao do nemo e do nadador
                if (fTime == 0) {
                    if (nemo.x == 395) {
                        fTime = 1;
                    }
                    if (nemo.x < 395) {
                        nemo.moveRight();
                    }
                    if (nadador.x < -200) {
                        nadador.x += nadador.velocX;
                    }
                }

                // Verifica se um dos backgrounds chegou ao inicio da tela
                if (xA == 0) {
                    xP = background.getIconWidth();
                }
                if (xP == 0) {
                    xA = background.getIconWidth();
                }

                // Decrementa as posicoes
                xA -= velocX;
                xP -= velocX;

                for (int i = 0; i < aguas_vivas.size(); i++) {
                    if (aguas_vivas.get(i).ativo == false) {
                        aguas_vivas.remove(i);
                    }
                }

                for (int i = 0; i < aguas_vivas2.size(); i++) {
                    if (aguas_vivas2.get(i).ativo == false) {
                        aguas_vivas2.remove(i);
                    }
                }
            }
            // Desenha na ordem os backgrounds e o nadador
            g.drawImage(img, xP, y
                    - 15, null);
            g.drawImage(img, xA, y
                    - 15, null);
            g.drawImage(img2, nadador.x, nadador.y,
                    this);
        } else {
            img3 = nemo.parado.getImage();
            if (control == Util.START) {
                img = (new ImageIcon(getClass().getResource("/imagens/start.gif"))).getImage();
                g.drawImage(img, 0, 0, this);
            }
        }

        for (AguaViva agua : aguas_vivas) {
            agua.draw(g);
        }

        for (AguaViva agua : aguas_vivas2) {
            agua.draw(g);
        }

        colisaoAguaVivas(aguas_vivas, img3);
        colisaoAguaVivas(aguas_vivas2, img3);

        if (delay < Util.DELAY) {
            img3 = nemo.choque.getImage();
            delay++;
        } else {
            img3 = nemo.nadando.getImage();
            delay = Util.DELAY;
        }

        progressBar(g);
        g.drawImage(img3, nemo.x, nemo.y, this);

        setScore(g);
        int posVidaX = -15;
        for (int i = 0; i < vidas; i++) {
            posVidaX += img4.getWidth(this) - 4;
            g.drawImage(img4, posVidaX, 35, null);
        }
        pause(g);
        if (control == Util.GAME_OVER) {
            aguas_vivas.clear();
            aguas_vivas2.clear();
            gameOver(g);
        }
        endGame(g);
    }

    public void addAguaViva() {
        int[] position = {80, 100, 125, 150, 200, 225, 250, 300, 325, 350, 400};
        Random px = new Random();
        agua_viva = new AguaViva(1200, position[px.nextInt(11)], 61, 65, 4, 4, true);
        
        if (level == Util.LEVEL_ONE) {
            aguas_vivas.add(agua_viva);
        }

        if (level >= Util.LEVEL_TWO) {
            agua_viva = new AguaViva(1200, position[px.nextInt(11)], 61, 65, 4, 4, true);
            aguas_vivas2.add(agua_viva);
        }
    }

    public void setScore(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Gamer", Font.PLAIN, 30));
        g2d.drawString("SCORE: " + score, 17, 30);
    }

    public void colisaoAguaVivas(ArrayList<AguaViva> a, Image img) {
        for (int i = 0; i < a.size(); i++) {
            if (stopGame != true && control == Util.PLAYING) {
                a.get(i).movimenta();
                if (a.get(i).x < -agua_viva.largura) {
                    a.get(i).ativo = false;
                }
                score++;

                if (Util.colisao(nemo, a.get(i))) {
                    Musica mu = new Musica(new File("res/choque.mp3"));
                    mu.start();
                    delay = 0;
                    a.get(i).ativo = false;
                    nemo.moveLeft();
                    vidas--;
                    if (score > 1500) {
                        score -= 1000;
                    } else {
                        if (score > 150) {
                            score -= 100;
                        } else {
                            score -= 50;
                        }
                    }
                }
            }
        }
    }

    public void gameOver(Graphics g) {
        Image img = game_over.getImage();
        nemo.x = 250;
        nemo.y = 200;
        g.drawImage(img, 190, 150, null);
        for (int i = 0; i < aguas_vivas.size(); i++) {
            aguas_vivas.remove(i);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(13, 59, 81));
        g2d.setFont(new Font("Gamer", Font.PLAIN, 40));
        g2d.drawString("SCORE", 268, 267);
        g2d.drawString("BEST", 460, 267);

        if (score > bestScore) {
            bestScore = score;
        }

        g2d.setColor(new Color(10, 44, 61));
        g2d.setFont(new Font("Gamer", Font.PLAIN, 35));
        g2d.drawString("" + score, 268, 290);
        g2d.drawString("" + bestScore, 460, 290);
    }

    public void progressBar(Graphics g) {
        int xp = (int) score / 500;
        Image img;
        if (score >= 14000) {
            xp = (int) ((score - 14000) / 500);
        } else {
            if (score >= 7000) {
                xp = (int) ((score - 7000) / 500);
            }
        }

        switch (xp) {
            case 0:
                this.atualXp = 0;
                break;
            case 1:
                this.atualXp = 1;
                break;
            case 2:
                this.atualXp = 2;
                break;
            case 3:
                this.atualXp = 3;
                break;
            case 4:
                this.atualXp = 4;
                break;
            case 5:
                this.atualXp = 5;
                break;
            case 6:
                this.atualXp = 6;
                break;
            case 7:
                this.atualXp = 7;
                break;
            case 8:
                this.atualXp = 8;
                break;
            case 9:
                this.atualXp = 9;
                break;
            case 10:
                this.atualXp = 10;
                break;
            case 11:
                this.atualXp = 11;
                break;
            case 12:
                this.atualXp = 12;
                break;
            case 13:
                this.atualXp = 13;
                break;
            case 14:
                this.atualXp = 14;
                break;
        }

        //Aumenta a velocidade das águas vivas
        if (level >= Util.LEVEL_TWO) {
            agua_viva.velocX = 14;
            nemo.velocY = 14;
            nadador.velocY = 14;
            if (numAguas > 15) {
                numAguas--;
            }
        } else {
            if (atualXp > 4) {
                agua_viva.velocX = atualXp;
                nemo.velocY = atualXp;
                nadador.velocY = atualXp;
                if (numAguas > 20) {
                    numAguas--;
                }
            }
        }

        img = xpBar[atualXp].getImage();
        int x = getWidth() - img.getWidth(this);
        g.drawImage(img, x, 8, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Gamer", Font.PLAIN, 25));
        g2d.drawString("LEVEL " + level, getWidth() - 115, 60);

        if (score >= 14000) {
            level = Util.LEVEL_THREE;
            img = stars[2].getImage();
        } else {
            if (score >= 7000) {
                level = Util.LEVEL_TWO;
                img = stars[1].getImage();
            } else {
                img = stars[0].getImage();
            }
        }
        g.drawImage(img, x - img.getWidth(this), 8, null);
    }

    public void endGame(Graphics g) {
        Image father = (new ImageIcon(getClass().getResource("/imagens/marlin.gif"))).getImage();
        Image finalImg = (new ImageIcon(getClass().getResource("/imagens/win.gif"))).getImage();
        if (score >= 20800 && !stopGame) { //21000
            if (Util.colisao(nemo, marlin)) {
                g.drawImage(finalImg, 0, 0, this);
                control = Util.WIN;
            } else {
                marlin.x -= 5;
                marlin.y = nemo.y;
                g.drawImage(father, marlin.x, marlin.y, this);
            }
        }
    }

    public void pause(Graphics g) {
        Image paused = (new ImageIcon(getClass().getResource("/imagens/paused.gif"))).getImage();
        if (pausaTecla) {
            g.drawImage(paused, 0, 0, null);
        }
    }
}
