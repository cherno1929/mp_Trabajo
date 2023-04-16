import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileController {

    //Atributes
    private String locationUsuario = "Ficheros_app/Usuarios";
    private String locationDesafios = "Ficheros_app/Desafios";
    private String localPersoanjes = "Ficheros_app/Personaje_Usuario";
    private String LocalAtributos = "Ficheros_app/Atributos_Personajes";

    //Metodos
    public boolean existeUsuario(Usuario user){
        String nombre = user.getNum_Registro();
        File file_ToRead = new File(this.locationUsuario);
        Set<String> fileNames = this.searchFiles(file_ToRead);
        return fileNames.contains(nombre +".txt");
    }

    //Usuario
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

    public void deleteUsuario(Usuario user){
        String zone = this.locationUsuario + "/" + user.getNum_Registro() + ".txt";
        if(existeUsuario(user)){
            File file_ToDestroy = new File(zone);
            if(file_ToDestroy.delete()){
                System.out.println("El archivo "+user.getNombre() +" fue borrado correctamente");
            }else {
                System.out.println("error");
            }
        }else {
            System.out.println("No existe este usuario");
        }
    }

    private void addAllInfoUser(Usuario user,String ubic) {
        try{
            FileWriter userWriter = new FileWriter(ubic);
            userWriter.write("Nombre : " +user.getNombre() + "\n");
            userWriter.write("NickName : "+user.getNick()+"\n");
            userWriter.write("Password : "+user.getPassword()+"\n");
            userWriter.write("Numero de reegistro : "+user.getNum_Registro()+"\n");
            userWriter.write("Rol : "+ user.getRol());
            userWriter.write("Personaje : "+user.getPersonajeActivo().getNombre()+"\n");
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Exception :: "+e);
            throw new RuntimeException(e);
        }
    }

    public void addUsuario(Usuario usuario){
        String zonaUser = this.locationUsuario + "/" + usuario.getNum_Registro() + ".txt";
        try {
            File newUser = new File(zonaUser);
            if (newUser.createNewFile()){
                System.out.println("El usuario "+ usuario.getNombre() +" ha sido creado");
                addAllInfoUser(usuario,zonaUser);
            }
        } catch (IOException ioe) {
            System.out.println("Error : " + ioe);
            throw new RuntimeException(ioe);
        }
    }

    public void modificarUsuario(Usuario user){
        String zonaUser = this.locationUsuario + "/" + user.getNum_Registro() + ".txt";
        try{
        FileWriter fil = new FileWriter(zonaUser);
            BufferedWriter bufUser = new BufferedWriter(fil);
            bufUser.write("");
            bufUser.close();
            addAllInfoUser(user,zonaUser);
        } catch (IOException e) {
            System.out.println("Error : "+e);
            throw new RuntimeException(e);
        }
    }


}