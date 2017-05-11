package game.mainScreenTeam;

import guiPractice.components.Action;

public interface Draggable {
	public boolean isHovered(int x, int y);
	//public void act() ;
	//public Action getAction();

	public int getX();

	public int getY();

	public void setX(int x);

	public void setY(int y);

	public int getOrigX();

	public int getOrigY();
}
