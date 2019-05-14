package dentaku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dentaku extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField(""); //計算結果を表示するテキストフィールド

	//フレームのビルド
	public Dentaku() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(250, 300));
		this.setTitle("電子式卓上計算機");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH); //テキストフィールドを配置

		JPanel keyPanel = new JPanel(); //ボタンを配置するパネルを用意
		keyPanel.setLayout(new GridLayout(4, 4)); //4行4列のGridLayoutにする
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new JButton("7"), 0); //ボタンをレイアウトにはめこんでいく
		keyPanel.add(new JButton("8"), 1);
		keyPanel.add(new JButton("9"), 2);
		keyPanel.add(new JButton("÷"), 3);
		keyPanel.add(new JButton("4"), 4);
		keyPanel.add(new JButton("5"), 5);
		keyPanel.add(new JButton("6"), 6);
		keyPanel.add(new JButton("×"), 7);
		keyPanel.add(new JButton("1"), 8);
		keyPanel.add(new JButton("2"), 9);
		keyPanel.add(new JButton("3"), 10);
		keyPanel.add(new JButton("－"), 11);
		keyPanel.add(new JButton("0"), 12);
		keyPanel.add(new JButton("."), 13);
		keyPanel.add(new JButton("＋"), 14);
		keyPanel.add(new JButton("＝"), 15);

		contentPane.add(new JButton("C"), BorderLayout.SOUTH);//Cボタンを配置する
		this.setVisible(true);
	}
}
