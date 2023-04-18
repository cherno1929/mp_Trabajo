import java.util.*;

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
                LogIn();
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
            char c1 = (char)(asd.nextInt(26) + 'a'); // + 'a' para que se posicione dentro del rango de alfabeto
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
    // LogIn (Pedir datos -> Comprobar existencia -> Si existe, comprobar datos -> Dejar pasar si esta to bien)
    public void LogIn() {
        System.out.print("Introduce tu nombre de cuenta (NO NICKNAME!): ");
        String name_login = menu_opc.next();
        System.out.print("Introduce la contraseña asociada a la cuenta: ");
        String pswd_login = menu_opc.next();
        System.out.print("Comprobando cuenta...\n\n");
        String hash_login = name_login + "-" + pswd_login; //Creación de clave para acceder al usuario
        FileController aux_file = new FileController();
        Map<String,Usuario> lista_usuarios = aux_file.getAllUsuarios(); //Guardamos datos de todos los usuarios en una lista
        if (!(lista_usuarios.get(hash_login) == null)) { //Si a la hora de recolectar el usuario de la lista, nos devuelven datos (Existe)...
            if (!aux_file.existeUsuario(lista_usuarios.get(hash_login))) { //Si nuestra clave NO coincide con uno de los usuarios en la lista...
                System.out.print("Nombre o contraseña incorrectos, vuelva a intentarlo.\n");
                Menu(); //...De vuelta al menu.
            }
            else { //Si, al contrario, sí existe...
                Usuario user_log = lista_usuarios.get(hash_login);
                if (user_log.getRol().equals(Rol.baneado)) {
                    System.out.print("Esta cuenta está vetada. Contacte con un operador si considera esta situación como errónea.\n");
                    Menu();
                }
                else{
                    System.out.print("Bienvenido, " + user_log.nick + "...\n");
                    Menu_Principal mp = new Menu_Principal();
                    mp.Pantalla_Inicio(user_log.getRol());
                }
            }
        }
        else{ //En cambio, si este no es encontrado (NO recibimos datos de la lista)...
            System.out.print("Nombre o contraseña incorrectos, vuelva a intentarlo.\n");
            Menu();
        }

    }

    // MostrarRanking (Recolectar victorias -> Ordenarlas en un hash (Mayor a menor cantidad victorias) -> Mostrar al usuario)
    public void showRanking() {
        FileController ranking = new FileController();
        List<String> usuarios_ranking = ranking.verGanadores();
        HashMap<String, Integer> mapa_ranking = new HashMap<String, Integer>();
        for(String lectura_usuario : usuarios_ranking){
            if (!mapa_ranking.containsKey(lectura_usuario)){
                mapa_ranking.put(lectura_usuario, 1);
            }
            else{
                mapa_ranking.put(lectura_usuario, mapa_ranking.get(lectura_usuario) + 1);
            }
            System.out.println(lectura_usuario + " - " + mapa_ranking.get(lectura_usuario));
        }
        // FALTA ORDENAR
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
