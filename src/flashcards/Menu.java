package flashcards;

import flashcards.commands.ExportCommand;
import flashcards.commands.ImportCommand;
import flashcards.console.ApplicationCommand;
import flashcards.console.ApplicationCommands;

import java.util.Scanner;

public class Menu {

    public static void menu(String[] args) {
        Database database = new Database();

        for (int i = 0; i < args.length / 2; i++) {
            if ("-import".equals(args[2 * i])) {
                ImportCommand importCommand = new ImportCommand(database);
                importCommand.importCards(args[2 * i + 1]);
            }
        }

        while (true) {
            database.print("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):");

            Scanner scanner = new Scanner(System.in);
            String commandText = scanner.nextLine().trim();
            database.appendLog(commandText);
            String command = commandText.replace(' ', '_').toUpperCase();

            if ("EXIT".equals(command)) {
                database.print("Bye bye!");
                for (int i = 0; i < args.length / 2; i++) {
                    if ("-export".equals(args[2 * i])) {
                        ExportCommand exportCommand = new ExportCommand(database);
                        exportCommand.exportCards(args[2 * i + 1]);
                    }
                }
                break;
            }

            try {
                ApplicationCommand applicationCommand = ApplicationCommands.valueOf(command).getCommand(database);
                applicationCommand.execute();
            } catch (IllegalArgumentException e) {
                database.print(String.format("Command not exists exception: %s", e.getMessage()));
            }

            database.print("");
        }
    }
}