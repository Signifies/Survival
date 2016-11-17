package Utilities;

import me.ES96.Survival.com.Survival;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 11/16/16.
 */
public enum  SPermissions
{

    BUILD_BYPASS_CHAT("Build.bypass.chat"),
    BUILD_BYPASS_PLACE("Build.bypass.place"),
    BUILD_BYPASS_BREAK("Build.bypass.break"),
    BUILD_BYPASS_INTERACT("Build.bypass.interact"),
    BUILD_BYPASS_DROP("Build.bypass.drop"),
    BUILD_BYPASS_PICKUP("Build.bypass.pickup"),
    BUILD_BYPASS_TPTOGGLE("Build.bypass.toggle"),
    BUILD_BYPASS_STATUS("Build.status"),
    BUILD_MANGEMENT("Build.manger"),

    PLUGIN_DONATOR_2 ("plugin.donator.2");



    private String key;

    SPermissions(String key) {
        this.key = key;
    }

    public boolean checkPermission(Player p){
        System.out.println(check(p));
        return p.hasPermission(getKey());
    }

    public boolean checkPermission(CommandSender sender)
    {
        System.out.println(check(sender));
        return sender.hasPermission(getKey());
    }

    public String getKey() {
        return key;
    }



    public String check(Player p)
    {
        String s = p.hasPermission(getKey()) ? "" : SUtils.prefix +"" + ChatColor.RED +"Error, " + p.getName() + " does not have the permission: " + getKey();

        return s;
    }
    public String check(CommandSender p)
    {
        String s = p.hasPermission(getKey()) ? "" : SUtils.prefix +"" + ChatColor.RED +"Error, " + p.getName() + " does not have the permission: " + getKey();
        return  s;
    }


}
