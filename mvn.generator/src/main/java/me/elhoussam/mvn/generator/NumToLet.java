package me.elhoussam.mvn.generator;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class NumToLet {
	Boolean Color = false;
	static String space = " ";
	protected String link;
	protected Hashtable<Integer, String> BasicNumber = null; 
	protected String ScaleNombre[] = null ;
	static String ColorAnsi[]= {"\u001B[31m","\u001B[32m","\u001B[33m","\u001B[34m","\u001B[35m","\u001B[36m","\u001B[38m","\u001B[0m" };
	
	
	protected void Init( Hashtable<Integer, String> ht, String scalenombre[], String Link) {
		BasicNumber = (Hashtable<Integer, String>) ht.clone();
		ScaleNombre = scalenombre.clone() ;
		link = Link.toString();
	}	
	/*
	 * ScanInput : to read long from the user
	 */
	@SuppressWarnings("resource")
	public static Long ScanInput() {
		Scanner sc  = new Scanner(System.in);
		Long a = 0L ; Boolean checker= false ;
		while( !checker ) {
			try{print("# Number : "); a = sc.nextLong() ; 
			checker=(a<0)?false:true;
			print((!checker)?ColorAnsi[0]+"\tshould be greater then or equal zero\n"+ColorAnsi[ColorAnsi.length-1]:"" );
			}catch(Exception e ){ sc.next(); print(ColorAnsi[3]+"\tthis number is too long\n"+ColorAnsi[ColorAnsi.length-1]);	}
		}
		return a;
	}
	/*
	 * get : to return the corresponding string that match with input byte 
	 * */
	protected String get(byte i) {
		String s = BasicNumber.get((int) i );
		return s ;
	}
	/*
	 * ToggleColor : to activate color feature in output
	 * */
	public boolean ToggleColor() {
		Color = ! Color ;
		return Color ;
	}
	// Print function that simply print str in the console
	public static void print( Object obj ) {
		System.out.print( obj );
	}
	/*
	 * inner class NB : that store the number with it scale 
	 * */
	class NB{
		short nb; // number under 999 
		String str ; // scale hundred million, milliard .....
		public NB(short a/*short*/, String b) {
			this.nb=a; this.str =b;
		}

	}
	/*
	 * Extractor : 
	 * 			that generating vector from a giving input nombre
	 * 			by dividing the input number into elementary number 
	 * */
	protected ArrayList<NB> Extractor(long InputNombre){
		ArrayList<NB> myvec = new ArrayList<NB>();
		long innerVal =  InputNombre ; 
		byte i=0;
		while( innerVal > 0 ){
			short localExtractorVal = ExtractorValue(i);
			short localvar = (short) (innerVal % localExtractorVal ) ;
			if ( localvar > 0 ){
				// parsing the different part of the numbre
				NB o = new NB(localvar,
			    	((Color)?ColorAnsi[i]:"").concat( 
			    	ScaleNombre[i]).concat(
			    	(Color)?ColorAnsi[ColorAnsi.length-1]:"") 
			    	); 
				myvec.add(o);
			}i++;
			innerVal = innerVal / localExtractorVal ;
		}
		return myvec;
	}
	protected short ExtractorValue(byte nb) {
		return 1000;
	}
	/*
	 * Constructor : taking the vector generated by prev method
	 * 				and start generating the corresponding string
	 * 				with the giving vector
	 * */
	protected String Constructor(ArrayList<NB> InputVec) {
		//print("NumTo : Constructor");
		String mystr = "";
		for(byte i=0; i < InputVec.size() ; i++) {
			String localstr="";
			if ( i > 0 )
				localstr = this.link ; // if en => and , fr => et
			NB o = InputVec.get(i) ;
			mystr = (
					((o.nb==1 && o.str.contains(ScaleNombre[1]))?
							"":BasicParser(o.nb)+space ) // to prevent the one hundred, On Thousand
					.concat( o.str )+space +localstr+space+mystr
					);
			
		}
		return mystr;
	}
	/*
	 * Generate Function : start devide the number and store into
	 * 	smaller pieces with it scale int NB's Objects
	 * 
	 * */
	public String Generate(long InputNombre){
		String innerStr = ( this.Constructor( Extractor( InputNombre ) ) );
		String finalresult = RemoveDoubleSpaces( (InputNombre != 0)? innerStr : TensParser( (short) InputNombre )) ;
		print( "["+finalresult+"]" );
		return finalresult;
	}
	/*
	 * RemoveDoubleSpaces : Remove double spaces in the input string 
	 * */
	private String RemoveDoubleSpaces(String input) {
		String localString = input.trim();
		while( localString.contains("  ") ) {
			localString = localString.replaceFirst("  "," ");
		}
		return localString ;
	}
	protected abstract String BasicParser( short inputNombre );
	protected abstract String TensParser(short inputNombre);
	protected abstract String HundredParser(short inputNombre);	
}
/*
 * int 2 147 483 647 : 2 milliard => 10^9
 * long 9 223 372 036 854 775 807 : 9 Trillion => 10^18
 * */