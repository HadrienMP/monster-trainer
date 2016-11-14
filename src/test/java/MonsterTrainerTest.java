import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MonsterTrainerTest {

	MonsterTrainer monsterTrainer;
	
	@Test
	public void trainer_should_move_on_an_empty_map() throws Exception {
		// Arrange
		GameMap gameMap = new GameMap(2,2);
		Trainer trainer = new Trainer("Michel", 1, 1, Orientation.SOUTH);
		monsterTrainer = new MonsterTrainer(gameMap, trainer);
	
		// Act
		trainer.move(Move.FORWARD);
				
		// Assert
		assertThat(trainer.toString()).isEqualTo("Michel 1-2 S, 0 monster(s)");
	}
	
}