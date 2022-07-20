/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import static jframes.DBConnection.con;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author sunil
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form SignupPage
     */
    public HomePage() {
        initComponents();
        showPieChart();
        setProductDetailsToTable();
        setCatDetailsToTable();
        setDataToCard();
    }
     DefaultTableModel model;
     
     
     public void setDataToCard(){
         Statement st=null;
         ResultSet rs=null;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            st = con.createStatement();
            rs = st.executeQuery("select * from product_details");
            rs.last();
            prodnum.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from customer_details"); 
            rs.last();
            cusnum.setText(Integer.toString(rs.getRow()));
                     
         } catch (Exception e) {
              e.printStackTrace();
         }
     }
     
     
     
      public void setProductDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product_details");
            
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                
                String idcategory = rs.getString("idcategory");
                String brand= rs.getString("brand");
                
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
               
                
               
                
                
                Object[] obj = {idproduct,idcategory,product,brand,price,quantity};
                model =(DefaultTableModel) homeprod.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
      //to set the category details into the table
    public void setCatDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from category_details");
            
            while(rs.next()){
                String idcategory = rs.getString("idcategory");
                String category = rs.getString("category");
               
                
                Object[] obj = {idcategory,category};
                model =(DefaultTableModel) homecat.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      
        try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select product, count(*) as product_count from orderedproducts group  by idproduct");
           
           while(rs.next()){
               barDataset.setValue(rs.getString("product"), new Double(rs.getDouble("product_count")));
                       
           }
           
           
        } catch (Exception e) {
        }
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("EasyPC Product Sales",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }

  
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelout = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        panelorder = new javax.swing.JPanel();
        Customers1 = new javax.swing.JLabel();
        panelcat = new javax.swing.JPanel();
        categorylabel1 = new javax.swing.JLabel();
        panelbrand = new javax.swing.JPanel();
        categorylabel2 = new javax.swing.JLabel();
        panelprod = new javax.swing.JPanel();
        categorylabel3 = new javax.swing.JLabel();
        panelcus = new javax.swing.JPanel();
        Customers = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cusnum = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        prodnum = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        homeprod = new rojeru_san.complementos.RSTableMetro();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        homecat = new rojeru_san.complementos.RSTableMetro();
        jLabel32 = new javax.swing.JLabel();
        panelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 233, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/easypclogooo.png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 210, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 40));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 80, 50));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel4.setText("DATABASE SYSTEM");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 410, 50));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome, Admin!");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 200, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-close-24.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 40, 30, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 90));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Verdana", 3, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Features");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 320, 60));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 40, 40));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Home Page");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 140, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 320, 60));

        panelout.setBackground(new java.awt.Color(51, 51, 51));
        panelout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneloutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paneloutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paneloutMouseExited(evt);
            }
        });
        panelout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel20.setText("LOG-OUT");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        panelout.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 40));

        jPanel2.add(panelout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 320, 60));

        panelorder.setBackground(new java.awt.Color(51, 51, 51));
        panelorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelorderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelorderMouseExited(evt);
            }
        });
        panelorder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Customers1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        Customers1.setForeground(new java.awt.Color(255, 255, 255));
        Customers1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-receipt-approved-35.png"))); // NOI18N
        Customers1.setText("Orders (Point of Sale)");
        Customers1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Customers1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Customers1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Customers1MouseExited(evt);
            }
        });
        panelorder.add(Customers1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 40));

        jPanel2.add(panelorder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 320, 60));

        panelcat.setBackground(new java.awt.Color(51, 51, 51));
        panelcat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcatMouseExited(evt);
            }
        });
        panelcat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categorylabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        categorylabel1.setForeground(new java.awt.Color(255, 255, 255));
        categorylabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-opened-folder-35.png"))); // NOI18N
        categorylabel1.setText("Categories");
        categorylabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorylabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorylabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorylabel1MouseExited(evt);
            }
        });
        panelcat.add(categorylabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 40));

        jPanel2.add(panelcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 320, 60));

        panelbrand.setBackground(new java.awt.Color(51, 51, 51));
        panelbrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelbrandMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelbrandMouseExited(evt);
            }
        });
        panelbrand.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categorylabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        categorylabel2.setForeground(new java.awt.Color(255, 255, 255));
        categorylabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-tag-window-35.png"))); // NOI18N
        categorylabel2.setText("Brand");
        categorylabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorylabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorylabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorylabel2MouseExited(evt);
            }
        });
        panelbrand.add(categorylabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 40));

        jPanel2.add(panelbrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 320, 60));

        panelprod.setBackground(new java.awt.Color(51, 51, 51));
        panelprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelprodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelprodMouseExited(evt);
            }
        });
        panelprod.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categorylabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        categorylabel3.setForeground(new java.awt.Color(255, 255, 255));
        categorylabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-shopping-cart-35.png"))); // NOI18N
        categorylabel3.setText("Products");
        categorylabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorylabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorylabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorylabel3MouseExited(evt);
            }
        });
        panelprod.add(categorylabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 40));

        jPanel2.add(panelprod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 320, 60));

        panelcus.setBackground(new java.awt.Color(51, 51, 51));
        panelcus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcusMouseExited(evt);
            }
        });
        panelcus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Customers.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        Customers.setForeground(new java.awt.Color(255, 255, 255));
        Customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-customer-35.png"))); // NOI18N
        Customers.setText("Customers");
        Customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomersMouseExited(evt);
            }
        });
        panelcus.add(Customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 40));

        jPanel2.add(panelcus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 320, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 320, 700));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel2.setText("DATABASE SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 410, 50));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel3.setText("DATABASE SYSTEM");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 410, 50));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Welcome, Admin!");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 30, 210, 50));

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cusnum.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        cusnum.setText("420");
        jPanel5.add(cusnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8-customer-64.png"))); // NOI18N
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 220, 120));

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 0, 0)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view product.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        prodnum.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        prodnum.setText("69");
        jPanel12.add(prodnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 220, 120));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel12.setText("Number of Customers:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 200, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel13.setText("Product Details");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 200, -1));

        homeprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "CategoryID", "ProductName", "Brand", "Price", "Quantity"
            }
        ));
        homeprod.setColorBackgoundHead(new java.awt.Color(51, 204, 0));
        homeprod.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        homeprod.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        homeprod.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        homeprod.setColorSelBackgound(new java.awt.Color(204, 0, 0));
        homeprod.setIntercellSpacing(new java.awt.Dimension(0, 0));
        homeprod.setRowHeight(30);
        jScrollPane1.setViewportView(homeprod);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 780, 190));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel31.setText("Number of Products:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 200, -1));

        homecat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CategoryID", "Category"
            }
        ));
        homecat.setColorBackgoundHead(new java.awt.Color(51, 204, 0));
        homecat.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        homecat.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        homecat.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        homecat.setColorSelBackgound(new java.awt.Color(204, 0, 0));
        homecat.setIntercellSpacing(new java.awt.Dimension(0, 0));
        homecat.setRowHeight(25);
        jScrollPane2.setViewportView(homecat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 520, 240, 210));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel32.setText("Category Details");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 200, -1));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 380, 220));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 780));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void CustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomersMouseClicked
        // TODO add your handling code here:
       CustomerPage customerpg = new CustomerPage();
        customerpg.setVisible(true);
        dispose();
    }//GEN-LAST:event_CustomersMouseClicked

    private void categorylabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseClicked
        // TODO add your handling code here:
        CategoryPage catpg = new CategoryPage();
        catpg.setVisible(true);
        dispose();
    }//GEN-LAST:event_categorylabel1MouseClicked

    private void categorylabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel2MouseClicked
        // TODO add your handling code here:
        BrandPage brandpg = new BrandPage();
        brandpg.setVisible(true);
        dispose();
    }//GEN-LAST:event_categorylabel2MouseClicked

    private void categorylabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel3MouseClicked
        // TODO add your handling code here:
        ProductPage productpg = new ProductPage();
        productpg.setVisible(true);
        dispose();
    }//GEN-LAST:event_categorylabel3MouseClicked

    private void Customers1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Customers1MouseClicked
        // TODO add your handling code here:
        OrderPage order = new OrderPage();
        order.setVisible(true);
        dispose();
    }//GEN-LAST:event_Customers1MouseClicked

    private void panelcatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcatMouseExited
        // TODO add your handling code here:
        panelcat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_panelcatMouseExited

    private void panelcatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcatMouseEntered
        // TODO add your handling code here:
        panelcat.setBackground(Color.black);
    }//GEN-LAST:event_panelcatMouseEntered

    private void panelbrandMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelbrandMouseExited
        // TODO add your handling code here:
        panelbrand.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_panelbrandMouseExited

    private void panelbrandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelbrandMouseEntered
        // TODO add your handling code here:
        panelbrand.setBackground(Color.black);
    }//GEN-LAST:event_panelbrandMouseEntered

    private void panelprodMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelprodMouseExited
        // TODO add your handling code here:
        panelprod.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_panelprodMouseExited

    private void panelprodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelprodMouseEntered
        // TODO add your handling code here:
        panelprod.setBackground(Color.black);
    }//GEN-LAST:event_panelprodMouseEntered

    private void panelcusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcusMouseEntered
        // TODO add your handling code here:
         panelcus.setBackground(Color.black);
    }//GEN-LAST:event_panelcusMouseEntered

    private void panelcusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcusMouseExited
        // TODO add your handling code here:
        panelcus.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_panelcusMouseExited

    private void panelorderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelorderMouseEntered
        // TODO add your handling code here:
        panelorder.setBackground(Color.black);
    }//GEN-LAST:event_panelorderMouseEntered

    private void panelorderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelorderMouseExited
        // TODO add your handling code here:
        panelorder.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_panelorderMouseExited

    private void categorylabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseEntered
        // TODO add your handling code here:
        panelcat.setBackground(Color.black);
    }//GEN-LAST:event_categorylabel1MouseEntered

    private void categorylabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseExited
        // TODO add your handling code here:
         panelcat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_categorylabel1MouseExited

    private void categorylabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel2MouseEntered
        // TODO add your handling code here:
        panelbrand.setBackground(Color.black);
    }//GEN-LAST:event_categorylabel2MouseEntered

    private void categorylabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel2MouseExited
        // TODO add your handling code here:
        panelbrand.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_categorylabel2MouseExited

    private void categorylabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel3MouseEntered
        // TODO add your handling code here:
        panelprod.setBackground(Color.black);
    }//GEN-LAST:event_categorylabel3MouseEntered

    private void categorylabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel3MouseExited
        // TODO add your handling code here:
        panelprod.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_categorylabel3MouseExited

    private void CustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomersMouseEntered
        // TODO add your handling code here:
        panelcus.setBackground(Color.black);
    }//GEN-LAST:event_CustomersMouseEntered

    private void CustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomersMouseExited
        // TODO add your handling code here:
        panelcus.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_CustomersMouseExited

    private void Customers1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Customers1MouseEntered
        // TODO add your handling code here:
         panelorder.setBackground(Color.black);
    }//GEN-LAST:event_Customers1MouseEntered

    private void Customers1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Customers1MouseExited
        // TODO add your handling code here:
         panelorder.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_Customers1MouseExited

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        
        LoginPage login = new LoginPage();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void paneloutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_paneloutMouseClicked

    private void paneloutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseEntered
        // TODO add your handling code here:
       panelout.setBackground(Color.black); 
    }//GEN-LAST:event_paneloutMouseEntered

    private void paneloutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseExited
        // TODO add your handling code here:
        panelout.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_paneloutMouseExited

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here:
        panelout.setBackground(Color.black); 
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here:
        panelout.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_jLabel20MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 

        /* Create and display the form */
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Customers;
    private javax.swing.JLabel Customers1;
    private javax.swing.JLabel categorylabel1;
    private javax.swing.JLabel categorylabel2;
    private javax.swing.JLabel categorylabel3;
    private javax.swing.JLabel cusnum;
    private rojeru_san.complementos.RSTableMetro homecat;
    private rojeru_san.complementos.RSTableMetro homeprod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelPieChart;
    private javax.swing.JPanel panelbrand;
    private javax.swing.JPanel panelcat;
    private javax.swing.JPanel panelcus;
    private javax.swing.JPanel panelorder;
    private javax.swing.JPanel panelout;
    private javax.swing.JPanel panelprod;
    private javax.swing.JLabel prodnum;
    // End of variables declaration//GEN-END:variables

}