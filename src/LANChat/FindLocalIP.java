package LANChat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class FindLocalIP {
    public static String ip() throws SocketException {
        String ip,localIp;
        ArrayList<String> list = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isLoopback() || !iface.isUp())
                continue;
            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                ip = addr.getHostAddress();
                list.add(iface.getDisplayName() + " " + ip);
            }
            if (list.size()==2){
                localIp = list.get(1);
                String[] ipList = localIp.split(" ");
                return ipList[1];
            }else {
                localIp = list.get(0);
                String[] ipList = localIp.split(" ");
                return ipList[1];
            }
        }
        return "Server IP not Found!";
    }
}
