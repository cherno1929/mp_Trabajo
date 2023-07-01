import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemonioTest {



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