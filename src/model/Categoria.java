package model;

import java.io.*;

public class Categoria implements Registro {
    private int id;
    private String nome;

    
    public Categoria(String nome) {
        this.nome = nome;
    }

    
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
    }
}
