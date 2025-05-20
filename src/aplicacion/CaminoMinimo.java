package aplicacion;
import dominio.*;

public class CaminoMinimo {

    public CaminoMinimo(){}

    public static void main(String[] args){

        Grafo grafo = new Grafo();
        grafo.agregarPlaneta("Tatooine");
        grafo.agregarPlaneta("Alderaan");
        grafo.agregarPlaneta("Coruscant");
        grafo.agregarRuta("Tatooine","Alderaan", 10);
        grafo.agregarRuta("Alderaan","Coruscant", 5);
        grafo.agregarRuta("Tatooine","Coruscant", 15);

        CaminoMinimo caminoMinimo = new CaminoMinimo();

        caminoMinimo.calcularYMostrarCaminoMinimo("Tatooine", "Coruscant");

    }

    public void calcularYMostrarCaminoMinimo(String inicio, String fin){
        
    }
}