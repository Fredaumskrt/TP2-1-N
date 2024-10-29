package controller;

import model.ArquivoCategorias;
import model.Categoria;
import view.VisaoCategorias;

public class ControleCategorias {

    private ArquivoCategorias arquivoCategorias;
    private VisaoCategorias visaoCategorias;

    public ControleCategorias() {
        try {
            this.arquivoCategorias = new ArquivoCategorias(Categoria.class.getConstructor(), "categorias.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.visaoCategorias = new VisaoCategorias();
    }

    // Menu de operações
    public void menu() {
        int opcao;
        do {
            System.out.println("\nMenu de Categorias:");
            System.out.println("1) Incluir Categoria");
            System.out.println("2) Buscar Categoria");
            System.out.println("3) Alterar Categoria");
            System.out.println("4) Excluir Categoria");
            System.out.println("0) Retornar ao menu principal");
            opcao = visaoCategorias.leIdCategoria();
    
            switch (opcao) {
                case 1:
                    incluirCategoria();
                    break;
                case 2:
                    buscarCategoria();
                    break;
                case 3:
                    alterarCategoria();
                    break;
                case 4:
                    excluirCategoria();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }
    

    private void incluirCategoria() {
        Categoria categoria = visaoCategorias.leCategoria();
        try {
            int id = arquivoCategorias.create(categoria);
            categoria.setID(id);
            System.out.println("Categoria incluída com ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarCategoria() {
        int id = visaoCategorias.leIdCategoria();
        try {
            Categoria categoria = arquivoCategorias.read(id);
            visaoCategorias.mostraCategoria(categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alterarCategoria() {
        int id = visaoCategorias.leIdCategoria();
        try {
            Categoria categoria = arquivoCategorias.read(id);
            if (categoria != null) {
                System.out.println("Dados atuais da categoria:");
                visaoCategorias.mostraCategoria(categoria);
                Categoria novaCategoria = visaoCategorias.leCategoria();
                novaCategoria.setID(id);
                arquivoCategorias.update(novaCategoria);
                System.out.println("Categoria alterada com sucesso.");
            } else {
                System.out.println("Categoria não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluirCategoria() {
        int id = visaoCategorias.leIdCategoria();
        try {
            if (arquivoCategorias.delete(id)) {
                System.out.println("Categoria excluída com sucesso.");
            } else {
                System.out.println("Erro ao excluir categoria.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
