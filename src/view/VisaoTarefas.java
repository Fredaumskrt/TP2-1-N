package view;

import model.Tarefa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VisaoTarefas {
    private Scanner scanner;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VisaoTarefas() {
        scanner = new Scanner(System.in);
    }

    
    public Tarefa leTarefa() {
        try {
            System.out.print("ID da Tarefa: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nome da Tarefa: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição da Tarefa: ");
            String descricao = scanner.nextLine();
            System.out.print("Data de Criação (dd/mm/yyyy): ");
            Date dataCriacao = leData(); 
            System.out.print("Data de Conclusão (dd/mm/yyyy): ");
            Date dataConclusao = leData(); 
            System.out.print("Status da Tarefa: ");
            String status = scanner.nextLine();
            System.out.print("Prioridade: ");
            int prioridade = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Categoria: ");
            int idCategoria = Integer.parseInt(scanner.nextLine());
            
            return new Tarefa(id, nome, descricao, dataCriacao, dataConclusao, status, prioridade, idCategoria);
        } catch (Exception e) {
            System.out.println("Erro ao ler tarefa: " + e.getMessage());
            return null; 
        }
    }

    
    public void mostraTarefa(Tarefa tarefa) {
        if (tarefa != null) {
            System.out.println("ID: " + tarefa.getID());
            System.out.println("Nome: " + tarefa.getNome());
            System.out.println("Descrição: " + tarefa.getDescricao());
            System.out.println("Data de criação: " + tarefa.getDataCriacao());
            System.out.println("Data de conclusão: " + tarefa.getDataConclusao());
            System.out.println("Status: " + tarefa.getStatus());
            System.out.println("Prioridade: " + tarefa.getPrioridade());
            System.out.println("ID da Categoria: " + tarefa.getIdCategoria());
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    
    private Date leData() {
        try {
            return dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Data inválida, tente novamente no formato dd/mm/yyyy.");
            return leData(); 
        }
    }

    
    public int leIdTarefa() {
        System.out.print("ID da Tarefa: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
