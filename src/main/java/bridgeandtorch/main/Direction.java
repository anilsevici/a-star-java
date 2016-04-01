package bridgeandtorch.main;

public enum Direction {
	SourceToTarget(1), TargetToSource(2);

	private final int directioncode;

	Direction(int directioncode) {
		this.directioncode = directioncode;
	}

	public int getdirectioncode() {
		return this.directioncode;
	}

}
