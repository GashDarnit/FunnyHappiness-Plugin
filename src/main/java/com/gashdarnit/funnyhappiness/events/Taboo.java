package com.gashdarnit.funnyhappiness.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.FileWriter;

public class Taboo implements Listener {
    private static String fileDir = "D:\\1.20.1 Happiness SMP\\bot-info\\";
    
    @EventHandler
    public void eatHaramFoodListener(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective("Religion");

        //Religion = 1 => Muslim
        if(objective.getScore(player.getName()).getScore() == 1 && (item.getType() == Material.PORKCHOP || item.getType() == Material.COOKED_PORKCHOP)) {
            player.getWorld().strikeLightning(player.getLocation());
            
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lGOD&r has punished " + player.getName() + " for eating &4&lHARAM&r food!"));
            addMessageToFile("[Server]: GOD has punished " + player.getName() + " for eating HARAM food!");
        }
    }
    
    private void addMessageToFile(String addMessage) {
        try {
            FileWriter fw = new FileWriter(fileDir + "minecraft_chat.txt", true);
            fw.write(addMessage);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
