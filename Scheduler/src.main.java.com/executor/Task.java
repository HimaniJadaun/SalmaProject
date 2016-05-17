/**
 *  Copyright 2016 SuperHighway Labs (P) Limited . All Rights Reserved.
 *  SUPERHIGHWAY LABS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package executor;

/**
 *  
 *  @version     1.0, May 16, 2016
 *  @author himani
 */
public  class Task {
    
    private PrimaryTask primaryTask;
    private AlternativeTask alternativeTask;
    public PrimaryTask getPrimaryTask() {
        return primaryTask;
    }
    public void setPrimaryTask(PrimaryTask primaryTask) {
        this.primaryTask = primaryTask;
    }
    public AlternativeTask getAlternativeTask() {
        return alternativeTask;
    }
    public void setAlternativeTask(AlternativeTask alternativeTask) {
        this.alternativeTask = alternativeTask;
    }

}
