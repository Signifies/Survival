package Utilities;

import me.ES96.Survival.com.Survival;
import me.ES96.Survival.com.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Signifies on 4/2/2018 - 21:47.
 */
public class RankManagement
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
                    User.setRank(user,Rank.GUEST);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.GUEST.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
                case MEMBER:
                    if(User.getRank(admin).getPriority() > Rank.MEMBER.getPriority())
                    {
                        User.setRank(user,Rank.MEMBER);
                        admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.MEMBER.toString() +".");
                    }else
                    {
                        admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                    }
                    break;
            case GAMER:
                if(User.getRank(admin).getPriority() > Rank.GAMER.getPriority())
                {
                    User.setRank(user,Rank.GAMER);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.GAMER.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case BUILDER:
                if(User.getRank(admin).getPriority() > Rank.BUILDER.getPriority())
                {
                    User.setRank(user,Rank.BUILDER);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.BUILDER.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case MOD:
                if(User.getRank(admin).getPriority() > Rank.MOD.getPriority())
                {
                    User.setRank(user,Rank.MOD);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.MOD.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case SMOD:
                if(User.getRank(admin).getPriority() > Rank.SMOD.getPriority())
                {
                    User.setRank(user,Rank.SMOD);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.SMOD.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case DEV:
                if(User.getRank(admin).getPriority() > Rank.SMOD.getPriority())
                {
                    User.setRank(user,Rank.DEV);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.DEV.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;
            case ADMIN:
                if(User.getRank(admin).getPriority() >=Rank.ADMIN.getPriority())
                {
                    User.setRank(user,Rank.ADMIN);
                    admin.sendMessage(ChatColor.GRAY + "You have set the user, "+ChatColor.WHITE + user.getName() +"'s"+ ChatColor.GRAY +" rank to " + Rank.ADMIN.toString() +".");
                }else
                {
                    admin.sendMessage(ChatColor.RED + "Sorry, your rank isn't high enough to set the Rank " +rank.toString() + " For this user.");
                }
                break;

        }
    }

}
