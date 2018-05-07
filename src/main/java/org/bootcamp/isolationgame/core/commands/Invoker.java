package org.bootcamp.isolationgame.core.commands;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        (new Thread(() -> command.execute())).start();
    }
}
