package aplicacion;
import dominio.*;
import java.util.*;

public class CaminoMinimo {

    public CaminoMinimo(){}

    static Grafo grafo = new Grafo();

    public static void main(String[] args){
        grafo.agregarPlaneta("Tatooine");
        grafo.agregarPlaneta("Alderaan");
        grafo.agregarPlaneta("Coruscant");
        grafo.agregarRuta("Tatooine","Alderaan", 10);
        grafo.agregarRuta("Alderaan","Coruscant", 7);
        grafo.agregarRuta("Tatooine","Coruscant", 15);

        CaminoMinimo caminoMinimo = new CaminoMinimo();

        caminoMinimo.calcularYMostrarCaminoMinimo("Tatooine", "Coruscant");

    }

    /**
     * Algoritmo de Dijsktra
     */


    public void calcularYMostrarCaminoMinimo(String inicio, String fin){


        Map<String, Integer> distancias = new HashMap<>();
        //Hemos creado, para mostrar el camino, un mapa que nos indique el actual en funcion del anterior.
        Map<String, String> actualAnterior = new HashMap<>();
        Set<String> visitados = new TreeSet<>();

        if (grafo.planetas.contains(inicio)) {

            /**
             * Después, para cada planeta existente, inicializamos la distancia desde el inicio hasta él en infinito. Añadimos a las distancias la distancia del propio planeta a sí mismo como 0.
             */
            for (String planeta : grafo.planetas) {
                distancias.put(planeta, Integer.MAX_VALUE);
            }
            distancias.put(inicio, 0);

            /**
             * Hasta que hayamos visitados todos los planetas, inicializamos una variable que representará al planeta (nodo) con el que estemos trabajando.
             * Inicializamos también la distancia menor.
             */
            while (visitados.size()<grafo.planetas.size()) {
                String actual = null;
                int distanciaMenor = Integer.MAX_VALUE;

                /**
                 * Para cada planeta, en caso de que no haya sido visitado aún y que la distancia del origen a dicho planeta sea menor que la distancia menor inicializada anteriormente,
                 * cambiamos la distancia menor por dicha distancia, y el planeta con el que trabajamos pasa a ser el actual..
                 */
                for (String planeta : grafo.planetas) {
                    if (!visitados.contains(planeta) && distancias.get(planeta) < distanciaMenor){
                        distanciaMenor = distancias.get(planeta);
                        actual = planeta;
                    }
                }
                /**
                 * En caso de que el planeta actual sea nulo, lo cual quiere decir que no hay ningún planeta en el grafo, salimos del bucle.
                 */
                if (actual==null){
                    break;
                }
                /**
                 * Añadimos el planeta actual a los visitados y, para cada arista que haya en la lista de adyacencia, extraemos el planeta adyacente. Si dicho planeta no ha sido visitado,
                 * comprobamos si la distancia del actual más la de la arista que los une es menor que la distancia del origen al adyacente; en caso de serlo, añadimos el adyacente junto con dicha distancia.
                 */
                visitados.add(actual);


                for (Arista arista : grafo.adyacencias.get(actual)){
                    if (!visitados.contains(arista.planeta2)){
                        int distanciaActualizada = distancias.get(actual) + arista.distancia;
                        if (distanciaActualizada < distancias.get(arista.planeta2)){
                            distancias.put(arista.planeta2, distanciaActualizada);
                            actualAnterior.put(arista.planeta2, actual);

                        }
                    }

                }
            }

        }

        ArrayList <String> caminoAlReves = new ArrayList<>();
        ArrayList <String> camino = new ArrayList<>();
        String paso = fin;

        while(paso!=null) {
            caminoAlReves.add(paso);
            paso = actualAnterior.get(paso);
        }
        for (int i = caminoAlReves.size()-1; i>=0; i--){
            camino.add(caminoAlReves.get(i));
        }

        System.out.println("El camino minimo de " + inicio + " a " + fin + " es " + camino);
        System.out.println("La distancia minima de " + inicio + " a " + fin + " es de: " + distancias.get(fin));

        }
    }