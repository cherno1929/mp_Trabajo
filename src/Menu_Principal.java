import java.util.Scanner;

public class Menu_Principal {
    Scanner eleccion = new Scanner(System.in);
    App_Operador Operador = new App_Operador();
    AppController ac = new AppController();
    public void Pantalla_Inicio(Rol rol){
        System.out.print("Elige una opción:\n1.Desafíos\n2.Inventario\n3.Ranking\n4.Salir\n");
        if (rol == Rol.operador){
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
                if (!rol.equals(Rol.operador)) {
                    System.out.println("Usted no tiene acceso a esa opción.");
                    Pantalla_Inicio(rol);
                }
                else{
                    Operador.Menu();
                }
            default:
                System.out.println("Opción incorrecta. Por favor, vuelva a introducir su dato.");
                Pantalla_Inicio(rol);
        }
    }
}
