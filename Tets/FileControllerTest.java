import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    Usuario user_Test;
    FileController fc_Test;

    @BeforeEach
    void setUp(){
        user_Test = new Usuario();
        fc_Test = new FileController();
    }

    @Test
    void existeUsuario_NoExist() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        Assertions.assertEquals(false,fc_Test.existeUsuario(user_Test));
    }

    @Test
    void existeUsuario_Exist() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        Personaje p_tets = new Personaje();
        p_tets.setId("TeteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        fc_Test.addUsuario(user_Test);
        Assertions.assertEquals(true,fc_Test.existeUsuario(user_Test));
        fc_Test.deleteUsuario(user_Test);
        fc_Test.borrarPersoanje(p_tets);
    }

    @Test
    void deleteUsuario() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        Personaje p_tets = new Personaje();
        p_tets.setId("TeteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        fc_Test.addUsuario(user_Test);

        Assertions.assertEquals(true,fc_Test.existeUsuario(user_Test));

        fc_Test.deleteUsuario(user_Test);

        Assertions.assertEquals(false,fc_Test.existeUsuario(user_Test));
    }

    @Test
    void modificarUsuario() {
        //Asignar id
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        //AsignarPersonaje
        Personaje p_tets = new Personaje();
        p_tets.setId("TeteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        //Asignar Nombre
        user_Test.setNombre("Hermenegildo");
        //Asignar Rol
        user_Test.setRol(Rol.usuario);
        //Asignar Nick
        user_Test.setNick("uwu");
        //Asignar Password
        user_Test.setPassword("*****");
        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        Usuario user_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());
        //Hacer alguna modificación
        user_Test_aux.setNombre("no uwu >:)");
        //Get File Location
        String Filelocation = fc_Test.locationUsuario + "/" + user_Test_aux.getNum_Registro() + ".txt";
        //Change Data
        fc_Test.modificarUsuario(user_Test_aux);

        //Charge new user
        user_Test_aux = fc_Test.getUsuario(user_Test_aux.getNum_Registro());

        Assertions.assertEquals(false,user_Test.equals(user_Test_aux));

        fc_Test.deleteUsuario(user_Test);
        fc_Test.borrarPersoanje(p_tets);
    }

    @Test
    void addUsuario() {
    }

    @Test
    void getUsuario() {
    }

    @Test
    void addPersonaje() {
    }

    @Test
    void addAllInfoPersonaje() {
    }

    @Test
    void modificarPersonaje() {
    }

    @Test
    void getPersonaje() {
    }

    @Test
    void buscarMods() {
    }

    @Test
    void buscarArmas() {
    }

    @Test
    void buscarArmaduras() {
    }

    @Test
    void buscarHabilidades() {
    }

    @Test
    void getTalentos() {
    }

    @Test
    void getDones() {
    }

    @Test
    void getDisciplinas() {
    }

    @Test
    void existePersonaje() {
    }

    @Test
    void buscarEsbirros() {
    }

    @Test
    void testBuscarEsbirros() {
    }
}