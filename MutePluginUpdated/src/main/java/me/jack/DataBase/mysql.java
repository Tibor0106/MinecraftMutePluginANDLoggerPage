package me.jack.DataBase;

import me.jack.muteplayers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mysql {
    private Connection connection;
    public static String GetLogTableName(){
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class);
        String a =  plugin.getConfig().getString("database.data_table");
        return a;
    }
    public static Connection getConnection(){
        Plugin plugin = me.jack.muteplayers.getPlugin(me.jack.muteplayers.class);
        String username = plugin.getConfig().getString("database.username");
        String password= plugin.getConfig().getString("database.password");
        String db = plugin.getConfig().getString("database.database");
        String address = plugin.getConfig().getString("database.address");
        Integer port = plugin.getConfig().getInt("database.port");
        String url = "jdbc:mysql://"+address+":"+port+"/"+db+"";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException eq) {
            eq.printStackTrace();
        }
        return connection;
    }
    public static void Log(String username, String staff, int duration, String date, String reason){
        try{
            String Query = "INSERT INTO "+mysql.GetLogTableName()+" (staff, player, duration, reason, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prStmt = mysql.getConnection().prepareStatement(Query);
            prStmt.setString(1, staff);
            prStmt.setString(2,username);
            prStmt.setInt(3,duration);
            prStmt.setString(4,reason);
            prStmt.setString(5,date);
            prStmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
            Bukkit.getLogger().info(muteplayers.getPrefix()+" §cDatabase log fail! §echeck errors");
        }
    }
}
