package im.view.module.em.function;

import im.view.module.Module;
import im.view.module.em.EM;

public class CourseEMFactory {
	public static Module getFunction(int index,EM em){
		switch(index){
			case 0:
				return CourseInfo.getFun(em);
			case 1:return CourseInsert.getFun(em);
			case 2:
			case 3:
			case 4:
			case 5:
			default:return null;
		}
	}
}
