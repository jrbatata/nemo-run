/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Instrucoes extends JPanel {

    protected JButton btNext;
    protected JButton btPrevious;
    protected JButton btBack;
    protected ImageIcon imgNext, imgNextPressed;
    protected ImageIcon imgPrevious, imgPreviousPressed;
    protected ImageIcon imgBack, imgBackPressed;
    protected ImageIcon[] frames;
    protected int index;

    public Instrucoes(Principal principal) {

        setLayout(null);
        index = 0;

        //Inicializacao do vetor de quadros
        frames = new ImageIcon[5];
        for (int i = 0; i < 5; i++) {
            //Atribui a cada posicao do vetor a uma imagem que tem o mesmo nome, mudando apenas o numero do quadro
            frames[i] = new ImageIcon(getClass().getResource("/imagens/how_to_play_" + i + ".gif"));
        }

        //Inicializacao dos imagens
        imgNext = new ImageIcon(getClass().getResource("/imagens/button_next.png"));
        imgNextPressed = new ImageIcon(getClass().getResource("/imagens/button_next_pressed.png"));

        imgPrevious = new ImageIcon(getClass().getResource("/imagens/button_previous.png"));
        imgPreviousPressed = new ImageIcon(getClass().getResource("/imagens/button_previous_pressed.png"));

        imgBack = new ImageIcon(getClass().getResource("/imagens/button_back.png"));
        imgBackPressed = new ImageIcon(getClass().getResource("/imagens/button_back_pressed.png"));

        //Inicializacao do botao Proximo
        btNext = new JButton();
        btNext.setBounds(700, 465, imgNext.getIconWidth(), imgNext.getIconHeight());
        btNext.setText(null);
        btNext.setIcon(imgNext);
        btNext.setPressedIcon(imgNextPressed);
        btNext.setBorderPainted(false);
        btNext.setContentAreaFilled(false);
        add(btNext);

        //Inicializacao do botao Anterior
        btPrevious = new JButton();
        btPrevious.setBounds(230, 465, imgPrevious.getIconWidth(), imgPrevious.getIconHeight());
        btPrevious.setText(null);
        btPrevious.setIcon(imgPrevious);
        btPrevious.setPressedIcon(imgPreviousPressed);
        btPrevious.setBorderPainted(false);
        btPrevious.setContentAreaFilled(false);
        add(btPrevious);

        //Inicializacao do botao Voltar
        btBack = new JButton();
        btBack.setBounds(20, 20, imgBack.getIconWidth(), imgBack.getIconHeight());
        btBack.setText(null);
        btBack.setIcon(imgBack);
        btBack.setPressedIcon(imgBackPressed);
        btBack.setBorderPainted(false);
        btBack.setContentAreaFilled(false);
        add(btBack);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        //Desenha o quadro atual
        Image img = frames[index].getImage();
        g.drawImage(img, 0, 0, this);
        controlButtons();
    }

    public void controlButtons() {
        //Se for o primeiro quadro
        if (index == 0) {
            btPrevious.setEnabled(false);
            btNext.setEnabled(true);
        }
        //Se nao for o primeiro ou ultímo
        if (index > 0 && index < 4) {
            btPrevious.setEnabled(true);
            btNext.setEnabled(true);
        }

        //Se for o ultimo quadro
        if (index == 4) {
            btPrevious.setEnabled(true);
            btNext.setEnabled(false);
        }
    }

    public void next() {
        if (index < 5) {
            index++;
        }
    }

    public void previous() {
        if (index > 0) {
            index--;
        }
    }

    public void reset() {
        index = 0;
    }
}
