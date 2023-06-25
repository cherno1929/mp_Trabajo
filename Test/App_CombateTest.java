import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        user_Test.setPersonajeActivo(pereJ);
    }

    @Test
    void a(){
        App_Combate app_Com = new App_Combate();


    }



}