import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinaTest {

    @Test
    void getSetCosteSangre() {
        Disciplina disp = new Disciplina();

        disp.setCoste_Sangre(0);
        Assertions.assertTrue(0 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(1);
        Assertions.assertTrue(1 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(2);
        Assertions.assertTrue(2 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(3);
        Assertions.assertTrue(3 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(-1);
        Assertions.assertTrue(3 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(4);
        Assertions.assertTrue(3 == disp.getCoste_Sangre());

        disp.setCoste_Sangre(0);
        Assertions.assertTrue(3 == disp.getCoste_Sangre());
    }

    @Test
    void getSetSangreRobada() {
        Disciplina disp = new Disciplina();

        Assertions.assertTrue(4 == disp.getSangre_Robada());

        disp.setSangre_Robada(10);

        Assertions.assertTrue(4 == disp.getSangre_Robada());
    }

}