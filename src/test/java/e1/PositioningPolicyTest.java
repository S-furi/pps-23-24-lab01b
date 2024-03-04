package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public abstract class PositioningPolicyTest {
    protected PositioningPolicy positioningPolicy;

    @BeforeAll
    void setUp() {
        this.positioningPolicy = createPositioningPolicy();
    }

    protected abstract PositioningPolicy createPositioningPolicy();

    @Test
    void testSomething() {
        assertAll(
            () -> assertNotNull(this.positioningPolicy.getKnightPosition()),
            () -> assertNotNull(this.positioningPolicy.getPawnPosition())
        );
    }
}
