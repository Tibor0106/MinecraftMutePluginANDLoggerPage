package me.jack.commands;

import me.jack.DataBase.mysql;
import me.jack.cons.Mute;
import me.jack.cons.User;
import me.jack.muteplayers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Date;


public class mute_cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 3){
            sender.sendMessage(muteplayers.getPrefix()+" §eUsage: §c/mute <user> <duration> <reason>");
            return false;
        }
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class);
        String reason = "";
        Player p;
        try{
            p = Bukkit.getPlayer(args[0]);
            //test player
            p.getHealth();
        }catch (Exception e ){
            e.printStackTrace();
            sender.sendMessage(muteplayers.getPrefix()+" §cFail! §4Player not found!");
            return false;
        }
        String duration = args[1];
        if(duration.toCharArray()[duration.length()-1] == 'd'){

        } else if (duration.toCharArray()[duration.length()-1] == 'h') {

        } else if (duration.toCharArray()[duration.length()-1] == 'm') {

        } else if (duration.toCharArray()[duration.length()-1] == 's') {

        } else {
            sender.sendMessage(muteplayers.getPrefix()+" §cFail! Invalid time format! §aFormats:   \n - 1d : 1 day\n - 1h: 1 hour\n - 1m: 1 minute\n - 1s: 1 second");
        }
        int dur2sec = 0;
        if(!CheckNumber(remove_str(duration))){
            sender.sendMessage(muteplayers.getPrefix()+" §cFail! Invalid time format! §aFormats:   \n - 1d : 1 day\n - 1h: 1 hour\n - 1m: 1 minute\n - 1s: 1 second");
            return false;
        }
        if (duration.toCharArray()[duration.length() - 1] == 'd') {
            dur2sec = Integer.parseInt(remove_str(duration));
            dur2sec*=86400;
        } else if (duration.toCharArray()[duration.length() - 1] == 'h') {
            dur2sec = Integer.parseInt(remove_str(duration));
           dur2sec*=3600;
        } else if (duration.toCharArray()[duration.length() - 1] == 'm') {
            dur2sec = Integer.parseInt(remove_str(duration));
            dur2sec*=60;
        }  else if (duration.toCharArray()[duration.length() - 1] == 's') {
            dur2sec = Integer.parseInt(remove_str(duration));
        } else {
            sender.sendMessage(muteplayers.getPrefix()+" §cFail! Invalid time format! §aFormats:   \n - 1d : 1 day\n - 1h: 1 hour\n - 1m: 1 minute\n - 1s: 1 second");
        }
        for (int i = 2; i < args.length; i++){
            reason += args[i]+" ";
        }
        Boolean check = false;
        for (Mute i : User.mutes){
            if(i.username.toString() == p.getName().toString()){
                i.Staff = sender.getName();
                i.reason = reason;
                i.duration = dur2sec;
                i.date = muteplayers.GetDateTime();
                i.username = p.getName();
                check = true;
                break;
            }
        }
        if(check){
            p.sendMessage(muteplayers.getPrefix()+" §CYou are muted!\n§r§7 - §6Time §c"+muteplayers.ConvertSecToTime(dur2sec)+ "\n §r§7- §6Staff: §c"+p.getName()+"\n §r§7- §6Reason: §c§o"+reason);
            muteplayers.Log("§e"+sender.getName()+"§c was §lmuted §e"+p.getName()+" §e"+duration+" §ctime. Reason: §e§o"+reason);
            if(plugin.getConfig().getBoolean("Database_log")){
                mysql.Log(p.getName(), sender.getName(), dur2sec, muteplayers.GetDateTime(), reason);
            }
            return true;
        } else {
            p.sendMessage(muteplayers.getPrefix()+" §CYou are muted!\n§r§7 - §6Time §c"+muteplayers.ConvertSecToTime(dur2sec)+ "\n §r§7- §6Staff: §c"+p.getName()+"\n §r§7- §6Reason: §c§o"+reason);
            Mute mute = new Mute();
            mute.username = p.getName();
            mute.Staff = sender.getName();
            mute.reason = reason;
            mute.duration = dur2sec;
            mute.date = muteplayers.GetDateTime();
            User.mutes.add(mute);
            muteplayers.Log("§e"+sender.getName()+"§c was §lmuted §e"+p.getName()+" §e"+duration+" §ctime. Reason: §e§o"+reason);
        }
        if(plugin.getConfig().getBoolean("Database_log")){
            mysql.Log(p.getName(), sender.getName(), dur2sec, muteplayers.GetDateTime(), reason);
        }
        return false;
    }
    public String remove_str(String a){
        String removed = "";
        for (char i : a.toCharArray()){
            if (i == 'm' || i == 'd' || i == 's' || i == 'h'){
            } else {
                removed +=i;
            }
        }
        return removed;
    }
    public Boolean CheckNumber(String num){
        int numj = 0;
        try{
            numj = Integer.parseInt(num);
            return true;

        }catch (NumberFormatException e){
            return false;
        }
    }
}
