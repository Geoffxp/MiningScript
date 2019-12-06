package Tasks;

import Scripts.Main;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class CurrentlyMiningTask extends Task {

    private static final int[] SPENT_ORE = {11390, 11391};
    private static final int MINING_ANIMATION = 624;

    @Override
    public boolean validate() {
        return Players.getLocal().isAnimating()
                && Players.getLocal().getAnimation() == MINING_ANIMATION;
    }

    @Override
    public int execute() {

        Main.updateTime();

        SceneObject spentOre = SceneObjects.getNearest(SPENT_ORE);

        if (Skills.getCurrentLevel(Skill.MINING) >= 30) {
            SceneObject newCoal = SceneObjects.getNearest(MiningTask.COAL);
            boolean isCoalTaken = false;
            try {
                if (spentOre.getPosition().equals(MiningTask.currentCoal.getPosition())) {
                    isCoalTaken = true;
                }
            }
            catch(NullPointerException e) {}
            try {
                if (isCoalTaken) {
                    Log.fine("Coal taken. Trying a new node.");
                    Movement.walkTo(Players.getLocal().getPosition());
                    newCoal.interact("Mine");
                    MiningTask.setCurrentCoal(newCoal);
                }
            }
            catch (NullPointerException e) {
                Log.fine("No coal detected.");
            }
        }

        if (Skills.getCurrentLevel(Skill.MINING) >= 15 && Skills.getCurrentLevel(Skill.MINING) < 30) {
            SceneObject newIron = SceneObjects.getNearest(MiningTask.IRON);
            boolean isIronTaken = false;
            try {
                if (spentOre.getPosition().equals(MiningTask.currentIron.getPosition())) {
                    isIronTaken = true;
                }
            }
            catch(NullPointerException e) {}
            try {
                if (isIronTaken) {
                    Log.fine("Iron taken. Trying a new node.");
                    Movement.walkTo(Players.getLocal().getPosition());
                    newIron.interact("Mine");
                    MiningTask.setCurrentIron(newIron);
                }
            }
            catch (NullPointerException e) {
                Log.fine("No iron detected.");
            }
        }

        if (Skills.getCurrentLevel(Skill.MINING) < 15) {
            if(Main.oreToggle) {
                SceneObject newCopper = SceneObjects.getNearest(MiningTask.COPPER);
                boolean isCopperTaken = false;
                try {
                    if (spentOre.getPosition().equals(MiningTask.currentCopper.getPosition())) {
                        isCopperTaken = true;
                    }
                }
                catch(NullPointerException e) {}
                try {
                    if (isCopperTaken) {
                        Log.fine("Copper taken. Trying a new node.");
                        Movement.walkTo(Players.getLocal().getPosition());
                        newCopper.interact("Mine");
                        MiningTask.setCurrentCopper(newCopper);
                    }
                }
                catch (NullPointerException e) {
                    Log.fine("No copper detected.");
                }
            }
            else {
                SceneObject newTin = SceneObjects.getNearest(MiningTask.TIN);
                boolean isTinTaken = false;
                try {
                    if (spentOre.getPosition().equals(MiningTask.currentTin.getPosition())) {
                        isTinTaken = true;
                    }
                }
                catch(NullPointerException e) {}
                try {
                    if (isTinTaken) {
                        Log.fine("Tin taken. Trying a new node.");
                        Movement.walkTo(Players.getLocal().getPosition());
                        newTin.interact("Mine");
                        MiningTask.setCurrentTin(newTin);
                    }
                }
                catch (NullPointerException e) {
                    Log.fine("No tin detected.");
                }
            }
        }

        return Random.high(800,1400);
    }
}
