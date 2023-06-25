import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EsbirroTest {

    @Test
    void getSetNombre(){
        Esbirro hs = new Esbirro();

        Assertions.assertTrue(null == hs.getNombre());

        hs.setNombre("albaricoque");

        Assertions.assertTrue(hs.getNombre().equals("albaricoque"));
    }

    @Test
    void getSetSalud(){
        Esbirro esb = new Esbirro();

        esb.setSalud(3);
        Assertions.assertTrue(3 == esb.getSalud());

        esb.setSalud(2);
        Assertions.assertTrue(2 == esb.getSalud());

        esb.setSalud(1);
        Assertions.assertTrue(1 == esb.getSalud());

        esb.setSalud(0);
        Assertions.assertTrue(0 == esb.getSalud());

        esb.setSalud(4);
        Assertions.assertTrue(0 == esb.getSalud());

        esb.setSalud(-1);
        Assertions.assertTrue(0 == esb.getSalud());
    }

}