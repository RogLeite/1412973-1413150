package PosArmas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;


import Tabuleiro.ExceptionCellAlreadyFilled;
import Tabuleiro.Tabuleiro;
import Tabuleiro.TabuleiroListener;
import TabuleiroPartida.ExceptionBoardNotInstanced;
import TabuleiroPartida.FrameEmbateListener;
import TopoNivel.GameFrame;
import TopoNivel.MyActionListener;
import TopoNivel.MyMouseListener;
import TopoNivel.TestadorListener;
import Armas.Arma;

//import Tabuleiro.TabuleiroInvisivel;

import Armas.ArmaListener;
import Armas.ConjArmas;

public class FrameArmas extends GameFrame {

	private static final long serialVersionUID = 1L;
	private static FrameArmas tabuleiro;
	private static TabuleiroArmas P;
	private static JLabel campoP;
	private static JButton butt;

	//	private static TabuleiroInvisivel T;
	private static final String LOAD_STRING = "MATCH_LOAD_BOARDS";
	private static final String BASE_ACTION_STRING = "PLACE";
	private static final String TAKE_ACTION_STRING = "PLACE_TAKE_ACTION";
	private static final String PLACEMENT_DONE_STRING = "PLACE_DONE";
	private static float SIZE_FACTOR = 20;
	private static float ALT_DEFAULT = 16*SIZE_FACTOR;
	private static float MARGIN = 1*SIZE_FACTOR;
	private static float LARG_DEFAULT = 2*ALT_DEFAULT;
	private static String[] TEXTS = {"Posicione suas armas!"};
	private static int QTD_FRAMES = 1;
	private static ArmasPickPanel APanel;
	public FrameArmas(String name){
		super();
		CURR_FRAME = "CAPT";
		setBounds(0,0,(int)((LARG_DEFAULT+(MARGIN*4))+20),(int)(ALT_DEFAULT+(MARGIN*8)));
		P = new TabuleiroArmas((int)(ALT_DEFAULT+3*MARGIN),(int)MARGIN, ALT_DEFAULT, getPlayerOption(QTD_FRAMES++),this);
		P.setLayout(null);
		P.setVisibilidade(true);
		getContentPane().add(P,getContentPane().getComponentCount());
		//		vide paintComponent

		campoP = new JLabel(name + ", " + TEXTS[0]);
		campoP.setSize((int)(MARGIN*7 + name.length()*8), (int)(MARGIN*2));
		campoP.setLocation((int)(getWidth()-campoP.getWidth())/2,(int)(getHeight()-MARGIN*7));

		getContentPane().add(campoP,getContentPane().getComponentCount());

		butt = new JButton("Pronto!");
		butt.setSize((int)(MARGIN*7), (int)(MARGIN*2));
		butt.setLocation((int)(getWidth()-butt.getWidth())/2,(int)(getHeight()-MARGIN*5));
		butt.addActionListener(new MyActionListener());
		butt.setActionCommand(getPlacementDoneString());
		butt.setIgnoreRepaint(true);
		butt.setLayout(null);
		getContentPane().add(butt,getContentPane().getComponentCount());

		//		T = new TabuleiroInvisivel((int)(ALT_DEFAULT+MARGIN),(int)MARGIN, ALT_DEFAULT);
		//		T.setLayout(null);
		//		T.addMouseListener(new MyMouseListener(getTakeActionString()));
		//		ArmaListener.addArmas(T, SIZE_FACTOR);
		//		getContentPane().add(T,getContentPane().getComponentCount());

		APanel = ArmasPickPanel.instance(0, 0,getWidth()/2,ALT_DEFAULT);
		/*ConjArmas CArmas = new ConjArmas();
		CArmas.ArmVect= ArmaListener.getFilledArray((float)getAPanel().getWidth(),(int) ALT_DEFAULT/16);

//		System.out.printf("getComponentCount = %d\n",getContentPane().getComponentCount());
//		System.out.printf("CArmas.ArmVect.length = %d\n",CArmas.ArmVect.length);
		for(int i=0;i<CArmas.ArmVect.length;i++){
//			System.out.printf("3+i = %d\n",3+i);
			APanel.add((CArmas.ArmVect[i]));
		}*/
		APanel.addMouseListener(new MyMouseListener(getTakeActionString()));
		addMouseListener(new MyMouseListener(getTakeActionString()));
		add(APanel);
		setTitle("Posicionamento das Armas "+name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);

	}

