
package executor;

import java.util.ArrayList;
import java.util.List;


public class Processor {
    private String      name;
    List<ProcessorTask> taskList = new ArrayList<>();

    public Processor(String name) {
       this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProcessorTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<ProcessorTask> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Processor [name=" + name + ", taskList=" + taskList + "]";
    }

}
