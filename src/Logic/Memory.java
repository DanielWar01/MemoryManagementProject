package Logic;

public class Memory {
    private String memoryAddress;
    private boolean enabled;
    private int spaceSize;
    private String PID;

    public Memory(String memoryAddress, boolean enabled, int spaceSize, String PID) {
        this.memoryAddress = memoryAddress;
        this.enabled = enabled;
        this.spaceSize = spaceSize;
        this.PID = PID;
    }

    public String getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(String memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "memoryAddress='" + memoryAddress + '\'' +
                ", enabled=" + enabled +
                ", spaceSize=" + spaceSize +
                ", PID='" + PID + '\'' +
                '}';
    }
}
