package Utilities;

import me.ES96.Survival.com.Survival;
import org.bukkit.ChatColor;
public enum Rank
{
    //Possible idea, use for loop to instatiate multiple enums from a string list???
    //List of parameters....
    //TODO: PREFIX, Permissions (StringList & can this work via config??), Priority for Inheritance...

    /**
     * <>mainClass.</>.getPerms().getPermissions().getString("");
     * Get Perms is the Rank Data file where basic information about a rank is stored.
     * Might change this over to use custom message/color system.
     */
    GUEST(Survival.getPerms().getPermissions().getString("Ranks.GUEST.prefix"),1),
    MOD(Survival.getPerms().getPermissions().getString("Ranks.MOD.prefix"),5),
    ADMIN(Survival.getPerms().getPermissions().getString("Ranks.ADMIN.prefix"),7),
    DEV(Survival.getPerms().getPermissions().getString("Ranks.DEV.prefix"),8),
    OWNER(Survival.getPerms().getPermissions().getString("Ranks.OWNER.prefix"),9);

    private final int priority;
    private final String prefix;

    Rank(String prefix,int priority)
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

}
 


