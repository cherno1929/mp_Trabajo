import java.util.Set;

public class Arma extends Equipo{

    //Atributos
    private int punt_Atk;
    public int num_Manos;

    //Constructor

    public Arma(String nombre, Set<Modificador> mod, int punt_Atk, int num_Manos) {
        super(nombre, mod);
        this.punt_Atk = punt_Atk;
        this.num_Manos = num_Manos;
    }

    public Arma() {
        super();
    }

    //Get-Set

    public int getPunt_Atk() {
        return punt_Atk;
    }

    public void setPunt_Atk(int punt_Atk) {
        this.punt_Atk = punt_Atk;
    }

    public int getNum_Manos() {
        return num_Manos;
    }

    public void setNum_Manos(int num_Manos) {
        this.num_Manos = num_Manos;
    }
}
