
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class MakeVo {

	final String INPUT_PATH;
	final String OUTPUT_PATH;
	final String DELIMITER;

	String osName = null;
	String newLineChar = null;

	// 初期化
	File inputFile = null;
	File outputFile = null;

	FileReader fr = null;
	FileWriter fw = null;

	BufferedReader br = null;
	BufferedWriter bw = null;


	MakeVo(String input, String output, String delimiter) {
		// OS判別		
		osName = System.getProperty("os.name");
		if(osName.startsWith("Windows")) {
			newLineChar = "\r\n";
		} else {
			newLineChar = "\n";
		}
		INPUT_PATH = input;
		OUTPUT_PATH = output;
		DELIMITER = delimiter;
	}

	public void execute() {

		try {
			// ファイルを読み込む
			inputFile = new File(INPUT_PATH);
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);

			// 必要行数取得
			String line = br.readLine();
			int colNum = -2;
			while(line != null) {
				colNum++;
				line = br.readLine();
			}

			// アクセサ格納用配列生成
			String[] accesserSet = new String[colNum];
			String[] accesserGet = new String[colNum];

			//ファイル入出力
			line = null;
			inputFile = new File(INPUT_PATH);
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			line = br.readLine();
			StringTokenizer token = null;
			int i = -2;
			while(line != null) {
				token = new StringTokenizer(line, DELIMITER);

				while(token.hasMoreTokens()) {
					String type = token.nextToken();
					// 内容取得
					if(type.equals("クラス名")) {
						String className = token.nextToken();
						String outputPath = OUTPUT_PATH + className + ".java";
						outputFile = new File(outputPath);
						// クラス名と同名のJavaファイル生成
						outputFile.createNewFile();

						fw = new FileWriter(outputFile);
						bw = new BufferedWriter(fw);

						bw.write("public class " + className + " {");
						bw.newLine();
					} else if(type.equals("フィールド")){
						// 型
						String typeName = token.nextToken();
						// 変数名
						String variableName = token.nextToken();
						// レベル
						String secLevel = token.nextToken();
						// セッター内容
						if(token.nextToken().equals("○")) {
							accesserSet[i] = "\tpublic void set" +
									variableName.substring(0, 1).toUpperCase() +
									variableName.substring(1) +
									"("  + typeName + " " + variableName + ") {" + newLineChar +
									"\t\tthis." + variableName + " = " + variableName + ";" + newLineChar + 
									"\t}";
						}
						// ゲッター内容
						if(token.nextToken().equals("○")) {
							accesserGet[i] = "\tpublic " + typeName + " get" +
									variableName.substring(0, 1).toUpperCase() +
									variableName.substring(1) + "() {" + newLineChar +
									"\t\treturn this." + variableName + ";" + newLineChar + 
									"\t}";
						}
						bw.write("\t" + secLevel + " " + typeName + " " + variableName + ";");
						bw.newLine();
					}
				}

				line = br.readLine();
				i++;
			}
			bw.newLine();

			// セッター書き出し
			for(int j=0; j<accesserSet.length; j++) {
				if(accesserSet[j] != null) {
					bw.write(accesserSet[j]);
					bw.newLine();
					bw.newLine();
				}
			}

			// ゲッター書き出し
			for(int j=0; j<accesserGet.length; j++) {
				if(accesserGet[j] != null) {
					bw.write(accesserGet[j]);
					bw.newLine();
					bw.newLine();
				}
			}

			bw.write("}");

			br.close();
			bw.close();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
