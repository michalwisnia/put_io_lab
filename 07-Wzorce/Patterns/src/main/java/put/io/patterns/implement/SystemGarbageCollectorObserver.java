package put.io.patterns.implement;

public class SystemGarbageCollectorObserver implements SystemStateObserver {
    @Override
    public void update(SystemState lastSystemState) {

        if (lastSystemState.getAvailableMemory() < 200.00) {
            System.out.println("> Running garbage collector...");
        }
    }
}
