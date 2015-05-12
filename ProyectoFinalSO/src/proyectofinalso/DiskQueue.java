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
    private boolean status; // if busy, true else false.

    public DiskQueue(Disk a){
        disk = a;
        status=false;
    }
    
    /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk to read the data on the block number.
     */
     public  synchronized void read(int blocknumber, byte data[]) throws InterruptedException{
         while (status == true) {
             wait();
         }
         status = true;
         disk.beginRead(blocknumber, data);
    }
     
      /**
     * If the Resource(Disk) is not busy, calls the writeRead method on the disk to read the data on the block number.
     */
     public  synchronized void write(int blocknumber, byte data[]) throws InterruptedException{
         while (status == true) {
             wait();
         }
         status = true;
         disk.beginWrite(blocknumber, data);
     }
     
      /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk to read the data on the block number.
     */
     public synchronized void endIO(){
         status=false;
         notifyAll();
     }
    
}
