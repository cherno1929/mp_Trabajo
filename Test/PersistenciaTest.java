import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

class PersistenciaTest {

    @Test
    void fechaPers() {
        Persistencia pers = new Persistencia();

        pers.setFecha_Combate(new Date());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = dateFormat.format(pers.fecha_Combate);

        Assertions.assertTrue(pers.getStringFecha().equals(dateStr));
    }

    @Test
    void esbV() {
        List<Esbirro> esbirros = new ArrayList<Esbirro>();
        for (int i = 0; i <= 2; i++) {
            esbirros.add(constrEsbirro());
        }
        Persistencia pers = new Persistencia();

        Assertions.assertTrue(pers.getStringEsbirros_Vivos().equals(""));

        pers.setEsbirros_Vivos(esbirros);
        Assertions.assertTrue(pers.getStringEsbirros_Vivos().equals("HolaBuenasHolaBuenasHolaBuenas"));
    }

    @Test
    void getSetOro(){

        Persistencia peer = new Persistencia();

        peer.setOroGanado(0);
        Assertions.assertTrue(0 == peer.getOroGanado());

        peer.setOroGanado(10);
        Assertions.assertTrue(10 == peer.getOroGanado());

        peer.setOroGanado(100);
        Assertions.assertTrue(100 == peer.getOroGanado());

        peer.setOroGanado(-10);
        Assertions.assertTrue(100 == peer.getOroGanado());

        peer.setOroGanado(-100);
        Assertions.assertTrue(100 == peer.getOroGanado());

    }

    @Test
    void usuarios() {
        Usuario user_Test = new Usuario();

        String usuarioId = "Non_Exist_User";
        user_Test.setNum_Registro(usuarioId);
        user_Test.setNombre("Hermenegildo");
        user_Test.setRol(Rol.usuario);
        user_Test.setNick("u");
        user_Test.setPassword("*****");

        Persistencia pers = new Persistencia();

        Assertions.assertTrue(null == pers.getJ1());

        pers.setJ1(user_Test);

        Assertions.assertFalse(null == pers.getJ1());
    }

    @Test
    void getStringEsbirros_Vivos() {
        Persistencia pers_Test1 = new Persistencia();

        List<Esbirro> esb = new ArrayList<Esbirro>();

        esb.add(constrEsbirro());
        esb.add(constrEsbirro());
        esb.add(constrEsbirro());
        esb.add(constrEsbirro());

        pers_Test1.setEsbirros_Vivos(esb);

        Assertions.assertTrue(pers_Test1.getStringEsbirros_Vivos().equals("HolaBuenasHolaBuenasHolaBuenasHolaBuenas"));

        Esbirro esbirroTest01 = new Esbirro();
        esb.add(esbirroTest01);

        Assertions.assertTrue(pers_Test1.getStringEsbirros_Vivos().equals("HolaBuenasHolaBuenasHolaBuenasHolaBuenas"));
    }

    Esbirro constrEsbirro() {
        Esbirro esb = new Esbirro();

        esb.setSalud(2);
        esb.setNombre("HolaBuenas");

        return esb;
    }

}