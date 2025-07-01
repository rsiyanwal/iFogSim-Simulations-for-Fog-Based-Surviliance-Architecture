package org.fog.test.perfeval;

// Importing the Packages
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.function.LongFunction;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.power.PowerHost;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.cloudbus.cloudsim.sdn.overbooking.BwProvisionerOverbooking;
import org.cloudbus.cloudsim.sdn.overbooking.PeProvisionerOverbooking;
import org.fog.application.AppEdge;
import org.fog.application.AppLoop;
import org.fog.application.Application;
import org.fog.application.selectivity.FractionalSelectivity;
import org.fog.entities.Actuator;
import org.fog.entities.FogBroker;
import org.fog.entities.FogDevice;
import org.fog.entities.FogDeviceCharacteristics;
import org.fog.entities.Sensor;
import org.fog.entities.Tuple;
import org.fog.placement.Controller;
import org.fog.placement.ModuleMapping;
import org.fog.placement.ModulePlacementEdgewards;
import org.fog.placement.ModulePlacementMapping;
import org.fog.policy.AppModuleAllocationPolicy;
import org.fog.scheduler.StreamOperatorScheduler;
import org.fog.utils.FogLinearPowerModel;
import org.fog.utils.FogUtils;
import org.fog.utils.TimeKeeper;
import org.fog.utils.distribution.DeterministicDistribution;


public class FaunaMonitoring {
    // Initializing static empty lists for different types of devices in a fog computing system:
    // FogDevice, Sensor, and Actuator.
    static List<FogDevice> fogDevices = new ArrayList<FogDevice>();
    static List<Sensor> sensors = new ArrayList<Sensor>();
    static List<Actuator> actuators = new ArrayList<Actuator>();

    // This code initializes three static integer variables representing the number of proxy servers,
    // fog nodes per proxy server, and cameras per fog node in a fog computing system.
    //static int numOfHubs = 1;                   // For Hosting Docker Hub
    // APU
    //static int numOfNodesPerHub = 1;            // Nodes Per Proxy Server
    static int numOfCamerasPerNode = 1;         // Cameras per Node               <-- Actuator
    static int numOfESP8266PerNode = 1;             // ESP per Node (Raspberry Pi)
    // MSU
    static int numofESPforMSPerESP8266 = 3;
    static int numOfMSPerESP = 4;    // Motion Sensors Per ESP         <-- Sensor

    private static boolean CLOUD = true;

