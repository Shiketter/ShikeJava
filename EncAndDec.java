/**
 * 簡易暗号化復&号化クラス
 * @author SHO Sakamoto
 * @since 2013/07/06
 */
public class EncAndDec {

	private final String[] s = {"c", "r", "p", "d", "e", "a", "g", "h", "m", "j"};
	private final String subHyphen = "x";

	/**
	 * 数字→英字
	 * @param argument 変換元文字列
	 * @return 変換後文字列
	 */
	public String executeEnc(String argument) {

		if(argument == null || argument.equals("")) {
			return null;
		}

		for(int i=0; i<argument.length(); i++) {
			String str = String.valueOf(i);
			if(argument.indexOf(str) != -1) {
				argument = argument.replace(str, s[i]);
			}
		}

		if(argument.indexOf("-") != -1) {
			argument = argument.replace("-", subHyphen);
		}

		return argument;
	}

	/**
	 * 数字→英字
	 * @param argument 変換元文字列
	 * @param exclusion 除外したい文字列
	 * @return 変換後文字列
	 */
	public String executeEnc(String argument, String exclusion) {

		if(argument == null || argument.equals("") ||
				exclusion == null || exclusion.equals("")) {
			return null;
		}

		int index = argument.indexOf(exclusion);
		if(index == -1) {
			return null;
		}

		String wantEnc = argument.substring(0, index);

		for(int i=0; i<wantEnc.length(); i++) {
			String str = String.valueOf(i);
			if(wantEnc.indexOf(str) != -1) {
				wantEnc= wantEnc.replace(str, s[i]);
			}
		}

		if(wantEnc.indexOf("-") != -1) {
			wantEnc = wantEnc.replace("-", subHyphen);
		}

		return wantEnc + exclusion;
	}


	/**
	 * 英字→数字
	 * @param argument 変換元文字列
	 * @return 変換後文字列
	 */
	public String executeDec(String argument) {

		if(argument == null || argument.equals("")) {
			return null;
		}

		for(int i=0; i<argument.length(); i++) {
			String str = String.valueOf(i);
			if (i < 10) {
				if(argument.indexOf(s[i]) != -1) {
					argument = argument.replace(s[i], str);
				}
			}
		}

		if(argument.indexOf(subHyphen) != -1) {
			argument = argument.replace(subHyphen, "-");
		}

		return argument;
	}

	/**
	 * 英字→数字
	 * @param argument 変換元文字列
	 * @param exclusion 除外したい文字列
	 * @return 変換後文字列
	 */
	public String executeDec(String argument, String exclusion) {

		if(argument == null || argument.equals("") ||
				exclusion == null || exclusion.equals("")) {
			return null;
		}

		int index = argument.indexOf(exclusion);
		if(index == -1) {
			return null;
		}

		String wantDec = argument.substring(0, index);

		for(int i=0; i<wantDec.length(); i++) {
			String str = String.valueOf(i);
			if (i < 10) {
				if(wantDec.indexOf(s[i]) != -1) {
					wantDec = wantDec.replace(s[i], str);
				}
			}
		}

		if(wantDec.indexOf(subHyphen) != -1) {
			wantDec = wantDec.replace(subHyphen, "-");
		}

		return wantDec + exclusion;
	}


}
