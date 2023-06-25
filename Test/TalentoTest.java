import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TalentoTest {

    @Test
    void getSetNombre(){
        Talento hs = new Talento();

        Assertions.assertTrue(null == hs.getNombre());

        hs.setNombre("Pirotecnia");

        Assertions.assertTrue(hs.getNombre().equals("Pirotecnia"));
    }

    @Test
    void getSetAtk() {
        Talento hs = new Talento();

        Assertions.assertTrue(hs.getAtk() == 0);

        hs.setAtk(5);

        Assertions.assertTrue(5 == hs.getAtk());
    }

    @Test
    void getSetDef() {
        Talento hs = new Talento();

        Assertions.assertTrue(hs.getDef() == 0);

        hs.setDef(5);

        Assertions.assertTrue(5 == hs.getDef());
    }

}