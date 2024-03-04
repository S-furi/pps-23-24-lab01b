package e1;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PositioningPolicyTest {
    private PositioningPolicy positioningPolicy;

    @Test
    void testRandomPositioningPolicyCreationIsNotNull() {
        this.positioningPolicy = new RandomPositioningPolicy();
        assertNotNull(this.positioningPolicy);
    }

    @Test
    void testDeterministicPositioningPolicyCreationIsNotNull() {
        this.positioningPolicy = new DeterministicPositioningPolicy();
        assertNotNull(this.positioningPolicy);
    }
}
