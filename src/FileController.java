import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileController {

    //Atributes
    private String locationUsuario = "Ficheros_app/Usuarios";
    private String locationDesafios = "Ficheros_app/Desafios";
    private String localPersoanjes = "Ficheros_app/Personaje_Usuario";
    private String LocalAtributos = "Ficheros_app/Atributos_Personajes";

    //Metodos
    ////Usuario
    public boolean existeUsuario(Usuario user){
        String nombre = user.getNum_Registro();
        File file_ToRead = new File(this.locationUsuario);
        Set<String> fileNames = this.searchFiles(file_ToRead);
        return fileNames.contains(nombre +".txt");
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
            userWriter.write("Rol : "+ user.getRol()+"\n");
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

    public Map<String,Usuario> getAllUsuarios(){
        Map<String,Usuario> allUser = new HashMap<String,Usuario>();
        File locationUser = new File(this.locationUsuario);
        File[] userFiles = locationUser.listFiles();
        if(userFiles != null){
            for (File user : userFiles) {
                try {
                    BufferedReader userReader = new BufferedReader(new FileReader(user));
                    String line;
                    Usuario user_X = new Usuario();
                    while ((line = userReader.readLine()) != null) {
                        String[] lineData = line.split(" : ");
                        String attr = lineData[0];
                        String attrData = lineData[1];
                        if (attr.equals("Nombre")) {
                            user_X.setNombre(attrData);
                        } else if (attr.equals("NickName")) {
                            user_X.setNick(attrData);
                        } else if (attr.equals("Password")) {
                            user_X.setPassword(attrData);
                        } else if (attr.equals("Numero de reegistro")) {
                            user_X.setNum_Registro(attrData);
                        } else if (attr.equals("Rol")) {
                            if (attrData.equals("usuario")) {
                                user_X.setRol(Rol.usuario);
                            } else if (attrData.equals("operador")) {
                                user_X.setRol(Rol.operador);
                            } else {
                                user_X.setRol(Rol.baneado);
                            }
                        } else if (attr.equals("Personaje")) {
                            //user_X.setPersonajeActivo(this.buscarPersonaje(attrData));
                        }
                    }
                    allUser.put(user_X.getNombre() + "-" + user_X.getPassword(), user_X);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            System.out.println("Error :: No hay usuarios");
        }
        return allUser;
    }

    public Personaje buscarPersonaje(String idPersonaje) {
        if (this.existePersonaje(idPersonaje)){
            String name_ToRead = this.localPersoanjes + "/" + idPersonaje + ".txt";
            Personaje pers_X = new Personaje();
            try {
                BufferedReader file_Reader = new BufferedReader(new FileReader(name_ToRead));
                String line;
                while((line = file_Reader.readLine()) != null){
                    String[] arr = line.split(" : ");
                    String arrIndx = arr[0];
                    String arrData = arr[1];
                    if (arrIndx.equals("Nombre")){
                        pers_X.setNombre(arrData);
                    } else if (arrIndx.equals("Oro")) {
                        pers_X.setOro(Integer.parseInt(arrData));
                    } else if (arrIndx.equals("poder")) {
                        pers_X.setPoder(Integer.parseInt(arrData));
                    } else if (arrIndx.equals("Id")) {
                        pers_X.setId(arrData);
                    } else if (arrIndx.equals("Escudo")) {
                        pers_X.setEscudo(0);
                    } else if (arrIndx.equals("Salud")) {
                        pers_X.setPunt_Salud(Integer.parseInt(arrData));
                    } else if (arrIndx.equals("Habilidades")) {
                        String[] nomHabilidad = arrData.split(" - ");
                        pers_X.setHabilidades(this.buscarHabilidades(nomHabilidad));
                    } else if (arrIndx.equals("Armas")) {
                        String[] nomArma = arrData.split(" - ");
                        pers_X.setArmas(this.buscarArmas(nomArma));
                    } else if (arrIndx.equals("Armaduras")) {
                        String[] nomArmaduras = arrData.split(" - ");
                        pers_X.setArmaduras(this.buscarArmaduras(nomArmaduras));
                    } else if (arrIndx.equals("Esbirros")) {
                        String[] nomEsbirros = arrData.split(" - ");
                        pers_X.setEsbirros(this.buscarEsbirros(nomEsbirros));
                    }
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            return pers_X;
        }else {
            return null;
        }

    }

    private Set<Esbirro> buscarEsbirros(String[] nomEsbirros) {
        return null;
    }

    private Set<Armadura> buscarArmaduras(String[] nomArmaduras) {
        return null;
    }

    private Set<Arma> buscarArmas(String[] nomArma) {
        return null;
    }

    private Set<Habilidad_Especial> buscarHabilidades(String[] nomHabilidad) {
        return null;
    }

    public boolean existePersonaje(String idPersonaje){
        File toRead = new File(this.localPersoanjes);
        Set<String> id_Personajes = searchFiles(toRead);
        return id_Personajes.contains(id_Personajes);
    }
    //


}