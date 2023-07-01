import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Persistencia {
    public Usuario j1;
    public Usuario j2;
    public int n_Turnos;
    public Date fecha_Combate;
    public Usuario ganador;
    public Usuario perdedor;
    public List<Esbirro> esbirros_Vivos;
    public int OroGanado;

    //Metodos
    public String getStringEsbirros_Vivos() {
        String dataLine = "";
        if (this.esbirros_Vivos != null) {
            for (Esbirro esb : this.esbirros_Vivos) {
                if (esb != null && esb.getNombre() != null){
                    dataLine += esb.getNombre();
                }
            }
        }
        return dataLine;
    }

    //Getter-Setter
    public Usuario getJ1() {
        return j1;
    }

    public void setJ1(Usuario j1) {
        this.j1 = j1;
    }

    public Usuario getJ2() {
        return j2;
    }

    public void setJ2(Usuario j2) {
        this.j2 = j2;
    }

    public int getN_Turnos() {
        return n_Turnos;
    }

    public void setN_Turnos(int n_Turnos) {
        this.n_Turnos = n_Turnos;
    }

    public String getFecha_Combate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(this.fecha_Combate);
    }

    public void setFecha_Combate(Date fecha_Combate) {
        this.fecha_Combate = fecha_Combate;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    public List<Esbirro> getEsbirros_Vivos() {
        return esbirros_Vivos;
    }

    public void setEsbirros_Vivos(List<Esbirro> esbirros_Vivos) {
        this.esbirros_Vivos = esbirros_Vivos;
    }

    public int getOroGanado() {
        return OroGanado;
    }

    public void setOroGanado(int oroGanado) {
        if (oroGanado >= 0){
            OroGanado = oroGanado;
        }
    }

    public Usuario getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(Usuario perdedor) {
        this.perdedor = perdedor;
    }

    public String getStringFecha() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(this.fecha_Combate);
    }
}
