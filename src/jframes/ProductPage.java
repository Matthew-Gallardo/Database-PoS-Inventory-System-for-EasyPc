/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package jframes;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframes.DBConnection.con;
/**
 *
 * @author Administrator
 */
public class ProductPage extends javax.swing.JFrame {

    /**
     * Creates new form CategoryPage
     */
    public ProductPage() {
        initComponents();
        setProductDetailsToTable();
        category();
        brand();
        LoadImageID();
        LoadImage();
    }
    Connection con1;
    PreparedStatement pst;
    ResultSet rs;
    
    String brand,status;
    int idbrand;
    DefaultTableModel model;
    
    
    
    public void setProductDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product_details");
            
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String category = rs.getString("category");
                String idcategory = rs.getString("idcategory");
                String brand= rs.getString("brand");
                String idbrand = rs.getString("idbrand");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String status = rs.getString("status");
                
               
                
                
                Object[] obj = {idproduct,product,category,idcategory,brand,idbrand,price,quantity,status};
                model =(DefaultTableModel) tableproduct.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     public class CategoryItem{
         int id;
         String name;
         public CategoryItem(int id, String name){
             this.id=id;
             this.name=name;
     
         }
         public String toString(){
             return name;
         }
     }
     
     
     public class BrandItem{
         int id;
         String name;
         public BrandItem(int id, String name){
             this.id=id;
             this.name=name;
     
         }
         public String toString(){
             return name;
         }
     }
     
     public void LoadImageID() {
        try {
            pst = con.prepareStatement("select idproduct from product_details");
            rs =pst.executeQuery();
            imageID.removeAllItems();
            while (rs.next()){
                imageID.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void LoadImage() {
        try {
            byte[] imagedata =rs.getBytes("imageFile");
            format = new ImageIcon(imagedata);
            Image mm = format.getImage();
            Image img2 = mm.getScaledInstance(displayLabel.getWidth(),displayLabel.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(img2);
            displayLabel.setIcon(image);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     
     
     
     private void category()
     {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from category_details");
            txtcat.removeAllItems();
            
            while(rs.next()){
            txtcat.addItem(new CategoryItem (rs.getInt(1),rs.getString(2)));
            
            
            }
                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }
     
     private void brand()
     {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from brand_details");
            txtbrandcombo.removeAllItems();
            
            while(rs.next()){
            txtbrandcombo.addItem(new BrandItem (rs.getInt(1),rs.getString(2)));
            
            
            }
                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }
   
   
    //method to clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tableproduct.getModel();
        model.setRowCount(0);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtcat = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        txtbrandcombo = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        txtproductstatus = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtproduct = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableproduct = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        displayLabel = new javax.swing.JLabel();
        btnAttach = new javax.swing.JButton();
        imagepath = new javax.swing.JLabel();
        Imagelable = new javax.swing.JLabel();
        imageID = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        imagepath1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(78, 148, 79));
        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(78, 148, 79));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-back-40 (1).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(233, 239, 192));
        jLabel3.setText("Manage Product");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -20, 280, 120));

        jPanel5.setBackground(new java.awt.Color(78, 148, 79));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Product", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(233, 239, 192))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel27.setText("Product Name:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        jPanel5.add(txtcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 30));

