import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        fc_Test.addDesafio(desafio_Test);

        Date date = new Date();
        assertEquals(false,fc_Test.es_Baneable(date,desafio_Test.getJ2()));

        System.gc();

        fc_Test.borrarDesafio(desafio_Test.getId());
        fc_Test.deleteUsuario(user_Test);
    }


    @Test
    void añadirPersistencia() {
        Persistencia pers = new Persistencia();

        pers.setJ1(user_Test);
    }


    @Test
    void añadirRanking() {
        FileController_Combate fc_Test = new FileController_Combate();

        fc_Test.añadirRanking(user_Test);

        Assertions.assertTrue(fc_Test.verGanadores().contains(user_Test.getNombre()));
    }


}