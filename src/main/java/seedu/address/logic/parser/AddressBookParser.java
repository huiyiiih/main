package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UpdatePhotoCommand;
import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.CheckScheduleCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.logic.commands.event.RepeatCommand;
import seedu.address.logic.commands.event.ToggleTimetableCommand;
import seedu.address.logic.commands.relationship.SetRelCommand;
import seedu.address.logic.parser.event.AddEventCommandParser;
import seedu.address.logic.parser.event.DeleteEventCommandParser;
import seedu.address.logic.parser.event.EditEventCommandParser;
import seedu.address.logic.parser.event.RepeatCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.relationship.SetRelCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    private static CheckCommandsParser checkCommand = new CheckCommandsParser();
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        commandWord = checkCommand.matchCommand(commandWord);
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD_ALL:
            return new ListCommand(ListCommand.Option.ALL);

        case ListCommand.COMMAND_WORD_EVENTS:
            return new ListCommand(ListCommand.Option.EVENTS);

        case ListCommand.COMMAND_WORD_PERSONS:
            return new ListCommand(ListCommand.Option.PERSONS);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case CheckScheduleCommand.COMMAND_WORD:
            return new CheckScheduleCommand();

        case SetRelCommand.COMMAND_WORD:
            return new SetRelCommandParser().parse(arguments);

        case UpdatePhotoCommand.COMMAND_WORD:
            return new UpdatePhotoCommandParser().parse(arguments);

        case RepeatCommand.COMMAND_WORD:
            return new RepeatCommandParser().parse(arguments);

        case ToggleTimetableCommand.COMMAND_WORD:
            return new ToggleTimetableCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
