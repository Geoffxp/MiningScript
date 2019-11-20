package Scripts;

import Data.Location;
import Tasks.BankTask;
import Tasks.MiningTask;
import Tasks.Traverse;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "MiningScript", desc = "My first script!", developer = "Reph3x", category = ScriptCategory.MINING)
public class Main extends TaskScript {

	//Toggling this boolean is what switches between copper and tin
    public static boolean oreToggle = true;

    public static void main(String[] args) {

    }

    @Override
    public void onStart() {
        //Submit the tasks. Tasks on top have higher priority than tasks on bottom.
        //If a tasks' validate function returns true, that tasks execute function will be
        //ran on a loop while the validate function is still true.
        //If a validate function is false, it moves on the the next task.
        //If the final tasks' validate function is false, it tries the first again, so on.
        //
        //Top priority is banking. If our inventory is full, bank. no exceptions.
        //Traverse next, If we are not at a mine area and our inventory isn't full,
        //we are running to a mine.
        //For mining, if we are at a mine and our inventory isn't full, we mining.
        submit(
            new BankTask(),
            new Traverse(),
            new MiningTask()
        );
    }

    @Override
    public void onStop() {

    }

	//Hamdy function to easily flip the copper/tin ore toggle
    public static void toggleOre() {
        oreToggle = !oreToggle;
    }
}
