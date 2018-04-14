package eecs221.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eecs221.Model.Cargo;
import eecs221.Model.Shelf;
import eecs221.Model.WareHouse;
import eecs221.Util.CSVReader;
import eecs221.Util.SearchPath;

public class FrameWindow extends JFrame{
	
	private JFrame f;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelLoad;
	private JPanel panelSetParameter1;
	private JPanel panelSetParameter2;
	private JPanel panelSetParameter3;

	
	private Button btn;
	private Button btn_exit;
	private Button btn_search;
	private Button btn_clear;
	private JTextField jTextPath;
	
	private JTextField jTextShelfX;
	private JTextField jTextShelfY;
	private JTextField jTextWarehouseX;
	private JTextField jTextWarehouseY;
	private JTextField jTextTargetNumber;
	private JLabel jLabel_CSV;
	private JLabel jLabel_Shelf;
	private JLabel jLabel_Warehouse;
	private JLabel jLabel_Target;
	private JLabel jLabel_Notice;
	
	private ArrayList<Cargo> cargos;
	
	public FrameWindow(){
		init();
	}
	
	public void init() {
		
		this.setTitle("Main");
		this.setSize(1500, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelLeft = new JPanel();
		panelLoad = new JPanel();
		panelSetParameter1 = new JPanel();
		panelSetParameter2 = new JPanel();
		panelSetParameter3 = new JPanel();

		panelRight = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				DrawAxio(g);
			}
		};

		panelLeft.setLayout(new GridLayout(0,3));
		
		btn = new Button("Import CSV file");
		btn_exit = new Button("Exit");
		btn_search = new Button("Find Path");
		btn_clear = new Button("Clear");
		jTextPath = new JTextField(40);
		
