/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import analisis_lexico.*;
import static analisis_lexico.Analisis_Lexico.LetraANumero;
import static analisis_lexico.Analisis_Lexico.compara_cadena;
import static analisis_lexico.Analisis_Lexico.errores_lexicos;
import static analisis_lexico.Analisis_Lexico.esCadena;
import static analisis_lexico.Analisis_Lexico.esComentario;
import static analisis_lexico.Analisis_Lexico.esIdentifcador;
import static analisis_lexico.Analisis_Lexico.esNumero;
import static analisis_lexico.Analisis_Lexico.funcion_hash;
import static analisis_lexico.Analisis_Lexico.indexar;
import static analisis_lexico.Analisis_Lexico.pseudo_codigo;
import static analisis_lexico.tokens.obtener_tokens;
import analisis_sintactico.tabla_sintactica;
import static analisis_sintactico.tabla_sintactica.pseudo_c;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joses
 */
public class Interfaz extends javax.swing.JFrame {
ImageIcon img = new ImageIcon("C:\\Users\\joses\\OneDrive\\Documentos\\NetBeansProjects\\Compilador\\src\\img\\twitter_bird.png");
static DefaultTableModel modelo;
        ArrayList<String> Preservadas = new ArrayList<>();
        ArrayList<String> Operadores = new ArrayList<>();
        ArrayList<String> Delimitadores = new ArrayList<>();
        
