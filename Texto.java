import java.util.Arrays;
import java.util.Scanner;

/**
 * Un objeto de esta clase guarda en un array las diferentes
 * palabras que hay en un texto
 *
 * Cada palabra es un objeto Palabra que guarda la palabra (como String)
 * y su frecuencia de aparici�n en el texto
 *
 * El array guarda como m�ximo n palabras distintas
 *
 *
 */
public class Texto {

    private Palabra[] palabras;
    private int total;

    /**
     * Constructor
     * Crea el array al tama�o n
     * e inicializa adecuadamente el resto de atributos
     */
    public Texto(int n) {
        palabras = new Palabra[n];
        total = 0;
    }

    /**
     *
     * @return true si el texto est� completo
     */
    public boolean textoCompleto() {
        return total == palabras.length;
    }

    /**
     *
     * @return el n� de palabras distintas aparecidas en
     * el texto y guardadas en el array
     */
    public int totalPalabras() {
        int totalPalabras = total;
        for(int i = 0; i < total; i++)  {
            for(int j = 0; j < total; j++)  {
                if(palabras[i].equals(palabras[j]) && i!=j)  {
                    totalPalabras--;                
                }
            }
        }
        return totalPalabras;
    }

    /**
     * Dada una l�nea de texto conteniendo diferentes palabras
     * el m�todo extre las palabras y las inserta en el array
     *
     * Las palabras en la l�nea est�n separadas por uno o varios espacios
     * seguidos, o por el punto o por la coma
     * Puede haber espacios al comienzo y final de la l�nea
     *
     * Ej - "   a single      type.  " contiene tres palabras: a single type
     *      "y un mozo de campo y plaza  "  contiene 7 palabras
     *
     * Antes de insertar una palabra habr� que comprobar que no se
     * ha a�adido previamente. 
     * Si ya se ha a�adido solo hay que incrementar su frecuencia de
     * aparici�n 
     * Si no est� la palabra y hay sitio en el array para una nueva
     * se inserta de forma que quede ordenada (!!no se ordena, se
     * inserta en orden!!)
     *  
     * Hay que usar como m�todos de ayuda estaPalabra() e
     * insertarPalabraEnOrden()
     */
    public void addPalabras(String linea) {
        String[] aux = linea.trim().split("[.\\,\\s]+");
        for(int i = 0; i < aux.length; i++)   {
            if(estaPalabra(aux[i]) >= 0)    {
                palabras[estaPalabra(aux[i])].incrementar();
            }
            else if (!textoCompleto())  {
                insertarPalabraEnOrden(aux[i]);
            }
        }
    }

    /**
     *  dada una palabra devuelve la posici�n en la que se
     *  encuentra en el array o -1 si no est�
     *
     *  Indiferente may�sculas y min�sculas
     */
    public int estaPalabra(String palabra) {
        for(int i = 0; i < total; i++)   {
            if(palabras[i].getPalabra().equalsIgnoreCase(palabra))   {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param palabra inserta la palabra en el lugar adecuado
     *                de forma que el array palabras quede ordenado
     *                alfab�ticamente
     *  Solo hay que insertar en este m�todo, se asume que la palabra
     *                no est� y que es posible a�adirla
     *
     */
    private void insertarPalabraEnOrden(String palabra) {
        int i = total - 1;
        while(i >= 0 && palabras[i].getPalabra().compareToIgnoreCase(palabra) > 0) {
            palabras[i + 1] = palabras[i];
            i--;
        }
        palabras[i + 1] = new Palabra(palabra);
        total++;
    }

    /**
     * Representaci�n textual del array de palabras
     * Cada palabra y su frecuencia de aparici�n
     * Se muestran en l�neas de 5 en 5 palabras
     * (ver enunciado)
     *
     * De forma eficiente ya que habr� muchas concatenaciones
     *
     *
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < total; i++)   {
            if(i % 5 == 0)  {
                sb.append("\n");
            }
            sb.append(String.format("%5s", palabras[i].toString()));
        }
        return sb.toString();
    }

    /**
     *  Devuelve la palabra de la posici�n p
     *  Si p es incorrecto se devuelve null
     *      
     */
    public Palabra getPalabra(int p) {
        return palabras[p];
    }

    /**
     *
     * @return un array de cadenas con las palabras del texto
     * capitalizadas de forma alterna
     */
    public String[] capitalizarAlterna() {
        String[] capitalizarAlterna = new String[total];
        for(int i = 0; i < total; i++)   {
            capitalizarAlterna[i] = Utilidades.capitalizarAlterna(palabras[i].getPalabra());
        }
        return capitalizarAlterna;
    }

    /**
     *
     * @return un array de cadenas con las palabras que tienen letras
     * repetidas
     */
    public String[] palabrasConLetrasRepetidas() {
        String[] letrasRepetidas = new String[total];
        int tamano = 0;
        for(int i = 0; i < total; i++)   {
            if(Utilidades.tieneLetrasRepetidas(palabras[i].getPalabra()))   {
                letrasRepetidas[tamano] = palabras[i].getPalabra();
                tamano++;
            }
        }
        return Arrays.copyOf(letrasRepetidas, tamano);
    }

    /**
     *
     * @return un array con la frecuencia de palabras de cada longitud
     * La palabra m�s larga consideraremos de longitud 15
     *
     */
    public int[] calcularFrecuenciaLongitud() {
        int[] frecuenciaLongitud = new int[15];
        for(int i = 0; i < total; i++)   {
            int posNum = palabras[i].getPalabra().length();
            frecuenciaLongitud[posNum - 1]++;
        }
        return frecuenciaLongitud;
    }

    /**
     *
     * @param frecuencia se borra del array palabras aquellas de frecuencia
     *                   menor a la proporcionada
     * @return el n de palabras borradas
     */
    public int borrarDeFrecuenciaMenor(int frecuencia) {
        int borradas = 0;
        for(int i = total - 1; i >= 0; i--)   {
            if(palabras[i].getFrecuencia() < frecuencia)    {
                borradas++;
                System.arraycopy(palabras, i + 1, palabras, i, total - i - 1);
                total--;
            }
        }
        return borradas;
    }

    /**
     *  Lee de un fichero un texto formado por una
     *  serie de l�neas.
     *  Cada l�nea contiene varias palabras separadas
     *  por espacios o el . o la ,
     *
     */
    public void leerDeFichero(String nombre) {
        Scanner sc = new Scanner(
                this.getClass().getResourceAsStream(nombre));
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            this.addPalabras(linea);
        }
        sc.close();

    }
}
