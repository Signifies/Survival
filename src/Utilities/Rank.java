package Utilities;

import me.ES96.Survival.com.Survival;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public enum Rank
{
    //Possible idea, use for loop to instatiate multiple enums from a string list???
    //List of parameters....
    //TODO: PREFIX, Permissions (StringList & can this work via config??), Priority for Inheritance...


    GUEST(ChatColor.translateAlternateColorCodes('&',""),0),
    MEMBER(ChatColor.translateAlternateColorCodes('&',""),1),
    BUILDER(ChatColor.translateAlternateColorCodes('&',""),2),
    MOD(ChatColor.translateAlternateColorCodes('&',""),3),
    SMOD(ChatColor.translateAlternateColorCodes('&',""),4),
    ADMIN(ChatColor.translateAlternateColorCodes('&',""),5),
    DEV(ChatColor.translateAlternateColorCodes('&',""),6);

    private final int priority;
    private final String prefix;

    Rank(String prefix,int priority) //Instatiated inside main class as a new object with config placeholders added.
    {
//        this.id = id;
        this.prefix = prefix;
        this.priority = priority;

    }

    public boolean hasPrefix()
    {
        return prefix != null;
    }

    public int getPriority()
    {
        return priority;
    }

    public String getPrefix()
    {
        return prefix;
    }

    //TODO??? Do we want to access User user? instead? We'll try both methods.
    //Add player and we can make this logic right here??
    public boolean isPermissible(Rank required)
    {
        return priority <= required.priority;
    }

    public void setRank(Player p, Rank rank)
    {
        //Config instance??? probably move this to different class such as usermanagement....
    }

//    public boolean hasPermission(Rank required)
//    {
//        return rank.isPermissible(required);
//    }

}
 


