package game;

import javax.swing.ImageIcon;

public class Objeto {
    
	protected int x, y;
	protected int largura, altura;
	protected int altMin, altMax;
	protected int velocX, velocY;
	protected boolean ativo;
	protected ImageIcon parado;
	protected ImageIcon nadando;

	public Objeto(int x, int y, int largura, int altura, int velocX, int velocY, boolean ativo) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.velocX = velocX;
		this.velocY = velocY;
		this.ativo = ativo;
	}

	// Move o personagem para cima
	public void moveUp() {
		if (this.y + this.altura >= this.altMin && y + this.altura <= this.altMax)
			this.y -= this.velocY;
		if(this.y + this.altura >= altMax)
    		this.y = altMax-altura;
	}

	// Move o personagem para baixo
	public void moveDown() {
		if (this.y + this.altura >= this.altMin && y + this.altura <= this.altMax)
			this.y += this.velocY;
		if(this.y + this.altura < altMin)
    		this.y = altMin-altura;
	}

	// Move o personagem para direita
	public void moveRight() {
		if (this.y >= 0 && this.y + this.largura <= 850)
			this.x += this.velocX;
	}

	// Move o personagem para esquerda
	public void moveLeft() {
		if (this.y >= 0 && this.y + this.largura <= 850)
			this.x -= this.velocX;
	}
}
