package seedu.address.transaction.commands;

import java.util.logging.Logger;
import seedu.address.person.commons.core.LogsCenter;
import seedu.address.transaction.model.Model;
import seedu.address.transaction.model.Transaction;
import seedu.address.transaction.ui.TransactionUi;

public class AddCommand extends Command {
    private Transaction transaction;
    public static final String COMMAND_WORD = "add";
    private final Logger logger = LogsCenter.getLogger(getClass());

    public AddCommand(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public CommandResult execute(Model model, seedu.address.person.model.Model personModel) {
        TransactionUi transactionUi = new TransactionUi();
        model.addTransaction(transaction);
        logger.info(transaction.toString());
        return new CommandResult(transactionUi.addedTransaction(transaction));
    }
}
