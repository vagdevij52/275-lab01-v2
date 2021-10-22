package parser;

import java.text.ParseException;
import java.util.Scanner;
public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Enter filename");
		String fname = s.nextLine();
		MesonetProcessor mp = new MesonetProcessor();
		mp.processFile(fname);
	}

}
