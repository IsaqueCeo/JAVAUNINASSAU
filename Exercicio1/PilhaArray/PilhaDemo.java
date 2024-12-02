
import java.util.Stack;

public class PilhaDemo {

    public static void main(String[] args) {

        Stack<String> pilha = new Stack<>();

        pilha.push("Elemento 1");

        pilha.push("Elemento 2");

        pilha.push("Elemento 3");

        System.out.println("Pilha inicial: " + pilha);

        String topo = pilha.pop();

        System.out.println("Elemento removido: " + topo);

        System.out.println("Pilha após remoção: " + pilha);

        System.out.println("Elemento no topo: " + pilha.peek());

    }

}
