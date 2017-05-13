package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.PermissionsConfig;
import Utilities.SPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES359 on 11/16/16.
 */
public class Survival extends JavaPlugin
{

    private PermissionsConfig perms = new PermissionsConfig(this);
    public static boolean DEBUG = true;

    public void onEnable()
    {
        configuration();
        permissions();
        commands();
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);

    }

    void configuration()
    {
        Debug.log(Debug.pluginLog() +"Configuration loading...");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    void permissions()
    {
        perms.saveDefaultPermissions();
        perms.savePermissions();
    }


    void commands()
    {

    }
    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }

    public PermissionsConfig getPerms()
    {
        return perms;
    }
}
