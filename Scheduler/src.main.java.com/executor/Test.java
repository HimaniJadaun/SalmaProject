/**
 *  Copyright 2016 SuperHighway Labs (P) Limited . All Rights Reserved.
 *  SUPERHIGHWAY LABS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package executor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  
 *  @version     1.0, May 16, 2016
 *  @author himani
 */
public class Test {
    public static void main(String[] args) {
        Processor p1 = new Processor("P1", 0, 0);
        Processor p2 = new Processor("P2", 0, 0);
        Deque<Processor> processorList = new ArrayDeque<>();
        processorList.add(p1);
        processorList.add(p2); 
        //processorList.removeLast();
        processorList.remove();
        System.out.println("hii"+processorList);
    }

}
