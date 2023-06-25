import java.util.ArrayList;
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

    public Pacto getPactoSelected(int pos) {
        return pactos.get(pos);
    }

    public void setPacto(List<Pacto> pactos) {
        this.pactos = pactos;
    }

    public void setPacto(Pacto pactos) {
        if (this.pactos == null){
            this.pactos = new ArrayList<Pacto>();
        }
        this.pactos.add(pactos);
    }
}
