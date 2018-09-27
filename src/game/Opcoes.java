/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Opcoes extends JPanel {

    protected boolean music, sound;
    protected JButton btBack; 
    protected JButton btMusicOn,btMusicOff;
    protected JButton btSoundOn, btSoundOff;
    protected ImageIcon background;
    protected ImageIcon imgBack, imgBackPressed;
    protected ImageIcon imgOnEnabled, imgOnDisabled;
    protected ImageIcon imgOffEnabled, imgOffDisabled;
    
    

    public Opcoes(Principal principal) {

        //Inicializacao dos imagens
        imgOnEnabled = new ImageIcon(getClass().getResource("/imagens/button_on_pressed.png"));
        imgOnDisabled = new ImageIcon(getClass().getResource("/imagens/button_on.png"));

        imgOffEnabled = new ImageIcon(getClass().getResource("/imagens/button_off_pressed.png"));
        imgOffDisabled = new ImageIcon(getClass().getResource("/imagens/button_off.png"));

        imgBack = new ImageIcon(getClass().getResource("/imagens/button_back.png"));
        imgBackPressed = new ImageIcon(getClass().getResource("/imagens/button_back_pressed.png"));
        
        background = new ImageIcon(getClass().getResource("/imagens/settings.png"));
        setLayout(null);
        music = true;
        sound = true;

        //Inicializacao do botao MusicOn    
        btMusicOn = new JButton();
        btMusicOn.setBounds(390, 245, imgOnEnabled.getIconWidth(), imgOnEnabled.getIconHeight());
        btMusicOn.setText(null);
        btMusicOn.setIcon(imgOnEnabled);
        btMusicOn.setBorderPainted(false);
        btMusicOn.setContentAreaFilled(false);
        add(btMusicOn);

        //Inicializacao do botao MusicOff
        btMusicOff = new JButton();
        btMusicOff.setBounds(452, 245, imgOffDisabled.getIconWidth(), imgOffDisabled.getIconHeight());
        btMusicOff.setText(null);
        btMusicOff.setIcon(imgOffDisabled);
        btMusicOff.setPressedIcon(imgOffEnabled);
        btMusicOff.setBorderPainted(false);
        btMusicOff.setContentAreaFilled(false);
        add(btMusicOff);

        //Inicializacao do botao SoundOn    
        btSoundOn = new JButton();
        btSoundOn.setBounds(390, 285, imgOnEnabled.getIconWidth(), imgOnEnabled.getIconHeight());
        btSoundOn.setText(null);
        btSoundOn.setIcon(imgOnEnabled);
        btSoundOn.setBorderPainted(false);
        btSoundOn.setContentAreaFilled(false);
        add(btSoundOn);

        //Inicializacao do botao SoundOff
        btSoundOff = new JButton();
        btSoundOff.setBounds(452, 285, imgOffDisabled.getIconWidth(), imgOffDisabled.getIconHeight());
        btSoundOff.setText(null);
        btSoundOff.setIcon(imgOffDisabled);
        btSoundOff.setPressedIcon(imgOffEnabled);
        btSoundOff.setBorderPainted(false);
        btSoundOff.setContentAreaFilled(false);
        add(btSoundOff);

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
        Image img = background.getImage();
        g.drawImage(img, 0, 0, this);
    }

    public void switchButton(ActionEvent e) {
        if (e.getSource().equals(btMusicOff)) {
            btMusicOn.setIcon(imgOnDisabled);
            btMusicOn.setPressedIcon(imgOnEnabled);
            btMusicOff.setIcon(imgOffEnabled);
            btMusicOff.setPressedIcon(imgOffDisabled);
            music = false;
        } else {
            if (e.getSource().equals(btMusicOn)) {
                btMusicOn.setIcon(imgOnEnabled);
                btMusicOn.setPressedIcon(imgOnDisabled);
                btMusicOff.setIcon(imgOffDisabled);
                btMusicOff.setPressedIcon(imgOffEnabled);
                music = true;
            }
        }

        if (e.getSource().equals(btSoundOff)) {
            btSoundOn.setIcon(imgOnDisabled);
            btSoundOn.setPressedIcon(imgOnEnabled);
            btSoundOff.setIcon(imgOffEnabled);
            btSoundOff.setPressedIcon(imgOffDisabled);
            sound = false;

        } else {
            if (e.getSource().equals(btSoundOn)) {
                btSoundOn.setIcon(imgOnEnabled);
                btSoundOn.setPressedIcon(imgOnDisabled);
                btSoundOff.setIcon(imgOffDisabled);
                btSoundOff.setPressedIcon(imgOffEnabled);
                sound = true;
            }
        }
    }
}
