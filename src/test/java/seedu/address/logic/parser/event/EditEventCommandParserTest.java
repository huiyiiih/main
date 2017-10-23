package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_SOCCER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.TIMING_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.TIMING_SOCCER;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SOCCER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_SOCCER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MIDTERM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.model.event.Title;
import seedu.address.model.event.timeslot.Timing;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_MIDTERM, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_MIDTERM, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_MIDTERM, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE, Title.MESSAGE_TITLE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_TIMING, Timing.MESSAGE_TIMING_CONSTRAINTS); // invalid timing

        // invalid timing followed by valid email
        assertParseFailure(parser, "1" + INVALID_TIMING + DESCRIPTION_SOCCER, Timing.MESSAGE_TIMING_CONSTRAINTS);

        // valid title followed by invalid timing. The test case for invalid timing followed by valid timing
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TITLE_MIDTERM + INVALID_TIMING, Timing.MESSAGE_TIMING_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE + INVALID_TIMING + VALID_DESCRIPTION_MIDTERM,
                Title.MESSAGE_TITLE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + TIMING_SOCCER
                + DESCRIPTION_MIDTERM + TITLE_MIDTERM;

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withTitle(VALID_TITLE_MIDTERM)
                .withTiming(VALID_TIMING_SOCCER).withDescription(VALID_DESCRIPTION_MIDTERM).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + TIMING_SOCCER + DESCRIPTION_MIDTERM;

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withTiming(VALID_TIMING_SOCCER)
                .withDescription(VALID_DESCRIPTION_MIDTERM).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TITLE_MIDTERM;
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withTitle(VALID_TITLE_MIDTERM).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timing
        userInput = targetIndex.getOneBased() + TIMING_MIDTERM;
        descriptor = new EditEventDescriptorBuilder().withTiming(VALID_TIMING_MIDTERM).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_MIDTERM;
        descriptor = new EditEventDescriptorBuilder().withDescription(VALID_DESCRIPTION_MIDTERM).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + TIMING_MIDTERM + DESCRIPTION_MIDTERM
                + TIMING_MIDTERM + DESCRIPTION_MIDTERM + TIMING_SOCCER + DESCRIPTION_SOCCER;

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withTiming(VALID_TIMING_SOCCER)
                .withDescription(VALID_DESCRIPTION_SOCCER).build();

        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_TIMING + TIMING_SOCCER;
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withTiming(VALID_TIMING_SOCCER).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_SOCCER + INVALID_TIMING + TIMING_SOCCER;
        descriptor = new EditEventDescriptorBuilder().withTiming(VALID_TIMING_SOCCER)
                .withDescription(VALID_DESCRIPTION_SOCCER).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
