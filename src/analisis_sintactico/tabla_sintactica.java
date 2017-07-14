
package analisis_sintactico;

import java.util.ArrayList;
import java.util.HashMap;

public class tabla_sintactica 
{
    static ArrayList <ArrayList<TDA>> tabla_sintactica=new ArrayList<>();
    static HashMap<String, Integer> terminales = new HashMap<>();
    static TDA nuevo;
    
    public static void tabla_sintactica()
    {
        
    }
 
    public static void cargar_tabla()
    {
        mapa_terminales();
        for (int i = 0; i < 18; i++) 
        {
            tabla_sintactica.add(new ArrayList<TDA>());
        }
        //PROGRAMA => 0
        nuevo=new TDA();        nuevo.terminal="inicio";        nuevo.produccion="PROGRAMA => inicio { CUERPO } fin";        tabla_sintactica.get(0).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(0).add(nuevo);
        
        //CUERPO => 1
        nuevo=new TDA();        nuevo.terminal="TIPO";        nuevo.produccion="CUERPO => DECLARACION LISTA_SENTENCIAS";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(1).add(nuevo);
        
        //DECLARACION => 2
        nuevo=new TDA();        nuevo.terminal="TIPO";        nuevo.produccion="DECLARACION => TIPO Identificador DECLARACION'";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        
        //DECLARACION' => 3
        nuevo=new TDA();        nuevo.terminal=",";        nuevo.produccion="DECLARACION' => , TIPO Identificador DECLARACION'";        tabla_sintactica.get(3).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion="vacio";        tabla_sintactica.get(3).add(nuevo);
        
        //LISTA_SENTENCIAS => 4
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="LISTA_SENTENCIAS => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="LISTA_SENTENCIAS => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="LISTA_SENTENCIAS => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="LISTA_SENTENCIAS => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(4).add(nuevo);
        
        //LISTA_SENTENCIAS' => 5
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="LISTA_SENTENCIAS' => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="LISTA_SENTENCIAS' => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="LISTA_SENTENCIAS' => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="LISTA_SENTENCIAS' => SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(5).add(nuevo);
        
        //SENTENCIAS => 6
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="SENTENCIA => Identificador = EXPRESION_SIMPLE ;";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="SENTENCIA => while ( EXPRESION ) { LISTA_SENTENCIAS }";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="SENTENCIA  => if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="SENTENCIA => switch ( Identificador ) { LISTA_CASE FIN_SWITCH";        tabla_sintactica.get(6).add(nuevo);
        
        //FIN_SWITCH => 7
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="FIN_SWITCH => DEFAULT }";        tabla_sintactica.get(7).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="FIN_SWITCH => }";        tabla_sintactica.get(7).add(nuevo);
        
        //FIN_IF => 8
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="FIN_IF => if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="else_if";        nuevo.produccion="FIN_IF => else_if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="else";        nuevo.produccion="FIN_IF => else { LISTA_SENTENCIAS } FIN_ELSE";        tabla_sintactica.get(8).add(nuevo);
        
        //FIN_ELSE => 9
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="FIN_ELSE => if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(9).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(9).add(nuevo);
        
        //LISTA_CASE => 10
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(10).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="LISTA_CASE => CASE LISTA_CASE'";        tabla_sintactica.get(10).add(nuevo);
        
        //LISTA_CASE' => 11
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(11).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="LISTA_CASE' => CASE LISTA_CASE'";        tabla_sintactica.get(11).add(nuevo);
        
        //CASE => 12
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(12).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="CASE => case Constante : { LISTA_SENTENCIAS }";        tabla_sintactica.get(12).add(nuevo);

        //DEFAULT => 13
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="DEFAULT => default : { LISTA_SENTENCIAS }";        tabla_sintactica.get(13).add(nuevo);
        
        //EXPRESION => 14
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="EXPRESION => EXPRESION_SIMPLE EXPRESION'";        tabla_sintactica.get(14).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="Constante";        nuevo.produccion="EXPRESION => EXPRESION_SIMPLE EXPRESION'";        tabla_sintactica.get(14).add(nuevo);
        
        //EXPRESION' => 15
        nuevo=new TDA();        nuevo.terminal=")";        nuevo.produccion="vacio";        tabla_sintactica.get(15).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="EXPRESION' => OPERADOR_LOGICO EXPRESION_SIMPLE";        tabla_sintactica.get(15).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="EXPRESION' => OPERADOR_BOOLEANO EXPRESION_SIMPLE";        tabla_sintactica.get(15).add(nuevo);
        
        //EXPRESION_SIMPLE => 16
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="EXPRESION_SIMPLE => TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="Constante";        nuevo.produccion="EXPRESION_SIMPLE =>TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        
        //EXPRESION_SIMPLE' => 17
        nuevo=new TDA();        nuevo.terminal="OPERADOR_ARITMETICO";        nuevo.produccion="EXPRESION_SIMPLE'=>OPERADOR_ARITMETICO TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=")";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        
    }
    
    public static void mapa_terminales()
    {
        terminales.put("PROGRAMA", 0);
        terminales.put("CUERPO", 1);
        terminales.put("DECLARACION", 2);
        terminales.put("DECLARACION'", 3);
        terminales.put("LISTA_SENTENCIAS", 4);
        terminales.put("LISTA_SENTENCIAS'", 5);
        terminales.put("SENTENCIA", 6);
        terminales.put("FIN_SWITCH", 7);
        terminales.put("FIN_IF", 8);
        terminales.put("FIN_ELSE", 9);
        terminales.put("LISTA_CASE", 10);
        terminales.put("LISTA_CASE'", 11);
        terminales.put("CASE", 12);
        terminales.put("DEFAULT", 13);
        terminales.put("EXPRESION", 14);
        terminales.put("EXPRESION'", 15);
        terminales.put("EXPRESION_SIMPLE", 16);
        terminales.put("EXPRESION_SIMPLE'", 17);
    }
    
    public static int recuperar_terminal(String terminal)
    {
        int valor=0;
        try
        {
            valor=terminales.get(terminal);
        }
        catch (Exception e)
        {
            valor=100;
        }
        return valor;
    }
}
