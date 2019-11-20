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
        if(Skills.getLevel(Skill.MINING) >= 30) {
            return Location.coal_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
        }
        if(Skills.getLevel(Skill.MINING) >= 15 ) {
            return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
        }
        return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull() && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating();
    }

    @Override
    public int execute() {
        if (Skills.getLevel(Skill.MINING) >= 30 && Skills.getLevel(Skill.MINING) < 55) {
            //SceneObject coal = SceneObjects.getNearest();
            //coal.interact("Mine");
        }
        if (Skills.getLevel(Skill.MINING) >= 15 && Skills.getLevel(Skill.MINING) < 30) {
            SceneObject iron = SceneObjects.getNearest(11365, 11364);
            iron.interact("Mine");
        }
        if (Skills.getLevel(Skill.MINING) < 15) {
            SceneObject copper = SceneObjects.getNearest(10943, 11161);
            SceneObject tin = SceneObjects.getNearest(11360, 11361);
            if (Main.oreToggle) {
                copper.interact("Mine");
            } else {
                tin.interact("Mine");
            }
        }
        return Random.nextInt(400,800);
    }
}
