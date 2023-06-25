import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    Usuario user_Test;
    FileController fc_Test;

    @BeforeEach
    void setUp(){
        System.gc();
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
    void addCharacter() {
        Personaje p_tets = new Personaje();

        p_tets.setId("TeteoDePrueba");
        p_tets.setNombre("Buenas!!");

        fc_Test.addPersonaje(p_tets);

        Personaje pesr_Aux = fc_Test.getPersonaje(p_tets.getId());

        Assertions.assertTrue(p_tets.getNombre().equals(pesr_Aux.getNombre()));

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

        System.gc();

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

        //Hacer alguna modificación
        user_Test.setNombre("no >:)");
        //Get File Location
        String Filelocation = fc_Test.locationUsuario + "/" + user_Test.getNum_Registro() + ".txt";
        //Change Data
        fc_Test.modificarUsuario(user_Test);

        //Charge new user
        Usuario user_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());

        Assertions.assertEquals(true,user_Test.nombre.equals(user_Test_aux.nombre));

        fc_Test.deleteUsuario(user_Test);
        fc_Test.borrarPersoanje(p_tets);
    }

    @Test
    void addUsuario() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        Personaje p_tets = new Personaje();
        p_tets.setId("TeteoDePrueba");
        user_Test.setPersonajeActivo(p_tets);
        fc_Test.addUsuario(user_Test);

        Assertions.assertEquals(true,fc_Test.existeUsuario(user_Test));

        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void getUsuario() { //Por algun motivo esta fallando
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
        user_Test.setNick("abcd");
        //Asignar Password
        user_Test.setPassword("*****");
        //Crear Usuario
        fc_Test.addUsuario(user_Test);

        Usuario user_Test_aux = fc_Test.getUsuario(user_Test.getNum_Registro());
        Assertions.assertEquals(true,user_Test.nick.equals(user_Test_aux.nick));
        Assertions.assertEquals(true,user_Test.password.equals(user_Test_aux.password));
        Assertions.assertEquals(true,user_Test.nombre.equals(user_Test_aux.nombre));
        Assertions.assertEquals(true,user_Test.rol == user_Test_aux.rol);

        fc_Test.deleteUsuario(user_Test);
        fc_Test.borrarPersoanje(p_tets);
    }

    @Test
    void addPersonaje() { //Obtiene correctamenete los personajes pero falla al compararlos
        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        fc_Test.addPersonaje(pers_Test);

        Personaje pers_Test_aux = fc_Test.getPersonaje(pers_Test.getId());

        Assertions.assertEquals(true,pers_Test.getId().equals(pers_Test_aux.getId()));
        Assertions.assertEquals(true,pers_Test.getNombre().equals(pers_Test_aux.getNombre()));
        Assertions.assertEquals(true,pers_Test.getOro() == pers_Test_aux.getOro());
        Assertions.assertEquals(true,pers_Test.getPunt_Salud() == pers_Test_aux.getPunt_Salud());
        Assertions.assertEquals(true,pers_Test.getPoder() == pers_Test_aux.getPoder());

        fc_Test.borrarPersoanje(pers_Test);
    }

    @Test
    void modificarPersonaje() {
        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        fc_Test.addPersonaje(pers_Test);

        pers_Test.setPunt_Salud(10);

        fc_Test.modificarPersonaje(pers_Test);

        Personaje pers_Test_aux = fc_Test.getPersonaje(pers_Test.getId());

        Assertions.assertEquals(true,pers_Test_aux.getPunt_Salud() == 10);

        fc_Test.borrarPersoanje(pers_Test_aux);
    }

    @Test
    void getPersonaje_NoExiste() { //Obtiene correctamenete los personajes pero falla al compararlos
        String id_Pers = "Non Existent character";

        Personaje pers_Test = fc_Test.getPersonaje(id_Pers);

        Assertions.assertEquals(null,pers_Test);
    }

    @Test
    void getPersonaje_Existe() { //Obtiene correctamenete los personajes pero falla al compararlos
        Personaje pers_Test = new Personaje();
        pers_Test.setId("asodwekfiew");
        pers_Test.setNombre("tets_0");
        pers_Test.setOro(100);
        pers_Test.setPoder(5);
        pers_Test.setPunt_Salud(5);

        fc_Test.addPersonaje(pers_Test);

        Assertions.assertEquals(true,fc_Test.existePersonaje(pers_Test.getId()));

        fc_Test.borrarPersoanje(pers_Test);
    }

    @Test
    void buscarMods_NoExiste() {
        String[] mod_No_Existe = {"No_existe"};


        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    List<Modificador> buscados = new ArrayList<Modificador>(fc_Test.buscarMods(mod_No_Existe));
                });
    }

    @Test
    void buscarMods_Existe() {
        String[] mod_No_Existe = {"No_existe"};


        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    List<Modificador> buscados = new ArrayList<Modificador>(fc_Test.buscarMods(mod_No_Existe));
                });
    }

    @Test
    void buscarArmas() {
        String[] mod_Buscar = {"Sol"};

        List<Modificador> buscados = new ArrayList<Modificador>(fc_Test.buscarMods(mod_Buscar));

        Assertions.assertEquals(true,!buscados.isEmpty());
    }

    @Test
    void buscarArmaduras_NoExiste() {
        String[] arm_No_Existe = {"No_existe"};


        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    List<Armadura> buscados = new ArrayList<Armadura>(fc_Test.buscarArmaduras(arm_No_Existe));
                });
    }

    @Test
    void buscarArmaduras_Existe() {
        String[] arm_Existe = {"Armadura_de_cuero"};

        List<Armadura> buscados = new ArrayList<Armadura>(fc_Test.buscarArmaduras(arm_Existe));

        Assertions.assertNotNull(buscados.get(0));
    }

    @Test
    void buscarHabilidade() {

        final List<Habilidad_Especial>[] buscados = new List[]{null};

        Assertions.assertDoesNotThrow(
                () -> {
                    buscados[0] = new ArrayList<Habilidad_Especial>(fc_Test.buscarHabilidades(Vampiro.class));
                });

        Assertions.assertEquals(true, buscados[0].size() > 0);
    }


    @Test
    void buscarEsbirros_NoExiste() {
        String[] esbirro_NoExiste = {"no_Existe"};

        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    List<Esbirro> buscados = new ArrayList<Esbirro>(fc_Test.buscarEsbirros(esbirro_NoExiste));
                });
    }

    @Test
    void buscarEsbirros_Existe() {
        String[] esbirro_NoExiste = {"Esbitto_Test"};

        List<Esbirro> buscados = new ArrayList<Esbirro>(fc_Test.buscarEsbirros(esbirro_NoExiste));

        Assertions.assertNotNull(buscados.get(0));
    }

    @Test
    void deleteCharacter() {
        Personaje p_tets = new Personaje();

        p_tets.setId("TeteoDePrueba");
        p_tets.setNombre("Buenas!!");

        fc_Test.addPersonaje(p_tets);

        Personaje pesr_Aux = fc_Test.getPersonaje(p_tets.getId());

        System.gc();

        fc_Test.borrarPersoanje(p_tets);

        Assertions.assertFalse(fc_Test.existePersonaje(p_tets.getId()));
    }

}