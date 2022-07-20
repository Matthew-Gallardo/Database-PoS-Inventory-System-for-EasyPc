/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import com.mysql.cj.xdevapi.Statement;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframes.DBConnection.con;

/**
 *
 * @author Administrator
 */
public class OrderPage extends javax.swing.JFrame {

    /**
     * Creates new form CategoryPage
     */
    public OrderPage() {
        initComponents();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now =LocalDateTime.now();
        String date = formatter.format(now);
        txtdatetoday.setText(date);
       
      
    }
    Connection con;
    PreparedStatement insert;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    
  
   
    private void pos(){
      String  productid =txtproductid.getText();
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            
            insert=con.prepareStatement("select * from product_details where idproduct=?");
            insert.setString(1, productid);
            rs=insert.executeQuery();
    
            while(rs.next()){
                //qty=current quantity . quantity=new quantity
                int qty;
                qty=rs.getInt("quantity");
               // JOptionPane.showMessageDialog(this, quantity);
               int price = Integer.parseInt(txtprice.getText());
               int quantity = Integer.parseInt(txtquantity.getText());
               
               int total = price *quantity;
               if(quantity>=qty)
               {
                   JOptionPane.showMessageDialog(this, "Available product"+"="+qty +"pcs");
                   JOptionPane.showMessageDialog(this, "Not enough Quantity");
               }else{
                   model= (DefaultTableModel)tableorder.getModel();
                   model.addRow(new Object[]
                   {
                       txtcustomerid.getText(),
                       txtproductid.getText(),
                       txtproductname.getText(),
                       txtbrand.getText(),
                       txtprice.getText(),
                       txtquantity.getText(),
                       total
                   } );    
                   
                         int sum=0;
                         for(int i=0;i<tableorder.getRowCount();i++){
                             sum=sum +Integer.parseInt(tableorder.getValueAt(i, 6).toString());
                         }
                         txtsubtotal.setText(Integer.toString(sum));
                   
                      
                        txtemail.setText("");
                        txtproductid.setText("");
                        txtproductname.setText("");
                        txtbrand.setText("");
                        txtprice.setText("");
                        txtquantity.setText("");
                        txtshippingaddress.setText("");
                        
                        txtproductid.requestFocus();
          
               }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    
}
      private void order(){
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now =LocalDateTime.now();
        String date = formatter.format(now);
        
        int customerid = Integer.parseInt(txtcustomerid.getText());
 
        String customer = txtcustomername.getText();
        String subtotal= txtsubtotal.getText();
        String pay = txtpay.getText();
        String balance = txtbalance.getText();
        String mop = txtmop.getSelectedItem().toString();
        String address= txtshippingaddress.getText();
        int lastinsertid=0;
     

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
            
            String sql = ("Insert into order_details(idcustomer,customer,subtotal,pay,balance,mop,date,shipping_address)values(?,?,?,?,?,?,?,?)");
            insert=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                    
            insert.setInt(1, customerid);
            insert.setString(2, customer);
            insert.setString(3, subtotal);
            insert.setString(4, pay);
            insert.setString(5, balance);
            insert.setString(6, mop);
            insert.setString(7, date);
            insert.setString(8, address);
          
            insert.executeUpdate();
            ResultSet generatedKeyResult =insert.getGeneratedKeys();
          
            if(generatedKeyResult.next()){
                lastinsertid = generatedKeyResult.getInt(1);
            }
            JOptionPane.showMessageDialog(this, lastinsertid);
            
            txtordernum.setText(String.valueOf(lastinsertid));
            
            
            
            int rows = tableorder.getRowCount();
            
            String query = ("Insert into orderedproducts(idorder,idproduct,product,brand,price,quantity,total)values(?,?,?,?,?,?,?)");
            insert=con.prepareStatement(query);
            
            String idproduct="";
            String product="";
            String brand="";
            String price="";
            String quantity="";
            int total=0;
                    
            for(int i=0;i<tableorder.getRowCount();i++){
               idproduct=(String)tableorder.getValueAt(i, 1);
               product=(String)tableorder.getValueAt(i, 2);
               brand=(String)tableorder.getValueAt(i, 3);
               price=(String)tableorder.getValueAt(i, 4);
               quantity=(String)tableorder.getValueAt(i, 5);
               total=(int)tableorder.getValueAt(i, 6);
               
              insert.setInt(1, lastinsertid);
              insert.setString(2, idproduct);
              insert.setString(3, product); 
              insert.setString(4, brand);
              insert.setString(5, price);
              insert.setString(6, quantity);
              insert.setInt(7, total); 
              insert.executeUpdate();
            }
            //update stocks/quantity
            String query1 = ("update product_details set quantity=quantity-? where idproduct=?");
            insert=con.prepareStatement(query1);
            
             for(int i=0;i<tableorder.getRowCount();i++){
               idproduct=(String)tableorder.getValueAt(i, 1);
               quantity=(String)tableorder.getValueAt(i, 5);
              
               
               
               
              insert.setString(1, quantity);
              insert.setString(2, idproduct);
            
              insert.execute();
            }
            
            
            
            insert.addBatch();
            JOptionPane.showMessageDialog(this, "Ordered Product Saved");
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
      public void print(){
          String customerid=txtcustomerid.getText();
          String sub=txtsubtotal.getText();
          String pay=txtpay.getText();
          String balance=txtbalance.getText();
          String address=txtshippingaddress.getText();
          String mop = txtmop.getSelectedItem().toString();
          String ordernum=txtordernum.getText();
          
        try {
            new print(ordernum,customerid,sub,pay,balance,address,mop,tableorder.getModel()).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      }
  
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        productdisplay = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtproductname = new javax.swing.JTextField();
        txtbrand = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        txtquantity = new javax.swing.JTextField();
        txtproductid = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cusdisplay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtshippingaddress = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtcustomername = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtcustomerid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableorder = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txtsubtotal = new javax.swing.JTextField();
        txtmop = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        txtbalance = new javax.swing.JTextField();
        txtdatetoday = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtordernum = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(211, 211, 211));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Details"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(productdisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 100));

        jLabel4.setText("Product ID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel6.setText("Quantity:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, -1, -1));

        jLabel13.setText("Product Name:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 46, -1, 10));

        jLabel15.setText("Brand:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabel17.setText("Price:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, -1, -1));

        txtproductname.setEditable(false);
        jPanel1.add(txtproductname, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 110, -1));

