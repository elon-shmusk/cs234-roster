package tests.cs234_Project;

import src.Remove_Player;

import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class RemovePlayerTest {


    @Test
    public void testDialogPresence() {
        Remove_Player removePlayerDialog = new Remove_Player(null); // Pass null as parent panel for simplicity
        assertNotNull("Remove Player dialog not instantiated", removePlayerDialog);
        assertTrue("Remove Player dialog is not visible", removePlayerDialog.isVisible());
    }

    @Test
    public void testCheckBoxPresence() {
        Remove_Player removePlayerDialog = new Remove_Player(null); // Pass null as parent panel for simplicity
        assertNotNull("Player 1 checkbox not found", removePlayerDialog.player1);
        assertNotNull("Player 2 checkbox not found", removePlayerDialog.player2);
        assertNotNull("Player 3 checkbox not found", removePlayerDialog.player3);
    }
}
