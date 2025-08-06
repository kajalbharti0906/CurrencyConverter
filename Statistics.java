public class Statistics {
    private int addedCount;
    private int removedCount;
    private int modifiedCount;
    private int statusChangedCount;
    
    public Statistics() {
        this.addedCount = 0;
        this.removedCount = 0;
        this.modifiedCount = 0;
        this.statusChangedCount = 0;
    }
    
    public void incrementAdded() {
        addedCount++;
    }
    
    public void incrementRemoved() {
        removedCount++;
    }
    
    public void incrementModified() {
        modifiedCount++;
    }
    
    public void incrementStatusChanged() {
        statusChangedCount++;
    }
    
    public String getStatistics() {
        return String.format("[ADDED:%d,REMOVED:%d,MODIFIED:%d,STATUS_CHANGED:%d]",
                           addedCount, removedCount, modifiedCount, statusChangedCount);
    }
    
    // Getters for individual counts if needed
    public int getAddedCount() { return addedCount; }
    public int getRemovedCount() { return removedCount; }
    public int getModifiedCount() { return modifiedCount; }
    public int getStatusChangedCount() { return statusChangedCount; }
}