package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.PermissionsConfig;
import Utilities.SPermissions;
import commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 11/16/16.
 */
public class Survival extends JavaPlugin {

    private PermissionsConfig perms = new PermissionsConfig(this);
    private ArrayList<UUID> notify = new ArrayList<>();
    public static ArrayList<String> staff = new ArrayList<>();
    private User user = new User(this);
    public static boolean DEBUG = true;
    public PluginDescriptionFile pdfFile = this.getDescription();


    public void onEnable() {
        configuration();
        permissions();
        commands();
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandListeners(this), this);

    }

    void configuration() {
        Debug.log(Debug.pluginLog() + "Configuration loading...");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    void permissions() {
        perms.saveDefaultPermissions();
        perms.savePermissions();
    }


    void commands() {
        registerCmd("cmddisable", new CommandListeners(this));
        registerCmd("list", new SListCommand(this));
        registerCmd("chat", new SChatCommand(this));
        registerCmd("message", new SMessageCommand(this));
        registerCmd("kick", new SKickCommand(this));
        registerCmd("whitelist", new SWhitelistCommand(this));
        registerCmd("tp", new STPCommand(this));
        registerCmd("tphere", new STPhereCommand(this));
        registerCmd("edit", new SEditCommand(this));
    }

    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }

    public PermissionsConfig getPerms() {
        return perms;
    }

    public ArrayList<UUID> getNotifications() {
        return notify;
    }

    public boolean permissionDefault() {
        return this.getConfig().getBoolean("perm-message.default");
    }

    public String getMessage()
    {
        return this.getConfig().getString("perm-message.format");
    }
    public boolean lock()
    {
        return getConfig().getBoolean("server-lock");
    }

    public User getUser()
    {
        return user;
    }
}
