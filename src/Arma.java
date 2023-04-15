import java.util.Set;

public class Arma extends Equipo{

    //Atributos
    private int punt_Atk;
    private Set<Modificador> mods;
    public int num_Manos;

    //Constructor
    public Arma(String nombre, Modificador mod) {
        super(nombre, mod);
    }

    public Arma(String nombre, Modificador mod, int punt_Atk, Set<Modificador> mods, int num_Manos) {
        super(nombre, mod);
        this.punt_Atk = punt_Atk;
        this.mods = mods;
        this.num_Manos = num_Manos;
    }

    //Get-Set

    public int getPunt_Atk() {
        return punt_Atk;
    }

    public void setPunt_Atk(int punt_Atk) {
        this.punt_Atk = punt_Atk;
    }

    public Set<Modificador> getMods() {
        return mods;
    }

    public void setMods(Set<Modificador> mods) {
        this.mods = mods;
    }

    public int getNum_Manos() {
        return num_Manos;
    }

    public void setNum_Manos(int num_Manos) {
        this.num_Manos = num_Manos;
    }
}
