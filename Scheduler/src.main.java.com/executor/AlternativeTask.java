
package executor;

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
