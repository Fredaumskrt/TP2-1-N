package view;

import model.Categoria;
import java.util.Scanner;

public class VisaoCategorias {
    private Scanner scanner;

    public VisaoCategorias() {
        scanner = new Scanner(System.in);
    }

    
    public Categoria leCategoria() {
        System.out.print("Nome da Categoria: ");
        String nome = scanner.nextLine();
        return new Categoria(-1, nome);
    }

    
    public void mostraCategoria(Categoria categoria) {
        if (categoria != null) {
            System.out.println("ID: " + categoria.getID());
            System.out.println("Nome: " + categoria.getNome());
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    
    public int leIdCategoria() {
        System.out.print("ID da Categoria: ");
        return scanner.nextInt();
    }
}
