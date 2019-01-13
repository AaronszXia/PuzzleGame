package PuzzleGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frame extends JFrame {
	//Frame的菜单栏
	JMenuBar bar = new JMenuBar();
	//JMenuBar bar的菜单
	JMenu menu = new JMenu("Game");
	//JMenu menu的菜单项1和菜单项2
	JMenuItem item1 = new JMenuItem("Start game");
	JMenuItem item2 = new JMenuItem("Quit game");
	//游戏图片显示区域对象
	Image img;
	
	JPanel panelParent = new JPanel();
	JButton btn1 = new JButton("Start");
	JButton btn2 = new JButton("Quit");
	JRadioButton radioBtn1_1 = new JRadioButton("图片1");
	JRadioButton radioBtn1_2 = new JRadioButton("图片2", true);
	JRadioButton radioBtn1_3 = new JRadioButton("图片3");
	
	JRadioButton radioBtn2_1 = new JRadioButton("低");
	JRadioButton radioBtn2_2 = new JRadioButton("中", true);
	JRadioButton radioBtn2_3 = new JRadioButton("高");
	
	JLabel label = new JLabel("B16111923 夏龙飞 @ All rights reserved.", JLabel.CENTER);
	//hard记录游戏难度，pic记录玩家选择使用的图片
	int hard = 4, pic = 1;
	//字符串数组分别记录三张示例图片的路径
	String filePath[] = new String[3];
	
	//构造方法，包含对游戏的初始化操作
	Frame() {
		this.setTitle("Welcome to the puzzle game!");
		init();
	}
	
	//游戏的初始化，设置各个组件和容器的属性，并将它们添加到Frame容器中
	void init() {
		
		filePath[0] = "src/PuzzleGame/Puzzle0.jpg";
		filePath[1] = "src/PuzzleGame/Puzzle1.jpg";
		filePath[2] = "src/PuzzleGame/Puzzle2.jpg";
		
		img = new Image(hard, filePath[pic]);
		setBounds(0, 0, img.imageWidth, img.imageHeight + 210);
		setJMenuBar(bar);
		add(panelParent);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//--------------
		setItem();
		setMenu();
		setBar();
		setBtn();
		setPanel();
		validate();		//刷新JFrame
		//---------------
	}
	
	//游戏界面刷新，供用户改变游戏难度或者更改示例图片是调用
	void fresh() {	
		panelParent.remove(img);
		img = new Image(hard, filePath[pic]);
		panelParent.add(img, BorderLayout.CENTER);
		
		remove(panelParent);
		add(panelParent);
		setBounds(0, 0, img.imageWidth, img.imageHeight + 200);
		validate();
	}
	
	//为JMenuItem类对象item1和item2添加监视器
	void setItem() {
		item1.addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						img.randomOrder();
					}
				}
		);
		item2.addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						System.exit(1);
					}
				}
		);
	}
	
	//在JMenu类对象menu上添加对象item1和item2
	void setMenu() {
		menu.add(item1);
		menu.addSeparator();
		menu.add(item2);
	}
	
	//为JMenuBar类对象bar上添加对象menu
	void setBar() {
		bar.add(menu);
	}
	
	//为JButton类对象添btn1和btn2添加监视器
	void setBtn() {
		btn1.addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						img.randomOrder();
					}
				}
		);
		btn2.addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						System.exit(1);
					}
				}
		);
	}
	
	//负责JPanel类对象panelParent及其所拥有的组件设置属性
	void setPanel() {
		JPanel panelChildSouth1 = new JPanel();
		panelChildSouth1.add(btn1);
		panelChildSouth1.add(btn2);
		
		//--select a picture--------------------------
		JPanel panelChildSouth2 = new JPanel();
		panelChildSouth2.setLayout(new FlowLayout());
		panelChildSouth2.add(new JLabel("示例图片"));
		
		panelChildSouth2.add(radioBtn1_1);
		radioBtn1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn1_1.isSelected()) {
					pic = 0;
					fresh();
				}
			}
		});
		panelChildSouth2.add(radioBtn1_2);
			radioBtn1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn1_2.isSelected()) {
					pic = 1;
					fresh();
				}
			}
		});
		panelChildSouth2.add(radioBtn1_3);
		radioBtn1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn1_3.isSelected()) {
					pic = 2;
					fresh();
				}
			}
		});
		ButtonGroup picture = new ButtonGroup();
		picture.add(radioBtn1_1);
		picture.add(radioBtn1_2);
		picture.add(radioBtn1_3);
		//-------------------------------------------
		
		//--set difficulty---------------------------
		JPanel panelChildSouth3 = new JPanel();
		panelChildSouth3.setLayout(new FlowLayout());
		panelChildSouth3.add(new JLabel("难度"));
		
		panelChildSouth3.add(radioBtn2_1);
		radioBtn2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn2_1.isSelected()) {
					hard = 3;
					fresh();
				}
			}
		});

		panelChildSouth3.add(radioBtn2_2);
		radioBtn2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn2_2.isSelected()) {
					hard = 4;
					fresh();					
				}
			}
		});
		
		panelChildSouth3.add(radioBtn2_3);
		radioBtn2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioBtn2_3.isSelected()) {
					hard = 5;
					fresh();
				}
			}
		});
		
		ButtonGroup difficulty = new ButtonGroup();
		difficulty.add(radioBtn2_1);
		difficulty.add(radioBtn2_2);
		difficulty.add(radioBtn2_3);
		//--------------------------------------------
		
		JPanel panelChild = new JPanel();
		panelChild.setLayout(new GridLayout(4, 1));
		panelChild.add(panelChildSouth1);
		panelChild.add(panelChildSouth2);
		panelChild.add(panelChildSouth3);
		panelChild.add(label);
		
		panelParent.setLayout(new BorderLayout());
		panelParent.removeAll();
		panelParent.add(img, BorderLayout.CENTER);
		panelParent.add(panelChild, BorderLayout.SOUTH);
		
	}
	
	
}
