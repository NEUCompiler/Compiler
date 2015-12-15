package com.middleCode;

import java.util.ArrayList;

import com.wordScanner.WordScanner;

public class ForFour {
	
	private int i = 1,j=1;

	private ArrayList<Quat> quats = new ArrayList<>();
	WordScanner scanner = new WordScanner();
	
	 public static void main(String[] args) {
		 ForFour aForFour=new ForFour();
		 aForFour.forRead();
		 System.out.println(aForFour.quats.toString());
	 
	 }
	public void forRead() {
		String aString;
		Quat quat;
		while((aString=read()) != null) {
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
				quat = new Quat();
				forSuanshu(quat);
			}
			else if(("end".equals(aString))&&(";".equals(read()))) {
				quat = new Quat();
				forEndIfWhileLast(quat);
			}
			else if(("end".equals(aString))&&(".".equals(read()))) {
				quat=new Quat();
			    forEndLast(quat);				
		    }
		}
		
	} 
	
	public void forProgram(Quat quat) {
	     quat.setFirst("program");
	     quat.setSecond(read());
	     
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
		String ifFirst=read();
		quat.setSecond(ifFirst);
		String ifSecond=read();
		quat.setFirst(ifSecond);
		String ifThird=read();
		quat.setFirst(ifThird);
		String  result = "t" + i++;
		quat.setFourth(result);
		quats.add(quat);
		
		Quat secondQuat=new Quat();
		secondQuat.setFirst("if");
		secondQuat.setSecond(result);
		//第四项要指向if else之后语句
		
		quats.add(secondQuat);
		
		Quat thirdQuat=new Quat();
		thirdQuat.setFirst("then");
		
		quats.add(thirdQuat);
	}
	
	public void forElse(Quat quat) {
		quat.setFirst("else");
		String D="t"+i;
		quat.setSecond(D);
	
		quats.add(quat);
	}
	
	public void forwhile(Quat quat) {
		quat.setFirst("while");
		
		quats.add(quat);
		
		Quat fivethQuat=new Quat();
		String whileFirst=read();
		fivethQuat.setSecond(whileFirst);
		String whileSecond=read();
		fivethQuat.setFirst(whileSecond);
		String  result = "t" + j++;
		fivethQuat.setFourth(result);
		
		quats.add(fivethQuat);	
	}
	
	public void forSuanshu(Quat quat) {
		String cString;
		while((cString=read())!=";") {
			cString=cString+read();
		}
		Priority priority = new Priority();
		
		ArrayList<Quat> quaList = priority.dealConverseExpression(cString);
		for (Quat q : quaList) {
			quats.add(q);
		}
	}
	
	public void forEndLast(Quat quat) {
		quat.setFirst("end");
	    quat.setSecond(quats.get(0).getSecond());
	    
	    
	    quats.add(quat);
	}
	
	public void forEndIfWhileLast(Quat quat) {
		quat.setFirst("end");
		String dString;
		
		for (int i=quats.size(); i>0; i--) {
			
			String s = quats.get(i).getFirst();
			
			if ("if".equals(s) || "while".equals(s)) {
			dString=quats.get(i).getSecond();
				break;
			}
		}
		
		quat.setSecond("dString");
		
		quats.add(quat);
	}
	private String read() {
		return scanner.read();
	}// 词法读取w

}

