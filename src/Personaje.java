import java.util.List;
import java.util.Set;

public class Personaje {

    //Atributos
    protected String nombre;
    protected Set<Habilidad_Especial> habilidades;
    protected Set<Arma> armas;
    protected List<Arma> armas_Activas;
    protected Set<Armadura> armaduras;
    protected Set<Esbirro> esbirros;
    protected int oro;
    protected int punt_Salud;
    protected Set<Modificador> mods;
    protected int poder;
    protected int escudo;


    //Constructor


    public Personaje(String nombre, Set<Habilidad_Especial> habilidades, Set<Arma> armas, List<Arma> armas_Activas, Set<Armadura> armaduras, Set<Esbirro> esbirros, int oro, int punt_Salud, Set<Modificador> mods, int poder, int escudo) {
        this.nombre = nombre;
        this.habilidades = habilidades;
        this.armas = armas;
        this.armas_Activas = armas_Activas;
        this.armaduras = armaduras;
        this.esbirros = esbirros;
        this.oro = oro;
        this.punt_Salud = punt_Salud;
        this.mods = mods;
        this.poder = poder;
        this.escudo = escudo;
    }

    //Get-Set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Habilidad_Especial> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidad_Especial> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<Arma> getArmas() {
        return armas;
    }

    public void setArmas(Set<Arma> armas) {
        this.armas = armas;
    }

    public List<Arma> getArmas_Activas() {
        return armas_Activas;
    }

    public void setArmas_Activas(List<Arma> armas_Activas) {
        this.armas_Activas = armas_Activas;
    }

    public Set<Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(Set<Armadura> armaduras) {
        this.armaduras = armaduras;
    }

    public Set<Esbirro> getEsbirros() {
        return esbirros;
    }

    public void setEsbirros(Set<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getPunt_Salud() {
        return punt_Salud;
    }

    public void setPunt_Salud(int punt_Salud) {
        this.punt_Salud = punt_Salud;
    }

    public Set<Modificador> getMods() {
        return mods;
    }

    public void setMods(Set<Modificador> mods) {
        this.mods = mods;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public int findPwr() { // Estadística que se muestra en la pestaña de personaje. No confundir con valor de ataque.
        int aux_pwr = 0;
        if(!this.armas_Activas.isEmpty()) {
            for (Arma armaArck : this.armas_Activas) {
                aux_pwr += armaArck.getPunt_Atk();
            }
        }
        return (poder + aux_pwr);
    }

    public int findShd() { // Estadística que se muestra en la pestaña de personaje. No confundir con valor de defensa.
        int aux_def = 0;
        if (!this.esbirros.isEmpty()){
            for(Esbirro esbHP : this.esbirros){
                aux_def += esbHP.getSalud();
            }
        }
        return (escudo + aux_def);
    }
}
