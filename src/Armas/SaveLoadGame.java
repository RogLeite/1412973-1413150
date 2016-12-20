package Armas;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Armas.Arma;

public class SaveLoadGame {

	public static void SaveConjArmas (String player, ConjArmas a) throws IOException{
		FileOutputStream fout = new FileOutputStream(player + ".tmp");
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		Arma vect[]=a.getArmVec();

		for(int i=0; i<vect.length; i++){
			oout.writeFloat(vect[i].getThisSize());
			oout.writeInt(vect[i].getNumPartes());
			oout.writeDouble(vect[i].getVectorLocation()[0].getX());
			oout.writeDouble(vect[i].getVectorLocation()[0].getY());
			oout.writeInt(vect[i].getRotate());
			for(int j=0; j<vect[i].getNumPartes(); j++)
				oout.writeBoolean(vect[i].isHitHere(vect[i].getVectorLocation()[j]));
		}
		oout.close();
	}

	public static ConjArmas LoadConjArmas (String player) throws IOException, ClassNotFoundException{
		FileInputStream finp = new FileInputStream(player + ".tmp");
		ObjectInputStream oinp = new ObjectInputStream(finp);
		ConjArmas c=ConjArmas.getEmptyArray();
		Arma a;
		while(true) /*achar opção melhor*/{
			float size= oinp.readFloat();
			int numPartes= oinp.readInt();
			double x= oinp.readDouble();
			double y= oinp.readDouble();
			int rotate= oinp.readInt();
			a=new Arma(numPartes, TipoArma.getnome(numPartes).getColor(),size);
			a.setRotate(rotate);
			a.setLocation((float)x,(float) y);
			for(int j=0; j<a.getVectorLocation().length; j++)
				if(oinp.readBoolean())
					a.Atingir(a.getVectorLocation()[j]);
			ConjArmas.addArma(c,a);
		}
		oinp.close();
		return c;

	}
}
