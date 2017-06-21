package im.view.module.em;

import im.view.module.Module;
import im.view.window.MainWindow;

public abstract class EM extends Module{
	/**
	 * 软件主要功能模块，软件实现功能全部基于该模块之上，必须继承该模块
	 * 
	 * 继承该模块，需在类名后加EM标识(edit module)
	 *
	 * 操作模块暂且只允许取出子模块。不允许外部设置子模块
	 * 
	 * 以下为继承后必须实现参数
	 * @param tab 功能菜单标签，供用户选择模块下各种功能
	 * @param orerate 主要编辑模块，完成各种功能操作
	 * @param stateBar 模块中的状态，因为模块功能不同，状态栏实现不同，所以归属于模块管理，不纳入窗体
	 * @param admin 控制用户权限，根据用户权限提供不同的功能
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
