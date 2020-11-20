package com.mulesoft.command;

import com.mulesoft.model.PathOrFolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// Receiver
public class Command {
    // KVM of all the possible path
    public HashMap<String, String> routes = new HashMap<>();
    // List of available commands
    List<String> commands = new ArrayList<>();
    // User current location
    public String userActualUbication = "";

    public Command() {
        // initial user current location
        userActualUbication = "/home/user";
        // initial paths
        routes.put("/home", "home");
        routes.put("/home/user", "user");
        // initialize possible commands
        commands.add("ls");
        commands.add("pwd");
        commands.add("mkdir");
        commands.add("cd");
        commands.add("rm");
        commands.add("help");
        commands.add("exit");
    }

    public void pwd(){
        System.out.println(userActualUbication);
    }

    public void mkdir(String newPath) {

    }

    public void ls() {
        routes.entrySet().forEach(route -> {
            route.getKey().contains(userActualUbication);
        });
    }

    public void help() {
        commands.forEach(cmd -> {
            System.out.println(cmd);
        });
    }

    public void cd(String upOrDown) {
        if(upOrDown.equals("..")) {
            String thisPath = routes.get(userActualUbication);
            if(thisPath == null)
                System.out.println("directory you are trying to access does not exist");
            userActualUbication = userActualUbication.replace("/"+thisPath, "");
        }
    }

    public void rm(String toDelete) {

    }

}