package im.view.module.em.function;

import im.view.module.Module;
import im.view.module.em.EM;

public class StudentEMFactory {
	public static Module getFunction(int index,EM em){
		switch(index){
			case 0:
				return StudentEdit.getFun(em);
			case 1:
				return StudentInsert.getFun(em);
			case 2:
			case 3:
//				return StudentInfo.getFun(em);
			default:return null;
		}
	}
}
