import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileController {

    //Atributes
    private String locationUsuario = "Ficheros_app/Usuarios";
    private String locationDesafios = "Ficheros_app/Desafios";
    private String localPersonajes = "Ficheros_app/Personaje_Usuario";
    private String LocalAtributos = "Ficheros_app/Atributos_Personajes";

    //Metodos
    public boolean existeUsuario(Usuario user){
        String nombre = user.getNombre();
        File file_ToRead = new File(this.locationUsuario);
        Set<String> fileNames = this.searchFiles(file_ToRead);
        return fileNames.contains(nombre);
    }

    private Set<String> searchFiles(File file) {
        Set<String> mySearch = new HashSet<String>();
        File[] list_Files = file.listFiles();
        if(list_Files != null){
            for(File fil : list_Files){
                mySearch.add(fil.getName());
            }
        }
        return mySearch;
    }


}
