
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.logic.Driller;
import com.mygdx.game.logic.GasStation;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.PlayState;
import com.mygdx.game.logic.TradeCenter;
import com.mygdx.game.logic.WorldContactListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Rita on 06/06/2016.
 */
@RunWith(GdxTestRunner.class)
public class tests
{

   @Test
    public void createPlayState()
    {
        PlayState p = new PlayState();
        assertEquals(p.getScore(),0);
    }

    @Test
    public void testFileExists()
    {
        assertTrue(Gdx.files.internal("motherload_sprites/ground_right.png").exists());
    }

}
