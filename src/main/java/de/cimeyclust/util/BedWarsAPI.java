package de.cimeyclust.util;

import cn.nukkit.Player;
import cn.nukkit.block.*;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.cimeyclust.BedWars;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class BedWarsAPI
{
    private BedWars plugin;
    private File file;
    private Config config;

    public BedWarsAPI(BedWars plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "bedwars.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    // Getter Methods

    public Boolean checkIfExists(String path)
    {
        return this.config.exists("bedwars."+path);
    }

    public List<String> getNames()
    {
        return this.config.getStringList("names");
    }

    public Level getWorld(String name)
    {
        return this.plugin.getServer().getLevelByName(this.config.getString("bedwars."+name+".world"));
    }

    public Integer getTeamNumber(String name)
    {
        return this.config.getInt("bedwars."+name+".teamNumber");
    }

    public Integer getPlayerPerTeam(String name)
    {
        return this.config.getInt("bedwars."+name+".playerPerTeam");
    }

    public BlockBed getBed(String name, int index)
    {
        if(this.config.exists("bedwars."+name+".beds."+index)) {
            int x = this.config.getInt("bedwars." + name + ".beds.team" + index + ".bedX");
            int y = this.config.getInt("bedwars." + name + ".beds.team" + index + ".bedY");
            int z = this.config.getInt("bedwars." + name + ".beds.team" + index + ".bedZ");
            return (BlockBed) this.getWorld(name).getBlock(x, y, z);
        }
        else
        {
            return null;
        }
    }

    public BlockClay getClaySpawner(String name, int index)
    {
        if(this.config.exists("bedwars."+name+".claySpawners."+index)) {
            int x = this.config.getInt("bedwars." + name + ".claySpawners.claySpawner" + index + ".spawnerX");
            int y = this.config.getInt("bedwars." + name + ".claySpawners.claySpawner" + index + ".spawnerY");
            int z = this.config.getInt("bedwars." + name + ".claySpawners.claySpawner" + index + ".spawnerZ");
            return (BlockClay) this.getWorld(name).getBlock(x, y, z);
        }
        else
        {
            return null;
        }
    }

    public BlockIron getIronSpawner(String name, int index)
    {
        if(this.config.exists("bedwars."+name+".ironSpawners."+index)) {
            int x = this.config.getInt("bedwars." + name + ".ironSpawners.ironSpawner" + index + ".spawnerX");
            int y = this.config.getInt("bedwars." + name + ".ironSpawners.ironSpawner" + index + ".spawnerY");
            int z = this.config.getInt("bedwars." + name + ".ironSpawners.ironSpawner" + index + ".spawnerZ");
            return (BlockIron) this.getWorld(name).getBlock(x, y, z);
        }
        else
        {
            return null;
        }
    }

    public BlockGold getGoldSpawner(String name, int index)
    {
        if(this.config.exists("bedwars."+name+".goldSpawners."+index)) {
            int x = this.config.getInt("bedwars." + name + ".goldSpawners.goldSpawner" + index + ".spawnerX");
            int y = this.config.getInt("bedwars." + name + ".goldSpawners.goldSpawner" + index + ".spawnerY");
            int z = this.config.getInt("bedwars." + name + ".goldSpawners.goldSpawner" + index + ".spawnerZ");
            return (BlockGold) this.getWorld(name).getBlock(x, y, z);
        }
        else
        {
            return null;
        }
    }

    // Setup
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Boolean isSetup(String name)
    {
        return this.config.getBoolean("bedwars."+name+".setup");
    }

    public String getSetupPlayer(String name)
    {
        return this.config.getString("bedwars."+name+".setupPlayer");
    }

    public Boolean getSetupExists(Player player)
    {
        return this.config.exists("bedwars."+this.getSetupName(player)+".setup");
    }

    public Integer getLastIndexOfBeds(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".beds.lastIndex");
    }

    public Integer getLastIndexOfClaySpawner(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".claySpawners.lastIndex");
    }

    public Integer getLastIndexOfGoldSpawner(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".goldSpawners.lastIndex");
    }

    public String getSetupName(Player player)
    {
        return this.config.getString("bedwars.setup."+player.getName()+".name");
    }

    public Boolean getSetupWorld(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".world");
    }

    public Boolean getSetupTeamNumber(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".teamNumber");
    }

    public Boolean getSetupPlayerPerTeam(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".playerPerTeam");
    }

    public Boolean getSetupBed(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".beds");
    }

    public Boolean getSetupClaySpawner(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".claySpawner");
    }

    public Boolean getSetupIronSpawner(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".ironSpawner");
    }

    public Boolean getSetupGoldSpawner(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".goldSpawner");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Setter Methods

    public void addDefault()
    {
        if(!this.config.exists("names"))
        {
            this.config.set("names", new ArrayList<String>());
        }
        this.config.save(this.file);
    }

    // Setup
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupWorld(Player player, String name)
    {
        List<String> names = this.getNames();
        names.add(name);
        this.config.set("names", names);

        this.config.set("bedwars.setup."+player.getName()+".world", true);
        this.config.set("bedwars.setup."+player.getName()+".name", name);
        this.config.save(this.file);
    }

    public void setWorld(Player player, Level level)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".world", level.getName());
        this.config.set("bedwars."+this.getSetupName(player)+".setup", true);
        this.config.set("bedwars."+this.getSetupName(player)+".setupPlayer", player.getName());
        this.config.set("bedwars.setup."+player.getName()+".world", false);
        this.config.save(this.file);

        this.setupTeamNumber(player);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupTeamNumber(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".teamNumber", true);
        this.config.save(this.file);
    }

    public void setTeamNumber(Player player, int number)
    {
        this.config.set("bedwars.setup."+player.getName()+".teamNumber", false);
        this.config.set("bedwars."+this.getSetupName(player)+".teamNumber", number);
        this.config.save(this.file);

        this.setupPlayerPerTeam(player);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupPlayerPerTeam(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".playerPerTeam", true);
        this.config.save(this.file);
    }

    public void setPlayerPerTeam(Player player, int number)
    {
        this.config.set("bedwars.setup."+player.getName()+".playerPerTeam", false);
        this.config.set("bedwars."+this.getSetupName(player)+".playerPerTeam", number);
        this.config.save(this.file);

        this.setupBed(player);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupBed(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".beds", true);
        this.config.save(this.file);
    }

    public void setBed(Player player, BlockBed blockBed, int index, String teamColor)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".beds.lastIndex", index);
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".beds.team"+index+".bedX", blockBed.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".beds.team"+index+".bedY", blockBed.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".beds.team"+index+".bedZ", blockBed.getZ());
        this.config.set("bedwars."+this.getSetupName(player)+".beds.team"+index+".teamColor", teamColor);
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".beds", false);
            player.sendMessage("§aFor each team, hit a block of clay with the left or right mouse button to add them as clay spawner.");
            this.setupClaySpawner(player);
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupClaySpawner(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".claySpawner", true);
        this.config.save(this.file);
    }

    public void setClaySpawner(Player player, BlockClay blockClay, int index)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".claySpawners.lastIndex", index);
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".claySpawners.claySpawner"+index+".spawnerX", blockClay.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".claySpawners.claySpawner"+index+".spawnerY", blockClay.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".claySpawners.claySpawner"+index+".spawnerZ", blockClay.getZ());
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".claySpawner", false);
            player.sendMessage("§aFor each team, hit a block of iron with the left or right mouse button to add them as iron spawner.");
            this.setupIronSpawner(player);
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupIronSpawner(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".ironSpawner", true);
        this.config.save(this.file);
    }

    public void setIronSpawner(Player player, BlockIron blockIron, int index)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".ironSpawners.lastIndex", index);
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".ironSpawners.ironSpawner"+index+".spawnerX", blockIron.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".ironSpawners.ironSpawner"+index+".spawnerY", blockIron.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".ironSpawners.ironSpawner"+index+".spawnerZ", blockIron.getZ());
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".ironSpawner", false);
            player.sendMessage("§aFor each team, hit a block of gold with the left or right mouse button to add them as gold spawner.");
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupGoldSpawner(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".goldSpawner", true);
        this.config.save(this.file);
    }

    public void setGoldSpawner(Player player, BlockGold blockGold, int index)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".goldSpawners.lastIndex", index);
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".goldSpawners.goldSpawner"+index+".spawnerX", blockGold.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".goldSpawners.goldSpawner"+index+".spawnerY", blockGold.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".goldSpawners.goldSpawner"+index+".spawnerZ", blockGold.getZ());
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".goldSpawner", false);
            player.sendMessage("§aHit the block that will be marked as the lobby spawn point.");
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupCancel(Player player)
    {
        this.config.getSection("bedwars").remove(this.getSetupName(player));
        List<String> names = this.getNames();
        names.remove(this.getSetupName(player));
        this.config.set("names", names);
        ConfigSection section = this.config.getSection("bedwars.setup");
        section.remove(player.getName());

        this.config.save(this.file);
    }

    public void removeSetup()
    {
        ConfigSection section = this.config.getSection("bedwars");
        section.remove("setup");
        for(String name : this.getNames())
        {
            if(this.isSetup(name))
            {
                section.remove(name);

                List<String> names = this.getNames();
                names.remove(name);
                this.config.set("names", names);

                this.config.save(this.file);
            }
        }

        this.config.save(this.file);
    }
}
