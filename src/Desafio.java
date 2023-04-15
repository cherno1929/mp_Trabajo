import java.util.Set;

public class Desafio {

    //Atributo
    public int oro;
    public Personaje j1;
    public Personaje j2;
    public Set<Debilidad> mod_Deb_j1;
    public Set<Debilidad> mod_Deb_j2;
    public Set<Fortaleza> mod_Fort_j1;
    public Set<Fortaleza> mod_Fort_j2;

    //Constructor


    public Desafio(int oro, Personaje j1, Personaje j2, Set<Debilidad> mod_Deb_j1, Set<Debilidad> mod_Deb_j2, Set<Fortaleza> mod_Fort_j1, Set<Fortaleza> mod_Fort_j2) {
        this.oro = oro;
        this.j1 = j1;
        this.j2 = j2;
        this.mod_Deb_j1 = mod_Deb_j1;
        this.mod_Deb_j2 = mod_Deb_j2;
        this.mod_Fort_j1 = mod_Fort_j1;
        this.mod_Fort_j2 = mod_Fort_j2;
    }

    //Get-Set

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public Personaje getJ1() {
        return j1;
    }

    public void setJ1(Personaje j1) {
        this.j1 = j1;
    }

    public Personaje getJ2() {
        return j2;
    }

    public void setJ2(Personaje j2) {
        this.j2 = j2;
    }

    public Set<Debilidad> getMod_Deb_j1() {
        return mod_Deb_j1;
    }

    public void setMod_Deb_j1(Set<Debilidad> mod_Deb_j1) {
        this.mod_Deb_j1 = mod_Deb_j1;
    }

    public Set<Debilidad> getMod_Deb_j2() {
        return mod_Deb_j2;
    }

    public void setMod_Deb_j2(Set<Debilidad> mod_Deb_j2) {
        this.mod_Deb_j2 = mod_Deb_j2;
    }

    public Set<Fortaleza> getMod_Fort_j1() {
        return mod_Fort_j1;
    }

    public void setMod_Fort_j1(Set<Fortaleza> mod_Fort_j1) {
        this.mod_Fort_j1 = mod_Fort_j1;
    }

    public Set<Fortaleza> getMod_Fort_j2() {
        return mod_Fort_j2;
    }

    public void setMod_Fort_j2(Set<Fortaleza> mod_Fort_j2) {
        this.mod_Fort_j2 = mod_Fort_j2;
    }
}
