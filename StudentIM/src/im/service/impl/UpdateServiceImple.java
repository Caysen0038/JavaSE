package im.service.impl;

import im.dao.factory.DAOFactory;
import im.entity.Record;
import im.service.UpdateService;

public class UpdateServiceImple implements UpdateService{

	public UpdateServiceImple(){
		
	}
	@Override
	public int update(Record[] record) {
		int n=0;
		for(int i=0,j=record.length;i<j;i++){
			try{
				DAOFactory.getDAO("record").doUpdate(record[i]);
			}catch(Exception e){
				continue;
			}
			n++;
		}
		return n;
	}

}
