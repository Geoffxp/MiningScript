package Tasks;

import Data.Location;
import Scripts.Main;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Traverse extends Task {

    private Location copper_tin_iron_mine = Location.copper_tin_iron_mine;
    private Location coal_mine = Location.coal_mine;

    @Override
    public boolean validate() {
        if (Skills.getLevel(Skill.MINING) >= 30) {
            return !coal_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull();
        } else {
            return !copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull();
        }
    }

    @Override
    public int execute() {

        Main.updateTime();

        if(Movement.getRunEnergy() > Random.nextInt(25,50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        if(Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }
        if(Skills.getLevel(Skill.MINING) >= 30) {
            Movement.walkTo(coal_mine.getArea().getTiles().get(Random.nextInt(0, coal_mine.getArea().getTiles().size()-1)));
        } else {
            Movement.walkTo(copper_tin_iron_mine.getArea().getTiles().get(Random.nextInt(0, copper_tin_iron_mine.getArea().getTiles().size() - 1)));
        }
        return Random.high(1000,2000);
    }
}
