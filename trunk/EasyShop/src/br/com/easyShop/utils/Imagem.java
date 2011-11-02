package br.com.easyShop.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Imagem {
	
	public byte[] ReadImageAsByteArray (String filename) throws IOException{  
//		     byte []buffer = new byte[1024];  
//		       
//		     InputStream is = this.getClass().getResourceAsStream( filename );  
//		     ByteArrayOutputStream out = new ByteArrayOutputStream();  
//		       
//		     while (is.read( buffer ) != -1)  {  
//		         out.write( buffer );  
//		     }  
//		     return out.toByteArray();  
		
		
		
		
		
		
		
		
//		        InputStream is = this.getClass().getResourceAsStream( filename );  
//		        int i, len=0;  
//		        byte bArray[] = new byte[500];  
//		        byte bArray2[];  
//		        byte b[] = new byte[1];  
//		        try {  
//		   
//		            while ( is.read(b) != -1 ){  
//		                if ( len+1 >= bArray.length ){  
//		     bArray2 = new byte[bArray.length];  
//		     for ( i = 0; i < len; i++ ){  
//		                        bArray2[i] = bArray[i];  
//		                    }              
//		     bArray = new byte[bArray2.length+500];  
//		     for ( i = 0; i < len; i++ ){  
//		                        bArray[i] = bArray2[i];  
//		                    }              
//		                }  
//		                bArray[len] = b[0];  
//		                len++;  
//		            }  
//		        } catch (IOException ex) {  
//		            ex.printStackTrace();  
//		        }  
//		        return bArray;  
		
		
		
		 File file = new File(filename);  
		 //File file = new File("C:\\Documents and Settings\\Jefferson\\Meus documentos\\catalogo\\publico\\fotos\\edson.jpg");  
		 FileInputStream fis = new FileInputStream(file);  
		  byte[] bytes = new byte [(int) file.length()];  
		  return bytes;
		  //fis.read (bytes);  
		
		 } 

}
