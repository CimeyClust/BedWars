package de.cimeyclust.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import de.cimeyclust.BedWars;

public class BedWarsMainCommand extends Command {

    private BedWars plugin;

    public BedWarsMainCommand(String name, String description, String usageMessage, String[] aliases, BedWars plugin) {
        super(name, description, usageMessage, aliases);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        if(commandSender instanceof Player) {
            Player player = ((Player) commandSender).getPlayer();
            if(args.length >= 1) {
                this.setUsage("§c/bedwars <setup>");
                String option = args[0];

                if (option instanceof String) {
                    if (option.equals("setup")) {
                        if (player.isOp()) {
                            this.setUsage("§c/bedwars setup <name>");
                            if (!this.plugin.getBedWarsAPI().getSetupExists(player)) {
                                if(args.length == 2) {
                                    String name = args[1];

                                    if (name instanceof String) {
                                        this.plugin.getBedWarsAPI().setupWorld(player, name);
                                        player.sendMessage("§aThe name of the BedWars, you are creating is " + name + ".\n§6Type in the name of the world you want BedWars to take place in. (Type cancel to cancel the setup!)");
                                    } else {
                                        player.sendMessage("§cArgument 2 have to be a string and not a number!");
                                        player.sendMessage("§c" + this.getUsage());
                                    }
                                }
                                else
                                {
                                    player.sendMessage("§c" + this.getUsage());
                                }
                            } else {
                                player.sendMessage("§cYou are already in setup!");
                            }
                        } else {
                            player.sendMessage("§cYou do not have the necessary authorization to do this!");
                        }
                    }
                } else {
                    player.sendMessage("§cArgument 1 have to be a string and not a number!");
                    player.sendMessage("§c" + this.getUsage());
                }
            }
            else
            {
                player.sendMessage("§c" + this.getUsage());
            }
        }
        else
        {
            commandSender.sendMessage("§cYou can only execute this command as a player!");
        }


        return true;
    }
}
