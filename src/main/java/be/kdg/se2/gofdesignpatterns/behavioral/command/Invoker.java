package be.kdg.se2.gofdesignpatterns.behavioral.command;

class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
