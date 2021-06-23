package de.cimeyclust.util;

import cn.nukkit.Player;
import cn.nukkit.block.*;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.passive.EntityVillager;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.cimeyclust.BedWars;

import java.io.File;
import java.util.*;

public class BedWarsAPI
{
    private BedWars plugin;
    private File file;
    private Config config;
    public int index;

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

    public Block getItemSeller(String name, int index)
    {
        if(this.config.exists("bedwars."+name+".sellers."+index)) {
            int x = this.config.getInt("bedwars." + name + ".sellers.seller" + index + ".sellerX");
            int y = this.config.getInt("bedwars." + name + ".sellers.seller" + index + ".sellerY");
            int z = this.config.getInt("bedwars." + name + ".sellers.seller" + index + ".sellerZ");
            return this.getWorld(name).getBlock(x, y, z);
        }
        else
        {
            return null;
        }
    }

    public BlockEntitySign getJoinSign(String name)
    {
        int x = this.config.getInt("bedwars." + name + ".joinSignX");
        int y = this.config.getInt("bedwars." + name + ".joinSignY");
        int z = this.config.getInt("bedwars." + name + ".joinSignZ");
        return (BlockEntitySign) this.getWorld(name).getBlockEntity(new Location(x, y, z));
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
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".beds.lastIndex", 0);
    }

    public Integer getLastIndexOfClaySpawner(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".claySpawners.lastIndex", 0);
    }

    public Integer getLastIndexOfIronSpawner(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".ironSpawners.lastIndex", 0);
    }

    public Integer getLastIndexOfGoldSpawner(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".goldSpawners.lastIndex", 0);
    }

    public Integer getLastIndexOfItemSeller(Player player)
    {
        return this.config.getInt("bedwars."+this.plugin.getBedWarsAPI().getSetupName(player)+".sellers.lastIndex", 0);
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

    public Boolean getSetupItemSeller(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".seller");
    }

    public Boolean getSetupJoinSign(Player player)
    {
        return this.config.getBoolean("bedwars.setup."+player.getName()+".joinSign");
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
        if(names.contains(name))
        {
            player.sendMessage("§cA BedWars with the name "+ name +" already exists; Cancellation!");
            this.setupCancel(player);
            return;
        }
        names.add(name);
        this.config.set("names", names);

        this.config.set("bedwars.setup."+player.getName()+".world", true);
        this.config.set("bedwars.setup."+player.getName()+".name", name);
        this.config.set("bedwars."+this.getSetupName(player)+".setupPlayer", player.getName());
        this.config.set("bedwars."+this.getSetupName(player)+".setup", true);
        this.config.save(this.file);
    }

    public void setWorld(Player player, Level level)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".world", level.getName());
        this.config.set("bedwars.setup."+player.getName()+".world", false);
        this.config.save(this.file);
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
        BlockBed bed = new BlockBed();
        bed.place(bed.toItem(), bed, bed, blockBed.getBlockFace(), blockBed.getX(), blockBed.getY(), blockBed.getZ(), player);
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".beds", false);
            player.sendMessage("§aFor each team, hit a block of clay with the left or right mouse button to add them as clay spawner.");
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
            player.sendMessage("§aFor each team, hit a block with the left or right mouse button to add them as ground for the item seller.");
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupItemSeller(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".seller", true);
        this.config.save(this.file);
    }

    public void setItemSeller(Player player, Block block, int index)
    {
        this.config.set("bedwars."+this.getSetupName(player)+".sellers.lastIndex", index);
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".sellers.seller"+index+".sellerX", block.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".sellers.seller"+index+".sellerY", block.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".sellers.seller"+index+".sellerZ", block.getZ());
        if(index == this.getTeamNumber(this.getSetupName(player)))
        {
            this.config.set("bedwars.setup."+player.getName()+".seller", false);
            player.sendMessage("§aFinal step!; Hit the sign in another world as the BedWars world with which the players can enter the round with the left or right mouse button.");
        }

        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupJoinSign(Player player)
    {
        this.config.set("bedwars.setup."+player.getName()+".joinSign", true);
        this.config.save(this.file);
    }

    public void setJoinSign(Player player, BlockSignPost block)
    {
        this.config.save(this.file);
        this.config.set("bedwars."+this.getSetupName(player)+".joinSignX", block.getX());
        this.config.set("bedwars."+this.getSetupName(player)+".joinSignY", block.getY());
        this.config.set("bedwars."+this.getSetupName(player)+".joinSignZ", block.getZ());
        this.config.set("bedwars.setup."+player.getName()+".joinSign", false);

        ConfigSection section = this.config.getSection("bedwars."+this.getSetupName(player));
        section.remove("setupPlayer");
        ConfigSection section1 = this.config.getSection("bedwars.setup");
        section1.remove(player.getName());
        this.config.set("bedwars."+this.getSetupName(player)+".setup", false);

        BlockEntitySign blockEntity = (BlockEntitySign) block.getLevel().getBlockEntity(block.getLocation());
        if (blockEntity == null) {
            player.sendMessage("§cError: Unable to find block entity for the sign at " + block.getX() + " " + block.getY() + " " + block.getZ() + " in world " + player.getLevel());
        }
        else
        {
            String[] text = new String[4];
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 1)
            {
                text[0] = "§3BedWars Solo";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 2)
            {
                text[0] = "§3BedWars Duo";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 3)
            {
                text[0] = "§3BedWars Trio";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 4)
            {
                text[0] = "§3BedWars Squad";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 5)
            {
                text[0] = "§3BedWars Quint";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 6)
            {
                text[0] = "§3BedWars Sext";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 7)
            {
                text[0] = "§3BedWars Sept";
            }
            if(this.getPlayerPerTeam(this.getSetupName(player)) == 7)
            {
                text[0] = "§3BedWars Oct";
            }

            if(this.getTeamNumber(this.getSetupName(player)) == 2)
            {
                text[1] = "§92 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 3)
            {
                text[1] = "§93 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 4)
            {
                text[1] = "§94 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 5)
            {
                text[1] = "§95 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 6)
            {
                text[1] = "§96 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 7)
            {
                text[1] = "§97 Teams";
            }
            else if(this.getTeamNumber(this.getSetupName(player)) == 8)
            {
                text[1] = "§98 Teams";
            }
            text[3] = "§f[§0"+this.getSetupName(player)+"§f]";
            text[2] = "§a0 / "+(this.getPlayerPerTeam(this.getSetupName(player))*this.getTeamNumber(this.getSetupName(player)));
            blockEntity.setText(text);
            player.sendMessage("§aSetup done!");
        }

        this.config.save(this.file);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupCancel(Player player)
    {
        if(this.isSetup(this.getSetupName(player))) {
            this.config.getSection("bedwars").remove(this.getSetupName(player));
        }
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
