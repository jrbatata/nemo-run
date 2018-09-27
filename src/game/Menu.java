package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

    protected JButton btStart;
    protected JButton btInst;
    protected JButton btSets;
    protected ImageIcon banner;
    protected ImageIcon imgStart, imgStartPressed;
    protected ImageIcon imgInst, imgInstPressed;
    protected ImageIcon imgSets, imgSetsPressed;
    protected boolean mudaTela = false;

    public Menu(Principal principal) {
        
        //Inicializacao das imagens
        banner = new ImageIcon(getClass().getResource("/imagens/banner.png"));
        imgStart = new ImageIcon(getClass().getResource("/imagens/button_start.png"));
        imgStartPressed = new ImageIcon(getClass().getResource("/imagens/button_start_pressed.png"));
        imgInst = new ImageIcon(getClass().getResource("/imagens/button_htp.png"));
        imgInstPressed = new ImageIcon(getClass().getResource("/imagens/button_htp_pressed.png"));
        imgSets = new ImageIcon(getClass().getResource("/imagens/button_settings.png"));
        imgSetsPressed = new ImageIcon(getClass().getResource("/imagens/button_settings_pressed.png"));        
        setLayout(null);
        
        //Inicializacao do botao Jogar
        btStart = new JButton();
        btStart.setBounds(315, 275, imgStart.getIconWidth(), imgStart.getIconHeight());
        btStart.setText(null);
        btStart.setIcon(imgStart);
        btStart.setPressedIcon(imgStartPressed);
        btStart.setBorderPainted(false); //Tira a borda do botao
        btStart.setContentAreaFilled(false); //Tira o fundo branco do botao
        add(btStart);
        
        //Inicializacao do botao Instrucoes
        btInst = new JButton();
        btInst.setBounds(275, 325, imgInst.getIconWidth(), imgInst.getIconHeight());
        btInst.setText(null);
        btInst.setIcon(imgInst);
        btInst.setPressedIcon(imgInstPressed);
        btInst.setBorderPainted(false); 
        btInst.setContentAreaFilled(false);
        add(btInst);
        
        //Inicializacao do botao Opcoes
        btSets = new JButton();
        btSets.setBounds(314, 385, imgSets.getIconWidth(), imgSets.getIconHeight());
        btSets.setText(null);
        btSets.setIcon(imgSets);
        btSets.setPressedIcon(imgSetsPressed);
        btSets.setBorderPainted(false);
        btSets.setContentAreaFilled(false);
        add(btSets);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mudaTela == false) {
            Image img = banner.getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
