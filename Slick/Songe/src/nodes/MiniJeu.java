package nodes;


public class MiniJeu {
	private int id;
	
	private int[] levels;
	private int[] scores;
	
	public MiniJeu(
			int id,
			int niv1, int niv2, int niv3, int niv4,
			int score1, int score2, int score3, int score4
			) {
		
		this.id = id;
		
		levels = new int[] {niv1, niv2, niv3, niv4};
		scores = new int[] {score1, score2, score3, score4};
	}
	
	public int getLevelFromScore(int score) {
		for(int i=scores.length-2; i>=0; i--) {
			if(score > scores[i])
				return levels[i+1];
		}
		return levels[0];
	}
	
	public int getId() {
		return this.id;
	}

}