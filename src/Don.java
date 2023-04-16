public class Don extends Habilidad_Especial{

    //Atributos
    private int umbral;

    private int rabia_Otorgada;
    //Constructor

    public Don(String nombre, int atk, int def, int umbral, int rabia_Otorgada) {
        super(nombre, atk, def);
        this.umbral = umbral;
        this.rabia_Otorgada = rabia_Otorgada;
    }

    public Don() {

    }

    //Get-Set

    public int getUmbral() {
        return umbral;
    }

    public void setUmbral(int umbral) {
        this.umbral = umbral;
    }

    public int getRabia_Otorgada() {
        return rabia_Otorgada;
    }

    public void setRabia_Otorgada(int rabia_Otorgada) {
        this.rabia_Otorgada = rabia_Otorgada;
    }
}
