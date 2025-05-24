package MysticalSacks;

import MysticalSacks.Listeners.SackListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticalSacks extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Mystical Sacks loaded!");

        Bukkit.getPluginManager().registerEvents(new SackListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Goodbye!");
    }

}
