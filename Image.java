package PuzzleGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Image extends JPanel implements MouseListener {
	
	//row和col分别记录拼图的行数和列数
	private int row;
	private int col;
	
    int imageWidth;			//读取的图像文件尺寸
    int imageHeight;
    
    private Cell cell[];	//创建一个按钮对象数组
    private Cell blankCell = null;
    
    private BufferedImage imgBuf = null;			//打开的图片文件
    private BufferedImage smallImgBuf = null;		//裁切后的小图片文件
    
    String filePath;	//图片文件的路径
    
    //构造方法，设置img对象中小图片cell的个数以及将要读取的示例图片的存放路径
    Image(int hard, String str) {
    	this.setLayout(null);
    	filePath = str;
    	row = hard;
    	col = hard;
    	cell = new Cell[row * col];
    	cutImage();
    	addCells();
    }
    
    //读取示例图片文件，并实现对其分割成指定数目的小图片，将小图片到每一个cell对象上面，为每一个cell对象设置其初始位置
    public void cutImage() {
    	int num = 0;
    	int iconWidth = 0;
    	int iconHeight = 0;
    	ImageIcon icon = null;
    	try {	//读取文件图像
    		imgBuf = ImageIO.read(new File(filePath));
    		imageWidth = imgBuf.getWidth();
			imageHeight = imgBuf.getHeight();
			iconWidth = imageWidth / col;
	    	iconHeight = imageHeight / row;
	    	//System.out.println("Image Width->" + imageWidth + "\nImage Height->" + imageHeight);
    	}
    	catch (IOException e) {
    		System.out.println(e);
		}
    	//通过访问Cell对象数组cell[]来对其初始化
    	for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                num = i*col+j;		//表示当前这个图像的坐标id，在数组中的下标
                if(num < row * col - 1) {
                    smallImgBuf = imgBuf.getSubimage(iconWidth*j, iconHeight*i, iconWidth, iconHeight);
                    icon = new ImageIcon(smallImgBuf);		//将图像转化成图标
                }
                else {		//使最后一张图像为空白图像
                	icon = new ImageIcon(
                    		"/Users/xialongfei/eclipse-workspace/BasicSoftwarePractice/src/White.jpg"
                    		);
                }
                cell[num] = new Cell(icon, num, iconWidth, iconHeight);	//添加图标到每一个cell对象上面
                cell[num].setLocation(iconWidth*j, iconHeight*i);		//为每一个图标设置位置
            }
        }
    	
    	blankCell = cell[cell.length - 1];		// 初始化空白格
    }
    
    //将cell对象一个一个地添加到img对象上，并且对每个cell对象添加监听机制
    public void addCells() {
    	for(int i = 0; i < cell.length; i++)
        {
            this.add(cell[i]);			//将每一个按钮添加到当前这个面板上面
            if(i < cell.length-1)		//空白格不添加监听机制
                cell[i].addMouseListener(this);
        }
    }
    
    //产生随机数，并通过setLocation(int x, int y)方法设置cell对象的位置
    public void randomOrder() {
    	Random random = new Random();
        for(int i = 0 ; i < cell.length ; i++) {
            
            int random1 = random.nextInt(cell.length);//cells的长度是9，但是他的上限是9，取不到9，所取值范围是0-8
            int random2 = random.nextInt(cell.length);
            
            int x = cell[random1].getX();
            int y = cell[random1].getY();	//获取下标是index1的数组元素按钮的坐标
            cell[random1].setLocation(cell[random2].getX(), cell[random2].getY());
            cell[random2].setLocation(x, y);
        }
    }

    //判断每个cell对象是否都在它原本的位置（玩家是否获胜）
    public boolean isWin() {
        for (int i = 0; i < cell.length; i++) {
            int x = cell[i].getX();
            int y = cell[i].getY();
            if (x / (imageWidth / col) + (y / (imageHeight / row) * col) != i) {
                return false;
            }
        }
        return true;
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {	//响应鼠标点击事件的方法
		Cell t = (Cell) e.getSource();
        int x = blankCell.getX();
        int y = blankCell.getY();
        
        if (t.getY() == y && t.getX() + imageWidth / col == x) {	// 图像向右走
            t.move('R');
            blankCell.move('L');
        }
        else if (t.getY() == y && t.getX() - imageWidth / col == x) {	// 图像向左走
            t.move('L');
            blankCell.move('R');
        }
        else if (t.getX() == x && t.getY() + imageHeight / row == y) {	// 图像向上走
            t.move('U');
            blankCell.move('D');
        }
        else if (t.getX() == x && t.getY() - imageHeight / row == y) {	// 图像向下走
            t.move('D');
            blankCell.move('U');
        }
        
        if (isWin()) {
            int choice = JOptionPane.showConfirmDialog(null, "过关！\n再来一局？", "提示", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {		// 表示再来一局
                this.randomOrder();
            }
            else				//表示退出游戏
                System.exit(1);
        }
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
    
}
