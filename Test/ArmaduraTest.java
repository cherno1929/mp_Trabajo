import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmaduraTest {
    @Test
    void def() {
        Armadura arD = new Armadura();

        arD.setPunt_Def(0);
        Assertions.assertTrue(0 == arD.getPunt_Def());

        arD.setPunt_Def(1);
        Assertions.assertTrue(1 == arD.getPunt_Def());

        arD.setPunt_Def(2);
        Assertions.assertTrue(2 == arD.getPunt_Def());

        arD.setPunt_Def(3);
        Assertions.assertTrue(3 == arD.getPunt_Def());

        arD.setPunt_Def(-1);
        Assertions.assertTrue(3 == arD.getPunt_Def());

        arD.setPunt_Def(4);
        Assertions.assertTrue(3 == arD.getPunt_Def());
    }
}