package TopoNivel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

//import PlayerNameInput.FrameRegistroNomes;
import PlayerNameInput.FrameRegistroNomesListener;
//import PosArmas.FrameArmas;
import PosArmas.FrameArmasListener;
//import TabuleiroPartida.FrameEmbate;
import TabuleiroPartida.FrameEmbateListener;

public class Testador{
//	private static String COMM1 = "CAPT_GOTO_MATCH";
//	private static String COMM2 = "CAPT_GOTO_POS_ARMAS";
//	private static String COMM3 = "PLACE_POS_GOTO_ARMAS_P2";
//	private static String COMM4 = "PLACE_GOTO_BATTLE";
//	private static String P1Name = "Player 1";
//	private static String P2Name = "Player 2";
	private static String PlayerNames[];
	private static int placements = 0;
	private static int sl;
	private static int sa;
	private static int x;
	private static int y;
	private static JFrame main;
	public static void main(String[] args) {
		beginGame();
	}
	private static void beginGame(){		//Frame de Registro
		main = FrameRegistroNomesListener.instance();
		recalculateLocation();
		main.setLocation(x,y);
		main.setLayout(null);
		main.setVisible(true);
	}
	public static void receiveCommand(String s){	
//		if(s.compareTo(COMM2)==0){	//GOTO Frame de pos armas player 1
//			main.setVisible(false);
//			main = FrameArmas.instance(COMM3, P1Name);
////			recalculateLocation();
////			main.setLocation(x,y);
////			main.setLayout(null);
////			main.setVisible(true);
//		}
//		if(s.compareTo(COMM3)==0){	//GOTO Frame de pos armas player 2
//			main.setVisible(false);
//			main = FrameArmas.instance(COMM4, P2Name);
////			recalculateLocation();
////			main.setLocation(x,y);
////			main.setLayout(null);
////			main.setVisible(true);
//		}
//		if(s.compareTo(COMM4)==0){				//GOTO Frame de batalha
//			main.setVisible(false);
//			main = FrameEmbate.instance();
////			recalculateLocation();
////			main.setLocation(x,y);
////			main.setLayout(null);
////			main.setVisible(true);
//		}
//		recalculateLocation();
//		main.setVisible(true);

	}

	private static void recalculateLocation(){
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		sl=screenSize.width;
		sa=screenSize.height;
		x=sl/2-(main.getWidth()/2);
		y=sa/2-(main.getHeight()/2);
		main.setLocation(x,y);
		main.setLayout(null);
	}
	public static void nameRegisterDone(String Names[]) {
		PlayerNames = Names;

//		System.out.printf("\nCheguei PlayerNames[0] '%s' Testador.nameRegisterDone()\n",PlayerNames[0]);
//		System.out.printf("\nCheguei PlayerNames[1] '%s' Testador.nameRegisterDone()\n",PlayerNames[1]);
		startPlacement();
	}
	private static void startPlacement() {
		main.setVisible(false);
		main = FrameArmasListener.instance(PlayerNames[placements]);
		recalculateLocation();
		main.setVisible(true);
	}
	public static void weaponsPlacementDone() {
		placements++;
		if(placements<PlayerNames.length){
			startPlacement();
		}
		else{
			startMatch();
		}
		
	}
	private static void startMatch() {
		main.setVisible(false);
		main = FrameEmbateListener.instance(PlayerNames);
		recalculateLocation();
		main.setVisible(true);
	}
	public static String[] getPlayers() {
		return PlayerNames;
	}
	

}
