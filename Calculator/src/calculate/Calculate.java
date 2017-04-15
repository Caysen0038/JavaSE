package calculate;
import java.math.*;
public class Calculate {
	public static BigDecimal countData(char[] exp){
		Data data=new Data(exp.length);
		for(int i=0;i<exp.length;i++){
			if((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){		//����exp������ջ
				String num="";
				while((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){
					num=num+String.valueOf(exp[i++]);
					if(i>=exp.length){
						break;
					}
				}
				data.putData(new BigDecimal(num));
				i--;
			}else if(exp[i]=='('){		//�ж����ţ�����'('����Ѱ�Ҷ�Ӧ��')'
				int left[]=new int[exp.length];
				int right[]=new int[exp.length];
				int ltop=0,rtop=0,lefttab=0,righttab=0,n=0;
				left[ltop++]=i++;
				lefttab+=1;
				while(righttab!=lefttab){	//ֱ���ҵ���ͬ������'('��')'
					if(exp[i]=='('){
						lefttab+=1;
						left[ltop++]=i;
					}else if(exp[i]==')'){
						righttab+=1;
						right[rtop++]=i;
					}
					i++;
				}
				char[] temp=new char[i-1-left[0]];	//����һ��'('�����һ��')'�м�����������������ݹ�
				for(int j=left[0]+1;j<i-1;j++){
					temp[n++]=exp[j];
				}
				data.putData(countData(temp));
				if(!data.isSignNull()&&(data.getSign()=='*'||data.getSign()=='/')){
					//�������ǰ��*����/������������һ��
					switch(data.outSign()){
						case '*':data.putData(myMul(data.outTop(),data.outTop()));break;
						case '/':BigDecimal t=data.outTop();
							data.putData(myDiv(data.outTop(),t));break;
					}
				}
				i--;	//����i--��ѭ������ʱ����һ����
			}else if(exp[i]=='*'||exp[i]=='/'){
				if(exp[i+1]=='('&&i+1<exp.length){
					data.putSign(exp[i]);
					continue;
				}
				switch(exp[i]){
					case '*':data.putData(myMul(data.outTop(),new BigDecimal(Calculate.nextNum(exp,i+1))));
							i=Calculate.nextSign(exp, i+1);
							//��Ϊ���������������Ҫ��һ������������
							break;
					case '/':data.putData(myDiv(data.outTop(),new BigDecimal(Calculate.nextNum(exp,i+1))));
							i=Calculate.nextSign(exp, i+1);break;
				}
			}else if(exp[i]=='+'||exp[i]=='-'){		//���ȼ��ͣ���ջ
					data.putSign((int)exp[i]);
			}
		}
		Calculate.reckon(data);	//������+��-���㣬���û�У�ֱ�ӷ���ԭ��
		return data.getTop();	//����ջ������ջ��Ψһһ����
	}
	
	public static boolean checkBracket(char[] exp){		//��֤�Ϸ���
		Bracket bracket=new Bracket(exp.length);
		boolean judge=true;
		for(int i=0;i<exp.length;i++){
			if(exp[i]=='('||exp[i]=='{'||exp[i]=='['){
				bracket.putBracket((int)exp[i]);
			}else if(exp[i]==')'){
				if(bracket.getTop()=='('){
					bracket.outTop();
				}else{
					judge=false;
					break;
				}
			}else if(exp[i]==']'){
				if(bracket.getTop()=='['){
					bracket.outTop();
				}else{
					judge=false;
					break;
				}
			}else if(exp[i]=='}'){
				if(bracket.getTop()=='{'){
					bracket.outTop();
				}else{
					judge=false;
					break;
				}
			}
		}
		if(!bracket.isNull()){
			judge=false;
		}
		return judge;
	}
	private static void reckon(Data data){		//�Ӽ�����
		while(!data.isSignNull()){
			switch(data.outSign()){
				case '+':data.putData(myAdd(data.outTop(),data.outTop()));break;
				case '-':BigDecimal temp=data.outTop();
					data.putData(mySub(data.outTop(),temp));
					break;
			}
		}
	}
	private static String nextNum(char[] exp,int i){	//����*��/ʱ��ȡ��һ��������
		String num="";
		while((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){
			num=num+String.valueOf(exp[i++]);
			if(i>=exp.length){
				break;
			}
		}
		return num;
	}
	private static int nextSign(char[] exp,int i){		//������ȡִ��nextNum����һ��i����ʼλ��
		while(exp[i]>='0'&&exp[i]<='9'){
			i++;
			if(i>=exp.length){
				break;
			}
		}
		return --i;
	}
	private static BigDecimal myAdd(BigDecimal num1,BigDecimal num2){
		return num1.add(num2);
	}
	private static BigDecimal mySub(BigDecimal num1,BigDecimal num2){
		return num1.subtract(num2);
	}
	private static BigDecimal myMul(BigDecimal num1,BigDecimal num2){
		return num1.multiply(num2);
	}
	private static BigDecimal myDiv(BigDecimal num1,BigDecimal num2){
		return num1.divide(num2,16,BigDecimal.ROUND_HALF_UP);//������������߱���16λС������������
	}
}
