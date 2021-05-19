package de.blu.flyspeed;

import de.blu.flyspeed.command.FlySpeedCommand;
import de.blu.flyspeed.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlySpeed extends JavaPlugin {

  @Override
  public void onEnable() {
    // Register Command
    this.getCommand("flyspeed").setExecutor(new FlySpeedCommand());

    // Register Listener
    Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
  }
}
