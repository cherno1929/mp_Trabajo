import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class App_CombateTest {

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
        pereJ.setNombre("Personaje_Test01");
        pereJ.setPunt_Salud(5);
        pereJ.setOro(0);

        user_Test.setPersonajeActivo(pereJ);
    }

    @Test
    void CombateFinalizado() {
        Usuario usert_Test2 = new Usuario();
        Personaje pers_Test2 = new Personaje();

        pers_Test2.setPunt_Salud(5);
        pers_Test2.setId("Test2");
        pers_Test2.setNombre("Personaje_Test02");

        usert_Test2.setPersonajeActivo(pers_Test2);

        App_Combate appTest1 = new App_Combate();

        Assertions.assertFalse(appTest1.CombateFinalizado(user_Test,usert_Test2));

        user_Test.getPersonajeActivo().setPunt_Salud(0);
        Assertions.assertTrue(appTest1.CombateFinalizado(user_Test,usert_Test2));

        user_Test.getPersonajeActivo().setPunt_Salud(2);
        usert_Test2.getPersonajeActivo().setPunt_Salud(0);
        Assertions.assertTrue(appTest1.CombateFinalizado(user_Test,usert_Test2));

        user_Test.getPersonajeActivo().setPunt_Salud(0);
        Assertions.assertTrue(appTest1.CombateFinalizado(user_Test,usert_Test2));

        user_Test.getPersonajeActivo().setPunt_Salud(-10);
        Assertions.assertTrue(appTest1.CombateFinalizado(user_Test,usert_Test2));

    }

    @Test
    void enviarOro() {
        FileController fc_Test = new FileController();
        fc_Test.addUsuario(user_Test);
        fc_Test.addPersonaje(user_Test.getPersonajeActivo());

        Personaje personaje_Test02 = new Personaje();
        personaje_Test02.setOro(100);
        personaje_Test02.setId("Test02");

        Usuario usuario_Test02 = new Usuario();
        usuario_Test02.setPersonajeActivo(personaje_Test02);
        usuario_Test02.setNum_Registro("Test_Usuario_02");
        fc_Test.addUsuario(usuario_Test02);

        App_Combate app_Test1 = new App_Combate();

        Desafio desafio_Test01 = new Desafio();
        desafio_Test01.setJ1(user_Test);
        desafio_Test01.setJ2(usuario_Test02);

        app_Test1.enviarOro(desafio_Test01,-10);
        Personaje pessonaje_Test01 = fc_Test.getPersonaje(pereJ.getId());
        Assertions.assertTrue(pessonaje_Test01.getOro() == 0);

        app_Test1.enviarOro(desafio_Test01,1000);
        pessonaje_Test01 = fc_Test.getPersonaje(pereJ.getId());
        Assertions.assertTrue(personaje_Test02.getOro() == 0);
        Assertions.assertTrue(pessonaje_Test01.getOro() == 100);

        personaje_Test02.setOro(100);

        pereJ.setOro(10);
        app_Test1.enviarOro(desafio_Test01,10);
        pessonaje_Test01 = fc_Test.getPersonaje(pereJ.getId());
        Assertions.assertTrue(pessonaje_Test01.getOro() == 20);

        pereJ.setOro(20);
        app_Test1.enviarOro(desafio_Test01,50);
        pessonaje_Test01 = fc_Test.getPersonaje(pereJ.getId());
        Assertions.assertTrue(pessonaje_Test01.getOro() == 70);

        fc_Test.deleteUsuario(usuario_Test02);
        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void rendirse() {
        App_Combate app_Test01 = new App_Combate();

        Usuario usuario_Test02 = new Usuario();
        usuario_Test02.setNum_Registro("Test_Usuario_02");

        app_Test01.setJ1(user_Test);
        app_Test01.setJ2(usuario_Test02);

        app_Test01.Rendirse(usuario_Test02);

        Assertions.assertTrue(app_Test01.getGanador().getNum_Registro().equals("Non_Exist_User"));

        app_Test01.Rendirse(user_Test);

        Assertions.assertTrue(app_Test01.getGanador().getNum_Registro().equals("Test_Usuario_02"));

        app_Test01.Rendirse(null);

        Assertions.assertTrue(app_Test01.getGanador().getNum_Registro().equals("Test_Usuario_02"));

    }

    @Test
    void recibirDamage() {
        App_Combate app_combate_Test01 = new App_Combate();

        Personaje personaje_Test01 = new Personaje();
        personaje_Test01.setPunt_Salud(5);

        app_combate_Test01.recibirDamage(personaje_Test01,0);
        Assertions.assertTrue(personaje_Test01.getPunt_Salud() == 5);

        app_combate_Test01.recibirDamage(personaje_Test01,5);
        Assertions.assertTrue(personaje_Test01.getPunt_Salud() == 0);

        personaje_Test01.setPunt_Salud(5);
        app_combate_Test01.recibirDamage(personaje_Test01,10);
        Assertions.assertTrue(personaje_Test01.getPunt_Salud() == 0);

        personaje_Test01.setPunt_Salud(5);
        app_combate_Test01.recibirDamage(personaje_Test01,-5);
        Assertions.assertTrue(personaje_Test01.getPunt_Salud() == 5);

    }

    @Test
    void mostrarModificador() {
        Set<Modificador> mods = new HashSet<Modificador>();

        Modificador mod1 = new Modificador();
        mod1.setNombre("Test01");
        mods.add(mod1);
        Modificador mod2 = new Modificador();
        mod2.setNombre("Test02");
        mods.add(mod2);
        Modificador mod3 = new Modificador();
        mod3.setNombre("Test03");
        mods.add(mod3);

        App_Combate appTest1 = new App_Combate();

        Assertions.assertTrue(appTest1.mostrarModificador(mods).length() == 18);

        Modificador mod4 = new Modificador();
        mods.add(mod4);

    }





}