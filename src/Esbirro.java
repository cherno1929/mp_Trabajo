public class Esbirro extends Personaje{

    //Atributos
    protected String nombre;
    protected int salud;

    //Constructor

    public Esbirro(String nombre, int salud) {
        this.nombre = nombre;
        this.salud = salud;
    }

    public Esbirro() {

    }

    //Get-Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if (salud >= 0 && salud <= 3){
            this.salud = salud;
        }
    }
}
