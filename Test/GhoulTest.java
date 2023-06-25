import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhoulTest {
    @Test
    void getSetDepenedencia(){
        Ghoul gh = new Ghoul();

        gh.setNiv_Dependencia(5);
        Assertions.assertTrue(5 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(4);
        Assertions.assertTrue(4 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(3);
        Assertions.assertTrue(3 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(2);
        Assertions.assertTrue(2 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(1);
        Assertions.assertTrue(1 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(0);
        Assertions.assertTrue(0 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(-1);
        Assertions.assertTrue(0 == gh.getNiv_Dependencia());

        gh.setNiv_Dependencia(6);
        Assertions.assertTrue(0 == gh.getNiv_Dependencia());

    }



}