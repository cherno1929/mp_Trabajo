public class Ghoul extends Esbirro{

    //Atributo
    private int niv_Dependencia;

    //Contructor
    public Ghoul(String nombre, int salud) {
        super(nombre, salud);
    }

    public Ghoul(String nombre, int salud, int niv_Dependencia) {
        super(nombre, salud);
        this.niv_Dependencia = niv_Dependencia;
    }

    //Get-Set

    public int getNiv_Dependencia() {
        return niv_Dependencia;
    }

    public void setNiv_Dependencia(int niv_Dependencia) {
        this.niv_Dependencia = niv_Dependencia;
    }
}