    public static void main(String[] args) {
//        int numOfHubs = Integer.parseInt(args[0]);                   // For Hosting Docker Hub
//        int numOfNodesPerHub =  Integer.parseInt(args[1]);            // Nodes Per Proxy Server
//        int hubDeviceType =  Integer.parseInt(args[2]);               // Hub Device Type (Hub)
//        int fogDeviceType =  Integer.parseInt(args[3]);               // Node Device Type (APU)

        int numOfHubs = 4;                   // For Hosting Docker Hub
        int numOfNodesPerHub =  4;            // Nodes Per Proxy Server
        int hubDeviceType =  1;               // Hub Device Type (Hub)
        int fogDeviceType =  1;               // Node Device Type (APU)*/

        Log.printLine("Starting Simulation for Fauna Monitoring...");
        Log.printLine("Hubs: "+numOfHubs+" "+"APUs: "+numOfNodesPerHub);

        try {
            Log.disable();
            int num_user = 1;
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;
            CloudSim.init(num_user, calendar, trace_flag);

            /* *
             * Fauna Monitoring Application
             * */
            String appID = "FMS";
            FogBroker broker = new FogBroker("broker");
            Application application = createApplication(appID, broker.getId());
            application.setUserId(broker.getId());

            // Creating Fog Devices
            createFogDevices(broker.getId(), appID, numOfHubs, numOfNodesPerHub, hubDeviceType, fogDeviceType);

            // Controller and Module Mapping
            Controller controller = null;
            ModuleMapping moduleMapping = ModuleMapping.createModuleMapping();

            if(CLOUD){
                for(FogDevice device: fogDevices){
                    /*if(device.getName().startsWith("MS")){
                        moduleMapping.addModuleToDevice("Motion-Capture", device.getName());
                    }*/
                    if(device.getName().startsWith("espMS")){
                        moduleMapping.addModuleToDevice("Motion-Capture", device.getName());
                        moduleMapping.addModuleToDevice("MSD-ESP-Sender", device.getName());
                    }
                    if(device.getName().startsWith("esp8266")){
                        moduleMapping.addModuleToDevice("MSD-ESPNOW-MQTT-Forwarder", device.getName());
                        System.out.println(device.getName());
                    }
                    if(device.getName().startsWith("fogNode")){
                        moduleMapping.addModuleToDevice("Servo-Image-Module", device.getName());
                        moduleMapping.addModuleToDevice("Picture-Capture", device.getName());
                        moduleMapping.addModuleToDevice("Image-Processing", device.getName());
                        moduleMapping.addModuleToDevice("Image-Producer", device.getName());
                        System.out.println(device.getName());
                    }
                    /*if(device.getName().startsWith("cameraDevice")){
                        moduleMapping.addModuleToDevice("Picture-Capture", device.getName());
                        System.out.println(device.getName());
                    }*/
                    if(device.getName().startsWith("hub")){
                        moduleMapping.addModuleToDevice("Image-Broker", device.getName());
                        moduleMapping.addModuleToDevice("Image-Consumer", device.getName());
                        System.out.println(device.getName());
                    }
                }
            /*if(CLOUD){
                for(FogDevice device: fogDevices) {
                    moduleMapping.addModuleToDevice("Motion-Capture", "cloud");
                    moduleMapping.addModuleToDevice("MSD-ESPNOW-MQTT-Forwarder", "cloud");
                    moduleMapping.addModuleToDevice("Servo-Image-Module", "cloud");
                    moduleMapping.addModuleToDevice("Image-Processing", "cloud");
                    moduleMapping.addModuleToDevice("Image-Producer", "cloud");
                    moduleMapping.addModuleToDevice("Picture-Capture", "cloud");
                    moduleMapping.addModuleToDevice("Image-Broker", "cloud");
                    moduleMapping.addModuleToDevice("Image-Consumer", "cloud");
                }*/
            }else{
                for(FogDevice device: fogDevices){
//                    if(device.getName().startsWith("MS")){
//                        moduleMapping.addModuleToDevice("Motion-Capture", device.getName());
//                        System.out.println(device.getName());
//                    }
                    if(device.getName().startsWith("espMS")){
                        moduleMapping.addModuleToDevice("Motion-Capture", device.getName());
                        moduleMapping.addModuleToDevice("MSD-ESP-Sender", device.getName());
                        System.out.println(device.getName());
                    }
                    if(device.getName().startsWith("esp8266")){
                        moduleMapping.addModuleToDevice("MSD-ESPNOW-MQTT-Forwarder", device.getName());
                        System.out.println(device.getName());
                    }
                    if(device.getName().startsWith("fogNode")){
                        moduleMapping.addModuleToDevice("Servo-Image-Module", device.getName());
                        moduleMapping.addModuleToDevice("Image-Processing", device.getName());
                        moduleMapping.addModuleToDevice("Image-Producer", device.getName());

                        System.out.println(device.getName());
                    }
                    if(device.getName().startsWith("cameraDevice")){
                        moduleMapping.addModuleToDevice("Picture-Capture", device.getName());
                        System.out.println(device.getName());
                    }
                    if(device.getName().startsWith("hub")){
                        moduleMapping.addModuleToDevice("Image-Broker", device.getName());
                        moduleMapping.addModuleToDevice("Image-Consumer", device.getName());
                        System.out.println(device.getName());
                    }
                }
            }

            System.out.println("Module Mapping: " + moduleMapping.getModuleMapping());
            System.out.println("Application: " +application.getEdgeMap());


            controller = new Controller("master-controller", fogDevices, sensors, actuators);
            controller.submitApplication(application,
                    (CLOUD)?(new ModulePlacementMapping(fogDevices, application, moduleMapping))
                            :(new ModulePlacementEdgewards(fogDevices, sensors, actuators, application, moduleMapping))
            );

            TimeKeeper.getInstance().setSimulationStartTime(
                    Calendar.getInstance().getTimeInMillis()
            );

            CloudSim.startSimulation();
            CloudSim.stopSimulation();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Unexpected Error!");
        }
    }

