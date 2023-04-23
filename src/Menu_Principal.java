import java.util.Scanner;

public class Menu_Principal {
    Scanner eleccion = new Scanner(System.in);
    App_Operador Operador = new App_Operador();
    AppController ac = new AppController();
    public Rol rolUser;
    public void Pantalla_Inicio(Usuario user){
        this.rolUser = user.getRol();
        int choice = 0;
        boolean esAdmin = user.getRol() == Rol.operador;
         while (choice != 4){
            System.out.print("\nElige una opción:\n1.Desafiar\n2.Inventario\n3.Ranking\n4.Salir\n");
            if (esAdmin) {
                System.out.print("5.Opciones avanzadas\n");
            }
            choice = eleccion.nextInt();
            switch (choice) {
                case 1:
                    // Desafiar()
                    break;
                case 2:
                    // Inventario()
                    break;
                case 3:
                    this.ac.showRanking();
                    break;
                case 4:
                    System.out.println("Que tenga buen día");
                    System.exit(1);
                    break;
                case 5:
                    if (!esAdmin) {
                        System.out.println("Usted no tiene acceso a esa opción.");
                        this.Pantalla_Inicio(user);
                    } else {
                        this.Operador.Menu(this);
                    }
                case 6:
                    ac.CreateCharacter();
                default:
                    System.out.println("Opción incorrecta. Por favor, vuelva a introducir su dato.\n");
                    this.Pantalla_Inicio(user);
            }
        }
    }
}