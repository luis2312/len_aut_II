
package analisis_lexico;

import com.sun.org.apache.xml.internal.utils.StringToStringTable;
import interfaz.Interfaz;
import static interfaz.Interfaz.reservadas;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Analisis_Lexico 
{
    static ArrayList <ArrayList<String>> tabla_palabraDef;
    static ArrayList <ArrayList<String>> tabla_operadoresArit;
    static ArrayList <ArrayList<String>> tabla_operadoresLog;
    static ArrayList <ArrayList<String>> tabla_id;
    static ArrayList <ArrayList<String>> tabla_delimitador;
    static ArrayList <ArrayList<String>> tabla_operadoresBoo;
    public static ArrayList <String> pseudo_codigo=new ArrayList<>();
    static int count_pal_re=0, count_id=0, count_oper_arit=0, count_oper_log=0;
    //identificadores, numeros, strings
    
   
    public static void indexar()
    {
        //falta verificar si existe colicion
        //tabla_palabraDef.get(pos).add(cadena);
        System.out.println("Tabla completa"+tabla_palabraDef.toString());
        System.out.println("Tabla completa"+tabla_id.toString());
        System.out.println("Tabla completa"+tabla_operadoresArit.toString());
        System.out.println("Tabla completa"+tabla_operadoresLog.toString());
        System.out.println("Tabla completa"+tabla_delimitador.toString());
        System.out.println("Map "+pseudo_codigo.toString());
        //System.out.println("Seccion"+tabla_p.get(pos).toString());
    }
    
    public static void generar()
    {
        tabla_palabraDef = new ArrayList<>(100);
        tabla_id = new ArrayList<>(100);
        tabla_operadoresArit = new ArrayList<>(100);
        tabla_operadoresLog = new ArrayList<>(100);
        tabla_delimitador = new ArrayList<>(100);
        tabla_operadoresBoo = new ArrayList<>(100);
        for (int i = 0; i < 100; i++)
        {
            tabla_palabraDef.add(i,new ArrayList<String>());
            tabla_id.add(i,new ArrayList<String>());
            tabla_operadoresArit.add(i,new ArrayList<String>());
            tabla_operadoresLog.add(i,new ArrayList<String>());
            tabla_delimitador.add(i,new ArrayList<String>());
            tabla_operadoresBoo.add(i,new ArrayList<String>());
        }
       
        //datos definidos
        
        //Palabras reservadas
        obtenIndex("inicio", 1);
        obtenIndex("fin", 1);
        obtenIndex("while", 1);
        obtenIndex("if_else", 1);
        obtenIndex("if", 1);
        obtenIndex("case", 1);
        obtenIndex("default", 1);
        obtenIndex("identificador", 1);
        obtenIndex("constante", 1);
        obtenIndex("int", 1);
        obtenIndex("float", 1);
        obtenIndex("double", 1);
        obtenIndex("String", 1);
        obtenIndex("byte", 1);
        obtenIndex("boolean", 1);
        obtenIndex("char", 1);
        
        //Operadores Aritmeticos
        obtenIndex("+", 2);
        obtenIndex("-", 2);
        obtenIndex("/", 2);
        obtenIndex("*", 2);
        obtenIndex("=", 2);
        obtenIndex("%", 2);
        obtenIndex("^", 2);
        
        //Operadores Logicos
        obtenIndex("<", 3);
        obtenIndex(">", 3);
        obtenIndex("<=", 3);
        obtenIndex(">=", 3);
        obtenIndex("==", 3);
        obtenIndex("!=", 3);
        
        //Delimitadores
        //obtenIndex("#", 5);
        obtenIndex(";", 5);
        obtenIndex(";\r", 5); 
        obtenIndex("{", 5);
        obtenIndex("}", 5);
        obtenIndex(")", 5);
        obtenIndex(",", 5);
        obtenIndex("(", 5);
        
        //Operadores Booleanos
        obtenIndex("||", 6);
        obtenIndex("&&", 6);
        
    }
    
    
    //convertir un texto a un numero
    public static int LetraANumero(String llave)
    {
        String s = llave;
        int valor=0;
        String cad = s.toLowerCase();
        String t = "";
        for (int i = 0; i < cad.length(); ++i) 
        {
            //
            char ch = cad.charAt(i);
            if (!t.isEmpty()) 
                t += " ";
            int n = ch == ' ' ? 0 : ch - 'a' + 1;
                // --> ch == ' ' condicion booleana si es vacio toma el valor de 0 "?" <-- por default
                // --> si no cumple la primer condicion se va a la segunda (despues de ":")
                // --> se le resta el valor de a y de le suma un 1 al valor de char.
            char cag='a';
            //System.out.println("este es el valos dde a :"+(int) cag);
            t += String.valueOf(n); 
            valor = valor + Integer.parseInt(String.valueOf(n)); 
        }
        if (valor<0) {
            valor=valor*-1;
        }
        return valor;
       
    }
    
    //GENERAR EL INDICE
    public static void obtenIndex(String cadena, int id)
    {
        int numero=0, pos=0;
        numero=LetraANumero(cadena);
        pos = funcion_hash(numero);
        if (id==1) 
            tabla_palabraDef.get(pos).add(cadena);
        else if(id==2)
            tabla_operadoresArit.get(pos).add(cadena);
        else if(id==3)
            tabla_operadoresLog.get(pos).add(cadena);
        else if(id==4)
            tabla_id.get(pos).add(cadena);
        else if(id==5)
            tabla_delimitador.get(pos).add(cadena);
        else if(id==6)
            tabla_operadoresBoo.get(pos).add(cadena);
    }
    
    //FUNCION HASH
    public static int funcion_hash(int llave)
    {
        int r,n=llave,m=0;
        r = ((n + 1) % 100);
        //System.out.println("Resultado hash: "+r);
        return r;
    }
    
    
    //Se verifica que cada una de las palabras obtenidas 
    //en el codigo esten presentes en alguna de las listas.
    public static void compara_cadena(ArrayList<String> palabras, int linea)
    {
        int numero=0, pos=0;
        String aux="";
        boolean bandera=false,bandera2=false,bandera3=false,bandera4=false;
        for(int i = 0; i < palabras.size(); i++) 
        {
            bandera=false;
            bandera2=false;
            bandera3=false;
            numero=LetraANumero(palabras.get(i));
            pos = funcion_hash(numero);     
            //PALABRA RESERVADA
               
                bandera=esIdentifcador(palabras.get(i));
                bandera2=esCadena(palabras.get(i));
                bandera3=esComentario(palabras.get(i));
                bandera4=esNumero(palabras.get(i));
                
                if(bandera)
                    Interfaz.identificadores(palabras.get(i));
                if(bandera2)
                    Interfaz.cadenas(palabras.get(i));
                if(bandera3)
                    Interfaz.comentario(palabras.get(i));
                if(bandera4)
                    Interfaz.numero(palabras.get(i));
                
                    
                if(!bandera||!bandera2||!bandera3||!bandera4)
                {
                   
                    if (!tabla_palabraDef.get(pos).isEmpty()) //si no esta vacia puede ser una palabra reservada
                    {                
                        for(int j = 0; j < tabla_palabraDef.get(pos).size(); j++) 
                        {
                            if (tabla_palabraDef.get(pos).get(j).equals(palabras.get(i)))
                            {
                                System.out.println(""+palabras.get(i)+" es una palabra reservada");
                                count_pal_re++;
                                aux="Pal_Reservada"+count_pal_re;
                                if (palabras.get(i).matches("int")||palabras.get(i).matches("float")||palabras.get(i).matches("double")
                                   ||palabras.get(i).matches("String")||palabras.get(i).matches("byte")||palabras.get(i).matches("boolean")
                                   ||palabras.get(i).matches("char")) 
                                {
                                    pseudo_codigo.add("TIPO");
                                }
                                else
                                {
                                    pseudo_codigo.add(palabras.get(i));
                                }
                                //pseudo_codigo.add(aux);
                                reservadas(palabras.get(i));
                                bandera=true;
                            }
                        }
                    }
                    if (!tabla_operadoresArit.get(pos).isEmpty()) //si no esta vacia puede ser un operador aritmetico
                    {                
                        for(int j = 0; j < tabla_operadoresArit.get(pos).size(); j++) 
                        {
                            if (tabla_operadoresArit.get(pos).get(j).equals(palabras.get(i)))
                            {
                                System.out.println(""+palabras.get(i)+" es un operador aritmetico");
                                //count_oper_arit++;
                                //aux="Ope_arit"+count_oper_arit;
                                if (palabras.get(i).matches("="))
                                {
                                    pseudo_codigo.add("=");
                                }
                                else
                                {
                                    pseudo_codigo.add("OPERADOR_ARITMETICO");
                                }
                                Interfaz.operadoresA(palabras.get(i));
                                bandera=true;
                            }
                        }
                    }
                    if (!tabla_operadoresLog.get(pos).isEmpty()) //si no esta vacia puede ser un operador logico
                    {                
                        for(int j = 0; j < tabla_operadoresLog.get(pos).size(); j++) 
                        {
                            if (tabla_operadoresLog.get(pos).get(j).equals(palabras.get(i)))
                            {
                                System.out.println(""+palabras.get(i)+" es un operador logico");
                                //count_oper_log++;
                                //aux="Ope_log"+count_oper_log;
                                pseudo_codigo.add("OPERADOR_LOGICO");
                                Interfaz.operadoresL(palabras.get(i));
                                bandera=true;
                            }
                        }
                    }
                    if (!tabla_operadoresBoo.get(pos).isEmpty()) //si no esta vacia puede ser un operador logico
                    {                
                        for(int j = 0; j < tabla_operadoresBoo.get(pos).size(); j++) 
                        {
                            if (tabla_operadoresBoo.get(pos).get(j).equals(palabras.get(i)))
                            {
                                System.out.println(""+palabras.get(i)+" es un operador booleano");
                                pseudo_codigo.add("OPERADOR_BOOLEANO");
                                //Interfaz.operadoresL(palabras.get(i));// falta agregarlo a la interfaz
                                bandera=true;
                            }
                        }
                    }
                    if (!tabla_delimitador.get(pos).isEmpty()) //si no esta vacia puede ser un delimitador
                    {                
                        for(int j = 0; j < tabla_delimitador.get(pos).size(); j++) 
                        {
                            if (tabla_delimitador.get(pos).get(j).equals(palabras.get(i)))
                            {
                                pseudo_codigo.add(palabras.get(i));
                                System.out.println(""+palabras.get(i)+" es delimitador");
                                Interfaz.delimitadores(palabras.get(i));
                                bandera=true;
                            }
                        }
                    }
                    
                }if (!bandera && !bandera2 && !bandera3 && !bandera4)
                {
                    Interfaz.desconocidos(palabras.get(i));
                    System.out.println("error en la liena : U" +linea);
                }
                   
                
                
                    
        }
    }
    
    public static boolean esNumero(String lexema) 
    {
        boolean bandera=false;
        String estado="q0";
        String aux="";
        int x;
        try
        {
            for (int i = 0; i < lexema.length(); i++) 
            {
                switch (estado)
                {
                    case "q0":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            
                            if (lexema.length()>i+1) {
                                estado="q1";
                            }
                            else if (lexema.length()==i+1) {
                                bandera=true;
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                            }
                                
                        }
                        catch (Exception e)
                        { 
                            if ("+".equals(aux)) {
                                estado="q6";
                            }
                            else if ("-".equals(aux)) {
                                estado="q7";
                            }
                            else
                            {
                                i=lexema.length();
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case "q1":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            if (lexema.length()>i+1) {
                                estado="q1";
                            }
                            else if (lexema.length()==i+1) {
                                bandera=true;
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                            }
                        }
                        catch (Exception e)
                        { 
                            if (".".equals(aux)) {
                                estado="q3";
                            }
                            else
                                estado = "q5";
                            break;
                        }
                        break;
                    }
                    case "q2":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            if (lexema.length()>i+1) {
                                estado="q2";
                            }
                            else if (lexema.length()==i+1) {
                                bandera=true;
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                            }
                        }
                        catch (Exception e)
                        { 
                            if (".".equals(aux)) {
                                estado="q3";
                            }
                            else
                                estado = "q5";
                            break;
                        }
                        break;
                    }
                    case "q3":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            if (lexema.length()>i+1)
                                estado="q4";                          
                            else if (lexema.length()==i+1)
                            {
                                estado="q4";
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                                bandera=true;
                            }
                        }
                        catch (Exception e)
                        { 
                            estado = "q5";
                            break;
                        }
                        break;
                    }
                    case "q4":
                    {
                        if (lexema.length()==i+1)
                        {
                            estado="q4";
                            pseudo_codigo.add("Constante");
                            bandera=true;
                            System.out.println(lexema+" Es un numero");
                        }
                        break;
                    }
                    case "q5":
                    {
                        i=lexema.length();
                        break;
                    }
                    case "q6":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            if (lexema.length()>i+1)
                                estado="q1";   
                            else if (lexema.length()==i+1)
                            {
                                estado="q4";
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                                bandera=true;
                            }
                            
                        }
                        catch (Exception e)
                        { 
                            estado = "q5";
                            break;
                        }
                        break;
                    }
                    case "q7":
                    {
                        aux = lexema.charAt(i) + "";
                        try
                        {
                            x = Integer.parseInt(aux);
                            if (lexema.length()>i+1)
                                estado="q2"; 
                            else if (lexema.length()==i+1)
                            {
                                estado="q4";
                                pseudo_codigo.add("Constante");
                                System.out.println(lexema+" Es un numero");
                                i=lexema.length();
                                bandera=true;
                            }
                        }
                        catch (Exception e)
                        { 
                            estado = "q5";
                            break;
                        }
                        break;
                    }
                }
            }
        }  
        catch (Exception e)
        {
            
        }
        return bandera;
    }
    
    public static boolean esCadena(String lexema) 
    {
        String resultado="";
        String caracterin = "", aux="";
        String estado="q0";
        boolean bandera=false;
        try
        {
            caracterin = lexema.charAt(0) + "" +lexema.charAt(1);
        }
        catch (Exception e)
        {
            return bandera=false;
        }
        
        
        lexema=quitaSalto(lexema);

        for (int i = 0; i < lexema.length(); i++) 
        {
            switch (estado)
            {
                case "q0":
                {
                    if (caracterin.matches("' "))
                    {
                        estado="q1";
                    }
                    else
                        estado="q4";
                    break;
                }
                case "q1":
                {
                    aux = lexema.charAt(i) + "";
                    if (!"'".equals(aux) && lexema.length() > i+1) 
                        estado="q2";
                    else if ("'".equals(aux) && lexema.length() == i+1) 
                    {
                        resultado="Es una cadena de texto";
                        pseudo_codigo.add("Constante");
                        System.out.println(""+lexema+" "+resultado);
                        estado="q3";
                        bandera=true;
                    }
                    break;
                }
                case "q2":
                {
                    aux = lexema.charAt(i-1) + "" +lexema.charAt(i);
                    if (!" '".equals(aux) && lexema.length() > i+1) 
                        estado="q2";
                    else if (" '".equals(aux) && lexema.length() == i+1) 
                    {
                        resultado="Es una cadena de texto";
                        pseudo_codigo.add("Constante");
                        System.out.println(""+lexema+" "+resultado);
                        estado="q3";
                        bandera=true;
                    }
                    else
                        estado="q4";
                    break;
                }
                case "q4":
                {
                    i=lexema.length();
                }
            }
            
        }
        return bandera;
    }
    
    public static boolean esComentario(String lexema) 
    {
        String resultado="";
        String caracterin = "", aux="";
        String estado="q0";
        boolean bandera=false;
        try
        {
            caracterin = lexema.charAt(0) + "" +lexema.charAt(1);
        }
        catch (Exception e)
        {
            return bandera=false;
        }
        
        lexema=quitaSalto(lexema);

        for (int i = 0; i < lexema.length(); i++) 
        {
            switch (estado)
            {
                case "q0":
                {
                    if ("| ".equals(caracterin))
                    {
                        estado="q1";
                    }
                    else
                        estado="q4";
                    break;
                }
                case "q1":
                {
                    aux = lexema.charAt(i) + "";
                    if (!"|".equals(aux) && lexema.length() > i+1) 
                        estado="q2";
                    else if ("|".equals(aux)  && lexema.length() == i+1) 
                    {
                        resultado="Es un comentario";
                        //pseudo_codigo.add("Comentario");//no se agrega al pseudocodigo
                        System.out.println(""+lexema+" "+resultado);
                        estado="q3";
                        bandera=true;
                    }
                    break;
                }
                case "q2":
                {
                    aux = lexema.charAt(i-1) + "" +lexema.charAt(i);
                    if (!" |".equals(aux) && lexema.length() > i+1) 
                        estado="q2";
                    else if (" |".equals(aux) && lexema.length() == i+1) 
                    {
                        resultado="Es un comentario";
                        //pseudo_codigo.add("Comentario");//no se agrega al pseudocodigo
                        System.out.println(""+lexema+" "+resultado);
                        estado="q3";
                        bandera=true;
                    }
                    else
                        estado="q4";
                    break;
                }
                case "q4":
                {
                    i=lexema.length();
                }
            }
            
        }
        return bandera;
    }
    
    public static boolean esIdentifcador(String lexema) 
    {
        boolean bandera = false;
        String estado="q0";
        String caracter1 = "", caracter2= "", caracter3="", aux="";
        try
        {
            for (int i = 0; i < lexema.length(); i++) 
            {
                switch (estado)
                {
                    case "q0":
                    {
                        caracter1 = lexema.charAt(0) + "";
                        if (caracter1.matches("v"))
                            estado="q1";
                        else
                            estado="q5";
                        break;
                    }
                    case "q1":
                    {
                        caracter2 = lexema.charAt(1) + "";
                        if (caracter2.matches("a"))
                            estado="q2";
                        else
                            estado="q5";
                        break;
                    }
                    case "q2":
                    {
                        caracter3 = lexema.charAt(2) + "";
                        if (caracter3.matches("r"))
                            estado="q3";
                        else
                            estado="q5";
                        break;
                    }
                    case "q3":
                    {
                        if (lexema.length()>i+1)
                            estado="q4";                          
                        else if (lexema.length()==i+1)
                        {
                            estado="q4";
                            obtenIndex(lexema, 4);
                            //count_id++;
                            //aux="ID"+count_id;
                            pseudo_codigo.add("ID");
                            bandera=true;
                            System.out.println(""+lexema+" es un identificador");
                        } 
                        else
                            estado="q5";
                        break;
                    }
                    case "q4":
                    {
                        if (lexema.length()==i+1)
                        {
                            estado="q4";
                            obtenIndex(lexema, 4);
                            //count_id++;
                            //aux="ID"+count_id;
                            pseudo_codigo.add("ID");
                            bandera=true;
                            System.out.println(""+lexema+" es un identificador");
                        }   
                        else
                            estado="q4";
                        break;
                    }
                    case "q5":
                    {
                        i=lexema.length()-1;
                        break;
                    }
                }
            }
        }  
        catch (Exception e)
        {
            
        }
        return bandera;
    }
    
    
    public static String imprimirMap()
    {
        int tamano=0;
        String cadena="";
       
        tamano=pseudo_codigo.size();
        
        for (int i = 0; i < tamano; i++) {
            if (!"Delimitador".equals(pseudo_codigo.get(i))) 
                cadena=cadena+pseudo_codigo.get(i)+" ";
            else
                cadena=cadena+pseudo_codigo.get(i)+"\n";
        }
        System.out.println(""+cadena);
        return cadena;
    }
    
    static String quitaSalto(String cadena)
    {
	String [] cadSplit = cadena.split("\r");
	String cadReturn = "";
	for( int i = 0; i < cadSplit.length; i++ )
            cadReturn += cadSplit[i];
		
	return cadReturn;
    }
    
    public static ArrayList<String> recuperar_pseudo()
    {
        return pseudo_codigo;
    }
    
   
}