       static Object datosR[]= new Object[100]; //Reservadas
       static Object datosO[] = new Object[100]; //Operadores aritmeticos
       static Object datosL[] = new Object[100]; //Operadores logicos
       static Object datosN[] = new Object[100]; //Numeros
       static Object datosD[] = new Object[100]; //Delimitadores
       static Object datosC[] = new Object[100]; //Comentarios
       static Object datosI[] = new Object[100]; //Identificadores
       static Object datosCa[] = new Object[100]; //Cadenas
       static Object datosDesco[] = new Object[100]; //Desconocidos
       public static Panel_sintactico P_sintactico;
       public static Panel_lexico P_lexico;
       public static Panel_semantico P_semantico;
/**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setBackground(Color.yellow);
        btnLexicos.setEnabled(false);
        
        P_sintactico = new Panel_sintactico();
        P_sintactico.setSize(789, 184);
        P_sintactico.setLocation(5,5);
                
        P_lexico = new Panel_lexico();
        P_lexico.setSize(789, 184);
        P_lexico.setLocation(5,5);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(P_lexico, BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
        
        P_semantico = new Panel_semantico();
        P_semantico.setSize(789, 184);
        P_semantico.setLocation(5,5);
        modelo=(DefaultTableModel) jTable1.getModel();
        modelo.addColumn("Token"); modelo.addColumn("Tipo");
    }
   
    public static void reservadas(String dato){
        datosR[0] = dato;
        datosR[1] = "Palabra Reservada";
        modelo.addRow(datosR);       
    }
    
    public static void identificadores(String dato){
        datosI[0] = dato;
        datosI[1] = "Identificador";
        modelo.addRow(datosI);
    }
    
    public static void comentario(String dato){
        datosC[0] = dato;
        datosC[1] = "Comentario";
        modelo.addRow(datosC);
    }
    
    public static void operadoresA(String dato){
        datosO[0] = dato;
        datosO[1] = "Operador Aritmetico";
        modelo.addRow(datosO);
    }
    
    public static void operadoresL(String dato){
        datosL[0] = dato;
        datosL[1] = "Operador Logico";
        modelo.addRow(datosL);
    }
    
    public static void delimitadores(String dato){
        datosD[0] = dato;
        datosD[1] = "Delimitador";
        modelo.addRow(datosD);
    }
    
    public static void cadenas(String dato){
        datosCa[0] = dato;
        datosCa[1] = "Cadena";
        modelo.addRow(datosCa);
    }
    
    public static void desconocidos(String dato){
        datosDesco[0] = dato;
        datosDesco[1] = "Desconocido";
        modelo.addRow(datosDesco);
        
    }
    
    public static void numero(String dato){
        datosN[0] = dato;
        datosN[1] = "Numerico";
        modelo.addRow(datosN);    
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        Lineas = new javax.swing.JEditorPane();
        txtATexto1 = new javax.swing.JEditorPane();
        labelLineas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAbrirArchivo = new javax.swing.JButton();
        btnGuardarArchivo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jCodigoSintactica = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();
        PanelPrincipal = new javax.swing.JPanel();
        btnSintacticos = new javax.swing.JButton();
        btnLexicos = new javax.swing.JButton();
        btnSemanticos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Birdy++");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 51, 255));
        setForeground(new java.awt.Color(102, 102, 102));
        setIconImage(img.getImage());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setAutoscrolls(true);

        Lineas.setEditable(false);
        Lineas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Lineas.setText("1");
        Lineas.setAutoscrolls(false);
        Lineas.setOpaque(false);

        txtATexto1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtATexto1.setAutoscrolls(false);
        txtATexto1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtATexto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtATexto1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtATexto1KeyReleased(evt);
            }
        });

        labelLineas.setText("1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(695, 695, 695)
                        .addComponent(labelLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtATexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(366, Short.MAX_VALUE)
                .addComponent(labelLineas)
                .addGap(226, 226, 226))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtATexto1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(Lineas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jScrollPane1.setEnabled(false);

        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tabla de simbolos");

        jToolBar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jToolBar1.setRollover(true);

        btnAbrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-abrir-archivo.png"))); // NOI18N
        btnAbrirArchivo.setToolTipText("Abrir Archivo");
        btnAbrirArchivo.setFocusable(false);
        btnAbrirArchivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirArchivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirArchivoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAbrirArchivo);

        btnGuardarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnGuardarArchivo.setToolTipText("Guardar Archivo");
        btnGuardarArchivo.setFocusable(false);
        btnGuardarArchivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarArchivo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarArchivoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardarArchivo);

        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-de-reproduccion.png"))); // NOI18N
        jButton2.setToolTipText("Compilar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jCodigoSintactica.setEditable(false);
        jScrollPane6.setViewportView(jCodigoSintactica);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanel3);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Pseudo Codigo");

        PanelPrincipal.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnSintacticos.setText("Errores Sintacticos");
        btnSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSintacticosActionPerformed(evt);
            }
        });

        btnLexicos.setText("Errores Lexicos");
        btnLexicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicosActionPerformed(evt);
            }
        });

        btnSemanticos.setText("Errores Semanticos");
        btnSemanticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemanticosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLexicos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSintacticos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSemanticos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(PanelPrincipal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(127, 127, 127)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(jLabel3)))
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSintacticos)
                        .addComponent(btnSemanticos)
                        .addComponent(btnLexicos))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //
        
        Panel_lexico.txtLexico.setText("");
        modelo.setRowCount(0);
        Preservadas.clear();
        Operadores.clear();
        Delimitadores.clear();
        ArrayList<String> aux=new ArrayList<>();
        ArrayList<String> palabras = new ArrayList<>();
        String token = "";
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(), "\n");
        while(st.hasMoreElements())
        {
            token = st.nextToken();
            palabras.add(token);       
        }
        for (int i = 0; i < palabras.size(); i++) {
            System.out.println(palabras.get(i));
        }        
        for(int i=0; i < palabras.size();i++){
                aux=obtener_tokens(palabras.get(i));
                compara_cadena(aux, i+1);
        }
        jCodigoSintactica.setText("");
        jCodigoSintactica.setText(analisis_lexico.Analisis_Lexico.imprimirMap());
        
        //esto es de la fase sintactica
        tabla_sintactica.pila.clear();//quitar cuando se cambie cargar tabla
        tabla_sintactica.no_terminales.clear();//quitar cuando se cambie cargar tabla
        tabla_sintactica.tabla_sintactica.clear();//quitar cuando se cambie cargar tabla
        tabla_sintactica.cargar_tabla();//solo al inicio del programa
        tabla_sintactica.tabla_sintactica();
        //
        analisis_lexico.Analisis_Lexico.pseudo_codigo.clear();
        analisis_lexico.Analisis_Lexico.codigo.clear();
        analisis_lexico.Analisis_Lexico.int_semantico.clear();
        analisis_lexico.Analisis_Lexico.tabla_id.clear();
        //
        
        Preservadas.clear();
        Delimitadores.clear();
        Operadores.clear();
        
        String error = "Correcto";
        
        if (!errores_lexicos.isEmpty())
        {
            error="";
            for(int k=0;k<errores_lexicos.size();k=k+2)
            {
                error=error+"Error en la linea: " + errores_lexicos.get(k+1) + " en la palabra: " + errores_lexicos.get(k)+"\n";
            }
        }
        Panel_lexico.txtLexico.setText(error);
        
        errores_lexicos.clear();
        error="Correcto";
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void btnGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarArchivoActionPerformed

        JFileChooser fc=new JFileChooser();
        int seleccion = fc.showSaveDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            try (FileWriter fw = new FileWriter(archivo)){
                fw.write(txtATexto1.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnGuardarArchivoActionPerformed

    private void btnAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirArchivoActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.bird", "bird");
        fc.setFileFilter(filtro);
        int seleccion=fc.showOpenDialog(this);
        if(seleccion== JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
               try(FileReader fr=new FileReader(archivo)){
        String cadena="";
        int valor=fr.read();
        int conta = 1;
        while(valor!=-1){
            cadena=cadena+(char)valor;
            valor=fr.read();
            Lineas.setText(conta +"");
            conta++;
        }
        txtATexto1.setText(cadena);
    } catch (IOException e1) {
        e1.printStackTrace();
    }
        }

        
    }//GEN-LAST:event_btnAbrirArchivoActionPerformed

    private void txtATexto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyReleased
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"\n",true);
        String txt = "",token,lineas = "";
        int cont = 1;

        while (st.hasMoreTokens()){
            token= st.nextToken();
            if("\n".equals(token)) cont++;
        }

        for(int i = 1; i <= cont; i++){
            txt += i+"\n";
            lineas = i+"";
        }
        Lineas.setText(txt);
        labelLineas.setText(lineas);

    }//GEN-LAST:event_txtATexto1KeyReleased

    private void txtATexto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtATexto1KeyPressed
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(),"\n",true);
        String txt = "",token,lineas = "";
        int cont = 1;

        while (st.hasMoreTokens()){
            token= st.nextToken();
            if("\n".equals(token)) cont++;
        }

        for(int i = 1; i <= cont; i++){
            txt += i+"\n";
            lineas = i+"";
        }
        Lineas.setText(txt);
        labelLineas.setText(lineas);
    }//GEN-LAST:event_txtATexto1KeyPressed

    private void btnSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSintacticosActionPerformed
        btnLexicos.setEnabled(true);
        btnSemanticos.setEnabled(true);
        btnSintacticos.setEnabled(false);
        
        PanelPrincipal.removeAll();
        PanelPrincipal.add(P_sintactico, BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    }//GEN-LAST:event_btnSintacticosActionPerformed

    private void btnLexicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicosActionPerformed
        btnLexicos.setEnabled(false);
        btnSemanticos.setEnabled(true);
        btnSintacticos.setEnabled(true);
        
        PanelPrincipal.removeAll();
        PanelPrincipal.add(P_lexico, BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();

    }//GEN-LAST:event_btnLexicosActionPerformed

    private void btnSemanticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemanticosActionPerformed
        btnLexicos.setEnabled(true);
        btnSemanticos.setEnabled(false);
        btnSintacticos.setEnabled(true);
        
        PanelPrincipal.removeAll();
        PanelPrincipal.add(P_semantico, BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    }//GEN-LAST:event_btnSemanticosActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                analisis_lexico.Analisis_Lexico.generar();
                new Interfaz().setVisible(true);
            }
        });
    }
    
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane Lineas;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnAbrirArchivo;
    private javax.swing.JButton btnGuardarArchivo;
    private javax.swing.JButton btnLexicos;
    private javax.swing.JButton btnSemanticos;
    private javax.swing.JButton btnSintacticos;
    private javax.swing.JButton jButton2;
    private javax.swing.JEditorPane jCodigoSintactica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelLineas;
    private javax.swing.JEditorPane txtATexto1;
    // End of variables declaration//GEN-END:variables
}
