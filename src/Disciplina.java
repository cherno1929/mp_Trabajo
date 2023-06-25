public class Disciplina extends Habilidad_Especial{

    //Atributos
    private int coste_Sangre;
    private int sangre_Robada = 4;

    //Constructores

    public Disciplina(String nombre, int atk, int def, int coste_Sangre, int sangre_Robada) {
        super(nombre, atk, def);
        this.coste_Sangre = coste_Sangre;
        this.sangre_Robada = sangre_Robada;
    }

    public Disciplina() {

    }


    public int getCoste_Sangre() {
        return coste_Sangre;
    }

    public void setCoste_Sangre(int coste_Sangre) {
        if (coste_Sangre >= 1 && coste_Sangre <= 3) {
            this.coste_Sangre = coste_Sangre;
        }
    }

    public int getSangre_Robada() {
        return sangre_Robada;
    }

    public void setSangre_Robada(int sangre_Robada) {
        this.sangre_Robada = 4;
    }
}
