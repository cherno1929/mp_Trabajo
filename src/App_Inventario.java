import java.util.*;

public class App_Inventario {
    //Atributos
    private List<Arma> inventarioArmas;
    private List<Armadura> inventarioArmadura;
    private  Personaje PersJug;
    private  Personaje inventarioMods;
    private Scanner reader = new Scanner(System.in);
    private FileController fileContrl= new FileController();

    //Metodos


    //Contructor
    public App_Inventario(List<Arma> inventarioArmas, List<Armadura> inventarioArmadura, Personaje persJug, Personaje inventarioMods) {
        this.inventarioArmas = inventarioArmas;
        this.inventarioArmadura = inventarioArmadura;
        PersJug = persJug;
        this.inventarioMods = inventarioMods;
    }

    public App_Inventario() {

    }

    public void showInventario(){
        showPersoanje(this.PersJug);
    }

    public List<Personaje> getAllPersonajes(){
        List<Personaje> allPersj = new ArrayList<Personaje>();
        return null;
    }

    private void showPersoanje(Personaje persJug) {
        if (persJug != null){
            System.out.println("Nombre : " + persJug.getNombre());
            System.out.println("Poder :: " + persJug.getPoder());
            System.out.println("Salud :: " + persJug.getPunt_Salud());
            System.out.println("Oro :: " + persJug.getOro());
            showArmas(persJug.getArmas());
            showArmaduras(persJug.getArmaduras());
            showModificadores(persJug.getMods());
            showEsbirros(persJug.getEsbirros());
        }
    }

    private void showEsbirros(Set<Esbirro> esbirros) {
        System.out.print("Esbirros :: ");
        if (esbirros != null){
            int i = 0;
            for (Esbirro arm : esbirros) {
                System.out.print("Esbirro " + i + " : " + arm.getNombre());
                i++;
            }
        }else {
            System.out.print("Armaduras :: ");
        }
    }

    private void showModificadores(Set<Modificador> mods) {
        System.out.print("Modificadores :: ");
        if (mods != null){
            int i = 0;
            for (Modificador arm : mods) {
                System.out.print("Modificador " + i + " : " + arm.getNombre());
                i++;
            }
        }else {
            System.out.print(":(");
        }
    }

    private void showArmaduras(Set<Armadura> persArmadura) {
        System.out.print("Armaduras :: ");
        if (persArmadura != null){
            int i = 0;
            for (Armadura arm : persArmadura) {
                System.out.print("Armadura " + i + " : " + arm.getNombre());
                i++;
            }
        }else {
            System.out.println(":(");
        }
    }

    private void showArmas(Set<Arma> persArma) {
        System.out.print("Armas :: ");
        if (persArma != null){
            int i = 0;
            for (Arma arm : persArma) {
                System.out.print("Arma " + i + " : " + arm.getNombre());
                i++;
            }
            System.out.println();
        }else {
            System.out.println(":(");
        }
    }

    //Metodos
    public Set<Modificador> modificarMods(Set<Modificador> modJ1) {
        int opMod = 0;
        if (modJ1 == null){
            modJ1 = new HashSet<Modificador>();
        }
        while (opMod != 3){
            if (modJ1.size() > 0){
                this.mostrarModicadores(modJ1);
                System.out.println("1. Eliminar Modificador\n2. Añadir Modifacador\n3. Salir\n(Hay " + modJ1.size() + " modificadores)\n");
                opMod = this.reader.nextInt();
                if (opMod == 1) {
                    eleminarModificador(modJ1);
                } else if (opMod == 2) {
                    añadirModificador(modJ1);
                }
            } else {
                System.out.println("No hay Modificadores...\nDesea añadir alguno?\n1. Añadir\n3. Salir");
                opMod = this.reader.nextInt();
                if (opMod == 1) {
                    añadirModificador(modJ1);
                }
            }
        }
        if (modJ1.size() == 0){
            System.out.println("No hay modificadores");
        }
        return modJ1;
    }

    private void añadirModificador(Set<Modificador> modJ1) {
        FileController modReader = new FileController();
        List<Modificador> allMods = this.fileContrl.getMods();
        int i = 0;
        for (Modificador mod : allMods){
            System.out.println("Nº " + i);
            showModificador(mod);
            i++;
        }


            System.out.println("\nElige un modificador para añadirlo");
            int op = reader.nextInt();
            if (op < allMods.size() && op >= 0){

        modJ1.add(allMods.get(op));
            }
    }

