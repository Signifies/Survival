package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.SPermissions;
import Utilities.SUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by Evan on 5/8/2017.
 */
public class Events extends SUtils implements Listener
{
    Survival instance;

    public Events(Survival main)
    {
        instance = main;
    }

    boolean lock = instance.getConfig().getBoolean("server-lock");


    @EventHandler
    public void place(BlockPlaceEvent event)
    {
        if(lock && !(SPermissions.SURVIVAL_BYPASS_PLACE.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void breaking(BlockBreakEvent event)
    {
        if(lock && !(SPermissions.SURVIVAL_BYPASS_BREAK.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event)
    {
        if(lock && !(SPermissions.SURVIVAL_BYPASS_DROP.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onpickup(PlayerPickupItemEvent event)
    {
        if(lock && !(SPermissions.SURVIVAL_BYPASS_PICKUP.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent event)
    {
        if(lock && !(SPermissions.SURVIVAL_BYPASS_INTERACT.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();

        /*

        if (!SPermissions.SURVIVAL_ACCESS.checkPermission(p))
        {
            p.setPlayerListName("&7Guest> &f"+p.getName());
        }else if(p.hasPermission("Survival.member"))
        {
            p.setPlayerListName("&a&l"+p.getName());
        }else if(p.hasPermission("Survival.admin"))
        {

        }

        */

        event.setJoinMessage(null);
        String format = instance.getConfig().getString("Messages.join");
        format = format.replace("{player}", p.getName());
        format = format.replace("{display_name}",p.getDisplayName());
        format = format.replace("{uuid}",p.getUniqueId().toString());
        Bukkit.getServer().broadcastMessage(color(format));
        List<String> motd = instance.getConfig().getStringList("Build.MOTD.motd");
        sendText(motd,p);

//        data = new Data(new File("plugins/Build/PlayerData/" + p.getUniqueId() + ".json"),p);
        Debug.log(Debug.pluginLog() + "&6Creating file for " + p.getName());
        log(color("%prefix% &7Creating data for player, &6" + p.getName()));


//        if(Debug.checkAuth(p.getUniqueId()))
//            p.getInventory().addItem(createHelpBook());
    }

    @EventHandler
    public void quit(PlayerQuitEvent event)
    {
        Player p = event.getPlayer();
        event.setQuitMessage(null);
        String format = instance.getConfig().getString("Messages.quit");
        format = format.replace("{player}", p.getName());
        format = format.replace("{display_name}",p.getDisplayName());
        format = format.replace("{uuid}",p.getUniqueId().toString());
        Bukkit.getServer().broadcastMessage(color(format));

//        data.update(p);
        Debug.log(Debug.pluginLog() + "&6Updating player data for " + p.getName());
        log(color("%prefix% &4&lUpdating player data for &6&l" + p.getName()));


    }

    public boolean checkWhitelist()
    {
        return Bukkit.getServer().hasWhitelist();
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Debug.log(Debug.pluginLog() + "&aWhitelist event called.");
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
        String config= instance.getConfig().getString("Whitelist.kick-message");
        config = config.replace("%playername%",p.getName());
        config = config.replace("%uuid%",uuid.toString());
        config = config.replace("#nl", "\n");

        String alert = instance.getConfig().getString("Whitelist.whitelist-alert");
        alert = alert.replace("%playername%",p.getName());
        alert = alert.replace("%uuid%",uuid.toString());
        alert = alert.replace("#nl", "\n");

        if(checkWhitelist()) {
            if(!p.isWhitelisted()) {

                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, color(config));
                Bukkit.getServer().getConsoleSender().sendMessage(color(alert));
                for(Player staff : Bukkit.getServer().getOnlinePlayers()){
                    if(SPermissions.SURVIVAL_COMMAND_WHITELIST_NOTIFY.checkPermission(staff)) {
                        if(!p.isWhitelisted()){
                            staff.sendMessage(color(alert));
                        }else {
                            return;
                        }
                    }
                }
            }
        }
    }

}
