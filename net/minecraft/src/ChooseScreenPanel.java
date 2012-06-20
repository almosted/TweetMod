package net.minecraft.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ChooseScreenPanel extends JFrame {
	
    JFileChooser fileChooser = new JFileChooser();

	public ChooseScreenPanel() {
	    this.getContentPane().add(fileChooser);
	    this.setLocationRelativeTo(null);
	    ActionListener actionListener = new ActionListener() {
	    	
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  
	        JFileChooser theFileChooser = (JFileChooser)actionEvent.getSource();
	        String command = actionEvent.getActionCommand();
	        
	        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
	          File selectedFile = theFileChooser.getSelectedFile();
	          	try {
	          		TweetMod.picturePc = selectedFile.getAbsoluteFile().toString();
	          		TweetMod.uploadScreen(TweetMod.picturePc);
	          		TweetMod.tweetWithPicture = true;
	          		dispose();
	          	} catch (IOException e) {
	          		e.printStackTrace();
	          	}  
	        }
	        else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
	        	dispose();
	        }
	      }
	    };
	    fileChooser.addActionListener(actionListener);
	    fileChooser.APPROVE_SELECTION.equals("Tweet this picture!");
	    fileChooser.setCurrentDirectory(ModLoader.getMinecraftInstance().getAppDir("minecraft" + File.separator + "screenshots"));
	    fileChooser.setVisible(true);

	  }
}
