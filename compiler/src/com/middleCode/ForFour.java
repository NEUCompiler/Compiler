package com.middleCode;

import java.util.ArrayList;
import java.util.zip.CRC32;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.wordScanner.WordScanner;

public class ForFour {
	
	private int i = 1,j=1,k=1;

	private ArrayList<Quat> quats = new ArrayList<>();
	WordScanner scanner = new WordScanner();
	
	 public static void main(String[] args) {
		 ForFour aForFour=new ForFour();
		 aForFour.forreadWord();
		 System.out.println(aForFour.quats.toString());
	 
	 }
	public void forreadWord() {
		String aString;
		Quat quat;
		while(!"".equals((aString=readWord()))) {
			if("program".equals(aString)) {
				quat = new Quat();
				forProgram(quat);
			}
			else if("begin".equals(aString)) {
				quat = new Quat();
				forBegain(quat);
				
			}
			else if("do".equals(aString)){
				quat =new Quat();
				fordo(quat);
				
			}
			else if("if".equals(aString)){
				quat = new Quat();
				forif(quat);
			}
			else if("else".equals(aString)){
				quat=new Quat();
				forElse(quat);
			}
			else if(":=".equals(aString)) {
				forSuanshu();
			}
			else if(("end".equals(aString))&&(";".equals(readWord()))) {
				quat = new Quat();
				forEndIfWhileLast(quat);
			}
			else if(("end".equals(aString))&&(".".equals(readWord()))) {
				quat=new Quat();
			    forEndLast(quat);				
		    }
		}
		
		System.out.println(quats.toString());
	} 
	
	public void forProgram(Quat quat) {
	     quat.setFirst("program");
	     quat.setSecond(readWord());
	     
	     quats.add(quat);     
	}
	
	public void forBegain(Quat quat) {
		quat.setFirst("begin");
		
		quats.add(quat);
		
	}
	
	public void fordo(Quat quat) {
		quat.setFirst("do");
		
		quats.add(quat);
	}
	public void forif(Quat quat) {
		String ifFirst=readWord();
		quat.setSecond(ifFirst);
		String ifSecond=readWord();
		quat.setFirst(ifSecond);
		String ifThird=readWord();
		quat.setThird(ifThird);
		String  result = "t" + i++;
		quat.setFourth(result);
		
		quats.add(quat);
		
		Quat secondQuat=new Quat();
		secondQuat.setFirst("if");
		secondQuat.setSecond(result);
		String  result1 = "t" + i++;
		secondQuat.setFourth(result1);
	
		quats.add(secondQuat);
		
		Quat thirdQuat=new Quat();
		thirdQuat.setFirst("then");
		
		quats.add(thirdQuat);
	}
	
	public void forElse(Quat quat) {
		quat.setFirst("else");
		String D="t"+k++;
		quat.setSecond(D);
	
		quats.add(quat);
	}
	
	public void forwhile(Quat quat) {
		quat.setFirst("while");
		
		quats.add(quat);
		
		Quat fivethQuat=new Quat();
		String whileFirst=readWord();
		fivethQuat.setSecond(whileFirst);
		String whileSecond=readWord();
		fivethQuat.setFirst(whileSecond);
		String  result = "t" + j++;
		fivethQuat.setFourth(result);
		
		quats.add(fivethQuat);	
	}
	
	public void forSuanshu() {
		Quat quat = new Quat();
		String cString;
		String dString = null;
		while(!(cString=readWord()).matches(";||if||while||while||end")) {
			dString=dString+cString;
		}
        //弹出while时，说明此时已经读完一个表达式或字符或数字
		//if(isExpression(dString)){
		//此处调用计算表达式或字符或数字的函数（dstring)
		
		
		if(cString.equals(";")){
			forSuanshu();
		}
		else if(cString.equals("if")){
			Quat quat1 = new Quat();
			forif(quat1);
		}
		else if (cString.equals("while")) {
			Quat quat2 = new Quat();
			forwhile(quat2);
		}
		else if(cString.equals("end"))	{
			if(";".equals(readWord())){
				Quat quat3 = new Quat();
				forEndIfWhileLast(quat);
			}
			else {
				Quat quat4 = new Quat();
				forEndLast(quat);
			}
		}
			
	}
	
	public void forEndLast(Quat quat) {
		quat.setFirst("end");
	    quat.setSecond(quats.get(0).getSecond());
	    
	    
	    quats.add(quat);
	}
	
	public void forEndIfWhileLast(Quat quat) {
		quat.setFirst("end");
		String dString=null;
		
		for (int i=quats.size(); i>0; i--) {
			
			String s = quats.get(i-1).getFirst();
			
			if ("if".equals(s) || "while".equals(s)) {
			dString=quats.get(i).getFourth();
				break;
			}
		}
		
		quat.setSecond(dString);
		
		quats.add(quat);
	}
	

	private String readWord() {
		return scanner.readWord();
	}// 词法读取w

	
}

