import java.io.*;
import java.util.*;

public class FileController {

    //Atributes
    private String locationUsuario = "Ficheros_app/Usuarios";
    private String locationDesafios = "Ficheros_app/Desafios";
    private String localPersoanjes = "Ficheros_app/Personaje_Usuario";
    private String locationAtributos = "Ficheros_app/Atributos_Personajes";
    private String locationEsbirros = "Ficheros_app/Esbirros";
    private String locationArmas = "Ficheros_app/Armas";
    private String locationArmaduras = "Ficheros_app/Armaduras";
    private String locationHabilidades = "Ficheros_app/Habilidades";
    private String locationDataPersonaje = "Ficheros_app/Personajes";
    private String locationMods = "Ficheros_app/Modificadores";

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
            userWriter.write("Numero de registro : "+user.getNum_Registro()+"\n");
            userWriter.write("Rol : "+ user.getRol()+"\n");
            userWriter.write("Personaje : "+user.getPersonajeActivo().getId()+"\n");
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
                        } else if (attr.equals("Numero de registro")) {
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

    //PERSONAJES

    public void addPersonaje(Personaje personaj){
        if (!existePersonaje(personaj.getId())){
            String zonaCrear = this.localPersoanjes + "/" + personaj.getId() + ".txt";
            try {
                File file_ToWrite = new File(zonaCrear);
                if(file_ToWrite.createNewFile()){
                    this.addAllInfoPersonaje(personaj,zonaCrear);
                    System.out.println("El personaje "+personaj.getNombre()+" ha sido creado!");
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Error : El personaje " + personaj.getId() +" ya existe");
        }
    }

    private void addAllInfoPersonaje(Personaje personaj, String zoneToWrite) {
        File file_ToWrite = new File(zoneToWrite);
        try {
            BufferedWriter file_Writer = new BufferedWriter(new FileWriter(file_ToWrite));
            file_Writer.write("Nombre : " + personaj.getNombre()+"\n");
            file_Writer.write("Id : "+personaj.getId()+"\n");
            file_Writer.write("Oro : "+personaj.getOro()+"\n");
            file_Writer.write("Raza : "+personaj.getClass()+"\n");
            file_Writer.write("Arma : ");
            if(personaj.getArmas() != null){
                for (Arma weaponP : personaj.getArmas()) {
                    file_Writer.write(weaponP.getNombre() + " - "); //Guardar el nombre del arma (es su id)
                }
            }
            file_Writer.write("\n");
            file_Writer.write("Armaduras : ");
            if (personaj.getArmaduras() != null){
                for (Armadura armaduraP : personaj.getArmaduras()) {
                    file_Writer.write(armaduraP.getNombre() + " - "); //Guardar el nombre del arma (es su id)
                }
            }
            file_Writer.write("\n");
            file_Writer.write("Esbirros : ");
            if(personaj.getEsbirros() != null){
                for (Esbirro esbP : personaj.getEsbirros()) {
                    file_Writer.write(esbP.getNombre() + " - "); //Guardar el nombre del arma (es su id)
                }
            }
            file_Writer.write("\n");
            if(personaj.getClass() == Vampiro.class){
                file_Writer.write("Edad : "+((Vampiro) personaj).getEdad());
            }else if (personaj.getClass() == Licantropo.class){
                file_Writer.write("Altura : "+((Licantropo) personaj).getAltura());
                file_Writer.write("Peso : "+((Licantropo) personaj).getPeso());
            }
            file_Writer.close();
            //Habilidades, Salud, Poder, Mods, Habilidades vienen por defecto en los ficheros de las razas de personajes
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void modificarPersonaje(Personaje pers){
        String location = this.localPersoanjes + "/" + pers.getId() + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(location));
            writer.write("");
            writer.close();
            this.addAllInfoPersonaje(pers,location);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Personaje getPersonaje(String idPersonaje) {
        /*Anotación
            En los ficheros del personje, no se va a guardar la información relacionada con:
                -Habilidades
                -Modificadores
                -Armas
                -Armaduras
                -Salud
                -Esbirros
            Ya que esta información estará guardada en sus respectivos ficheros*/
        if (this.existePersonaje(idPersonaje)){
            String name_ToRead = this.localPersoanjes + "/" + idPersonaje + ".txt";
            Personaje pers_X = getRazaPersonaje(name_ToRead);
            try {
                BufferedReader file_Reader = new BufferedReader(new FileReader(name_ToRead));
                String line;
                while((line = file_Reader.readLine()) != null){
                    String[] arr = line.split(" : ");
                    if (arr.length > 1){
                        String arrIndx = arr[0];
                        String arrData = arr[1];
                        if (arrIndx.equals("Nombre")) {
                            pers_X.setNombre(arrData);
                        } else if (arrIndx.equals("Oro")) {
                            pers_X.setOro(Integer.parseInt(arrData));
                        } else if (arrIndx.equals("Id")) {
                            pers_X.setId(arrData);
                        } else if (arrIndx.equals("Arma")) {
                            String[] nomArma = arrData.split(" - ");
                            pers_X.setArmas(this.buscarArmas(nomArma));
                        } else if (arrIndx.equals("Armaduras")) {
                            String[] nomArmaduras = arrData.split(" - ");
                            pers_X.setArmaduras(this.buscarArmaduras(nomArmaduras));
                        } else if (arrIndx.equals("Esbirros")) {
                            String[] nomEsbirros = arrData.split(" - ");
                            pers_X.setEsbirros(this.buscarEsbirros(nomEsbirros));
                        } else if (arrIndx.equals("Modificador")) {
                            String[] nomMods = arrData.split(" - ");
                            pers_X.setMods(this.buscarMods(nomMods));
                        }
                        else if (arrIndx.equals("Edad") & (pers_X.getClass() == Vampiro.class)){
                            ((Vampiro) pers_X).setEdad(Integer.parseInt(arrData));
                        }else if(arrIndx.equals("Altura") & (pers_X.getClass() == Licantropo.class)){
                            ((Licantropo) pers_X).setAltura(Integer.parseInt(arrData));
                        } else if (arrIndx.equals("Peso") & (pers_X.getClass() == Licantropo.class)) {
                            ((Licantropo) pers_X).setPeso(Integer.parseInt(arrData));
                        }

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

    private Personaje getRazaPersonaje(String nameToRead) {
        String race = "";
        Personaje pers;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nameToRead));
            String line;
            while ((line = reader.readLine()) != null){
                String[] arr = line.split(" : ");
                if (arr[0].equals("Raza")){
                    race = arr[1];
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        if (race.equals("class Vampiro")){
            pers = new Vampiro();
        } else if (race.equals("class Licantropo")) {
            pers = new Licantropo();
        }else{
            pers = new Cazador();
        }
        return pers;
    }

    private Set<Modificador> buscarMods(String[] nomMods) {
        //Nombre
        //Grado Efecto
        Set<Modificador> mods = new HashSet<Modificador>();
        for (String nomMod : nomMods){
            Modificador mod = new Modificador();
            mod.setNombre(nomMod);
            String modLocation = this.locationMods + "/" + nomMod + ".txt";
            try{
                BufferedReader modRader = new BufferedReader(new FileReader(modLocation));
                String line;
                while ((line = modRader.readLine()) != null){
                    String[] arr = line.split(" : ");
                    String arrIdx = arr[0];
                    String arrData = arr[1];
                    if (arrIdx.equals("Efecto")){
                        mod.setGrado_Efecto(Integer.parseInt(arrData));
                    }
                }
                mods.add(mod);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return mods;
    }

    private Set<Esbirro> buscarEsbirros(String[] nomEsbirros) {
        return null;
    }

    private Set<Armadura> buscarArmaduras(String[] nomArmaduras) {
        return null;
    }

    private Set<Arma> buscarArmas(String[] nomArma) {
        Set<Arma> armas = new HashSet<Arma>();
        for(String nombreArma : nomArma){
            String locArmas = this.locationArmas +"/"+ nombreArma + ".txt";
            Arma arma = new Arma();
            arma.setNombre(nombreArma);
            try {
                BufferedReader armaReader = new BufferedReader(new FileReader(locArmas));
                String line;
                while((line = armaReader.readLine()) != null){
                    String[] arr = line.split(" : ");
                    if (arr.length > 1){
                        String arrInd = arr[0];
                        String arrData = arr[1];
                        if (arrInd.equals("Ataque")) {
                            arma.setPunt_Atk(Integer.parseInt(arrData));
                        } else if (arrInd.equals("Numero_Manos")) {
                            arma.setNum_Manos(Integer.parseInt(arrData));
                        } else if (arrInd.equals("Modificadores")) {
                            arma.setMod(this.buscarMods(arrData.split(" - ")));
                        }
                    }
                }
                armas.add(arma);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return armas;
    }

    private Set<Habilidad_Especial> buscarHabilidades(String[] nomHabilidad) {
        return null;
    }

    public boolean existePersonaje(String idPersonaje){
        String buscado = idPersonaje + ".txt";
        File toRead = new File(this.localPersoanjes);
        Set<String> id_Personajes = searchFiles(toRead);
        return id_Personajes.contains(buscado);
    }



}