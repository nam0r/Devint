package game;

import game.Piece.Tuple;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;

public class Pit {
	private int height;
	private int width;

	private ArrayList<int[]> pit = null;

	public Pit(int width, int height) {
		this.width = width;
		this.height = height;

		pit = new ArrayList<int[]>();

		makeCleanPit();
	}

	public void makeCleanPit() {
		pit.clear();

		for (int y = 0; y < height; y++) {
			addNewLine();
		}
	}

	public boolean insertPieceAt(Piece piece, int x, int y) {
		Tuple insertPos = new Tuple(x, y);
		if (isPieceInsertableIn(piece, insertPos)) {
			for (int i = 0; i < 4; i++)
				setBlockAt(i, new Tuple(piece.getPosOfBlock(i).x + insertPos.x,
						piece.getPosOfBlock(i).y + insertPos.y));

			return true;
		}

		return false;
	}

	public void setBlockAt(int value, Piece.Tuple pos) {
		setBlockAt(value, (int) pos.x, (int) pos.y);
	}

	public final void setBlockAt(int value, int x, int y) {
		pit.get(y)[x] = value;
	}

	public int getBlockAt(Vector2f pos) {
		return getBlockAt((int) pos.x, (int) pos.y);
	}

	public int getBlockAt(int x, int y) {
		return pit.get(y)[x];
	}

	public boolean isPieceInsertableIn(Piece piece, Tuple pos) {
		// return ( pos.x >= 0 && pos.x < size.x) && (pos.y >= 0 && pos.y <
		// size.y);
		boolean isInsertable = true;

		for (int idx = 0; idx < 4 && isInsertable; idx++) {
			Tuple blockPos = piece.getPosOfBlock(idx);

			isInsertable &= (pos.x + blockPos.x >= 0 && pos.x + blockPos.x < width)
					&& (pos.y + blockPos.y >= 0 && pos.y + blockPos.y < height)
					&& (getBlockAt((int) (pos.x + blockPos.x),
							(int) (pos.y + blockPos.y)) == -1);

		}

		return isInsertable;
	}

	public boolean isLineFull(int index) {
		boolean isFull = true;

		for (int idx = 0; idx < width && isFull; idx++) {
			isFull &= (getBlockAt(idx, index) != -1);
		}

		return isFull;
	}

	public int getNumberOfLines() {
		return height;
	}

	public int getNumberOfColumns() {
		return width;
	}

	public boolean hasFullLines() {
		for (int idx = 0; idx < height; idx++) {
			if (isLineFull(idx)) {
				return true;
			}
		}

		return false;
	}

	public void eraseLine(int index) {
		pit.remove(pit.get(index));

		addNewLine();
	}

	private void addNewLine() {
		int[] line = new int[width];
		for (int x = 0; x < width; x++)
			line[x] = -1;

		pit.add(line);
	}

}