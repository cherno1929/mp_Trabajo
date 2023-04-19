import java.util.List;
import java.util.Scanner;

public class App_Operador {
    //Atributos
    private Usuario operador;
    private FileController_Operator herramienta_Operador = new FileController_Operator();
    private List<Desafio> lista_Desafios = herramienta_Operador.getDesafios();
    public Menu_Principal mp;
    //Metodos
    public void Menu(Menu_Principal menuPrincipal) {
        this.setMp(menuPrincipal);
        System.out.print("Bienvenido, escoge una opción:\n1.Banear Usuario\n2.Desbanear Usuario\n3.Ver Lista de desafios (hay " + this.lista_Desafios.size() + " desafios)\n4.Volver\n");
        Scanner menu_opc = new Scanner(System.in);
        int opc = menu_opc.nextInt();
        switch (opc) {
            case 1:
                this.MenuBaneo(this.lista_Desafios);
                Menu(this.getMp());
                break;
            case 2:
                MenuDesBaneo(this.herramienta_Operador.getBaneados());
                break;
            case 3:
                if (this.lista_Desafios != null) {
                    this.MenuLista();
                } else {
                    System.out.println("No hay desafios de momento");
                }
                Menu(this.getMp());
                break;
            case 4:
                this.mp.Pantalla_Inicio(this.mp.rolUser);
                break;
            default:
                Menu(this.getMp());
        }
    }

    public void MenuDesBaneo(List<Usuario> listBaneados){
        int opt = 0;
        while (opt != -1 && listBaneados.size() > 0){
            System.out.println("A quien quieres desbanear ?? \n");
            int i = 0;
            for (Usuario baneado : listBaneados){
                showUsuario(baneado);
                System.out.println("Opcion :: "+i + "\n");
                i++;
            }
            System.out.println("Elige -1 si desea salir");
            Scanner scanOpt = new Scanner(System.in);
            opt = scanOpt.nextInt();
            if (opt >= 0  && opt < listBaneados.size()){
                System.out.println("Que rol le quieres asignar a "+listBaneados.get(opt).getNick() + "\n1.Operador\n2.Usuario");
                int opt2 = scanOpt.nextInt();
                Rol nuevoRol;
                if (opt2 == 1){
                    nuevoRol = Rol.operador;
                }else {
                    nuevoRol = Rol.usuario;
                }
                this.herramienta_Operador.desBanear(listBaneados.get(opt),nuevoRol);
                listBaneados = this.herramienta_Operador.getBaneados();
            }
        }
        if (listBaneados.size() == 0){
            System.out.println("No quedan baneados");
        }
        Menu(this.getMp());
    }

    public void showUsuario(Usuario user){
        if (user != null) {
            System.out.println("Nombre :: "+user.getNombre());
            System.out.println("Nick :: "+user.getNick());
            if (user.getRol() != null){
                System.out.println("Num_Registro :: " + user.getNum_Registro());
            }
            System.out.println();
        }
    }

    public void MenuBaneo(List<Desafio> listaDesafios){
        int opt = 1;
        while (opt != 0){
            if (listaDesafios != null) {
                int i = 0;
                for (Desafio desafio : listaDesafios) {
                    {
                        System.out.println("\nDESAFIO - "+ i);
                        showDesafio(desafio);
                        i++;
                    }
                }
                System.out.println("\nA quien desea banear?\nDe un numero del 0 al " + (listaDesafios.size() - 1) + "\nPulse a -1 para Salir");
                Scanner scanerReadln = new Scanner(System.in);
                opt = scanerReadln.nextInt();
                Desafio user = this.lista_Desafios.get(opt);
                if (user != null){
                    this.herramienta_Operador.banear(user.getJ1());
                }
            } else {
                System.out.println("No hay desafios");
                this.Menu(this.getMp());
            }
        }
        this.Menu(this.getMp());
    }

    private void MenuLista(){
        for (Desafio desafio : this.lista_Desafios){
            this.showDesafio(desafio);
        }
        int op = 0;
        System.out.println("\nQue desafio desea validar / modificar (hay " + this.lista_Desafios.size() + " desafios)\n Salir (-1)");
        Scanner menL = new Scanner(System.in);
        op = menL.nextInt();
        while (op != -1){
            int numDesafio = menL.nextInt();
            if ((numDesafio >= 0) & (numDesafio < this.lista_Desafios.size())) {
                this.MenuDesafio(numDesafio);
            }
            System.out.println("Que desafio desea validar / modificar (hay " + this.lista_Desafios.size() + " desafios)\n Salir (-1)");
            menL = new Scanner(System.in);
            op = menL.nextInt();
        }
    }

    private void MenuDesafio(int numDesafio){
        this.showDesafio(this.lista_Desafios.get(numDesafio));
        System.out.println("\nQue desea hacer : \n1) Validar (1) \n2) Modificar (2)\n3) Salir (0)");
        Scanner optionSelected = new Scanner(System.in);
        int opt = optionSelected.nextInt();
        if (opt < 3  && opt >= 0){
        }
    }

    private void showDesafio(Desafio desafio){
        System.out.println("\nJugador 1 :: "+desafio.getJ1().getNum_Registro());
        System.out.println("Jugador 2 :: "+desafio.getJ2().getNum_Registro());
        System.out.println("Oro apostado :: "+desafio.getOro());
        System.out.println("\nModificador J1");
        if (desafio.getMod_j1() != null){
            for (Modificador mod : desafio.getMod_j1()) {
                System.out.println(mod.getNombre());
            }
        }
        if (desafio.getMod_j2() != null){
            System.out.println("\nModificador J2");
            for (Modificador mod : desafio.getMod_j2()) {
                System.out.println(mod.getNombre());
            }
        }
    }
    //Get-Set

    public Usuario getOperador() {
        return operador;
    }

    public void setOperador(Usuario operador) {
        this.operador = operador;
    }

    public Menu_Principal getMp() {
        return mp;
    }

    public void setMp(Menu_Principal mp) {
        this.mp = mp;
    }
}
