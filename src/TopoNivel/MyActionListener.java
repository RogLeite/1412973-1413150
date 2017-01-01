package TopoNivel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import PlayerNameInput.FrameRegistroNomes;
import PlayerNameInput.FrameRegistroNomesListener;
import PosArmas.FrameArmas;
//import PosArmas.FrameArmas;
import PosArmas.FrameArmasListener;
import TabuleiroPartida.ExceptionBoardNotInstanced;
import TabuleiroPartida.FrameEmbateListener;
public class MyActionListener implements ActionListener{
	private String message;
	public void actionPerformed(ActionEvent e) {
		message = e.getActionCommand();
		Object o = e.getSource();
//		System.out.printf("\nCheguei message: '%s' MyActionListener.actionPerformed()\n",message);

		if(message.contains("GOTO")){
			//			System.out.println("Cheguei message.contains(GOTO) in MyActionListener.actionPerformed()");
			Testador.receiveCommand(message);
		}
		else if(message.contains(FrameEmbateListener.getThisActionCommand(MyActionListener.class))){
			//			System.out.println("Cheguei message.contains(MATCH) in MyActionListener.actionPerformed()");
			try {
				FrameEmbateListener.receiveCommand(message);
			} catch (ExceptionBoardNotInstanced e1) {
				FrameEmbateListener.instance(null);
				actionPerformed(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(message.contains(FrameRegistroNomesListener.getThisActionCommand(MyActionListener.class))){
			//			System.out.println("Cheguei message.contains(CAPT) in MyActionListener.actionPerformed()");
			FrameRegistroNomesListener.receiveCommand(message);
		}
		else if((FrameArmas)(JFrame)(((JPanel) ((JButton) o).getParent()).getParent().getParent().getParent()) instanceof FrameArmas){
			JPanel p = (JPanel) ((Component) o).getParent();
			JFrame f = (JFrame)(p.getParent().getParent().getParent());
			FrameArmas g = (FrameArmas)f;
			if(message.contains(g.getThisActionCommand(MyActionListener.class))){
				//			System.out.println("Cheguei message.contains(PLACE) in MyActionListener.actionPerformed()");
				g.receiveCommand(message);
			}
		}
	}

}
