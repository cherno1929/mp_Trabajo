import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PactoTest {

    @Test
    void amo(){

        Pacto pact = new Pacto();

        pact.setAmo(constrAmo());

        Assertions.assertTrue(pact.getAmo().getNombre().equals("Amo_01"));

        Personaje amo2 = constrAmo();
        amo2.setNombre("Gerald");

        Assertions.assertFalse(pact.getAmo().getNombre().equals(amo2.getNombre()));
    }



    Personaje constrAmo(){
        Personaje amo = new Personaje();

        amo.setNombre("Amo_01");
        amo.setId("xyz");
        amo.setPunt_Salud(3);
        amo.setOro(20);

        return amo;
    }

    @Test
    void descr(){
        Pacto pact = new Pacto();

        Assertions.assertTrue(pact.getDescripcion() == null);

        pact.setDescripcion("Este pacto propone que el amo tiene control sobre el esbirro");

        Assertions.assertTrue(pact.getDescripcion().equals("Este pacto propone que el amo tiene control sobre el esbirro"));
    }

    Esbirro constrEsbr() {
        Esbirro esb = new Esbirro();

        esb.setSalud(2);
        esb.setNombre("Fawir");

        return esb;
    }

    @Test
    void esbirro() {

        Pacto pct = new Pacto();

        Esbirro esb = constrEsbr();

        pct.setEsbirro(esb);

        Assertions.assertTrue(pct.getEsbirro().equals(esb));

        Esbirro esb2 = constrEsbr();
        esb2.setSalud(0);

        Assertions.assertFalse(pct.getEsbirro().equals(esb2));


    }


}


