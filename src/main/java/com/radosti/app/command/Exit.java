package com.radosti.app.command;

public class Exit implements Command{
    @Override
    public boolean matches(String[] parts) {
        if(parts.length == 1 && parts[0].equalsIgnoreCase("exit")){
            return true;
        }
        else{
        return false;}
    }
        public void execute(String[] parts){
            System.out.println("Goodbye!");
            System.exit(0);}
    }