        jButton4.setText("ADD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 80, 30));

        jButton5.setText("UPDATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 90, 30));

        jButton6.setText("DELETE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 80, 30));

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel29.setText("Price");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 140, 30));

        txtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpriceActionPerformed(evt);
            }
        });
        jPanel5.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 100, 30));

        jPanel5.add(txtbrandcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 200, 30));

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel30.setText("Brand");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, 30));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel31.setText("Quantity");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 140, 30));

        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });
        jPanel5.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 100, 30));

        txtproductstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "inStock", "OutOfStock" }));
        jPanel5.add(txtproductstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 100, 30));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel32.setText("Status");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, 30));

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel33.setText("Category:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 140, 30));

        txtproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductActionPerformed(evt);
            }
        });
        jPanel5.add(txtproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 200, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-ok-30.png"))); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 40, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-shopping-cart-30.png"))); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 40, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-tag-window-30.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 40, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-peso-symbol-30.png"))); // NOI18N
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 40, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-how-many-quest-30.png"))); // NOI18N
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8-opened-folder-30.png"))); // NOI18N
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 40, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 390, 630));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 30, 50));

        tableproduct.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 204, 0)));
        tableproduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product", "Category", "Category ID", "Brand", "Brand ID", "Price", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableproduct.setGridColor(new java.awt.Color(102, 204, 0));
        tableproduct.setSelectionBackground(new java.awt.Color(0, 255, 0));
        tableproduct.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableproduct.setShowGrid(false);
        tableproduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableproductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableproduct);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 690, 360));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-close-30.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 80, -1));

        jPanel4.setBackground(new java.awt.Color(78, 148, 79));

        displayLabel.setBackground(new java.awt.Color(255, 255, 255));
        displayLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAttach.setText("ATTACH PHOTO");
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });

        imagepath.setForeground(new java.awt.Color(233, 239, 192));
        imagepath.setText("Path");

        Imagelable.setBackground(new java.awt.Color(255, 255, 255));
        Imagelable.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        imageID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageIDActionPerformed(evt);
            }
        });

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        imagepath1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        imagepath1.setForeground(new java.awt.Color(233, 239, 192));
        imagepath1.setText("Search Product by ID:");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(233, 239, 192));
        jLabel7.setText("Display Product");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8-view-30 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Imagelable, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(imagepath, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(imagepath1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(displayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(66, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnAttach)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageID, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Imagelable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagepath1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imagepath))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAttach)
                            .addComponent(btnSearch)
                            .addComponent(imageID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 680, 310));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1300, 788));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 
   
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void tableproductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableproductMouseClicked
        // TODO add your handling code here:
        int rowNo = tableproduct.getSelectedRow();
        TableModel model = tableproduct.getModel();

        txtproduct.setText(model.getValueAt(rowNo, 1).toString());

    }//GEN-LAST:event_tableproductMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)tableproduct.getModel();
        int selectIndex = tableproduct.getSelectedRow();

        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);

        if(dialogResult == JOptionPane.YES_OPTION)
        {

            try {
                pst = con.prepareStatement("delete from product_details where idproduct =?");
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Brand Deleted");
                clearTable();
               setProductDetailsToTable();
                 txtproduct.setText("");
                txtcat.setSelectedIndex(-1);
                txtbrandcombo.setSelectedIndex(-1);
                txtproduct.requestFocus();
                txtprice.setText("");
                txtquantity.setText("");
               txtproductstatus.setSelectedIndex(-1);
                jButton5.setEnabled(true);
                new ProductPage().setVisible(true);
                this.dispose();
                 txtproduct.requestFocus();
  
                
            } catch (SQLException ex) {
                Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)tableproduct.getModel();
        int selectIndex = tableproduct.getSelectedRow();
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());
        
        String product = txtproduct.getText();
        String category = txtcat.getSelectedItem().toString(); //categoryname
        CategoryItem catitem =(CategoryItem)txtcat.getSelectedItem();//categoryid
        String brand = txtbrandcombo.getSelectedItem().toString();//brandname
        BrandItem  britem =(BrandItem)txtbrandcombo.getSelectedItem(); //brandid     
        String price = txtprice.getText();
        String quantity =txtquantity.getText();
        String status = txtproductstatus.getSelectedItem().toString();
        System.out.print("image path - " +path);
        
        File f =new File(path);
        
       
   

        try {
            InputStream is = new FileInputStream(f);
            Connection con =DBConnection.getConnection();
            pst = con.prepareStatement("update product_details set product=?,category=?,idcategory=?,brand=?,idbrand=?,price=?,quantity=?,status=?,imagePath=?,imageFile=? where idproduct= ? ");
            pst.setString(1, product);
            pst.setString(2, category);
            pst.setInt(3, catitem.id);
            pst.setString(4, brand);
            pst.setInt(5, britem.id);
            pst.setString(6, price);
            pst.setString(7, quantity);
            pst.setString(8, status);
            pst.setString(9, path);
            pst.setBlob(10, is);
            pst.setInt(11, id);
            
          
            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Brand  Updated");
            clearTable();
            setProductDetailsToTable();
            txtproduct.setText("");
            txtcat.setSelectedIndex(-1);
            txtbrandcombo.setSelectedIndex(-1);
            txtproduct.requestFocus();
            txtprice.setText("");
            txtquantity.setText("");
           txtproductstatus.setSelectedIndex(-1);
            jButton5.setEnabled(true);
            new ProductPage().setVisible(true);
            this.dispose();
            
            txtproduct.requestFocus();

            
            
         
           
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String product = txtproduct.getText();
        String category = txtcat.getSelectedItem().toString(); //categoryname
        CategoryItem catitem =(CategoryItem)txtcat.getSelectedItem();//categoryid
        String brand = txtbrandcombo.getSelectedItem().toString();//brandname
        BrandItem  britem =(BrandItem)txtbrandcombo.getSelectedItem(); //brandid     
        String price = txtprice.getText();
        String quantity =txtquantity.getText();
        String status = txtproductstatus.getSelectedItem().toString();
        System.out.print("image path - " +path);
        
        File f =new File(path);
        
        
        
        

        try {
            InputStream is = new FileInputStream(f);
            Connection con =DBConnection.getConnection();
            String sql = ("insert into product_details(product,category,idcategory,brand,idbrand,price,quantity,status,imagePath,imageFile) values(?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, product);
            pst.setString(2, category);
            pst.setInt(3, catitem.id);
            pst.setString(4, brand);
            pst.setInt(5, britem.id);
            pst.setString(6, price);
            pst.setString(7, quantity);
            pst.setString(8, status);
            pst.setString(9, path);
            pst.setBlob(10, is);
            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product Added");
             
            clearTable();
             LoadImageID();
            setProductDetailsToTable();
          
            
            txtproduct.setText("");
            txtcat.setSelectedIndex(-1);
            txtbrandcombo.setSelectedIndex(-1);
            txtprice.setText("");
            txtquantity.setText("");
            txtproductstatus.setSelectedIndex(-1);
            new ProductPage().setVisible(true);
            this.dispose();
            
            txtproduct.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityActionPerformed

    private void txtproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductActionPerformed
File f=null;
String path=null;
private ImageIcon format =null;
String fname=null;
int s=0;
byte[] pimage=null;

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("PNG JPG AND JPEG", "png","jpg","jpeg");
        filechooser.addChoosableFileFilter(fnef);
        int load= filechooser.showOpenDialog(null);
        
        if(load==filechooser.APPROVE_OPTION){
            f =filechooser.getSelectedFile();
            path = f.getAbsolutePath();
            imagepath.setText(path);
            ImageIcon ii = new ImageIcon(path);
            Image img = ii.getImage().getScaledInstance(Imagelable.getWidth(),Imagelable.getHeight(),Image.SCALE_SMOOTH);
            Imagelable.setIcon(new ImageIcon(img));
            
            
        }
    }//GEN-LAST:event_btnAttachActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String idproduct = imageID.getSelectedItem().toString();
        try {
            pst=con1.prepareStatement("Select * from product_details where idproduct =?");
            pst.setString(1, idproduct);
            rs = pst.executeQuery();
            if (rs.next()){
                LoadImage();
            }
            else{
                JOptionPane.showMessageDialog(this, "No Image Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void imageIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imageIDActionPerformed

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
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Imagelable;
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel displayLabel;
    private javax.swing.JComboBox imageID;
    private javax.swing.JLabel imagepath;
    private javax.swing.JLabel imagepath1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableproduct;
    private javax.swing.JComboBox txtbrandcombo;
    private javax.swing.JComboBox txtcat;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproduct;
    private javax.swing.JComboBox<String> txtproductstatus;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
