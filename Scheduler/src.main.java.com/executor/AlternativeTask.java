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
public class AlternativeTask {
    private Integer executionTime;
    private String name;

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public AlternativeTask(Integer executionTime,String name) {
        this.executionTime = executionTime;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
