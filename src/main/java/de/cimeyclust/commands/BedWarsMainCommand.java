package de.cimeyclust.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.cimeyclust.BedWars;

public class BedWarsMainCommand extends Command {

    private BedWars plugin;

    public BedWarsMainCommand(String name, String description, String usageMessage, String[] aliases, BedWars plugin) {
        super(name, description, usageMessage, aliases);
        this.setPermission("bedwars");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        if(commandSender instanceof Player)
        {
            Player player = ((Player) commandSender).getPlayer();

            if(player.hasPermission("bedwars")) {
                String option = args[0];

                if (option instanceof String)
                {
                    if(option.equals("setup"))
                    {
                        String name = args[1];

                        if(name instanceof String)
                        {
                            this.plugin.getBedWarsAPI().setupWorld();
                            player.sendMessage("§aType in the name of the world you want BedWars to take place in. (Type cancel to cancel the setup!)");
                        }
                        else
                        {
                            player.sendMessage("§cArgument 2 have to be a string and not a number!");
                            player.sendMessage("§c"+this.getUsage());
                        }
                    }
                }
                else
                {
                    player.sendMessage("§cArgument 1 have to be a string and not a number!");
                    player.sendMessage("§c"+this.getUsage());
                }
            }
        }
        else
        {
            commandSender.sendMessage("§cYou can only execute this command as a player!");
        }


        return true;
    }
}
