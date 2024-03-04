package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public abstract class PositioningPolicyTest {
    protected PositioningPolicy positioningPolicy;
    protected final int size = 5;

    @BeforeAll
    void setUp() {
        this.positioningPolicy = createPositioningPolicy();
    }

    protected abstract PositioningPolicy createPositioningPolicy();

    @Test
    void testPositioningiPolicyShouldHaveNotNullPiecesPositions() {
        assertAll(
            () -> assertNotNull(this.positioningPolicy.getKnightPosition()),
            () -> assertNotNull(this.positioningPolicy.getPawnPosition())
        );
    }
}
