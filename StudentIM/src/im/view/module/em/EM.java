package im.view.module.em;

import im.view.module.Module;
import im.view.window.MainWindow;

public abstract class EM extends Module{
	/**
	 * �����Ҫ����ģ�飬���ʵ�ֹ���ȫ�����ڸ�ģ��֮�ϣ�����̳и�ģ��
	 * 
	 * �̳и�ģ�飬�����������EM��ʶ(edit module)
	 *
	 * ����ģ������ֻ����ȡ����ģ�顣�������ⲿ������ģ��
	 * 
	 * ����Ϊ�̳к����ʵ�ֲ���
	 * @param tab ���ܲ˵���ǩ�����û�ѡ��ģ���¸��ֹ���
	 * @param orerate ��Ҫ�༭ģ�飬��ɸ��ֹ��ܲ���
	 * @param stateBar ģ���е�״̬����Ϊģ�鹦�ܲ�ͬ��״̬��ʵ�ֲ�ͬ�����Թ�����ģ����������봰��
	 * @param admin �����û�Ȩ�ޣ������û�Ȩ���ṩ��ͬ�Ĺ���
	 */
	public static final int WIDTH=MainWindow.WIDTH;
	public static final int HEIGHT=850;
	public static final int TAB_HEIGHT=120;
	public static final int EDIT_HEIGHT=700;
	public static final int STATE_HEIGHT=30;
	public static final int ICON_WIDTH=170;
	public static final int ICON_HEIGHT=93;
	private static final long serialVersionUID = -926024551522394180L;
	public abstract Module getTabModule();
	public abstract Module getEditModule();
	public abstract Module getStateBarModule();
	public abstract String getData();
}
