package de.blu.flyspeed.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class FlySpeedCommand implements CommandExecutor {

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (args.length == 0) {
      this.sendUsage(player);
      return false;
    }

    try {
      double speedValue;
      if (args[0].contains(".") || args[0].contains(",")) {
        speedValue = Double.parseDouble(args[0].replaceAll(",", "."));
      } else {
        speedValue = Double.parseDouble(args[0] + ".0");
      }

      if (speedValue >= 10) {
        speedValue = 10;
        player.setFlySpeed(1.0f);
      } else if (speedValue <= 0) {
        speedValue = 0;
        player.setFlySpeed(0.0f);
      } else {
        player.setFlySpeed(
            Float.parseFloat("0." + String.valueOf(speedValue).replaceAll("\\.", "")));
      }

      player.sendMessage(
          ChatColor.GRAY
              + "Your FlySpeed was changed to "
              + ChatColor.YELLOW
              + speedValue
              + ChatColor.GRAY
              + ".");
    } catch (Exception e) {
      this.sendUsage(player);
      return false;
    }

    return true;
  }

  private void sendUsage(Player player) {
    player.sendMessage(ChatColor.YELLOW + "Usage: /flyspeed <1-10>");
  }
}
