package me.jack;

import me.jack.DataBase.mysql;
import me.jack.commands.mute_cmd;
import me.jack.cons.runnable;
import me.jack.events.chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.lang.String.format;

public final class muteplayers extends JavaPlugin {

    public static String getPrefix(){
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class) ;
        return plugin.getConfig().getString("prefix").replace('&', '§');
    }
    public static String GetDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");
        String Date = now.format(myFormatObj);
        return Date;
    }
    public static void Log(String log){
        Bukkit.getLogger().info(getPrefix()+" §4LOG§r: "+log);
        for (Player p : Bukkit.getOnlinePlayers()){
            if(p.isOp() || p.hasPermission("muteplugin.command.mute")){
                p.sendMessage(getPrefix()+" §4LOG§r: "+log);
            }
        }
    }
    public static String ConvertSecToTime(int dur2sec){
        int hours = dur2sec / 3600;
        int minutes = (dur2sec % 3600) / 60;
        int seconds = dur2sec % 60;
        String time = format("%02d:%02d:%02d", hours, minutes, seconds);
        return  time;

    }
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class) ;
        Bukkit.getLogger().info(getPrefix() + "§cMute §eplugin §asuccessful loaded");
        getServer().getPluginManager().registerEvents(new chat(), this);
        getServer().getPluginCommand("mute").setExecutor(new mute_cmd());
        saveDefaultConfig();
        runnable.Start();
        Bukkit.getLogger().info("§c"+plugin.getConfig().getBoolean("Database_log"));

        if(plugin.getConfig().getBoolean("Database_log")){
            if (mysql.getConnection() != null ){
                Bukkit.getLogger().info(getPrefix()+" §aDatabase is working!");
            } else {
                Bukkit.getLogger().info(getPrefix()+" §cDatabase isn't working!");

            }
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info(getPrefix() + "§cMute §eplugin §asuccessful §cunloaded");
        saveDefaultConfig();
        saveConfig();
    }
}
