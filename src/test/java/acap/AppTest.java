package acap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {

    @Test
    void testClearTerminalDoesNotThrow() {
        assertDoesNotThrow(App::clearTerminal);
    }
}
