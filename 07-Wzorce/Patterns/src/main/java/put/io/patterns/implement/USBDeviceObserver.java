package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver {
    int Last_usb=0;
    boolean usb_init=false;
    @Override
    public void update(SystemState lastSystemState) {
        int cur_usb=lastSystemState.getUsbDevices();
        if(!usb_init){
            Last_usb=cur_usb;
            usb_init=true;
        }
        else{
            if(Last_usb!=cur_usb){
                System.out.println(String.format("Number of USB devices has changed from %d to %d", Last_usb,cur_usb));
                Last_usb=cur_usb;
            }
        }


    }
}