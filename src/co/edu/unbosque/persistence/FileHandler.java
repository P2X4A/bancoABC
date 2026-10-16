package co.edu.unbosque.persistence;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class FileHandler {
	//Metodo encargado de leer el archivo properties.
	//el metodo espera el nombre del archivo Properties que para este proyecto es "config.properties"
    public Properties cargarArchivoProperties(String nombreArchivo) throws IOException {
        Properties propiedades = new Properties();
        /*InputStream se encarga de buscar el archivo properties (según el nombre que tenga el archivo
        *en todo el classpath del proyecto, incluyendo /bin, que es donde está el archivo de este proyecto.
        */
        
        InputStream flujoEntrada = getClass().getClassLoader().getResourceAsStream(nombreArchivo);
        //Si el archivo es null/no encontrado significa que el archivo no fué encontrado y lanza la excepcion
        if (flujoEntrada == null) {
            throw new IOException("No se encontró el archivo de propiedades: " + nombreArchivo);
        }

        propiedades.load(flujoEntrada);
        flujoEntrada.close();
        return propiedades;
    }
}
