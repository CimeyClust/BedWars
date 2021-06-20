package de.cimeyclust.listener;


import cn.nukkit.Player;
import cn.nukkit.block.*;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerMessageEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.level.Level;
import de.cimeyclust.BedWars;

public class SetupListener implements Listener
{
    private BedWars plugin;

    public SetupListener(BedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSendMessage(PlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(this.plugin.getBedWarsAPI().getSetupWorld(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }

            for(Level level : this.plugin.getServer().getLevels().values())
            {
                if(level.getName().equals(message))
                {
                    this.plugin.getBedWarsAPI().setWorld(player, level);
                    player.sendMessage("§aThis map was solidified in the world "+level.getName()+".");
                    player.sendMessage("§aType in how many teams should there ever be a game. (minimum 2, maximum 8)");
                    this.plugin.getBedWarsAPI().setupTeamNumber(player);
                    event.setCancelled(true);
                    return;
                }
            }
            player.sendMessage("§cThe world with the given name was not found; Cancellation!");
            event.setCancelled(true);
            this.plugin.getBedWarsAPI().setupCancel(player);
            return;
        }
        else if(this.plugin.getBedWarsAPI().getSetupTeamNumber(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }

            if(this.plugin.getFunctions().isNumeric(message) && Integer.parseInt(message) >= 2 && Integer.parseInt(message) <= 8)
            {
                this.plugin.getBedWarsAPI().setTeamNumber(player, Integer.parseInt(message));
                event.setCancelled(true);
                player.sendMessage("§aType in how many players should be in one team. (minimum 1, maximum 8)");
                this.plugin.getBedWarsAPI().setupPlayerPerTeam(player);
                return;
            }
            else
            {
                player.sendMessage("§cYou have to enter a whole positive number, greater than 0; Cancellation!");
                event.setCancelled(true);
                this.plugin.getBedWarsAPI().setupCancel(player);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupPlayerPerTeam(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }

            if(this.plugin.getFunctions().isNumeric(message) && Integer.parseInt(message) >= 1 && Integer.parseInt(message) <= 8)
            {
                this.plugin.getBedWarsAPI().setPlayerPerTeam(player, Integer.parseInt(message));
                event.setCancelled(true);
                player.sendMessage("§aFor each team, hit a bed with the left or right mouse button to add them.");
                this.plugin.getBedWarsAPI().setupBed(player);
                player.sendMessage("§a"+this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))+" left!");
            }
            else
            {
                player.sendMessage("§cYou have to enter a whole positive number, greater than 0; Cancellation!");
                event.setCancelled(true);
                this.plugin.getBedWarsAPI().setupCancel(player);
            }
            return;
        }
        else if(this.plugin.getBedWarsAPI().getSetupBed(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupClaySpawner(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupIronSpawner(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupGoldSpawner(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupItemSeller(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
        else if(this.plugin.getBedWarsAPI().getSetupJoinSign(player))
        {
            if(message.equals("cancel"))
            {
                this.plugin.getBedWarsAPI().setupCancel(player);
                player.sendMessage("§cSetup was canceled successfully!");
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onPlayerInteractWithBlock(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(event.getAction().equals(PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) || event.getAction().equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
        {
            if(this.plugin.getBedWarsAPI().getSetupBed(player))
            {
                
            }
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event)
    {

    }

    @EventHandler
    public void onPlayerLeaveCancelSetup(PlayerQuitEvent event)
    {
        if(this.plugin.getBedWarsAPI().getSetupExists(event.getPlayer()))
        {
            this.plugin.getBedWarsAPI().setupCancel(event.getPlayer());
        }
    }
}
