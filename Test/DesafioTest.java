import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesafioTest {

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
    void getSetOro(){

        Desafio desf = new Desafio();

        desf.setOro(0);
        Assertions.assertTrue(0 == desf.getOro());

        desf.setOro(10);
        Assertions.assertTrue(10 == desf.getOro());

        desf.setOro(100);
        Assertions.assertTrue(100 == desf.getOro());

        desf.setOro(-1);
        Assertions.assertTrue(100 == desf.getOro());

        desf.setOro(-100);
        Assertions.assertTrue(100 == desf.getOro());

    }

    @Test
    void getSetUusario(){
        Desafio desf = new Desafio();

        Assertions.assertTrue(null == desf.getJ1());
        Assertions.assertTrue(null == desf.getJ2());

        desf.setJ1(user_Test);
        desf.setJ2(user_Test);

        Assertions.assertTrue(null != desf.getJ1());
        Assertions.assertTrue(null != desf.getJ2());
    }

    @Test
    void getSetValido(){
        Desafio desf = new Desafio();

        Assertions.assertFalse(desf.getValidado());

        desf.setValidado(true);

        Assertions.assertTrue(desf.getValidado());
    }

    @Test
    void getSetMods() {
        Desafio desf = new Desafio();

        Assertions.assertTrue(null == desf.getMod_j1());
        Assertions.assertTrue(null == desf.getMod_j2());

        Modificador mod1 = constrMod();
        Modificador mod2 = constrMod();

        desf.setMod_j1_one(mod1);
        desf.setMod_j2_one(mod2);

        Assertions.assertTrue(desf.mod_j1.contains(mod1));
        Assertions.assertTrue(desf.mod_j2.contains(mod2));
    }

    Modificador constrMod(){
        Modificador mod = new Modificador();

        mod.setGrado_Efecto(1);
        mod.setNombre("TNT");
        mod.setTipo_mod(Tipo_mod.Debilidad);

        return mod;
    }

}