package im.view.module.em.function;

import im.view.module.Module;
import im.view.module.em.EM;

public class TAndAEMFactory {
	public static Module getFunction(int index,EM em){
		switch(index){
			case 0:
				return TeacherInfo.getFun(em);
			case 1:return AdminInfo.getFun(em);
			case 2:return AdminInsert.getFun(em);
			case 3:return TeacherInsert.getFun(em);
			case 4:
			case 5:
			default:return null;
		}
	}
}
