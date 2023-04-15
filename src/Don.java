public class Don extends Habilidad_Especial{

    //Atributos
    private int umbral;

    //Constructor

    public Don(String nombre, int atk, int def, int umbral) {
        super(nombre, atk, def);
        this.umbral = umbral;
    }

    //Get-Set

    public int getUmbral() {
        return umbral;
    }

    public void setUmbral(int umbral) {
        this.umbral = umbral;
    }
}
