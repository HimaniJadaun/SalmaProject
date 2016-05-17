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
public class Processor {
    private String name;
    private Integer startTime;
    private Integer endTime;
    
    public Processor(String name, Integer startTime, Integer endTime) {
        super();
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStartTime() {
        return startTime;
    }
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }
    public Integer getEndTime() {
        return endTime;
    }
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

}
