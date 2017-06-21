package im.service.impl;
import im.service.*;
import im.dao.factory.DAOFactory;
import im.entity.*;
public class InsertServiceImpl implements InsertService{
	public InsertServiceImpl(){
		
	}
	@Override
	public int insert(Record[] record){
		/**
		 * ��Ϊ��Ҫִ�еļ�¼���ܻ��ж��������Բ�ȡ������ʽ���ݣ��ɸ��Ľӿ�����Ϊ�������ݲ���
		 */
		int n=0;
		for(int i=0,j=record.length;i<j;i++){
			try{
				DAOFactory.getDAO("record").doInsert(record[i]);
			}catch(Exception e){
				continue;
			}
			n++;
		}
		return n;
	}
}
