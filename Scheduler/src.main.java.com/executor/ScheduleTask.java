
package executor;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTask {

    public static void main(String[] args) {
        List<Processor> processorList = getProcessorList();
        List<InputTask> taskList = getTaskList();
        scheduleTask(processorList, taskList);
        //Printing task scheduling
        for (Processor processor : processorList) {
            for (ProcessorTask pTask : processor.getTaskList()) {
                System.out.println("Processor " + processor.getName() + ":  Task " + pTask.toString());
            }
        }

    }

    //Scheduling task
    public static void scheduleTask(List<Processor> processorList, List<InputTask> taskList) {
        int count = 0;
        for (InputTask task : taskList) {
            boolean primaryProcessorAssigned = false;
            boolean altProcessorAssigned = false;
            PrimaryTask pTask = task.getPrimaryTask();
            AlternativeTask aTask = task.getAlternativeTask();
            //Assigning First Primary Task
            if (count == 0) {
                Processor p1 = processorList.get(0);
                p1.getTaskList().add(new ProcessorTask(pTask.getReadyTime(), pTask.getReadyTime() + pTask.getExecutionTime(), TaskType.PRIMARY, pTask.getName()));

                //Assigning First Alternative Task
                Processor p2 = processorList.get(1);
                p2.getTaskList().add(new ProcessorTask(pTask.getDeadlineTime() - aTask.getExecutionTime(), pTask.getDeadlineTime(), TaskType.ALTERNATIVE, aTask.getName()));

            } else {
                //Primary task allocation for task other than first
                String assignedProcessorName = null;
                for (Processor processor : processorList) {
                    Integer firstFitIndexForPrimary = firstFitTimeIndexForPrimary(processor, task);
                    if (firstFitIndexForPrimary != -1) {
                        ProcessorTask newTask = null;
                        if (firstFitIndexForPrimary == 0) {
                            newTask = new ProcessorTask(pTask.getReadyTime(), pTask.getReadyTime() + pTask.getExecutionTime(), TaskType.PRIMARY, pTask.getName());
                            processor.getTaskList().add(firstFitIndexForPrimary, newTask);
                            primaryProcessorAssigned = true;
                            assignedProcessorName = processor.getName();
                            break;
                        } else if (firstFitIndexForPrimary == processor.getTaskList().size()) {
                            ProcessorTask prevTask = processor.getTaskList().get(firstFitIndexForPrimary-1);
                            newTask = new ProcessorTask(prevTask.getEndTime(), prevTask.getEndTime() + pTask.getExecutionTime(), TaskType.PRIMARY, pTask.getName());
                            processor.getTaskList().add(firstFitIndexForPrimary, newTask);
                            primaryProcessorAssigned = true;
                            assignedProcessorName = processor.getName();
                            break;
                            
                        } else {
                            ProcessorTask nextTask = processor.getTaskList().get(firstFitIndexForPrimary);
                            if (nextTask.getStartTime() >= pTask.getDeadlineTime()) {
                                newTask = new ProcessorTask(pTask.getDeadlineTime() - aTask.getExecutionTime(), pTask.getDeadlineTime(), TaskType.ALTERNATIVE, aTask.getName());
                                processor.getTaskList().add(firstFitIndexForPrimary, newTask);
                                primaryProcessorAssigned = true;
                                assignedProcessorName = processor.getName();
                                break;
                            }
                        }
                    } else {
                        continue;
                    }
                }
                //Spawning a new processor
                if (!primaryProcessorAssigned) {
                    String processorName = "P" + (processorList.size() + 1);
                    Processor newProcessor = new Processor(processorName);
                    processorList.add(newProcessor);
                    assignedProcessorName = newProcessor.getName();
                    newProcessor.getTaskList().add(new ProcessorTask(pTask.getReadyTime(), pTask.getReadyTime() + pTask.getExecutionTime(), TaskType.PRIMARY, pTask.getName()));
                }
                //Secondary task allocation other tahn first
                for (Processor processor : processorList) {
                    if (assignedProcessorName.equals(processor.getName())) {
                        continue;
                    }
                    Integer firstFitIndexForAlt = firstFitTimeIndexForAlternative(processor, task);
                    if (firstFitIndexForAlt != -1) {
                        ProcessorTask newTask = null;
                        if (firstFitIndexForAlt == 0 ) {
                            newTask = new ProcessorTask(pTask.getDeadlineTime() - aTask.getExecutionTime(), pTask.getDeadlineTime(), TaskType.ALTERNATIVE, aTask.getName());
                            processor.getTaskList().add(firstFitIndexForAlt, newTask);
                            altProcessorAssigned = true;
                            break;
                        } 
                        else if (firstFitIndexForAlt == processor.getTaskList().size()){
//                            ProcessorTask prevTask = processor.getTaskList().get(firstFitIndexForAlt-1);
                            newTask = new ProcessorTask(pTask.getDeadlineTime() - aTask.getExecutionTime(), pTask.getDeadlineTime(), TaskType.ALTERNATIVE, aTask.getName());
                            processor.getTaskList().add(firstFitIndexForAlt, newTask);
                            altProcessorAssigned = true;
                            break;
                        }
                        else {
                            // ProcessorTask currTask = processor.getTaskList().get(firstFitIndexForAlt - 1);
                            ProcessorTask nextTask = processor.getTaskList().get(firstFitIndexForAlt);
                            if (nextTask.getStartTime() >= pTask.getDeadlineTime()) {
                                newTask = new ProcessorTask(pTask.getDeadlineTime() - aTask.getExecutionTime(), pTask.getDeadlineTime(), TaskType.ALTERNATIVE, aTask.getName());
                                processor.getTaskList().add(firstFitIndexForAlt, newTask);
                                altProcessorAssigned = true;
                                break;
                            }
                        }

                    }
                }
                //Spawning a new processor
                if (!altProcessorAssigned) {
                    String processorName = "P" + (processorList.size() + 1);
                    Processor newProcessor = new Processor(processorName);
                    processorList.add(newProcessor);
                    newProcessor.getTaskList().add(new ProcessorTask(pTask.getReadyTime(), pTask.getReadyTime() + aTask.getExecutionTime(), TaskType.ALTERNATIVE, aTask.getName()));
                }
            }
            count++;
        }
    }

    //Getting index to allocate primary task
    private static Integer firstFitTimeIndexForPrimary(Processor p, InputTask task) {
        PrimaryTask primaryTask = task.getPrimaryTask();
        if (null != p && !p.getTaskList().isEmpty()) {
            ProcessorTask prevTask = null;
            int index = 0;
            if (p.getTaskList().size() == 1) {
                ProcessorTask pTask = p.getTaskList().get(0);
                if (pTask.getStartTime() >= (primaryTask.getReadyTime() + primaryTask.getExecutionTime())) {
                    return 0;
                }
                if (pTask.getEndTime() <= (primaryTask.getDeadlineTime() - primaryTask.getExecutionTime())) {
                    return 1;
                }
            }

            for (ProcessorTask pTask : p.getTaskList()) {
                if (index == 0) {
                    prevTask = pTask;
                } else {
                    ProcessorTask currTask = pTask;
                    if ((primaryTask.getDeadlineTime() - primaryTask.getExecutionTime()) >= prevTask.getEndTime() && primaryTask.getDeadlineTime() <= currTask.getStartTime()) {
                        return index;
                    }
                    prevTask = currTask;
                }
                index++;
            }
            ProcessorTask lastTask = p.getTaskList().get(p.getTaskList().size() - 1);
            if (lastTask.getEndTime() <= (primaryTask.getDeadlineTime() - primaryTask.getExecutionTime())) {
                return index;
            }
        }
        return -1;
    }

    //Getting index to allocate secondary task
    private static Integer firstFitTimeIndexForAlternative(Processor p, InputTask task) {
        PrimaryTask primaryTask = task.getPrimaryTask();
        AlternativeTask altTask = task.getAlternativeTask();
        if (null != p && !p.getTaskList().isEmpty()) {
            ProcessorTask prevTask = null;
            int index = 0;
            if (p.getTaskList().size() == 1) {
                ProcessorTask pTask = p.getTaskList().get(0);
                if (pTask.getStartTime() >= (primaryTask.getReadyTime() + altTask.getExecutionTime())) {
                    return 0;
                }
                if (pTask.getEndTime() <= (primaryTask.getDeadlineTime() - altTask.getExecutionTime())) {
                    return 1;
                }
            }

            for (ProcessorTask pTask : p.getTaskList()) {
                if (index == 0) {
                    prevTask = pTask;
                } else {
                    ProcessorTask currTask = pTask;
                    if ((primaryTask.getDeadlineTime() - altTask.getExecutionTime()) >= prevTask.getEndTime() && primaryTask.getDeadlineTime() <= currTask.getStartTime()) {
                        return index;
                    }
                    prevTask = currTask;
                }
                index++;
            }
            ProcessorTask lastTask = p.getTaskList().get(p.getTaskList().size() - 1);
            if (lastTask.getEndTime() <= (primaryTask.getDeadlineTime() - altTask.getExecutionTime())) {
                return index;
            }
        }
        return -1;
    }

    private static List<InputTask> getTaskList() {
        List<InputTask> taskList = new ArrayList<>();
        InputTask task1 = new InputTask();
        InputTask task2 = new InputTask();
        InputTask task3 = new InputTask();
        InputTask task4 = new InputTask();
        InputTask task5 = new InputTask();
        InputTask task6 = new InputTask();
        InputTask task7 = new InputTask();
        InputTask task8 = new InputTask();
        InputTask task9 = new InputTask();

        PrimaryTask primary1 = new PrimaryTask(1, 2, 3, "primary1");
        AlternativeTask alternative1 = new AlternativeTask(1, "alternative1");
        PrimaryTask primary2 = new PrimaryTask(2, 2, 5, "primary2");
        AlternativeTask alternative2 = new AlternativeTask(1, "alternative2");
        PrimaryTask primary3 = new PrimaryTask(3, 2, 7, "primary3");
        AlternativeTask alternative3 = new AlternativeTask(1, "alternative3");
        PrimaryTask primary4 = new PrimaryTask(4, 2, 8, "primary4");
        AlternativeTask alternative4 = new AlternativeTask(1, "alternative4");
        
        PrimaryTask primary5 = new PrimaryTask(4, 3, 8, "primary5");
        AlternativeTask alternative5 = new AlternativeTask(2, "alternative5");
        PrimaryTask primary6 = new PrimaryTask(5, 3, 10, "primary6");
        AlternativeTask alternative6 = new AlternativeTask(2, "alternative6");
        PrimaryTask primary7 = new PrimaryTask(6, 3, 12, "primary7");
        AlternativeTask alternative7 = new AlternativeTask(2, "alternative7");
        PrimaryTask primary8 = new PrimaryTask(8, 3, 12, "primary8");
        AlternativeTask alternative8 = new AlternativeTask(2, "alternative8");
        PrimaryTask primary9 = new PrimaryTask(8, 2, 13, "primary9");
        AlternativeTask alternative9 = new AlternativeTask(1, "alternative9");

        task1.setAlternativeTask(alternative1);
        task1.setPrimaryTask(primary1);
        task2.setAlternativeTask(alternative2);
        task2.setPrimaryTask(primary2);
        task3.setAlternativeTask(alternative3);
        task3.setPrimaryTask(primary3);
        task4.setAlternativeTask(alternative4);
        task4.setPrimaryTask(primary4);
        
        task5.setAlternativeTask(alternative5);
        task5.setPrimaryTask(primary5);
        task6.setAlternativeTask(alternative6);
        task6.setPrimaryTask(primary6);
        task7.setAlternativeTask(alternative7);
        task7.setPrimaryTask(primary7);
        task8.setAlternativeTask(alternative8);
        task8.setPrimaryTask(primary8);
        task9.setAlternativeTask(alternative9);
        task9.setPrimaryTask(primary9);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.add(task8);
        taskList.add(task9);
        return taskList;
    }

    private static List<Processor> getProcessorList() {
        Processor p1 = new Processor("P1");
        Processor p2 = new Processor("P2");
        List<Processor> processorList = new ArrayList<>();
        processorList.add(p1);
        processorList.add(p2);
        return processorList;
    }

}
