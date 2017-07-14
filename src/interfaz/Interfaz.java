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
import static analisis_lexico.Analisis_Lexico.esCadena;
import static analisis_lexico.Analisis_Lexico.esComentario;
import static analisis_lexico.Analisis_Lexico.esIdentifcador;
import static analisis_lexico.Analisis_Lexico.esNumero;
import static analisis_lexico.Analisis_Lexico.funcion_hash;
import static analisis_lexico.Analisis_Lexico.indexar;
import static analisis_lexico.tokens.obtener_tokens;
import analisis_sintactico.tabla_sintactica;
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
/**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
         modelo=(DefaultTableModel) jTable1.getModel();
       modelo.addColumn("Token"); modelo.addColumn("Tipo");

    }
   
    public void CargarPalabras(){
        Preservadas.add("inicio");
        Preservadas.add("fin");
        Preservadas.add("lista_sentencia");
        Preservadas.add("sentencia");
        Preservadas.add("expresion");
        Preservadas.add("while");
        Preservadas.add("if_else");
        Preservadas.add("switch_case");
        Preservadas.add("if");
        Preservadas.add("for");
        Preservadas.add("lista_case");
        Preservadas.add("case");
        Preservadas.add("default");
        Preservadas.add("condicion");
        Preservadas.add("condicion_logica");
        Preservadas.add("condicion_and");
        Preservadas.add("condicion_or");
        Preservadas.add("identificador");
        Preservadas.add("constante");
        Preservadas.add("int");
        Preservadas.add("float");
        Preservadas.add("double");
        Preservadas.add("String");
        Preservadas.add("byte");
        Preservadas.add("boolean");
        Preservadas.add("char");
        
        Operadores.add("+");
        Operadores.add("-");
        Operadores.add("/");
        Operadores.add("*");
        Operadores.add("=");
        Operadores.add("%");
        Operadores.add("^");
        
        //Operadores Logicos
        Operadores.add("<");
        Operadores.add(">");
        Operadores.add("<=");
        Operadores.add(">=");
        Operadores.add("==");
        Operadores.add("!=");
        
        Delimitadores.add("#");
        Delimitadores.add(";");
        Delimitadores.add("; \r");
        Delimitadores.add("{");
        Delimitadores.add("}");
        Delimitadores.add(")");
        Delimitadores.add(",");
        Delimitadores.add("(");
        Delimitadores.add("'");
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
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jErrores = new javax.swing.JEditorPane();
        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAbrirArchivo = new javax.swing.JButton();
        btnGuardarArchivo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jCodigoSintactica = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Birdy++");
        setAutoRequestFocus(false);
        setBackground(java.awt.Color.white);
        setIconImage(img.getImage());

        jPanel2.setBackground(java.awt.Color.white);

        Lineas.setEditable(false);
        Lineas.setText("1");
        Lineas.setOpaque(false);

        txtATexto1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
                .addGap(18, 18, 18)
                .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtATexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLineas)
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtATexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Lineas))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelLineas)
                .addGap(226, 226, 226))
        );

        jScrollPane2.setViewportView(jPanel2);

        jScrollPane1.setEnabled(false);

        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Tabla de simbolos");

        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Table.png"))); // NOI18N
        jButton1.setText("   Generar tabla de simbolos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jErrores.setEditable(false);
        jErrores.setBackground(java.awt.SystemColor.control);
        jScrollPane4.setViewportView(jErrores);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("Errores");
        jLabel2.setToolTipText("");

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

        jButton3.setText("jButton3");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jCodigoSintactica.setEditable(false);
        jScrollPane6.setViewportView(jCodigoSintactica);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanel3);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText("Pseudo Codigo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
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
                            .addComponent(jLabel3))
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
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        Preservadas.clear();
        Delimitadores.clear();
        Operadores.clear();
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> tokens2 = new ArrayList<>();
        ArrayList<String> tokens3 = new ArrayList<>();
        
        String linea = "",token2 = "",error = "",auxL="";
        boolean bandera1 =false,bandera2=false,bandera3=false,bandera4=false;
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(), "\n");
        
        while(st.hasMoreElements()){
            linea = st.nextToken();
            tokens.add(linea);
        }
        
        //tokens tiene cada linea
        for(int x =0;x<tokens.size();x++)
        {
           // tokens2.add(tokens.get(x)); //a tokens2 se agrega cada linea
            StringTokenizer st2 = new StringTokenizer(tokens.get(x), " ");

            while(st2.hasMoreElements()){
                token2 = st2.nextToken();
                tokens2.add(token2); //tokens2 tiene los tokens que tiene cada linea
            }
        }
       
        
        
        CargarPalabras();
      
        int lineasError [] = new int[tokens.size()];
        int numeroLinea = tokens.size();
        System.out.println("numero lineas " + numeroLinea);
        System.out.println("numero array " + lineasError.length);
        int noLinea = 0;

        for(int i=0;i<tokens.size();i++){
           System.out.println(tokens.get(i));
          tokens3 =  obtener_tokens(tokens.get(i));
         
                bandera1=esIdentifcador(tokens.get(i));
               System.out.println(bandera1);
          if(!bandera1)
          {
              noLinea = i;
                        i++;
                        lineasError[noLinea] = i;
                        i--;
          }

        }
        
            for(int k=0;k<lineasError.length;k++){
                String vacio = Integer.toString(lineasError[k]);
                auxL = "Error en la linea: " + Integer.toString(lineasError[k]) + "\n";
                if(vacio.equals("0"))
                    System.out.println("");
                else
                error += auxL;
               
                
            }
            if(bandera1)
           jErrores.setText("Correcto!");
            else
                 jErrores.setText(error);
            
            
            
            

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        modelo.setRowCount(0);
        Preservadas.clear();
        Operadores.clear();
        Delimitadores.clear();
        
        CargarPalabras();
      
       
        ArrayList<String> aux=new ArrayList<>();
        ArrayList<String> palabras = new ArrayList<>();
       
        String token = "";
      
        StringTokenizer st = new StringTokenizer(txtATexto1.getText(), "\n");
      
     
        while(st.hasMoreElements()){
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
        tabla_sintactica.cargar_tabla();
        tabla_sintactica.tabla_sintactica();
        //
        
        
        analisis_lexico.Analisis_Lexico.pseudo_codigo.clear();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton btnAbrirArchivo;
    private javax.swing.JButton btnGuardarArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JEditorPane jCodigoSintactica;
    private javax.swing.JEditorPane jErrores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelLineas;
    private javax.swing.JEditorPane txtATexto1;
    // End of variables declaration//GEN-END:variables
}
