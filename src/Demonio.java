import java.util.List;
import java.util.Set;

public class Demonio extends Esbirro{

    //Atributos
    private Set<Esbirro> esbirros;
    private List<Pacto> pacto;



    //Constructor
    public Demonio(String nombre, int salud, Set<Esbirro> esbirros, List<Pacto> pacto) {
        super(nombre, salud);
        this.esbirros = esbirros;
        this.pacto = pacto;
    }

    public Demonio(String nombre, int salud) {
        super(nombre, salud);
    }

    //Get-Set


    public Set<Esbirro> getEsbirros() {
        return esbirros;
    }

    public void setEsbirros(Set<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public List<Pacto> getPacto() {
        return pacto;
    }

    public void setPacto(List<Pacto> pacto) {
        this.pacto = pacto;
    }
}
