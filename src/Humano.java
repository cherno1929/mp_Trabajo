public class Humano extends Esbirro{

    //Atributos
    private Niv_Lealtad lealtad;


    //Contructor
    public Humano(String nombre, int salud) {
        super(nombre, salud);
    }

    public Humano(String nombre, int salud, Niv_Lealtad lealtad) {
        super(nombre, salud);
        this.lealtad = lealtad;
    }

    //Get_Set

    public Niv_Lealtad getLealtad() {
        return lealtad;
    }

    public void setLealtad(Niv_Lealtad lealtad) {
        this.lealtad = lealtad;
    }
}
