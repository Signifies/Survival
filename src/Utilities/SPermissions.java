package Utilities;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/**
 * Created by ES359 on 11/16/16.
 */
public enum  SPermissions
{

    SURVIVAL_ACCESS("Survival.accesst"),

    SURVIVAL_BYPASS_CHAT("Survival.bypass.chat"),
    SURVIVAL_BYPASS_PLACE("Survival.bypass.place"),
    SURVIVAL_BYPASS_BREAK("Survival.bypass.break"),
    SURVIVAL_BYPASS_INTERACT("Survival.bypass.interact"),
    SURVIVAL_BYPASS_DROP("Survival.bypass.drop"),
    SURVIVAL_BYPASS_PICKUP("Survival.bypass.pickup"),
    COMMAND_BYPASS("Survival.bypass"),
    SURVIVAL_CHAT_CLEARSELF("Survival.chat.clearself"),
    SURVIVAL_CHAT_CLEAR_OTHERS("Survival.chat.clearothers"),
    SURVIVAL_CHAT("Survival.chat"),
    SURVIVAL_CHAT_CLEAR("Survival.chat.clear"),
    SURVIVAL_MESSAGE_COMMAND("Survival.message"),
    SURVIVAL_COMMAND_KICK("Survival.kick"),
    SURVIVAL_BYPASS_TPTOGGLE("Survival.bypass.toggle"),
    COMMAND_BYPASS_TOGGLE("Survival.bypass.toggle"),
    SURVIVAL_COMMAND_WHITELIST("Survival.whitelist"),
    SURVIVAL_BYPASS_STATUS("Survival.status"),
    SURVIVAL_COMMAND_TP("Survival.tp"),
    SURVIVAL_COMMAND_TPHERE("Survival.tphere"),
    SURVIVAL_MANAGEMENT_LIST("Survival.list"),
    SURVIVAL_MANGEMENT("Survival.manger"),
    SURVIVAL_COMMAND_WHITELIST_NOTIFY("Survival.whitelist.notify"),
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
