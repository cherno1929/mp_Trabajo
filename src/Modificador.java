public class Modificador {
    //Atributos
    protected String nombre;
    protected int grado_Efecto;
    protected tipo_mod tipo_mod;

    //Constructor

    public Modificador(String nombre, int grado_Efecto, tipo_mod tipo_mod) {
        this.nombre = nombre;
        this.grado_Efecto = grado_Efecto;
        this.tipo_mod = tipo_mod;
    }

    public Modificador() {

    }

    //Get-Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGrado_Efecto() {
        return grado_Efecto;
    }

    public void setGrado_Efecto(int grado_Efecto) {
        this.grado_Efecto = grado_Efecto;
    }

    public tipo_mod getTipo_mod() {
        return tipo_mod;
    }

    public void setTipo_mod(tipo_mod tipo_mod) {
        this.tipo_mod = tipo_mod;
    }
}
