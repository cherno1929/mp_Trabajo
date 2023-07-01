import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    Usuario user_Test;
    FileController fc_Test;
    Personaje pereJ;

    @BeforeEach
    void setUp(){
        user_Test = new Usuario();
        fc_Test = new FileController();

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
    void existeUsuario1() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        Assertions.assertEquals(false,fc_Test.existeUsuario(user_Test));
    }

    @Test
    void existeUsuario2() {
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
    void getPersonaje1() { //Obtiene correctamenete los personajes pero falla al compararlos
        String id_Pers = "Non Existent character";

        Personaje pers_Test = fc_Test.getPersonaje(id_Pers);

        Assertions.assertEquals(null,pers_Test);
    }

    @Test
    void getPersonaje2() { //Obtiene correctamenete los personajes pero falla al compararlos
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
    void buscarEsbirros1() {
        String[] esbirro_NoExiste = {"no_Existe"};

        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    List<Esbirro> buscados = new ArrayList<Esbirro>(fc_Test.buscarEsbirros(esbirro_NoExiste));
                });
    }

    @Test
    void buscarEsbirros2() {
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

    @Test
    void searchFiles() {

        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        user_Test.setNombre("Hermenegildo");
        user_Test.setRol(Rol.usuario);
        user_Test.setNick("u");
        user_Test.setPassword("*****");
        Personaje pereJ = new Personaje();
        pereJ.setId("fav");
        pereJ.setNombre("Personaje_Test01");
        pereJ.setPunt_Salud(5);
        pereJ.setOro(0);
        user_Test.setPersonajeActivo(pereJ);

        fc_Test.addUsuario(user_Test);

        Assertions.assertTrue(fc_Test.searchFiles(new File("Ficheros_app/Usuarios")).contains(user_Test.getNum_Registro() + ".txt"));

        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void getAllUsuarios() {
        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        user_Test.setNombre("Hermenegildo");
        user_Test.setRol(Rol.usuario);
        user_Test.setNick("u");
        user_Test.setPassword("*****");
        Personaje pereJ = new Personaje();
        pereJ.setId("fav");
        pereJ.setNombre("Personaje_Test01");
        pereJ.setPunt_Salud(5);
        pereJ.setOro(0);
        user_Test.setPersonajeActivo(pereJ);

        fc_Test.addUsuario(user_Test);

        Assertions.assertTrue(fc_Test.getAllUsuarios().size() >= 1);

        fc_Test.deleteUsuario(user_Test);
    }

    @Test
    void getHab() {
        Habilidad_Especial habTest01 = fc_Test.getHab(new File("Ficheros_app/Habilidades/Disciplina/Chupar_Sangre.txt"),Vampiro.class);
        Assertions.assertTrue(habTest01.getNombre().equals("Chupar_Sangre"));
        Assertions.assertTrue(habTest01.getAtk() == 3);
        Assertions.assertTrue(habTest01.getDef() == 0);
        Assertions.assertTrue(habTest01 instanceof Disciplina);

        habTest01 = fc_Test.getHab(new File("Ficheros_app/Habilidades/Don/Garra Umbria.txt"),Licantropo.class);

        Assertions.assertTrue(habTest01.getNombre().equals("Garra Umbria"));
        Assertions.assertTrue(habTest01.getAtk() == 3);
        Assertions.assertTrue(habTest01.getDef() == 1);
        Assertions.assertTrue(habTest01 instanceof Don);

        habTest01 = fc_Test.getHab(new File("Ficheros_app/Habilidades/Talento/Puño acero.txt"),Cazador.class);

        Assertions.assertTrue(habTest01.getNombre().equals("Puño acero"));
        Assertions.assertTrue(habTest01.getAtk() == 5);
        Assertions.assertTrue(habTest01.getDef() == 2);
        Assertions.assertTrue(habTest01 instanceof Talento);

    }

    @Test
    void getRazaPersonaje() {
        Vampiro vampiro_Test = new Vampiro();
        vampiro_Test.setId("VampiroTest");

        fc_Test.addPersonaje(vampiro_Test);
        Assertions.assertTrue(fc_Test.getRazaPersonaje("Ficheros_app/Personaje_Usuario/"+vampiro_Test.getId() + ".txt") instanceof Vampiro);
        fc_Test.borrarPersoanje(vampiro_Test);

        Licantropo licantropo_Test = new Licantropo();
        licantropo_Test.setId("LicantropoTest");

        fc_Test.addPersonaje(licantropo_Test);
        Assertions.assertTrue(fc_Test.getRazaPersonaje("Ficheros_app/Personaje_Usuario/"+licantropo_Test.getId()+".txt") instanceof Licantropo);
        fc_Test.borrarPersoanje(licantropo_Test);

        Cazador cazador_Test = new Cazador();
        cazador_Test.setId("cazadorTest");

        fc_Test.addPersonaje(cazador_Test);
        Assertions.assertTrue(fc_Test.getRazaPersonaje("Ficheros_app/Personaje_Usuario/"+cazador_Test.getId()+".txt") instanceof Cazador);
        fc_Test.borrarPersoanje(cazador_Test);
    }

    ///////////////

    @Test
    void addDesafio() throws IOException {
        Desafio desafio_Test = new Desafio();
        desafio_Test.setValidado(false);
        desafio_Test.setOro(10);
        desafio_Test.setMod_j1(null);
        desafio_Test.setMod_j2(null);
        desafio_Test.setJ1(user_Test);
        desafio_Test.setJ2(user_Test);
        fc_Test.addDesafio(desafio_Test);
        String data = "";
        try {
            BufferedReader readerTest = new BufferedReader(new FileReader("Ficheros_app/Desafios/"+desafio_Test.getId()));
            String line;
            while ((line=readerTest.readLine())!=null){
                data += line + "\n";
            }
            Assertions.assertTrue(data.equals("J1 : Non_Exist_User\n" +
                    "J2 : Non_Exist_User\n" +
                    "Oro : 20\n" +
                    "Mod_J1 : \n" +
                    "Mod_J2 : \n" +
                    "Validado : false\n"));
        }catch (IOException e){
            throw e;
        }
    }

    @Test
    void getDisciplinas() {
        Set<Habilidad_Especial> disciplinas = fc_Test.getDisciplinas();

        boolean valid = true;

        for (Habilidad_Especial displina : disciplinas) {
            valid = displina instanceof Disciplina;
            if (!valid){
                break;
            }
        }

        Assertions.assertTrue(valid);
        Assertions.assertTrue(disciplinas.size() >= 1);
    }

    @Test
    void getDones() {
        Set<Habilidad_Especial> dones = fc_Test.getDones();

        boolean valid = true;

        for (Habilidad_Especial don : dones) {
            valid = don instanceof Don;
            if (!valid){
                break;
            }
        }
        Assertions.assertTrue(valid);
        Assertions.assertTrue(dones.size() >= 1);
    }

    @Test
    void getTalentos() {
        Set<Habilidad_Especial> talentos = fc_Test.getTalentos();
        boolean valid = true;

        for (Habilidad_Especial talento : talentos) {
            valid = talento instanceof Talento;
            if (!valid){
                break;
            }
        }
        Assertions.assertTrue(valid);
        Assertions.assertTrue(talentos.size() >= 1);
    }
}