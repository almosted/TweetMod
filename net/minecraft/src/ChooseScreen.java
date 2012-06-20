package net.minecraft.src;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ChooseScreen extends JFrame
{  
	JFrame frame = new ChooseScreenPanel();
	
    public ChooseScreen() {
    	//frame.setTitle("Choose a screen (*.png)");
    	frame.setTitle("Choose a screen");
    	frame.setSize(600, 350);
        frame.setVisible(true);
      }
} 