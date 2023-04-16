import java.util.Random;
import java.util.Scanner;

public class AppController {
    //Atributos
    public Usuario usuarioActivo = new Usuario();
    public boolean bloqueado;
    Scanner menu_opc = new Scanner(System.in);

    //Metodos

    // MostrarMenu
    public void Menu() {
        System.out.print("Bienvenido, escoge una opción:\n1.Registrarse\n2.Iniciar sesión\n3.Salir\n");
        int opc = menu_opc.nextInt();
        switch(opc){
            case 1:
                Register();
                break;
            case 2:
                // Login();
                break;
            case 3:
                System.exit(0);
            default:
                Menu();
        }
    }
    // Registrarse (Pedir datos, generar codigo LNNLL y crear fichero [Esta en FileController])
    public void Register() {
        System.out.print("Introduce tu nombre de cuenta (Este será usado para LogIn):\n");
        String nombre = menu_opc.next();
        System.out.print("Introduce tu nickname:\n");
        String nick = menu_opc.next();
        System.out.print("Introduce tu contraseña:\n");
        String pswd = menu_opc.next();
        System.out.print("Confirma tu contraseña:\n");
        String rep_pswd = menu_opc.next();
        if (pswd.equals(rep_pswd)){
            Random asd = new Random();
            char c1 = (char)(asd.nextInt(26) + 'a'); // + 'a' para que se posicione dentro del alfabeto
            char c2 = (char)(asd.nextInt(26) + 'a'); // ^^^^^^
            char c3 = (char)(asd.nextInt(26) + 'a'); // ^^^^^^
            int n1 = (asd.nextInt(10));
            int n2 = (asd.nextInt(10));
            Personaje nulo = new Personaje(); // Los nuevos usuarios comienzan sin un personaje
            this.usuarioActivo.setNombre(nombre);
            this.usuarioActivo.setNick(nick);
            this.usuarioActivo.setPassword(pswd);
            this.usuarioActivo.setNum_Registro(""+c1+n1+n2+c2+c3);
            this.usuarioActivo.setRol(Rol.usuario);
            this.usuarioActivo.setPersonajeActivo(nulo);
            FileController nuevoUser = new FileController();
            nuevoUser.addUsuario(usuarioActivo);
            Menu();
        }
        else{
            System.out.print("Contraseña incorrecta.\n");
            Register();
        }
    }

    //Get-Set
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
