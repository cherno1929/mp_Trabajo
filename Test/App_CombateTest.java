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

        Assertions.assertTrue(appTest1.mostrarModificador(mods).equals("Test01Test02Test03"));

        Modificador mod4 = new Modificador();
        mods.add(mod4);

    }





}