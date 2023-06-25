import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Habilidad_EspecialTest {

    @Test
    void getSetNombre(){
        Habilidad_Especial hs = new Habilidad_Especial();

        Assertions.assertTrue(null == hs.getNombre());

        hs.setNombre("Pirotecnia");

        Assertions.assertTrue(hs.getNombre().equals("Pirotecnia"));
    }

    @Test
    void getSetAtk() {
        Habilidad_Especial hs = new Habilidad_Especial();

        Assertions.assertTrue(hs.getAtk() == 0);

        hs.setAtk(5);

        Assertions.assertTrue(5 == hs.getAtk());
    }

    @Test
    void getSetDef() {
        Habilidad_Especial hs = new Habilidad_Especial();

        Assertions.assertTrue(hs.getDef() == 0);

        hs.setDef(5);

        Assertions.assertTrue(5 == hs.getDef());
    }

}