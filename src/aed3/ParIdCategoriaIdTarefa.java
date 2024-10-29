package aed3;

import java.io.*;

public class ParIdCategoriaIdTarefa implements RegistroArvoreBMais<ParIdCategoriaIdTarefa> {
    private int idCategoria;
    private int idTarefa;
    private static final short TAMANHO = 8; //  (4 bytes para cada int)

   
    public ParIdCategoriaIdTarefa() {
        this(-1, -1);
    }

    // cnstrutor que recebe idCategoria e idTarefa
    public ParIdCategoriaIdTarefa(int idCategoria, int idTarefa) {
        this.idCategoria = idCategoria;
        this.idTarefa = idTarefa;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    @Override
    public ParIdCategoriaIdTarefa clone() {
        return new ParIdCategoriaIdTarefa(this.idCategoria, this.idTarefa);
    }

    @Override
    public int compareTo(ParIdCategoriaIdTarefa outro) {
        if (this.idCategoria != outro.idCategoria) {
            return Integer.compare(this.idCategoria, outro.idCategoria);
        } else {
            return Integer.compare(this.idTarefa, outro.idTarefa);
        }
    }

    @Override
    public short size() {
        return TAMANHO;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idCategoria);
        dos.writeInt(idTarefa);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idCategoria = dis.readInt();
        this.idTarefa = dis.readInt();
    }

    @Override
    public String toString() {
        return "Categoria: " + idCategoria + " | Tarefa: " + idTarefa;
    }
}
