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
		 * ��ֹʹ��
		 */
	}
	public QuitModule(FileEM em){
		this.em=em;
		
		setName("�˳�");
		setType("quit");
		
	}
	@Override
	public void load(){
		/**
		 * �������Ż�Ϊ�����Ի���ȷ�ϣ��ȴ��Ի������ƽ�������ӽ���
		 */
		System.exit(1);
	}
}
