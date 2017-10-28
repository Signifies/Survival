package me.ES96.Survival.com;

import Utilities.Debug;
import Utilities.Rank;
import Utilities.SPermissions;
import Utilities.SUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;

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



    @EventHandler
    public void place(BlockPlaceEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_PLACE.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void breaking(BlockBreakEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_BREAK.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_DROP.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onpickup(PlayerPickupItemEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_PICKUP.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent event)
    {
        if(instance.lock() && !(SPermissions.SURVIVAL_BYPASS_INTERACT.checkPermission(event.getPlayer())))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();

//    TODO later    instance.getUser().getRank(p);

        instance.getUser().setRank(p, Rank.GUEST); //We can also get the chat prefix once configured...


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
        boolean perm = SPermissions.SURVIVAL_BYPASS_CHAT.checkPermission(p);


        if(instance.lock() && !(perm))
        {
            event.setCancelled(true);
        }else if(SPermissions.SURVIVAL_ACCESS.checkPermission(event.getPlayer()) || SPermissions.SURVIVAL_CHAT.checkPermission(p))
        {
            event.setCancelled(false);
        }else if(perm)
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
            p.sendMessage(color(instance.getConfig().getString("Chat.chat-disabled")));
            return;
        }

//        event.getPlayer().setDisplayName(instance.getPerms().getPermissions().getString("User-data." +p.getUniqueId() + ".name-color"));


        String message = event.getMessage();

        if(message.contains("-help"))
        {
            p.sendMessage(color("&8========== [&cChat &b&lFunctions&8] &8=========="));
            p.sendMessage("");
            p.sendMessage(color("&b#name &8&l- &7Displays player's name in shout."));
            p.sendMessage(color("&b#location &8&l- &7Will display user location. "));
            p.sendMessage(color("&eColor formatting in shout: ") + "&1, &2, &3, &4, &5, &6, &7, &8, &9 ETC.");
            p.sendMessage(color("&b#world &8&l- &7Displays the players current world."));
            p.sendMessage(color("&b#exp &8&l- Displays players Exp level."));
            p.sendMessage(color("&2&nFor Permissions to each function please check the Authors site."));
            p.sendMessage(color("&9How to use color codes: &3&nhttp://minecraftcolorcodes.com/"));
            p.sendMessage(color("&8==================================="));
        }

        String location =  color("&7X:&a"+p.getLocation().getBlockX() +" &7Y&a:" +p.getLocation().getBlockY() + " &7Z&a:" + p.getLocation().getBlockZ() +"&r" );

        if(SPermissions.SURVIVAL_CHAT_COLOR.checkPermission(p))
        {
            message = message.replace("&","§");
        }

        if(SPermissions.SURVIVAL_CHAT_WORLD.checkPermission(p))
        {
            message = message.replace("#world", p.getWorld().getName());
        }

        if(SPermissions.SURVIVAL_CHAT_EXP.checkPermission(p))
        {
            message = message.replace("#exp", ""+p.getExpToLevel());
        }

        if(SPermissions.SURVIVAL_CHAT_LOCATION.checkPermission(p))
        {
            message = message.replace("#location", location);
            message = message.replace("#loc", location);
        }

        if(SPermissions.SURVIVAL_CHAT_ITEM.checkPermission(p))
        {
            if(message.contains("#item"))
            {
                message = message.replace("#item", "¯\\_ツ_/¯");
                TextComponent m = new TextComponent("Mouse over to see "+ p.getName()+"'s item!");
                m.setColor(ChatColor.AQUA);

                String format = ""+p.getItemInHand().getItemMeta().getDisplayName()+"\n" + p.getItemInHand().getItemMeta().getLore() + "\n"+p.getItemInHand().getItemMeta().getEnchants();

                m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format).create()));

                for(Player player : Bukkit.getServer().getOnlinePlayers())
                {
                    player.spigot().sendMessage(m);
                }

            }
        }




        if(!instance.getConfig().getBoolean("Chat.custom-chat.Enabled")) return;




        String format = instance.getConfig().getString("Chat.custom-chat.Format");
        format = format.replace("%name%", p.getName());
        format = format.replace("%msg%", message); //TODO
        format = format.replace("%world%", p.getWorld().getName());
        format = format.replace("%UUID%", p.getUniqueId().toString());
        format = format.replace("%location%",location);
//        format = format.replace("%chatcolor%", instance.getPerms().getPermissions().getString("User-data." +p.getUniqueId() + ".chat-color"));
//                format = format.replaceAll("%IP%", "" + player.getAddress());

        //TODO: Add vault to get prefix from PermissionsEX...

        event.setFormat(color(format));

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