        String class_str = this.getClass().getResource("/file/warehouse-grid.csv").getPath();  
        try {
			class_str = URLDecoder.decode(class_str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        String[] addr = class_str.split("eecs221.jar");
        
        //URL class_str = this.getClass().getClassLoader().getResource("warehouse-grid.csv")  ;
        //System.out.println(addr[0]);
        
        if(addr.length != 1)
        	jTextPath.setText(addr[0].substring(6, addr[0].length()) + "/file/warehouse-grid.csv");
        else
        	jTextPath.setText(addr[0]);

		//jTextPath.setText(class_str);

		System.out.println(this.getClass().getResource(""));

		jTextShelfX = new JTextField(16);
		jTextShelfY = new JTextField(16);
		jTextWarehouseX = new JTextField(16);
		jTextWarehouseY = new JTextField(16);
		jTextTargetNumber = new JTextField(16);
		
		jTextShelfX.setText("1");
		jTextShelfY.setText("1");
		jTextWarehouseX.setText("1");
		jTextWarehouseY.setText("1");
		jTextTargetNumber.setText("149");

		jLabel_CSV = new JLabel("Path: ");
		jLabel_Shelf = new JLabel("  Size of Shelves:       ");
		jLabel_Warehouse = new JLabel("  Size of Warehouse:       ");
		jLabel_Target = new JLabel("Target: ");
		jLabel_Notice = new JLabel("Result: ");
		
		panelLeft.setBorder(BorderFactory.createTitledBorder("Controller"));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		panelLoad.setLayout(new FlowLayout());
		panelSetParameter1.setLayout(new FlowLayout());
		panelSetParameter2.setLayout(new FlowLayout());
		panelSetParameter3.setLayout(new FlowLayout());

		panelLoad.add(jLabel_CSV);
		panelLoad.add(jTextPath);
		
		panelSetParameter1.add(jLabel_Shelf);
		panelSetParameter1.add(jTextShelfX);
		panelSetParameter1.add(jTextShelfY);

		panelSetParameter2.add(jLabel_Warehouse);
		panelSetParameter2.add(jTextWarehouseX);
		panelSetParameter2.add(jTextWarehouseY);
		
		panelSetParameter3.add(jLabel_Target);
		panelSetParameter3.add(jTextTargetNumber);
		
		panelLeft.add(panelLoad);
		panelLeft.add(panelSetParameter1);
		panelLeft.add(panelSetParameter2);
		panelLeft.add(panelSetParameter3);
		panelLeft.add(jLabel_Notice);
		panelLeft.add(btn);		
		panelLeft.add(btn_clear);
		panelLeft.add(btn_search);
		panelLeft.add(btn_exit);
		jLabel_CSV.setFont(new   java.awt.Font("Dialog",   1,   20));
		jLabel_Shelf.setFont(new   java.awt.Font("Dialog",   1,   20));
		jLabel_Warehouse.setFont(new   java.awt.Font("Dialog",   1,   20));
		jLabel_Target.setFont(new   java.awt.Font("Dialog",   1,   20));
		jLabel_Notice.setFont(new   java.awt.Font("Dialog",   1,   20));
		panelLeft.setFont(new   java.awt.Font("Dialog",   1,   20));   

		

		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panelMain = new JPanel();  
        panelMain.setLayout(new GridLayout(1,2));  
        panelMain.add(panelLeft);  
        panelMain.add(panelRight);
		//this.setContentPane(panelLoad);

        this.add(panelMain);
        myEvent();      
		this.setVisible(true);
		
	}
	
	public void DrawAxio(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(30, 30, 690, 690);
		
		g.drawString("0", 50, 675);
		g.drawString("-1,-1", 10, 740);
		g.drawString("X", 720, 740);
		g.drawString("Y", 10, 30);
		g.setColor(Color.gray);
		g.drawLine(64, 30, 64, 720);
		g.drawLine(30, 660, 720, 660);

	}

	public void myEvent() {
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("read CSV"); 
				
				String path = jTextPath.getText();
				//path.replaceAll("%20", "\t");
				CSVReader csvReader = new CSVReader(path);
				cargos = csvReader.setUpArray(18, 10);
				
				//draw graphic
				Graphics g = panelRight.getGraphics();
				//removeAll();
				int s_x = Integer.parseInt(jTextShelfX.getText());
				int s_y = Integer.parseInt(jTextShelfY.getText());
				int wh_x = Integer.parseInt(jTextWarehouseX.getText());
				int wh_y = Integer.parseInt(jTextWarehouseY.getText());


				for(int i = 1 ; i < 20 ; i++) {
					if(i % 2 == 0)
						g.drawString(Integer.toString( i/2*(s_x+wh_x) - 1 ), i*34+30, 740);
					else
						g.drawString(Integer.toString( i/2*(s_x+wh_x)+s_x - 1 ), i*34+30, 740);
				}
				for(int i = 1 ; i < 12 ; i++) {
					g.drawString(Integer.toString( (i-1) * (s_y + wh_y)), 10, 740-(i*60) - 20);

//					
//					if(i % 2 == 0)
//						g.drawString(Integer.toString( i/2*(s_y+wh_y) - 1), 10, 740-(i*60) - 10);
//					else 
//						g.drawString(Integer.toString( i/2*(s_y+wh_y)+s_y - 1), 10, 740-(i*60) - 10);
				}
				
				for(Cargo c:cargos) {
					int x = c.getAxio_X();
					int y = c.getAxio_Y();
//					if(c.getNumber()==149)
//						g.drawOval(64 + ((x) * 34), 775 - ((y+2) * 60), 10, 10);
//					else
					g.drawOval(64 + ((x) * 34), 775 - ((y+2) * 60), 5, 5);
					
				}				
			}
		});
		
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graphics g = panelRight.getGraphics();
				repaint();
			}
		});
		
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s_x = Integer.parseInt(jTextShelfX.getText());
				int s_y = Integer.parseInt(jTextShelfY.getText());
				int wh_x = Integer.parseInt(jTextWarehouseX.getText());
				int wh_y = Integer.parseInt(jTextWarehouseY.getText());
				int number = Integer.parseInt(jTextTargetNumber.getText());
				
				Shelf s = new Shelf(s_y, s_x);
				WareHouse wh = new WareHouse(wh_x,wh_y);
				
				SearchPath sp = new SearchPath(s, wh);
				int[] targetpoint = sp.findSingleItem(cargos, number);
				int[] startpoint = {0, 0};
				int[] turnpoint = sp.findMiddlePoint(targetpoint, startpoint);
				Graphics g = panelRight.getGraphics();
				g.setColor(Color.red);
				if(turnpoint[0] == 0) {
					jLabel_Notice.setText("Result: Not Found item " + number +"!");
				}else {
					g.drawOval((turnpoint[0]-1)*34+64, 717, 5, 5);
					g.drawOval((startpoint[0]-1)*34+64, 717, 5, 5);
					g.drawOval((targetpoint[0])*34+64,775 - ((targetpoint[1] + 2) * 60), 5, 5);
					g.drawLine((startpoint[0]-1)*34+64, 717, (turnpoint[0]-1)*34+64, 717);
					g.drawLine((turnpoint[0] -1)*34+64, 717, (targetpoint[0])*34+64, 775 - ((targetpoint[1]+2) * 60));
				
					jLabel_Notice.setText("Result: Start point: [-1, -1] Turn point: [" + (turnpoint[0]-1) + ", -1]");
				}
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("exit!!");  
				System.exit(0);
			}
		});
	}
}
