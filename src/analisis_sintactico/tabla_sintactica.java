
package analisis_sintactico;

import static analisis_lexico.Analisis_Lexico.recuperar_pseudo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class tabla_sintactica 
{
    public static ArrayList <ArrayList<TDA>> tabla_sintactica=new ArrayList<>();
    public static HashMap<String, Integer> no_terminales = new HashMap<>();
    public static Stack<String> pila = new Stack<>(); 
    public static Stack<String> pila_aux = new Stack<>(); 
    public static ArrayList <String> pseudo_c=new ArrayList<>();
    public static Object pseudo_c_aux=new ArrayList<>();
    public static TDA nuevo;
    
    public static void tabla_sintactica()
    {
        pseudo_c=recuperar_pseudo();
        pseudo_c_aux=pseudo_c.clone();//solo como respaldo
        verificador();
    }
    
    public static void verificador()
    {
        boolean verifica = true;
        boolean encontro = false;
        String inicio = "PROGRAMA";
        String aux_pseudo = pseudo_c.get(0);
        int pos;
        pos=recuperar_terminal(inicio);
        for (int i = 0; i < tabla_sintactica.get(pos).size(); i++) 
        {
            if (tabla_sintactica.get(pos).get(i).terminal.equals(aux_pseudo)) 
            {
                System.out.println("AQUI ESTA LA PRODUCCION"+tabla_sintactica.get(pos).get(i).produccion);
                meter_pila(tabla_sintactica.get(pos).get(i).produccion);
                //la produccion que salga se agregara a la pila
            }
        }
        
        //englobar en try catch
        ////SI ES UN TERMINAL
        while(verifica && !pila.empty() && !encontro)
        {
            if (pila.lastElement().equals("inicio")||pila.lastElement().equals("TIPO")||pila.lastElement().equals("ID")
            ||pila.lastElement().equals("while")||pila.lastElement().equals("if")||pila.lastElement().equals("switch")
            ||pila.lastElement().equals("default")||pila.lastElement().equals("else_if")||pila.lastElement().equals("else")
            ||pila.lastElement().equals("case")||pila.lastElement().equals("Constante")||pila.lastElement().equals("OPERADOR_ARITMATICO")
            ||pila.lastElement().equals("}")||pila.lastElement().equals(")")||pila.lastElement().equals(";")||pila.lastElement().equals(",")
            ||pila.lastElement().equals("OPERADOR_LOGICO")||pila.lastElement().equals("OPERADOR_BOOLEANO")||pila.lastElement().equals("{")
            ||pila.lastElement().equals("fin")||pila.lastElement().equals("(")||pila.lastElement().equals("TERMINO")
            ||pila.lastElement().equals("=")||pila.lastElement().equals(":")) 
            {
                if (pila.lastElement().equals("TERMINO")) 
                {
                    if (pseudo_c.get(0).equals("ID") || pseudo_c.get(0).equals("Constante")) {
                        pila.pop();
                        pseudo_c.remove(0);
                    }
                }
                else if (pila.lastElement().equals(pseudo_c.get(0))) 
                {
                    pila.pop();
                    pseudo_c.remove(0);
                }
                else
                    verifica=false;

            }
            else if (pila.lastElement().matches("ERROR_LEXICO")) {
                verifica=false;
            }
            else
            {
                aux_pseudo = pseudo_c.get(0);
                System.out.println("Codigo "+aux_pseudo);
                System.out.println("PILA "+pila.lastElement());
                pos=recuperar_terminal(pila.lastElement());
                //if pos igual a 100 marcar error
                for (int i = 0; i < tabla_sintactica.get(pos).size(); i++) 
                {
                    if (i==tabla_sintactica.get(pos).size()-1 && !tabla_sintactica.get(pos).get(i).terminal.equals(aux_pseudo) && !encontro) 
                    {
                        verifica=false;
                        encontro=true;
                        break;
                    }
                    else if (tabla_sintactica.get(pos).get(i).terminal.equals(aux_pseudo)) 
                    {
                        System.out.println("AQUI ESTA LA PRODUCCION"+tabla_sintactica.get(pos).get(i).produccion);
                        if (tabla_sintactica.get(pos).get(i).produccion.equals("vacio")) 
                        {
                            pila.pop();
                            break;
                            //encontro=true;
                        }
                        else if (tabla_sintactica.get(pos).get(i).produccion.equals("error")) 
                        {//se encontro un error
                            verifica=false;
                            break;
                        }
                        else
                        { 
                            pila.pop();
                            meter_pila(tabla_sintactica.get(pos).get(i).produccion);
                            break;
                            //encontro=true;
                        }
                       
                        //la produccion que salga se agregara a la pila
                    }
                }
            }
            System.out.println("este es el contenido de la pila "+pila.toString());
            System.out.println("este es el contenido del codigo  "+pseudo_c.toString());
        }
        if (pila.empty())
            System.out.println("TERMINO");
        else if (encontro || !verifica)
            System.out.println("ERROR SINTACTICO");
        
        
    }
    
    
    public static void cargar_tabla()
    {
        mapa_no_terminales();
        for (int i = 0; i < 18; i++) 
        {
            tabla_sintactica.add(new ArrayList<TDA>());
        }
        //PROGRAMA => 0
        nuevo=new TDA();        nuevo.terminal="inicio";        nuevo.produccion="inicio { CUERPO } fin";        tabla_sintactica.get(0).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(0).add(nuevo);
        
        //CUERPO => 1
        nuevo=new TDA();        nuevo.terminal="TIPO";        nuevo.produccion="DECLARACION LISTA_SENTENCIAS";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(1).add(nuevo);
        
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(1).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(1).add(nuevo);
        
        
        //DECLARACION => 2
        nuevo=new TDA();        nuevo.terminal="TIPO";        nuevo.produccion="TIPO ID DECLARACION'";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(2).add(nuevo);
        
        //DECLARACION' => 3
        nuevo=new TDA();        nuevo.terminal=",";        nuevo.produccion=", TIPO ID DECLARACION'";        tabla_sintactica.get(3).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion=";";        tabla_sintactica.get(3).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="inicio";        nuevo.produccion="inicio { CUERPO } fin";        tabla_sintactica.get(3).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(3).add(nuevo);
    
        //LISTA_SENTENCIAS => 4
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(4).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(4).add(nuevo);
        
        //LISTA_SENTENCIAS' => 5
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="SENTENCIA LISTA_SENTENCIAS'";        tabla_sintactica.get(5).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(5).add(nuevo);
        
        //SENTENCIAS => 6
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="ID = EXPRESION_SIMPLE ;";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="while ( EXPRESION ) { LISTA_SENTENCIAS }";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(6).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="switch ( ID ) { LISTA_CASE FIN_SWITCH";        tabla_sintactica.get(6).add(nuevo);
        
        //FIN_SWITCH => 7
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="DEFAULT }";        tabla_sintactica.get(7).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="}";        tabla_sintactica.get(7).add(nuevo);
        
        //FIN_IF => 8
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="else_if";        nuevo.produccion="else_if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="else";        nuevo.produccion="else { LISTA_SENTENCIAS } FIN_ELSE";        tabla_sintactica.get(8).add(nuevo);
        
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="vacio";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="while";        nuevo.produccion="vacio";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="switch";        nuevo.produccion="vacio";        tabla_sintactica.get(8).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(8).add(nuevo);
        
        
        //FIN_ELSE => 9
        nuevo=new TDA();        nuevo.terminal="if";        nuevo.produccion="if ( EXPRESION ) { LISTA_SENTENCIAS } FIN_IF";        tabla_sintactica.get(9).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="}";        nuevo.produccion="vacio";        tabla_sintactica.get(9).add(nuevo);
        
        //LISTA_CASE => 10
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(10).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="CASE LISTA_CASE'";        tabla_sintactica.get(10).add(nuevo);
        
        //LISTA_CASE' => 11
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(11).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="CASE LISTA_CASE'";        tabla_sintactica.get(11).add(nuevo);
        
        //CASE => 12
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="vacio";        tabla_sintactica.get(12).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="case";        nuevo.produccion="case Constante : { LISTA_SENTENCIAS }";        tabla_sintactica.get(12).add(nuevo);

        //DEFAULT => 13
        nuevo=new TDA();        nuevo.terminal="default";        nuevo.produccion="default : { LISTA_SENTENCIAS }";        tabla_sintactica.get(13).add(nuevo);
        
        //EXPRESION => 14
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="EXPRESION_SIMPLE EXPRESION'";        tabla_sintactica.get(14).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="Constante";        nuevo.produccion="EXPRESION_SIMPLE EXPRESION'";        tabla_sintactica.get(14).add(nuevo);
        
        //EXPRESION' => 15
        nuevo=new TDA();        nuevo.terminal=")";        nuevo.produccion="vacio";        tabla_sintactica.get(15).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="OPERADOR_LOGICO EXPRESION_SIMPLE";        tabla_sintactica.get(15).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="OPERADOR_BOOLEANO EXPRESION_SIMPLE";        tabla_sintactica.get(15).add(nuevo);
        
        //EXPRESION_SIMPLE => 16
        nuevo=new TDA();        nuevo.terminal="ID";        nuevo.produccion="TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="Constante";        nuevo.produccion="TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="vacio";        tabla_sintactica.get(16).add(nuevo);
        
        //EXPRESION_SIMPLE' => 17
        nuevo=new TDA();        nuevo.terminal="OPERADOR_ARITMETICO";        nuevo.produccion="OPERADOR_ARITMETICO TERMINO EXPRESION_SIMPLE'";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=")";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal=";";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_LOGICO";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        nuevo=new TDA();        nuevo.terminal="OPERADOR_BOOLEANO";        nuevo.produccion="vacio";        tabla_sintactica.get(17).add(nuevo);
        
    }
    
    public static void mapa_no_terminales()
    {
        no_terminales.put("PROGRAMA", 0);
        no_terminales.put("CUERPO", 1);
        no_terminales.put("DECLARACION", 2);
        no_terminales.put("DECLARACION'", 3);
        no_terminales.put("LISTA_SENTENCIAS", 4);
        no_terminales.put("LISTA_SENTENCIAS'", 5);
        no_terminales.put("SENTENCIA", 6);
        no_terminales.put("FIN_SWITCH", 7);
        no_terminales.put("FIN_IF", 8);
        no_terminales.put("FIN_ELSE", 9);
        no_terminales.put("LISTA_CASE", 10);
        no_terminales.put("LISTA_CASE'", 11);
        no_terminales.put("CASE", 12);
        no_terminales.put("DEFAULT", 13);
        no_terminales.put("EXPRESION", 14);
        no_terminales.put("EXPRESION'", 15);
        no_terminales.put("EXPRESION_SIMPLE", 16);
        no_terminales.put("EXPRESION_SIMPLE'", 17);
    }
    
    public static int recuperar_terminal(String terminal)
    {
        int valor=0;
        try
        {
            valor=no_terminales.get(terminal);
        }
        catch (Exception e)
        {
            valor=100;
        }
        return valor;
    }
    
    //mete a la pila la produccion
    public static void meter_pila(String produccion)
    {
        String token = "";
        int t;
        StringTokenizer st = new StringTokenizer(produccion, " ");
        while(st.hasMoreElements())
        {
            token = st.nextToken();
            pila_aux.push(token);
        }
        t=pila_aux.size();
        for (int i = 0; i < t; i++) {
            pila.add(pila_aux.pop());
        }
        //System.out.println("este es el contenido de la pila "+pila.toString());
    }
    
    
}
