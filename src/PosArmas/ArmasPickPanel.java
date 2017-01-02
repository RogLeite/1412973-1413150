package PosArmas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import Armas.Arma;
import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionComponentIsNotArma;
import Armas.ExceptionNoWeaponSelected;
import Armas.ExceptionWeaponAllreadySelected;

public class ArmasPickPanel extends JPanel{
	private static ArmasPickPanel APanel;
	private FrameArmas frameMae;
	private ArmasPickPanel(float x, float y, float width, float height,FrameArmas fM){
		super();
		setBounds((int) x, (int) y, (int) width, (int) height);

		ConjArmas CArmas = new ConjArmas();
		CArmas.ArmVect= ArmaListener.getFilledArray((int)(height/16));
		setLayout(null);
		setIgnoreRepaint(true);
		setVisible(true);
		frameMae = fM;
//		System.out.printf("getComponentCount = %d\n",getContentPane().getComponentCount());
//		System.out.printf("CArmas.ArmVect.length = %d\n",CArmas.ArmVect.length);
		for(int i=0;i<CArmas.ArmVect.length;i++){
//			System.out.printf("(CArmas.ArmVect[%d]==null) = %s ArmasPickPanel.ArmasPickPanel()\n",i,Boolean.toString(CArmas.ArmVect[i]==null));
//			System.out.printf("CArmas.ArmVect[%d].getLocation() = %s ArmasPickPanel.ArmasPickPanel()\n",i,CArmas.ArmVect[i].getLocation().toString());
			add((CArmas.ArmVect[i]));
		}
	}
	public static ArmasPickPanel instance(float x, float y, float width, float height,FrameArmas frameMae){
		APanel = new ArmasPickPanel(x,y,width, height, frameMae);
		return APanel;
	}
	public static ArmasPickPanel getInstance(){
//		System.out.println("Cheguei ArmasPickPanel.getInstance");
		if(APanel==null){
			System.out.println("Cheguei APanel = null\t ArmasPickPanel.getInstance");
		}
		return APanel;
	}
	public void paintComponent(Graphics g){

		Graphics2D g2d = (Graphics2D) g;/*
		g2d.setColor(Color.GRAY);
		g2d.fill(getBounds());*/
	}
	public void selectArmaAqui(Point p){
		try{
//			System.out.println("Cheguei ArmasPickPanel.selectArmaAqui");
			if(!(getComponentAt(p) instanceof Arma)){
				throw new ExceptionComponentIsNotArma();
			}
			Arma a = (Arma) getComponentAt(p);
			ArmaListener.selectArma(a);
			a.setVisible(false);
			Rectangle pa = a.getBounds();
			this.remove(a);
			Graphics2D g2d = (Graphics2D)getGraphics();
			g2d.clearRect((int)pa.getX(),(int)pa.getY(),(int)pa.getWidth(),(int)pa.getHeight());
//			System.out.println("Cheguei repaint ArmasPickPanel.selectArmaAqui");
//			repaint(getBounds());
		}catch (ExceptionWeaponAllreadySelected e) {
			System.out.println("Cheguei ExceptionWeaponAllreadySelected in ArmasPickPanel.selectArmaAqui");
			return;
		}catch (ExceptionComponentIsNotArma e) {
						System.out.println("Cheguei ExceptionComponentIsNotArma in ArmasPickPanel.selectArmaAqui");
			return;
		}	
	}
	public void rotatePiece() {
		try {
//			System.out.println("Cheguei ArmasPickPanel.rotatePiece()");
			ArmaListener.rotatePiece();
		} catch (ExceptionNoWeaponSelected e) {
			System.out.println("Cheguei ExceptionNoWeaponSelected in ArmasPickPanel.rotatePiece()");
			return; 
		}
	}
	public void devolverArma(Arma a) {
		Graphics2D g2d = (Graphics2D)(/*frameMae.*/getGraphics());
		g2d.clearRect(a.getX(),a.getY(),a.getWidth(),a.getHeight());
		a.setVisible(true);
		add(a);
		a.repaint(0,0,a.getWidth(),a.getHeight());
//		repaint(this.getX()+1,this.getY()+1,this.getWidth()-1,this.getHeight()-1);
//		getComponentAt(r.getLocation()).repaint();
	}
}
