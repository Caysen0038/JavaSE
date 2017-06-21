package im.view.module.em.function;

import im.view.module.Module;
import im.view.module.em.EM;

public class ScoreEMFactory {
	public static Module getFunction(int index,EM em){
		switch(index){
			case 0:
				return ScoreEdit.getFun(em);
			case 1:
				return ScoreInsert.getFun(em);
			case 2:
			case 3:
//				return ScoreInfo.getFun(em);
			default:return null;
		}
	}
}
