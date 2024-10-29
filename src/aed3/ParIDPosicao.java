
package aed3;

import java.io.*;

public class ParIDPosicao implements RegistroHashExtensivel<ParIDPosicao> {
    private int id;
    private long posicao;
    private static final int TAMANHO = 12; 

    public ParIDPosicao() {
        this(-1, -1);
    }

    public ParIDPosicao(int id, long posicao) {
        this.id = id;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public long getPosicao() {
        return posicao;
    }

    @Override
    public int hashCode() {
        return Math.abs(Integer.hashCode(id));
    }

    @Override
    public int compareTo(ParIDPosicao outro) {
        return Integer.compare(this.id, outro.id);
    }

    @Override
    public short size() {
        return TAMANHO;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeLong(posicao);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.posicao = dis.readLong();
    }
}
