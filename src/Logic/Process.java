package Logic;

public class Process {
    private String PID;
    private int memory;
    private String status;

    public Process(String PID, int memory, String status) {
        this.PID = PID;
        this.memory = memory;
        this.status = status;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Process{" +
                "PID=" + PID +
                ", memory=" + memory +
                ", status='" + status + '\'' +
                '}';
    }
}
