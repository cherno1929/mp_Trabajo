import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class App_Inventario {
    //Atributos
    private List<Arma> inventarioArmas;
    private List<Armadura> inventarioArmadura;
    private  Personaje PersJug;
    private  Personaje inventarioMods;

    //Metodos


    //Contructor
    public App_Inventario(List<Arma> inventarioArmas, List<Armadura> inventarioArmadura, Personaje persJug, Personaje inventarioMods) {
        this.inventarioArmas = inventarioArmas;
        this.inventarioArmadura = inventarioArmadura;
        PersJug = persJug;
        this.inventarioMods = inventarioMods;
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
