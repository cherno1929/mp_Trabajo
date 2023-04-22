import java.util.Scanner;

public class Menu_Principal {
    Scanner eleccion = new Scanner(System.in);
    App_Operador Operador = new App_Operador();
    AppController ac = new AppController();
    public Rol rolUser;
    public void Pantalla_Inicio(Rol rol){
        this.rolUser = rol;
        System.out.print("Elige una opción:\n1.Desafíar\n2.Inventario\n3.Ranking\n4.Salir\n");
        boolean esAdmin = rol == Rol.operador;
        if (esAdmin){
            System.out.print("5.Opciones avanzadas\n");
        }
        int choice = eleccion.nextInt();
        switch(choice) {
            case 1:
                // Desafios()
                break;
            case 2:
                // Inventario()
                break;
            case 3:
                ac.showRanking();
                break;
            case 4:
                System.exit(1);
                break;
            case 5:
                if (!esAdmin){
                        System.out.println("Usted no tiene acceso a esa opción.");
                        Pantalla_Inicio(rol);
                }
                else{
                    Operador.Menu(this);
                }
            default:
                System.out.println("Opción incorrecta. Por favor, vuelva a introducir su dato.\n");
                Pantalla_Inicio(rol);
        }
    }
}
