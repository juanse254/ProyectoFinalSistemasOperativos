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
    private String state; 
    private Boolean flag; 

    public DiskBlock(int size, int location, byte[] data, String state, Boolean flag) {
        this.size = size;
        this.location = location;
        this.data = data;
        this.state = state;
        this.flag = flag;
    }
   

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

}
