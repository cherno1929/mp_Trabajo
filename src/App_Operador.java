import java.util.List;
import java.util.Scanner;

public class App_Operador {
    //Atributos
    private Usuario operador;
    private FileController_Operator herramienta_Operador = new FileController_Operator();
    private List<Desafio> lista_Desafios = herramienta_Operador.getDesafios();

    //Metodos
    public void Menu() {
        if (this.operador.getRol() == Rol.operador){
            System.out.print("Bienvenido, escoge una opción:\n1.Banear Usuario\n2.Desbanear Usuario\n3.Ver Lista de desafios (hay " + this.lista_Desafios.size() + " desafios)\n4.");
            Scanner menu_opc = new Scanner(System.in);
            int opc = menu_opc.nextInt();
            switch (opc) {
                case 1:
                    this.MenuBaneo(this.lista_Desafios);
                    Menu();
                    break;
                case 2:
                    this.MenuDesBaneo(this.herramienta_Operador.getBaneados());
                    break;
                case 3:
                    if (this.lista_Desafios != null) {
                        this.MenuLista();
                    } else {
                        System.out.println("No hay desafios de momento");
                    }
                    Menu();
                    break;
                case 4:
                    break;
                default:
                    Menu();
            }
        }else {
            System.out.println("Rol invalido");
        }
    }



    public void MenuDesBaneo(List<Usuario> listBaneados){
        int opt = 1;
        while (opt != -1){
            if (listBaneados != null) {
                for (Desafio desafio : lista_Desafios) {
                    {
                        showDesafio(desafio);
                    }
                }
                System.out.println("A quien desea banear?\nDe un numero del 0 al " + listBaneados.size() + "\nPulse a -1 para Salir");
                Scanner scanerReadln = new Scanner(System.in);
                opt = scanerReadln.nextInt();
                this.herramienta_Operador.desBanear(this.lista_Desafios.get(opt).getJ1());
            } else {
                System.out.println("No hay desafios");
                this.Menu();
            }
        }
        this.Menu();
    }

    public void MenuBaneo(List<Desafio> listaDesafios){
        int opt = 1;
        while (opt != 0){
            if (listaDesafios != null) {
                for (Desafio desafio : listaDesafios) {
                    {
                        showDesafio(desafio);
                    }
                }
                System.out.println("A quien desea banear?\nDe un numero del 0 al " + listaDesafios.size() + "\nPulse a 0 para Salir");
                Scanner scanerReadln = new Scanner(System.in);
                opt = scanerReadln.nextInt();
                this.herramienta_Operador.banear(this.lista_Desafios.get(opt).getJ1());
            } else {
                System.out.println("No hay desafios");
                this.Menu();
            }
        }
        this.Menu();
    }

    private void MenuLista(){
        for (Desafio desafio : this.lista_Desafios){
            this.showDesafio(desafio);
        }
        System.out.println("Que desafio desea validar / modificar (hay "+ this.lista_Desafios.size()+" desafios)\n");
        Scanner menL = new Scanner(System.in);
        int numDesafio = menL.nextInt();
        if ((numDesafio >= 0 ) & (numDesafio < this.lista_Desafios.size())){
            this.MenuDesafio(numDesafio);
        }
    }

    private void MenuDesafio(int numDesafio){
        this.showDesafio(this.lista_Desafios.get(numDesafio));
        System.out.println("Que desea hacer : \n1) Validar \n2) Modificar \n");
        //??????????????????????????????????????????????????????
    }
    private void showDesafio(Desafio desafio){
        System.out.println("Jugador 1 :: "+desafio.getJ1().getNum_Registro());
        System.out.println("Jugador 2 :: "+desafio.getJ2().getNum_Registro());
        System.out.println("Oro apostado :: "+desafio.getOro());
        System.out.println("Modificador J1\n");
        if (desafio.getMod_j1() != null){
            for (Modificador mod : desafio.getMod_j1()) {
                System.out.println(mod.getNombre() + "\n");
            }
        }
        if (desafio.getMod_j2() != null){
            System.out.println("Modificador J2\n");
            for (Modificador mod : desafio.getMod_j2()) {
                System.out.println(mod.getNombre() + "\n");
            }
        }
        System.out.println("\n");
    }
    //Get-Set

    public Usuario getOperador() {
        return operador;
    }

    public void setOperador(Usuario operador) {
        this.operador = operador;
    }



}
