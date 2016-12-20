package PlayerNameInput;

import javax.swing.JFrame;
import javax.xml.bind.Marshaller.Listener;

//import TabuleiroPartida.FrameEmbateMenuBar;
import TopoNivel.MyActionListener;
import TopoNivel.TestadorListener;

public class FrameRegistroNomesListener extends Listener{
	public static String getThisActionCommand(Class<?> class1) {
		if(class1.isAssignableFrom(MyActionListener.class)){
			return FrameRegistroNomes.getBaseActionString();
		}
		return "<in FrameRegistroNomesListener>YOU DON'T KNOW WHAT YOU'RE LOOKING FOR!";
	}

	public static void receiveCommand(String m) {
		
		if(m.equals(FrameRegistroNomes.getButtonPressedString())){
//			System.out.println("Cheguei equals CAPT_BUTTON_PRESSED in receiveCommand.FrameRegistroNomesListener");
			FrameRegistroNomes.getInstance().safeTerminate();
			TestadorListener.receiveCommand(FrameRegistroNomesListener.class);
		}
	}

	public static JFrame instance() {
		return FrameRegistroNomes.instance();
	}

	public static String[] getPlayerNames() {
		return FrameRegistroNomes.getInstance().getPlayerNames();
		
	}
	
	
	
	
	
}
