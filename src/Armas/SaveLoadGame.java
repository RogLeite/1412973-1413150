package Armas;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Armas.Arma;
import Tabuleiro.TabuleiroInvisivel;
import Tabuleiro.TabuleiroListener;

public class SaveLoadGame {

	public static void SaveConjArmas (String player, TabuleiroInvisivel tabInv) throws IOException{
		System.out.printf("Cheguei SaveConjArmas.\n ");
		FileOutputStream fout = new FileOutputStream(player + ".txt");
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		
			
//			oout.writeInt(vect[i].getNumPartes());
//			oout.writeFloat(vect[i].getThisSize());		
//			oout.writeDouble(vect[i].getVectorLocation()[0].getX());
//			oout.writeDouble(vect[i].getVectorLocation()[0].getY());
//			oout.writeInt(vect[i].getRotate());
//			for(int j=0; j<vect[i].getNumPartes(); j++)
//				oout.writeBoolean(vect[i].isHitHere(vect[i].getVectorLocation()[j]));
			oout.writeObject(tabInv);
		oout.close();
	}

	public static TabuleiroInvisivel LoadConjArmas (String player) throws IOException, ClassNotFoundException{
		System.out.printf("Cheguei LoadConjArmas.\n ");
		FileInputStream finp = new FileInputStream(player + ".txt");
		ObjectInputStream oinp = new ObjectInputStream(finp);
		TabuleiroInvisivel tabInv;
//		TabuleiroInvisivel ti = TabuleiroInvisivel.newInstance(t.getSideTab(), t);
//		Arma a;
//		while(true) /*achar opção melhor*/{
//			int numPartes= oinp.readInt();
//			if( numPartes==-1) break;
//			float size= oinp.readFloat();
//			double x= oinp.readDouble();
//			double y= oinp.readDouble();
//			int rotate= oinp.readInt();
//			//decide tipo Arma e instancia
//			if (numPartes==3)
//				a=new Hidroaviao((int)size, (int)size);
//			else
//				a=new Navio(TipoArma.getnome(numPartes),(int)size, (int)size);
//			a.setRotate(rotate);
//			a.setLocation((float)x,(float) y);
//			for(int j=0; j<a.getVectorLocation().length; j++)
//				if(oinp.readBoolean())
//					a.Atingir(a.getVectorLocation()[j]);
//			ConjArmas.addArma(c,a);
		
//		}
			tabInv = (TabuleiroInvisivel)oinp.readObject();
			//*****************************
			// usa esse ti
	//		ti.receiveArma();
			//**********************
		//transferirTabuleiroInvisivel();
		oinp.close();
		finp.close();
		return tabInv;

	}
}
