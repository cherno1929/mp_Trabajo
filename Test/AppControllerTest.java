import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    AppController controller_test;
    Usuario test_User;
    Personaje test_Personaje;

    @BeforeEach
    void setUp(){
        System.gc();
        controller_test = new AppController();
        test_User = new Usuario();
        test_Personaje = new Personaje();
    }

    @Test
    public void checkIdNotRepeted(){
        String id_Test = controller_test.getRandomId();
        String id_Location = controller_test.fc.locationUsuario + "/" + id_Test + ".txt";
        File fileTest = new File(id_Location);
        Assertions.assertEquals(false, fileTest.exists());
    }

    @Test
    public void checkIdRepeted(){
        test_User.setNick("UserTest_01");
        test_User.setNombre("Test_01");
        test_User.setNum_Registro(controller_test.getRandomId());
        test_User.setRol(Rol.usuario);
        test_User.setPassword("prove");
        test_Personaje.setId("test_01");
        test_User.setPersonajeActivo(test_Personaje);
        controller_test.fc.addUsuario(test_User);
        String id_Location = controller_test.fc.locationUsuario + "/" + test_User.getNum_Registro() + ".txt";
        File fileTest = new File(id_Location);
        Assertions.assertEquals(true, fileTest.exists());
        fileTest.delete();
    }



}