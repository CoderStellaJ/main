package seedu.address.inventory.logic.parser;

import seedu.address.inventory.logic.commands.DeleteCommand;
import seedu.address.inventory.logic.commands.exception.NotANumberException;
import seedu.address.inventory.logic.parser.exception.InvalidNumberException;
import seedu.address.inventory.ui.InventoryMessages;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws NotANumberException if the user input does not conform the expected format
     */
    public static DeleteCommand parse(String userInput) throws NotANumberException, InvalidNumberException {
        int index;
        try {
            index = Integer.parseInt(userInput.substring(1));
        } catch (Exception e) {
            throw new NotANumberException(InventoryMessages.MESSAGE_NOT_A_NUMBER);
        }
        if (index < 1) {
            throw new InvalidNumberException(InventoryMessages.NO_SUCH_INDEX_INVENTORY);
        }
        DeleteCommand deleteCommand = new DeleteCommand(index);
        return deleteCommand;
    }
}
