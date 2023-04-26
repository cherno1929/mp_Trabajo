import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class App_Combate {
    private Usuario J1; //En un principio, este eres tú
    private Usuario J2;
    private int turno;
    private int oroApostado;
    private Desafio desafioCombate;
    private Usuario ganador;
    private Usuario perdedor;
    private Scanner reader = new Scanner(System.in);
    private App_Inventario inventario = new App_Inventario();
    private FileController_Combate fileContr = new FileController_Combate();

    private String zonaRegistry = new String();

    private StringBuilder reg = new StringBuilder();

    //Metodos
    public void menuCombatePrincipal(FileController_Operator fil,Usuario user /*Si queremos usar un file controller ya existente*/ /*Recuerda antes asignar un usuario J1*/) {
        if (user != null) {
            this.setJ1(user);
        }
        int opt = 0;
        while (opt != 3) {
            System.out.println("\nQue desea hacer?\n1. Desafiar\n2. Ver Lista Desafios\n3. Salir");
            opt = this.reader.nextInt();
            if (opt == 1){
                desafiar();
            } else if (opt == 2) {
                verDesafios();
            }
        }
    }

    private void verDesafios() {
        int opt = -2;
        List<Desafio> desafios = this.fileContr.getSolicitudesDesafio(this.J1);
        if (desafios != null && desafios.size() > 0){
            while (opt!=-1) {
                if (desafios.size() > 0){
                    System.out.println("\nSolicitudes de desafio\n");
                    mostrarDesafios(desafios);
                    System.out.println("\nElige una solicitud (0-" + (desafios.size() - 1) + ")\n-1.Salir");
                    opt = reader.nextInt();
                    if (opt >= 0 && opt < desafios.size()) {
                        acepatar_rechazar(desafios.get(opt));
                    } else {
                        System.out.println("\nOpcion no valida\n");
                    }
                } else {
                    System.out.println("\nNo hay desafios\n");
                    opt = -1;
                }
            }
        } else {
            System.out.println("No hay desafios actualmente");
        }
    }

    private void acepatar_rechazar(Desafio desafio) {
        if (desafio != null) {
            int opt = 0;
            System.out.println("\n1. Aceptar\n2. Rechazar\n3. Salir");
            while (!(opt >= 1 && opt <= 3)) {
                opt = reader.nextInt();
                if (opt == 1) {
                    this.setJ2(desafio.getJ1());
                    this.setDesafioCombate(desafio);
                    preCombate();
                    break;
                } else if (opt == 2) {
                    enviarOro(desafio, (int) (desafio.getOro() * 0.1));
                }
            }
            if (opt != 3) {
                this.fileContr.destruirDesafio(desafio);
            }
        }
    }

    private void enviarOro(Desafio desafio, int cantOro) {
        if (desafio.getJ2().getPersonajeActivo().getOro() == 0){

        } else if (desafio.getJ2().getPersonajeActivo().getOro() < 0) {
            desafio.getJ1().getPersonajeActivo().setOro(desafio.getJ1().getPersonajeActivo().getOro() + desafio.getJ2().getPersonajeActivo().getOro());
            desafio.getJ2().getPersonajeActivo().setOro(0);
        }else {
            desafio.getJ1().getPersonajeActivo().setOro(desafio.getJ1().getPersonajeActivo().getOro() + cantOro);
            desafio.getJ2().getPersonajeActivo().setOro(desafio.getJ2().getPersonajeActivo().getOro() - cantOro);
        }
        this.fileContr.modificarPersonaje(desafio.getJ1().getPersonajeActivo());
        this.fileContr.modificarPersonaje(desafio.getJ2().getPersonajeActivo());
    }

    private void preCombate() {
        int opt = 0;
        while (opt != 3){
            System.out.println("Desea modificar su inventario?\n1. Si\n2. No, empezar combate\n3. Salir");
            opt = this.reader.nextInt();
            if(opt == 1){
                this.inventario.modificarPers(this.J1,this.J1.getRol());
            } else if (opt == 2) {
                Combate();
            }
        }
    }


    private void Combate() {
        setZonaRegistry("Ficheros_App/Registros_Combate/" + getJ1().getNum_Registro() + "-" + getJ2().getNum_Registro() + ".txt");
        setTurno(1);
        Personaje p1 = new Personaje();
        p1 = J1.getPersonajeActivo();
        Personaje p2 = new Personaje();
        p2 = J2.getPersonajeActivo();
        p1.setPunt_Salud(5);
        p2.setPunt_Salud(5);
        while (!CombateFinalizado(this.J1, this.J2)) {
            if (!(turno % 2 == 0)) { // Turno impar
                MostrarMenuTurno(J1, p1, p2);
            } else {
                MostrarMenuTurno(J2, p1, p2);
            }
            setTurno(this.getTurno()+1);
        }
        p1.setPunt_Salud(5);
        p2.setPunt_Salud(5);
        this.createRegistry();
        Date nowDate = new Date();
        Persistencia pers = new Persistencia();
        pers.setN_Turnos(this.turno);
        pers.setJ1(this.getJ1());
        pers.setJ2(this.getJ2());
        pers.setFecha_Combate(nowDate);
        pers.setGanador(this.getGanador());
        pers.setPerdedor(this.getPerdedor());
        // pers.setEsbirros_Vivos(); --> Los esbirros
        repartirOro();
        this.fileContr.addPersistencia(pers);
        this.fileContr.añadirRanking(this.ganador);
    }

    private void repartirOro() {
        this.enviarOro(this.desafioCombate,this.desafioCombate.getOro());
    }

    private boolean CombateFinalizado(Usuario p1, Usuario p2){
        if (p1.getPersonajeActivo().hasFainted()){
            this.setGanador(p2);
            this.setPerdedor(p1);
            System.out.println("El ganador es " + ganador.getNombre() + "!\n\n");
        } else if (p2.getPersonajeActivo().hasFainted()) {
            this.setGanador(p1);
            this.setPerdedor(p2);
            System.out.println("El ganador es " + ganador.getNombre() + "!\n\n");
        }
        return (p1.getPersonajeActivo().hasFainted()) || (p2.getPersonajeActivo().hasFainted()) || (ganador != null);
    } // Comprobacion combate terminado

    private void MostrarMenuTurno(Usuario user, Personaje p1, Personaje p2){
        System.out.println("Turno de " + user.getNombre() + "!");
        System.out.println("Vida de " + p1.getNombre() + ": " + p1.getPunt_Salud());
        System.out.println("Vida de " + p2.getNombre() + ": " + p2.getPunt_Salud());
        System.out.println("Elige tu acción:\n1.Atacar\n2.Rendirte\n");
        Habilidad_Especial h;
        int opcion = reader.nextInt();
        if (opcion == 1) {
            if (user == J1) {
                h = ElegirHabilidad(p1);
                Atacar(p1, p2, h);
            } else {
                h = ElegirHabilidad(p2);
                Atacar(p2, p1, h);
            }
        } else if (opcion == 2){
            Rendirse(user);
            return;
        } else {
            MostrarMenuTurno(user, p1, p2);
        }
    } // Mostrar pestaña de accion para x usuario

    private void Rendirse(Usuario user){
        boolean j1_loser = user == J1;
        if (j1_loser) {
            setGanador(J2);
            setPerdedor(J1);
        } else {
            setGanador(J1);
            setPerdedor(J2);
        }
    }

    private Habilidad_Especial ElegirHabilidad(Personaje p){
        List<Habilidad_Especial> habilidades_disponibles = new ArrayList<Habilidad_Especial>(p.getHabilidades());
        showHE(habilidades_disponibles);
        System.out.println("Elija su habilidad:");
        int eh = (reader.nextInt() - 1);
        while (!(eh >= 0 && eh < habilidades_disponibles.size())){
                eh = (reader.nextInt() - 1);
        }
        return habilidades_disponibles.get(eh);
    }

    private void showHE(List<Habilidad_Especial> he){
        int i = 1;
        System.out.println("Habilidades disponibles:\n");
        for (Habilidad_Especial h : he){
            System.out.println(i + ": " + h.getNombre());
            i++;
        }
    }

    private void Atacar(Personaje patk, Personaje prec, Habilidad_Especial h){
        int d_extra = 0;
        Random valor_dado = new Random();
        int vd = 0;
        int ataque_final = 0;
        if (patk.getMods() != null) {
            for (Modificador mod : patk.getMods()) {
                if (mod.tipo_mod == Tipo_mod.Fortaleza) {
                    d_extra += mod.grado_Efecto;
                }
            }
        }
        int dados = patk.calcPwr() + h.getAtk() + d_extra;
        for (int i = 0; i < dados; i++){
            vd = valor_dado.nextInt(6) + 1;
            if (vd >= 5){
                ataque_final++;
            }
        }
        recibirDamage(prec, ataque_final);
        System.out.println("El ataque ha hecho " + ataque_final + " de daño!");
        reg.append("Turno " + getTurno() + ": Personaje " + patk.getNombre() + " ataca con " + h.getNombre() + ". " + ataque_final + " dmg\n");
    }

    private void recibirDamage(Personaje atacado ,int atk) {
        List<Esbirro> esbirros = new ArrayList<Esbirro>(atacado.getEsbirros());
        if (esbirros != null && esbirros.size() > 0){
            int i = 0;
            Esbirro esb = esbirros.get(i);
            while (i < esbirros.size() && !atacado.hasFainted() && atk > 0) {
                if (esb.getSalud() > 0) {
                    if (atk <= esb.getSalud()) {
                        esb.setSalud(esb.getSalud() - atk);
                        break;
                    } else {
                        atk -= esb.getSalud();
                        esb.setSalud(0);
                    }
                }
                i++;
                esb = esbirros.get(i);
            }
            if (atk > 0) {
                atacado.setPunt_Salud(atacado.getPunt_Salud() - atk);
            }
        }else {
            atacado.setPunt_Salud(atacado.getPunt_Salud() - atk);
        }
    }
    private void mostrarDesafios(List<Desafio> desafios) {
        if (desafios != null) {
            List<Integer> listIndx = new ArrayList<Integer>();
            int i = 0;
            for (Desafio desaf : desafios) {
                if (desaf.getJ2().getNum_Registro().equals(this.J1.getNum_Registro())  && desaf.getValidado()){
                    System.out.println("Desafio Nº " + i);
                    mostrarDesafio(desaf);
                    i++;
                }else {
                    listIndx.add(i);
                }
            }
            for (int j : listIndx) {
                desafios.remove(j);
            }
        } else {
            System.out.println("No hay desafios");
        }
    }

    private void mostrarDesafio(Desafio desaf) {
        if (desaf != null) {
            System.out.println("J1 : "+desaf.getJ1().getNum_Registro());
            System.out.println("J2 : "+desaf.getJ2().getNum_Registro());
            System.out.println("Oro apostado : " + desaf.getOro());
            System.out.println("ModJ1 : "+mostrarModificador(desaf.getMod_j1()));
            System.out.println("ModJ2 : "+mostrarModificador(desaf.getMod_j2()));
        }
    }

    private String mostrarModificador(Set<Modificador> mods) {
        String dataLine = "";
        if (mods != null) {
            for (Modificador mod : mods) {
                dataLine += mod.getNombre();
            }
        }
        return dataLine;
    }

    private void desafiar() {
        List<Modificador> mods_Disponibles = new ArrayList<Modificador>(this.fileContr.getMods());
        Desafio desafio = new Desafio();
        if (J1 != null) {
            desafio.setJ1(this.J1);
            int opt = 0;

            System.out.println("Se te harán algunas preguntas para crear una solicitud de combate\n");
            Usuario rival = seleccionarUsuario(new ArrayList<Usuario>(this.fileContr.getAllUsuarios().values()));
            if (rival != null) {
                desafio.setJ2(rival);
            }else {
                System.out.println("Algo ha fallado...");
                return;
            }
            int OroApostado = apostarOro();
            desafio.setOro(OroApostado);
            desafio.setValidado(false); // En la creación del desafio, este aun no es validado por un operador
            System.out.println("\nSelecciona tus modificadores\n");
            desafio.setMod_j1(inventario.modificarMods(new HashSet<Modificador>()));
            System.out.println("\nSelecciona los modificadores de tu rival\n");
            desafio.setMod_j2(inventario.modificarMods(new HashSet<Modificador>()));

            Date nowDate = new Date(System.currentTimeMillis());

            if (!this.fileContr.es_Baneable(nowDate,desafio.getJ2())){
                this.fileContr.addDesafio(desafio);

                System.out.println("Tu desafio ha sido mandado");
            }else {
                this.fileContr.banear(this.J1);
                System.out.println("Tu cuenta ha sido baneada");
                System.exit(1);
            }
        }else {
            System.out.println("Algo fue mal con tu usuario...");
        }

    }

    private int apostarOro() {
        int oroApostado = J1.getPersonajeActivo().getOro()+1;
        if (this.J1.getPersonajeActivo() != null){
            while (oroApostado > J1.getPersonajeActivo().getOro()){
                System.out.println("Cuanto oro desea apostar");
                oroApostado = reader.nextInt();
            }
        } else {
            System.out.println("Algo fue mal con tu personaje...");
        }
        return oroApostado;
    }

    private Usuario seleccionarUsuario(ArrayList<Usuario> usuarios) {
        Usuario selectedUser = null;
        if (usuarios != null) {
            int selectOpt = -2;
            while (!((selectOpt == -1) || (selectOpt >= 0 && selectOpt < usuarios.size()))){
                mostrarNombreUsuarios(usuarios);
                System.out.println("Selecciona a quien te quieres enfrentar\n-1. Salir");
                selectOpt = reader.nextInt();
                if ((selectOpt >= 0 && selectOpt < usuarios.size())) {
                    selectedUser = usuarios.get(selectOpt);
                }
            }
        }else {
            System.out.println("No hay usuarios");
        }
        return selectedUser;
    }

    private void mostrarNombreUsuarios(ArrayList<Usuario> usuarios) {
        if (usuarios != null) {
            int i = 0;
            for (Usuario user : usuarios) {
                if (user.getNombre() != null) {
                    if (user.getNombre() != this.J1.getNombre()){
                        System.out.println("Usuario " + i + " : " + user.getNombre());
                    }else {
                        usuarios.remove(user);
                    }
                }
                i++;
            }
        }
    }

    public void createRegistry(){
        try {
            File newRegistry = new File(zonaRegistry);
            FileWriter fw = new FileWriter(zonaRegistry);
            fw.write("" + reg);
            fw.write("ganador - " + getGanador().getNombre() + "(numreg - " + getGanador().getNum_Registro() + ")");
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error : " + ioe);
            throw new RuntimeException(ioe);
        }
    }

    //Get-Set
    public Usuario getJ1() {
        return J1;
    }

    public void setJ1(Usuario j1) {
        J1 = j1;
    }

    public Usuario getJ2() {
        return J2;
    }

    public void setJ2(Usuario j2) {
        J2 = j2;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getOroApostado() {
        return oroApostado;
    }

    public void setOroApostado(int oroApostado) {
        this.oroApostado = oroApostado;
    }

    public Desafio getDesafioCombate() {
        return desafioCombate;
    }

    public void setDesafioCombate(Desafio desafioCombate) {
        this.desafioCombate = desafioCombate;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    public Scanner getReader() {
        return reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public App_Inventario getInventario() {
        return inventario;
    }

    public void setInventario(App_Inventario inventario) {
        this.inventario = inventario;
    }

    public FileController_Combate getFileContr() {
        return fileContr;
    }

    public void setFileContr(FileController_Combate fileContr) {
        this.fileContr = fileContr;
    }

    public Usuario getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(Usuario perdedor) {
        this.perdedor = perdedor;
    }

    public String getZonaRegistry() {
        return zonaRegistry;
    }

    public void setZonaRegistry(String zonaRegistry) {
        this.zonaRegistry = zonaRegistry;
    }
}