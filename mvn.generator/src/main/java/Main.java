
import static me.elhoussam.mvn.generator .NumToLet.print;

import me.elhoussam.mvn.generator.GenAr;
import me.elhoussam.mvn.generator.GenEn;
import me.elhoussam.mvn.generator.GenFr;
import me.elhoussam.mvn.generator.GenInd;
import me.elhoussam.mvn.generator.NumToLet;

public class Main {
	public static void main(String argv[]) throws Exception{
		NumToLet obj ;
		long a = 1 ;
		boolean bool = false ;
		while ( true ) {
		String aa = "";
		a = NumToLet.ScanInput();
		//obj= new GenEn(); 	print("En : ");  aa =obj.Generate( a ); print("\n");
		//	obj= new GenFr(); 	print("FR : ");	aa =obj.Generate( a ); print("\n");
		//obj= new GenAr(); 	print("Ar : ");	aa = obj.Generate( a ); print("\n");
		obj= new GenInd(); 	print("Ind : ");	aa = obj.Generate( a ); print("\n");
		aa = aa.trim() ;
		obj.ToggleColor();
		
		}
		
	}
	
}
