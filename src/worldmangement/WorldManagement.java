package worldmangement;

import Utilities.SUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Signifies on 8/2/2019 - 16:30.
 */
public class WorldManagement extends SUtils {


    public void test () {

        /**
         * These are the same values processed by the bukkit.yml. Idk if It helps
         * doing it ourselves or just using that. gotta test.
         */
        World world = Bukkit.getServer().getWorld("world"); //BC world
        world.setMonsterSpawnLimit(5);
        world.setTicksPerMonsterSpawns(5);
        Player player;


    }


    /**
     * Get all of the online players -> determine what is half of those are in beds.
     */
    public void BfiftyPercentSleeping() {
        //ArrayList<UUID> user = new ArrayList<>();
        int count = 0;
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.isSleeping() && p.getWorld().getName().equalsIgnoreCase("world")/*Might remove this */) {
                count++;
                log("Current count: " +count,1);
            }
            int result = count/2;
            if(result >=2) {
                //setweather cycle clear
                //set daytime true.
                p.getWorld().setWeatherDuration(0);
                p.getWorld().setTime(0);
            }
        }

    }

}
