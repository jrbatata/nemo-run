package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Principal extends JFrame implements ActionListener, KeyListener {

    protected boolean[] controleTecla = new boolean[2]; // vetor para armazenar as teclas pressionadas
    protected boolean jogando; //Verifica de o usuario esta jogando
    protected boolean over_tocando; //Verifica se a musica de game over esta tocando
    protected boolean falando; //Se o audio do Marlin falando está ativo
    protected int contAguas;
    protected Menu menu;
    protected Tela tela;
    protected Instrucoes inst;
    protected Opcoes sets;
    protected Musica menu_song;
    protected Musica game_song;
    protected Musica water;
    protected Musica marlin;
    protected Musica over_song;

    public Principal() {
        menu_song = new Musica(new File("res/menu.mp3"));
        menu = new Menu(this);
        tela = new Tela(this);
        inst = new Instrucoes(this);
        sets = new Opcoes(this);

        setTitle("Nemo Run");
        setSize(850, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(menu);

        tela.addKeyListener(this);
        tela.setFocusable(true);

        menu.btStart.addActionListener(this);
        menu.btInst.addActionListener(this);
        menu.btSets.addActionListener(this);

        inst.btNext.addActionListener(this);
        inst.btPrevious.addActionListener(this);
        inst.btBack.addActionListener(this);

        sets.btMusicOn.addActionListener(this);
        sets.btMusicOff.addActionListener(this);
        sets.btSoundOn.addActionListener(this);
        sets.btSoundOff.addActionListener(this);
        sets.btBack.addActionListener(this);

        contAguas = 0;
        jogando = true;
        over_tocando = false;
        falando = false;
    }

    public static void main(String[] args) {
        Principal p = new Principal();
        p.iniciaAnimacao();
    }

    public void iniciaAnimacao() {
        if (sets.music) {
            menu_song.loop = true;
            menu_song.start();
        }
        while (jogando) {
            if (tela.reinicia) { //Comando para fazer o nemo ficar parado quando reinicia o jogo
                controleTecla[0] = false;
                controleTecla[1] = false;
                update();
                tela.reinicia = false;
            }
            try {
                if (contAguas > tela.numAguas) {
                    tela.addAguaViva();
                    contAguas = 0;
                }
                update();
                tela.repaint();
                Thread.sleep(45);
                if (Util.colisao(tela.nemo, tela.nadador)) {
                    tela.control = Util.GAME_OVER;
                    tela.nemo.x += 10;
                    game_song.suspend();
                    water.suspend();
                    if (!over_tocando) {
                        over_song = new Musica(new File("res/game_over.mp3"));
                        over_song.start();
                        over_tocando = true;
                    }
                }
                contAguas++;

                if (sets.sound) {
                    if (tela.score >= 20000) {
                        if (!falando) {
                            marlin = new Musica(new File("res/marlin.mp3"));
                            marlin.start();
                            falando = true;
                        } else {
                            marlin.resume();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ESCAPE) {
            if (sets.music) {
                menu_song = new Musica(new File("res/menu.mp3"));
                menu_song.start();
                game_song.stop();
            }
            tela.setVisible(false);
            add(menu);
            menu.setVisible(true);
            menu.requestFocus();
            tela.control = Util.MAIN;
            tela.inicializaComponentes();
            tela.addKeyListener(this);
            tela.setFocusable(true);
            falando = false;
            if (tela.pausaTecla) {
                tela.pausaTecla = false;
            }
        }
        if (tecla == KeyEvent.VK_P) {
            if (tela.control >= Util.PLAYING) {
                if (sets.sound) {
                    Musica mu = new Musica(new File("res/click.mp3"));
                    mu.start();
                }
                tela.pausaTecla = !tela.pausaTecla;
                tela.stopGame = !tela.stopGame;
                if (sets.music) {
                    if (tela.pausaTecla) {
                        game_song.suspend();
                        water.suspend();
                    } else {
                        game_song.resume();
                        water.resume();
                    }
                }
            }
        }

        setKey(tecla, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        setKey(tecla, false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sets.sound) {
            Musica mu = new Musica(new File("res/click.mp3"));
            mu.start();
        }
        //Botoes do Menu Principal
        if (e.getSource().equals(menu.btStart)) {

            tela = new Tela(this);

            if (sets.music) {
                menu_song.stop();
                game_song = new Musica(new File("res/corrida.mp3"));
                game_song.loop = true;
                game_song.start();
            }

            menu.setVisible(false);
            add(tela);

            tela.control = Util.START;
            tela.addKeyListener(this);
            tela.requestFocus();
            tela.setFocusable(true);
            tela.setVisible(true);

        } else {
            if (e.getSource().equals(menu.btInst)) {
                menu.setVisible(false);
                add(inst);
                inst.setVisible(true);
                inst.requestFocus();
            } else {
                if (e.getSource().equals(menu.btSets)) {
                    menu.setVisible(false);
                    add(sets);
                    sets.setVisible(true);
                    sets.requestFocus();
                }
            }
        }

        //Botoes de Instruções
        if (e.getSource().equals(inst.btNext)) {
            inst.next();
        } else {
            if (e.getSource().equals(inst.btPrevious)) {
                inst.previous();
            } else {
                if (e.getSource().equals(inst.btBack)) {
                    inst.reset();
                    inst.setVisible(false);
                    menu.setVisible(true);
                    menu.requestFocus();
                }
            }
        }

        //Botoes de Opcoes
        sets.switchButton(e);
        if (sets.music) {
            menu_song.resume();
        } else {
            menu_song.suspend();
        }

        if (e.getSource().equals(sets.btBack)) {
            sets.setVisible(false);
            menu.setVisible(true);
            menu.requestFocus();
        }
    }

    private void setKey(int tecla, boolean pressionada) { //metodo para saber se a tecla esta pressionada
        switch (tecla) {
            case KeyEvent.VK_ENTER:
                tela.control++;
                if (sets.sound) {
                    if (tela.control >= Util.START && tela.control < Util.GAME_OVER) {
                        water = new Musica(new File("res/agua.mp3"));
                        water.start();
                    }
                }
                if (tela.control >= Util.GAME_OVER) {
                    tela.control = Util.PLAYING;
                    tela.inicializaComponentes();
                    falando = false;
                    if (sets.music) {
                        if (tela.score >= 21000) {
                            over_song.suspend();
                            over_tocando = false;
                        } else {
                            game_song.resume();
                        }
                    }
                }
                break;
            case KeyEvent.VK_UP:
                // Seta para cima
                controleTecla[0] = pressionada;
                break;
            case KeyEvent.VK_DOWN:
                // Seta para baixo
                controleTecla[1] = pressionada;
                break;
        }
    }

    public void update() {
        if (controleTecla[0]) {
            if (tela.control >= Util.PLAYING) {
                if (tela.pausaTecla != true) {
                    tela.nemo.moveUp();
                    tela.nadador.moveUp();
                }
            }
        } else {
            tela.nemo.startingPosition();
            tela.nadador.startingPosition();
        }
        if (controleTecla[1]) {
            if (tela.control >= Util.PLAYING) {
                if (tela.pausaTecla != true) {
                    tela.nemo.moveDown();
                    tela.nadador.moveDown();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
