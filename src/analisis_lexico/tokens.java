
package analisis_lexico;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class tokens 
{
    public static ArrayList<String> obtener_tokens(String cadena)
    {
        ArrayList<String> palabras = new ArrayList<>();
        String token = "";
        String concatena="";
        int count=0;
        boolean band_add_array = true;//añade la palabra al array list
        boolean band_conca_cadena = false;//permite concatenar la cadena de texto
        boolean band_conca_coment = false;//permite concatenar el comentario
        boolean band_inicio_comen = false;
        boolean band_inicio_cadena = false;
        char caracter_cadena='\'';   
        char caracter_comen='|';
        int count_comen =0;
        int count_cadena =0;
        StringTokenizer st = new StringTokenizer(cadena, " ");
        int cantidad_tokens=st.countTokens();
        while(st.hasMoreElements())
        {
            count_comen++;
            count_cadena++;
            token = st.nextToken();
             
            //(1) si tiene espacios la cadena de texto al principio y al final
            if("'".equals(token) && band_inicio_cadena==false)
            {
                band_inicio_cadena=true;
                count++;
            }
            if (band_inicio_cadena && "'".equals(token) && count > 1) {
                concatena=concatena+token;
                palabras.add(concatena);
                band_inicio_cadena=false;
                count=0;
                concatena="";
                continue;
            }
            else if (band_inicio_cadena && cantidad_tokens == count_cadena) {
                concatena=concatena+token;
                palabras.add(concatena);
                band_inicio_cadena=false;
                count=0;
                concatena="";
                continue;
            }
            else if (band_inicio_cadena) 
            {
                concatena=concatena+token+" ";
                count++;
                continue;
            }
            //fin (1)
            
            //(2) si tiene espacios el comentario al principio y al final
            if("|".equals(token) && band_inicio_comen==false)
            {
                band_inicio_comen=true;
                count++;
            }
            if (band_inicio_comen && "|".equals(token) && count > 1) {
                concatena=concatena+token;
                palabras.add(concatena);
                band_inicio_comen=false;
                count=0;
                concatena="";
                continue;
            }
            else if (band_inicio_comen && cantidad_tokens == count_comen) {
                concatena=concatena+token;
                palabras.add(concatena);
                band_inicio_cadena=false;
                count=0;
                concatena="";
                continue;
            }
            else if (band_inicio_comen) 
            {
                concatena=concatena+token+" ";
                count++;
                continue;
            }
            //fin (2)
            else 
            {//no permite que se agregen cadenas a la ista mientras se cierra la cadena
                palabras.add(token);
            }
            
            /*
            if(token.length()>1 && token.charAt(0)==caracter_cadena)
            {
                band_add_array = false; 
                band_conca_cadena = true;       
            }
            else if(token.length()>1 && (token.charAt(0))==caracter_comen)
            {
                band_add_array = false;
                band_conca_coment = true;                
            }
            else if (band_add_array) 
            {//no permite que se agregen cadenas a la ista mientras se cierra la cadena
                palabras.add(token);
            }    
            if (band_conca_coment && token.charAt(token.length()-1)!=caracter_comen) 
            {//se concatena hasta que se cierre el comentario
                concatena=concatena+token+" ";
            }        
            else if (band_conca_cadena && token.charAt(token.length()-1)!=caracter_cadena) 
            {//se concatena hasta que se cierre la cadena     
                concatena=concatena+token+" ";
            }
            if(token.length()>1 && token.charAt(token.length()-1)==caracter_cadena)
            {//cierre de la cadena y add al array
                concatena=concatena+token;
                band_conca_cadena = false;
                band_add_array = true;
                palabras.add(concatena);
                concatena="";
            }
            else if(token.length()>1 && token.charAt(token.length()-1)==caracter_comen)
            {//cierre del comentario y add al array
                concatena=concatena+token;
                band_conca_coment = false;
                band_add_array = true;
                palabras.add(concatena);
                concatena="";
            }
            
            */
        
        }
        System.out.println("Hola que hace"+palabras.toString());
        return palabras;
    }
}
