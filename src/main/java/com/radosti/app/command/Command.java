package com.radosti.app.command;

public interface Command {
    public boolean matches(String[] parts);
    public void execute(String[] parts);
}