    private static void createFogDevices(int userID, String appID, int numOfHubs, int numOfNodesPerHub, int hubDeviceType, int fogDeviceType){
        FogDevice cloud = createFogDevice("cloud", 44800, 40000, 100, 10000, 0, 0.01, 16*103, 16*83.25);
        cloud.setParentId(-1);
        fogDevices.add(cloud);

        for(int i = 0; i < numOfHubs; i++){
            FogDevice hub = addHubs(i+"", userID, appID, cloud.getId(), numOfNodesPerHub, hubDeviceType, fogDeviceType);
            hub.setUplinkLatency(100);
            fogDevices.add(hub);
        }
    }

    private static FogDevice addHubs(String id, int userID, String appID, int parentID, int numOfNodesPerHub, int hubDeviceType, int fogDeviceType){
        FogDevice hub;
        switch (hubDeviceType){
            case 1:
                // Raspberry Pi 2
                hub = createFogDevice("hub-"+id, 300, 512, 25, 10000, 1, 0.0, 3.7, 1.6);
                break;
            case 2:
                // Raspberry Pi 3
                hub = createFogDevice("hub-"+id, 460, 1024, 50, 10000, 1, 0.0, 5.9, 2.1);
                break;
            case 3:
                // Raspberry Pi 3 B+
                hub = createFogDevice("hub-"+id, 527, 1024, 100, 10000, 1, 0.0, 6.4, 2.9);
                break;
            case 4:
                // Raspberry Pi 3 A+
                hub = createFogDevice("hub-"+id, 536, 512, 100, 10000, 1, 0.0, 5.4, 1.2);
                break;
            case 5:
                // Raspberry Pi 4B (4096)
                hub = createFogDevice("hub-"+id, 2037, 4096, 115, 10000, 1, 0.0, 7.6, 3.4);
                break;
            case 6:
                // Raspberry Pi 4B (8192)
                hub = createFogDevice("hub-"+id, 2037, 8192, 115, 10000, 1, 0.0, 7.6, 3.4);
                break;
            case 7:
                // LattePanda
                hub = createFogDevice("hub-"+id, 2037, 4096, 115, 10000, 1, 0.0, 7.6, 3.4);
                break;
            case 8:
                // Jetson
                hub = createFogDevice("hub-"+id, 36388, 16368, 600, 10000, 1, 0.0, 30, 10);
                break;
            case 9:
                // NUC11
                hub = createFogDevice("hub-"+id, 44000, 65536, 2400, 10000, 1, 0.0, 40, 10);
                break;
            case 10:
                // PN50
                hub = createFogDevice("hub-"+id, 20000, 32768, 2400, 10000, 1, 0.0, 60, 10);
                break;
            default:
                System.out.println("Please provide proper hub device! Using Default Raspberry Pi 3 A+");
                hub = createFogDevice("hub-"+id, 536, 512, 100, 10000, 1, 0.0, 5.4, 1.2);
        }
        hub.setParentId(parentID);
        for(int i = 0; i < numOfNodesPerHub; i++){
            FogDevice node = addNode(id+"-"+i, userID, appID, hub.getId(), fogDeviceType);
            node.setUplinkLatency(100);
            fogDevices.add(node);
        }
        return hub;
    }

