import java.util.List;
import java.util.Set;

public class Demonio extends Esbirro{

    //Atributos
    private Set<Esbirro> esbirros;
    private List<Pacto> pactos;


    //Constructor
    public Demonio(String nombre, int salud, Set<Esbirro> esbirros, List<Pacto> pactos) {
        super(nombre, salud);
        this.esbirros = esbirros;
        this.pactos = pactos;
    }

    public Demonio() {

    }

    //Get-Set


    public Set<Esbirro> getEsbirros() {
        return esbirros;
    }

    public void setEsbirros(Set<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public List<Pacto> getPacto() {
        return pactos;
    }

    public void setPacto(List<Pacto> pactos) {
        this.pactos = pactos;
    }
}
