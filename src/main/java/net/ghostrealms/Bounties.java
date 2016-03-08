package net.ghostrealms;

import net.ghostrealms.lib.Database;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by James Raynor on 3/7/16.
 */
public class Bounties extends JavaPlugin {
    public Database database;

    public void onEnable() {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        if (this.getConfig() == null) this.saveDefaultConfig();

        //TODO command registry and event registry
        if (this.getConfig().getBoolean("database.using"))
            this.setupDatabase(this.getConfig().getString("database.host"), this.getConfig().getString("database.username"), this.getConfig().getString("database.password"), this.getConfig().getString("database.name"), this.getConfig().getInt("database.port"));
    }

    public void onDisable() {
        if (this.getConfig().getBoolean("database.using"))
            if (this.database.isConnected())
                this.database.disconnect();
    }

    private void setupDatabase(String host, String username, String password, String name, int port) {
        this.database = new Database(name, host, username, password, port);
        this.database.connect();
    }

}