    // APUs
    private static FogDevice addNode(String id, int userID, String appID, int parentID, int fogDeviceType){
        FogDevice node;
        switch (fogDeviceType){
            case 1:
                // Raspberry Pi 2
                node = createFogDevice("fogNode-"+id, 300, 512, 25, 10000, 2, 0.0, 3.7, 1.6);
                break;
            case 2:
                // Raspberry Pi 3
                node = createFogDevice("fogNode-"+id, 460, 1024, 50, 10000, 2, 0.0, 5.9, 2.1);
                break;
            case 3:
                // Raspberry Pi 3 B+
                node = createFogDevice("fogNode-"+id, 527, 1024, 100, 10000, 2, 0.0, 6.4, 2.9);
                break;
            case 4:
                // Raspberry Pi 3 A+
                node = createFogDevice("fogNode-"+id, 536, 512, 100, 10000, 2, 0.0, 5.4, 1.2);
                break;
            case 5:
                // Raspberry Pi 4B (4096)
                node = createFogDevice("fogNode-"+id, 2037, 4096, 115, 10000, 2, 0.0, 7.6, 3.4);
                break;
            case 6:
                // Raspberry Pi 4B (8192)
                node = createFogDevice("fogNode-"+id, 2037, 8192, 115, 10000, 2, 0.0, 7.6, 3.4);
                break;
            case 7:
                // LattePanda
                node = createFogDevice("fogNode-"+id, 2037, 4096, 115, 10000, 2, 0.0, 7.6, 3.4);
                break;
            case 8:
                // Jetson
                node = createFogDevice("fogNode-"+id, 36388, 16368, 600, 10000, 2, 0.0, 30, 10);
                break;
            case 9:
                // NUC11
                node = createFogDevice("fogNode-"+id, 44000, 65536, 2400, 10000, 2, 0.0, 40, 10);
                break;
            case 10:
                // PN50
                node = createFogDevice("fogNode-"+id, 20000, 32768, 2400, 10000, 2, 0.0, 60, 10);
                break;
            default:
                System.out.println("Please provide proper node device! Using Default Raspberry Pi 3 A+");
                node = createFogDevice("hub-"+id, 536, 512, 100, 10000, 2, 0.0, 5.4, 1.2);
        }
        node.setParentId(parentID);

        for(int i = 0; i < numOfCamerasPerNode; i++){
            FogDevice camera = addCamera(id+"-"+i, userID, appID, node.getId());
            camera.setUplinkLatency(100);
            fogDevices.add(camera);
        }

        for(int i = 0; i < numOfESP8266PerNode; i++){
            FogDevice esp8266 = addEsp8266(id+"-"+i, userID, appID, node.getId());
            esp8266.setUplinkLatency(100);
            fogDevices.add(esp8266);
        }
        return node;
    }

    private static FogDevice addCamera(String id, int userID, String appID, int parentID){
        //FogDevice camera = createFogDevice("cameraDevice-"+id, 30000, 80000, 10000, 10000, 2, 0.0, 10, 5);
        FogDevice camera = createFogDevice("cameraDevice-"+id, 1, 1, 1, 1, 2, 0.0, 6.5, 0.0016);
        camera.setParentId(parentID);

        Sensor sensor = new Sensor("camera-"+id, "IMAGE", userID, appID, new DeterministicDistribution(5));
        sensors.add(sensor);
        sensor.setLatency(1.0);
        sensor.setGatewayDeviceId(camera.getId());

        Actuator actuator = new Actuator("camera-"+id, userID, appID, "IMAGE");
        //actuator.setGatewayDeviceId(camera.getId());
        actuator.setGatewayDeviceId(parentID);
        actuator.setLatency(1.0);
        actuators.add(actuator);

        return camera;

    }

