package Utilities;

import me.ES96.Survival.com.Survival;
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
    public static String prefix = ChatColor.translateAlternateColorCodes('&',"&2Survival> &7->");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    private String permission = color("&cUnknown command. Type \"/help\" for help.");

    public boolean checkAuthor(UUID uuid)
    {
        return uuid.toString().equals(author);
    }

    public void displayAuthInfo(Player p)
    {
        if(checkAuthor(p.getUniqueId()))
        {
            p.sendMessage(color("&a&l&oHello, &7"+ p.getUniqueId().toString() +"\n &aThis server is using ") + getPrefix());
        }
    }

    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }

    public String check(boolean value, String name)
    {
        return  value ? name +ChatColor.GREEN+" [Enabled]"  : name + ChatColor.RED +" [Disabled]";
    }

    public String defaultMessage(boolean value, String msg)
    {
        return value ? color(permission) : color(msg);
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
            if(SPermissions.SURVIVAL_MANGEMENT.checkPermission(p))
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
        Bukkit.broadcastMessage(color("&7&lThe chat has been &a&lcleared&7&l."));
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
    public String color(String message) {
        String msg =  message;
//        msg = msg.replace("&", "§");
//        ChatColor.translateAlternateColorCodes('&',msg);
        msg = msg.replace("%prefix%",getPrefix());
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
