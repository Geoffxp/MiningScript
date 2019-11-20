package Tasks;

import Data.Location;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Traverse extends Task {

    //I made these locations their own variables to try and make my
    //code neater and it didn't really work
    private Location copper_tin_iron_mine = Location.copper_tin_iron_mine;
    private Location coal_mine = Location.coal_mine;

    @Override
    public boolean validate() {
        //If our mining skill is 30 or more, we want to be running to the coal mine
        //when we are not at the coal mine, and while our inventory isn't full.
        if (Skills.getLevel(Skill.MINING) >= 30) {
            return !coal_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull();
        } else {
            //if our level is less than 30 then we want to run to the
            //copper/tin/iron mine if we are not there already.
            return !copper_tin_iron_mine.getArea().contains(Players.getLocal().getPosition()) && !Inventory.isFull();
        }
    }

    @Override
    public int execute() {
        //same thing as in the bank task.
        if(Movement.getRunEnergy() > Random.nextInt(25,50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        //same thing as the bank task.
        if(Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }
        //If our mining skill is 30 or more then we want to move to the coal
        //mine. I think I have to expand this to actually interact with
        //the stairs in falador to get to the mine.
        if (Skills.getLevel(Skill.MINING) >= 30) {
            //We get the coal mine area that we saved above from the enum
            //And get an array of every tile in it (each spot you can be in is
            //its own little square, each square is a tile). We then get a random
            //tile from the array using the random function which is set to
            //0 and the length of the array of tiles minus 1. Java arrays
            //start at 0, so for example, the final index of an array of length 4 is 3.
            //{0,1,2,3} is length 4. get it?
            Movement.walkTo(coal_mine.getArea().getTiles().get(Random.nextInt(0, coal_mine.getArea().getTiles().size()-1)));
        } else {
            Movement.walkTo(copper_tin_iron_mine.getArea().getTiles().get(Random.nextInt(0, copper_tin_iron_mine.getArea().getTiles().size() - 1)));
        }
        return Random.nextInt(1000,2000);
    }
}
