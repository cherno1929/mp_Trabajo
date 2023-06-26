import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileController_Operator extends FileController{

    //Atributes
    public final String locationDesafios = "Ficheros_app/Desafios";
    private final String locationDataPersonaje = "Ficheros_app/Personajes";
    public List<Desafio> listaDesafios = this.getDesafios();

    //Metodods
    public void editarPersonajes(){

    }

    public Desafio getDesafio(String id){
        List<Desafio> desafios = this.getDesafios();

        Desafio obj = null;

        for (Desafio desf : desafios) {
            if (id.equals(desf.getId())){
                obj = desf;
            }
        }
        return obj;
    }

    public void banear(String idUsuario){
        Usuario userToBan = this.getUsuario(idUsuario);
        userToBan.setRol(Rol.baneado);
        this.modificarUsuario(userToBan);
    }

    public void banear(Usuario usuario){
        if (existeUsuario(usuario)){
            usuario.setRol(Rol.baneado);
            this.modificarUsuario(usuario);
        }
    }

    public void desBanear(Usuario usuario){
        usuario.setRol(Rol.usuario);
        this.modificarUsuario(usuario);
    }

    public void desBanear(Usuario usuario, Rol nuevoRol) {
        if (existeUsuario(usuario)){
            usuario.setRol(nuevoRol);
            this.modificarUsuario(usuario);
        }
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
                                if (existeUsuario(arrData)) {
                                    desaf.setJ1(getUsuario(arrData));
                                }
                            } else if (arrIdx.equals("J2")) {
                                if (existeUsuario(arrData)) {
                                    desaf.setJ2(getUsuario(arrData));
                                }
                            } else if (arrIdx.equals("Mod_J1")) {
                                desaf.setMod_j1(buscarMods(arrData.split(" - ")));
                            } else if (arrIdx.equals("Mod_J2")) {
                                desaf.setMod_j2(buscarMods(arrData.split(" - ")));
                            } else if (arrIdx.equals("Validado")) {
                                desaf.setValidado(Boolean.parseBoolean(arrData));
                            }
                        }
                    }
                    if (desaf.getValidado() == false && desaf.haveJ()){
                        desafios.add(desaf);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return desafios;
    }

    public List<Desafio> getSolicitudesDesafio(Usuario user) {
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
                            } else if (arrIdx.equals("Validado")) {
                                desaf.setValidado(Boolean.parseBoolean(arrData));
                            }
                        }
                    }
                    if (desaf.getValidado() == true && user.getNum_Registro().equals(desaf.getJ2().getNum_Registro())){
                        desafios.add(desaf);
                    }
                    desafioReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return desafios;
    }

    public void modificarDesafio(String nomFile, Desafio desafio){
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(nomFile));
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeDesafio(nomFile,desafio);
    }

    private String nameModsDesafio(Set<Modificador> mods){
        String names = "";
        if (mods != null){
            for (Modificador mod : mods) {
                names += (mod.getNombre() + " - ");
            }
        }
        return names;
    }


    public void writeDesafio(String nomFile, Desafio desafio) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(nomFile));
            fileWriter.write("J1 : "+desafio.getJ1().getNum_Registro());
            fileWriter.write("\nJ2 : "+desafio.getJ2().getNum_Registro());
            fileWriter.write("\nOro : "+desafio.getOro());
            fileWriter.write("\nMod_J1 : " + this.nameModsDesafio(desafio.mod_j1));
            fileWriter.write("\nMod_J2 : " + this.nameModsDesafio(desafio.mod_j2));
            fileWriter.write("\nValidado : " + desafio.getValidado());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


    public void validarDesafio(Desafio desafio) {
        desafio.setValidado(true);
        this.modificarDesafio(this.locationDesafios +"/"+ desafio.getJ1().getNum_Registro() + "-" + desafio.getJ2().getNum_Registro() + ".txt",desafio);
    }
}
