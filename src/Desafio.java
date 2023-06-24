import java.util.Set;

public class Desafio {

    //Atributo
    public int oro;
    public Usuario j1;
    public Usuario j2;
    public Set<Modificador> mod_j1;
    public Set<Modificador> mod_j2;
    public boolean validado;

    public Desafio() {

    }

    //Get-Set

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

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

    public Set<Modificador> getMod_j1() {
        return mod_j1;
    }

    public void setMod_j1(Set<Modificador> mod_j1) {
        this.mod_j1 = mod_j1;
    }

    public Set<Modificador> getMod_j2() {
        return mod_j2;
    }

    public void setMod_j2(Set<Modificador> mod_j2) {
        this.mod_j2 = mod_j2;
    }

    public boolean getValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getId(){
        return this.getJ1().getNum_Registro()+"-"+this.getJ2().getNum_Registro();
    }
}
