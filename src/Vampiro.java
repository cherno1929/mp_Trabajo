import java.util.List;
import java.util.Set;

public class Vampiro extends Personaje{

    //Atributos
    private int punt_Salud;
    private int edad;
    private List<Disciplina> disciplinas;

    //Metodos
    ////Usar Disciplina


    //Contructor
    public Vampiro(String nombre, Set<Habilidad_Especial> habilidades, Set<Arma> armas, List<Arma> armas_Activas, Set<Armadura> armaduras, Set<Esbirro> esbirros, int oro, int punt_Salud, Set<Modificador> mods, int poder, int escudo) {
        super(nombre, habilidades, armas, armas_Activas, armaduras, esbirros, oro, punt_Salud, mods, poder, escudo);
    }

    //Get-Set

    @Override
    public int getPunt_Salud() {
        return punt_Salud;
    }

    @Override
    public void setPunt_Salud(int punt_Salud) {
        this.punt_Salud = punt_Salud;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}