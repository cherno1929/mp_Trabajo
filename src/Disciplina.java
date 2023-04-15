public class Disciplina extends Habilidad_Especial{

    //Atributos
    private int coste_Sangre;
    private int sangre_Robada;

    //Contructores

    public Disciplina(String nombre, int atk, int def) {
        super(nombre, atk, def);
    }


    public int getCoste_Sangre() {
        return coste_Sangre;
    }

    public void setCoste_Sangre(int coste_Sangre) {
        this.coste_Sangre = coste_Sangre;
    }

    public int getSangre_Robada() {
        return sangre_Robada;
    }

    public void setSangre_Robada(int sangre_Robada) {
        this.sangre_Robada = sangre_Robada;
    }
}
