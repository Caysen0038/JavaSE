package im.service.proxy;

import im.entity.Record;
import im.service.UpdateService;
import im.service.impl.UpdateServiceImple;

public class UpdateProxy implements UpdateService{

	private UpdateServiceImple update=null;
	@Override
	public int update(Record[] record) {
		update=new UpdateServiceImple();
		int n=update.update(record);
		update=null;
		return n;
	}

}
