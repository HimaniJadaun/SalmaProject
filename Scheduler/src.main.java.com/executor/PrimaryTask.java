
package executor;

public class PrimaryTask{
    private String name;
    private Integer readyTime;
    private Integer executionTime;
    private Integer deadlineTime;
    
    public PrimaryTask(Integer readyTime, Integer executionTime,Integer deadlineTime ,String name) {
        this.deadlineTime=deadlineTime;
        this.readyTime = readyTime;
        this.executionTime = executionTime;
        this.name= name;
    }
    public Integer getReadyTime() {
        return readyTime;
    }
    public void setReadyTime(Integer readyTime) {
        this.readyTime = readyTime;
    }
    public Integer getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }
    public Integer getDeadlineTime() {
        return deadlineTime;
    }
    public void setDeadlineTime(Integer deadlineTime) {
        this.deadlineTime = deadlineTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "PrimaryTask [name=" + name + ", readyTime=" + readyTime + ", executionTime=" + executionTime + ", deadlineTime=" + deadlineTime + "]";
    }

}
