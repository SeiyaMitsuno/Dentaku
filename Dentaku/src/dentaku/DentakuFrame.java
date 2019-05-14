package dentaku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DentakuFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField(""); //計算結果を表示するテキストフィールド
	double stackedValue = 0.0; //演算子ボタンを押す前にテキストフィールドにあった値
	boolean isStacked = false; //stackedValueに数値を入力したかどうか
	boolean afterCalc = false; //演算子ボタンを押した後かどうか
	String currentOp = ""; //押された演算子ボタンの名前

	//フレームのビルド
	public DentakuFrame() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(250, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("電子式卓上計算機");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH); //テキストフィールドを配置

		JPanel keyPanel = new JPanel(); //ボタンを配置するパネルを用意
		keyPanel.setLayout(new GridLayout(4, 4)); //4行4列のGridLayoutにする
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new NumberButton("7"), 0); //ボタンをレイアウトにはめこんでいく
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new CalcButton("÷"), 3);
		keyPanel.add(new NumberButton("4"), 4);
		keyPanel.add(new NumberButton("5"), 5);
		keyPanel.add(new NumberButton("6"), 6);
		keyPanel.add(new CalcButton("×"), 7);
		keyPanel.add(new NumberButton("1"), 8);
		keyPanel.add(new NumberButton("2"), 9);
		keyPanel.add(new NumberButton("3"), 10);
		keyPanel.add(new CalcButton("－"), 11);
		keyPanel.add(new NumberButton("0"), 12);
		keyPanel.add(new NumberButton("."), 13);
		keyPanel.add(new CalcButton("＋"), 14);
		keyPanel.add(new CalcButton("＝"), 15);

		JPanel keyPanel2 = new JPanel();
		keyPanel2.setLayout(new GridLayout(1,4));
		contentPane.add(keyPanel2, BorderLayout.SOUTH);
		keyPanel2.add(new AllClearButton(),0);
		keyPanel2.add(new ClearButton(),1);
		keyPanel2.add(new PlusMinusButton("±"), 2);
		keyPanel2.add(new SqrtButton("√"), 3);
		this.setVisible(true);
	}

	/* テキストフィールドに引数の文字列をつなげる */
	public void appendResult(String c) {
		if (!afterCalc) { //演算子ボタンを押した直後でないなら
			result.setText(result.getText() + c); //押したボタンの名前をつなげる
			result.setHorizontalAlignment(JTextField.RIGHT);	//計算結果を右寄せ
		}
		else {
			result.setText(c); //押したボタンの文字列だけを設定する（いったんクリアしたかに見える）
			afterCalc = false;
		}
	}

	/* 数字を入力するボタンの定義 */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(String keyTop) {
			super(keyTop); //JButtonクラスのコンストラクタを呼び出す
			this.addActionListener(this); //このボタンにアクションイベントのリスナを設定
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText(); //ボタンの名前を取り出す
			appendResult(keyNumber); //ボタンの名前をテキストフィールドにつなげる
		}
	}

	/* 演算子ボタンを定義 */
	public class CalcButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public CalcButton(String op) {
			super(op);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (isStacked) { //以前に演算子ボタンが押されたのなら計算結果を出す
				double resultValue = (Double.valueOf(result.getText()))
						.doubleValue();
				if (currentOp.equals("＋")) //演算子に応じて計算する
					stackedValue += resultValue;
				else if (currentOp.equals("－"))
					stackedValue -= resultValue;
				else if (currentOp.equals("×"))
					stackedValue *= resultValue;
				else if (currentOp.equals("÷"))
					stackedValue /= resultValue;

				//結果が小数点以下の数を含まないなら，int型で返す
				if(stackedValue % 1 <= 0.000001 && stackedValue % 1 >= -0.000001) {
					int IntstackedValue;
					IntstackedValue = (int)stackedValue;
					result.setText(String.valueOf(IntstackedValue)); //計算結果をテキストフィールドに設定
				}else {
					result.setText(String.valueOf(stackedValue)); //計算結果をテキストフィールドに設定
				}
			}
			currentOp = this.getText(); //ボタン名から押されたボタンの演算子を取り出す
			stackedValue = (Double.valueOf(result.getText()));
			afterCalc = true;
			if (currentOp.equals("＝"))
				isStacked = false;
			else
				isStacked = true;
		}
	}

	/* オールクリアボタンの定義 */
	public class AllClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public AllClearButton() {
			super("AC");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");
		}
	}

	/* クリアボタンの定義 */
	public class ClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ClearButton() {
			super("C");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			result.setText("");
		}
	}

	/* プラスマイナス反転ボタンの定義 */
	public class PlusMinusButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public PlusMinusButton(String pm) {
			super(pm);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			double resultValue = Double.valueOf(result.getText());
			resultValue = -resultValue;	//符号の反転
			//結果が小数点以下の数を含まないなら，int型で返す
			if(resultValue % 1 <= 0.000001 && resultValue % 1 >= -0.000001) {
				int IntresultValue;
				IntresultValue = (int)resultValue;
				result.setText(String.valueOf(IntresultValue)); //計算結果をテキストフィールドに設定
			}else {
				result.setText(String.valueOf(resultValue)); //計算結果をテキストフィールドに設定
			}
		}
	}

	/* √ボタンの定義 */
	public class SqrtButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public SqrtButton(String sqrt) {
			super(sqrt);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			double resultValue = Double.valueOf(result.getText());
			resultValue = Math.sqrt(resultValue);	//平方根の計算
			//結果が小数点以下の数を含まないなら，int型で返す
			if(resultValue % 1 <= 0.000001 && resultValue % 1 >= -0.000001) {
				int IntresultValue;
				IntresultValue = (int)resultValue;
				result.setText(String.valueOf(IntresultValue)); //計算結果をテキストフィールドに設定
			}else {
				result.setText(String.valueOf(resultValue)); //計算結果をテキストフィールドに設定
			}
		}
	}
}