package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.SUtils;
import org.bukkit.entity.Player;

/**
 * Created by Evan on 5/12/2017.
 */
public class User extends SUtils
{

    private Survival instance;

    public User(Survival var)
    {
        instance = var;
    }

    public void writeUser(Player p)
    {
       if(instance.getPerms().getPermissions().getConfigurationSection("User-data").contains(p.getUniqueId().toString()))
       {
           log("&4Player exists already!");
       }else
       {
           instance.getPerms().getPermissions().set("User-data."+p.getUniqueId(),p.getUniqueId());
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

    }

    public void deleteUser(Player p)
    {


    }




    public User getData()
    {
        return null;
    }

}
