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
}
