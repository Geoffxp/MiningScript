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
import org.rspeer.ui.Log;

public class MiningTask extends Task {

    public static final int[] COAL = {11366, 11367};
    public static final int[] IRON = {11365, 11364};
    public static final int[] COPPER = {10943, 11161};
    public static final int[] TIN = {11360, 11361};

    public static SceneObject currentCoal;
    public static SceneObject currentIron;
    public static SceneObject currentCopper;
    public static SceneObject currentTin;

    @Override
    public boolean validate() {
        if(Skills.getLevel(Skill.MINING) >= 30) {
            return Location.coal_mine.getArea().contains(Players.getLocal().getPosition())
                    && !Inventory.isFull()
                    && !Players.getLocal().isMoving()
                    && !Players.getLocal().isAnimating();
        }
        if(Skills.getLevel(Skill.MINING) >= 15 ) {
            return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition())
                    && !Inventory.isFull()
                    && !Players.getLocal().isMoving()
                    && !Players.getLocal().isAnimating();
        }
        return Location.copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition())
                && !Inventory.isFull()
                && !Players.getLocal().isMoving()
                && !Players.getLocal().isAnimating();
    }

    @Override
    public int execute() {

        Main.updateTime();
        Main.updateOreCount();

        if (Random.nextInt(1,100) <= Random.nextInt(1,5)) {
            int wait = Random.high(10000, 25000);
            Log.fine("Random afk time initiated. "+wait/1000+" seconds.");
            return wait;
        }

        if (Skills.getLevel(Skill.MINING) >= 30 /*&& Skills.getLevel(Skill.MINING) < 55*/) {
            currentCoal = SceneObjects.getNearest(COAL);
            try {
                currentCoal.interact("Mine");
            }
            catch(NullPointerException e) {
                Log.fine("No coal detected.");
            }
        }
        if (Skills.getLevel(Skill.MINING) >= 15 && Skills.getLevel(Skill.MINING) < 30) {
            currentIron = SceneObjects.getNearest(IRON);
            try {
                currentIron.interact("Mine");
            }
            catch (NullPointerException e) {
                Log.fine("No iron detected.");
            }
        }
        if (Skills.getLevel(Skill.MINING) < 15) {
            currentCopper = SceneObjects.getNearest(COPPER);
            currentTin = SceneObjects.getNearest(TIN);
            if (Main.oreToggle) {
                try {
                    currentCopper.interact("Mine");
                }
                catch (NullPointerException e) {
                    Log.fine("No copper detected.");
                }
            } else {
                try {
                    currentTin.interact("Mine");
                }
                catch (NullPointerException e) {
                    Log.fine("No tin detected.");
                }
            }
        }
        return Random.high(800,1400);
    }

    public static void setCurrentCoal(SceneObject coal) {
        currentCoal = coal;
    }
    public static void setCurrentIron(SceneObject iron) {
        currentIron = iron;
    }
    public static void setCurrentCopper(SceneObject copper) {
        currentCopper = copper;
    }
    public static void setCurrentTin(SceneObject tin) {
        currentTin = tin;
    }
}
