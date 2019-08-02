package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;

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



    @EventHandler
    public void place(BlockPlaceEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_PLACE.checkPermission(event.getPlayer())))
        {
            event.getPlayer().sendMessage(color("&cServer is in lockdown."));
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void breaking(BlockBreakEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_BREAK.checkPermission(event.getPlayer())))
        {
            event.getPlayer().sendMessage(color("&cServer is in lockdown."));
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_DROP.checkPermission(event.getPlayer())))
        {
            event.getPlayer().sendMessage(color("&cServer is in lockdown."));
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_INTERACT.checkPermission(event.getPlayer())))
        {
            event.getPlayer().sendMessage(color("&cServer is in lockdown."));
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();

//    TODO.md later    instance.getUser().getRank(p);

       // instance.getUser().setRank(p, Rank.GUEST); //We can also get the chat prefix once configured...
        User.setRank(p,Rank.GUEST);

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
        List<String> motd = instance.getConfig().getStringList("MOTD.motd");
        sendText(motd,p);

//        data = new Data(new File("plugins/Build/PlayerData/" + p.getUniqueId() + ".json"),p);
//        Debug.log(Debug.pluginLog() + "&6Creating file for " + p.getName());
//        log(color("%prefix% &7Creating data for player, &6" + p.getName()));

//       instance.getUser().writeUser(p);

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




    @EventHandler
    public void chat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();


        boolean chat = instance.getConfig().getBoolean("Chat.Enabled");
        //boolean perm = SPermissions.SURVIVAL_BYPASS_CHAT.checkPermission(p);


        if(!(chat) && !User.isPermissible(p, Rank.MOD))
        {
            event.setCancelled(true);
            p.sendMessage(color(instance.getConfig().getString("Chat.chat-disabled")));
        }else
        {
            event.setCancelled(false);
        }

        String message = event.getMessage();
        List<String> list = instance.getConfig().getStringList("blocked-words");

        if(list.contains(message.toLowerCase().replaceAll("\\s", "").replaceAll("&[a-z0-9]", ""))) {
            event.setCancelled(true);
            p.sendMessage(color("&4You're not allowed to use that word."));
        }


            if(User.isPermissible(p, Rank.MOD)) {
                p.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName() +ChatColor.RESET);
            }

            if(User.isPermissible(p,Rank.ADMIN)) {
                p.setDisplayName(ChatColor.RED + p.getName()+ChatColor.RESET);
            }

            if(User.isPermissible(p,Rank.DEV)) {
                p.setDisplayName(ChatColor.DARK_GREEN +p.getName()+ChatColor.RESET);
            }

            if(Debug.checkScience(p.getUniqueId())) {
                p.setDisplayName(ChatColor.YELLOW + p.getName() +ChatColor.RESET);
            }

        if(!instance.getConfig().getBoolean("Chat.custom-chat.Enabled")) return;


        //TODO.md Set administrators color in tab list on join and chat on chat.

        String format = instance.getConfig().getString("Chat.custom-chat.Format");

        format = format.replace("{name}", p.getName());
        format = format.replace("{msg}", message); //TODO.md
        format = format.replace("{world}", p.getWorld().getName());
        format = format.replace("{RANK}",User.getRankPrefix(p));
        format = format.replace("{PREFIX}",User.getCustomPrefix(p));
//        format = format.replace("%chatcolor%", instance.getPerms().getPermissions().getString("User-data." +p.getUniqueId() + ".chat-color"));
//                format = format.replaceAll("%IP%", "" + player.getAddress());

        //TODO.md: Add vault to get prefix from PermissionsEX...

        event.setFormat(format);//TODO.md Fix.

    }

    @EventHandler
    public void serverPing(ServerListPingEvent event)
    {

        Debug.log(Debug.pluginLog() + "&9Server Ping event called.");
        String pingmessage = color(instance.getConfig().getString("MOTD.server-list"));

        pingmessage = pingmessage.replaceAll("&", "\u00A7");
        pingmessage = pingmessage.replace("#nl", "\n");
//        pingmessage = pingmessage.replace("%staff%",getStaff(main));
//        pingmessage = pingmessage.replace("%users%",getUsers());
//        pingmessage = pingmessage.replace("%time%",getStamp().toString());
        event.setMotd(pingmessage);
        //uitl.logToConsole("TEST: " +event.getAddress());
    }

    public boolean checkWhitelist()
    {
        return Bukkit.getServer().hasWhitelist();
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
        String config = instance.getConfig().getString("Whitelist.kick-message");
        config = config.replace("{name}", p.getName());
        config = config.replace("{uuid}", "" + uuid);
        config = config.replace("\n", "\n");
        String alert = instance.getConfig().getString("Whitelist.whitelist-alert");
        alert = alert.replace("{name}", p.getName());
        alert = alert.replace("{uuid}", "" + uuid);

        String result = instance.getConfig().getBoolean("Maintenance.enabled") ? instance.getConfig().getString("Maintenance.msg") : config;

        // IF WL = TRUE; THEN DO ALLOWANCE; THEN DO DISALLOWANCE.
        // IF WL = TRUE; AND P HAS PERM; THEN ALLOW

        if (checkWhitelist()) {
            /*This logic is important, because we want them to join regardless if they are whitelisted if permission specific control is enabled.*/
            if (checkWhitelist() && SPermissions.BYPASS_STATUS.checkPermission(p) || (instance.getConfig().getBoolean("Whitelist.use-permission") && p.hasPermission(instance.getConfig().getString("Whitelist.permission")))) {
                event.setResult(PlayerLoginEvent.Result.ALLOWED);
            }else if(!p.isWhitelisted()) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, org.bukkit.ChatColor.translateAlternateColorCodes('&', result));
                Bukkit.getServer().getConsoleSender().sendMessage(color(alert));
                for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                    if (User.isPermissible(staff, Rank.MOD) && !User.evaluateWhitelistNotifications(staff.getUniqueId())) {
                        if (p.isWhitelisted()) {
                            return;
                        }
                        staff.sendMessage(color(alert));
                    }
                }
            }
        }
    }
}
