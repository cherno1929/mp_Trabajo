public class Habilidad_Especial {

    //Atributes
    protected String nombre;
    protected int atk;
    protected  int def;


    //Constructors

    public Habilidad_Especial(String nombre, int atk, int def) {
        this.nombre = nombre;
        this.atk = atk;
        this.def = def;
    }

    //Get-Set


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
