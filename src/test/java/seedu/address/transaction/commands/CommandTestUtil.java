package seedu.address.transaction.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.util.CliSyntax.PREFIX_DATETIME;
import static seedu.address.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.util.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.transaction.model.Model;
import seedu.address.transaction.model.TransactionContainsKeywordsPredicate;
import seedu.address.transaction.util.TransactionList;

public class CommandTestUtil {
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_NAME_BENSEN= "Benson Meier";
    public static final String VALID_DATE = "03-Sep-2019";
    public static final String VALID_AMOUNT = "20";
    public static final String VALID_DESC = "DeScRiPtIoN @12345";
    public static final String VALID_CATEGORY = "cAtEgOrY @12345";

    public static final String DESC_NAME_ALICE = PREFIX_NAME + "Alice Pauline";
    public static final String DESC_NAME_BENSEN= PREFIX_NAME + "Benson Meier";
    public static final String DESC_DATE = PREFIX_DATETIME + "03-Sep-2019";
    public static final String DESC_AMOUNT = PREFIX_AMOUNT + "20";
    public static final String DESC_DESC = PREFIX_DESCRIPTION + "DeScRiPtIoN @12345";
    public static final String DESC_CATEGORY = PREFIX_CATEGORY + "cAtEgOrY @12345";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link seedu.address.person.logic.commands.CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model transactionModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel, seedu.address.person.model.Model personModel) {
        try {
            CommandResult result = command.execute(transactionModel, personModel);
            System.out.println("inside test util:" + expectedCommandResult.getFeedbackToUser());
            System.out.println(result.getFeedbackToUser());
            assertEquals(expectedCommandResult, result);
            System.out.println("did first assert equals");
            assertEquals(expectedModel, transactionModel);
            //consider checking the filtered list
        } catch (Exception ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model, seedu.address.person.model.Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model transactionModel,
                                            String expectedMessage,
                                            Model expectedModel, seedu.address.person.model.Model personModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, transactionModel, expectedCommandResult, expectedModel, personModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage,
                                            seedu.address.person.model.Model personModel) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TransactionList transactionList = actualModel.getTransactionList();
        TransactionList expectedFilteredList = actualModel.getFilteredList();

        assertThrows(Exception.class, expectedMessage, () -> command.execute(actualModel, personModel));
        assertEquals(transactionList, actualModel.getTransactionList());
        assertEquals(expectedFilteredList, actualModel.getFilteredList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person with the given name in the
     * {@code model}'s transaction list
     */
    public static void showTransactionsOfPerson(Model model, String name) {
        assertTrue(model.hasTransactionWithName(name));
        final String[] splitName = name.split("\\s+");
        model.updatePredicate(new TransactionContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        //to apply the predicate on the filtered list
        model.getFilteredList();
    }
}