        txtbrand.setEditable(false);
        jPanel1.add(txtbrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 120, -1));

        txtprice.setEditable(false);
        jPanel1.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 120, -1));
        jPanel1.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 50, -1));

        txtproductid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtproductidKeyPressed(evt);
            }
        });
        jPanel1.add(txtproductid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 90, -1));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 1030, 140));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cusdisplay.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(cusdisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 100));

        jLabel3.setText("Shipping Address:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, -1));

        jLabel9.setText("Email Address:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        txtshippingaddress.setEditable(false);
        txtshippingaddress.setColumns(20);
        txtshippingaddress.setRows(5);
        jScrollPane1.setViewportView(txtshippingaddress);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 270, 70));

        jLabel10.setText("Customer Name:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jLabel12.setText("Customer ID:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        txtcustomername.setEditable(false);
        jPanel2.add(txtcustomername, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 150, -1));

        txtemail.setEditable(false);
        jPanel2.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 150, -1));

        txtcustomerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcustomeridActionPerformed(evt);
            }
        });
        txtcustomerid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcustomeridKeyPressed(evt);
            }
        });
        jPanel2.add(txtcustomerid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 90, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 1030, 140));

        jButton1.setBackground(new java.awt.Color(233, 239, 192));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 310, 80, 50));

        tableorder.setBackground(new java.awt.Color(233, 239, 192));
        tableorder.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 204, 0)));
        tableorder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Product ID", "Product Name", "Brand", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableorder.setGridColor(new java.awt.Color(102, 204, 0));
        tableorder.setSelectionBackground(new java.awt.Color(0, 255, 0));
        tableorder.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableorder.setShowGrid(false);
        tableorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableorderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableorder);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 790, 280));

        jButton2.setBackground(new java.awt.Color(233, 239, 192));
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 430, 80, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setText("Order No.:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 600, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setText("Date:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 560, -1, -1));

        jButton4.setBackground(new java.awt.Color(233, 239, 192));
        jButton4.setText("Print Invoice:");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 650, 110, 40));

        txtsubtotal.setEditable(false);
        txtsubtotal.setBackground(new java.awt.Color(255, 255, 255));
        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });
        jPanel4.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 510, 110, -1));

        txtmop.setBackground(new java.awt.Color(233, 239, 192));
        txtmop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash on Delivery", "Gcash", "Paypal", "Bank Transfer" }));
        jPanel4.add(txtmop, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Mode of Payment:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("SubTotal:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 520, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel16.setText("Pay:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 510, -1, -1));

        txtpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayActionPerformed(evt);
            }
        });
        jPanel4.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 510, 110, -1));

        txtbalance.setEditable(false);
        txtbalance.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.add(txtbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 550, 110, -1));

        txtdatetoday.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanel4.add(txtdatetoday, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 560, 110, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel18.setText("Balance:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 550, -1, -1));

        txtordernum.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanel4.add(txtordernum, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 600, 110, 20));

        jPanel3.setBackground(new java.awt.Color(78, 148, 79));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-close-24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1244, 24, -1, 44));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-back-40 (1).png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 24, -1, 44));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/easypclogo-removebg-preview.png"))); // NOI18N
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 210, 60));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel7.setText("POINT OF SALES");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 300, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1300, 788));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pos();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableorderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableorderMouseClicked
        // TODO add your handling code here:
        int rowNo = tableorder.getSelectedRow();
       TableModel model = tableorder.getModel();

        txtproductid.setText(model.getValueAt(rowNo, 1).toString());
    }//GEN-LAST:event_tableorderMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if(tableorder.getSelectedRow() != -1) {
        model.removeRow(tableorder.getSelectedRow());
         }
       int sum=0;
          for(int i=0;i<tableorder.getRowCount();i++){
                             sum=sum + Integer.parseInt(tableorder.getValueAt(0, 6).toString());
                         }
                         txtsubtotal.setText(Integer.toString(sum));
                   
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int pay = Integer.parseInt(txtpay.getText());
        int subtotal = Integer.parseInt(txtsubtotal.getText());
        
        int balance = pay-subtotal;
        txtbalance.setText(String.valueOf(balance));
  
        order();
        print();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtcustomeridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcustomeridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcustomeridActionPerformed

    private void txtcustomeridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcustomeridKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            String  customerid =txtcustomerid.getText();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
                insert=con.prepareStatement("select * from customer_details where idcustomer=?");
                insert.setString(1, customerid);
                rs= insert.executeQuery();
                if (rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Customer ID not found");
                } else{
                    String customer =rs.getString("customer");
                    String email =rs.getString("email");
                    String address =rs.getString("shipping_address");
                    
                    byte[] imagedata =rs.getBytes("customer_img");
                    ImageIcon format = new ImageIcon(imagedata);
                    Image mm = format.getImage();
                    Image img2 = mm.getScaledInstance(cusdisplay.getWidth(),cusdisplay.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(img2);
                    cusdisplay.setIcon(image);
                     
                    txtcustomername.setText(customer.trim());
                    txtemail.setText(email.trim());
                    txtshippingaddress.setText(address.trim());
                   
                }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtcustomeridKeyPressed

    private void txtproductidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductidKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            String  productid =txtproductid.getText();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
                insert=con.prepareStatement("select * from product_details where idproduct=?");
                insert.setString(1, productid);
                rs= insert.executeQuery();
                if (rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Product ID not found");
                } else{
                    String product =rs.getString("product");
                    String brand =rs.getString("brand");
                    String price =rs.getString("price");
                    
                    byte[] imagedata =rs.getBytes("imageFile");
                    ImageIcon format1 = new ImageIcon(imagedata);
                    Image mm1 = format1.getImage();
                    Image img3 = mm1.getScaledInstance(productdisplay.getWidth(),productdisplay.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon image1 = new ImageIcon(img3);
                    productdisplay.setIcon(image1);
                  

                     
                    txtproductname.setText(product.trim());
                    txtbrand.setText(brand.trim());
                    txtprice.setText(price.trim());
                    
                }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtproductidKeyPressed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void txtpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cusdisplay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel productdisplay;
    private javax.swing.JTable tableorder;
    private javax.swing.JTextField txtbalance;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtcustomerid;
    private javax.swing.JTextField txtcustomername;
    private javax.swing.JLabel txtdatetoday;
    private javax.swing.JTextField txtemail;
    private javax.swing.JComboBox<String> txtmop;
    private javax.swing.JLabel txtordernum;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproductid;
    private javax.swing.JTextField txtproductname;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextArea txtshippingaddress;
    private javax.swing.JTextField txtsubtotal;
    // End of variables declaration//GEN-END:variables
}
