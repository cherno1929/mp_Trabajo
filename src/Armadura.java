import java.util.Set;

public class Armadura extends Equipo{
    //Atributos
    private int punt_Def;

    //Constructor

    public Armadura(String nombre, Set<Modificador> mod, int punt_Def) {
        super(nombre, mod);
        this.punt_Def = punt_Def;
    }

    public Armadura() {

    }

    //Get-Set

    public int getPunt_Def() {
        return punt_Def;
    }

    public void setPunt_Def(int punt_Def) {
        if (punt_Def >= 0 && punt_Def < 4) {
            this.punt_Def = punt_Def;
        }
    }
}
