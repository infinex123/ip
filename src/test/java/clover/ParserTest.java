package clover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseValidCommandReturnsCorrectType() throws DukeException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand, "Expected a ListCommand for 'list' input");
    }

    @Test
    void parseInvalidCommandThrows() {
        assertThrows(DukeException.class, () -> Parser.parse("thisisnotacommand"));
    }
}
