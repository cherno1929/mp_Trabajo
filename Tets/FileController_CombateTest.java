import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileController_CombateTest {

    FileController_Combate fc_Test;
    Usuario user_Test;
    @BeforeEach
    void setUp() {
        fc_Test = new FileController_Combate();
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
        user_Test.setNick("uwu");
        //Asignar Password
        user_Test.setPassword("*****");
    }

    @Test
    void es_Baneable_No() {
        FileController_Combate fc_Combate_aux = new FileController_Combate();

        Desafio desafio_Test = new Desafio();

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        Date date = new Date(System.currentTimeMillis());
        assertEquals(false,fc_Test.es_Baneable(date,desafio_Test.getJ2()));

        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void es_Baneable_Si() {
        FileController_Combate fc_Combate_aux = new FileController_Combate();

        Desafio desafio_Test = new Desafio();

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        Persistencia persistencia_Tets = new Persistencia();

        persistencia_Tets.setPerdedor(desafio_Test.j1);
        persistencia_Tets.setJ1(desafio_Test.j1);
        persistencia_Tets.setGanador(desafio_Test.j2);
        persistencia_Tets.setJ2(desafio_Test.j2);
        persistencia_Tets.setN_Turnos(20);
        persistencia_Tets.setOroGanado(5);
        persistencia_Tets.setEsbirros_Vivos(null);
        persistencia_Tets.setFecha_Combate(new Date(System.currentTimeMillis() - 10000));

        Date date = new Date(System.currentTimeMillis());
        assertEquals(true,fc_Test.es_Baneable(date,desafio_Test.getJ2()));

        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void añadirRanking() {

        fc_Test.añadirRanking(user_Test);

        Assertions.assertTrue(fc_Test.verGanadores().contains(user_Test.getNombre()));
    }

}