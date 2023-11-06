package com.gashdarnit.funnyhappiness.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiscellaneousCommands implements CommandExecutor, TabExecutor {
    private static Map<String, Integer> religions = new HashMap<>();
    private static List<String> religionOperations = new ArrayList<>();

    public MiscellaneousCommands() {
        initializeReligions();
        initializeReligionsOperations();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) {
            System.out.println("Sender not a player");
            return true;
        }

        Player player = (Player) commandSender;

        // /religion <operation>> <Religion>
        if(command.getName().equalsIgnoreCase("religion")) {
            if(args.length == 2) {
                try {
                    String religion = args[1];
                    if(args[0].equalsIgnoreCase("join")) {
                        if(getValidReligion(religion)) {
                            Scoreboard scoreboard = player.getScoreboard();
                            Objective objective = scoreboard.getObjective("Religion");

                            if(objective.getScore(player.getName()).getScore() == religions.get(religion)) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You are already a &6&l" + religion.toUpperCase()));
                                return true;
                            }

                            objective.getScore(player.getName()).setScore(religions.get(religion));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getName() + " joined &6&l" + religion.toUpperCase()));
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l" + religion + "&r does not exist"));
                            return true;
                        }

                    } else if(args[0].equalsIgnoreCase("add")) {
                        if(religions.containsKey(religion)) {
                            player.sendMessage(religion + " already exists.");
                            return true;
                        }
                        //do something

                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if(player.isOp()) {
                            //do something

                        } else {
                            player.sendMessage("You need to be a Server Operator to run this command");
                        }
                    } else {
                        player.sendMessage("Unknown operation <" + args[0] + ">");
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage("That is not a valid religion");
                }

            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        final List<String> completions = new ArrayList<>();
        if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0], religionOperations, completions);
        } else if(args.length == 2) {
            StringUtil.copyPartialMatches(args[1], religions.keySet(), completions);
        }

        completions.sort(String.CASE_INSENSITIVE_ORDER);
        return completions;
    }

    private boolean getValidReligion(String religion) {
        for(String key : religions.keySet()) {
            if(religion.equalsIgnoreCase(key)) return true;
        }

        return false;
    }

    private static void initializeReligions() {
        religions.put("Christian", 0);
        religions.put("Muslim", 1);
        religions.put("Hindu", 2);
    }
    private static void initializeReligionsOperations() {
        religionOperations.add("join");
        religionOperations.add("add");
        religionOperations.add("remove");
    }
}
