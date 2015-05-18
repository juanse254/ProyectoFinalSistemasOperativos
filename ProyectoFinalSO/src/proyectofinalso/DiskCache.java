/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalso;

import java.util.ArrayList;
import static java.lang.System.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class DiskCache {

    private LinkedList <DiskBlock> cache;
    private Disk disk;
    private int cache_size;
    protected byte[] data;
    private DiskQueue Monitor;
    
    public DiskCache(Disk a, int size){
       cache_size = size;
       cache= new LinkedList<>();
       disk= a; 
       data = new byte[disk.DISK_SIZE * disk.BLOCK_SIZE];
       Monitor = new DiskQueue(disk);
    }
    
    
    public synchronized void read(int blocknumber, byte datax[]) throws InterruptedException {
        if(cache.size() == cache_size){
            ClearCache();
        }
        Boolean isCache = false;
        for (DiskBlock Block : cache) {
            if (Block.getLocation() == blocknumber && Block.getFlag().equals(Boolean.FALSE)) {
                arraycopy(data, Block.getLocation() * Block.getSize(), Block.getData(), 0, Block.getSize());
                Block.setFlag(Boolean.TRUE);
                isCache = true;
                break;
            }
            else if(Block.getLocation() == blocknumber && Block.getFlag().equals(Boolean.TRUE)){
                int value = cache.indexOf(Block);
                Block.setFlag(Boolean.FALSE);
                cache.addLast(Block);
                cache.remove(value);
                isCache = true;
                break;
            }
        }
        if (!isCache) {
            for(DiskBlock x:cache){
                if (x.getStat() == DiskBlock.state.CLEAN) {
                    arraycopy(data, blocknumber * Disk.BLOCK_SIZE, datax, 0, Disk.BLOCK_SIZE);
                    int y = cache.indexOf(x);
                    cache.remove(x);
                    DiskBlock block = new DiskBlock(disk.BLOCK_SIZE, blocknumber, datax, DiskBlock.state.DIRTY, true, DiskBlock.type.READ); //revisar el clean y el false
                    cache.add(y,block);
                    Monitor.read(blocknumber, datax);
                    break;
                }
            }
        }
    }

    public synchronized void write(int blocknumber, byte datax[]) throws InterruptedException {
         if(cache.size() == cache_size){
            ClearCache();
        }
        Boolean isCache = false;
        for (DiskBlock Block : cache) {
            if (Block.getLocation() == blocknumber && Block.getFlag().equals(Boolean.FALSE)) {
                arraycopy(Block.getData(), 0, Block.getData(), Block.getSize() * Disk.BLOCK_SIZE, Block.getSize());
                Block.setFlag(Boolean.TRUE);
                isCache = true;
                break;
            }
            else if(Block.getLocation() == blocknumber && Block.getFlag().equals(Boolean.TRUE)){
                int value = cache.indexOf(Block);
                Block.setFlag(Boolean.FALSE);
                cache.addLast(Block);
                cache.remove(value);
                isCache = true;
                break;
            }
        }
        if (!isCache) {
            for(DiskBlock x:cache){
                if (x.getStat() == DiskBlock.state.CLEAN) {
                    int y = cache.indexOf(x);
                    cache.remove(x);
                    DiskBlock block = new DiskBlock(disk.BLOCK_SIZE, blocknumber, datax, DiskBlock.state.DIRTY, true, DiskBlock.type.WRITE); //revisar el clean y el false
                    cache.add(y,block);
                    break;
                }
            }
        }
    }

    public void shutDown() {

    }
    
    private synchronized void ClearCache() throws InterruptedException {
                for (DiskBlock Block : cache) {
                    if (Block.getStat() == DiskBlock.state.DIRTY) {
                        if (Block.getKind() == DiskBlock.type.READ) {
                            Monitor.read(Block.getLocation(), Block.getData());
                        }
                        else if(Block.getKind() == DiskBlock.type.READ){
                            Monitor.write(Block.getLocation(), Block.getData());
                        }
                        Block.setStat(DiskBlock.state.CLEAN);
                        Block.setFlag(Boolean.TRUE);
                    }
        }
    }
}
