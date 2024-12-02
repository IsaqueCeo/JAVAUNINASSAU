
import java.util.ArrayList;

public class ListaDemo {

    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("Elemento 1");
        lista.add("Elemento 2");
        lista.add("Elemento 3");

        System.out.println("Lista inicial: " + lista);

        lista.remove(1);

        System.out.println("Após remover o segundo elemento: " + lista);

        System.out.println("Elemento na posição 1: " + lista.get(0));

    }

}
