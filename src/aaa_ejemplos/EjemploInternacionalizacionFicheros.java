package aaa_ejemplos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EjemploInternacionalizacionFicheros
{
	public static void main(String[] args) throws IOException
	{
		Properties propiedades = new Properties();
		FileInputStream esp = null;
	    FileInputStream en = null;
	    
	    // tiene que estar accesible el fichero
	    esp = new FileInputStream("/home/david/Documentos/workspace/D_Interfaces/src/aaa_ejemplos/Etiquetas.properties");

        // cargamos el archivo de propiedades
        propiedades.load(esp);
		System.out.println(propiedades.getProperty("etiqueta_boton_abrir"));
		
		// tiene que estar accesible el fichero
		en = new FileInputStream("/home/david/Documentos/workspace/D_Interfaces/src/aaa_ejemplos/Etiquetas_es.properties");

        // cargamos el archivo de propiedades
        propiedades.load(en);
		System.out.println(propiedades.getProperty("etiqueta_boton_abrir"));
        
		/*System.out.println(ResourceBundle.getBundle("Etiquetas.properties").getString("etiqueta_boton_abrir"));
		ResourceBundle bundle=ResourceBundle.getBundle("Etiquetas_es.properties");
		System.out.println(bundle.getString("etiqueta_boton_abrir"));*/
	}
}
