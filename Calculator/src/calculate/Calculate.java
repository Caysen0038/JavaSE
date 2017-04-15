package calculate;
import java.math.*;
public class Calculate {
	public static BigDecimal countData(char[] exp){
		Data data=new Data(exp.length);
		for(int i=0;i<exp.length;i++){
			if((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){		//遍历exp提数进栈
				String num="";
				while((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){
					num=num+String.valueOf(exp[i++]);
					if(i>=exp.length){
						break;
					}
				}
				data.putData(new BigDecimal(num));
				i--;
			}else if(exp[i]=='('){		//判断括号，出现'('立即寻找对应的')'
				int left[]=new int[exp.length];
				int right[]=new int[exp.length];
				int ltop=0,rtop=0,lefttab=0,righttab=0,n=0;
				left[ltop++]=i++;
				lefttab+=1;
				while(righttab!=lefttab){	//直到找到相同数量的'('和')'
					if(exp[i]=='('){
						lefttab+=1;
						left[ltop++]=i;
					}else if(exp[i]==')'){
						righttab+=1;
						right[rtop++]=i;
					}
					i++;
				}
				char[] temp=new char[i-1-left[0]];	//将第一个'('和最后一个')'中间的所有数据提出打进递归
				for(int j=left[0]+1;j<i-1;j++){
					temp[n++]=exp[j];
				}
				data.putData(countData(temp));
				if(!data.isSignNull()&&(data.getSign()=='*'||data.getSign()=='/')){
					//如果括号前世*或者/，则立即运算一次
					switch(data.outSign()){
						case '*':data.putData(myMul(data.outTop(),data.outTop()));break;
						case '/':BigDecimal t=data.outTop();
							data.putData(myDiv(data.outTop(),t));break;
					}
				}
				i--;	//若不i--，循环自增时会跳一个数
			}else if(exp[i]=='*'||exp[i]=='/'){
				if(exp[i+1]=='('&&i+1<exp.length){
					data.putSign(exp[i]);
					continue;
				}
				switch(exp[i]){
					case '*':data.putData(myMul(data.outTop(),new BigDecimal(Calculate.nextNum(exp,i+1))));
							i=Calculate.nextSign(exp, i+1);
							//因为向后提数，所以需要跳一个数继续操作
							break;
					case '/':data.putData(myDiv(data.outTop(),new BigDecimal(Calculate.nextNum(exp,i+1))));
							i=Calculate.nextSign(exp, i+1);break;
				}
			}else if(exp[i]=='+'||exp[i]=='-'){		//优先级低，进栈
					data.putSign((int)exp[i]);
			}
		}
		Calculate.reckon(data);	//最后进行+和-运算，如果没有，直接返回原数
		return data.getTop();	//返回栈顶，即栈内唯一一个数
	}
	
	public static boolean checkBracket(char[] exp){		//验证合法性
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
	private static void reckon(Data data){		//加减运算
		while(!data.isSignNull()){
			switch(data.outSign()){
				case '+':data.putData(myAdd(data.outTop(),data.outTop()));break;
				case '-':BigDecimal temp=data.outTop();
					data.putData(mySub(data.outTop(),temp));
					break;
			}
		}
	}
	private static String nextNum(char[] exp,int i){	//用于*或/时提取下一个数计算
		String num="";
		while((exp[i]>='0'&&exp[i]<='9')||exp[i]=='.'){
			num=num+String.valueOf(exp[i++]);
			if(i>=exp.length){
				break;
			}
		}
		return num;
	}
	private static int nextSign(char[] exp,int i){		//用于提取执行nextNum后下一个i的起始位置
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
		return num1.divide(num2,16,BigDecimal.ROUND_HALF_UP);//对于无理数最高保留16位小数并四舍五入
	}
}
