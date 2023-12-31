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
import edu.graffwhitley.ripdash.objects.CharacterTransformer;
import edu.graffwhitley.ripdash.objects.background.ChainBgObj;
import edu.graffwhitley.ripdash.objects.background.LampPostBgObj;
import edu.graffwhitley.ripdash.tiles.ground.GroundTile;
import edu.graffwhitley.ripdash.tiles.ground.HalfGroundTile;
import edu.graffwhitley.ripdash.tiles.special.CoinPickup;
import edu.graffwhitley.ripdash.tiles.special.JumpBoostTile;
import edu.graffwhitley.ripdash.tiles.special.JumpPadTile;
import edu.graffwhitley.ripdash.tiles.spikes.FlippedHalfSpikeTile;
import edu.graffwhitley.ripdash.tiles.spikes.FlippedSpikeTile;
import edu.graffwhitley.ripdash.tiles.spikes.HalfSpikeTile;
import edu.graffwhitley.ripdash.tiles.spikes.RandomSpikesTile;
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
                    nextObject = new CoinPickup(x, y);
                    break;
                case "HalfBlock":
                    nextObject = new HalfGroundTile(HalfGroundTile.HALF_SQUARE, x, y, false);
                    break;
                case "HalfBlockBottom":
                    nextObject = new HalfGroundTile(HalfGroundTile.HALF_SQUARE, x, y, true);
                    break;
                case "HalfSpike":
                    nextObject = new HalfSpikeTile(HalfSpikeTile.HALFSPIKE, x, y); 
                    break;
                case "JumpBoost":
                    nextObject = new JumpBoostTile(JumpBoostTile.JUMP_BOOST, x, y);
                    break;
                case "JumpPad":
                    nextObject = new JumpPadTile(JumpPadTile.JUMP_PAD, x, y);
                    break;
                case "RandomSpikes":
                    int randSprite = RandomSpikesTile.RANDOM_SPIKES_0;
                    switch ((int)(Math.random() * 4)) {
                        case 1:
                            randSprite = RandomSpikesTile.RANDOM_SPIKES_1;
                            break;
                        case 2:
                            randSprite = RandomSpikesTile.RANDOM_SPIKES_2;
                            break;
                        case 3:
                            randSprite = RandomSpikesTile.RANDOM_SPIKES_3;
                    }
                    nextObject = new RandomSpikesTile(randSprite, x, y);
                    break;
                case "Spike":
                    nextObject = new SpikeTile(SpikeTile.SPIKE, x, y);
                    break;
                case "Square":
                    nextObject = new GroundTile(GroundTile.SQUARE, x, y);
                    break;
                case "SliderTransformer":
                    nextObject = new CharacterTransformer("SliderCharacter", x, y);
                    break;
                case "ShipTransformer":
                    nextObject = new CharacterTransformer("ShipCharacter", x, y);
                    break;
                case "Chain":
                    nextObject = new ChainBgObj(x, y);
                    break;
                case "LampPost":
                    nextObject = new LampPostBgObj(x, y);
                    break;
                case "CharacterSpawner":
                    nextObject = new SliderCharacter(x, y);
                    break;
                case "CameraFocus":
                    nextObject = new CameraFocus(x, y);
                    break;
                case "FlippedHalfSpike":
                    nextObject = new FlippedHalfSpikeTile(FlippedHalfSpikeTile.HALFFLIPPEDSPIKE, x, y); 
                    break; 
                case "FlippedSpike":
                    nextObject = new FlippedSpikeTile(FlippedSpikeTile.FLIPPEDSPIKE, x, y);
                    break;
                case "FinishLine":
                    level.winX = x;
                    break;
                
            }
            if (nextObject != null) {
                level.addObject(nextObject);
                nextObject.createBody(world);
            }
        }

        level.addObject(new LevelGround(world, -24));
        level.addObject(new LevelGround(world, 24));

        CharacterType player = new SliderCharacter(0, -28);
		player.createBody(world);
		level.addObject(player);

        return level;
    }

    public static Level readLevel(String directoryPath, World world) {
        String levelJSON = readLevelFile(directoryPath);
        return parseToObjects(levelJSON, world);
    }
}
