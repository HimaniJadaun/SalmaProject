
package executor;


public class ProcessorTask {
    private String   name;
    private Integer  startTime;
    private Integer  endTime;
    private TaskType type;

    public ProcessorTask() {

    }

    public ProcessorTask(Integer readyTime, Integer endTime, TaskType primary, String name) {
        this.startTime = readyTime;
        this.endTime = endTime;
        this.type = primary;
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProcessorTask [name=" + name + ", startTime=" + startTime + ", endTime=" + endTime + ", type=" + type + "]";
    }

}
