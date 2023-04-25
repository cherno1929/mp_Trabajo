import java.util.Scanner;

public class Menu_Principal {
    Scanner eleccion = new Scanner(System.in);
    App_Operador Operador = new App_Operador();
    AppController ac = new AppController();
    App_Combate comb = new App_Combate();
    App_Inventario invt = new App_Inventario();
    public Usuario user;

    public void Pantalla_Inicio(Usuario user){
        this.setUser(user);
        int choice = 0;
        boolean esAdmin = (this.user.getRol() == Rol.operador);
         while (choice != 4){
            System.out.print("\nElige una opción:\n1.Desafiar\n2.Inventario\n3.Ranking\n4.Salir\n");
            if (esAdmin) {
                System.out.print("5.Opciones avanzadas\n");
            }
            choice = eleccion.nextInt();
            switch (choice) {
                case 1:
                    this.comb.menuCombatePrincipal(null,user);
                    break;
                case 2:
                    this.invt.modificarPers(user, Rol.usuario);
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
                    ac.CreateCharacter(user);
                default:
                    System.out.println("Opción incorrecta. Por favor, vuelva a introducir su dato.\n");
                    this.Pantalla_Inicio(user);
            }
        }
    }


    //Get-Set

    public Scanner getEleccion() {
        return eleccion;
    }

    public void setEleccion(Scanner eleccion) {
        this.eleccion = eleccion;
    }

    public App_Operador getOperador() {
        return Operador;
    }

    public void setOperador(App_Operador operador) {
        Operador = operador;
    }

    public AppController getAc() {
        return ac;
    }

    public void setAc(AppController ac) {
        this.ac = ac;
    }

    public App_Combate getComb() {
        return comb;
    }

    public void setComb(App_Combate comb) {
        this.comb = comb;
    }

    public App_Inventario getInvt() {
        return invt;
    }

    public void setInvt(App_Inventario invt) {
        this.invt = invt;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}