    private void eleminarModificador(Set<Modificador> modJ1) {
        int op = 0;
        List<Modificador> modsList = new ArrayList<Modificador>(modJ1);
        while (op != -1 && modJ1.size() > 0){
            mostrarModicadores(modsList);
            if (modsList.size() == 1){
                System.out.println("Elija un modificador (0)  \n-1. Salir");
            }else {
                System.out.println("Elija un modificador, del 0 al " + (modJ1.size()-1) + "\n-1. Salir\n");
            }
            op = reader.nextInt();
            if (op != -1 && op >= 0 && op < modJ1.size()) {
                modJ1.remove(modsList.get(op));
            }
        }
    }

    private void mostrarModicadores(Set<Modificador> modJ1) {
        int i = 0;
        if (modJ1 != null){
            for (Modificador mod : modJ1) {
                System.out.println("Nº " + i);
                this.showModificador(mod);
                i++;
            }
        }
    }

    private void mostrarModicadores(List<Modificador> modJ1) {
        int i = 0;
        for (Modificador mod : modJ1){
            System.out.println("Nº Mod "+i);
            this.showModificador(mod);
            i++;
        }
    }

    private void showModificador(Modificador mod) {
        System.out.println("Nombre : " + mod.getNombre());
        System.out.println("Grado de efecto : " + mod.getGrado_Efecto());
        System.out.println("Tipo de modificador : " + mod.getTipo_mod()+"\n");
    }

    public void modificarPers(Usuario j1) {
        mostrarPersonaje(j1);
    }

    private void mostrarPersonaje(Usuario j1) {
        if (j1 != null || j1.getPersonajeActivo() != null){
            int opt = 0;
            while (opt != 10){
                System.out.println("\nDueño : " + j1.getNombre());
                j1.getPersonajeActivo().mostrarPersonaje();
                System.out.println("\nQue desea hacer?\n1. Armas\n2. Armaduras\n3.Ver oro\n4. Cambiar Nombre\n5. Cambiar Modificadores\n6. Cambiar Habilidades\n7. Cambiar Salud\n8. Cambiar Esbirros\n9. Cambiar Poder\n9. Cambiar Escudo\n10. Salir");
                opt = this.reader.nextInt();
                if (opt == 1){
                    this.modificarArmas(j1.getPersonajeActivo());
                } else if (opt == 2) {
                    this.modificarArmadura(j1.getPersonajeActivo()); // El personaje solo puede equiparse una armadura
                } else if (opt == 3) {
                    this.verOro(j1.getPersonajeActivo());
                } else if (opt == 4) {
                    this.cambiarNombrePers(j1.getPersonajeActivo());
                }
            }
        }else {
            System.out.println("Error al encontrar al personaje");
        }
    }

    private void cambiarNombrePers(Personaje personajeActivo) {
        System.out.println("Que nombre desea poner ?\n1. Salir");

    }

    private void modificarArmadura(Personaje pers) {
        int opt = 0;
        while (opt != 3) {
            pers.mostrarArmaduras();
            if (pers.getArmadura_Activa() != null){
                mostrarArmadura(pers.armadura_Activa);
                System.out.println("\nQue desea hacer?\n1. Poner armadura\n2. Quitar armadura");
                opt = reader.nextInt();
                if (opt == 1) {
                    añadirArmadura(pers);
                }
                if (opt == 2) {
                    pers.setArmadura_Activa(null);
                }
            } else {
                System.out.println("\nDesea cambiar la armadura?\n1. Si\n2. No");
                opt = reader.nextInt();
                if (opt == 1) {
                    añadirArmadura(pers);
                } else if (opt == 2) {
                    break;
                }
            }
        }
    }

    private void añadirArmadura(Personaje pers) {
        List<Armadura> armaduras = new ArrayList<Armadura>(pers.getArmaduras());
        mostrarArmaduras(armaduras);
        System.out.println("Elija una armadura (0-" +(pers.getArmaduras().size()-1)+")");
        int opt = reader.nextInt();
        {
            if (opt >= 0 && opt < pers.getArmaduras().size()) {
                if (pers.armadura_Activa == null){
                    pers.setArmadura_Activa(armaduras.get(opt));
                }else if (pers.armadura_Activa.equals(armaduras.get(opt))) {
                    System.out.println("Esta armadura ya existe");
                }
            }
        }
    }

