import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileController_OperatorTest {

    Usuario user_Test;
    FileController_Operator fc_Test;
    @BeforeEach
    void setUp(){
        user_Test = new Usuario();
        fc_Test = new FileController_Operator();
    }

    @Test
    void banear() {
        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        //AsignarPersonaje
        Personaje p_tets = new Personaje();
        p_tets.setId("TesteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("uwu");
        //Asignar Password
        user_Test.setPassword("*****");

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        fc_Test.banear(user_Test);

        Usuario fc_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());

        Assertions.assertEquals(Rol.baneado,fc_Test_aux.getRol());
    }

    @Test
    void desBanear() {
        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        //AsignarPersonaje
        Personaje p_tets = new Personaje();
        p_tets.setId("TesteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("uwu");
        //Asignar Password
        user_Test.setPassword("*****");

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        fc_Test.banear(user_Test);

        Usuario fc_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());

        Assertions.assertEquals(Rol.baneado,fc_Test_aux.getRol());

        fc_Test.desBanear(user_Test,Rol.usuario);

        fc_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());

        Assertions.assertEquals(true,fc_Test_aux.getRol() != Rol.baneado);
    }

    /*
    @Test
    void borrarDesafio() {

        FileController_Combate fc_Combate_aux = new FileController_Combate();

        Desafio desafio_Test = new Desafio();

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

        fc_Test.addDesafio(desafio_Test);

        List<Desafio> desafios = fc_Combate_aux.getDesafios();

        Assertions.assertEquals(true,desafios.contains(desafio_Test));

    }
         */

    // No funciona

    @Test
    void validarDesafio() {
        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        //AsignarPersonaje
        Personaje p_tets = new Personaje();
        p_tets.setId("TesteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("uwu");
        //Asignar Password
        user_Test.setPassword("*****");

        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        user_Test.setPersonajeActivo(pers_Test);

        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        Desafio desafio_Test = new Desafio();

        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);
        desafio_Test.setValidado(false);
        desafio_Test.setMod_j2(null);
        desafio_Test.setMod_j1(null);
        desafio_Test.setOro(100);

        fc_Test.addDesafio(desafio_Test);

        fc_Test.validarDesafio(desafio_Test);

        List<Desafio> desafios = fc_Test.getDesafios();

        HashMap<String,Desafio> desafios_M = new HashMap<>();

        for (Desafio desf : desafios){
            desafios_M.put(desf.getJ1().getNombre() + "-" + desf.getJ2().getNombre(),desf);
        }

        Assertions.assertEquals(true,desafios_M.get(desafio_Test.getJ1().getNombre()+"-"+desafio_Test.getJ2().getNombre()).validado);
    }
}