/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public class DiskCache {

    private ArrayList <DiskBlock> buffer;
    private Disk disk;
    
    public DiskCache(Disk a){
    
       buffer= new ArrayList<>();
       disk= a; 
           
    }
    
    
    public synchronized void read(int blocknumber, byte data[]) {
        
        DiskBlock block= new DiskBlock(512, blocknumber, data, "Clean", false ); //revisar el clean y el false
        
       
    }

    public synchronized void write(int blocknumber, byte data[]) {
        
         DiskBlock block= new DiskBlock(512, blocknumber, data, "Clean", false ); //revisa el clean y el false
    }

    public void shutDown() {

    }
}
