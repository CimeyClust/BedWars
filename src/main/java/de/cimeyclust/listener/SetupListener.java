package de.cimeyclust.listener;


import cn.nukkit.Player;
import cn.nukkit.block.*;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
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
                if(block instanceof BlockBed && block.getLevel().equals(this.plugin.getBedWarsAPI().getWorld(this.plugin.getBedWarsAPI().getSetupName(player))))
                {
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 1) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 1)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 2) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 2)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 3) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 3)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 4) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 4)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 5) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 5)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 6) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 6)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 7) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 7)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 8) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getBed(this.plugin.getBedWarsAPI().getSetupName(player), 8)))
                        {
                            player.sendMessage("§cYou have already chosen this bed!");
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 0)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 1, "red");
                        player.sendMessage("§aThe bed was set!");
                        player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-1) + " left!");
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 1)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 2, "blue");
                        if(2 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-2) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 2)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 3, "green");
                        if(3 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-3) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 3)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 4, "yellow");
                        if(4 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-4) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 4)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 5, "orange");
                        if(5 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-5) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 5)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 6, "purple");
                        if(6 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-6) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 6)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 7, "pink");
                        if(7 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-7) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfBeds(player) == 7)
                    {
                        this.plugin.getBedWarsAPI().setBed(player, (BlockBed) block, 8, "lightblue");
                        if(8 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe bed was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-8) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupClaySpawner(player);
                            return;
                        }
                    }
                    else {
                        player.sendMessage("§cFailed!");
                    }
                    event.setCancelled(true);
                }
                else {
                    player.sendMessage("§cYou have to choose a bed that is in the BedWars world you specified!");
                }
            }

            if(this.plugin.getBedWarsAPI().getSetupClaySpawner(player))
            {
                if(block instanceof BlockClay && block.getLevel().equals(this.plugin.getBedWarsAPI().getWorld(this.plugin.getBedWarsAPI().getSetupName(player))))
                {
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getClaySpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8)))
                        {
                            player.sendMessage("§cYou have already chosen this block of clay!");
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 0)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 1);
                        player.sendMessage("§aThe clay spawner was set!");
                        player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-1) + " left!");
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 1)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 2);
                        if(2 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-2) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 2)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 3);
                        if(3 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-3) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 3)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 4);
                        if(4 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-4) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 4)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 5);
                        if(5 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-5) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 5)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 6);
                        if(6 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-6) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 6)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 7);
                        if(7 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-7) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfClaySpawner(player) == 7)
                    {
                        this.plugin.getBedWarsAPI().setClaySpawner(player, (BlockClay) block, 8);
                        if(8 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe clay spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-8) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupIronSpawner(player);
                            return;
                        }
                    }
                    else {
                        player.sendMessage("§cFailed!");
                    }
                    event.setCancelled(true);
                }
                else {
                    player.sendMessage("§cYou have to choose a clay spawner that is in the BedWars world you specified!");
                }
            }

            if(this.plugin.getBedWarsAPI().getSetupIronSpawner(player))
            {
                if(block instanceof BlockIron && block.getLevel().equals(this.plugin.getBedWarsAPI().getWorld(this.plugin.getBedWarsAPI().getSetupName(player))))
                {
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getIronSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8)))
                        {
                            player.sendMessage("§cYou have already chosen this block of iron!");
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 0)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 1);
                        player.sendMessage("§aThe iron spawner was set!");
                        player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-1) + " left!");
                        event.setCancelled(true);
                        this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                        return;
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 1)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 2);
                        if(2 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-2) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 2)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 3);
                        if(3 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-3) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 3)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 4);
                        if(4 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-4) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 4)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 5);
                        if(5 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-5) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 5)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 6);
                        if(6 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-6) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 6)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 7);
                        if(7 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe iron spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-7) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfIronSpawner(player) == 7)
                    {
                        this.plugin.getBedWarsAPI().setIronSpawner(player, (BlockIron) block, 8);
                        player.sendMessage("§aThe iron spawner was set!");
                        if(8 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aYou have successfully set all iron spawners!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-8) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupGoldSpawner(player);
                            return;
                        }
                    }
                    else {
                        player.sendMessage("§cFailed!");
                    }
                    event.setCancelled(true);
                }
                else {
                    player.sendMessage("§cYou have to choose an iron spawner that is in the BedWars world you specified!");
                }
            }

            if(this.plugin.getBedWarsAPI().getSetupGoldSpawner(player))
            {
                if(block instanceof BlockGold && block.getLevel().equals(this.plugin.getBedWarsAPI().getWorld(this.plugin.getBedWarsAPI().getSetupName(player))))
                {
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 1)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 2)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 3)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 4)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 5)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 6)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 7)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getGoldSpawner(this.plugin.getBedWarsAPI().getSetupName(player), 8)))
                        {
                            player.sendMessage("§cYou have already chosen this block of gold!");
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 0)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 1);
                        player.sendMessage("§aThe gold spawner was set!");
                        player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-1) + " left!");
                        event.setCancelled(true);
                        this.plugin.getBedWarsAPI().setupItemSeller(player);
                        return;
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 1)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 2);
                        if(2 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-2) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 2)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 3);
                        if(3 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-3) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 3)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 4);
                        if(4 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-4) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 4)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 5);
                        if(5 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-5) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 5)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 6);
                        if(6 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-6) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 6)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 7);
                        if(7 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-7) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfGoldSpawner(player) == 7)
                    {
                        this.plugin.getBedWarsAPI().setGoldSpawner(player, (BlockGold) block, 8);
                        if(8 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe gold spawner was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-8) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupItemSeller(player);
                            return;
                        }
                    }
                    else {
                        player.sendMessage("§cFailed!");
                    }
                    event.setCancelled(true);
                }
                else {
                    player.sendMessage("§cYou have to choose a gold spawner that is in the BedWars world you specified!");
                }
            }

            if(this.plugin.getBedWarsAPI().getSetupItemSeller(player))
            {
                if(!(block instanceof BlockGold) && block.getLevel().equals(this.plugin.getBedWarsAPI().getWorld(this.plugin.getBedWarsAPI().getSetupName(player))))
                {
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 1) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 1)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 2) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 2)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 3) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 3)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 4) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 4)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 5) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 5)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 6) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 6)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 7) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 7)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 8) != null) {
                        if (block.equals(this.plugin.getBedWarsAPI().getItemSeller(this.plugin.getBedWarsAPI().getSetupName(player), 8)))
                        {
                            player.sendMessage("§cYou have already chosen this block!");
                            event.setCancelled(true);
                            return;
                        }
                    }

                    if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 0)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 1);
                        player.sendMessage("§aThe item seller ground block was set!");
                        player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-1) + " left!");
                        event.setCancelled(true);
                        this.plugin.getBedWarsAPI().setupJoinSign(player);
                        return;
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 1)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 2);
                        if(2 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-2) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 2)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 3);
                        if(3 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-3) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 3)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 4);
                        if(4 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-4) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 4)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 5);
                        if(5 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-5) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 5)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 6);
                        if(6 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-6) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 6)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 7);
                        if(7 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-7) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else if(this.plugin.getBedWarsAPI().getLastIndexOfItemSeller(player) == 7)
                    {
                        this.plugin.getBedWarsAPI().setItemSeller(player, block, 8);
                        if(8 != this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player)))
                        {
                            player.sendMessage("§aThe item seller ground block was set!");
                            player.sendMessage("§a" + (this.plugin.getBedWarsAPI().getTeamNumber(this.plugin.getBedWarsAPI().getSetupName(player))-8) + " left!");
                            event.setCancelled(true);
                            this.plugin.getBedWarsAPI().setupJoinSign(player);
                            return;
                        }
                    }
                    else {
                        player.sendMessage("§cFailed!");
                    }
                    event.setCancelled(true);
                }
                else {
                    player.sendMessage("§cYou have to choose a block, except a gold block, that is in the BedWars world you specified!");
                }
            }

            if(this.plugin.getBedWarsAPI().getSetupJoinSign(player))
            {
                if(block instanceof BlockSignPost)
                {
                    this.plugin.getBedWarsAPI().setJoinSign(player, (BlockSignPost) block);
                }
                else {
                    player.sendMessage("§cYou have to choose a sign!");
                }
            }
        }
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
