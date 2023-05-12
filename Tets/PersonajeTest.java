import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeTest {

    Personaje persj_Test;
    FileController fc_Test;

    @BeforeEach
    void setUp(){
        fc_Test = new FileController();
        persj_Test = fc_Test.getPersonaje("Test_01");
    }

    @Test
    void calcPwr_NoWeapon() {

        Assertions.assertEquals(0,persj_Test.calcPwr());
    }

    @Test
    void calcPwr_withWeapon() {
        String[] arma = {"Mandoble"};
        persj_Test.setArmas_Activas(new ArrayList<Arma>(fc_Test.buscarArmas(arma)));
        Assertions.assertEquals(6,persj_Test.calcPwr());
    }

    @Test
    void calcShd_NoEsb_NoEsc() {

        Assertions.assertEquals(0,persj_Test.calcShd());
    }

    @Test
    void calcShd_NoEsb() {
        String[] armadura = {"Armadura_de_cuero"};
        List<Armadura> armad= new ArrayList<Armadura>(fc_Test.buscarArmaduras(armadura));
        persj_Test.setArmadura_Activa(armad.get(0)); // Solo hay un único elemento
        Assertions.assertEquals(0,persj_Test.calcShd());
    }

    @Test
    void calcShd() {
        Humano esbirro_Test = new Humano();
        esbirro_Test.nombre = "human_Test";
        esbirro_Test.salud = 3;
        esbirro_Test.setLealtad(Niv_Lealtad.medio);
        Set<Esbirro> esbirros_Test = new HashSet<Esbirro>();
        esbirros_Test.add(esbirro_Test);
        persj_Test.setEsbirros(esbirros_Test);
        Assertions.assertEquals(esbirro_Test.getSalud(),persj_Test.calcShd());
    }

    @Test
    void calcEsb_NoEsb() {
        Assertions.assertEquals(true,persj_Test.calcEsb().isEmpty());
    }

    @Test
    void hasFainted_HP_0() {
        persj_Test.setPunt_Salud(0);
        Assertions.assertEquals(true,persj_Test.hasFainted());
    }

    @Test
    void hasFainted_HP_Superor0() {
        persj_Test.setPunt_Salud(1);
        Assertions.assertEquals(false,persj_Test.hasFainted());
    }

    @Test
    void hasFainted_HP_Menor0() {
        persj_Test.setPunt_Salud(-1);
        Assertions.assertEquals(true,persj_Test.hasFainted());
    }

}