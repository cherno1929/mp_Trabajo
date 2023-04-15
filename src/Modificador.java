public class Modificador {
    //Atributos
    protected String nombre;
    protected int grado_Efecto;

    //Contructor

    public Modificador(String nombre, int grado_Efecto) {
        this.nombre = nombre;
        this.grado_Efecto = grado_Efecto;
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
}
