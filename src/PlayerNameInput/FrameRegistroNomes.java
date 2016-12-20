package PlayerNameInput;
import java.awt.Color;
/*import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/
import javax.swing.*;
import javax.swing.border.*;

import TopoNivel.MyActionListener;
public class FrameRegistroNomes extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String title = "Registro";
	public final int LARG_DEFAULT=300;
	public final int ALT_DEFAULT=280;
	private final float MARGIN;
	private static FrameRegistroNomes frame;
	private static final String BASE_ACTION_STRING = "CAPT";
	private static final String BUTTON_PRESSED_STRING = "CAPT_BUTTON_PRESSED";
	private static String PlayerNames[];
	private FrameRegistroNomes() {
		setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);

		JTextField campoP1 = new JTextField();
		JTextField campoP2 = new JTextField();
		
		JButton butt = new JButton("Começar");
		
		int Width = getWidth(); 
		int Height = getHeight();
		
		MARGIN = Width/8;
		
		
		campoP1.setBounds(Width/3,(int)MARGIN, Width*2/3-(int)MARGIN, Height/10);
		campoP2.setBounds(Width/3, 2*(int)MARGIN+Height/10, Width*2/3-(int)MARGIN, Height/10);
		
		campoP1.setBorder(new LineBorder(Color.GRAY));
		campoP2.setBorder(new LineBorder(Color.GRAY));
		campoP1.setEditable(true);
		campoP2.setEditable(true);
		
		add(campoP1,0);
		add(campoP2,1);
		
		butt.setSize( Width/3, Height/5);
		butt.setLocation(Width/2-butt.getWidth()/2, 3*(int)MARGIN+Height/5);
		butt.addActionListener(new MyActionListener());
		butt.setActionCommand(getButtonPressedString());
		add(butt,2);

		JLabel labelP1 = new JLabel("Player 1");
		JLabel labelP2 = new JLabel("Player 2");
		
		labelP1.setBounds((int)MARGIN,(int)MARGIN, Width/3-(int)MARGIN, Height/10);
		labelP2.setBounds((int)MARGIN, 2*(int)MARGIN+Height/10, Width/3-(int)MARGIN, Height/10);
		
		add(labelP1,3);
		add(labelP2,4);
		
		setDefaultLookAndFeelDecorated(true);
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static String getBaseActionString() {
		return BASE_ACTION_STRING;
	}
	public static String getButtonPressedString() {
		return BUTTON_PRESSED_STRING;
	}
	public static FrameRegistroNomes instance() {
		frame = new FrameRegistroNomes();
		return frame;
	}
	public static FrameRegistroNomes getInstance() {
		return frame;
	}
	
	
	public void receiveCommand(String message){
		
	}
	protected void safeTerminate() {
		PlayerNames = new String[]{((JTextField)(getInstance().getContentPane().getComponent(0))).getText(),((JTextField)(getInstance().getContentPane().getComponent(1))).getText()};
		for(int i=0;i<2;i++){
			if(PlayerNames[i].equals("")){
				PlayerNames[i] = "Player"+Integer.toString(i+1);
//				System.out.printf("\nCheguei PlayerNames[0] '%s' FrameRegistroNomes.safeTerminate()\n",PlayerNames[0]);
			}
		}
		
	}
	public String[] getPlayerNames() {
		return PlayerNames;
	}
	
	
}
