package proyectofinalso;

import java.util.*;
import java.io.*;
import static java.lang.System.*;

/** A file system. */

public class FileSys {
    /** The disk holding this file system. */
    private FastDisk disk;


    /** Initializes a FileSys instance for managing a disk.
     *
     * @param disk the disk containing the persistent data.
     */
    public FileSys(FastDisk disk) {
        this.disk = disk;
    } // FileSys(FastDisk)


} // FileSys
