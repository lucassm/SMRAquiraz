package model;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Arquivo {
	
	
	/**
	 * 
	 * @param caminho
	 * @return text
	 * @throws IOException
	 */
	public String lerArquivo(String caminho) throws IOException{
		try {
			DataInput a = new DataInputStream(new FileInputStream(caminho));
			String line;
			String texto = "";
			while((line = a.readLine()) != null){
				StringBuffer linhaModificada = new StringBuffer(line);
				texto = texto+line;
			}
			return texto;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param texto
	 * @param caminho
	 * @throws IOException
	 */
	public void escreveArquivo(String texto,String caminho) throws IOException{
		try {
			DataOutput b = new DataOutputStream(new FileOutputStream(caminho));
			StringBuffer stringTexto = new StringBuffer(texto);
			b.writeBytes(stringTexto+"\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}