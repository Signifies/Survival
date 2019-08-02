package Utilities;

import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by ES359 on 11/16/16.
 */
public class SUtils
{
    /**
     * Plugin prefix.
     */
    public static String  prefix = ChatColor.translateAlternateColorCodes('&',"&2Survival> &7->");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    private String permission = color("&cUnknown command. Type \"/help\" for help.");

    private Notifications staff = new Notifications(Action.NOTIFY_ADMIN.getMessage(), false,true);


    public boolean checkAuthor(UUID uuid)
    {
        return uuid.toString().equals(author);
    }



    String[] tag = {color("&7GUEST&r"),color("&9MOD&r"),color("&cADMIN&r"),color("&aDEV&r")};


    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public static String getPrefix()
    {
        return prefix;
    }

    public String check(boolean value, String name)
    {
        return  value ? name +ChatColor.GREEN+" [Enabled]"  : name + ChatColor.RED +" [Disabled]";
    }

    public String msg(String configPath, boolean removeColor, String[] values)
    {
        String result = configPath;
        if(result !=null)
        {
            if(removeColor)
            {
                result = color(result);
            }
            for(int k=0; k < values.length; k++)
            {
                String replaced = (values[k] != null) ? values[k] : "NULL";
                result = result.replace("{"+ k +"}",replaced);
            }
            return removeColor ? result : color(result);
        }
        log("Error finding a message!",1);
        return null;
    }

    public String msg(String path, String... replaced)
    {
        return msg(path,false,replaced);
    }

    public String msg(String path)
    {
        return msg(path,false,new String[0]);
    }



