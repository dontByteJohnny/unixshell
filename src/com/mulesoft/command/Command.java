package com.mulesoft.command;

import com.mulesoft.model.PathOrFolder;

import java.util.*;
import java.util.stream.Collectors;

// Receiver
public class Command {
    // KVM of all the possible path
    public HashMap<String, PathOrFolder> routes = new HashMap<>();
    // List of available commands
    List<String> commands = new ArrayList<>();
    // User current location
    public String userActualUbication = "";

    public Command() {
        // initial user current location
        userActualUbication = "/home/user";
        // initial paths
        routes.put("/home", new PathOrFolder("home", "folder"));
        routes.put("/home/user", new PathOrFolder("user", "folder"));
        routes.put("/home/file.txt", new PathOrFolder("file.txt", "file"));
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
        routes.put(userActualUbication+"/"+newPath, new PathOrFolder(newPath, "folder"));
    }

    public void touch(String newPath) {
        routes.put(userActualUbication+"/"+newPath, new PathOrFolder(newPath, "file"));
    }

    public void ls() {
        int actualPositionLength = userActualUbication.split("/").length;
        Set<String> pathsToPrint = new HashSet<>();
        routes.entrySet().stream().forEach(route -> {
            String path = route.getKey();
            String[] pathSplitted = path.split("/");
            if (path.contains(userActualUbication) && pathSplitted.length > actualPositionLength) {
                String pathOrFileInActualUbication = pathSplitted[actualPositionLength];
                pathsToPrint.add(pathOrFileInActualUbication);
            }
        });
        pathsToPrint.forEach(path -> System.out.println(path));
    }

    public void lsRecursive() {
        int actualPositionLength = userActualUbication.split("/").length;
        routes.entrySet().forEach(route -> {
            String path = route.getKey();
            if (path.contains(userActualUbication) && path.split("/").length >actualPositionLength) {
                PathOrFolder pathOrFolder = routes.get(userActualUbication);
                String pathOrFileInActualUbication = path.split(pathOrFolder.getName())[1];
                System.out.println(pathOrFileInActualUbication);
            }
        });
    }

    public void help() {
        commands.forEach(cmd -> {
            System.out.println(cmd);
        });
    }

    public void cd(String upOrDown) {
        String auxUserUbication;
        if(upOrDown.equals("..")) {
            PathOrFolder thisPath = routes.get(userActualUbication);
            auxUserUbication = userActualUbication.replace("/"+thisPath.getName(), "");
        } else {
            auxUserUbication = userActualUbication + "/" + upOrDown;
        }
        PathOrFolder pathOrFolder = routes.get(auxUserUbication);
        if(pathOrFolder == null)
            System.out.println("directory does not exists");
        else if("file".equals(pathOrFolder.getType()))
            System.out.println("trying to navegate into a file");
        else
            userActualUbication = auxUserUbication;
    }

    public void rm(String toDelete) {
        String pathOrFileToDelete = userActualUbication + "/" + toDelete;
        PathOrFolder pathOrFolder = routes.get(pathOrFileToDelete);
        if(pathOrFolder != null)
            routes.remove(pathOrFileToDelete);
    }

}