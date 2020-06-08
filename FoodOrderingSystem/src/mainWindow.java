import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
//import java.util.Hashstartmap;
import java.util.Set;
//import java.util.startmap;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;

public class mainWindow {

	private JFrame frame;
	private JTextField idtf;
	private JTextField nametf;
	private JTextField datetf;
	private JTextField startersqty;
	private JTextField mainqtytf;
	private JTextField dessertqtytf;
	private JTextField totaltf;
	private float startamt = 0;
	private float mainamt = 0;
	private float dessertamt = 0;
	private float sum = 0;
	private JTable table;
	private static QueryTableModel tablemodel = new QueryTableModel();
	public static QueryTableModel tablemodel2 = new QueryTableModel();
	private Statement stmt1 = null;
	private JTable table_1;
	private JTextField exportDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbConnection dbobj = new dbConnection();
		try {
			dbobj.connecttoDB();
			Connection conn = dbobj.getConnection();
			stmt1 = conn.createStatement();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1163, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "CRUD Operations", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel.setBounds(12, 13, 333, 337);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 31, 56, 16);
		panel.add(lblNewLabel);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCustomerName.setBounds(12, 60, 118, 16);
		panel.add(lblCustomerName);

		JLabel lblDate = new JLabel("Date(yyyy/mm/dd)");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDate.setBounds(12, 89, 130, 16);
		panel.add(lblDate);

		JLabel lblStarters = new JLabel("Starters");
		lblStarters.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblStarters.setBounds(12, 118, 56, 16);
		panel.add(lblStarters);

		JLabel lblStartersQuantity = new JLabel("Starters Quantity");
		lblStartersQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblStartersQuantity.setBounds(12, 147, 118, 16);
		panel.add(lblStartersQuantity);

		JLabel lblMainCourse = new JLabel("Main Course");
		lblMainCourse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMainCourse.setBounds(12, 176, 118, 16);
		panel.add(lblMainCourse);

		JLabel lblMainCourseQuantity = new JLabel("Main Course Quantity");
		lblMainCourseQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMainCourseQuantity.setBounds(12, 205, 130, 16);
		panel.add(lblMainCourseQuantity);

		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDessert.setBounds(12, 234, 56, 16);
		panel.add(lblDessert);

		JLabel lblDessertQuantity = new JLabel("Dessert Quantity");
		lblDessertQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDessertQuantity.setBounds(12, 263, 118, 16);
		panel.add(lblDessertQuantity);

		JLabel lblBillAmount = new JLabel("Bill Amount");
		lblBillAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBillAmount.setBounds(12, 292, 118, 16);
		panel.add(lblBillAmount);

		idtf = new JTextField();
		idtf.setBounds(166, 28, 155, 22);
		panel.add(idtf);
		idtf.setColumns(10);

		nametf = new JTextField();
		nametf.setColumns(10);
		nametf.setBounds(166, 57, 155, 22);
		panel.add(nametf);

		datetf = new JTextField();
		datetf.setColumns(10);
		datetf.setBounds(166, 86, 155, 22);
		panel.add(datetf);

		JComboBox starterscb = new JComboBox();
		starterscb.setModel(new DefaultComboBoxModel(new String[] { "Select Dish" }));

		HashMap<String, Float> startmap = new HashMap<String, Float>();
		startmap.put("Crispy Garlic bread", (float) 6.95);
		startmap.put("Creamy Seafood Chowder", (float) 7.45);
		startmap.put("BBQ Chicken Wings", (float) 11.95);
		startmap.put("Classic Caesar Salad", (float) 8.95);
		startmap.put("Irish Smoked Salmon", (float) 7.45);
		Set<String> start = startmap.keySet();
		for (String key : start) {
			starterscb.addItem(key);
		}

		JComboBox maincb = new JComboBox();
		maincb.setModel(new DefaultComboBoxModel(new String[] { "Select Dish" }));
		HashMap<String, Float> mainmap = new HashMap<String, Float>();
		mainmap.put("Irish Stew", (float) 16.95);
		mainmap.put("Dublin Coodle", (float) 16.95);
		mainmap.put("Cottage Pie", (float) 14.95);
		mainmap.put("Steak Chips", (float) 24.95);
		mainmap.put("Cajun Chicken Burger", (float) 12.95);
		Set<String> mainset = mainmap.keySet();
		for (String key1 : mainset) {
			maincb.addItem(key1);
			
		}

