package TopoNivel;

import java.awt.Point;

import javax.swing.JFrame;


public abstract class GameFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final String NEUTRAL = "P0";
	private int vet_index = 0;
	private String CURR_PLAYER;
	private boolean can_play = true;
	protected static String CURR_FRAME=null;

	public abstract String getCurrFrame()/* throws ExceptionFrameNonExistant*/;
	public abstract void denyedPlay();
	public abstract void takeAction(Point p);
	public abstract void takeAction(String bound_player,Point p);
	protected abstract void cellHit();
	
	public String getPlayerOption(int i){
		//		System.out.printf("Cheguei GameFrame.getPlayerOption(%d)\n",i);
		//		System.out.printf("\tNome: %s",PLAYER_OPTIONS[i-1]);
//		return PLAYER_OPTIONS[i-1];
		return TestadorListener.getPlayers()[i-1];
	}
	public String getCurrPlayer(){
		//		System.out.println("Cheguei GameFrame.getCurrPlayer()");
//		System.out.printf("\n\t vet_index: %d",vet_index);
		return CURR_PLAYER;
	}
	public boolean currPlayerIsNeutral(){
		if(CURR_PLAYER == NEUTRAL){
			return true;
		}
		return false;
	}
	public void setNeutralPlayer(){
		CURR_PLAYER = NEUTRAL;
	}
	public void nextPlayer(){ 
		if ((vet_index>=getAllPlayers().length||vet_index<=0)){
			//			System.out.println("Cheguei dentro if GameFrame.nextPlayer()");
			vet_index = 1;
		}
		else{
			//		System.out.println("Cheguei GameFrame.nextPlayer()");
			vet_index++;
		}
//		System.out.printf("\nCheguei CURR_PLAYER : '%s' GameFrame.nextPlayer()\n",getPlayerOption(vet_index));
		CURR_PLAYER = getPlayerOption(vet_index);
	}
	private String[] getAllPlayers() {
		
		return TestadorListener.getPlayers();
	}
	public boolean getCanPlay(){
		return can_play;
	}
	public void setCanPlay(boolean b){
		can_play = b;
	}
}
