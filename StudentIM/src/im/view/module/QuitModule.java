package im.view.module;

import im.view.module.em.FileEM;
public class QuitModule extends Module{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4177811153307618968L;
	@SuppressWarnings("unused")
	private FileEM em=null;
	@Deprecated
	@SuppressWarnings("unused")
	private QuitModule(){
		/*
		 * 禁止使用
		 */
	}
	public QuitModule(FileEM em){
		this.em=em;
		
		setName("退出");
		setType("quit");
		
	}
	@Override
	public void load(){
		/**
		 * 后期需优化为弹出对话框确认，等待对话框完善结束后添加进来
		 */
		System.exit(1);
	}
}
