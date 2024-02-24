package Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagementMemoryProcessTest {
    private Process process1;
    private Process process2;
    private Process process3;
    private Process process4;
    private Process process5;
    private Process process6;
    private Process process7;
    private Process process8;
    private Process process9;
    private Process process10;

    private String[] memory1;
    private String[] memory2;
    private String[] memory3;
    private String[] memory4;

    private ManagementMemoryProcess managementMemoryProcess;

    public void setup() {
        process1 = new Process("123-abc", (int) (Math.random()*20+1), "Nuevo");
        process2 = new Process("124-abd", (int) (Math.random()*20+1), "Listo");
        process3 = new Process("125-abe", (int) (Math.random()*20+1), "En ejecución");
        process4 = new Process("126-abf", (int) (Math.random()*20+1), "Nuevo");
        process5 = new Process("127-abg", (int) (Math.random()*20+1), "Nuevo");
        process6 = new Process("128-abh", (int) (Math.random()*20+1), "En ejecución");
        process7 = new Process("120-abi", (int) (Math.random()*20+1), "Nuevo");
        process8 = new Process("130-abj", (int) (Math.random()*20+1), "Nuevo");
        process9 = new Process("131-abk", (int) (Math.random()*20+1), "En ejecución");
        process10 = new Process("131-abk", (int) (Math.random()*20+1), "Nuevo");

        managementMemoryProcess = new ManagementMemoryProcess();


    }

    public void setupTwo(){
        setup();
        managementMemoryProcess.addProcess(process1);
        managementMemoryProcess.addProcess(process2);
        managementMemoryProcess.addProcess(process3);
        managementMemoryProcess.addProcess(process4);
        managementMemoryProcess.addProcess(process5);
        managementMemoryProcess.addProcess(process6);
        managementMemoryProcess.addProcess(process7);
        managementMemoryProcess.addProcess(process8);
        managementMemoryProcess.addProcess(process9);
        managementMemoryProcess.addProcess(process10);
    }



    @Test
    void getProcessList() {
        setupTwo();
        for (Process process : managementMemoryProcess.getProcessList()){
            System.out.println(process);
        }
    }

    @Test
    void getMemory() {

    }

    @Test
    void findProcess() {
        setupTwo();
        assertNotNull(managementMemoryProcess.findProcess(process2.getPID()));
        assertNotNull(managementMemoryProcess.findProcess(process3.getPID()));
        assertNotNull(managementMemoryProcess.findProcess(process7.getPID()));

        assertNull(managementMemoryProcess.findProcess("132-abd"));
        assertNull(managementMemoryProcess.findProcess("145-abd"));
    }

    @Test
    void addProcess() {
        setup();
        assertTrue(managementMemoryProcess.addProcess(process1));
        assertTrue(managementMemoryProcess.addProcess(process2));
        assertTrue(managementMemoryProcess.addProcess(process3));
        assertTrue(managementMemoryProcess.addProcess(process4));
        assertTrue(managementMemoryProcess.addProcess(process5));
        assertTrue(managementMemoryProcess.addProcess(process6));
        assertTrue(managementMemoryProcess.addProcess(process7));
        assertTrue(managementMemoryProcess.addProcess(process8));
        assertTrue(managementMemoryProcess.addProcess(process9));

        assertFalse(managementMemoryProcess.addProcess(process10));
    }

    @Test
    void unifyMemory() {


    }

    @Test
    void actionMemory() {
    }
}