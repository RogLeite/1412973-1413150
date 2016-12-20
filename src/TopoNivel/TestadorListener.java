package TopoNivel;

import javax.xml.bind.Marshaller.Listener;

//import PlayerNameInput.FrameRegistroNomes;
import PlayerNameInput.FrameRegistroNomesListener;
import PosArmas.FrameArmasListener;

public class TestadorListener extends Listener{

	public static void receiveCommand(Class<?> class1) {
		if(class1.isAssignableFrom(FrameRegistroNomesListener.class)){
//			System.out.println("Cheguei nameRegisterDone in receiveCommand.TestadorListener");
			Testador.nameRegisterDone(FrameRegistroNomesListener.getPlayerNames());
		}
		else if(class1.isAssignableFrom(FrameArmasListener.class)){
			Testador.weaponsPlacementDone();
		}

	}
	public static String[] getPlayers(){
		return Testador.getPlayers();
	}


}
