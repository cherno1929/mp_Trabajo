import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class VampiroTest {
    Vampiro vampy;

    @BeforeEach
    void setUp() {
        vampy = new Vampiro();
    }

    @Test
    void getSetEdad() {
        vampy.setEdad(55);
        Assertions.assertTrue(55 == vampy.getEdad());

        vampy.setEdad(0);
        Assertions.assertTrue(55 == vampy.getEdad());

        vampy.setEdad(5001);
        Assertions.assertTrue(55 == vampy.getEdad());


    }


    @Test
    void getSetDisciplina() {
        Disciplina disp1 = new Disciplina();

        disp1.setSangre_Robada(2);
        disp1.setCoste_Sangre(2);
        disp1.setNombre("Agujas de sangre");
        disp1.setAtk(2);
        disp1.setDef(0);

        vampy.setDisciplina(disp1);

        Assertions.assertTrue(vampy.getDisciplinas().size() == 1);
    }
}
