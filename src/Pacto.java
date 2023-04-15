public class Pacto {
    //Atributo
    private Personaje amo;
    private Esbirro esbirro;
    public String descripcion;

    //Constructor


    public Pacto(Personaje amo, Esbirro esbirro, String descripcion) {
        this.amo = amo;
        this.esbirro = esbirro;
        this.descripcion = descripcion;
    }

    //Get-Set

    public Personaje getAmo() {
        return amo;
    }

    public void setAmo(Personaje amo) {
        this.amo = amo;
    }

    public Esbirro getEsbirro() {
        return esbirro;
    }

    public void setEsbirro(Esbirro esbirro) {
        this.esbirro = esbirro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
