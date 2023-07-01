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
    void getEsbirros() {
        Assertions.assertTrue(0 == persj_Test.getEsbirros().size());

        Esbirro esb_Test01 = new Esbirro();
        esb_Test01.setNombre("test01");

        Set<Esbirro> esbirrosTest = new HashSet<>();
        esbirrosTest.add(esb_Test01);

        persj_Test.setEsbirros(esbirrosTest);

        Assertions.assertTrue(0 < persj_Test.getEsbirros().size());
        Assertions.assertTrue(persj_Test.getEsbirros().contains(esb_Test01));
    }

    @Test
    void calcPwr() {
        Assertions.assertEquals(0,persj_Test.calcPwr());
        String[] arma = {"Mandoble"};
        persj_Test.setArmas_Activas(new ArrayList<Arma>(fc_Test.buscarArmas(arma)));
        Assertions.assertEquals(2,persj_Test.calcPwr());
    }


    @Test
    void calcShd() {
        Assertions.assertEquals(0,persj_Test.calcShd());
        String[] armadura = {"Armadura_de_cuero"};
        List<Armadura> armad= new ArrayList<Armadura>(fc_Test.buscarArmaduras(armadura));
        persj_Test.setArmadura_Activa(armad.get(0)); // Solo hay un único elemento
        Assertions.assertEquals(0,persj_Test.calcShd());
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
    void calcEsb() {
        Assertions.assertEquals(true,persj_Test.calcEsb().isEmpty());
        persj_Test.setEsbirros(createEsbirros());
        Assertions.assertFalse(persj_Test.calcEsb().isEmpty());
        Assertions.assertTrue(persj_Test.calcEsb().size() == 3);
        persj_Test.setEsbirros(createHalfDeadEsbirros());
        Assertions.assertTrue(persj_Test.getEsbirros().size() == 6);
        Assertions.assertTrue(persj_Test.calcEsb().size() == 3);
    }

    Set<Esbirro> createHalfDeadEsbirros() {
        Set<Esbirro> esbirros = createEsbirros();
        for (Esbirro esb : esbirros){
            esb.setSalud(0);
        }
        esbirros.addAll(createEsbirros());
        return esbirros;
    }

    Set<Esbirro> createEsbirros() {
        Set<Esbirro> esbirros = new HashSet<Esbirro>();
        for (int i = 0; i < 3; i++) {
            Esbirro esb = new Esbirro();
            esb.setSalud(1);
            esb.setNombre("EsbirroTest");
            esbirros.add(esb);
        }
        return esbirros;
    }

    @Test
    void quitarArmaActiva() {
        Personaje personaje_Test = new Personaje();
        Assertions.assertTrue(personaje_Test.getArmas_Activas() == null);

        Arma armaTest = new Arma();
        armaTest.setNombre("MataSanos");
        armaTest.setPunt_Atk(2);
        armaTest.setNum_Manos(1);
        List<Arma> armas = new ArrayList<>();
        armas.add(armaTest);

        personaje_Test.setArmas_Activas(armas);
        Assertions.assertTrue(personaje_Test.getArmas_Activas() != null);
        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 1);

        personaje_Test.quitarArmaActiva(0);
        Assertions.assertTrue(personaje_Test.getArmas_Activas() != null);
        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 0);

        Arma armaTest2 = new Arma();
        armaTest.setNombre("DestrozaArmaduras");
        armaTest.setPunt_Atk(3);
        armaTest.setNum_Manos(2);

        armas.add(armaTest);
        armas.add(armaTest2);

        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 2);

        personaje_Test.quitarArmaActiva(1);
        personaje_Test.quitarArmaActiva(0);

        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 0);
    }

    @Test
    void añadirArmaActiva() {
        Personaje personaje_Test = new Personaje();

        Assertions.assertTrue(personaje_Test.getArmas_Activas() == null);

        Arma arma_Test01 = new Arma();
        arma_Test01.setNombre("Espada de luz");
        arma_Test01.setPunt_Atk(5);

        personaje_Test.añadirArmaActiva(arma_Test01);

        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 1);
        Assertions.assertTrue(personaje_Test.getArmas_Activas().contains(arma_Test01));

        Arma arma_Test02 = new Arma();
        arma_Test02.setNombre("Espada de huesos");
        arma_Test02.setPunt_Atk(2);

        personaje_Test.añadirArmaActiva(arma_Test02);

        Assertions.assertTrue(personaje_Test.getArmas_Activas().size() == 2);
        Assertions.assertTrue(personaje_Test.getArmas_Activas().contains(arma_Test02));
    }

    @Test
    void hasFainted() {
        persj_Test.setPunt_Salud(0);
        Assertions.assertEquals(true,persj_Test.hasFainted());
        persj_Test.setPunt_Salud(1);
        Assertions.assertEquals(false,persj_Test.hasFainted());
        persj_Test.setPunt_Salud(-1);
        Assertions.assertEquals(true,persj_Test.hasFainted());
    }

}