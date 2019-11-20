package Tasks;

import Data.Location;
import Scripts.Main;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class MiningTask extends Task {

    @Override
    public boolean validate() {
        //If the players mining skill is 30 or more, then we want to be mining while
        //we are in the mining area (specifically if the mining area contains the
        //player). We also only want to try to mine while the player is already
        //not moving, not mining already, and if our inventpry isn't full.
        if(Skills.getLevel(Skill.MINING) >= 30) {
            return Location.coal_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
        }
        //Same thing as above but for iron. We don't need to make sure that
        //our level is below 30 for mining iron because if it was, the if statement
        //above would be true and it gets returned. Return stops the code under
        //it from being ran (in the same function). Like a break.
        if(Skills.getLevel(Skill.MINING) >= 15 ) {
            return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
        }
        //If our mining skill is lower than 30 and 15, we just gonna mine some
        //copper and tin. Iron is in the same location as copper and tin so I
        //don't really need to check for level 15 but I did anyway.. whoops.
        return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
    }

    @Override
    public int execute() {
        //Coal isn't functioning rn btw, and I'm pretty sure this script breaks at
        //level 30 rn.
        //We HAVE to check the upper limits of the mining skill or else every
        //if statement would pass and it would try to mine every ore all at once.
        //You can get around this with nested if statements but I think thats
        //cumbersome and ugly.
        if (Skills.getLevel(Skill.MINING) >= 30 && Skills.getLevel(Skill.MINING) < 55) {
            //SceneObject coal = SceneObjects.getNearest();
            //coal.interact("Mine");
        }
        if (Skills.getLevel(Skill.MINING) >= 15 && Skills.getLevel(Skill.MINING) < 30) {
            //Each ore has two different sceneobject ids for some reason.
            SceneObject iron = SceneObjects.getNearest(11365, 11364);
            iron.interact("Mine");
        }
        if (Skills.getLevel(Skill.MINING) < 15) {
            //We find both the ckosest copper and closest tin
            SceneObject copper = SceneObjects.getNearest(10943, 11161);
            SceneObject tin = SceneObjects.getNearest(11360, 11361);
            //And only mine the copper or the tin, depending on the oreToggle
            //in the main class. The oreToggle boolean only toggles after
            //every bank run. It gets set to true on script start so it will
            //always mine copper first.
            if (Main.oreToggle) {
                copper.interact("Mine");
            } else {
                tin.interact("Mine");
            }
        }
        return Random.nextInt(400,800);
    }
}
