import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciadorTarefas {

    private List<Tarefas> listaDeTarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefas tarefa){
        this.listaDeTarefas.add(tarefa);
        System.out.println("'Tarefa '" + tarefa.getTitulo() + "'adicionada ao gerenciador.'");
    }

    public List<Tarefas> listarTodasTarefas(){
        return this.listaDeTarefas;
    }

    public void listarTodas(){
        List<Tarefas> tarefas = this.listaDeTarefas;
        if(tarefas.isEmpty()){
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("ID | Status | Título");
        System.out.println("----|--------|------");

        for(int i = 0; i < tarefas.size(); i++){
            Tarefas t = tarefas.get(i);

            String status = t.isConcluida() ? "Concluída" : "Pendente";
            System.out.printf("%2d | %s | %s\n", (i + 1), status, t.getTitulo());

        }
    }

    public void listarPendentes(){
        List<Tarefas> tarefasPendentes = new ArrayList<>();

        for(Tarefas t : this.listaDeTarefas){
            if(!t.isConcluida()){
                tarefasPendentes.add(t);
            }
        }

        if(tarefasPendentes.isEmpty()) {
            System.out.println("Nenhuma tarefa pendente encontrada.");
        } else {
            for(int i = 0; i < tarefasPendentes.size(); i++) {
                System.out.println((i + 1) + ". " + tarefasPendentes.get(i));
            }
        }
    }

    public List<Tarefas> buscarPorTitulo(String termoBusca){
        List<Tarefas> resultados = new ArrayList<>();

        String termoLower = termoBusca.toLowerCase();

        for(Tarefas tarefa : this.listaDeTarefas) {
            if(tarefa.getTitulo().toLowerCase().contains(termoLower)){
                resultados.add(tarefa);
            }
        }
        return resultados;
    }

    public boolean marcarConcluidas(int indiceUsuario){
        int indiceReal = indiceUsuario - 1;

        if(indiceReal >= 0 && indiceReal < this.listaDeTarefas.size()) {
            Tarefas tarefas = this.listaDeTarefas.get(indiceReal);

            if(tarefas.isConcluida()){
                System.out.println("A tarefa '" + tarefas.getTitulo() + "' já estava marcada como concluída");
                return false;
            } else {
                tarefas.setConcluida(true);
                System.out.println("Tarefa '" + tarefas.getTitulo() + "' marcada como concluída.");
                return true;
            }
        } else {
            System.out.println("Erro: Índice inválido!");
            return false;
        }
    }

    public boolean removerTarefa(int indiceUsuario){
        int indiceReal = indiceUsuario - 1;

        if(indiceReal >= 0 && indiceReal < this.listaDeTarefas.size()){
            Tarefas tarefaRemovida = this.listaDeTarefas.remove(indiceReal);
            System.out.println("Tarefa removida com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Índice de tarefa inválido ou inexistente.");
            return false;
        }
    }

}
