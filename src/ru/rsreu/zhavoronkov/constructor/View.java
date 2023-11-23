package ru.rsreu.zhavoronkov.constructor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import ru.rsreu.zhavoronkov.base.display.Display;
import ru.rsreu.zhavoronkov.base.resource.LevelMapParser;
import ru.rsreu.zhavoronkov.base.resource.ResourceLoader;
import ru.rsreu.zhavoronkov.base.utils.ImgType;


public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final String SAVE = "Save";
	private static final String LOAD = "Load";
	JButton load;
	JButton save;
	JTextField fileName;
	ArrayList<JComboBox<ImageIcon>> cBoxes;
	ImageIcon[] imgs;

	public View() {
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel panel = new JPanel();
	    GridLayout gl = new GridLayout(0,36);
	    panel.setLayout(gl);

	    cBoxes = new ArrayList<JComboBox<ImageIcon>>();
	    for(int i = 0; i < 864; i++) {
	    	JComboBox<ImageIcon> comboBox = new JComboBox<ImageIcon>(loadImgs());
	    	comboBox.setUI(new javax.swing.plaf.metal.MetalComboBoxUI(){
	    	      public void layoutComboBox(Container parent, MetalComboBoxLayoutManager manager) {
	    	        super.layoutComboBox(parent, manager);
	    	        arrowButton.setBounds(0,0,0,0);
	    	      }
	    	    });
	    	comboBox.setPreferredSize(new Dimension(20, 20));
	    	panel.add(comboBox);
	    	cBoxes.add(comboBox);
	    }
	    UIManager.put("ComboBox.squareButton", Boolean.FALSE);
	    
	    JPanel controlsPanel = new JPanel();
	    controlsPanel.setLayout(new GridLayout(2, 10, 3, 20));
	    load = new JButton(LOAD);
	    save = new JButton(SAVE);
	    fileName = new JTextField(3);
	    controlsPanel.add(load);
	    controlsPanel.add(save);

	    JLabel la1 = new JLabel("File name");
	    la1.setHorizontalAlignment(JLabel.RIGHT);
	    controlsPanel.add(la1);
	    controlsPanel.add(fileName);
	    JLabel la2 = new JLabel(" ");
	    la2.setHorizontalAlignment(JLabel.RIGHT);
	    JLabel la3 = new JLabel(" ");
	    la3.setHorizontalAlignment(JLabel.RIGHT);
	    JLabel la4 = new JLabel(" ");
	    la4.setHorizontalAlignment(JLabel.RIGHT);
	    controlsPanel.add(la2);
	    controlsPanel.add(la3);
	    controlsPanel.add(la4);
	    
	    Container pane = getContentPane();
	    pane.add(controlsPanel, BorderLayout.NORTH);
	    pane.add(panel, BorderLayout.SOUTH);
	    
	    setListeners();
    
	    pack();
	    setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    
	}
	
	private void setListeners() {
	    load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadLevel();
			}
			
		});
	    
	    save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveLevel();
			}
			
		});
	}
	
	private void saveLevel() {
		List<List<Integer>> elements = new ArrayList<List<Integer>>();
		List<Integer> line = new ArrayList<Integer>();
		for(int i = 0; i < cBoxes.size(); i++) {
			JComboBox<ImageIcon> cBox = cBoxes.get(i);
			line.add(cBox.getSelectedIndex());
			if(line.size() == 36) {
				elements.add(line);
				line = new ArrayList<Integer>();
			}
		}
		String name = fileName.getText();
		if(name != null && !name.isEmpty()) {
			String path = "res/level/" + name + ".csv";
			LevelMapParser.writeToSCV(elements, path, ";");
		}		
	}
	
	private void loadLevel() {
		String name = fileName.getText();
		if(name != null && !name.isEmpty()) {
			List<List<Integer>> items = loadItems(name);
			int index = 0;
			for(List<Integer> row: items) {
				for(Integer cell: row) {
					JComboBox<ImageIcon> cBox = cBoxes.get(index);
					cBox.setSelectedIndex(cell);
					index++;
				}
			}
		}
	}
	
	private List<List<Integer>> loadItems(String name) {
		String path = "res/level/" + name + ".csv";
		List<List<Integer>> res = null;
		res = LevelMapParser.parseCSV(path, ";");
		if(res == null) {
			JOptionPane.showMessageDialog(Display.getWindow(), "Файл не найден!", "Внимание", JOptionPane.ERROR_MESSAGE);
		}
		return res;
	}
	
	private ImageIcon[] loadImgs() {
		if(imgs != null) {
			return imgs;
		}
		
		ImgType[] types = ImgType.values();
		imgs = new ImageIcon[types.length];
		
		for(int i = 0; i < types.length; i++) {
			ImgType type = types[i];
			ImageIcon ii = new ImageIcon(ResourceLoader.loadImage(type.getPath()));
			imgs[i] = ii;
		}
		return imgs;
	}
	
}
