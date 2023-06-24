import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cazador extends Personaje{

    //Atributo
    private int punt_Voluntad;
    private List<Talento> talentos;

    //Metodos
    ////usar_Talento()

    //Constructor
    public Cazador(String nombre, Set<Habilidad_Especial> habilidades, Set<Arma> armas, List<Arma> armas_Activas, Set<Armadura> armaduras, Set<Esbirro> esbirros, int oro, int punt_Salud, Set<Modificador> mods, int poder, int escudo) {
        super(nombre, habilidades, armas, armas_Activas, armaduras, esbirros, oro, punt_Salud, mods, poder, escudo);
    }

    public int getPunt_Voluntad() {
        return punt_Voluntad;
    }

    public void setPunt_Voluntad(int punt_Voluntad) {
        if (0 <= punt_Voluntad && punt_Voluntad <= 3){
            this.punt_Voluntad = punt_Voluntad;
        }
    }

    public List<Talento> getTalentos() {
        return talentos;
    }

    public void setTalentos(List<Talento> talentos) {
        this.talentos = talentos;
    }

    public void setTalento(Talento talento) {
        if (this.talentos == null) {
            this.talentos = new ArrayList<Talento>();
        }
        this.talentos.add(talento);
    }

    public Cazador() {

    }
}