		JComboBox dessertcb = new JComboBox();
		dessertcb.setModel(new DefaultComboBoxModel(new String[] { "Select Dish" }));
		HashMap<String, Float> dessertmap = new HashMap<String, Float>();
		dessertmap.put("Irish Baily's Cheesecake", (float) 7.50);
		dessertmap.put("Death By Chocolate", (float) 9.50);
		dessertmap.put("Home-Made Apple Crumble", (float) 7.50);
		dessertmap.put("Red Velvet Cake", (float) 8.50);
		dessertmap.put("Sujata Mastani", (float) 11.50);
		Set<String> dessertset = dessertmap.keySet();
		for (String key2 : dessertset) {
			dessertcb.addItem(key2);
		}

		startersqty = new JTextField();
		startersqty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String dish = starterscb.getSelectedItem().toString();
				float amt = startmap.get(dish);
				startamt = Integer.parseInt(startersqty.getText()) * amt;
				sum = startamt + mainamt + dessertamt;
				totaltf.setText("" + sum);
			}
		});

		startersqty.setColumns(10);
		startersqty.setBounds(166, 144, 155, 22);
		panel.add(startersqty);

		mainqtytf = new JTextField();
		mainqtytf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String dish = maincb.getSelectedItem().toString();
				float amt = mainmap.get(dish);
				mainamt = Integer.parseInt(mainqtytf.getText()) * amt;
				sum = startamt + mainamt + dessertamt;
				totaltf.setText("" + sum);
			}
		});
		mainqtytf.setColumns(10);
		mainqtytf.setBounds(166, 202, 155, 22);
		panel.add(mainqtytf);

		dessertqtytf = new JTextField();
		dessertqtytf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String dish = dessertcb.getSelectedItem().toString();
				float amt = dessertmap.get(dish);
				dessertamt = Integer.parseInt(dessertqtytf.getText()) * amt;
				sum = startamt + mainamt + dessertamt;
				totaltf.setText("" + sum);
			}
		});
		dessertqtytf.setColumns(10);
		dessertqtytf.setBounds(166, 260, 155, 22);
		panel.add(dessertqtytf);

		totaltf = new JTextField();
		totaltf.setColumns(10);
		totaltf.setBounds(166, 289, 155, 22);
		panel.add(totaltf);

		starterscb.setBounds(166, 115, 155, 22);
		panel.add(starterscb);

		maincb.setBounds(166, 173, 155, 22);
		panel.add(maincb);

		dessertcb.setBounds(166, 231, 155, 22);
		panel.add(dessertcb);

		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idtf.getText());
				String name = nametf.getText();
				java.util.Date lol = new java.util.Date(datetf.getText());
				Date tdate = new Date(lol.getTime());
				System.out.println(tdate);
				String starter = starterscb.getSelectedItem().toString();
				int starterqty = Integer.parseInt(startersqty.getText());
				String main = maincb.getSelectedItem().toString();
				int mainqty = Integer.parseInt(mainqtytf.getText());
				String dessert = dessertcb.getSelectedItem().toString();
				int dessertqty = Integer.parseInt(dessertqtytf.getText());
				float amount = Float.parseFloat(totaltf.getText());
				Connection conn = dbConnection.getConnection();

				int output;

				try {
					Statement statement = conn.createStatement();
					String query = "Insert into orders values(" + id + ",'" + name + "','" + tdate + "','" + starter
							+ "','" + main + "','" + dessert + "'," + starterqty + "," + mainqty + "," + dessertqty
							+ "," + amount + ")";

					output = statement.executeUpdate(query);
					if (output > 0)
						JOptionPane.showMessageDialog(null, "Inserted Data Successfully");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

//				String query = "Insert into orders values("+id+",'"+name+"','"+tdate+"','"+starter+"','"+main+"','"+dessert+"',"+starterqty+","+mainqty+","+dessertqty+","+amount+")";
//		        int output = statement.executeUpdate("insert into customer (cust_id, name, age, address, start_date, end_date, area)"
//		                + " values ("+ customer.getId() +",'"+ customer.getName() +"',"+ customer.getAge()+",'"+ customer.getAddress()
//		                +"',"+ customer.getStart_date()+","+ customer.getEnd_date()+",'"+ customer.getArea()+"');");
//		        if(output > 0)
				tablemodel.refreshFromDB(stmt1,"Select * from orders");
				tablemodel2.refreshFromDB(stmt1, "Select * from bills");
			}
		});
		btnInsert.setBounds(12, 363, 97, 25);
		frame.getContentPane().add(btnInsert);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idtf.getText());
				String name = nametf.getText();
				java.util.Date lol = new java.util.Date(datetf.getText());
				Date tdate = new Date(lol.getTime());
				System.out.println(tdate);
				String starter = starterscb.getSelectedItem().toString();
				int starterqty = Integer.parseInt(startersqty.getText());
				String main = maincb.getSelectedItem().toString();
				int mainqty = Integer.parseInt(mainqtytf.getText());
				String dessert = dessertcb.getSelectedItem().toString();
				int dessertqty = Integer.parseInt(dessertqtytf.getText());
				float amount = Float.parseFloat(totaltf.getText());
				Connection conn = dbConnection.getConnection();

				int output;

				try {
					Statement statement = conn.createStatement();
//					String query = "Update orders set cust_name='" + name + "',date='" + tdate + "',starters='"
//							+ starter + "',maincourse ='" + main + "',dessert='" + dessert + "',startersqtyl="
//							+ starterqty + ",mainqty = " + mainqty + ",dessertqty = " + dessertqty + ",billamount="
//							+ amount + " where order_id = " + id + ")";
//					
					String query = "UPDATE orders SET cust_name = '" + name + "', date = '" + tdate + "', starters ='" + starter + "', maincourse = '" + main + "', dessert = '" +
					dessert + "', startersqtyl = " + starterqty + ", maincourseqty = " + mainqty + ", dessertqty = " + dessertqty + ", billamount = " + amount + " WHERE order_id = " + id;

					output = statement.executeUpdate(query);
					if (output > 0)
						JOptionPane.showMessageDialog(null, "Updated Data Successfully");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tablemodel.refreshFromDB(stmt1,"Select * from orders");
			}
		});
		btnUpdate.setBounds(121, 363, 97, 25);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idtf.getText());
				Connection conn = dbConnection.getConnection();

				int output;

				try {
					Statement statement = conn.createStatement();
				String query = "Delete from orders where order_id = " +id;
							output = statement.executeUpdate(query);
							if (output > 0)
								JOptionPane.showMessageDialog(null, "Deleted Data Successfully");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				tablemodel.refreshFromDB(stmt1,"Select * from orders");
			}
		});
		btnDelete.setBounds(230, 363, 97, 25);
		frame.getContentPane().add(btnDelete);

		JButton Clear = new JButton("Clear");
		Clear.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idtf.setText("");
				nametf.setText("");
				datetf.setText("");
				startersqty.setText("");
				mainqtytf.setText("");
				dessertqtytf.setText("");
				totaltf.setText("");
			}
		});
		Clear.setBounds(66, 401, 97, 25);
		frame.getContentPane().add(Clear);

		JButton btnExport = new JButton("Export");
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM orders";
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showfulldata}");
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"alldata.csv");
			      
			      // execute the query, and get a java resultset
			      
			}
		});
		btnExport.setBounds(186, 401, 97, 25);
		frame.getContentPane().add(btnExport);
		
