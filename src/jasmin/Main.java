package jasmin;

import jas.jasError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * The Jasmin version
     */
    public static final String version = "v2.4";
    public static final boolean DEBUG = false;
	
	public static void main(String args[]){
		boolean generate_linenum = true; //genera automaticamente numero righe
        String fname = args[0];
        String class_name = args[1];
        File file = new File(fname);
		ClassFile classFile = new ClassFile();
        try {
            BufferedReader inp;
            {
              FileInputStream fs = new FileInputStream(fname);
              InputStreamReader ir;
                ir = new InputStreamReader(fs);
              inp = new BufferedReader(ir);
            }
            classFile.readJasmin(inp, file.getName(), generate_linenum);
            inp.close();
	}
        catch(Exception e){
        	System.err.println("Errore nella lettura del file "+file.getName());
        }
        File out_file = null;
        out_file = new File(class_name);
        FileOutputStream outp = null;
        try {
			outp = new FileOutputStream(out_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			classFile.write(outp);
	        outp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (jasError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}