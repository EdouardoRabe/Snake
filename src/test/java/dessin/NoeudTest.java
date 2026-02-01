package dessin;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class NoeudTest {
    @Test
    public void constructorSetsFields() {
        Noeud n = new Noeud(10, 20, Color.RED);
        assertEquals(10, n.X);
        assertEquals(20, n.Y);
        assertEquals(Color.RED, n.couleur);
    }
}