	static String getTakeActionString() {
		return TAKE_ACTION_STRING;
	}
	static String getPlacementDoneString() {
		return PLACEMENT_DONE_STRING;
	}


//	public static void drawMessages(Graphics g, String s){
//		System.out.printf("Cheguei DrawMessage (%s) in FrameArmas", s);
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.drawString(s, (LARG_DEFAULT-MARGIN*7-s.length()*8)/2, (ALT_DEFAULT-MARGIN*2)/2);
//		//(getWidth()-campoP.getWidth())/2,(int)(getHeight()-MARGIN*7)
//	//	(int)(MARGIN*7 + name.length()*8), (int)(MARGIN*2));
//	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(!getCanPlay())
			g2d.drawString("PLAY DENIED", getWidth()/2, 2*getHeight()/3);
		//		g2d.drawString(TEXTS[0],getWidth()-MARGIN*7/2/*- TEXTS[0].length()*charlenght*/, getHeight()/2/*-MARGIN*7*/ );
	}

	public FrameArmas instance(String name){
		tabuleiro = new FrameArmas(name);
		return tabuleiro;
	}

	public static FrameArmas getInstance() /*throws ExceptionNotInstanced*/{
		//		if(tabuleiro==null)
		//			throw new ExceptionNotInstanced();
		return tabuleiro;
	}


	public void receiveCommand(String m) {
		if(m.equals(FrameArmas.getPlacementDoneString())){
			FrameArmas.safeTerminate();
			TestadorListener.receiveCommand(FrameArmasListener.class);
		}

	}


	/*	private static void posArmas (int x) {
	}
	 */
	public String getCurrFrame() {
		return CURR_FRAME;
	}

	public void denyedPlay() {
		setCanPlay(false);

	}

	public void takeAction( Point p) {
		//		System.out.printf("\nCheguei FrameArmas.takeAction(%s)\n",p.toString());
		try {
			((TabuleiroArmas) (getInstance().getContentPane().getComponent(0))).takeAction(p);

			return;
		} catch (ExceptionCellAlreadyFilled e) {
			//			System.out.println("\nCheguei Exception FrameArmas.takeAction()");
			return;
		}
	}
	public void takeAction(String bound_player, Point p) {}

	public static String getBaseActionString() {		
		return BASE_ACTION_STRING;
	}
	public static String getLoadString(){
		return LOAD_STRING;
	}

	public static void safeTerminate() {
		ArmaListener.confirmedReceive();
	}
	private ArmasPickPanel getAPanel(){
		return APanel;
	}

	public Tabuleiro getBoard() {
		return (Tabuleiro) getContentPane().getComponent(0);
	}

	public void rotatePiece() {
		System.out.println("Cheguei FrameArmas.rotatePiece()");
		getAPanel().rotatePiece();
		getBoard().hoveredHere(getBoard().getMousePosition());
	}
	public void selectArma(Point p){
		ArmasPickPanel.getInstance().selectArmaAqui(p);
	}
	public static String getThisActionCommand(Class<?> class1){
		if(class1.isAssignableFrom(MyMouseListener.class)){
			System.out.println("Cheguei MyMouseListener FrameArmasListener.getThisActionCommand()");
			return FrameArmas.getTakeActionString();
		}
		else if(class1.isAssignableFrom(MyActionListener.class)){
//			System.out.println("Cheguei MyActionListener FrameArmasListener.getThisActionCommand()");
			return FrameArmas.getBaseActionString();
		}
		else if(class1.isAssignableFrom(FrameArmasMenuBar.class)){
			System.out.println("Cheguei FrameArmasMenuBar string FrameArmasListener.getThisActionCommand()");
			return FrameEmbateListener.getThisActionCommand(FrameArmasListener.class);
		}
		return "<in FrameArmasListener>YOU DON'T KNOW WHAT YOU'RE LOOKING FOR!";
	}
	public void rememberBoard(int board) {
		try {
			getBoard().transferirTabuleiroInvisivel(FrameEmbateListener.getThisBoard(FrameEmbateListener.getInstance(),board),getBoard());
		} catch (ExceptionBoardNotInstanced e) {
			FrameEmbateListener.instance(getAllPlayers());
		}
	}

	public void receberArma(Arma a) {
		((ArmasPickPanel)(getContentPane().getComponent(3))).devolverArma(a);
	}
}

