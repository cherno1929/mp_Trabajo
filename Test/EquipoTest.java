import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {

    @Test
    void nombre() {
        Equipo eq = new Equipo();

        eq.setNombre("polvo de estrella");

        Assertions.assertTrue(eq.getNombre().equals("polvo de estrella"));

        Assertions.assertFalse(eq.getNombre().equals("Espada de hierro"));
    }

    @Test
    void mods(){
        Modificador mod = new Modificador();
        mod.setTipo_mod(Tipo_mod.Fortaleza);
        mod.setNombre("Presión");
        mod.setGrado_Efecto(2);

        Equipo eq = new Equipo();
        eq.setMod(mod);

        Assertions.assertTrue(eq.getMod().contains(mod));
    }

    @Test
    void quitMod(){
        Modificador mod = new Modificador();
        mod.setTipo_mod(Tipo_mod.Fortaleza);
        mod.setNombre("Presión");
        mod.setGrado_Efecto(2);

        Equipo eq = new Equipo();
        eq.setMod(mod);

        Assertions.assertTrue(eq.getMod().contains(mod));

        eq.quitMod(mod);

        Assertions.assertTrue(eq.getMod().size() == 0);
    }
}