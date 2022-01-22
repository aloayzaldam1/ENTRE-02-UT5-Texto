/**
 * clase Utilidades 
 * incluye  m�todos est�ticos para trabajar con String
 *
 */
public class Utilidades {

    /**
     * Dada una cadena devuelve una nueva capitalizando los caracteres de
     * tres en tres de forma
     * alterna empezando por may�scula (may�scula, min�scula, may�scula,
     * min�scula, ....) 
     * Ej. si la cadena recibida es "texto" devuelve "TEXto"
     * si la cadena recibida es "zapato" devuelve "ZAPato"
     * si la cadena recibida es "de" devuelve "DE"
     */

    public static String capitalizarAlterna(String cadena) {
        String capitalizar = "";
        int tama�o = cadena.length();
        int posicion = 0;
        for(int i = 0; i < tama�o; i++)  {
            for(int j = 0; j <= 2 && posicion < cadena.length();j++)   {
                if(i % 2 == 0)   {
                    capitalizar += cadena.valueOf(cadena.charAt(posicion)).toUpperCase();
                    posicion++;
                    tama�o--;
                }
                else    {
                    capitalizar += cadena.valueOf(cadena.charAt(posicion)).toLowerCase();
                    posicion++;
                }
            }
        }
        return capitalizar;
    }

    /**
     * Dada una cadena devuelve true si hay letras repetidas, false en otro caso
     * Es indiferente may�sculas o min�sculas
     *
     * Pista!! puedes hacer de forma sencilla este m�todo con ayuda de
     * indexOf y substring
     *
     */
    public static boolean tieneLetrasRepetidas(String cadena) {

        return false;
    }

    /**
     *
     */
    public static void main(String[] args) {
        String cadena = "zaPaTo";
        System.out.println(cadena + "\tCapitalizada alterna: " +
            Utilidades.capitalizarAlterna(cadena));
        cadena = "pez";
        System.out.println(cadena + "\tCapitalizada alterna: " +
            Utilidades.capitalizarAlterna(cadena));

        cadena = "vaso";
        System.out.println(cadena + "\tCapitalizada alterna: " +
            Utilidades.capitalizarAlterna(cadena));

        cadena = "semana";
        System.out.println(cadena + "\tTiene letras repetidas?: " +
            Utilidades.tieneLetrasRepetidas(cadena));

        cadena = "quebrantos";
        System.out.println(cadena + "\tTiene letras repetidas?: " +
            Utilidades.tieneLetrasRepetidas(cadena));

        cadena = "y";
        System.out.println(cadena + "\tTiene letras repetidas?: " +
            Utilidades.tieneLetrasRepetidas(cadena));

        cadena = "de";
        System.out.println(cadena + "\tTiene letras repetidas?: " +
            Utilidades.tieneLetrasRepetidas(cadena));

    }
}
