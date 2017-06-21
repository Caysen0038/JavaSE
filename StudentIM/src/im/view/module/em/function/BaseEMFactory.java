package im.view.module.em.function;

import im.view.module.Module;
import im.view.module.em.EM;

public class BaseEMFactory {
	public static Module getFunction(int index,EM em){
		switch(index){
			case 0:
				return Home.getFun(em);
			case 1:return UserCentral.getFun(em);
			case 2:
			case 3:
			default:return null;
		}
	}
}
