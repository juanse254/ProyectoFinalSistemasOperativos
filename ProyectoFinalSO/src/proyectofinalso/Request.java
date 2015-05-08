
import static java.lang.System.arraycopy;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dfellig
 */
public class Request {

    public static final Lock lock = new ReentrantLock();
    private byte[] requestBuffer = null;
    private int requestBlock = 0;
    public static enum task {READ, WRITE};
    private task operation; 
    private Condition condition = null;

    public Request(int requestBlock, byte[] requestBuffer, task operation) {
        
        if(false){
        this.requestBuffer = new byte[requestBuffer.length];
        arraycopy(requestBuffer, 0,
                this.requestBuffer, 0,
                requestBuffer.length);
        }else        
            this.requestBuffer = requestBuffer;
        this.requestBlock = requestBlock;
        this.operation = operation;
        condition = lock.newCondition();

    }
    
    public Condition getCondition(){
        return condition;
    }

    public byte[] getRequestBuffer() {
        return requestBuffer;
    }
    
    public int getRequestBlock(){
        return requestBlock;
    }
    
    public task getRequestTask(){
        return operation;
    }
    
    public void setRequestBlock(int requestBlock){
        this.requestBlock = requestBlock;
    }

}
