package controller;

import model.ArquivoTarefas;
import model.Tarefa;
import view.VisaoTarefas;

public class ControleTarefas {

    private ArquivoTarefas arquivoTarefas;
    private VisaoTarefas visaoTarefas;

    public ControleTarefas() {
        try {
            this.arquivoTarefas = new ArquivoTarefas(Tarefa.class.getConstructor(), "tarefas.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.visaoTarefas = new VisaoTarefas();
    }

    // Menu de operações
    public void menu() {
        int opcao;
        do {
            System.out.println("\nMenu de Tarefas:");
            System.out.println("1) Incluir Tarefa");
            System.out.println("2) Buscar Tarefa");
            System.out.println("3) Alterar Tarefa");
            System.out.println("4) Excluir Tarefa");
            System.out.println("5) Listar Tarefas por Categoria");
            System.out.println("0) Retornar ao menu principal");
            opcao = visaoTarefas.leIdTarefa();

            switch (opcao) {
                case 1 -> incluirTarefa();
                case 2 -> buscarTarefa();
                case 3 -> alterarTarefa();
                case 4 -> excluirTarefa();
                case 5 -> listarTarefasPorCategoria();
                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void incluirTarefa() {
        Tarefa tarefa = visaoTarefas.leTarefa();
        try {
            int id = arquivoTarefas.create(tarefa);
            tarefa.setID(id);
            System.out.println("Tarefa incluída com ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarTarefa() {
        int id = visaoTarefas.leIdTarefa();
        try {
            Tarefa tarefa = arquivoTarefas.read(id);
            visaoTarefas.mostraTarefa(tarefa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alterarTarefa() {
        int id = visaoTarefas.leIdTarefa();
        try {
            Tarefa tarefa = arquivoTarefas.read(id);
            if (tarefa != null) {
                System.out.println("Dados atuais da tarefa:");
                visaoTarefas.mostraTarefa(tarefa);
                Tarefa novaTarefa = visaoTarefas.leTarefa();
                novaTarefa.setID(id);
                arquivoTarefas.update(novaTarefa);
                System.out.println("Tarefa alterada com sucesso.");
            } else {
                System.out.println("Tarefa não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluirTarefa() {
        int id = visaoTarefas.leIdTarefa();
        try {
            if (arquivoTarefas.delete(id)) {
                System.out.println("Tarefa excluída com sucesso.");
            } else {
                System.out.println("Erro ao excluir tarefa.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarTarefasPorCategoria() {
        System.out.print("ID da Categoria: ");
        int idCategoria = visaoTarefas.leIdTarefa();
        try {
            arquivoTarefas.buscarTarefasPorCategoria(idCategoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
