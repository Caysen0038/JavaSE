package im.util;
import java.util.*;
public class RandomNumber {
	private static final Random RAN=new Random();
	public static int getNumber(int i){
		return RAN.nextInt(i);
	}
}
