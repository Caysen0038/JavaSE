package im.util.view;

public class Exist {
	private static String[] name={"phone","photo","email"};
	private static int length=name.length;
	public static boolean exist(String str){
		for(int i=0;i<length;i++){
			if(name[i].equals(str)){
				return true;
			}
		}
		return false;
	}
}
