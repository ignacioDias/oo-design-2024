import org.junit.jupiter.api.Test;
import videogame.weapon.*;
import videogame.gem.*;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GemTest {
    @Test
    public void weaponWithNoSlotsProducesAnError() {
        Weapon sword = new Sword();
        sword = new WaterGem(sword);
        sword = new WaterGem(sword);
        sword = new WaterGem(sword);

        Weapon finalSword = sword;
        assertThrows(IllegalArgumentException.class, () -> {
            new WaterGem(finalSword);
        });
    }
    @Test
    public void weaponWithGemShouldDoMoreDamage() {
        Weapon axe = new Axe();
        Weapon axeNoGem = new Axe();
        axe = new WaterGem(axe);
        assertTrue(axe.getDamage() > axeNoGem.getDamage());
    }

}
