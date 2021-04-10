package flashcards.console;

import flashcards.Database;
import flashcards.commands.AddCommand;
import flashcards.commands.AskCommand;
import flashcards.commands.ExportCommand;
import flashcards.commands.HardestCardCommand;
import flashcards.commands.ImportCommand;
import flashcards.commands.LogCommand;
import flashcards.commands.RemoveCommand;
import flashcards.commands.ResetStatsCommand;

public enum ApplicationCommands {
    ADD {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new AddCommand(database);
        }
    },
    REMOVE {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new RemoveCommand(database);
        }
    },
    IMPORT {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new ImportCommand(database);
        }
    },
    EXPORT {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new ExportCommand(database);
        }
    },
    ASK {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new AskCommand(database);
        }
    },
    LOG {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new LogCommand(database);
        }
    },
    HARDEST_CARD {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new HardestCardCommand(database);
        }
    },
    RESET_STATS {
        @Override
        public ApplicationCommand getCommand(Database database) {
            return new ResetStatsCommand(database);
        }
    };

    public abstract ApplicationCommand getCommand(Database database);
}