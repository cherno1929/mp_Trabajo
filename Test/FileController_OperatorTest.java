import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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
        Assertions.assertTrue(fc_Test.getBaneados().size() > 0);

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

    @Test
    void getDesafios() {
        FileController_Operator file_Op_Test01 = new FileController_Operator();

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        file_Op_Test01.addDesafio(desafio_Test);

        boolean valid = false;

        for (Desafio desf : file_Op_Test01.getDesafios()) {
            valid = desafio_Test.getId().equals(desf.getId());
            if (valid) {
                break;
            }
        }

        Assertions.assertTrue(valid);
        System.gc();
        file_Op_Test01.borrarDesafio(desafio_Test.getId());
    }

    @Test
    void getSolicitudesDesafio() {
        FileController_Operator file_Op_Test01 = new FileController_Operator();

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(true);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        file_Op_Test01.addUsuario(user_Test);
        file_Op_Test01.addDesafio(desafio_Test);

        boolean valid = false;

        Assertions.assertTrue(file_Op_Test01.getSolicitudesDesafio(user_Test).size() > 0);

        for (Desafio desf : file_Op_Test01.getSolicitudesDesafio(user_Test)) {
            valid = desafio_Test.getId().equals(desf.getId()) && desf.getValidado();
            if (valid) {
                break;
            }
        }

        Assertions.assertTrue(valid);

        file_Op_Test01.borrarDesafio(desafio_Test.getId());
        file_Op_Test01.deleteUsuario(user_Test);
    }

    @Test
    void modificarDesafio() {
        System.gc();

        FileController_Operator file_Op_Test01 = new FileController_Operator();

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        file_Op_Test01.addDesafio(desafio_Test);

        desafio_Test.setOro(20);

        file_Op_Test01.modificarDesafio("Ficheros_app/Desafios" +"/"+desafio_Test.getId() + ".txt",desafio_Test);

        Assertions.assertTrue(file_Op_Test01.getDesafio(desafio_Test.getId()) != null);
        Assertions.assertTrue(file_Op_Test01.getDesafio(desafio_Test.getId()).getOro() == 20);

        file_Op_Test01.borrarDesafio(desafio_Test.getId());

        File deleter = new File("Ficheros_app/Desafios" +"/"+desafio_Test.getId());

        System.gc();

        deleter.delete();
    }

    @Test
    void nameModsDesafio() {
        FileController_Operator file_Op_Test01 = new FileController_Operator();

        Assertions.assertTrue(file_Op_Test01.nameModsDesafio(new HashSet<>()).equals(""));

        Assertions.assertTrue(file_Op_Test01.nameModsDesafio(constructDesafios()).equals("Test_x - Test_x - Test_x - "));
    }

    Set<Modificador> constructDesafios() {
        Set<Modificador> mods = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            Modificador mod = new Modificador();
            mod.setTipo_mod(Tipo_mod.Fortaleza);
            mod.setNombre("Test_x");
            mod.setGrado_Efecto(1);
            mods.add(mod);
        }

        return mods;
    }

    @Test
    void writeDesafio() {
        FileController_Operator file_OP_Test01 = new FileController_Operator();

        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);

        file_OP_Test01.addDesafio(desafio_Test);

        File file_Test = new File("Ficheros_app/Desafios/"+desafio_Test.getId()+".txt");

        String contenTest = "";

        if (file_Test.exists()) {
            try {
                BufferedReader readerTest = new BufferedReader(new FileReader(file_Test));
                String line;
                while ((line = readerTest.readLine()) != null) {
                    contenTest += line + "\n";
                }
                Assertions.assertTrue(contenTest.equals("J1 : Non_Exist_User\n" +
                        "J2 : Non_Exist_User\n" +
                        "Oro : 10\n" +
                        "Mod_J1 : \n" +
                        "Mod_J2 : \n" +
                        "Validado : false\n"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        file_OP_Test01.borrarDesafio(desafio_Test.getId());
    }

    @Test
    void getBaneados() {
        FileController_Operator file_Op_Test01 = new FileController_Operator();

        file_Op_Test01.addUsuario(user_Test);

        file_Op_Test01.banear(user_Test);

        boolean valid = false;

        for (Usuario bannedUser : file_Op_Test01.getBaneados()) {
            valid = bannedUser.getNum_Registro().equals(user_Test.getNum_Registro());
            if (valid) {
                break;
            }
        }

        Assertions.assertTrue(file_Op_Test01.getBaneados().size() > 0 && valid);

        file_Op_Test01.deleteUsuario(user_Test);
    }


}