package proyectofinalso;
import static java.lang.System.*;

/** Convenience calls for using the Kernel.
 * Each function in this class makes a system call.  Sometimes, the arguments
 * are manipulated to make their user representation more convenient.
 * Note that this class contains only static methods.
 * All methods return integers.  Negative return values are error codes.
 * Some methods return positive values; others simply return 0 to mean "ok".
 *
 * @see Kernel
 */
public class Library {
    
    
   static int getDiskBlockCount() {
        
        int diskSize= Disk.getDiskSize(); 
        
        if(diskSize>0){
            return diskSize; 
        }else {
            
            return 0; 
        }
        
    }

    static int getDiskBlockSize() {

        int blockSize= Disk.BLOCK_SIZE; 
        
        if (blockSize>0){
            
            return blockSize;
            
        } else{
            
            return 0; 
        }
    }

       static int writeDiskBlock(int i, byte[] buf) {
        int rc = Kernel.interrupt(Kernel.INTERRUPT_USER, Kernel.SYSCALL_WRITE_DISK_BLOCK, 0, i, null, buf);
        if (rc < 0) {
            return rc;
        } else {
            return 0;
        }
    }

    static int readDiskBlock(int b, byte[] buf) {
        int rc = Kernel.interrupt(Kernel.INTERRUPT_USER, Kernel.SYSCALL_READ_DISK_BLOCK, 0, b, null, buf);
        if (rc < 0) {
            return rc;
        } else {
            return 0;
        }
    }

    static long getTime() {
        long[] t = new long[1];
        int rc = Kernel.interrupt(Kernel.INTERRUPT_USER,
                        Kernel.SYSCALL_GET_TIME,
                        0, t, null, null );
        if (rc < 0) {
            return rc;
        } else {
            return t[0];
        }
        // getTime
    }

    static int format() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int chdir(String nextToken) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int create(String nextToken) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int delete(String nextToken) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int mkdir(String nextToken) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int rmdir(String nextToken) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int symlink(String oldName, String newName) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int readdir(String dirname, byte[] buf) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static int readlink(String string, byte[] buf1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** This private constructor ensures that no instances of Library are
     * ever created.
     */
    private Library() {}

    /** A table of error messages corresponding to Kernel error return codes.
     * This table should be indexed by the negative of rc, where
     * <pre>
     *          rc = Kernel.interrupt(Kernel.INTERRUPT_USER, ... )
     * </pre>
     * and rc is less than 0.
     */
    public static final String[] errorMessage = {
        "OK",                           // 0
        "Invalid argument",             // ERROR_BAD_ARGUMENT = -1
        "No such class",                // ERROR_NO_CLASS = -2
        "Class has no main method",     // ERROR_NO_MAIN = -3
        "Command aborted",              // ERROR_BAD_COMMAND = -4
        "Argument out of range",        // ERROR_OUT_OF_RANGE = -5
        "End of file on console input", // ERROR_END_OF_FILE = -6
        "I/O error on console input",   // ERROR_IO = -7
        "Exception in user program",    // ERROR_IN_CHILD = -8
        "No such process"               // ERROR_NO_SUCH_PROCESS = -9
    };

    /** Performs SYSCALL_OUTPUT.
     * Displays text on the console.
     * @param s a String to display
     * @return zero
     */
    public static int output(String s) {
        return Kernel.interrupt(Kernel.INTERRUPT_USER,
            Kernel.SYSCALL_OUTPUT, 0, s, null, null);
    } // output

    /** Performs SYSCALL_INPUT.
     * Waits for the user to type some text and hit [return].
     * The input line is returned in the supplied StringBuffer
     * @param result a place to put the result
     * @return zero on success, or one of the error codes Kernel.END_OF_FILE or
     * Kernel.ERROR_IO.
     */
    public static int input(StringBuffer result) {
        result.setLength(0);
        return Kernel.interrupt(Kernel.INTERRUPT_USER,
                            Kernel.SYSCALL_INPUT, 0, result, null, null);
    } // input

    /** Performs SYSCALL_EXEC.
     * Launches the named program, and lets it run in parallel
     * to the current program.
     * @param command The name of a Java class to execute.
     * @param args The arguments to give the new program
     * @return a non-negative process id, or ERROR_BAD_COMMAND.
     */
    public static int exec(String command, String args[]) {
        return Kernel.interrupt(Kernel.INTERRUPT_USER,
            Kernel.SYSCALL_EXEC, 0, command, args, null);
    } // exec

    /** Performs SYSCALL_JOIN.
     * Waits for a process to terminate
     * @param pid a process id returned by a previous call to exec.
     * @return zero or ERROR_NO_SUCH_PROCESS
     */
    public static int join(int pid) {
        return Kernel.interrupt(Kernel.INTERRUPT_USER,
            Kernel.SYSCALL_JOIN, pid, null, null, null);
    } // join
} // Library