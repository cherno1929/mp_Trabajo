import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileController_Operator extends FileController{

    //Atributes
    private String locationDesafios = "Ficheros_app/Desafios";
    private String locationDataPersonaje = "Ficheros_app/Personajes";
    public List<Desafio> listaDesafios = this.getDesafios();

    //Metodods
    public void editarPersonajes(){

    }



    public void banear(String idUsuario){
        Usuario userToBan = this.getUsuario(idUsuario);
        userToBan.setRol(Rol.baneado);
        this.modificarUsuario(userToBan);
    }

    public void banear(Usuario usuario){
        usuario.setRol(Rol.baneado);
        this.modificarUsuario(usuario);
    }

    public void desBanear(Usuario usuario){
        usuario.setRol(Rol.usuario);
        this.modificarUsuario(usuario);
    }

    public void desBanear(Usuario usuario, Rol nuevoRol) {
        usuario.setRol(nuevoRol);
        this.modificarUsuario(usuario);
    }

    public void desBanear(String idUsuario){
        Usuario userToDesBan = this.getUsuario(idUsuario);
        userToDesBan.setRol(Rol.usuario);
        this.modificarUsuario(userToDesBan);
    }

    public List<Desafio> getDesafios() {
        List<Desafio> desafios = new ArrayList<Desafio>();
        File fileDesafios = new File(this.locationDesafios);
        File[] files = fileDesafios.listFiles();
        if (files != null) {
            for (File fil : files){
                try {
                    Desafio desaf = new Desafio();
                    BufferedReader desafioReader = new BufferedReader(new FileReader(fil));
                    String line;
                    while ((line = desafioReader.readLine()) != null) {
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Oro")){
                                desaf.setOro(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("J1")) {
                                desaf.setJ1(getUsuario(arrData));
                            } else if (arrIdx.equals("J2")) {
                                desaf.setJ2(getUsuario(arrData));
                            } else if (arrIdx.equals("Mod_J1")) {
                                desaf.setMod_j1(buscarMods(arrData.split(" - ")));
                            } else if (arrIdx.equals("Mod_J2")) {
                                desaf.setMod_j2(buscarMods(arrData.split(" - ")));
                                }
                        }
                    }
                    desafios.add(desaf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return desafios;
    }

    public void borrarDesafio(String idDesafio){ //El id del desafio esta pensado para ser el nombre de los dos usuarios que se enfrentarán
        String location = this.locationDesafios + "/" + idDesafio + ".txt";
        File fileDelete = new File(location);
        if (fileDelete.delete()){
            System.out.println("El desafio fue exitosamente borrado");
        }else {
            System.out.println("Error : El archivo no pudo ser borrado");
        }
    }

    public List<Usuario> getBaneados(){
        Map<String, Usuario> usuarios = this.getAllUsuarios();
        List<Usuario> baneados = new ArrayList<Usuario>();
        for (String keyUsers : usuarios.keySet()){
            if (usuarios.get(keyUsers).getRol() == Rol.baneado){
                baneados.add(usuarios.get(keyUsers));
            }
        }
        return baneados;
    }


}
