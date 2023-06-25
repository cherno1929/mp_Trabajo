import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanoTest {

    @Test
    void getSetNivLealtad(){
        Humano hm = new Humano();

        Assertions.assertTrue(null == hm.getLealtad());

        hm.setLealtad(Niv_Lealtad.bajo);
        Assertions.assertTrue(Niv_Lealtad.bajo == hm.getLealtad());

        hm.setLealtad(Niv_Lealtad.medio);
        Assertions.assertTrue(Niv_Lealtad.medio == hm.getLealtad());

        hm.setLealtad(Niv_Lealtad.alto);
        Assertions.assertTrue(Niv_Lealtad.alto == hm.getLealtad());
    }

}