package de.cimeyclust.util;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.cimeyclust.BedWars;
import jdk.nashorn.internal.ir.LiteralNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RoundManager
{
    private BedWars plugin;
    private File file;
    private Config config;

    public RoundManager(BedWars plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "bedwars.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    // Getter Methods

    public boolean checkIfRoundExists(String name)
    {
        return this.config.exists("round."+name);
    }

    public List<String> getRounds()
    {
        return this.config.getStringList("rounds");
    }

    public List<String> getPlayerOfRound(String name)
    {
        return this.config.getStringList("round"+name+"player");
    }


    // Setter Methods

    public void setRounds(List<String> rounds)
    {
        this.config.set("rounds", rounds);
        this.config.save(this.file);
    }

    public void setPlayers(List<String> players, String name)
    {
        this.config.set("round."+name+"player", players);
    }

    public void createRound(String name)
    {
        List<String> rounds = this.getRounds();
        rounds.add(name);
        this.setRounds(rounds);


        this.config.set("round."+name+".player", new ArrayList<String>());

        this.config.save(this.file);
    }

    public void joinRound(String name, Player player)
    {
        List<String> players = this.getPlayerOfRound(name);
        players.add(player.getName());
        this.setPlayers(players, name);
    }

    // Remove

    public void removeRound(String name)
    {
        ConfigSection section = this.config.getSection("round");
        section.remove(name);
        this.config.save(this.file);
    }

    public void removeAllRounds()
    {
        ConfigSection section = this.config.getSection("");
        section.remove("rounds");
        section.remove("round");
        this.config.save(this.file);
    }
}
