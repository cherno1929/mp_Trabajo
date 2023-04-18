import java.util.Set;

public class Desafio {

    //Atributo
    public int oro;
    public Usuario j1;
    public Usuario j2;
    public Set<Modificador> mod_Deb_j1;
    public Set<Modificador> mod_Deb_j2;
    public Set<Modificador> mod_Fort_j1;
    public Set<Modificador> mod_Fort_j2;

    //Constructor


    public Desafio(int oro, Usuario j1, Usuario j2, Set<Modificador> mod_Deb_j1, Set<Modificador> mod_Deb_j2, Set<Modificador> mod_Fort_j1, Set<Modificador> mod_Fort_j2) {
        this.oro = oro;
        this.j1 = j1;
        this.j2 = j2;
        this.mod_Deb_j1 = mod_Deb_j1;
        this.mod_Deb_j2 = mod_Deb_j2;
        this.mod_Fort_j1 = mod_Fort_j1;
        this.mod_Fort_j2 = mod_Fort_j2;
    }

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

    public Set<Modificador> getMod_Deb_j1() {
        return mod_Deb_j1;
    }

    public void setMod_Deb_j1(Set<Modificador> mod_Deb_j1) {
        this.mod_Deb_j1 = mod_Deb_j1;
    }

    public Set<Modificador> getMod_Deb_j2() {
        return mod_Deb_j2;
    }

    public void setMod_Deb_j2(Set<Modificador> mod_Deb_j2) {
        this.mod_Deb_j2 = mod_Deb_j2;
    }

    public Set<Modificador> getMod_Fort_j1() {
        return mod_Fort_j1;
    }

    public void setMod_Fort_j1(Set<Modificador> mod_Fort_j1) {
        this.mod_Fort_j1 = mod_Fort_j1;
    }

    public Set<Modificador> getMod_Fort_j2() {
        return mod_Fort_j2;
    }

    public void setMod_Fort_j2(Set<Modificador> mod_Fort_j2) {
        this.mod_Fort_j2 = mod_Fort_j2;
    }
}
