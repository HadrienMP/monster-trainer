import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TrainerTest {
	@Test
	public void should_move() throws Exception {
		// Arrange
		Trainer trainer = new Trainer("toto", 1, 1, Orientation.SOUTH);
		
		// Act
		trainer.move(Move.FORWARD);
		
		// Assert
		assertThat(trainer.toString()).isEqualTo("toto 1-2 S, 0 monster(s)");
	}

	@Test
	public void hunter_should_starts_with_name() throws Exception {
		// Arrange
		Trainer trainer = new Trainer("Michel", 1, 1, Orientation.SOUTH);
		
		// Assert
		assertThat(trainer.toString()).isEqualTo("Michel 1-1 S, 0 monster(s)");
	}
}
