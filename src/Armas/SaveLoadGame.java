package Armas;
import java.io.FileInputStream;
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
			oout.writeInt(vect[i].getNumPartes());
			oout.writeFloat(vect[i].getThisSize());		
			oout.writeDouble(vect[i].getVectorLocation()[0].getX());
			oout.writeDouble(vect[i].getVectorLocation()[0].getY());
			oout.writeInt(vect[i].getRotate());
			for(int j=0; j<vect[i].getNumPartes(); j++)
				oout.writeBoolean(vect[i].isHitHere(vect[i].getVectorLocation()[j]));
		}
		oout.close();
	}

	public static ConjArmas LoadConjArmas (String player) throws IOException, ClassNotFoundException, ExceptionArmVectFilled{
		FileInputStream finp = new FileInputStream(player + ".tmp");
		ObjectInputStream oinp = new ObjectInputStream(finp);
		ConjArmas c=ConjArmas.getEmptyArray();
		Arma a;
		while(true) /*achar opção melhor*/{
			int numPartes= oinp.readInt();
			if( numPartes==-1) break;
			float size= oinp.readFloat();
			double x= oinp.readDouble();
			double y= oinp.readDouble();
			int rotate= oinp.readInt();
			//decide tipo Arma e instancia
			if (numPartes==3)
				a=new Hidroaviao((int)size, (int)size);
			else
				a=new Navio(TipoArma.getnome(numPartes),(int)size, (int)size);
			a.setRotate(rotate);
			a.setLocation((float)x,(float) y);
			for(int j=0; j<a.getVectorLocation().length; j++)
				if(oinp.readBoolean())
					a.Atingir(a.getVectorLocation()[j]);
			ConjArmas.addArma(c,a);
		}
		oinp.close();
		finp.close();
		return c;

	}
}