//		columnModel.getColumn(0).setPreferredWidth(50);
//		columnModel.getColumn(1).setPreferredWidth(150);
//		columnModel.getColumn(2).setPreferredWidth(100);
//		columnModel.getColumn(3).setPreferredWidth(180);
//		columnModel.getColumn(4).setPreferredWidth(50);
//		columnModel.getColumn(5).setPreferredWidth(150);
//		columnModel.getColumn(6).setPreferredWidth(100);
//		columnModel.getColumn(7).setPreferredWidth(180);
//		columnModel.getColumn(8).setPreferredWidth(180);
//		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Export Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(339, 363, 775, 142);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Export today's Starters");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String query = "SELECT * FROM orders";
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showstarters(?)}");
					java.util.Date lol = new java.util.Date(exportDate.getText());
					Date tdate = new Date(lol.getTime());
					
					statement.setDate(1, tdate);
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"todaystarters.csv");
			}
		});
		btnNewButton.setBounds(12, 30, 179, 25);
		panel_2.add(btnNewButton);
		
		JButton btnExportAvgBill = new JButton("Export Avg Bill for today");
		btnExportAvgBill.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExportAvgBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showavgbill(?)}");
					java.util.Date lol = new java.util.Date(exportDate.getText());
					Date tdate = new Date(lol.getTime());
					
					statement.setDate(1, tdate);
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"todaysavgbill.csv");
			}
		});
		btnExportAvgBill.setBounds(585, 30, 172, 25);
		panel_2.add(btnExportAvgBill);
		
		JButton btnExportTodaysEarnings = new JButton("Export today's Earnings");
		btnExportTodaysEarnings.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExportTodaysEarnings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showearnings(?)}");
					java.util.Date lol = new java.util.Date(exportDate.getText());
					Date tdate = new Date(lol.getTime());
					
					statement.setDate(1, tdate);
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"todaysearnings.csv");
			}
		});
		btnExportTodaysEarnings.setBounds(203, 30, 179, 25);
		panel_2.add(btnExportTodaysEarnings);
		
		JButton btnExportTodaysQuantities = new JButton("Export today's Quantities");
		btnExportTodaysQuantities.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExportTodaysQuantities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showquantities(?)}");
					java.util.Date lol = new java.util.Date(exportDate.getText());
					Date tdate = new Date(lol.getTime());
					
					statement.setDate(1, tdate);
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"todaysquantities.csv");
			}
		});
		btnExportTodaysQuantities.setBounds(394, 30, 179, 25);
		panel_2.add(btnExportTodaysQuantities);
		
		JLabel lblEnterDate = new JLabel("Enter Date:");
		lblEnterDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterDate.setBounds(258, 78, 100, 16);
		panel_2.add(lblEnterDate);
		
		exportDate = new JTextField();
		exportDate.setBounds(385, 75, 121, 22);
		panel_2.add(exportDate);
		exportDate.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tabbedPane.setBounds(357, 13, 769, 337);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Orders", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 762, 305);
		panel_1.add(scrollPane);
		
		table = new JTable(tablemodel);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = table.getColumnModel();
		scrollPane.setViewportView(table);
		tablemodel.refreshFromDB(stmt1,"Select * from orders");
		
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(50);
		columnModel.getColumn(7).setPreferredWidth(50);
		columnModel.getColumn(8).setPreferredWidth(50);
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Bills", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 764, 305);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable(tablemodel2);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnExportAllCustomers = new JButton("Export All Customers");
		btnExportAllCustomers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnExportAllCustomers.setBounds(76, 439, 179, 25);
		frame.getContentPane().add(btnExportAllCustomers);
		btnExportAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = dbConnection.getConnection();
				ResultSet rs = null;
			      // create the java statement
				try {
					CallableStatement statement = conn.prepareCall("{call showcustomers()}");
			
					 rs = statement.executeQuery();
//					statement.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
				writeToFile(rs,"allcustomers.csv");
			}
		});
		tablemodel2.refreshFromDB(stmt1, "Select * from bills");
	}
	private void writeToFile(ResultSet rs,String filename){
		try{
			System.out.println("In writeToFile");
			FileWriter outputFile = new FileWriter(filename);
			PrintWriter printWriter = new PrintWriter(outputFile);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for(int i=0;i<numColumns;i++){
				printWriter.print(rsmd.getColumnLabel(i+1)+",");
			}
			printWriter.print("\n");
			while(rs.next()){
				for(int i=0;i<numColumns;i++){
					printWriter.print(rs.getString(i+1)+",");
				}
				printWriter.print("\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
}
