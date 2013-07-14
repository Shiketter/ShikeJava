package main;

public class StringCompression {

	public String comp(String str) {
		// aaabbcc → a3b2c2
		int length = str.length();
		//int spaceLength = 0;
		int cnt = 1;
		String newStr = "";

		char old = ' ';
		for(int i = 0; i<length; i++) {
			char ch = str.charAt(i);
			if(i > 0) {
				if(ch == str.charAt(i-1)) {
					cnt++;
				} else {
					newStr = newStr + old + "" + cnt;
					cnt = 1;
				}
			}
			if(i == str.length()-1) {
				newStr = newStr + ch + "" + cnt;
			}
			old = ch;
		}

		if(newStr.length() >= str.length() ) {
			return str;
		}

		return newStr;
	}
}
