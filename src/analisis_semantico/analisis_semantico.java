
package analisis_semantico;

import static analisis_lexico.Analisis_Lexico.recuperar_int;
import static analisis_lexico.Analisis_Lexico.recuperar_var;
import analisis_lexico.TDA_pseudo_codigo;
import analisis_lexico.TDA_variable;
import static analisis_sintactico.tabla_sintactica.recupera_c;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
    public static String txt_semantico="";
     
    public static void semantico()
    {
        txt_semantico="";
        variables=recuperar_var();
        variables_aux= (ArrayList) variables.clone();
        
        int_codigo_=recuperar_int();
        int_codigo_aux= (ArrayList) int_codigo_.clone();
        
        codigo_=recupera_c();
        codigo_aux= (ArrayList) codigo_.clone();//solo como respaldo
        
        recorre();//toma las lineas en donde se realizara la verificacion semantica
        elimina_linea();//descarta la linea de declaraciones
        variables_duplicadas();//verifica si existen variables declaradas duplicadas
        muestra_c();//se realiza la inferencia
        interfaz.Panel_semantico.txtSemantico.setText(txt_semantico);
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
            if (caracter.equals("+")||caracter.equals("-")||caracter.equals("/")||caracter.equals("*")||caracter.equals("=")||
                    caracter.equals("OPERADOR_BOOLEANO")||caracter.equals("OPERADOR_LOGICO")) {
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
       String evalua="";
       String resultado_semantico="";
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
                       evalua=evalua+codigo_local.get(j).token+" ";
                   }
                   if (aux2>aux) {
                       break;
                   }
               }
               cadena=cadena+"\n";
                //aqui se evaluara la cadena --> solo mandar una linea
               boolean ban=existe_igual(evalua);
               String resultado="";
               if (ban) {
                   resultado=verifica_exp_simple(evalua);
               }
               else
               {
                   resultado=separar_cadena(evalua);
               }
               if (resultado.equals("error")) 
                   resultado_semantico=resultado_semantico+"Error en la linea "+int_codigo_.get(i)+"\n";
               else if (resultado.equals("error_en_tipo_de_dato")) 
                   resultado_semantico=resultado_semantico+"Error en la linea "+int_codigo_.get(i)+" los tipos de dato no coinciden\n";
               else if (resultado.equals("error_no_definida")) 
                   resultado_semantico=resultado_semantico+"Error en la linea "+int_codigo_.get(i)+" variable no definida\n";
               else
               {
                   resultado_semantico=resultado_semantico+"La evaluacion semantica en la linea "+int_codigo_.get(i)+" fue correcta\n";
               }
               evalua="";
           }
           System.out.println("Esta es la cadena limpia\n"+cadena);
           System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"+resultado_semantico);
           txt_semantico=txt_semantico+resultado_semantico;
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
    
    public static String separar_cadena(String cadena)
    {
        cadena=analisis_lexico.Analisis_Lexico.quitaSalto(cadena);
        String resultado_interno="";
        String actual_interno="";
        
        String resultado_externo="";
        String actual_externo="";
        String exp_boo="OPERADOR_BOOLEANO";
        String exp_log="OPERADOR_LOGICO";
        String cadena_boo="";
        String cadena_log="";
        StringTokenizer st_boo = new StringTokenizer(cadena, " ");
        int no_st_boo=st_boo.countTokens();
        int cont_boo=0;
        int cont_log=0;
        
        if (cadena.equals("") || no_st_boo<=1) {
            resultado_externo="error";
        }
        else
        {
            while(st_boo.hasMoreElements()&&!resultado_externo.equals("error"))
            {
                cont_boo++;
                String aux_next=st_boo.nextToken();
                if (!aux_next.equals(exp_boo)&&cont_boo<no_st_boo) {
                    cadena_boo=cadena_boo+aux_next+" ";
                }
                else
                {
                    if (cont_boo==no_st_boo) {
                        cadena_boo=cadena_boo+aux_next;
                    }
                    StringTokenizer st_log = new StringTokenizer(cadena_boo, " ");
                    String aux2=cadena_boo;
                    int no_st_log=st_log.countTokens();
                    while(st_log.hasMoreElements()&&!resultado_interno.equals("error"))
                    {
                        cont_log++;
                        String aux=st_log.nextToken();
                        boolean ba=existe_log(aux2);
                        if (ba) 
                        {
                            if (!aux.equals(exp_log)&&cont_log<no_st_log) 
                            {
                                cadena_log=cadena_log+aux+" ";
                            }
                            else
                            {
                                if (cont_log==no_st_log) {
                                    cadena_log=cadena_log+aux;
                                }
                                resultado_interno=verifica_operacion(cadena_log);
                                if (actual_interno.equals("")) {
                                    actual_interno=resultado_interno;
                                }
                                else if (!resultado_interno.equals(actual_interno)) {
                                    resultado_interno="error";
                                }
                                cadena_boo="";
                                cadena_log="";
                            }
                        }
                        else
                            resultado_interno="error";
                        
                    }
                    cont_log=0;
                    if (resultado_interno.equals("error")) {
                        resultado_externo=resultado_interno;
                    }
                }


            }
        }
        return resultado_externo;
        
    }
    
    public static String verifica_operacion(String cadena)
    {
        String regresa="";
        String[] arreglo=cadena.split(" ");
        int count_cadena=0, count_numerico=0;
        String var="";
        for (int i = 0; i < arreglo.length; i=i+2) 
        {
            var="";
            if (es_variable(arreglo[i])) {
                var=valor_var_tabla_id(arreglo[i]);
            }
            if (var.equals("error")) {
                regresa="error";
                break;
            }
            if ((i)==arreglo.length-1) 
            {
                if ( arreglo[i].equals("C_int")||var.equals("int")||arreglo[i].equals("C_float")
                    ||var.equals("float") )
                {
                  count_numerico=count_numerico+1;
                }
                else if (arreglo[i].equals("C_String")||var.equals("String") || arreglo[i].equals("C_char")||var.equals("char"))
                {
                  count_cadena=count_cadena+1;
                }
            }
            else
            {
                if ((arreglo[i].equals("C_int")||var.equals("int")||arreglo[i].equals("C_float")
                    ||var.equals("float"))&&
                    ((arreglo[i+1].equals("+"))||(arreglo[i+1].equals("-"))||(arreglo[i+1].equals("/"))||(arreglo[i+1].equals("*"))) )
                {
                  count_numerico=count_numerico+2;
                }
                else if ((arreglo[i].equals("C_String")||var.equals("String") || arreglo[i].equals("C_char")||var.equals("char")) 
                      && ((arreglo[i+1].equals("+"))))
                {
                  count_cadena=count_cadena+2;
                }
            }
            
        }
        if (count_numerico==arreglo.length) {
            regresa="numero";
        }
        else if (count_cadena==arreglo.length) {
            regresa="cadena";
        }
        else
            regresa="error";
        
        return regresa;
    }
    
    public static String verifica_exp_simple(String cadena)
    {
        String regresa="";
        String[] arreglo=cadena.split("=");
        int count_String=0, count_char=0, count_int=0, count_float=0;
        String var="";
        String var_switch="";
        if (es_variable(arreglo[0])) {
            var=valor_var_tabla_id(arreglo[0]);
        }
        if (var.equals("error")) {
            regresa="error_no_definida";
        }
        else
        {
            String[] arreglo_=arreglo[1].split(" ");
            for (int i = 1; i < arreglo_.length; i=i+2) 
            {
                switch (var)
                {
                    case "String":
                    {
                        var_switch="";
                        if (es_variable(arreglo_[i])) {
                            var_switch=valor_var_tabla_id(arreglo_[i]);
                        }
                        if (var_switch.equals("error")) {
                            regresa="error";
                        }
                        else
                        {
                            if ((i)==arreglo_.length-1) 
                            {
                                if (arreglo_[i].equals("C_String")||var_switch.equals("String") || arreglo_[i].equals("C_char")||var_switch.equals("char"))
                                {
                                    count_String=count_String+1;
                                }
                            }
                            else
                            {
                                if ((arreglo_[i].equals("C_String")||var_switch.equals("String") || arreglo_[i].equals("C_char")||var_switch.equals("char")) 
                                      && ((arreglo_[i+1].equals("+"))))
                                {
                                    count_String=count_String+2;
                                }
                            }
                        }
                        break;
                    }
                    case "float":
                    {
                        var_switch="";
                        if (es_variable(arreglo_[i])) {
                            var_switch=valor_var_tabla_id(arreglo_[i]);
                        }
                        if (var_switch.equals("error")) {
                            regresa="error";
                        }
                        else
                        {
                            if ((i)==arreglo_.length-1) 
                            {
                                if (arreglo_[i].equals("C_int")||var_switch.equals("int") || arreglo_[i].equals("C_float")||var_switch.equals("float"))
                                {
                                    count_float=count_float+1;
                                }
                            }
                            else
                            {
                                if ((arreglo_[i].equals("C_int")||var_switch.equals("int") || arreglo_[i].equals("C_float")||var_switch.equals("float")) 
                                      && 
                                   ((arreglo_[i+1].equals("+"))||(arreglo_[i+1].equals("-"))||(arreglo_[i+1].equals("/"))||(arreglo_[i+1].equals("*"))) )
                                {
                                    count_float=count_float+2;
                                }
                            }
                        }
                        break;
                    }
                    case "int":
                    {
                        var_switch="";
                        if (es_variable(arreglo_[i])) {
                            var_switch=valor_var_tabla_id(arreglo_[i]);
                        }
                        if (var_switch.equals("error")) {
                            regresa="error";
                        }
                        else
                        {
                            if ((i)==arreglo_.length-1) 
                            {
                                if (arreglo_[i].equals("C_int")||var_switch.equals("int"))
                                {
                                    count_int=count_int+1;
                                }
                            }
                            else
                            {
                                if ((arreglo_[i].equals("C_int")||var_switch.equals("int")) 
                                      && 
                                   ((arreglo_[i+1].equals("+"))||(arreglo_[i+1].equals("-"))||(arreglo_[i+1].equals("/"))||(arreglo_[i+1].equals("*"))) )
                                {
                                    count_int=count_int+2;
                                }
                            }
                        }
                        break;
                    }
                    case "char":
                    {
                        var_switch="";
                        if (es_variable(arreglo_[i])) {
                            var_switch=valor_var_tabla_id(arreglo_[i]);
                        }
                        if (var_switch.equals("error")) {
                            regresa="error";
                        }
                        else
                        {
                            if ((i)==arreglo_.length-1) 
                            {
                                if ((arreglo_[i].equals("C_char")||var_switch.equals("char")))
                                {
                                    count_char=count_char+1;
                                }
                            }
                            else
                            {
                                if ((arreglo_[i].equals("C_char")||var_switch.equals("char")) 
                                      && 
                                   ((arreglo_[i+1].equals("+"))||(arreglo_[i+1].equals("-"))||(arreglo_[i+1].equals("/"))||(arreglo_[i+1].equals("*"))) )
                                {
                                    count_char=count_char+2;
                                }
                            }
                        }
                        break;
                    }
                }
                    
            } 
            if (count_String==arreglo_.length-1) {
                regresa="String";
            }
            else if (count_char==arreglo_.length-1) {
                regresa="char";
            }
            else if (count_float==arreglo_.length-1) {
                regresa="float";
            }
            else if (count_int==arreglo_.length-1) {
                regresa="int";
            }
            else 
                regresa="error_en_tipo_de_dato";
        }
        
        return regresa;
    }
    
    public static String valor_var_tabla_id(String id)
    {
        String valor_var="error";
        int t = variables.size();
        for (int i = 0; i < t; i++) 
        {
            if (id.equals(variables.get(i).nombre) || id.equals(variables.get(i).nombre+" ")) 
            {
                valor_var=variables.get(i).tipo;
                break;
            }
        }
        return valor_var;
    }
    
    //este no se ocupa
    public static boolean existe_igual(String cadena)
    {
        boolean bandera;
        Pattern pat = Pattern.compile(".*=.*");
        Matcher mat = pat.matcher(cadena);
        bandera = mat.matches();
        return bandera;
    }
    
    public static boolean existe_log(String cadena)
    {
        boolean bandera;
        Pattern pat = Pattern.compile(".*OPERADOR_LOGICO.*");
        Matcher mat = pat.matcher(cadena);
        bandera = mat.matches();
        return bandera;
    }
    
    public static void variables_duplicadas()
    {
        int t = variables.size();
        boolean bandera=false;
        String cadena="Existen variables duplicadas (/°o°)/ ";
        for (int i = 0; i < t; i++) {
            if (variables.get(i).duplicada>0) {
                bandera=true;
                cadena=cadena+" "+variables.get(i).nombre+",";
            }
        }
        if (bandera) {
            txt_semantico=txt_semantico+cadena+"\n";
        }
    }
    
}
