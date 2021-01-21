package de.cimeyclust.util;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import de.cimeyclust.BedWars;

import java.io.File;
import java.util.ArrayList;
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

    public List<Integer> getIDs()
    {
        return this.config.getIntegerList("ids");
    }

    public Boolean getSetupWorld()
    {
        return this.config.getBoolean("bedwars.setup."+(this.getIDs().size()-1)+".world");
    }

    public Boolean getSetupTeamNumber()
    {
        return this.config.getBoolean("bedwars.setup."+(this.getIDs().size()-1)+".teamNumber");
    }


    // Setter Methods

    public void addDefault()
    {
        if(!this.file.exists())
        {
            this.config.set("ids", new ArrayList<Integer>());
        }
        this.config.save(this.file);
    }

    // Setup
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupWorld()
    {
        List<Integer> list = new ArrayList<Integer>();
        list = this.getIDs();
        list.add((this.getIDs().size()));
        this.config.set("ids", list);

        this.config.set("bedwars.setup."+(this.getIDs().size())+".world", true);
        this.config.save(this.file);
    }

    public void setWorld(Level level)
    {
        this.config.set("bedwars."+(this.getIDs().size()-1)+".world", level.getName());
        this.config.set("bedwars.setup."+(this.getIDs().size())+".world", false);
        this.config.save(this.file);

        this.setupTeamNumber();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupTeamNumber()
    {
        this.config.set("bedwars.setup."+(this.getIDs().size()-1)+".teamNumber", true);
        this.config.save(this.file);
    }

    public void setTeamNumber(int number)
    {
        this.config.set("bedwars.setup."+(this.getIDs().size()-1)+".teamNumber", false);
        this.config.set("bedwars."+(this.getIDs().size()-1)+".teamNumber", number);
        this.config.save(this.file);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setupCancel()
    {
        List<Integer> list = new ArrayList<Integer>();
        list = this.getIDs();
        list.remove((this.getIDs().size()-1));
        this.config.set("ids", list);
        ConfigSection section = this.config.getSection("bedwars");
        section.remove("setup");
        section = this.config.getSection("bedwars");
        section.remove((this.getIDs().size()-1));

        this.config.save(this.file);
    }
}
