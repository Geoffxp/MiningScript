package Tasks;

import Scripts.Main;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class BankTask extends Task {


     //We only want to bank if our inventory is full, or if the bank is already open.
     //If we don't check if the bank is open, this task won't be ran as soon as we
     //deposit a single item.
     //Remember, when this function returns true, the execute function below
    //will be ran.
    @Override
    public boolean validate() {
        return Inventory.isFull() || Bank.isOpen();
    }

    @Override
    public int execute() {
        //Makes the player run if our energy is 25-50(random every time it's checked),
        //and we are currently not running
        if(Movement.getRunEnergy() > Random.nextInt(25,50) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
        }
        //Makes the player run if his health bar is visible no matter what
        //i.e. when we are hit
        if(Players.getLocal().isHealthBarVisible()) {
            Movement.toggleRun(true);
        }

        Bank.open();
        if (Bank.isOpen()) {
            //Deposit everything in our inventory besides any pickaxe
            Bank.depositAllExcept(1265, 1267, 1269, 1271, 1273, 1275, 11920);
            //This is when we toggle which ore we are mining.
            Main.toggleOre();
            //Make sure you close the bank lol
            Bank.close();
        }

        return Random.nextInt(1000, 2000);
    }
}
