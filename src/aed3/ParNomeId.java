package aed3;

import java.io.*;

public class ParNomeId implements RegistroArvoreBMais<ParNomeId> {
    private String nome;
    private int id;
    private static final short TAMANHO = 64;

    public ParNomeId() {
        this("", -1);
    }

    public ParNomeId(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Math.abs(nome.hashCode());
    }

    public int getID() {
        return this.id;
    }

    public short size() {
        return TAMANHO;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(nome);
        dos.writeInt(id);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.nome = dis.readUTF();
        this.id = dis.readInt();
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", ID: " + id;
    }

    
    @Override
    public int compareTo(ParNomeId outro) {
        return this.nome.compareTo(outro.nome);
    }

    
    @Override
    public ParNomeId clone() {
        try {
            return new ParNomeId(this.nome, this.id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
