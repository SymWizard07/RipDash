package edu.graffwhitley.ripdash;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.graffwhitley.CameraFocus;
import edu.graffwhitley.ripdash.character.CharacterType;
import edu.graffwhitley.ripdash.character.SliderCharacter;
import edu.graffwhitley.ripdash.tiles.ground.GroundTile;
import edu.graffwhitley.ripdash.tiles.ground.HalfGroundTile;
import edu.graffwhitley.ripdash.tiles.spikes.SpikeTile;

public class LevelLoader {

    private LevelLoader() {}

    private static String readLevelFile(String directoryPath) {
        return Gdx.files.internal(directoryPath).readString();
    }

    private static Level parseToObjects(String levelJsonString, World world) {
        Level level = new Level();
        Gson gson = new Gson();

        // Don't ask
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
        List<Map<String, Object>> list = gson.fromJson(levelJsonString, type);

        LevelObject nextObject;

        for (Map<String, Object> item : list) {
            nextObject = null;
            float x = ((Double)item.get("x")).floatValue() * 2.0f;
            float y = ((Double)item.get("y")).floatValue() * -2.0f + 3;

            switch ((String)item.get("objectName")) {
                case "Ground":
                    nextObject = new GroundTile(GroundTile.BRICK, x, y);
                    break;
                case "Coin":
                    //nextObject = new CoinPickup();
                    break;
                case "HalfBlock":
                    nextObject = new HalfGroundTile(HalfGroundTile.HALF_SQUARE, x, y, false);
                    break;
                case "HalfBlockBottom":
                    nextObject = new HalfGroundTile(HalfGroundTile.HALF_SQUARE, x, y, true);
                    break;
                case "HalfSpike":
                    //nextObject = new HalfSpikeTile();
                    break;
                case "JumpBoost":
                    //nextObject = new JumpBoostTile();
                    break;
                case "JumpPad":
                    //nextObject = new JumpPadTile();
                    break;
                case "RandomSpikes":
                    //nextObject = new RandomSpikesTile();
                    break;
                case "Spike":
                    nextObject = new SpikeTile(SpikeTile.SPIKE, x, y);
                    break;
                case "Square":
                    nextObject = new GroundTile(GroundTile.SQUARE, x, y);
                    break;
                case "Tunnel":
                    //nextObject = new CharacterTransformer();
                    break;
                case "Chain":
                    //nextObject = new ChainBgTile();
                    break;
                case "LampPost":
                    //nextObject = new LampPostBgTile();
                    break;
                case "CharacterSpawner":
                    nextObject = new SliderCharacter(x, y);
                    break;
                case "CameraFocus":
                    nextObject = new CameraFocus(x, y);
                case "FlippedHalfSpike":
                    nextObject = new SpikeTile(SpikeTile.FLIPPEDHALFSPIKE, x, y); 
                case "FlippedSpike":
                    nextObject = new SpikeTile(SpikeTile.FLIPPEDSPIKE, x, y);
                
            }
            if (nextObject != null) {
                level.addObject(nextObject);
                nextObject.createBody(world);
            }
        }

        level.addObject(new LevelGround(world, -24));
        level.addObject(new LevelGround(world, 24));

        CharacterType player = new SliderCharacter(-300, -28);
		player.createBody(world);
		level.addObject(player);

        return level;
    }

    public static Level readLevel(String directoryPath, World world) {
        String levelJSON = readLevelFile(directoryPath);
        return parseToObjects(levelJSON, world);
    }
}
