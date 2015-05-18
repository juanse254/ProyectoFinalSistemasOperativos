/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalso;

/**
 *
 * @author user
 */
public class DiskBlock {

    private int size;
    private int location;
    private byte [] data; 
    private Boolean flag;
    private state stat;
    private type kind;

    public type getKind() {
        return kind;
    }

    public void setKind(type kind) {
        this.kind = kind;
    }

    public DiskBlock(int size, int location, byte[] data, state sta, Boolean flag, type k) {
        this.size = size;
        this.location = location;
        this.data = data;
        this.flag = flag;
        this.stat = sta;
        this.kind = k;
    }

    public byte[] getData() {
        return data;
    }

    public Boolean getFlag() {
        return flag;
    }
    public static enum  state {
        EMPTY, DIRTY, CLEAN
    }; 
    
    public static enum  type {
        READ, WRITE
    };
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public state getStat() {
        return stat;
    }
    public void setStat(state stat) {
        this.stat = stat;
    }
}
