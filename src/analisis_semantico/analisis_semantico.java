
package analisis_semantico;

import static analisis_lexico.Analisis_Lexico.recuperar_int;
import static analisis_lexico.Analisis_Lexico.recuperar_var;
import analisis_lexico.TDA_pseudo_codigo;
import analisis_lexico.TDA_variable;
import static analisis_sintactico.tabla_sintactica.recupera_c;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class analisis_semantico 
{
    public static ArrayList<TDA_variable> variables=new ArrayList<>();
    public static ArrayList<TDA_variable> variables_aux=new ArrayList<>();
    public static ArrayList<Integer> int_codigo_=new ArrayList<>();
    public static ArrayList<Integer> int_codigo_aux=new ArrayList<>();
    public static ArrayList <TDA_pseudo_codigo> codigo_=new ArrayList<>();
    public static ArrayList <TDA_pseudo_codigo> codigo_aux=new ArrayList<>();
    public static int borra_linea;
    
    public static ArrayList <TDA_pseudo_codigo> codigo_local=new ArrayList<>();
     
    public static void semantico()
    {
        variables=recuperar_var();
        variables_aux= (ArrayList) variables.clone();
        
        int_codigo_=recuperar_int();
        int_codigo_aux= (ArrayList) int_codigo_.clone();
        
        codigo_=recupera_c();
        codigo_aux= (ArrayList) codigo_.clone();//solo como respaldo
        
        recorre();
        elimina_linea();
        muestra_c();
        
        limpia_v();     
        
    }
    
    public static void recorre()
    {
        String cadena="";
        System.out.println("semantico");
        TDA_pseudo_codigo nuevo;
        try
        {
            for (int i = 0; i < int_codigo_.size(); i++) 
            {
                for (int j = 0; j < codigo_.size(); j++) 
                {
                    int aux=int_codigo_.get(i);
                    int aux2=codigo_.get(j).linea;
                    if (aux==aux2) 
                    {
                        boolean b=es_tipo(codigo_.get(j).token);
                        if (b) {
                            borra_linea=codigo_.get(j).linea;
                        }
                        else
                        {
                            boolean bandera=limpiar_cadena(codigo_.get(j).token);
                            if (bandera) {
                                cadena=cadena+codigo_.get(j).token+" ";
                                nuevo = new TDA_pseudo_codigo();
                                nuevo.linea=aux2;
                                nuevo.token=codigo_.get(j).token;
                                codigo_local.add(nuevo);
                            }
                        }
                    }
                    if (aux2>aux) {
                        break;
                    }
                }
                cadena=cadena+"\n";
            }
            System.out.println("Esta es la cadena con puras variables y caracteres \n"+cadena);
        }
        catch (Exception e)
        {}
    }
    
    public static boolean limpiar_cadena(String cadena)
    {
        boolean bandera = false;
        bandera=es_variable(cadena);
        if (bandera==false) {
            bandera=es_caracter(cadena);
        }
        return bandera;
    }
    
    public static boolean es_variable(String variable)
    {
        boolean bandera = false;
        try
        {
            if (bandera==false) 
            {
                Pattern pat = Pattern.compile("^var.*");
                Matcher mat = pat.matcher(variable);
                if (mat.matches()) {
                    bandera=true;
                } else {
                    bandera=false;
                }
            }
        }
        catch (Exception e)
        {}
        return bandera;
    }
    
     public static boolean es_caracter(String caracter)
    {
        boolean bandera = false;
        try
        {
            if (caracter.equals("+")||caracter.equals("-")||caracter.equals("/")||caracter.equals("*")||
                    caracter.equals("<")||caracter.equals(">")||caracter.equals("<=")||caracter.equals(">=")||
                    caracter.equals("==")||caracter.equals("!=")||caracter.equals("||")||caracter.equals("&&")||caracter.equals("=")) {
                bandera=true;
            }
            if (!bandera) {
                if (caracter.equals("C_float")||caracter.equals("C_String")||caracter.equals("C_int")||caracter.equals("C_char")) {
                    bandera=true;
                }
            }
            
        }
        catch (Exception e)
        {}
        return bandera;
    }
     
    public static boolean es_tipo(String cadena)
   {
       boolean bandera = false;
       try
       {
           if (cadena.equals("TIPO")) 
           {
               bandera=true;
           }
       }
       catch (Exception e)
       {}
       return bandera;
   }
    
    public static void elimina_linea()
    {
        for (int i = 0; i < int_codigo_.size(); i++) {
            if (borra_linea==int_codigo_.get(i)) 
            {
                System.out.println("borra linea "+borra_linea);
                int_codigo_.remove(i);
            }
        }
    }
     
    public static void muestra_c()
    {
       System.out.println("cadena limpia");
       String cadena="";
       try
       {
           for (int i = 0; i < int_codigo_.size(); i++) 
           {
               for (int j = 0; j < codigo_local.size(); j++) 
               {
                   int aux=int_codigo_.get(i);
                   int aux2=codigo_local.get(j).linea;
                   if (aux==aux2) 
                   {
                       cadena=cadena+codigo_local.get(j).token+" ";
                   }
                   if (aux2>aux) {
                       break;
                   }
               }
               cadena=cadena+"\n";
           }
           System.out.println("Esta es la cadena limpia\n"+cadena);
       }
       catch (Exception e)
       {}
    }
     
    public static void limpia_v()
    {
        codigo_.clear();
        codigo_local.clear();
        int_codigo_.clear();
        variables.clear();
    }
    
//    public stactic existe_variable()
//    {
//        
//        for (int i = 0; i < variables.size(); i++) 
//            {
//                String aux=variables.get(i).nombre;
//                if (variable.equals(aux)) {
//                    bandera=true;
//                }
//            }
//    }
    
   
}
