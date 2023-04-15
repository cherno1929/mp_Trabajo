public class Equipo {
    //Atributos
    protected String nombre;
    protected Modificador mod;

    //Constructor

    public Equipo(String nombre, Modificador mod) {
        this.nombre = nombre;
        this.mod = mod;
    }

    //Get-Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Modificador getMod() {
        return mod;
    }

    public void setMod(Modificador mod) {
        this.mod = mod;
    }
}
