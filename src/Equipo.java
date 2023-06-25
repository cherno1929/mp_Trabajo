import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Equipo {
    //Atributos
    protected String nombre;
    protected Set<Modificador> mod;

    //Constructor

    public Equipo(String nombre, Set<Modificador> mod) {
        this.nombre = nombre;
        this.mod = mod;
    }

    public Equipo() {

    }

    //Get-Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Modificador> getMod() {
        return mod;
    }

    public void setMod(Set<Modificador> mod) {
        this.mod = mod;
    }

    public void quitMod(Modificador mod){
        if (this.mod != null && this.mod.contains(mod)) {
            this.mod.remove(mod);
        }
    }

    public void setMod(Modificador mod) {
        if (this.mod == null) {
            this.mod = new HashSet<Modificador>();
        }
        this.mod.add(mod);
    }

}
