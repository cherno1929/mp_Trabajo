import java.util.List;
import java.util.Scanner;

public class App_Operador {
    //Atributos
    private Usuario operador;
    private FileController_Operator herramienta_Operador = new FileController_Operator();
    private List<Desafio> lista_Desafios = herramienta_Operador.getDesafios();

    //Metodos
    public void Menu() {
        System.out.print("Bienvenido, escoge una opción:\n1.Banear Usuario\n2.Desbanear Usuario\n3.Ver Lista de desafios (hay "+this.lista_Desafios.size()+" desafios)\n4.Volver\n");
        Scanner menu_opc = new Scanner(System.in);
        int opc = menu_opc.nextInt();
        switch(opc){
            case 1:
                System.out.println("A quien desea banear?");
                Scanner usuario = new Scanner(System.in);
                this.herramienta_Operador.banear(String.valueOf(usuario));
                Menu();
                break;
            case 2:
                System.out.println("A quien desea desbanear?");
                usuario = new Scanner(System.in);
                this.herramienta_Operador.desBanear(String.valueOf(usuario));
                Menu();
                break;
            case 3:
                   if (this.lista_Desafios != null) {
                        this.MenuLista();
                    }else {
                       System.out.println("No hay desafios de momento");
                       Menu();
                   }
                break;
            case 4:
                Menu_Principal mp = new Menu_Principal();
                mp.Pantalla_Inicio(Rol.operador);
            default:
                Menu();
        }
    }

    private void MenuLista(){
        for (Desafio desafio : this.lista_Desafios){
            this.showDesafio(desafio);
        }
        System.out.println("Que desafío desea validar / modificar (hay "+ this.lista_Desafios.size()+" desafios)\n");
        Scanner menL = new Scanner(System.in);
        int numDesafio = menL.nextInt();
        if ((numDesafio >= 0 ) && (numDesafio < this.lista_Desafios.size())){
            this.MenuDesafio(numDesafio);
        }
    }

    private void MenuDesafio(int numDesafio){
        this.showDesafio(this.lista_Desafios.get(numDesafio));
        System.out.println("Que desea hacer:\n1) Validar\n2) Modificar\n");
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
