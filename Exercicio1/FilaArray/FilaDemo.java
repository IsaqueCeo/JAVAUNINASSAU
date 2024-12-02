
import java.util.LinkedList;
import java.util.Queue;

public class FilaDemo {

    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();
        fila.add("Elemento 1");
        fila.add("Elemento 2");
        fila.add("Elemento 3");
        System.out.println("Fila inicial: " + fila);
        String removido = fila.poll();
        System.out.println("Elemento removido: " + removido);

        System.out.println("Fila após remoção: " + fila);

        System.out.println("Elemento na frente: " + fila.peek());

    }

}
