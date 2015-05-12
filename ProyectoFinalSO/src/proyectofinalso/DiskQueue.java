/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Sebastian
 */
public class DiskQueue {

    private static Disk disk;
    private boolean status; // if busy, true else false.
    private List<Request> queue;

    public DiskQueue(Disk a) {
        disk = a;
        status = false;
        queue = new ArrayList<Request>();
    }

    /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk
     * to read the data on the block number.
     */
    public synchronized void read(int blocknumber, byte data[]) throws InterruptedException {
        Request x = new Request(blocknumber, data, Request.task.READ);
        queue.add(x);
        Collections.sort(queue, new Comparator<Request>() {
            @Override
            public int compare(Request request1, Request request2) {
                return request1.getRequestBlock() - request2.getRequestBlock();
            }
        });
        ProcessIO();

    }

    /**
     * If the Resource(Disk) is not busy, calls the writeRead method on the disk
     * to read the data on the block number.
     */
    public synchronized void write(int blocknumber, byte data[]) throws InterruptedException {

        Request x = new Request(blocknumber, data, Request.task.WRITE);
        queue.add(x);
        Collections.sort(queue, new Comparator<Request>() {
            @Override
            public int compare(Request request1, Request request2) {
                return request1.getRequestBlock() - request2.getRequestBlock();
            }
        });
        ProcessIO();

    }

    /**
     * If the Resource(Disk) is not busy, calls the beginRead method on the disk
     * to read the data on the block number.
     */
    public synchronized void endIO() {
        status = false;
        notifyAll();

    }

    public synchronized void ProcessIO() throws InterruptedException {
        for (Request current : queue) {
            if (status) {
                wait();
            } else if (current.getRequestTask() == Request.task.READ) {
                status = true;
                disk.beginRead(current.getRequestBlock(), current.getRequestBuffer());
                current.setTaskNULL();

            } else if (current.getRequestTask() == Request.task.WRITE) {
                status = true;
                disk.beginWrite(current.getRequestBlock(), current.getRequestBuffer());
                current.setTaskNULL();

            } else if (current.getRequestTask() == Request.task.NULL) {

            }

        }
    }

}
