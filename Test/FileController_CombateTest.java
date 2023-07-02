import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileController_CombateTest {

    Usuario user_Test;
    Personaje pers_Test;
    @BeforeEach
    void setUp() {

        pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test = new Usuario();
        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        //AsignarPersonaje
        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("abc");
        //Asignar Password
        user_Test.setPassword("*****");

        user_Test.setPersonajeActivo(pers_Test);
    }


    @Test
    void es_Baneable() {

        FileController_Combate fc_Test = new FileController_Combate();


        fc_Test.addUsuario(user_Test);

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        Date date = new Date();
        Assertions.assertFalse(fc_Test.es_Baneable(date,desafio_Test.getJ2()));

        fc_Test.addDesafio(desafio_Test);

        System.gc();

        fc_Test.borrarDesafio(desafio_Test.getId());
        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void getPersistencia() {
        Persistencia pers = new Persistencia();

        pers.setJ1(user_Test);
        pers.setJ2(user_Test);
        Date dat = new Date();
        pers.setFecha_Combate(dat);
        pers.setOroGanado(0);
        pers.setGanador(user_Test);
        pers.setPerdedor(user_Test);
        pers.setN_Turnos(2);

        FileController_Combate f_Comb = new FileController_Combate();

        f_Comb.addPersistencia(pers);
        f_Comb.addUsuario(user_Test);

        Assertions.assertTrue(null != f_Comb.getPersistencia(pers));

        System.gc();

        f_Comb.deleteUsuario(user_Test);
        f_Comb.deletePersistence(pers);
    }


    @Test
    void addDestroyPersistenc() {
        Persistencia pers = new Persistencia();

        pers.setJ1(user_Test);
        pers.setJ2(user_Test);
        Date dat = new Date();
        pers.setFecha_Combate(dat);
        pers.setOroGanado(0);
        pers.setGanador(user_Test);
        pers.setPerdedor(user_Test);
        pers.setN_Turnos(2);

        FileController_Combate f_Comb = new FileController_Combate();

        f_Comb.addPersistencia(pers);

        f_Comb.addUsuario(user_Test);

        Assertions.assertTrue(f_Comb.existePersistencia(pers));

        f_Comb.deleteUsuario(user_Test);
        f_Comb.deletePersistence(pers);

        Assertions.assertFalse(f_Comb.existePersistencia(pers));

    }

    @Test
    void destruirDesafio() {
        FileController_Combate file_Test01 = new FileController_Combate();

        Desafio desafio_Test01 = new Desafio();
        desafio_Test01.setJ2(user_Test);
        desafio_Test01.setJ1(user_Test);
        desafio_Test01.setOro(10);

        file_Test01.addDesafio(desafio_Test01);

        Assertions.assertTrue(file_Test01.getDesafio(desafio_Test01.getId()) != null);

        file_Test01.destruirDesafio(desafio_Test01);

        Assertions.assertTrue(file_Test01.getDesafio(desafio_Test01.getId()) == null);

    }

    @Test
    void existePersistencia() {
        FileController_Combate file_Test01 = new FileController_Combate();

        Persistencia pers = new Persistencia();

        pers.setJ1(user_Test);
        pers.setJ2(user_Test);
        Date dat = new Date();
        pers.setFecha_Combate(dat);
        pers.setOroGanado(0);
        pers.setGanador(user_Test);
        pers.setPerdedor(user_Test);
        pers.setN_Turnos(2);

        Assertions.assertFalse(file_Test01.existePersistencia(pers));

        file_Test01.addPersistencia(pers);

        Assertions.assertTrue(file_Test01.existePersistencia(pers));

        file_Test01.deletePersistence(pers);

        Assertions.assertFalse(file_Test01.existePersistencia(pers));
    }

    @Test
    void añadirRanking() {
        FileController_Combate fc_Test = new FileController_Combate();

        fc_Test.añadirRanking(user_Test);

        Assertions.assertTrue(fc_Test.verGanadores().contains(user_Test.getNombre()));
    }

    @Test
    void  getAllPersistencias() {
        FileController_Combate fileControllerCombate_Test = new FileController_Combate();
        Persistencia pers = new Persistencia();
        pers.setJ1(user_Test);
        pers.setJ2(user_Test);
        Date dat = new Date();
        pers.setFecha_Combate(dat);
        pers.setOroGanado(0);
        pers.setGanador(user_Test);
        pers.setPerdedor(user_Test);
        pers.setN_Turnos(2);
        fileControllerCombate_Test.addUsuario(user_Test);
        fileControllerCombate_Test.addPersistencia(pers);
        List<Persistencia> persistencia = fileControllerCombate_Test.getAllPersistencias();
        boolean valid = false;
        Assertions.assertTrue(persistencia.size() >= 1);
        for (Persistencia pers_Test : persistencia) {
            valid = comparePers(pers,pers_Test);
            if (valid){break;}
        }
        fileControllerCombate_Test.deletePersistence(pers);
        fileControllerCombate_Test.deleteUsuario(user_Test);
        Assertions.assertTrue(valid);
    }

    boolean comparePers(Persistencia pers1,Persistencia pers2){

        boolean validOro = pers1.getOroGanado() == pers2.getOroGanado();
        boolean validTurnos = pers1.getN_Turnos() == pers2.getN_Turnos();
        boolean validFechaComb = pers1.getFecha_Combate().equals(pers2.getFecha_Combate());
        boolean validGanador = pers1.getGanador().getNum_Registro().equals(pers2.getGanador().getNum_Registro());
        boolean validPerdedor = pers1.getPerdedor().getNum_Registro().equals(pers2.getPerdedor().getNum_Registro());
        return validOro && validGanador && validPerdedor && validTurnos && validFechaComb;
    }

}