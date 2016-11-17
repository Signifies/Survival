package Utilities;

import me.ES96.Survival.com.Survival;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Debug
{
    static public String FAILED_ACTION = "[FAILED ACTION] ";
    static public String ACTION = "[ACTION] ";
    static public String LOG = "[LOG] ";
    static public String SEVERE = "[SEVERE] &c";
    static public String PREFIX = SUtils.prefix;
    private final static String VERSION = "1.4.0";
    static public String pluginLog()
    {
        return LOG+ ""+ PREFIX;
    }

   static String color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    static public void debugEnabled()
    {
        if(Survival.DEBUG)
        {
            System.out.println(Debug.LOG + " Debugging is enabled...");
        }else
        {
            System.out.println(Debug.LOG +"Debugging is " + Survival.DEBUG);
        }
    }

    static public void log(String message)
    {
        if(Survival.DEBUG)
        {
            Bukkit.getServer().getConsoleSender().sendMessage(color(message));
        }
    }

    static public void log(Player p, String message)
    {
        if(Survival.DEBUG)
        {
            p.sendMessage(color(message));
        }
    }

    static public void log(CommandSender p, String message)
    {
        if(Survival.DEBUG)
        {
            p.sendMessage(color(message));
        }
    }


    public static  boolean getValue()
    {
        return Survival.DEBUG;
    }

    public static void setValue(boolean val)
    {
        Survival.DEBUG = val;
    }

    /**
     * Simple debug flag enabler.
     * @param sender
     * @param args
     */
    static public void debugToggle(CommandSender sender, String[] args)
    {
        if(args.length > 1 && args[0].equalsIgnoreCase("debug"))
        {

            /**
             * REPLACE MAINCLASS WITH YOUR MAINCLASS.
             *
             * EXAMPLE: <MAINCLASS>.DEBUG.
             *
             * Make sure to add:
             * static public DEBUG = false; // To your main class.
             *
             *
             */

            setValue(Boolean.parseBoolean(args[1]));
            sender.sendMessage(color("[&4DEBUG&f] &c--> &7You have set Debug status to &4&l: " + getValue()));
        }
    }

    private static ArrayList<String> devList()
    {

        ArrayList<String> value = new ArrayList<>();
        //ES
        value.add("9c5dd792-dcb3-443b-ac6c-605903231eb2");
        value.add("347a17b6-5851-46c2-8747-5576443a311a");
        for(String text : value)
        {
            Debug.log(ChatColor.GOLD + text);
        }

        return value;
    }

    static public boolean checkAuth(UUID user)
    {
//        Debug.log("[LOG] &cCurrently logging the user, " + user.toString());
        return devList().contains(user.toString());
    }


    /**
     *  Informs author that plugin is being used by server.
     *
     * @param p
     */
    static  public void displayAuthInfo(Player p, String version)
    {
        if(checkAuth(p.getUniqueId()))
        {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l&oHello, &7"+ p.getName() +"\n &aThis server is using " +PREFIX + " ") + version);
        }
    }

}