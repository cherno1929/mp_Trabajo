import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonTest {

    @Test
    void getSetUmbral() {
        Don d = new Don();

        Assertions.assertTrue(0 == d.getUmbral());

        d.setUmbral(2);

        Assertions.assertTrue(2 == d.getUmbral());

    }

    @Test
    void getSetRabia() {
        Don d = new Don();

        Assertions.assertTrue(0 == d.getRabia_Otorgada());

        d.setRabia_Otorgada(2);

        Assertions.assertTrue(2 == d.getRabia_Otorgada());

    }

}