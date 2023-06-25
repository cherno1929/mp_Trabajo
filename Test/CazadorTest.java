import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CazadorTest {

    Cazador caz;

    @BeforeEach
    void setUp(){
        caz = new Cazador();
    }

    @Test
    void getSetVoluntad() {
        caz.setPunt_Voluntad(0);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 0);

        caz.setPunt_Voluntad(1);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 1);

        caz.setPunt_Voluntad(2);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 2);

        caz.setPunt_Voluntad(3);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 3);

        caz.setPunt_Voluntad(-1);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 3);

        caz.setPunt_Voluntad(4);
        Assertions.assertTrue(caz.getPunt_Voluntad() == 3);
    }

    @Test
    void getSetTalento(){

        Talento tal = new Talento();

        tal.setAtk(2);
        tal.setDef(1);
        tal.setNombre("Lightning lance");

        caz.setTalento(tal);

        Assertions.assertTrue(caz.getTalentos().size() == 1);

    }

}