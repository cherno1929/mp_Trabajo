import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DemonioTest {

    @Test
    void getSetPacto() {
        Demonio demonio_Test1 = new Demonio();
        Assertions.assertTrue(demonio_Test1.getPacto() == null);

        demonio_Test1.setPacto(constrPact1(demonio_Test1));
        Assertions.assertTrue(demonio_Test1.getPacto() != null && demonio_Test1.getPacto().size() == 1);

        demonio_Test1.setPacto(constrPactos(demonio_Test1));
        Assertions.assertTrue(demonio_Test1.getPacto() != null && demonio_Test1.getPacto().size() == 3);

        Pacto pact_Test = null;
        demonio_Test1.setPacto(pact_Test);
        Assertions.assertTrue(demonio_Test1.getPacto() != null && demonio_Test1.getPacto().size() == 3);

        List<Pacto> pactos_Test = new ArrayList<>();
        demonio_Test1.setPacto(pactos_Test);
        Assertions.assertTrue(demonio_Test1.getPacto() != null && demonio_Test1.getPacto().size() == 0);
    }

    List<Pacto> constrPactos(Demonio dm) {
        List<Pacto> pact = new ArrayList<>();

        for (int i = 0; i< 3; i++){
            pact.add(constrPact1(dm));
        }

        return pact;
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



}