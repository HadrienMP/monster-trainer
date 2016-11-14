import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MonsterTrainerTest {

	MonsterTrainer monsterTrainer;
	
	/**
	 * T = Trainer initial position
	 * F = Final position
	 * 
	 *         T>vv<
	 *         v^Fv^
	 *         v^<<^
	 *         >>>>^
	 * 
	 * @throws Exception
	 */
	@Test
	public void trainer_should_move_on_an_empty_map() throws Exception {
		// Arrange
		GameMap gameMap = new GameMap(5,4);
		Trainer trainer = new Trainer("Michel", 1, 1, Orientation.SOUTH);
		monsterTrainer = new MonsterTrainer(gameMap, trainer);
	
		// Act
		trainer.move(Move.FORWARD, Move.FORWARD, Move.FORWARD, 
				Move.TURN_LEFT, Move.FORWARD, Move.FORWARD, Move.FORWARD, Move.FORWARD,
				Move.TURN_LEFT, Move.FORWARD, Move.FORWARD, Move.FORWARD,
				Move.TURN_LEFT, Move.FORWARD,
				Move.TURN_LEFT, Move.FORWARD, Move.FORWARD,
				Move.TURN_RIGHT, Move.FORWARD, Move.FORWARD,
				Move.TURN_RIGHT, Move.FORWARD, Move.FORWARD,
				Move.TURN_RIGHT, Move.FORWARD,
				Move.TURN_RIGHT, Move.FORWARD);;
				
		// Assert
		assertThat(trainer.toString()).isEqualTo("Michel 3-2 S, 0 monster(s)");
	}
	
}