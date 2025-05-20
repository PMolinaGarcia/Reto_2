package dominio;

import java.util.*;

public class Grafo{

    public Set <String> planetas;
    public Set <Arista> rutas;
    public Map <String, List<Arista>> adyacencias;

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
            List<String> nombres = new ArrayList<>();
            for (int i = 0; i<lista.size(); i++){
                nombres.add(lista.get(i).planeta2);
            }
            return nombres;
        }
        return null;
    }

}