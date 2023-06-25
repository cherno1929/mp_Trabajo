import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModificadorTest {

    @Test
    void nombre() {
        Modificador eq = new Modificador();

        eq.setNombre("polvo de estrella");

        Assertions.assertTrue(eq.getNombre().equals("polvo de estrella"));

        Assertions.assertFalse(eq.getNombre().equals("estrella de hierro"));
    }

    @Test
    void gradoEfecto() {
        Modificador eq = new Modificador();

        eq.setGrado_Efecto(1);
        Assertions.assertTrue(1 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(2);
        Assertions.assertTrue(2 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(3);
        Assertions.assertTrue(3 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(4);
        Assertions.assertTrue(4 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(5);
        Assertions.assertTrue(5 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(0);
        Assertions.assertTrue(5 == eq.getGrado_Efecto());

        eq.setGrado_Efecto(-1);
        Assertions.assertTrue(5 == eq.getGrado_Efecto());

    }

    @Test
    void tipoMod() {
        Modificador eq = new Modificador();

        Assertions.assertTrue(null == eq.getTipo_mod());

        eq.setTipo_mod(Tipo_mod.Debilidad);
        Assertions.assertTrue(Tipo_mod.Debilidad == eq.getTipo_mod());

        eq.setTipo_mod(Tipo_mod.Fortaleza);
        Assertions.assertTrue(Tipo_mod.Fortaleza == eq.getTipo_mod());

    }

}