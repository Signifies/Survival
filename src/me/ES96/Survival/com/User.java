package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.Rank;
import Utilities.SUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Evan on 5/12/2017.
 */
public class User extends SUtils
{

    /**
     "Great snippit can u maybe do one on how to add perms to these ranks?"

     That could be done with a simple HashMap of the Rank and List of permissions for that rank.
     Simply get the user rank then check if the list contains the permission you're checking for.
     Alternatively, you can incorporate a numbered system where each rank has a corresponding number of power. Then each thing you want to allow or disallow has a minimum power required.
     */

    private static Survival instance;
    private static HashMap<Rank,StringList> permissions = new HashMap<>();

    public User(Survival var)
    {
        instance = var;
    }

    //TODO Check static compatibility with userMethods.
    //TODO Just incase Permission System fails we can implement an option to use regular permissions.

    //TODO VERY IMPORTANT. SOME PLUGINS RELY ON SENDER ONLY WHICH MEANS NO UUID!!!
    //TODO Meaning we MUST properly implement the UserManagement class from Build with the tested
    //TODO SOFTWARE from Survival. That way 2 methods different paremeters one being player other being sender
    //TODO Getting UUID from the players name in the stored file.

    public static void setRank(Player p, Rank rank) //TODO This will be checked on login or perhaps join...
    {

        if (instance.getUserData().getUUIDConfig().getConfigurationSection("Users").contains(p.getUniqueId().toString())) {
            Debug.log("User already found! Skipping RankSet!");
        } else {
            Debug.log(Debug.pluginLog() + "Adding the user to file. Data: " + p.getUniqueId() + " with the rank: " + rank.toString());
            instance.getUserData().getUUIDConfig().set("Users." + p.getUniqueId() + ".RANK", rank.toString());
            instance.getUserData().getUUIDConfig().set("Users."+p.getUniqueId().toString()+ ".PREFIX","");
            //UserData set Tptoggle value
            instance.getUserData().saveUUIDConfig();

        }
    }


    //Might need this one to be static to access in Rank.java...
    public static Rank getRank(Player player)
    {
        String data = instance.getUserData().getUUIDConfig().getString("Users." +player.getUniqueId() +".RANK");
        String statement = (data == null ? Rank.GUEST : Rank.valueOf(data)).toString();  //TODO removed once I'm satisfied that there are no issues/bugs and logic is correct.
        Debug.log(Debug.pluginLog()+"Logging logic data for #getRank() method. Data: "+ statement);
        return (data == null ? Rank.GUEST : Rank.valueOf(data));
        //return Rank.valueOf(data);
    }

    public static boolean hasRank(Player p, Rank hasRank) //TODO Check to see if hasRank should be first.
    {
        Debug.log(Debug.pluginLog()+"Logging Data for #hasRank() method.");
        Rank currentRank = getRank(p);
        Debug.log(Debug.pluginLog()+"Player's current Rank: "+currentRank);
        boolean statement = (getRank(p).compareTo(hasRank) >=0); //TODO removed once I'm satisfied that there are no issues/bugs and logic is correct.
        Debug.log(Debug.pluginLog()+"final value of #hasRank() : "+ statement);

        return statement; //I get it now
        //That number is checking to see if it even has a value at this point
        //Meaning we might have to still implement our second version
    }


    /**
     * Logic for permissions check
     *
     * A player wants to run the /tp command.
     *
     * We begin with the typical command structure and then add our check which is as follows.
     *
     * Using a method with an undetermined name with the parameters Player and Rank.
     *
     *  public boolean hasRank(Player p, Rank hasRank)
        {
        return (getRank(p).compareTo(hasRank) >= hasRank.getPriority()); //Idea 2, needs testing.
        }
    /**
     *
     * @param p Player. Checking the users request and their authentication.
     * @param required Comparing the users request to see if they have proper Authentication.
     * @return
     */
    public static boolean isPermissible(Player p, Rank required)
    {
        boolean test = hasRank(p,required);  //TODO removed once I'm satisfied that there are no issues/bugs and logic is correct.
        String result = test ? p.getName()+" has permission for this function!" : p.getName()+" does not have permission for this function!";
        Debug.log(Debug.pluginLog()+"Debugging the #isPermissible() method. " +result);
        Debug.log(Debug.pluginLog()+"Final value: "+test);
        return test;
    }


    /**
     *
     * @param rank
     * @param perm
     * @return
     */
    public static boolean isPermissible(Rank rank, StringList perm)
    {
        permissions.put(rank,perm);

        boolean result;
        if(permissions.containsKey(perm))
        {
            result = true;
        }else
        {
            result = false;
        }

        return result;
    }

    public void writeUser(Player p)
    {
           instance.getPerms().getPermissions().set("User-data.",p.getUniqueId());
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ ".uuid", p.getUniqueId());
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ ".name", p.getName());
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ ".rank", "GUEST");
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ ".ip", p.getAddress());
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ ".time-created", getStamp());
           instance.getPerms().getPermissions().set("User-data." + p.getUniqueId()+ "name-color", "&a");
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ "chat-color", "&a");
//        instance.getPerms().getPermissions().set("User-data."+p.getUniqueId()+ "");
           instance.getPerms().savePermissions();
           log(Debug.pluginLog() + "&a&lThe world, &n" + p.getWorld().getName() + "&a&l has been added.");
//           p.sendMessage(color( "&a&lThe player, &n" + p.getName() + "&a&l has been added."));


    }
    public static boolean hasCustomPrefix(UUID uuid)
    {
    String prefix= instance.getUserData().getUUIDConfig().getString("Users."+ uuid.toString()+".PREFIX");

    return prefix != null;
    }

    public static String getRankPrefix(Player player)
    {
        Rank rank = User.getRank(player);
        String prefix = rank.getPrefix();
        return rank.hasPrefix() ? prefix : "";
    }

    public static String getCustomPrefix(Player player)
    {
        String prefix= instance.getUserData().getUUIDConfig().getString("Users."+ player.getUniqueId().toString()+".PREFIX");
        return prefix;
    }




    public User getData()
    {
        return null;
    }

}
