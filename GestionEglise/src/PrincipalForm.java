/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Date;
import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author EL
 */
public class PrincipalForm extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalForm
     */
    public PrincipalForm() {
        initComponents();
        Id_Croyant.setEditable(false);
        Afficher();
        dateHeure();
        Home();
    }
    public void dateHeure(){
        
        Date d = new Date();
        SimpleDateFormat dat = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
        SimpleDateFormat heur = new SimpleDateFormat("HH:mm");
        
            heure.setText(heur.format(d));
            date.setText(dat.format(d));
    }
    void Home()
     {
        Church.setVisible(true);
        Background.setVisible(true);
        Ecrit.setVisible(true);
        Espace.setVisible(true);
        RocherTab.setVisible(true);
        Menus.setVisible(true);
        PMembres.setVisible(false);
     }
    
    void ChgStyle(JPanel Set)
    {
        PanMembre.setBackground(new Color(182,154,208));
        //PanPointsFocaux.setBackground(new Color(182,154,208));
    }
    void ChgStyle1(JPanel Set)
    {
        //PanMembre.setBackground(new Color(182,154,208));
        PanPointsFocaux.setBackground(new Color(182,154,208));
    }
    
    void RtnStyle(JPanel Reset)
    {
        PanMembre.setBackground(new Color(102,102,255));
        //PanPointsFocaux.setBackground(new Color(102,102,255));
    }
    void RtnStyle1(JPanel Reset)
    {
        //PanMembre.setBackground(new Color(102,102,255));
        PanPointsFocaux.setBackground(new Color(102,102,255));
    }
    
    //PARTIE COYANTS(MEMBRES)
     void Afficher()
    {
        try
        {
            //Les instructions pour etablir la connexion vers la base de donnees
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String chemin="jdbc:sqlserver://localhost:1433;databaseName=GestionEglise;user=sa;password=dddd";
            Connection con=DriverManager.getConnection(chemin);
            
            //Afficher les donnees
            String sql="select * from CROYANT";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            
            // objet DbUtils permet et formater les donnees sous forme de tableau
            TableMembres.setModel(DbUtils.resultSetToTableModel(rs));            
           
            con.close();
           
        }        
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Echec de chargement"+ex.toString());
        }
    }
     
     void Enregistrer(){
        try
        {
            //Les instructions pour etablir la connexion vers la base de donnees
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String chemin="jdbc:sqlserver://localhost:1433;databaseName=GestionEglise;user=sa;password=dddd;";
            Connection con=DriverManager.getConnection(chemin);
            
            //Enregistrement des donnees
            String sql="insert into CROYANT (nom,postnom,prenom,telephone,adresse,mail) values (?,?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, Nom_Croyant.getText());
            pst.setString(2, Postnom_Croyant.getText());
            pst.setString(3, Prenom_Croyant.getText());
            pst.setString(4, Telephone_Croyant.getText().toString());
            pst.setString(5, Adresse_Croyant.getText().toString());
            pst.setString(6, Mail_Croyant.getText().toString());
            
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Enregistrement réussi");
            Afficher();
        }        
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Echec d'enregistrement");
        }
    }
     
     void Modifier(){
        try
        {
            //Les instructions pour etablir la connexion vers la base de donnees
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String chemin="jdbc:sqlserver://localhost:1433;databaseName=GestionEglise;user=sa;password=dddd;";
            Connection con=DriverManager.getConnection(chemin);
            
            //Modification des donnees
            String sql="update CROYANT set nom=?,postnom=?,prenom=?,telephone=?,adresse=?,mail=? where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, Nom_Croyant.getText());
            pst.setString(2, Postnom_Croyant.getText());
            pst.setString(3, Prenom_Croyant.getText());
            pst.setString(4, Telephone_Croyant.getText());
            pst.setString(5, Adresse_Croyant.getText());
            pst.setString(6, Mail_Croyant.getText());
            pst.setInt(7, Integer.parseInt(Id_Croyant.getText()));
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Modification réussie");
            Afficher();
        }        
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Echec de modification"+ex.toString());
        }
    }
     
     void Supprimer(){
        try
        {
            //Les instructions pour etablir la connexion vers la base de donnees
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String chemin="jdbc:sqlserver://localhost:1433;databaseName=GestionEglise;user=sa;password=dddd;";
            Connection con=DriverManager.getConnection(chemin);
            
            //Suppression des donnees
            String sql="delete from CROYANT where id = ?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(Id_Croyant.getText()));
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Suppression réussie");
            Afficher();
        }        
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Echec de suppression"+ex.toString());
        }
    }
     
     void selection_modifier() {
        try {

            int row = TableMembres.getSelectedRow();
            String Table_click = (TableMembres.getModel().getValueAt(row, 0).toString());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=GestionEglise;user=sa;password=dddd;";
            Connection con = DriverManager.getConnection(url);
            String sql = "select * from CROYANT where id = '" + Table_click + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                 String id = rs.getString("id");
                 String nom = rs.getString("nom");
                 String postnom = rs.getString("postnom");
                 String prenom = rs.getString("prenom");
                 String telephone = rs.getString("telephone");
                 String adresse = rs.getString("adresse");
                 String mail = rs.getString("mail");
                 
               Id_Croyant.setText(id);
               Nom_Croyant.setText(nom);
               Postnom_Croyant.setText(postnom);
               Prenom_Croyant.setText(prenom);
               Telephone_Croyant.setText(telephone);
               Adresse_Croyant.setText(adresse);
               Mail_Croyant.setText(mail);
               
            } 
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

        jPanel1 = new javax.swing.JPanel();
        Church = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        heure = new javax.swing.JLabel();
        Menus = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PanMembre = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        PanPointsFocaux = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Espace = new javax.swing.JPanel();
        Ecrit = new javax.swing.JLabel();
        RocherTab = new javax.swing.JPanel();
        Background = new javax.swing.JLabel();
        PMembres = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Adresse_Croyant = new javax.swing.JTextField();
        Mail_Croyant = new javax.swing.JTextField();
        Telephone_Croyant = new javax.swing.JTextField();
        Prenom_Croyant = new javax.swing.JTextField();
        Postnom_Croyant = new javax.swing.JTextField();
        Id_Croyant = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableMembres = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        Nom_Croyant = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Church.setBackground(new java.awt.Color(107, 107, 198));
        Church.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Church.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CHURCH MANAGEMENT");
        Church.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 32, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_church_100px.png"))); // NOI18N
        Church.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, -1, 90));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_church_100px.png"))); // NOI18N
        Church.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 90));

        date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        date.setText("Date");
        Church.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 170, 20));

        heure.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        heure.setText("Heure");
        Church.add(heure, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 20));

        jPanel1.add(Church, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 90));

        Menus.setBackground(new java.awt.Color(102, 102, 255));
        Menus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Menus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_cash_in_hand_70px.png"))); // NOI18N
        Menus.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 13, -1, -1));

        jLabel6.setFont(new java.awt.Font("Constantia", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TRESORERIE");
        Menus.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Constantia", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CARNET DE PAIEMENTS");
        Menus.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_contacts_70px.png"))); // NOI18N
        Menus.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        PanMembre.setBackground(new java.awt.Color(102, 102, 255));
        PanMembre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanMembreMouseClicked(evt);
            }
        });
        PanMembre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_member_70px.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        PanMembre.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 60));

        jLabel9.setFont(new java.awt.Font("Constantia", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("MEMBRES");
        PanMembre.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 60, -1));

        Menus.add(PanMembre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 80));

        PanPointsFocaux.setBackground(new java.awt.Color(102, 102, 255));
        PanPointsFocaux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanPointsFocauxMouseClicked(evt);
            }
        });
        PanPointsFocaux.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_people_70px.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        PanPointsFocaux.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 60));

        jLabel11.setFont(new java.awt.Font("Constantia", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("POINTS FOCAUX");
        PanPointsFocaux.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        Menus.add(PanPointsFocaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 150, 80));

        jPanel1.add(Menus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 1070, 100));

        Espace.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ecrit.setFont(new java.awt.Font("Lucida Calligraphy", 1, 70)); // NOI18N
        Ecrit.setForeground(new java.awt.Color(255, 255, 255));
        Ecrit.setText("ROCHER TABERNACLE");
        Espace.add(Ecrit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        RocherTab.setBackground(new java.awt.Color(0, 0, 0,80));
        RocherTab.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout RocherTabLayout = new javax.swing.GroupLayout(RocherTab);
        RocherTab.setLayout(RocherTabLayout);
        RocherTabLayout.setHorizontalGroup(
            RocherTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1026, Short.MAX_VALUE)
        );
        RocherTabLayout.setVerticalGroup(
            RocherTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        Espace.add(RocherTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 1030, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Rocher.jpg"))); // NOI18N
        Espace.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 410));

        PMembres.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 255));
        jLabel12.setText("ESPACE DES MEMBRES");
        PMembres.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("ID:");
        PMembres.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("POST-NOM:");
        PMembres.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("PRENOM:");
        PMembres.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("ADRESSE:");
        PMembres.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("N° TELEPHONE:");
        PMembres.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("ACTUALISER");
        PMembres.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        Adresse_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Adresse_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 226, -1));

        Mail_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Mail_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 226, -1));

        Telephone_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Telephone_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 226, -1));

        Prenom_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Prenom_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 226, -1));

        Postnom_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Postnom_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 226, -1));

        Id_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Id_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 226, -1));

        TableMembres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableMembres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMembresMouseClicked(evt);
            }
        });
        TableMembres.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                TableMembresInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TableMembres);

        PMembres.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 107, 650, 290));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_refresh_50px.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        PMembres.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_add_administrator_50px.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        PMembres.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_update_user_50px.png"))); // NOI18N
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        PMembres.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_delete_user_male_50px_1.png"))); // NOI18N
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        PMembres.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("MAIL:");
        PMembres.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("AJOUTER");
        PMembres.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("SUPPRIMER");
        PMembres.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("HOME");
        PMembres.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, -1, -1));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("NOM:");
        PMembres.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        Nom_Croyant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMembres.add(Nom_Croyant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 226, -1));
        PMembres.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 282, 360, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_home_35px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        PMembres.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, -1, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("MODIFIER");
        PMembres.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icônes/icons8_search_20px.png"))); // NOI18N
        PMembres.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, -1, 30));

        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });
        PMembres.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 250, 30));

        jLabel30.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel30.setText("RECHERCHE");
        PMembres.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, 30));

        Espace.add(PMembres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 410));

        jPanel1.add(Espace, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1070, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 600));

        setSize(new java.awt.Dimension(1086, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        PMembres.setVisible(true);
        Church.setVisible(true);
        Menus.setVisible(true);
        RocherTab.setVisible(false);
        Ecrit.setVisible(false);
        Background.setVisible(false);
        ChgStyle(PanMembre);
        RtnStyle1(PanPointsFocaux);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        Enregistrer();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        Modifier();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void TableMembresInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_TableMembresInputMethodTextChanged
        Afficher();
        // TODO add your handling code here:
    }//GEN-LAST:event_TableMembresInputMethodTextChanged

    private void TableMembresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMembresMouseClicked
        selection_modifier();
        // TODO add your handling code here:
    }//GEN-LAST:event_TableMembresMouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        Id_Croyant.setText("");
        Nom_Croyant.setText("");
        Postnom_Croyant.setText("");
        Prenom_Croyant.setText("");
        Telephone_Croyant.setText("");
        Adresse_Croyant.setText("");
        Mail_Croyant.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        Supprimer();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Home();
        RtnStyle(PanMembre);
        RtnStyle1(PanPointsFocaux);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void PanMembreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanMembreMouseClicked
        
        // TODO add your handling code here:
    }//GEN-LAST:event_PanMembreMouseClicked

    private void PanPointsFocauxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanPointsFocauxMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_PanPointsFocauxMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        /*PMembres.setVisible(true);
        Church.setVisible(true);
        Menus.setVisible(true);
        RocherTab.setVisible(false);
        Ecrit.setVisible(false);
        Background.setVisible(false);*/
        ChgStyle1(PanPointsFocaux);
        RtnStyle(PanMembre);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        DefaultTableModel table = (DefaultTableModel)TableMembres.getModel();
        String search = Search.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
        TableMembres.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchKeyReleased

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
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adresse_Croyant;
    private javax.swing.JLabel Background;
    private javax.swing.JPanel Church;
    private javax.swing.JLabel Ecrit;
    private javax.swing.JPanel Espace;
    private javax.swing.JTextField Id_Croyant;
    private javax.swing.JTextField Mail_Croyant;
    private javax.swing.JPanel Menus;
    private javax.swing.JTextField Nom_Croyant;
    private javax.swing.JPanel PMembres;
    private javax.swing.JPanel PanMembre;
    private javax.swing.JPanel PanPointsFocaux;
    private javax.swing.JTextField Postnom_Croyant;
    private javax.swing.JTextField Prenom_Croyant;
    private javax.swing.JPanel RocherTab;
    private javax.swing.JTextField Search;
    private javax.swing.JTable TableMembres;
    private javax.swing.JTextField Telephone_Croyant;
    private javax.swing.JLabel date;
    private javax.swing.JLabel heure;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
