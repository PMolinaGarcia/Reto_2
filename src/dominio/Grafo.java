package dominio;

import java.util.*;

public class Grafo{

    public Set <String> planetas;
    //Un conjunto de aristas sobra, teniendo en el mapa almacenada la relación vértice-arista
    public Set <Arista> rutas;
    public Map <String, List<Arista>> adyacencias;

    public Grafo(){
        //Un HashSet es complicado de recorrer; mejor lo cambiamos por otro tipo de Set que recorra más
        //fácilmente.
        planetas = new TreeSet<>();
        rutas = new HashSet<>();
        adyacencias = new HashMap<>();
    }

    public void agregarPlaneta(String planeta){
        planetas.add(planeta);
        adyacencias.put(planeta, new ArrayList<>());
    }

    public void agregarRuta(String planeta1, String planeta2, int distancia){
        Arista arista = new Arista(planeta1, planeta2, distancia);
        rutas.add(arista);
        if (adyacencias.containsKey(planeta1)){
            adyacencias.get(planeta1).add(arista);
        }
    }

    public List<String> obtenerAdyacencias(String planeta){
        if (planetas.contains(planeta)){
            List<Arista> lista = adyacencias.get(planeta);
            List<String> adyacentes = new ArrayList<>();
            for (Arista arista : lista) {
                adyacentes.add(arista.planeta2);
            }
            return adyacentes;
        }
        return null;
    }

    public int obtenerCoste(String planetaOrigen, String planetaDestino){
        for (int i = 0; i<adyacencias.get(planetaOrigen).size(); i++){
            if (adyacencias.get(planetaOrigen).get(i).planeta2.compareTo(planetaDestino)==0){
                return adyacencias.get(planetaOrigen).get(i).distancia;
            }
        }
        return Integer.MAX_VALUE;
    }

}