    private void mostrarArmaduras(List<Armadura> armaduras) {
        if (armaduras != null) {
            for (Armadura arm : armaduras) {
                mostrarArmadura(arm);
            }
        }
    }


    private void mostrarArmadura(Armadura armaduraActiva) {
        if (armaduraActiva != null) {
            System.out.println("\nNombre : " + armaduraActiva.getNombre());
            System.out.println("Defensa : " + armaduraActiva.getPunt_Def());
            mostrarModicadores(armaduraActiva.getMod());
        }
    }

    private void modificarArmas(Personaje pers) {
        if (pers.getArmas() != null && pers.getArmas().size() > 0) {
            int opt = 0;
            List<Arma> equipo = new ArrayList<Arma>(pers.getArmas());
            while (opt != 3) {
                System.out.println("\nQue desea hacer?\n1. Equipar Arma\n2. Desequipar Arma\n3. Salir");
                opt = reader.nextInt();
                if (opt == 1) {
                    equiparArma(pers,equipo);
                } else if (opt == 2){
                    desEquiparArma(pers);
                }
            }
        } else {
            System.out.println("No hay armas");
        }
    }

    private void desEquiparArma(Personaje pers) {
        if (pers.armas_Activas != null){
            int opt = 0;
            while (opt != -1) {
                mostrarArmas(pers.getArmas_Activas());
                if (pers.armas_Activas.size() > 1){
                    System.out.println("Elija un arma (1 - 2)\n-1. Salir");
                    opt = reader.nextInt();
                    if (opt == 1 || opt == 2) {
                        pers.quitarArmaActiva(opt-1);
                    }
                }else if (pers.armas_Activas.size() == 1){
                    System.out.println("Solo hay esa arma\n1. Eliminar\n-1.Salir");
                    opt = reader.nextInt();
                    if (opt == 1){
                        pers.armas_Activas.remove(0);
                    }
                }else {
                    System.out.println("No hay armas");
                }
            }
        }else {
            System.out.println("No hay Armas equipadas");
        }
    }

    private void equiparArma(Personaje pers, List<Arma> equipo) {
        int opt = 0;
        while (opt != -1){
            mostrarArmas(equipo);
            System.out.println("Armas equipadas");
            mostrarArmas(pers.getArmas_Activas());
            System.out.println("Elija un arma (0 - " + (equipo.size() - 1) + ")\n-1. Salir");
            opt = reader.nextInt();
            if (opt >= 0 && opt < equipo.size()){
                pers.añadirArmaActiva(equipo.get(opt));
            } else {
                System.out.println("Valor fuera de rango");
            }
        }
    }

    private void mostrarArmas(List<Arma> armas) {
        if (armas != null){
            for (Arma arm : armas) {
                mostrarAtma(arm);
            }
        }else {
        }
    }

    private void mostrarAtma(Arma arm) {
        System.out.println("Nombre : "+arm.getNombre());
        System.out.println("Ataque : "+arm.getPunt_Atk());
        mostrarModicadores(arm.getMod());
    }

    private void verOro(Personaje personajeActivo) {
        System.out.println("\nEl personaje "+personajeActivo.getNombre()+ " tiene "+personajeActivo.getOro()+" de oro");
    }

    //Get-Set

    public List<Arma> getInventarioArmas() {
        return inventarioArmas;
    }

    public void setInventarioArmas(List<Arma> inventarioArmas) {
        this.inventarioArmas = inventarioArmas;
    }

    public List<Armadura> getInventarioArmadura() {
        return inventarioArmadura;
    }

    public void setInventarioArmadura(List<Armadura> inventarioArmadura) {
        this.inventarioArmadura = inventarioArmadura;
    }

    public Personaje getPersJug() {
        return PersJug;
    }

    public void setPersJug(Personaje persJug) {
        PersJug = persJug;
    }

    public Personaje getInventarioMods() {
        return inventarioMods;
    }

    public void setInventarioMods(Personaje inventarioMods) {
        this.inventarioMods = inventarioMods;
    }



}
