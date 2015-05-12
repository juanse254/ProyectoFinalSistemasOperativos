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
    }
    
    /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk to read the data on the blocknumber.
     */
     public  synchronized void read(int blocknumber, byte data[]){
         status = disk.busy;
         while (status == true) {
             this.wait();
         }
         disk.beginRead(blocknumber, data[]);
         endIO();
    }
     
      /**
     * If the Resource(Disk) is not busy, calls the writeRead method on the disk to read the data on the blocknumber.
     */
     public  synchronized void write(int blocknumber, byte data[]){
         status = disk.busy;
         while (status == true) {
             this.wait();
         }
         disk.beginWrite(blocknumber, data[]);
         endIO();
     }
     
      /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk to read the data on the blocknumber.
     */
     public synchronized void endIO(){
         this.notifyall();
     }
    
}
