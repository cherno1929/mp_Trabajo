import java.util.List;
import java.util.Set;

public class Licantropo extends Personaje{

    //Atributos
    private int peso;
    private int altura;
    private int punt_Rabia;
    private List<Don> dones;


    //Metodos
    ////Transformar



    //Constructor
    public Licantropo(String nombre, Set<Habilidad_Especial> habilidades, Set<Arma> armas, List<Arma> armas_Activas, Set<Armadura> armaduras, Set<Esbirro> esbirros, int oro, int punt_Salud, Set<Modificador> mods, int poder, int escudo) {
        super(nombre, habilidades, armas, armas_Activas, armaduras, esbirros, oro, punt_Salud, mods, poder, escudo);
    }

    public Licantropo(String nombre, Set<Habilidad_Especial> habilidades, Set<Arma> armas, List<Arma> armas_Activas, Set<Armadura> armaduras, Set<Esbirro> esbirros, int oro, int punt_Salud, Set<Modificador> mods, int poder, int escudo, int peso, int altura, int punt_Rabia, List<Don> dones) {
        super(nombre, habilidades, armas, armas_Activas, armaduras, esbirros, oro, punt_Salud, mods, poder, escudo);
        this.peso = peso;
        this.altura = altura;
        this.punt_Rabia = punt_Rabia;
        this.dones = dones;
    }

    //Get-Set

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPunt_Rabia() {
        return punt_Rabia;
    }

    public void setPunt_Rabia(int punt_Rabia) {
        this.punt_Rabia = punt_Rabia;
    }

    public List<Don> getDones() {
        return dones;
    }

    public void setDones(List<Don> dones) {
        this.dones = dones;
    }
}
