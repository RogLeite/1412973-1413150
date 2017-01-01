package PosArmas;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import TopoNivel.MyActionListener;

public class FrameArmasMenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	private static FrameArmasMenuBar instance;

	private FrameArmasMenuBar(){
		add(menuFile());
	}

	static FrameArmasMenuBar instanceEmbateMenuBar() {
		instance = new FrameArmasMenuBar();
		return instance;
	}
	private static JMenu menuFile(){
		
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem saveItem = new JMenuItem("Load Tables Layout");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		saveItem.setActionCommand(FrameArmasListener.getThisActionCommand(FrameArmasMenuBar.class));
		saveItem.addActionListener(new MyActionListener());
		menu.add(saveItem);
		
		return menu;
	}
}