public class Main {
    public static void main(String[] args) {

        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        MenuController menu = new MenuController(gerenciador);

        menu.iniciar();

    }
}