    /**
     * TAGS: {0}:name {1}:world {2}:rank {1}:message {3}:admin
     * @param admin The user that triggers the message.
     * @param notification Notification we need to broadcast.
     */
    public void adminNotifications(String admin, String notification)
    {
        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            String format = msg(staff.getBroadcastPrefix(),player.getName(),player.getWorld().getName(),notification, admin);
            if(User.isPermissible(player,Rank.MOD) && !User.evaluateNotificationSettings(player.getUniqueId()))
            {
                Bukkit.getServer().getConsoleSender().sendMessage(format);
                player.sendMessage(format);
            }
        }
    }

    /*

    **
     * //TODO make sure to update tag system to suport internal functions.
     * TAGS: {0}:name {1}:world {2}:rank {3}:message {4}:admin
     * @param admin The user that triggers the message.
     * @param notification Notification we need to broadcast.
     **/

    public void adminNotifications(String admin, String notification, Rank perms)
    {
        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            String format = msg(staff.getBroadcastPrefix(),notification, admin);
            if(perms.getPriority() >= User.getRank(player).getPriority())
            {
                Bukkit.getServer().getConsoleSender().sendMessage(format);
                player.sendMessage(format);
            }
        }
    }

    public String defaultMessage(boolean value, String msg)
    {
        return value ? color(permission) : color(msg);
    }

    public void log(String msg, int priority) {
        if(Survival.DEBUG || priority > 0) {
            Bukkit.getServer().getConsoleSender().sendMessage(prefix + color("&f[&4LOG&f]&r &6" + msg));
        }
    }

    //TODO.md our get prefix method will be the replacement value inside our chat API.

    public void enforceWhitelist(CommandSender staff) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(!p.isWhitelisted() && !(User.isPermissible(p,Rank.MOD))) {
                p.kickPlayer(color("&4[!] Server whitelist has been enforced!"));
            }
        }
        staff.sendMessage(color("&7The whitelist has been &aenforced."));
    }
    public void enforceWhitelist(CommandSender staff, String msg) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(!p.isWhitelisted() && !(User.isPermissible(p, Rank.MOD))) {
                p.kickPlayer(color(msg));
            }
        }
        //TODO.md Notifications API here.
    }

    public ArrayList<String> commandList()
    {
        ArrayList<String> value = new ArrayList<String>();

        value.add("     &f----- &7Build Commands &f-----");
        value.add("&a/build &7<section> [true || false]");
        value.add("&a/build &7status");
        value.add("&a/build &7perms");
        value.add("&a/build &7about");
        value.add("&a/build &7reload");
        value.add("&a/build &7warps");
        value.add("");
        value.add("&a/build &7chat [true || false]");
        value.add("&a/build &7<itemdrops> [true || false]");
        value.add("&a/build &7<itempickup> [true || false]");
        return value;
    }

    public void sendText(ArrayList<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }
    public void sendText(ArrayList<String> text, Player sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }



    public void sendText(List<String> text, Player sender)
    {
        int amt = Bukkit.getServer().getOnlinePlayers().size();
        int max = Bukkit.getServer().getMaxPlayers();

        for(String txt: text)
        {
            txt = txt.replace("%online_players%", ""+amt);
            txt = txt.replace("%max_players%", ""+max);
            txt = txt.replace("%player%",sender.getName());
            txt = txt.replace("%uuid%",sender.getUniqueId().toString());
            txt = txt.replace("%display_name%",sender.getDisplayName());
            txt = txt.replace("%IP%", sender.getAddress().toString());
            txt = txt.replace("%time%",getStamp().toString());
            sender.sendMessage(color(txt));
        }
    }

    public void sendText(List<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    public void sendText(List<String> text, CommandSender sender, String s)
    {
        int amt = Bukkit.getServer().getOnlinePlayers().size();
        int max = Bukkit.getServer().getMaxPlayers();

        for(String txt: text)
        {
            txt = txt.replace("%online_players%", ""+amt);
            txt = txt.replace("%max_players%", ""+max);
            txt = txt.replace("%player%",sender.getName());
            txt = txt.replace("%staff%",getStaff(s));
            txt =txt.replace("%time%",getStamp().toString());
            sender.sendMessage(color(txt));
        }
    }

    public String getStaff(String format)
    {
//        Bukkit.getServer().getOnlinePlayers().forEach(s -> BuildPermissions.BUILD_MANGEMENT.checkPermission(s));
        StringBuilder sb = new StringBuilder();
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if(User.isPermissible(p,Rank.MOD))
            {
                sb.append(p.getName() + ", ");
                Survival.staff.add(p.getName());
            }
        }
        if(sb.length() < 1)
            return color(format);
            //return color("&cError: No staff members online. &b&o.-.");
        else
            return sb.toString();
    }

    /**
     *  Logging message to the console.
     *
     * @param msg
     */
    public void log(String msg)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(color("&c&l[LOG]&f " + msg));
    }

    public void clearPlayer(Player p)
    {
        for(int i=0; i < 100; i++)
        {
            p.sendMessage("");
        }
        p.sendMessage(color("&7Your chat has been &7&nCleared&c, by an Admin, &a&n" + p.getName()));
    }

    public void selfClear(CommandSender sender) {
        for(int i=0; i <100; i++) {
            sender.sendMessage("");
        }
        sender.sendMessage( ChatColor.GRAY + "You have cleared your own chat, "+ ChatColor.GREEN +sender.getName());
    }

    public void clear() {
        for(Player p :Bukkit.getServer().getOnlinePlayers())
        {
            for(int i=0; i <100; i ++)
            {
                p.sendMessage("");
            }
        }
        Bukkit.broadcastMessage(color("&7The chat has been &acleared&7."));
    }


    Calendar cal = Calendar.getInstance();
    Date now = cal.getTime();
    public java.sql.Timestamp stamp = new java.sql.Timestamp(now.getTime());
    public java.sql.Timestamp getStamp() {
        return stamp;
    }


    /**
     * Method that handles all the color formatting
     *
     * @param message
     * @return
     */
    public static String color(String message) {
        String msg =  message;
//        msg = msg.replace("&", "§");
//        ChatColor.translateAlternateColorCodes('&',msg);
        //msg = msg.replace("%prefix%",getPrefix());
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    /**
     *  An adaption of AnonymousDr's Server Info Plugin.
     *
     *  https://github.com/AnonymousDr/ServerInfo/blob/master/src/org/togglecraft/serverinfo/main/ServerInfo.java
     *
     * @param plugin
     * @param author
     */
    public void information(Plugin plugin, Player author)
    {
        if(checkAuthor(author.getUniqueId()))
        {
            String text = color("&0---- &6Your Information &0----");
            author.sendMessage(color("&cIP: &a"+ author.getAddress().toString()));
            author.sendMessage(color("&cUUID: &3"+author.getUniqueId().toString()));
            author.sendMessage(color("&cName: &e"+author.getName()));
            String format = color("&0------------------------");
            author.sendMessage(format);
            String os =  System.getProperty("os.name");
            author.sendMessage(color("&4OS: &a&l"+os));
            double freeD=new File(plugin.getDataFolder()+"/..").getFreeSpace()/1073741824;
            double totalD=new File(plugin.getDataFolder()+"/..").getTotalSpace()/1073741824;
            author.sendMessage(ChatColor.AQUA+"Disk space used: "+ChatColor.GREEN+new DecimalFormat("#.##").format(totalD-freeD)+ChatColor.YELLOW+"/"+new DecimalFormat("#.##").format(totalD)+ChatColor.YELLOW+" GB ("+new DecimalFormat("#.##").format(((totalD-freeD)/totalD)*100)+"% used)");

            double free = Runtime.getRuntime().freeMemory() / 1048576;
            double total = Runtime.getRuntime().totalMemory() / 1048576;
            author.sendMessage(ChatColor.RED + "RAM Used: " + ChatColor.GREEN + new DecimalFormat("#.###").format(total - free) + ChatColor.YELLOW + "/" + new DecimalFormat("#.###").format(total) + ChatColor.YELLOW + " MB (" + new DecimalFormat("#.##").format(((total - free) / total) * 100) + "% used)");
            author.sendMessage(ChatColor.RED+"Number of cores: "+ChatColor.YELLOW+Runtime.getRuntime().availableProcessors());
            author.sendMessage(ChatColor.RED+"Java version: "+ChatColor.YELLOW+System.getProperty("java.version"));
            int c=0;
            for(World w:Bukkit.getWorlds())c+=w.getLoadedChunks().length;
            author.sendMessage(ChatColor.RED+"Chunks loaded: "+ChatColor.YELLOW+c);
        }
    }

}
