import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LicantropoTest {

    Licantropo lin;

    @BeforeEach
    void setUp(){
        lin = new Licantropo();
    }

    @Test
    void getSetRabia() {
        lin.setPunt_Rabia(1);
        Assertions.assertTrue(1 == lin.getPunt_Rabia());

        lin.setPunt_Rabia(2);
        Assertions.assertTrue(2 == lin.getPunt_Rabia());

        lin.setPunt_Rabia(3);
        Assertions.assertTrue(3 == lin.getPunt_Rabia());

        lin.setPunt_Rabia(0);
        Assertions.assertTrue(0 == lin.getPunt_Rabia());

        lin.setPunt_Rabia(-1);
        Assertions.assertTrue(0 == lin.getPunt_Rabia());

        lin.setPunt_Rabia(4);
        Assertions.assertTrue(0 == lin.getPunt_Rabia());
    }

    @Test
    void getSetDones() {
        Don don = new Don();

        don.setUmbral(1);
        don.setAtk(3);
        don.setDef(1);
        don.setRabia_Otorgada(1);
        don.setNombre("Rompe craneos");

        lin.setDone(don);

        Assertions.assertTrue(lin.getDones().size() == 1);
    }

    void getSetOther() {
        lin.setPeso(100);
        lin.setAltura(180);

        Assertions.assertTrue(100 == lin.getPeso());
        Assertions.assertTrue(180 == lin.getAltura());
    }

}