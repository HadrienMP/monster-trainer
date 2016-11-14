public class Trainer {

	private int x;
	private int y;
	private String name;

	public Trainer(String name, int x, int y, Orientation orientation) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public void move(Move moves) {
		y++;
	}

	@Override
	public String toString() {
		return name + " " + x + "-" + y + " S, 0 monster(s)";
	}

}