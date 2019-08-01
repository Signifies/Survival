package Utilities;

import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Signifies on 4/2/2018 - 21:47.
 */
public class RankManagement extends SUtils
{
    private Survival instance;
    public RankManagement(Survival instance)
    {
        this.instance = instance;
    }

    public void manageRank(Player user,Player admin, Rank rank)
    {
        switch (rank)
        {
            case GUEST:
                if(User.getRank(admin).getPriority() > Rank.GUEST.getPriority())
                {
                    User.setDesiredRank(user,Rank.GUEST);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.GUEST.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;

            case MOD:
                if(User.getRank(admin).getPriority() > Rank.MOD.getPriority())
                {
                    User.setDesiredRank(user,Rank.MOD);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.MOD.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case DEV:
                if(User.getRank(admin).getPriority() > Rank.MOD.getPriority())
                {
                    User.setDesiredRank(user,Rank.DEV);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.DEV.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case ADMIN:
                if(User.getRank(admin).getPriority() >=Rank.ADMIN.getPriority())
                {
                    User.setDesiredRank(user,Rank.ADMIN);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.ADMIN.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
                default:
                    admin.sendMessage(color("&7You have entered an incorrect rank."));
                    admin.sendMessage("Is the rank, &7" +rank.toString() +"&f a valid rank?");

        }
    }

}
