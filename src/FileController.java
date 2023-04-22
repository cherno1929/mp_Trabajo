import java.io.*;
import java.util.*;

public class FileController {

    //Atributes
    protected String locationUsuario = "Ficheros_app/Usuarios";
    protected String localPersoanjes = "Ficheros_app/Personaje_Usuario";
    protected String locationEsbirros = "Ficheros_app/Esbirros";
    protected String locationArmas = "Ficheros_app/Armas";
    protected String locationArmaduras = "Ficheros_app/Armaduras";
    protected String locationMods = "Ficheros_app/Modificadores";
    protected String localDones = "Ficheros_app/Habilidades/Don";
    protected String localDisciplinas = "Ficheros_app/Habilidades/Disciplina";
    protected String localTalentos = "Ficheros_app/Habilidades/Talento";
    protected String localPacto = "Ficheros_app/Pacto";
    protected String localRanking = "Ficheros_app/Ranking.txt";

    //Metodos

    ////Ranking
    public List<String> verGanadores(){
        List<String> ganadores = new ArrayList<String>();
        try {
            BufferedReader readVictorias = new BufferedReader(new FileReader(this.localRanking));
            String line;
            while ((line = readVictorias.readLine()) != null){ //Line contiene el nombre del jugador que ganó
                ganadores.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ganadores;
    }

    ////Usuario
    public boolean existeUsuario(Usuario user){
        String nombre = user.getNum_Registro();
        File file_ToRead = new File(this.locationUsuario);
        Set<String> fileNames = this.searchFiles(file_ToRead);
        return fileNames.contains(nombre +".txt");
    }

    protected Set<String> searchFiles(File file) {
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

    protected void addAllInfoUser(Usuario user,String ubic) {
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
                            user_X.setPersonajeActivo(this.getPersonaje(attrData));
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

    public Usuario getUsuario(String idUsuario){ // Y si el usuario no tiene personaje aun?
        Usuario userSearch = new Usuario();
        String location = this.locationUsuario + "/" + idUsuario + ".txt";
        try {
            BufferedReader userReader = new BufferedReader(new FileReader(location));
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] lineData = line.split(" : ");
                if (lineData.length > 1){
                    String attr = lineData[0];
                    String attrData = lineData[1];
                    if (attr.equals("Nombre")) {
                        userSearch.setNombre(attrData);
                    } else if (attr.equals("NickName")) {
                        userSearch.setNick(attrData);
                    } else if (attr.equals("Password")) {
                        userSearch.setPassword(attrData);
                    } else if (attr.equals("Numero de registro")) {
                        userSearch.setNum_Registro(attrData);
                    } else if (attr.equals("Rol")) {
                        if (attrData.equals("usuario")) {
                            userSearch.setRol(Rol.usuario);
                        } else if (attrData.equals("operador")) {
                            userSearch.setRol(Rol.operador);
                        } else {
                            userSearch.setRol(Rol.baneado);
                        }
                    } else if (attr.equals("Personaje")) {
                        userSearch.setPersonajeActivo(getPersonaje(attrData));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userSearch;
    }

    //PERSONAJES

    public void borrarPersoanje(Personaje persoanje){
        String zone = this.localPersoanjes + "/" + persoanje.getId() + ".txt";
            File file_ToDestroy = new File(zone);
            if(file_ToDestroy.delete()){
                System.out.println("El archivo fue borrado correctamente");
            }else {
                System.out.println("Error");
            }
    }

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

    protected void addAllInfoPersonaje(Personaje personaj, String zoneToWrite) {
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
        /*Anotación
            Los operadores no tienen numero de registro*/
        if (this.existePersonaje(idPersonaje)){
            String name_ToRead = this.localPersoanjes + "/" + idPersonaje + ".txt";
            Personaje pers_X = getRazaPersonaje(name_ToRead);
            try {
                BufferedReader file_Reader = new BufferedReader(new FileReader(name_ToRead));
                String line;
                pers_X.setHabilidades(this.buscarHabilidades(pers_X.getClass()));
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
                        } else if (arrIndx.equals("Salud")) {
                            pers_X.setPunt_Salud(Integer.parseInt(arrData));
                        } else if (arrIndx.equals("Poder")) {
                            pers_X.setPoder(Integer.parseInt(arrData));
                        } else if (arrIndx.equals("Edad") & (pers_X.getClass() == Vampiro.class)){
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

    protected Personaje getRazaPersonaje(String nameToRead) {
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

    protected Set<Modificador> buscarMods(String[] nomMods) {
        //Nombre
        //Grado Efecto
        Set<Modificador> mods = new HashSet<Modificador>();
        if (nomMods != null){
            for (String nomMod : nomMods) {
                Modificador mod = new Modificador();
                mod.setNombre(nomMod);
                String modLocation = this.locationMods + "/" + nomMod + ".txt";
                try {
                    BufferedReader modRader = new BufferedReader(new FileReader(modLocation));
                    String line;
                    while ((line = modRader.readLine()) != null) {
                        String[] arr = line.split(" : ");
                        String arrIdx = arr[0];
                        String arrData = arr[1];
                        if (arrIdx.equals("Efecto")) {
                            mod.setGrado_Efecto(Integer.parseInt(arrData));
                        } else if (arrIdx.equals("Tipo")) {
                            if (arrData.equals("Fortaleza")) {
                                mod.setTipo_mod(Tipo_mod.Fortaleza);
                            } else {
                                mod.setTipo_mod(Tipo_mod.Debilidad);
                            }
                        }
                    }
                    mods.add(mod);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return mods;
    }

    protected Set<Arma> buscarArmas(String[] nomArma) {
        Set<Arma> armas = new HashSet<Arma>();
        if (nomArma != null){
            for (String nombreArma : nomArma) {
                String locArmas = this.locationArmas + "/" + nombreArma + ".txt";
                Arma arma = new Arma();
                arma.setNombre(nombreArma);
                try {
                    BufferedReader armaReader = new BufferedReader(new FileReader(locArmas));
                    String line;
                    while ((line = armaReader.readLine()) != null) {
                        String[] arr = line.split(" : ");
                        if (arr.length > 1) {
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return armas;
    }

    protected Set<Armadura> buscarArmaduras(String[] nomArmaduras) {
        Set<Armadura> armaduras = new HashSet<Armadura>();
        if (nomArmaduras != null){
            for (String nombArmadura : nomArmaduras) {
                Armadura armadura = new Armadura();
                armadura.setNombre(nombArmadura);
                String armorLocation = this.locationArmaduras + "/" + nombArmadura + ".txt";
                try {
                    BufferedReader armorReader = new BufferedReader(new FileReader(armorLocation));
                    String line;
                    while ((line = armorReader.readLine()) != null) {
                        String[] arr = line.split(" : ");
                        if (arr.length > 1) {
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Defensa")) {
                                armadura.setPunt_Def(Integer.parseInt(arrData));
                            } else {
                                armadura.setMod(this.buscarMods(arrData.split(" - ")));
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                armaduras.add(armadura);
            }
        }
        return armaduras;
    }

    protected Set<Habilidad_Especial> buscarHabilidades(Class<? extends Personaje> raza) {
        if (raza == Vampiro.class){
            return getDisciplinas();
        }else if (raza == Licantropo.class){
            return getDones();
        }else{
            return getTalentos();
        }
    }

    protected Set<Habilidad_Especial> getTalentos() {
        Set<Habilidad_Especial> talentos = new HashSet<Habilidad_Especial>();
        File location = new File(this.localTalentos);
        File[] files = location.listFiles();
        if (files != null){
            for (File fil : files){
                Talento talent = new Talento();
                talent.setNombre(fil.getName().substring(0,fil.getName().length()-4));
                try{
                    BufferedReader talentoReader = new BufferedReader(new FileReader(fil));
                    String line;
                    while ((line = talentoReader.readLine()) != null){
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Ataque")){
                                talent.setAtk(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Defensa")) {
                                talent.setDef(Integer.parseInt(arrData));
                            }
                        }
                    }
                    talentos.add(talent);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return talentos;
    }

    protected Set<Habilidad_Especial> getDones() {
        Set<Habilidad_Especial> dones = new HashSet<Habilidad_Especial>();
        File location = new File(this.localDones);
        File[] files = location.listFiles();
        if (files != null){
            for (File fil : files){
                Don don = new Don();
                don.setNombre(fil.getName().substring(0,fil.getName().length()-4));
                try{
                    BufferedReader talentoReader = new BufferedReader(new FileReader(fil));
                    String line;
                    while ((line = talentoReader.readLine()) != null){
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Ataque")){
                                don.setAtk(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Defensa")) {
                                don.setDef(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Umbral")){
                                don.setUmbral(Integer.parseInt(arrData));
                            }
                        }
                    }
                    dones.add(don);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return dones;
    }

    protected Set<Habilidad_Especial> getDisciplinas() {
        Set<Habilidad_Especial> disciplinas = new HashSet<Habilidad_Especial>();
        File location = new File(this.localDisciplinas);
        File[] files = location.listFiles();
        if (files != null){
            for (File fil : files){
                Disciplina disciplina = new Disciplina();
                disciplina.setNombre(fil.getName().substring(0,fil.getName().length()-4));
                try{
                    BufferedReader talentoReader = new BufferedReader(new FileReader(fil));
                    String line;
                    while ((line = talentoReader.readLine()) != null){
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Ataque")){
                                disciplina.setAtk(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Defensa")) {
                                disciplina.setDef(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Coste")){
                                disciplina.setCoste_Sangre(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Sangre_Robada")) {
                                disciplina.setSangre_Robada(Integer.parseInt(arrData));
                            }
                        }
                    }
                    disciplinas.add(disciplina);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return disciplinas;
    }

    public boolean existePersonaje(String idPersonaje){
        String buscado = idPersonaje + ".txt";
        File toRead = new File(this.localPersoanjes);
        Set<String> id_Personajes = searchFiles(toRead);
        return id_Personajes.contains(buscado);
    }

    protected Set<Esbirro> buscarEsbirros(String[] nomEsbirros) {
        Set<Esbirro> esbirros = new HashSet<Esbirro>();
        if (nomEsbirros != null){
            for (String name : nomEsbirros){
                String fileName = this.locationEsbirros + "/" + name + ".txt";
                Esbirro esb = getRazaEsbirro(fileName);
                try{
                    BufferedReader esbirroReader = new BufferedReader(new FileReader(fileName));
                    String line;
                    while ((line = esbirroReader.readLine()) != null){
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Nombre")){
                                esb.setNombre(arrData);
                            } else if (arrIdx.equals("Salud")) {
                                esb.setSalud(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Dependencia") & esb.getClass() == Ghoul.class){
                                ((Ghoul) esb).setNiv_Dependencia(Integer.parseInt(arrData));
                            } else if (arrIdx.equals("Lealtad") & esb.getClass() == Humano.class) {
                                if (arrData.equals("Alto")){
                                    ((Humano) esb).setLealtad(Niv_Lealtad.alto);
                                } else if (arrData.equals("Medio")) {
                                    ((Humano) esb).setLealtad(Niv_Lealtad.medio);
                                }else {
                                    ((Humano) esb).setLealtad(Niv_Lealtad.bajo);
                                }
                            } else if (arrIdx.equals("Esbirros") & esb.getClass() == Demonio.class) {
                                String[] nomEsbirrosDemonio = arrData.split(" - "); 
                                ((Demonio) esb).setEsbirros(this.buscarEsbirros(nomEsbirrosDemonio));
                            } else if (arrIdx.equals("Pacto") & esb.getClass() == Demonio.class) {
                                ((Demonio) esb).setPacto(this.getPacto(arrData.split(" - "),esb));
                            }
                        }
                    }
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                esbirros.add(esb);
            }
        }
        return esbirros;
    }

    protected List<Pacto> getPacto(String[] idPactos, Esbirro esbrr) {
        List<Pacto> pactos = new ArrayList<Pacto>();
        if (idPactos != null){
            for (String idPacto : idPactos){
                Pacto pact = new Pacto();
                String location = this.localPacto + "/" + idPacto + ".txt";
                try {
                    BufferedReader pactoReader = new BufferedReader(new FileReader(location));
                    String line;
                    while ((line = pactoReader.readLine()) != null){
                        String[] arr = line.split(" : ");
                        if (arr.length > 1){
                            String arrIdx = arr[0];
                            String arrData = arr[1];
                            if (arrIdx.equals("Amo")){
                                pact.setAmo(getPersonaje(arrData)); //Sera necesario que en el fichero se guarde el id del personaje
                            } else if (arrIdx.equals("Esbirro")) {
                                pact.setEsbirro(esbrr);
                            } else if (arrIdx.equals("Descripcion")) {
                                pact.setDescripcion(arrData);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pactos.add(pact);
            }
        }
        return pactos;
    }

    protected Esbirro getRazaEsbirro(String fileName) {
        String race = "";
        Esbirro esb;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
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
        if (race.equals("class Humano")){
            esb = new Humano();
        } else if (race.equals("class Ghoul")) {
            esb = new Ghoul();
        }else{
            esb = new Demonio();
        }
        return esb;
    }

    protected List<Personaje> getAllPersonaje() {
        List<Personaje> persoanjes = new ArrayList<Personaje>();
        File[] filesPersoanje = new File(this.localPersoanjes).listFiles();
        for (File fil : filesPersoanje) {
            String nameFil = fil.getName();
            persoanjes.add(this.getPersonaje(nameFil));
        }
        return persoanjes;
    }
}