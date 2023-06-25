import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmaTest {

    @Test
    void n_Manos(){
        Arma ar = new Arma();

        ar.setNum_Manos(0);
        Assertions.assertTrue(0 == ar.getNum_Manos());

        ar.setNum_Manos(1);
        Assertions.assertTrue(1 == ar.getNum_Manos());

        ar.setNum_Manos(2);
        Assertions.assertTrue(2 == ar.getNum_Manos());

        ar.setNum_Manos(-1);
        Assertions.assertTrue(2 == ar.getNum_Manos());

        ar.setNum_Manos(3);
        Assertions.assertTrue(2 == ar.getNum_Manos());

    }

    @Test
    void atk() {

        Arma ar = new Arma();

        ar.setPunt_Atk(0);
        Assertions.assertTrue(0 == ar.getPunt_Atk());

        ar.setPunt_Atk(1);
        Assertions.assertTrue(1 == ar.getPunt_Atk());

        ar.setPunt_Atk(2);
        Assertions.assertTrue(2 == ar.getPunt_Atk());

        ar.setPunt_Atk(3);
        Assertions.assertTrue(3 == ar.getPunt_Atk());

        ar.setPunt_Atk(-1);
        Assertions.assertTrue(3 == ar.getPunt_Atk());

        ar.setPunt_Atk(4);
        Assertions.assertTrue(3 == ar.getPunt_Atk());

    }

}