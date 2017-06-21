package im.util.enums;

public enum MouseEnum {
	/*
	 * Êó±ê×´Ì¬
	 */
	NONE(0),ENTERED(1),PRESSED(2),CLICKED(3),RELEASED(4),EXITED(5);
	private int state;
	private MouseEnum(int state){
		this.state=state;
	}
	public int getState(){
		return this.state;
	}
}
