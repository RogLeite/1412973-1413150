package TopoNivel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import PlayerNameInput.FrameRegistroNomes;
import PlayerNameInput.FrameRegistroNomesListener;
//import PosArmas.FrameArmas;
import PosArmas.FrameArmasListener;


import TabuleiroPartida.FrameEmbateListener;
public class MyActionListener implements ActionListener{
	private String message;
	public void actionPerformed(ActionEvent e) {
		message = e.getActionCommand();
		System.out.printf("\nCheguei message: '%s' MyActionListener.actionPerformed()\n",message);
	
		if(message.contains("GOTO")){
//			System.out.println("Cheguei message.contains(GOTO) in MyActionListener.actionPerformed()");
			Testador.receiveCommand(message);
		}
		else if(message.contains(FrameEmbateListener.getThisActionCommand(MyActionListener.class))){
//			System.out.println("Cheguei message.contains(MATCH) in MyActionListener.actionPerformed()");
			FrameEmbateListener.receiveCommand(message);
		}
		else if(message.contains(FrameRegistroNomesListener.getThisActionCommand(MyActionListener.class))){
//			System.out.println("Cheguei message.contains(CAPT) in MyActionListener.actionPerformed()");
			FrameRegistroNomesListener.receiveCommand(message);
		}
		else if(message.contains(FrameArmasListener.getThisActionCommand(MyActionListener.class))){
//			System.out.println("Cheguei message.contains(PLACE) in MyActionListener.actionPerformed()");
			FrameArmasListener.receiveCommand(message);
		}
	}

}
