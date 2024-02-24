package Logic;

import java.util.ArrayList;
import java.util.UUID;

public class ManagementMemoryProcess {
    private ArrayList<Process> processList;
    private ArrayList<Memory> systemMemoryDynamic;
    private ArrayList<Memory> systemMemoryFixed;

    /**
     * El primer ArrayList almacena procesos
     * El siguiente ArrayList es una cola de los segmentos de la memoria el cual contiene un vector que
     * el cual consta de dos valores el primero si la memoria esta libre '0' o si esta ocupada '1'
     */
    public ManagementMemoryProcess() {
        processList = new ArrayList<>();
        systemMemoryFixed = new ArrayList<>();
        systemMemoryDynamic = new ArrayList<>();
    }

    public ArrayList<Process> getProcessList() {
        return (ArrayList<Process>) processList.clone();
    }

    public ArrayList<Memory> getMemoryDynamic(){
        return (ArrayList< Memory >) systemMemoryDynamic.clone();
    }

    public ArrayList<Memory> getSystemMemoryFixed(){
        return (ArrayList<Memory >) systemMemoryFixed.clone();
    }

    public Process findProcess(String PID){
        for (Process process : processList){
            if(process.getPID().equals(PID)) {
                return process;
            }
        }
        return null;
    }

    /**
     * Agrega los procesos al arraylist anteriormente creado
     * @param process Proceso a almacenar
     * @return Verdadero o falso si se pudo o no almacenar el proceso
     */
    public boolean addProcess(Process process) {
        if (findProcess(process.getPID()) == null){
            return processList.add(process);
        }
        return false;
    }

    public String generatorMemoryAddress(){
        return String.valueOf(UUID.randomUUID());
    }

    public void setFixedPartitions(int partitions, int space){
        for (int i = 0; i < partitions; i++) {
            String memoryAddress = generatorMemoryAddress();
            boolean enabled = true;
            int spaceSize = space/partitions;
            String PID = null;
            Memory memory = new Memory(memoryAddress, enabled, spaceSize, PID);
            systemMemoryFixed.add(memory);
        }
    }

    /**
     * Método que modifica la memoria realizando una insercion o extracción de un proceso en la misma
     * @param PID Proceso que va a ingresar o salir de la memoria
     * @param action Variable que determina si entra o sale un proceso de memoria(true ingresa, false sale)
     * @return El arraylist ya modificado con la acción realizada en la memoria
     */
    public ArrayList< Memory > actionMemoryFixed(String PID, boolean action){
        int count = 0;
        for (Memory memory : getSystemMemoryFixed()){
            System.out.println(memory.getSpaceSize()+" "+findProcess(PID).getMemory());
            if (memory.isEnabled() && action && memory.getSpaceSize() >= findProcess(PID).getMemory()) {

                Process process = findProcess(PID);
                systemMemoryFixed.set(count, new Memory(memory.getMemoryAddress(), false, process.getMemory(), process.getPID()));
                if ((memory.getSpaceSize() - findProcess(PID).getMemory()) != 0) {
                    systemMemoryFixed.add(count + 1, new Memory(generatorMemoryAddress(), false, memory.getSpaceSize() - process.getMemory(), null));
                }
                break;
            }
            if (memory.getSpaceSize() < findProcess(PID).getMemory()){
                System.out.println("No hay espacio suficiente para el proceso");
            }

            if (!memory.isEnabled() && !action && memory.getPID().equals(PID)) {
                if (systemMemoryFixed.get(count+1).isEnabled()){
                    systemMemoryFixed.set(count, new Memory(memory.getMemoryAddress(), false, memory.getSpaceSize(), null));
                } else{
                    systemMemoryFixed.set(count, new Memory(memory.getMemoryAddress(), false, memory.getSpaceSize() + systemMemoryDynamic.get(count + 1).getSpaceSize(), null));
                    systemMemoryFixed.remove(count+1);
                }
            }

            count++;
        }
        return systemMemoryFixed;
    }

    /**
     * Se quiere unificar la memoria cuando hay dos segmentos disponibles uno despues del siguiente,
     * esto solo en la gestión de memoria dinámica
     * @return La lista ya unificada
     */
    public ArrayList< Memory > unifyMemory() {
        ArrayList<Memory> result = new ArrayList<>();
        int sum = 0;
        Memory memory = new Memory("", false, 0, null);
        for (int i = 0; i < systemMemoryDynamic.size(); i++) {
            memory = systemMemoryDynamic.get(i);
            if (systemMemoryDynamic.get(i).isEnabled() && systemMemoryDynamic.get(i+1).isEnabled()){
                sum = systemMemoryDynamic.get(i).getSpaceSize() + systemMemoryDynamic.get(i+1).getSpaceSize();
                i++;
                memory.setSpaceSize(sum);
            }
            result.add(memory);
        }
        systemMemoryDynamic = result;
        return systemMemoryDynamic;
    }

    public ArrayList< Memory > actionMemoryDynamic(String PID, int action){

        return null;
    }


}
