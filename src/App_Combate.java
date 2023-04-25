import java.time.LocalDate;
import java.util.*;

public class App_Combate {
    private Usuario J1; //En un principo, este eres tú
    private Usuario J2;
    private int turno;
    private int oroApostado;
    private Desafio desafioCombate;
    private Usuario ganador;
    private Scanner reader = new Scanner(System.in);
    private App_Inventario inventario = new App_Inventario();
    private FileController_Combate fileContr = new FileController_Combate();


    //Metodos
    public void menuCombatePrinicipal(FileController_Operator fil,Usuario user /*Si queremos usar un file controller ya existente*/ /*Recuerda antes asignar un usuario J1*/) {
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
        List<Desafio> desafios = this.fileContr.getSolicitudesDesafio();
        if (desafios != null){
            while (opt!=-1) {
                System.out.println("\nSolicitudes de desafio\n");
                mostrarDesafios(desafios);
                System.out.println("\nElige una solicitud (0-" + (desafios.size() - 1) + ")\n-1.Salir");
                opt = reader.nextInt();
                if (opt >= 0 && opt < desafios.size()) {
                    this.setJ2(desafios.get(opt).getJ1());
                    this.setDesafioCombate(desafios.get(opt));
                    preCombate();
                    break;
                }else {
                    System.out.println("\nOpcion no valida\n");
                }
            }
        } else {
            System.out.println("No hay desafios actualmente");
        }
    }

    private void preCombate() {
        int opt = 0;
        while (opt != 3){
            System.out.println("Desea hacer modificar su invetrario?\n1. Si\n2. No, Empezar combate\n3. Salir");
            opt = this.reader.nextInt();
            if(opt == 1){
                this.inventario.modificarPers(this.J1,this.J1.getRol());
            } else if (opt == 2) {
                combate();
            }
        }

    }

    private void combate() {
        /*Anotaciones
        * -Ahora mismo, no se dispone de servidores, por lo tanto se ussará el esta misma cuenta, la del desafiado
        *   para hacer el combate*/
        //Tambien hará todo lo relacionado con la presistencia

        
    }

    private void mostrarDesafios(List<Desafio> desafios) {
        if (desafios != null) {
            int i = 0;
            for (Desafio desaf : desafios) {
                if (desaf.getJ2().getNum_Registro().equals(this.J1.getNum_Registro())  && desaf.getValidado()){
                    System.out.println("Desaafio Nº " + i);
                    mostrarDesafio(desaf);
                    i++;
                }else {
                    desafios.remove(desaf);
                }
            }
            if (i == 0) {
                System.out.println("\nNo tienes solicitudes de desafio, vulve más tarde\n");
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
                if ((selectOpt == -1) || (selectOpt >= 0 && selectOpt < usuarios.size())) {
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
}
