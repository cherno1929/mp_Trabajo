import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileController_OperatorTest {

    Usuario user_Test;

    Personaje pereJ;
    @BeforeEach
    void setUp(){

        user_Test = new Usuario();

        pereJ = new Personaje();

        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);

        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("u");
        //Asignar Password
        user_Test.setPassword("*****");

        pereJ.setId("fav");

        user_Test.setPersonajeActivo(pereJ);
    }


    @Test
    void banear() {

        FileController_Combate fc_Test = new FileController_Combate();

        fc_Test.addUsuario(user_Test);

        fc_Test.banear(user_Test);

        Assertions.assertTrue(Rol.baneado.equals(fc_Test.getUsuario(user_Test.getNum_Registro()).getRol()));

        System.gc();

        fc_Test.deleteUsuario(user_Test);

    }

    @Test
    void desBanear(){

        FileController_Combate fc_Test = new FileController_Combate();

        fc_Test.addUsuario(user_Test);

        fc_Test.desBanear(user_Test);

        Assertions.assertFalse(Rol.baneado.equals(fc_Test.getUsuario(user_Test.getNum_Registro()).getRol()));

    }

    @Test
    void borrarDesafio() {

        FileController_Operator fc_Op = new FileController_Operator();
        FileController_Combate fc_Combate_aux = new FileController_Combate();

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        fc_Combate_aux.addDesafio(desafio_Test);

        System.gc();

        fc_Op.borrarDesafio(desafio_Test.getId());

        Assertions.assertFalse(fc_Combate_aux.getDesafios().contains(desafio_Test));


        user_Test.setPersonajeActivo(pereJ);

    }



    // No funciona

    @Test
    void validarDesafio() {
        FileController_Operator fc_Op = new FileController_Operator();
        FileController_Combate fc_Combate_aux = new FileController_Combate();

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        fc_Op.addUsuario(user_Test);

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        fc_Combate_aux.addDesafio(desafio_Test);

        fc_Op.validarDesafio(desafio_Test);

        desafio_Test.setValidado(true);

        Assertions.assertFalse(fc_Op.getDesafios().contains(desafio_Test));

        System.gc();

        fc_Op.deleteUsuario(user_Test);
        fc_Op.borrarDesafio(desafio_Test.getId());

    }

}