    private static FogDevice addEsp8266(String id, int userID, String appID, int parentID){
        //FogDevice esp8266 = createFogDevice("esp8266-"+id, 30000, 80000, 10000, 10000, 2, 0.0, 100, 50);
        FogDevice esp8266 = createFogDevice("esp8266-"+id, 160, 4, 10, 5, 2, 0.0, 0.41, 0.0016);
        esp8266.setParentId(parentID);

        for(int i = 0; i < numofESPforMSPerESP8266; i++){
            FogDevice espMS = addEspMS(id+"-"+i, userID, appID, esp8266.getId());
            espMS.setUplinkLatency(100);
            fogDevices.add(espMS);
        }
        return esp8266;
    }

    // MSUs
    private static FogDevice addEspMS(String id, int userID, String appID, int parentID){
        //FogDevice espMS = createFogDevice("espMS-"+id, 30000, 80000, 10000, 10000, 3, 0.0, 10, 5);
        FogDevice espMS = createFogDevice("espMS-"+id, 160, 4, 10, 5, 3, 0.0, 0.41, 0.0016);
        espMS.setParentId(parentID);

        for(int i = 0; i < numOfMSPerESP; i++){
            FogDevice MS = addMS(id+"-"+i, userID, appID, espMS.getId());
            MS.setUplinkLatency(1);
            fogDevices.add(MS);
        }
        return espMS;
    }

    private static FogDevice addMS(String id, int userID, String appID, int parentID){
        //FogDevice MS = createFogDevice("MS-"+id, 30000, 80000, 10000, 10000, 3, 0.0, 10, 5);
        FogDevice MS = createFogDevice("MS-"+id, 160, 4, 10, 5, 4, 0.0, 0.41, 0.0016);
        MS.setParentId(parentID);
        Sensor sensor = new Sensor("motionSensor-"+id, "MSD", userID, appID, new DeterministicDistribution(5));
        sensors.add(sensor);
        sensor.setLatency(1.0);
        sensor.setGatewayDeviceId(MS.getId());
        return MS;
    }

