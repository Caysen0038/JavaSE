package im.view.module.em;

public class EMFactory {
	public static EM getEM(int type){
		/**
		 * @param type ģ�����ͣ����ز�ͬģ�飬ͬʱ��Ϊģ�����ʹ���ģ��
		 */
		switch(type){
			case 0:return new FileEM(String.valueOf(type));
			case 1:return new BaseEM(String.valueOf(type));
			case 2:return new ClassEM(String.valueOf(type));
			case 3:return new ScoreEM(String.valueOf(type));
			case 4:return new CourseEM(String.valueOf(type));
			case 5:return new StudentEM(String.valueOf(type));
			case 6:return new TAndAEM(String.valueOf(type));
			case 7:
			default:return null;
		}
	}
}
