/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalso;

/**
 *
 * @author Sebastian
 */
public class DiskQueue {
    
    private static Disk disk;
    private boolean status;

    public DiskQueue(Disk a){
        disk = a;
        status = disk.busy;
    }
    
    /**
     *
     */
     public static void read(){
        
    }
     
     public static void write(){
         
     }
     
     public static void endIO(){
         
     }
    
}
