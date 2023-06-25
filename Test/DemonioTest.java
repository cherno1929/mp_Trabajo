import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemonioTest {

    @Test
    void pacto(){
        Demonio dm = new Demonio();

        Pacto pact1 = constrPact1(dm);
        Pacto pact2 = constrPact2(dm);

        Assertions.assertTrue(dm.getPacto() == null);

        dm.setPacto(pact1);
        dm.setPacto(pact2);

        Assertions.assertTrue(dm.getPacto().size() == 2);
        Assertions.assertTrue(dm.getPacto().contains(pact1));
        Assertions.assertTrue(dm.getPacto().contains(pact2));

        Assertions.assertTrue(dm == dm.getPactoSelected(1).getAmo());
    }

    Personaje constrAmo(){
        Personaje amo = new Personaje();

        amo.setNombre("Amo_01");
        amo.setId("xyz");
        amo.setPunt_Salud(3);
        amo.setOro(20);

        return amo;
    }
    Pacto constrPact1(Demonio dm){
        Pacto pact = new Pacto();

        pact.setAmo(constrAmo());
        pact.setEsbirro(dm);
        pact.setDescripcion("Pacto Amo-Demonio");


        return pact;
    }

    Pacto constrPact2(Demonio dm){
        Pacto pact = constrPact1(dm);
        pact.setAmo(dm);
        return pact;
    }

}