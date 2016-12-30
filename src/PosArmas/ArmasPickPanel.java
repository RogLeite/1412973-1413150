package PosArmas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import Armas.ArmaListener;
import Armas.ConjArmas;
import Armas.ExceptionComponentIsNotArma;
import Armas.ExceptionNoWeaponSelected;
import Armas.ExceptionWeaponAllreadySelected;

public class ArmasPickPanel extends JPanel{
	private static ArmasPickPanel APanel;
	private ArmasPickPanel(float x, float y, float width, float height){
		super();
		setBounds((int) x, (int) y, (int) width, (int) height);

		ConjArmas CArmas = new ConjArmas();
		CArmas.ArmVect= ArmaListener.getFilledArray((int)(height/16));
		setLayout(null);
		setIgnoreRepaint(true);
		setVisible(true);
//		System.out.printf("getComponentCount = %d\n",getContentPane().getComponentCount());
//		System.out.printf("CArmas.ArmVect.length = %d\n",CArmas.ArmVect.length);
		for(int i=0;i<CArmas.ArmVect.length;i++){
			System.out.printf("(CArmas.ArmVect[%d]==null) = %s ArmasPickPanel.ArmasPickPanel()\n",i,Boolean.toString(CArmas.ArmVect[i]==null));
			System.out.printf("CArmas.ArmVect[%d].getLocation() = %s ArmasPickPanel.ArmasPickPanel()\n",i,CArmas.ArmVect[i].getLocation().toString());
			add((CArmas.ArmVect[i]));
		}
	}
	public static ArmasPickPanel instance(float x, float y, float width, float height){
		APanel = new ArmasPickPanel(x,y,width, height);
		return APanel;
	}
	public static ArmasPickPanel getInstance(){
		System.out.println("Cheguei ArmasPickPanel.getInstance");
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
			System.out.println("Cheguei ArmasPickPanel.selectArmaAqui");
			ArmaListener.selectArma(getComponentAt(p));
			getComponentAt(p).setVisible(false);
			this.remove(getComponentAt(p));
			System.out.println("Cheguei repaint ArmasPickPanel.selectArmaAqui");
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
			System.out.println("Cheguei ArmasPickPanel.rotatePiece()");
			ArmaListener.rotatePiece();
		} catch (ExceptionNoWeaponSelected e) {
			System.out.println("Cheguei ExceptionNoWeaponSelected in ArmasPickPanel.rotatePiece()");
			return; 
		}
	}

}
