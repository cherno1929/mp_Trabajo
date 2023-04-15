import java.util.Set;

public class Armadura extends Equipo{
    //Atributos
    private int punt_Def;
    private Set<Modificador> mods;

    //Constructor
    public Armadura(String nombre, Modificador mod) {
        super(nombre, mod);
    }

    public Armadura(String nombre, Modificador mod, int punt_Def, Set<Modificador> mods) {
        super(nombre, mod);
        this.punt_Def = punt_Def;
        this.mods = mods;
    }

    //Get-Set

    public int getPunt_Def() {
        return punt_Def;
    }

    public void setPunt_Def(int punt_Def) {
        this.punt_Def = punt_Def;
    }

    public Set<Modificador> getMods() {
        return mods;
    }

    public void setMods(Set<Modificador> mods) {
        this.mods = mods;
    }
}
