/**
 *  Copyright 2016 SuperHighway Labs (P) Limited . All Rights Reserved.
 *  SUPERHIGHWAY LABS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package executor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0, May 16, 2016
 * @author himani
 */
public class ScheduleTask {

    public static void main(String[] args) {
        Processor p1 = new Processor("P1", 0, 0);
        Processor p2 = new Processor("P2", 0, 0);
        List<Processor> processorList = new ArrayList<>();
        processorList.add(p1);
        processorList.add(p2);
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        Task task4 = new Task();
        PrimaryTask primary1 = new PrimaryTask(1, 2, 3, "primary1");
        AlternativeTask alternative1 = new AlternativeTask(1, "alternative1");
        PrimaryTask primary2 = new PrimaryTask(2, 2, 5, "primary2");
        AlternativeTask alternative2 = new AlternativeTask(1, "alternative2");
        PrimaryTask primary3 = new PrimaryTask(3, 2, 7, "primary3");
        AlternativeTask alternative3 = new AlternativeTask(1, "alternative3");
        PrimaryTask primary4 = new PrimaryTask(4, 2, 8, "primary4");
        AlternativeTask alternative4 = new AlternativeTask(1, "alternative4");
        task1.setAlternativeTask(alternative1);
        task1.setPrimaryTask(primary1);
        task2.setAlternativeTask(alternative2);
        task2.setPrimaryTask(primary2);
        task3.setAlternativeTask(alternative3);
        task3.setPrimaryTask(primary3);
        task4.setAlternativeTask(alternative4);
        task4.setPrimaryTask(primary4);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);

    }

    public static void scheduleTask(List<Processor> processorList, List<Task> taskList) {
        Map<String, String> processorToTaskMap = new HashMap<>();
        int count = 0;
        for (Task task : taskList) {
            //First Task
            PrimaryTask pTask = task.getPrimaryTask();
            AlternativeTask aTask = task.getAlternativeTask();
            if (count == 0) {
                Processor p1 = processorList.get(0);
                p1.setStartTime(pTask.getReadyTime());
                p1.setEndTime(pTask.getReadyTime() + pTask.getExecutionTime());
                processorToTaskMap.put(pTask.getName(), p1.getName() + "Start-->" + p1.getStartTime() + "End-->" + p1.getEndTime());
               
                Processor p2 = processorList.get(1);
                p2.setStartTime(pTask.getDeadlineTime() - aTask.getExecutionTime());
                p2.setEndTime(pTask.getDeadlineTime());
                processorToTaskMap.put(aTask.getName(), p2.getName() + "Start-->" + p2.getStartTime() + "End-->" + p2.getEndTime());
            }

            else {
                for (Processor processor : processorList) {
                    if ((pTask.getDeadlineTime() - pTask.getExecutionTime()) >= (processor.getEndTime())) {
                        processorToTaskMap.put(aTask.getName(), p2.getName() + "Start-->" + p2.getStartTime() + "End-->" + p2.getEndTime());
                    }
                }
            }
            count++;
        }
    }

    public static void schedulePrimaryTask(List<Processor> processorList, PrimaryTask primaryTask, Map<String, String> processorToTaskMap) {
        for (Processor processor : processorList) {
            processorToTaskMap.put(processor.getName() + "Satrt-->" + primaryTask.getReadyTime() + "End-->" + primaryTask.getExecutionTime(), primaryTask.getName());
        }
    }

    public static void scheduleAletrnativeTask(List<Processor> processorList, AlternativeTask alternativeTask, Map<String, String> processorToTaskMap) {
        for (Processor processor : processorList) {
            processorToTaskMap.put(processor.getName() + "Execution Time-->" + alternativeTask.getExecutionTime(), alternativeTask.getName());
        }
    }
}












