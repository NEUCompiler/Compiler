package com.middleCode;

import java.util.ArrayList;
import java.util.zip.CRC32;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.signTable.SignTable;
import com.wordScanner.WordScanner;

public class ForFour {

	private int i = 1, j = 1, k = 1;

	private ArrayList<Quat> quats = new ArrayList<>();
	WordScanner scanner = new WordScanner();
	SignTable signTable = new SignTable();

	public static void main(String[] args) {
		ForFour aForFour = new ForFour();
		aForFour.forreadWord();
		// System.out.println(aForFour.quats.toString());

	}

	public void forreadWord() {
		String aString;
		Quat quat;
		String preWord = null;
		while (!"".equals((aString = readWord()))) {
			if ("program".equals(aString)) {
				quat = new Quat();
				forProgram(quat);
			} else if ("begin".equals(aString)) {
				quat = new Quat();
				forBegain(quat);

			} else if ("do".equals(aString)) {
				quat = new Quat();
				fordo(quat);

			} else if ("if".equals(aString)) {
				quat = new Quat();
				forif(quat);
			} else if ("else".equals(aString)) {
				quat = new Quat();
				forElse(quat);
			} else if (":=".equals(aString)) {
				forSuanshu(preWord);
			} else if (("end".equals(aString)) && (";".equals(readWord()))) {
				quat = new Quat();
				forEndIfWhileLast(quat);
			} else if (("end".equals(aString)) && (".".equals(readWord()))) {
				quat = new Quat();
				forEndLast(quat);
			}
			preWord = aString;
		}

		displayQuats();
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
		String ifFirst = readWord();
		quat.setSecond(ifFirst);
		String ifSecond = readWord();
		quat.setFirst(ifSecond);
		String ifThird = readWord();
		quat.setThird(ifThird);
		String result = "t" + i++;
		quat.setFourth(result);

		quats.add(quat);

		Quat secondQuat = new Quat();
		secondQuat.setFirst("if");
		secondQuat.setSecond(result);
		String result1 = "t" + i++;
		secondQuat.setFourth(result1);

		quats.add(secondQuat);

		Quat thirdQuat = new Quat();
		thirdQuat.setFirst("then");

		quats.add(thirdQuat);
	}

	public void forElse(Quat quat) {
		quat.setFirst("else");
		String D = "t" + k++;
		quat.setSecond(D);

		quats.add(quat);
	}

	public void forwhile(Quat quat) {
		quat.setFirst("while");

		quats.add(quat);

		Quat fivethQuat = new Quat();
		String whileFirst = readWord();
		fivethQuat.setSecond(whileFirst);
		String whileSecond = readWord();
		fivethQuat.setFirst(whileSecond);
		String result = "t" + j++;
		fivethQuat.setFourth(result);

		quats.add(fivethQuat);
	}

	/**
	 * 对算数表达式进行处理。
	 * 
	 * @param preWord
	 */
	public void forSuanshu(String preWord) {
		String pre = null;
		Quat quat = new Quat();
		String cString;
		String dString = "";
		while (!(cString = readWord()).matches(";||if||while||while||end")) {
			dString = dString + signTable.replaceVariable(cString);
			if (":=".equals(cString)) {
				preWord = pre;
			}
			pre = cString;
		}
		// 弹出while时，说明此时已经读完一个表达式或字符或数字
		// if(isExpression(dString)){
		// 此处调用计算表达式或字符或数字的函数（dstring)
		Priority priority = new Priority();
		priority.dealConverseExpression(dString);
		for (Quat q : priority.getQuats()) {
			if (q.getFirst() == null) {
				q.setFirst(preWord);
			}
			quats.add(q);
		}
		// System.out.println(priority.getQuats().toString());

		if (cString.equals(";")) {
			forSuanshu(preWord);
		} else if (cString.equals("if")) {
			Quat quat1 = new Quat();
			forif(quat1);
		} else if (cString.equals("while")) {
			Quat quat2 = new Quat();
			forwhile(quat2);
		} else if (cString.equals("end")) {
			if (";".equals(readWord())) {
				Quat quat3 = new Quat();
				forEndIfWhileLast(quat);
			} else {
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
		String dString = null;

		for (int i = quats.size(); i > 0; i--) {

			String s = quats.get(i - 1).getFirst();

			if ("if".equals(s) || "while".equals(s)) {
				dString = quats.get(i).getFourth();
				break;
			}
		}

		quat.setSecond(dString);

		quats.add(quat);
	}

	/**
	 * 判断是不是表达式。
	 * 
	 * @param code
	 *            带判断代码。
	 * @return
	 */
	public boolean isExpression(String code) {

		if (code.contains("+") || code.contains("-") || code.contains("*")
				|| code.contains("/")) {
			return true;
		}
		return false;
	}

	private String readWord() {
		return scanner.readWord();
	}// 词法读取w

	/**
	 * 显示四元式。
	 */
	public void displayQuats() {
		System.out.println("四元式：    共" + quats.size() + "个");

		for (Quat quat : quats) {
			System.out.println(quat.toString());
		}
		System.out.println();
	}

}
