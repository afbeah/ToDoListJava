import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    private GerenciadorTarefas gerenciador;
    private Scanner scanner = new Scanner(System.in);

    public MenuController(GerenciadorTarefas gerenciador){
        this.gerenciador = gerenciador;
    }

    public void iniciar(){

        boolean executando = true;

        System.out.println("ToDo List");


        while (executando){

            System.out.println("====== Menu ======");
            System.out.println("1- Inserir Tarefas");
            System.out.println("2- Listar Todas as Tarefas");
            System.out.println("3- Listar Tarefas Pendentes");
            System.out.println("4- Buscar Tarefa por Título");
            System.out.println("5- Marcar Tarefa Concluída");
            System.out.println("6- Remover Tarefa");
            System.out.println("0- Sair da Lista");
            System.out.println("==================");

            System.out.println("Digite uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            System.out.println("A opção escolhida foi: " + opcao);

            executando = switch (opcao){
                case 1 -> handleInserirTarefas();
                case 2 -> handleListarTarefas();
                case 3 -> handleListarTarefasPendentes();
                case 4 -> handleBuscarTarefa();
                case 5 -> handleMarcarConcluida();
                case 6 -> handleRemoverTarefa();
                case 0 -> false;
                default -> {
                    System.out.println("Opção Inválida!");
                    yield true;
                }
            };
        }

        System.out.println("Fechando ToDo List.");

    }

    private boolean handleInserirTarefas(){
        System.out.println("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.println("Digite a descrição: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite a data de entrega (YYYY-MM-DD): ");
        LocalDate dataEntrega = LocalDate.parse(scanner.nextLine());

        Tarefas novaTarefa = new Tarefas(titulo, descricao, dataEntrega);

        gerenciador.adicionarTarefa(novaTarefa);

        return true;
    }

    private boolean handleListarTarefas(){
        System.out.println("\n== Listar Tarefas ==");

        gerenciador.listarTodas();
        System.out.println("=======================\n");

        return true;
    }

    private boolean handleListarTarefasPendentes(){
        System.out.println("\n== Listar Tarefas Pendentes ==");

        gerenciador.listarPendentes();

        System.out.println("=====================\n");
        return true;
    }

    private boolean handleBuscarTarefa(){
        System.out.println("--- Buscar Tarefa por Título ---");
        System.out.println("Digite parte ou título completo da tarefa: ");
        String termo = scanner.nextLine();

        List<Tarefas> resultados = gerenciador.buscarPorTitulo(termo);

        if(resultados.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com o termo: \"" + termo + "\".");
        } else {
            System.out.println(resultados.size() + " resultados encontrados: ");
            for(Tarefas tarefas : resultados){
                System.out.println("- " + tarefas);
            }
        }

        System.out.println("----------------------------");

        return true;

    }

    private boolean handleMarcarConcluida() {
        System.out.println("--- Marcar Tarefa Como Concluída ---");

        System.out.println("Tarefas Pendentes: ");
        gerenciador.listarPendentes();

        if (gerenciador.listarTodasTarefas().stream().noneMatch(t -> !t.isConcluida())) {
            System.out.println("Não há tarefas pendentes para conclusão.");
            System.out.println("---------------------------------\n");
        }

        System.out.println("Digite o ID da tarefa que deseja marcar: ");

        int idTarefa = scanner.nextInt();
        scanner.nextLine();

        gerenciador.marcarConcluidas(idTarefa);

        System.out.println("----------------------------\n");
        return true;
    }

    private boolean handleRemoverTarefa(){
            System.out.println("\n--- Remover Tarefa ---");

            List<Tarefas> todasTarefas = gerenciador.listarTodasTarefas();

            if (todasTarefas.isEmpty()) {
                System.out.println("Não há tarefas cadastradas para remover.");
                System.out.println("----------------------\n");
                return true;
            }

            System.out.println("Lista atual de todas as tarefas:");
            for(int i = 0; i < todasTarefas.size(); i++){
                System.out.println((i + 1) + ". " + todasTarefas.get(i));
            }

            System.out.println("\nDigite o NÚMERO (ID) da tarefa que deseja remover:");
            int idTarefa = scanner.nextInt();
            scanner.nextLine();

            gerenciador.removerTarefa(idTarefa);

            System.out.println("----------------------\n");
            return true;
        }


}
