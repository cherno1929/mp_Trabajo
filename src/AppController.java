import java.io.File;
import java.util.*;

public class AppController {
    //Atributos
    public Usuario usuarioActivo = new Usuario();
    public Usuario user_log = new Usuario();
    public boolean bloqueado;
    Scanner menu_opc = new Scanner(System.in);
    FileController fc = new FileController();

    /*Metodos*/

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
        user_log = lista_usuarios.get(hash_login);
        if (!(user_log == null)) { //Si a la hora de recolectar el usuario de la lista, nos devuelven datos (Existe)...
            if (!aux_file.existeUsuario(lista_usuarios.get(hash_login))) { //Si nuestra clave NO coincide con uno de los usuarios en la lista...
                System.out.print("Nombre o contraseña incorrectos, vuelva a intentarlo.\n");
                Menu(); //...De vuelta al menu.
            }
            else { //Si, al contrario, sí existe...
                if (user_log.getRol().equals(Rol.baneado)) {
                    System.out.print("Esta cuenta está vetada. Contacte con un operador si considera esta situación como errónea.\n");
                    Menu();
                }
                else{
                    System.out.print("Bienvenido, " + user_log.nick + "...\n");
                    Menu_Principal mp = new Menu_Principal();
                    usuarioActivo = user_log;
                    mp.Pantalla_Inicio(user_log);
                }
            }
        }
        else{ //En cambio, si este no es encontrado (NO recibimos datos de la lista)...
            System.out.print("Nombre o contraseña incorrectos, vuelva a intentarlo.\n");
            Menu();
        }
    }

    // MostrarRanking (Recolectar victorias -> Ordenarlas en un hash (Mayor a menor cantidad victorias) -> Mostrar al usuario 10 primeros)
    public void showRanking() {
        FileController ranking = new FileController(); // Para observar el archivo de ranking
        List<String> usuarios_ranking = ranking.verGanadores(); // Vemos la lista de victorias
        HashMap<String, Integer> mapa_ranking = new HashMap<>();
        for(String lectura_usuario : usuarios_ranking){
            if (!mapa_ranking.containsKey(lectura_usuario)){
                mapa_ranking.put(lectura_usuario, 1);
            }
            else{
                mapa_ranking.put(lectura_usuario, mapa_ranking.get(lectura_usuario) + 1);
            }
        } // Metemos cada valor en un hashmap, si se repite solamente suma 1 a su valor
        // ORDENAR
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>(); //Mapa que guarda 10 primeros
        ArrayList<Integer> list = new ArrayList<>(); // Lista de valores para ordenar del map original
        for (Map.Entry<String, Integer> entry : mapa_ranking.entrySet()) {
            list.add(entry.getValue());
        } //Insertar valores en lista
        Collections.sort(list); // Ordenar lista (Menor a mayor)
        int i = (list.size() - 1); // Ultimo elemento de lista
        while(i > (list.size() - 11)) {
            for (Map.Entry<String, Integer> entry : mapa_ranking.entrySet()) { //Buscamos por el hashmap
                if (entry.getValue().equals(list.get(i))) {
                    sortedMap.put(entry.getKey(), list.get(i));
                    i--; // Y nos movemos por la lista para buscar el siguiente valor
                } // Lo metemos al nuevo mapa, el que guarda los 10 primeros
            } // Si nos coincide algo en el hashmap con el valor que recogemos de la lista
        } // Hasta que hayamos bajado 10 valores desde el ultimo elemento de lista
        for (String nombreordenado : sortedMap.keySet()){
            System.out.println(nombreordenado + " - " + sortedMap.get(nombreordenado));
        } // Mostramos los 10 primeros (sortedMap)
    }

    /* CrearPersonaje (Otorgar campos para rellenar al usuario, generar numeros y letras aleatorios formato XXXXX,
       crear archivo de personaje y agregar personaje a clase usuario por ID) */
    public void CreateCharacter(Usuario user){
        List<Arma> mand = new ArrayList<>(fc.buscarArmas(new String[]{"Mandoble"}));
        System.out.print("Introduce el nombre de tu personaje:\n");
        String nombre_pj = menu_opc.next();
        System.out.print("Introduce la raza de tu personaje (Vampiro, Licantropo, Cazador):\n");
        String raza = menu_opc.next();
        switch(raza){
            case "Vampiro":
                System.out.print("Introduce la edad de tu vampiro en años (Mín 1, máx 5000):\n");
                int edad = menu_opc.nextInt();
                if (edad < 1) {
                    edad = 1;
                } else if (edad > 5000) {
                    edad = 5000;
                }
                Vampiro pjvamp = new Vampiro(nombre_pj, fc.getDisciplinas(), fc.buscarArmas(new String[]{"Mandoble"}), mand, fc.buscarArmaduras(new String[]{"Armadura_de_cuero"}), null,500,5,fc.buscarMods(new String[]{"Sol"}),5,0);
                pjvamp.setEdad(edad);
                pjvamp.setId(getRandomId());
                user.setPersonajeActivo(pjvamp);
                fc.addPersonaje(pjvamp);
                fc.modificarUsuario(user);
                break;
            case "Licantropo":
                System.out.print("Introduce el peso de tu licántropo en kg (Mín 80, máx 400):\n");
                int peso = menu_opc.nextInt();
                if (peso < 80) {
                    peso = 80;
                } else if (peso > 400) {
                    peso = 400;
                }
                System.out.print("Introduce la altura de tu licántropo en centímetros (Mín 150, máx 200):\n");
                int altura = menu_opc.nextInt();
                if (altura < 150) {
                    altura = 150;
                } else if (altura > 200) {
                    altura = 200;
                }
                Licantropo pjlic = new Licantropo(nombre_pj, fc.getDones(), fc.buscarArmas(new String[]{"Mandoble"}), mand, fc.buscarArmaduras(new String[]{"Armadura_de_cuero"}), null, 500, 5, fc.buscarMods(new String[]{"Sol"}), 5, 0);
                pjlic.setAltura(altura);
                pjlic.setPeso(peso);
                pjlic.setId(getRandomId());
                user.setPersonajeActivo(pjlic);
                fc.addPersonaje(pjlic);
                fc.modificarUsuario(user);
                break;
            case "Cazador":
                Cazador pjcaz = new Cazador(nombre_pj, fc.getTalentos(), fc.buscarArmas(new String[]{"Mandoble"}), mand, fc.buscarArmaduras(new String[]{"Armadura_de_cuero"}), null, 500, 5, fc.buscarMods(new String[]{"Sol"}), 5, 0);
                pjcaz.setId(getRandomId());
                user.setPersonajeActivo(pjcaz);
                fc.addPersonaje(pjcaz);
                fc.modificarUsuario(user);
                break;
            default:
                System.out.print("Esa raza no existe. Por favor, introduzca la raza de nuevo:");
        }
    }

     String getRandomId() {
        boolean valid = false;
        String randomId = null;
        Random randChar = new Random();
        Set<String> filesPersonajes = this.fc.searchFiles(new File(this.fc.localPersoanjes));
        while (!valid) {
            randomId = "";
            for (int i = 0; i < 5; i++) {
                randomId += (char) (randChar.nextInt(26) + 'A');
            }
            valid = !filesPersonajes.contains(randomId);
        }
        return randomId;
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
