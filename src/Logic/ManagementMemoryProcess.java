package Logic;

import java.util.ArrayList;

public class ManagementMemoryProcess {
    private ArrayList<Process> processList;

    public ManagementMemoryProcess() {
        processList = new ArrayList<Process>();
    }

    public ArrayList<Process> getProcessList() {
        return (ArrayList<Process>) processList.clone();
    }

    public Process findProcess(String PID){
        for (Process process : processList){
            if(process.getPID()  == PID) {
                return process;
            }
        }
        return null;
    }

    public boolean addProcess(Process process) {
        if (findProcess(process.getPID()) == null){
            return processList.add(process);
        }
        return false;
    }


}
