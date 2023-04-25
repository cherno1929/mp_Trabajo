import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void modificarPers(Usuario j1, Rol operador) {
        mostrarPersonaje(j1,operador);
    }

    private void mostrarPersonaje(Usuario j1, Rol operador) {
        if (j1 != null || j1.getPersonajeActivo() != null){
            int opt = 0;
            while (opt != 10){
                System.out.println("\nDueño : " + j1.getNombre());
                j1.getPersonajeActivo().mostrarPersonaje();
                System.out.println("\nQue desea hacer?\n1. Armas\n2. Armaduras\n3. Ver Oro\n4. Cambiar Nombre\n5. Cambiar Habilidades\n6. Cambiar Salud\n7. Cambiar Esbirros\n8. Cambiar Poder\n9. Cambiar Escudo\n10. Salir\n11. Modificadores");
                opt = this.reader.nextInt();
                switch (opt){
                case 1 :
                    this.modificarArmas(j1.getPersonajeActivo());
                    break;
                case 2 :
                    this.modificarArmadura(j1.getPersonajeActivo()); // El personaje solo puede equiparse una armadura
                    break;
                case 3 :
                    this.verOro(j1.getPersonajeActivo());
                    break;
                case 4 :
                    this.cambiarNombrePers(j1.getPersonajeActivo());
                    break;
                case 5:
                    this.modificarHabilidades(j1.getPersonajeActivo());
                    break;
                case 6:
                    this.cambiarSaludPers(j1.getPersonajeActivo());
                    break;
                case 7:
                    this.modificarEsbirros(j1.getPersonajeActivo());
                    break;
                case 8:
                    this.cambiarPoder(j1.getPersonajeActivo());
                    break;
                case 9:
                    this.cambiarEscudo(j1.getPersonajeActivo());
                    break;
                case 11:
                    if (operador == Rol.operador){
                        j1.personajeActivo.setMods(modificarMods(j1.getPersonajeActivo().getMods()));
                    }

                default:
                    System.out.println("Opcion no valida");

                }

                }

            }
    }

    private void modificarEsbirros(Personaje pers) {
        List<Esbirro> esbirrosDisponibles = new ArrayList<Esbirro>(this.fileContrl.getAllEsbirro());
        List<Esbirro> esbirrosPers = new ArrayList<Esbirro>(pers.getEsbirros());
        int op = 0;
        while (op != 3){
            System.out.println("Tus esbirros :: ");
            mostrarEsbirros(esbirrosDisponibles);
            System.out.println("Tus Esbirros\n1. Añadir\n2. Quitar\n3. Salir");
            op = reader.nextInt();
            if (op == 1) {
                añadirEsbirro(pers.getEsbirros(),esbirrosDisponibles);
            } else if (op == 2) {
                eliminarEsbirro(esbirrosPers);
            }
        }
    }

    private void añadirEsbirro(Set<Esbirro> esbirrosPers, List<Esbirro> esbirrosDisponibles) {
        if (esbirrosDisponibles != null) {
            int opt = 0;
            while (opt != -1) {
                System.out.println("Esbirros dism¡pinibles");
                mostrarEsbirros(esbirrosDisponibles);
                System.out.println("Elija un esbirro (0-" + (esbirrosDisponibles.size()-1) + ")\n-1. Salir");
                opt = reader.nextInt();
                if (opt >= 0 && opt < esbirrosDisponibles.size()) {
                    esbirrosPers.add(esbirrosDisponibles.get(opt));
                }else {
                    System.out.println("Valor fuera de rango");
                }
            }
        } else {
            System.out.println("No hay esbirros \n._.");
        }
    }

    private void eliminarEsbirro(List<Esbirro> esbirrosPers) {
        if (esbirrosPers == null || esbirrosPers.size() == 0) {
            System.out.println("\nNo hay esbirros");
        }else {
            int opt = 0;
            while (opt != 3 && esbirrosPers.size() > 0 && esbirrosPers != null) {
                mostrarEsbirros(esbirrosPers);
                System.out.println("Elije us esbirro (0-" + (esbirrosPers.size()-1) + ")");
                opt = reader.nextInt();
                if (opt >= 0 && opt < esbirrosPers.size() ) {
                    esbirrosPers.remove(opt);
                }
            }
        }
    }

    private void mostrarEsbirros(List<Esbirro> esbirros) {
        if (esbirros != null) {
            for (Esbirro esb : esbirros) {
                mostrarEsbirro(esb);
            }
        }else {
            System.out.println("\nNo tienes esbirros");
        }
        System.out.println();
    }

    private void mostrarEsbirro(Esbirro esb) {
        System.out.println("\nNombre : " + esb.getNombre());
        System.out.println("Salud : " + esb.getSalud());
        System.out.println("Raza : " + String.valueOf(esb.getClass()));
    }

    private void modificarHabilidades(Personaje pers) {
        List<Habilidad_Especial> specSkill = new ArrayList<Habilidad_Especial>(this.fileContrl.buscarHabilidades(pers.getClass()));
        List<Habilidad_Especial> persSkill = new ArrayList<Habilidad_Especial>(pers.getHabilidades());
        int optAQ = 0;
        while (optAQ != 3 && persSkill != null && persSkill.size() > 0){
            System.out.println("Habilidades de tu personaje ::");
            mostrarHabilidades(persSkill);
            System.out.println("Habilidades Disponibles");
            mostrarHabilidades(specSkill);
            if (persSkill != null && persSkill.size() > 0){
                System.out.println("\nQue deseas hacer?\n1. Añadir\n2. Quitar\n3. Salir");
                optAQ = reader.nextInt();
                if (optAQ == 1) {
                    this.añadirHabilidad(persSkill,specSkill);
                }else if (optAQ == 2){
                    this.quitarHabilidad(pers.getHabilidades(),persSkill);
                }
            } else{
                System.out.println("El personaje no tiene habilidades\n1. Añadir\n3. Salir");
                if (optAQ == 1) {

                }
            }
        }
        pers.setHabilidades(new HashSet<Habilidad_Especial>(persSkill));
    }

    private void quitarHabilidad(Set<Habilidad_Especial> habPers, List<Habilidad_Especial> persSkill) {
        int opt = 0;
        if (habPers != null) {
            {
                while (opt != -1 && persSkill.size() > 0 && persSkill != null){
                    int i = 0;
                    for (Habilidad_Especial hab : persSkill) {
                        System.out.println("\nNº Hab " + i);
                        mostrarHabilidad(hab);
                        i++;
                    }
                    System.out.println("Elija una habilidad (0-" + (persSkill.size() - 1) + ")\n-1.Salir");
                    opt = reader.nextInt();
                    if (opt >= 0 && opt < persSkill.size()) {
                        habPers.remove(persSkill.get(opt));
                        persSkill.remove(opt);
                    }
                }
            }
        }else {
            System.out.println("No hay hablilidades especiales");
        }
    }

    private void añadirHabilidad(List<Habilidad_Especial> persSkill, List<Habilidad_Especial> specSkill) {
        int opt = 0;
        while (opt != -1){
            System.out.println("Estas son las habilidades de tu persoje");
            mostrarHabilidades(persSkill);
            System.out.println("Estas son las habilidades disponbibles");
            mostrarHabilidades(specSkill);
            System.out.println("Selecciona una habilidad porfavor (0-"+(specSkill.size()-1)+")\n-1. Salir");
            opt = reader.nextInt();
            if (specSkill != null && opt >= 0 && opt < specSkill.size()) {
                if (!persSkill.contains(specSkill.get(opt))){
                    persSkill.add(specSkill.get(opt));
                }else {
                    System.out.println("Ya tienes esta habilidad");
                }
            }
        }
    }

    private void mostrarHabilidades(List<Habilidad_Especial> persSkill) {
        if (persSkill != null) {
            for (Habilidad_Especial hab : persSkill) {
                mostrarHabilidad(hab);
            }
        }
    }

    private void mostrarHabilidad(Habilidad_Especial hab) {
        if (hab != null) {
            System.out.println("\nNombre : "+hab.getNombre());
            System.out.println("Ataque : "+hab.getAtk());
            System.out.println("Defensa : "+hab.getDef());
            String tipo = "Tipo : "+hab.getClass();
            System.out.println(tipo.substring(6,tipo.length()-1));
            System.out.println();
        }
    }

    private void cambiarEscudo(Personaje personajeActivo) {
        System.out.println("Cuanto escudo desea?");
        int newInt = reader.nextInt();
        personajeActivo.setEscudo(newInt);
    }

    private void cambiarPoder(Personaje personajeActivo) {
        System.out.println("Cuanto poder desea?");
        int newInt = reader.nextInt();
        personajeActivo.setPoder(newInt);
    }

    private void cambiarSaludPers(Personaje personajeActivo) {
        System.out.println("Cuanta salud desea?");
        int newInt = reader.nextInt();
        personajeActivo.setPunt_Salud(newInt);
    }

    private void cambiarNombrePers(Personaje personajeActivo) {
        System.out.println("Que nombre desea poner?");
        BufferedReader readLn = new BufferedReader(new InputStreamReader(System.in));
        try {
            String newName = readLn.readLine();
            personajeActivo.setNombre(newName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
