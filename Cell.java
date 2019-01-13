package PuzzleGame;

import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton {
	//id——cell本应该所处的位置，iconWidth、iconHeight——图标大小
	private int id, iconWidth, iconHeight;		
	
	//构造方法
	Cell(Icon icon, int id, int iconWidth, int iconHeight) {
		setIcon(icon);
		setSize(iconWidth, iconHeight);
		this.id = id;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}
	
	//控制cell对象的移动
	public void move(char to) {
		Rectangle rec = this.getBounds();	//获取当前对象的这个边框
		if (to == 'R') {
			setLocation(rec.x + iconWidth, rec.y);
		}
		else if (to == 'D') {
			setLocation(rec.x, rec.y - iconHeight);
		}
		else if (to == 'L') {
			setLocation(rec.x - iconWidth, rec.y);
		}
		else if (to == 'U') {
			setLocation(rec.x, rec.y + iconHeight);
		}
	}
	public int getX() {
        return getBounds().x;
    }
    public int getY() {
        return getBounds().y;
    }
	public int getId() {
        return id;
    }
    
}