    private static FogDevice createFogDevice(String nodeName, long mips, int ram, long upBw, long downBw, int level, double ratePerMips, double busyPower, double idlePower) {
        List<Pe> peList = new ArrayList<Pe>();

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerOverbooking(mips))); // need to store Pe id and MIPS Rating

        int hostId = FogUtils.generateEntityId();
        long storage = 1000000; // host storage
        int bw = 10000;

        PowerHost host = new PowerHost(
                hostId,
                new RamProvisionerSimple(ram),
                new BwProvisionerOverbooking(bw),
                storage,
                peList,
                new StreamOperatorScheduler(peList),
                new FogLinearPowerModel(busyPower, idlePower)
        );
        List<Host> hostList = new ArrayList<Host>();
        hostList.add(host);
        String arch = "x86"; // system architecture
        String os = "Linux"; // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
        // devices by now
        FogDeviceCharacteristics characteristics = new FogDeviceCharacteristics(
                arch, os, vmm, host, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);
        FogDevice fogdevice = null;
        try {
            fogdevice = new FogDevice(nodeName, characteristics,
                    new AppModuleAllocationPolicy(hostList), storageList, 10, upBw, downBw, 0, ratePerMips);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //assert fogdevice != null;
        fogdevice.setLevel(level);
        return fogdevice;
    }

    private static Application createApplication(String appID, int userID){
        Application application = Application.createApplication(appID, userID);

        /* *
         * Adding Modules
         * */
        // Motion Sensor connected with ESP at level 3
        application.addAppModule("Motion-Capture", 1);
        // ESP handler for MS at level 3
        application.addAppModule("MSD-ESP-Sender", 1);

        // Fog node's ESP8266 at Level 2
        application.addAppModule("MSD-ESPNOW-MQTT-Forwarder", 1);
        // Fog node at Level 2
        application.addAppModule("Servo-Image-Module", 1);
        // Fog Node's Camera at Level 2
        application.addAppModule("Picture-Capture", 5);
        // Image Processing and Image Producer on Fog node at Level 2
        application.addAppModule("Image-Processing", 5);
        application.addAppModule("Image-Producer", 5);

        // Image Broker and Image Consumer at Hub at Level 1
        application.addAppModule("Image-Broker", 5);
        application.addAppModule("Image-Consumer", 5);

        /* *
         * Adding Edges
         * */
        application.addAppEdge("MSD", "Motion-Capture", 100 ,100, "MSD", Tuple.UP, AppEdge.SENSOR);
        application.addAppEdge("Motion-Capture", "MSD-ESP-Sender", 100, 100, "MSD", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("MSD-ESP-Sender", "MSD-ESPNOW-MQTT-Forwarder", 100, 100, "MSD", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("MSD-ESPNOW-MQTT-Forwarder", "Servo-Image-Module", 100, 100, "MSD", Tuple.UP, AppEdge.MODULE);
        //application.addAppEdge("Servo-Image-Module", "Picture-Capture", 500, 500, "TASK-1", Tuple.DOWN, AppEdge.MODULE);

        //////////////
        application.addAppEdge("IMAGE", "Picture-Capture", 500, 500, "IMAGE", Tuple.UP, AppEdge.SENSOR);
        application.addAppEdge("Servo-Image-Module", "Picture-Capture", 500, 500, "TASK-1", Tuple.DOWN, AppEdge.MODULE);
        application.addAppEdge("Picture-Capture", "Servo-Image-Module", 500, 500, "IMAGE", Tuple.UP, AppEdge.MODULE);
        //////////////

        //application.addAppEdge("Picture-Capture", "Servo-Image-Module", 500, 500, "IMAGE", Tuple.DOWN, AppEdge.MODULE);
        application.addAppEdge("Servo-Image-Module", "Image-Processing", 500, 500, "IMAGE", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("Image-Processing", "Image-Producer", 500, 500, "IMAGE", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("Image-Producer", "Image-Broker", 500, 500, "KAFKA-IMAGE", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("Image-Broker", "Image-Consumer", 500, 500, "KAFKA-IMAGE", Tuple.DOWN, AppEdge.MODULE);

        /* *
         * Tuple Mapping
         * */
        application.addTupleMapping("Motion-Capture", "MSD", "MSD", new FractionalSelectivity(1.0));
        application.addTupleMapping("MSD-ESP-Sender", "MSD", "MSD", new FractionalSelectivity(1.0));
        application.addTupleMapping("MSD-ESPNOW-MQTT-Forwarder", "MSD", "MSD", new FractionalSelectivity(1.0));
        application.addTupleMapping("Servo-Image-Module", "MSD", "TASK-1", new FractionalSelectivity(1.0));

        //////////////
        application.addTupleMapping("Picture-Capture", "IMAGE", "IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Picture-Capture", "TASK-1", "IMAGE", new FractionalSelectivity(1.0));
        //////////////

        //application.addTupleMapping("Picture-Capture", "TASK-1", "IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Servo-Image-Module", "IMAGE", "IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Image-Processing", "IMAGE", "IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Image-Producer", "IMAGE", "KAFKA-IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Image-Broker", "KAFKA-IMAGE", "KAFKA-IMAGE", new FractionalSelectivity(1.0));
        application.addTupleMapping("Image-Consumer", "KAFKA-IMAGE", "KAFKA-IMAGE", new FractionalSelectivity(1.0));

        /* *
         * App Loop
         * */
        final AppLoop loop = new AppLoop(new ArrayList<String>(){{
            //add("MSD");
            add("Motion-Capture");add("MSD-ESP-Sender");add("MSD-ESPNOW-MQTT-Forwarder");add("Servo-Image-Module");
            //add("TASK-1");
            add("Picture-Capture");
            //add("IMAGE");
            add("Servo-Image-Module");add("Image-Processing");add("Image-Producer");
            add("Image-Broker");
            add("Image-Consumer");
        }});
        List<AppLoop> loops = new ArrayList<AppLoop>(){{
            add(loop);
        }};
        System.out.println("Get Edge Map : "+application.getEdgeMap().values());
        application.setLoops(loops);

        return application;
    }
}
