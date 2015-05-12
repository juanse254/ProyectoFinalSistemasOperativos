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
public class DiskSizeTest {
       
    public static void main(String[] args) {
        
            int now = Library.getDiskBlockCount();
            if (now < 0) {
                Library.output("Error: " + Library.errorMessage[(int) -now] + "\n");
            } else {
                Library.output(
                    "Current size is " + now
                    +  "\n");
            }
        } // main
    } // TimeTest
