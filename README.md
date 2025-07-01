# iFogSim-Simulations-for-Fog-Based-Surviliance-Architecture
Simulations are crucial for analyzing device performance and identifying energy-efficient, low-latency options in IoT architecture design. We used the iFogSim simulation tool to optimize our fog-based IoT architecture and evaluate the performance of different devices and their interactions. Our evaluation included Raspberry Pi models and devices such as NVIDIA Jetson Xavier NX2, Intel NUC 11 Performance Kit, LattePanda Delta, and ASUS PN50 Barebone Mini PC. By adjusting parameters such as MIPS, RAM, bandwidth, busy power, and idle power, we identified the most suitable devices for our use case and determined the most energy-efficient devices with the lowest latency. A generic input for a simulation task in iFogSim is as follows,
| Input Name | Input Type | Description |
| ---------- | ---------- | ----------- |
| MIPS | int | Million Instructions Per Second|
| RAM (MB) | int | Random Access Memory |
| Uplink Bandwidth (MB) | int/float | The upload speed of the network to which your device is connected |
| Downlink Bandwidth (MB) | int/float | The download speed of the network to which your device is connected |
| Level | int | Level of the Device in your fog-architecture (0 means Cloud) |
| Rate Per MIPS | int/float | Amount payable per MIPS |
| Busy Power (W) | float | Max power consumption of your device |
| Idle Power (W) | float | Min power consumption of your device|

The table below lists the specifications of Raspberry Pi models and the specifications of other devices.
| Devices | NodeMCU | RPi2 | RPi3 | RPi3B+ | RPi3A+ | RPi4B | Jetson NX | NUC 11 | LP Delta | PN50 |
| ------- | ------- | ---- | ---- | ------ | ------ | ----- | --------- | ------ | -------- | ---- |
| MIPS | 80 | 298.7 | 460.9 | 526.7 | 536.23 | 2037.3 | 36288 | 44000 | 3500 | 20000 |
| RAM | 4 | 512 | 1024 | 1024 | 512 | 4096/8192 | 16384 | 65536 | 4096 | 32768 | 
| UpBw | - | 24.4 | 49.2 | 97.6 | 93.7 | 114 | 600 | 2400 | 1000 | 2400 |
| DwBw | 2 | 17 | 17 | 62.45 | 62.45 | 62.45 | - | - | - | - |
| Busy Pw | 0.41 | 5.9 | 5.9 | 6.4 | 5.4 | 7.6 | 30 | 40 | 15 | 60 |
| Idle Pw | 0.0016 | 2.1 | 2.1 | 2.9 | 1.2 | 3.4 | 10 | 10 | 5 | 10 | 

## Arguments for the Simulation Jar File
The simulation jar file takes four arguments: Number of Hubs, Number of Nodes per Hub, Hub Device Type and Node Device Type. 

**Note:** A [simulation code](https://github.com/rsiyanwal/iFogSim-Simulations-for-Fog-Based-Surviliance-Architecture/blob/main/fogSimulation.java) has been added to the repo. You should put the file in the iFogSim project at the location `..\iFogSim\src\org\fog\test\perfeval\fogSimulation.java`. You should get an output similar to:
```
Starting Simulation for Fauna Monitoring...
Hubs: 4 APUs: 4
Get Edge Map : [AppEdge [source=Image-Processing, destination=Image-Producer, tupleType=IMAGE], AppEdge [source=Servo-Image-Module, destination=Picture-Capture, tupleType=TASK-1], AppEdge [source=Image-Broker, destination=Image-Consumer, tupleType=KAFKA-IMAGE], AppEdge [source=MSD-ESPNOW-MQTT-Forwarder, destination=Servo-Image-Module, tupleType=MSD]]
esp8266-0-0-0
fogNode-0-0
esp8266-0-1-0
fogNode-0-1
esp8266-0-2-0
fogNode-0-2
esp8266-0-3-0
fogNode-0-3
hub-0
esp8266-1-0-0
fogNode-1-0
esp8266-1-1-0
fogNode-1-1
esp8266-1-2-0
fogNode-1-2
esp8266-1-3-0
fogNode-1-3
hub-1
esp8266-2-0-0
fogNode-2-0
esp8266-2-1-0
fogNode-2-1
esp8266-2-2-0
fogNode-2-2
esp8266-2-3-0
fogNode-2-3
hub-2
esp8266-3-0-0
fogNode-3-0
esp8266-3-1-0
fogNode-3-1
esp8266-3-2-0
fogNode-3-2
esp8266-3-3-0
fogNode-3-3
hub-3
Module Mapping: {esp8266-2-3-0=[MSD-ESPNOW-MQTT-Forwarder], esp8266-2-1-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-0-2-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-0-2-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-1-0-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-3-3-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-1-0-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-0-2-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-1-0-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-3-3-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-3-3-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-0-1-0-0=[Motion-Capture, MSD-ESP-Sender], fogNode-1-1=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], esp8266-3-1-0=[MSD-ESPNOW-MQTT-Forwarder], fogNode-1-2=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], fogNode-3-0=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-0-1-0-2=[Motion-Capture, MSD-ESP-Sender], fogNode-1-3=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], fogNode-3-1=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], esp8266-3-3-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-0-1-0-1=[Motion-Capture, MSD-ESP-Sender], fogNode-3-2=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], fogNode-3-3=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-3-2-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-3-2-0-2=[Motion-Capture, MSD-ESP-Sender], fogNode-1-0=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-3-2-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-3-1-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-0-0-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-3-1-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-0-0-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-3-1-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-0-0-0-0=[Motion-Capture, MSD-ESP-Sender], esp8266-0-3-0=[MSD-ESPNOW-MQTT-Forwarder], hub-0=[Image-Broker, Image-Consumer], esp8266-0-1-0=[MSD-ESPNOW-MQTT-Forwarder], hub-1=[Image-Broker, Image-Consumer], hub-2=[Image-Broker, Image-Consumer], hub-3=[Image-Broker, Image-Consumer], espMS-3-0-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-3-0-0-2=[Motion-Capture, MSD-ESP-Sender], esp8266-1-3-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-2-3-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-2-3-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-2-3-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-2-2-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-2-2-0-1=[Motion-Capture, MSD-ESP-Sender], esp8266-1-1-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-2-2-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-3-0-0-0=[Motion-Capture, MSD-ESP-Sender], esp8266-2-2-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-2-1-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-2-1-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-2-1-0-2=[Motion-Capture, MSD-ESP-Sender], esp8266-2-0-0=[MSD-ESPNOW-MQTT-Forwarder], fogNode-0-2=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-1-3-0-0=[Motion-Capture, MSD-ESP-Sender], fogNode-2-0=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], fogNode-0-3=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-1-3-0-1=[Motion-Capture, MSD-ESP-Sender], fogNode-2-1=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], esp8266-3-0-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-1-3-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-2-0-0-0=[Motion-Capture, MSD-ESP-Sender], fogNode-2-2=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-2-0-0-1=[Motion-Capture, MSD-ESP-Sender], fogNode-2-3=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], esp8266-3-2-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-2-0-0-2=[Motion-Capture, MSD-ESP-Sender], fogNode-0-0=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], fogNode-0-1=[Servo-Image-Module, Picture-Capture, Image-Processing, Image-Producer], espMS-1-2-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-1-2-0-2=[Motion-Capture, MSD-ESP-Sender], esp8266-0-2-0=[MSD-ESPNOW-MQTT-Forwarder], esp8266-0-0-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-1-2-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-1-1-0-2=[Motion-Capture, MSD-ESP-Sender], espMS-0-3-0-0=[Motion-Capture, MSD-ESP-Sender], esp8266-1-2-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-0-3-0-1=[Motion-Capture, MSD-ESP-Sender], espMS-1-1-0-0=[Motion-Capture, MSD-ESP-Sender], espMS-0-3-0-2=[Motion-Capture, MSD-ESP-Sender], esp8266-1-0-0=[MSD-ESPNOW-MQTT-Forwarder], espMS-1-1-0-1=[Motion-Capture, MSD-ESP-Sender]}
Application: {IMAGE=AppEdge [source=Image-Processing, destination=Image-Producer, tupleType=IMAGE], TASK-1=AppEdge [source=Servo-Image-Module, destination=Picture-Capture, tupleType=TASK-1], KAFKA-IMAGE=AppEdge [source=Image-Broker, destination=Image-Consumer, tupleType=KAFKA-IMAGE], MSD=AppEdge [source=MSD-ESPNOW-MQTT-Forwarder, destination=Servo-Image-Module, tupleType=MSD]}
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-2-3-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-2-1-0
Creating Motion-Capture on device espMS-0-2-0-1
Creating MSD-ESP-Sender on device espMS-0-2-0-1
Creating Motion-Capture on device espMS-0-2-0-0
Creating MSD-ESP-Sender on device espMS-0-2-0-0
Creating Motion-Capture on device espMS-1-0-0-0
Creating MSD-ESP-Sender on device espMS-1-0-0-0
Creating Motion-Capture on device espMS-3-3-0-2
Creating MSD-ESP-Sender on device espMS-3-3-0-2
Creating Motion-Capture on device espMS-1-0-0-2
Creating MSD-ESP-Sender on device espMS-1-0-0-2
Creating Motion-Capture on device espMS-0-2-0-2
Creating MSD-ESP-Sender on device espMS-0-2-0-2
Creating Motion-Capture on device espMS-1-0-0-1
Creating MSD-ESP-Sender on device espMS-1-0-0-1
Creating Motion-Capture on device espMS-3-3-0-0
Creating MSD-ESP-Sender on device espMS-3-3-0-0
Creating Motion-Capture on device espMS-3-3-0-1
Creating MSD-ESP-Sender on device espMS-3-3-0-1
Creating Motion-Capture on device espMS-0-1-0-0
Creating MSD-ESP-Sender on device espMS-0-1-0-0
Creating Servo-Image-Module on device fogNode-1-1
Creating Picture-Capture on device fogNode-1-1
Creating Image-Processing on device fogNode-1-1
Creating Image-Producer on device fogNode-1-1
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-3-1-0
Creating Servo-Image-Module on device fogNode-1-2
Creating Picture-Capture on device fogNode-1-2
Creating Image-Processing on device fogNode-1-2
Creating Image-Producer on device fogNode-1-2
Creating Servo-Image-Module on device fogNode-3-0
Creating Picture-Capture on device fogNode-3-0
Creating Image-Processing on device fogNode-3-0
Creating Image-Producer on device fogNode-3-0
Creating Motion-Capture on device espMS-0-1-0-2
Creating MSD-ESP-Sender on device espMS-0-1-0-2
Creating Servo-Image-Module on device fogNode-1-3
Creating Picture-Capture on device fogNode-1-3
Creating Image-Processing on device fogNode-1-3
Creating Image-Producer on device fogNode-1-3
Creating Servo-Image-Module on device fogNode-3-1
Creating Picture-Capture on device fogNode-3-1
Creating Image-Processing on device fogNode-3-1
Creating Image-Producer on device fogNode-3-1
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-3-3-0
Creating Motion-Capture on device espMS-0-1-0-1
Creating MSD-ESP-Sender on device espMS-0-1-0-1
Creating Servo-Image-Module on device fogNode-3-2
Creating Picture-Capture on device fogNode-3-2
Creating Image-Processing on device fogNode-3-2
Creating Image-Producer on device fogNode-3-2
Creating Servo-Image-Module on device fogNode-3-3
Creating Picture-Capture on device fogNode-3-3
Creating Image-Processing on device fogNode-3-3
Creating Image-Producer on device fogNode-3-3
Creating Motion-Capture on device espMS-3-2-0-1
Creating MSD-ESP-Sender on device espMS-3-2-0-1
Creating Motion-Capture on device espMS-3-2-0-2
Creating MSD-ESP-Sender on device espMS-3-2-0-2
Creating Servo-Image-Module on device fogNode-1-0
Creating Picture-Capture on device fogNode-1-0
Creating Image-Processing on device fogNode-1-0
Creating Image-Producer on device fogNode-1-0
Creating Motion-Capture on device espMS-3-2-0-0
Creating MSD-ESP-Sender on device espMS-3-2-0-0
Creating Motion-Capture on device espMS-3-1-0-0
Creating MSD-ESP-Sender on device espMS-3-1-0-0
Creating Motion-Capture on device espMS-0-0-0-2
Creating MSD-ESP-Sender on device espMS-0-0-0-2
Creating Motion-Capture on device espMS-3-1-0-1
Creating MSD-ESP-Sender on device espMS-3-1-0-1
Creating Motion-Capture on device espMS-0-0-0-1
Creating MSD-ESP-Sender on device espMS-0-0-0-1
Creating Motion-Capture on device espMS-3-1-0-2
Creating MSD-ESP-Sender on device espMS-3-1-0-2
Creating Motion-Capture on device espMS-0-0-0-0
Creating MSD-ESP-Sender on device espMS-0-0-0-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-0-3-0
Creating Image-Broker on device hub-0
Creating Image-Consumer on device hub-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-0-1-0
Creating Image-Broker on device hub-1
Creating Image-Consumer on device hub-1
Creating Image-Broker on device hub-2
Creating Image-Consumer on device hub-2
Creating Image-Broker on device hub-3
Creating Image-Consumer on device hub-3
Creating Motion-Capture on device espMS-3-0-0-1
Creating MSD-ESP-Sender on device espMS-3-0-0-1
Creating Motion-Capture on device espMS-3-0-0-2
Creating MSD-ESP-Sender on device espMS-3-0-0-2
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-1-3-0
Creating Motion-Capture on device espMS-2-3-0-1
Creating MSD-ESP-Sender on device espMS-2-3-0-1
Creating Motion-Capture on device espMS-2-3-0-2
Creating MSD-ESP-Sender on device espMS-2-3-0-2
Creating Motion-Capture on device espMS-2-3-0-0
Creating MSD-ESP-Sender on device espMS-2-3-0-0
Creating Motion-Capture on device espMS-2-2-0-0
Creating MSD-ESP-Sender on device espMS-2-2-0-0
Creating Motion-Capture on device espMS-2-2-0-1
Creating MSD-ESP-Sender on device espMS-2-2-0-1
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-1-1-0
Creating Motion-Capture on device espMS-2-2-0-2
Creating MSD-ESP-Sender on device espMS-2-2-0-2
Creating Motion-Capture on device espMS-3-0-0-0
Creating MSD-ESP-Sender on device espMS-3-0-0-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-2-2-0
Creating Motion-Capture on device espMS-2-1-0-0
Creating MSD-ESP-Sender on device espMS-2-1-0-0
Creating Motion-Capture on device espMS-2-1-0-1
Creating MSD-ESP-Sender on device espMS-2-1-0-1
Creating Motion-Capture on device espMS-2-1-0-2
Creating MSD-ESP-Sender on device espMS-2-1-0-2
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-2-0-0
Creating Servo-Image-Module on device fogNode-0-2
Creating Picture-Capture on device fogNode-0-2
Creating Image-Processing on device fogNode-0-2
Creating Image-Producer on device fogNode-0-2
Creating Motion-Capture on device espMS-1-3-0-0
Creating MSD-ESP-Sender on device espMS-1-3-0-0
Creating Servo-Image-Module on device fogNode-2-0
Creating Picture-Capture on device fogNode-2-0
Creating Image-Processing on device fogNode-2-0
Creating Image-Producer on device fogNode-2-0
Creating Servo-Image-Module on device fogNode-0-3
Creating Picture-Capture on device fogNode-0-3
Creating Image-Processing on device fogNode-0-3
Creating Image-Producer on device fogNode-0-3
Creating Motion-Capture on device espMS-1-3-0-1
Creating MSD-ESP-Sender on device espMS-1-3-0-1
Creating Servo-Image-Module on device fogNode-2-1
Creating Picture-Capture on device fogNode-2-1
Creating Image-Processing on device fogNode-2-1
Creating Image-Producer on device fogNode-2-1
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-3-0-0
Creating Motion-Capture on device espMS-1-3-0-2
Creating MSD-ESP-Sender on device espMS-1-3-0-2
Creating Motion-Capture on device espMS-2-0-0-0
Creating MSD-ESP-Sender on device espMS-2-0-0-0
Creating Servo-Image-Module on device fogNode-2-2
Creating Picture-Capture on device fogNode-2-2
Creating Image-Processing on device fogNode-2-2
Creating Image-Producer on device fogNode-2-2
Creating Motion-Capture on device espMS-2-0-0-1
Creating MSD-ESP-Sender on device espMS-2-0-0-1
Creating Servo-Image-Module on device fogNode-2-3
Creating Picture-Capture on device fogNode-2-3
Creating Image-Processing on device fogNode-2-3
Creating Image-Producer on device fogNode-2-3
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-3-2-0
Creating Motion-Capture on device espMS-2-0-0-2
Creating MSD-ESP-Sender on device espMS-2-0-0-2
Creating Servo-Image-Module on device fogNode-0-0
Creating Picture-Capture on device fogNode-0-0
Creating Image-Processing on device fogNode-0-0
Creating Image-Producer on device fogNode-0-0
Creating Servo-Image-Module on device fogNode-0-1
Creating Picture-Capture on device fogNode-0-1
Creating Image-Processing on device fogNode-0-1
Creating Image-Producer on device fogNode-0-1
Creating Motion-Capture on device espMS-1-2-0-1
Creating MSD-ESP-Sender on device espMS-1-2-0-1
Creating Motion-Capture on device espMS-1-2-0-2
Creating MSD-ESP-Sender on device espMS-1-2-0-2
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-0-2-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-0-0-0
Creating Motion-Capture on device espMS-1-2-0-0
Creating MSD-ESP-Sender on device espMS-1-2-0-0
Creating Motion-Capture on device espMS-1-1-0-2
Creating MSD-ESP-Sender on device espMS-1-1-0-2
Creating Motion-Capture on device espMS-0-3-0-0
Creating MSD-ESP-Sender on device espMS-0-3-0-0
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-1-2-0
Creating Motion-Capture on device espMS-0-3-0-1
Creating MSD-ESP-Sender on device espMS-0-3-0-1
Creating Motion-Capture on device espMS-1-1-0-0
Creating MSD-ESP-Sender on device espMS-1-1-0-0
Creating Motion-Capture on device espMS-0-3-0-2
Creating MSD-ESP-Sender on device espMS-0-3-0-2
Creating MSD-ESPNOW-MQTT-Forwarder on device esp8266-1-0-0
Creating Motion-Capture on device espMS-1-1-0-1
Creating MSD-ESP-Sender on device espMS-1-1-0-1
0.0 Submitted application FMS
=========================================
============== RESULTS ==================
=========================================
EXECUTION TIME : 13803
=========================================
APPLICATION LOOP DELAYS
=========================================
[Motion-Capture, MSD-ESP-Sender, MSD-ESPNOW-MQTT-Forwarder, Servo-Image-Module, Picture-Capture, Servo-Image-Module, Image-Processing, Image-Producer, Image-Broker, Image-Consumer] ---> 1121.4399572649631
=========================================
TUPLE CPU EXECUTION DELAY
=========================================
IMAGE ---> 6.085714285714218
TASK-1 ---> 6.799989827621628
KAFKA-IMAGE ---> 6.7666666666666515
MSD ---> 5.199960327148256
=========================================
cloud : Energy Consumed = 2664000.0
cameraDevice-0-0-0 : Energy Consumed = 3.2000000000000006
MS-0-0-0-0-0 : Energy Consumed = 3.2000000000000006
MS-0-0-0-0-1 : Energy Consumed = 3.2000000000000006
MS-0-0-0-0-2 : Energy Consumed = 3.2000000000000006
MS-0-0-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-0-0-0-0 : Energy Consumed = 441.6173999999938
MS-0-0-0-1-0 : Energy Consumed = 3.2000000000000006
MS-0-0-0-1-1 : Energy Consumed = 3.2000000000000006
MS-0-0-0-1-2 : Energy Consumed = 3.2000000000000006
MS-0-0-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-0-0-0-1 : Energy Consumed = 441.6173999999938
MS-0-0-0-2-0 : Energy Consumed = 3.2000000000000006
MS-0-0-0-2-1 : Energy Consumed = 3.2000000000000006
MS-0-0-0-2-2 : Energy Consumed = 3.2000000000000006
MS-0-0-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-0-0-0-2 : Energy Consumed = 441.6173999999938
esp8266-0-0-0 : Energy Consumed = 816.7225900000026
fogNode-0-0 : Energy Consumed = 7375.50000000003
cameraDevice-0-1-0 : Energy Consumed = 3.2000000000000006
MS-0-1-0-0-0 : Energy Consumed = 3.2000000000000006
MS-0-1-0-0-1 : Energy Consumed = 3.2000000000000006
MS-0-1-0-0-2 : Energy Consumed = 3.2000000000000006
MS-0-1-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-0-1-0-0 : Energy Consumed = 441.6173999999938
MS-0-1-0-1-0 : Energy Consumed = 3.2000000000000006
MS-0-1-0-1-1 : Energy Consumed = 3.2000000000000006
MS-0-1-0-1-2 : Energy Consumed = 3.2000000000000006
MS-0-1-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-0-1-0-1 : Energy Consumed = 441.6173999999938
MS-0-1-0-2-0 : Energy Consumed = 3.2000000000000006
MS-0-1-0-2-1 : Energy Consumed = 3.2000000000000006
MS-0-1-0-2-2 : Energy Consumed = 3.2000000000000006
MS-0-1-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-0-1-0-2 : Energy Consumed = 441.6173999999938
esp8266-0-1-0 : Energy Consumed = 816.7225900000026
fogNode-0-1 : Energy Consumed = 7375.50000000003
cameraDevice-0-2-0 : Energy Consumed = 3.2000000000000006
MS-0-2-0-0-0 : Energy Consumed = 3.2000000000000006
MS-0-2-0-0-1 : Energy Consumed = 3.2000000000000006
MS-0-2-0-0-2 : Energy Consumed = 3.2000000000000006
MS-0-2-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-0-2-0-0 : Energy Consumed = 441.6173999999938
MS-0-2-0-1-0 : Energy Consumed = 3.2000000000000006
MS-0-2-0-1-1 : Energy Consumed = 3.2000000000000006
MS-0-2-0-1-2 : Energy Consumed = 3.2000000000000006
MS-0-2-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-0-2-0-1 : Energy Consumed = 441.6173999999938
MS-0-2-0-2-0 : Energy Consumed = 3.2000000000000006
MS-0-2-0-2-1 : Energy Consumed = 3.2000000000000006
MS-0-2-0-2-2 : Energy Consumed = 3.2000000000000006
MS-0-2-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-0-2-0-2 : Energy Consumed = 441.6173999999938
esp8266-0-2-0 : Energy Consumed = 816.7225900000026
fogNode-0-2 : Energy Consumed = 7375.50000000003
cameraDevice-0-3-0 : Energy Consumed = 3.2000000000000006
MS-0-3-0-0-0 : Energy Consumed = 3.2000000000000006
MS-0-3-0-0-1 : Energy Consumed = 3.2000000000000006
MS-0-3-0-0-2 : Energy Consumed = 3.2000000000000006
MS-0-3-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-0-3-0-0 : Energy Consumed = 441.6173999999938
MS-0-3-0-1-0 : Energy Consumed = 3.2000000000000006
MS-0-3-0-1-1 : Energy Consumed = 3.2000000000000006
MS-0-3-0-1-2 : Energy Consumed = 3.2000000000000006
MS-0-3-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-0-3-0-1 : Energy Consumed = 441.6173999999938
MS-0-3-0-2-0 : Energy Consumed = 3.2000000000000006
MS-0-3-0-2-1 : Energy Consumed = 3.2000000000000006
MS-0-3-0-2-2 : Energy Consumed = 3.2000000000000006
MS-0-3-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-0-3-0-2 : Energy Consumed = 441.6173999999938
esp8266-0-3-0 : Energy Consumed = 816.7225900000026
fogNode-0-3 : Energy Consumed = 7375.50000000003
hub-0 : Energy Consumed = 6322.209999999976
cameraDevice-1-0-0 : Energy Consumed = 3.2000000000000006
MS-1-0-0-0-0 : Energy Consumed = 3.2000000000000006
MS-1-0-0-0-1 : Energy Consumed = 3.2000000000000006
MS-1-0-0-0-2 : Energy Consumed = 3.2000000000000006
MS-1-0-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-1-0-0-0 : Energy Consumed = 441.6173999999938
MS-1-0-0-1-0 : Energy Consumed = 3.2000000000000006
MS-1-0-0-1-1 : Energy Consumed = 3.2000000000000006
MS-1-0-0-1-2 : Energy Consumed = 3.2000000000000006
MS-1-0-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-1-0-0-1 : Energy Consumed = 441.6173999999938
MS-1-0-0-2-0 : Energy Consumed = 3.2000000000000006
MS-1-0-0-2-1 : Energy Consumed = 3.2000000000000006
MS-1-0-0-2-2 : Energy Consumed = 3.2000000000000006
MS-1-0-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-1-0-0-2 : Energy Consumed = 441.6173999999938
esp8266-1-0-0 : Energy Consumed = 816.7225900000026
fogNode-1-0 : Energy Consumed = 7375.50000000003
cameraDevice-1-1-0 : Energy Consumed = 3.2000000000000006
MS-1-1-0-0-0 : Energy Consumed = 3.2000000000000006
MS-1-1-0-0-1 : Energy Consumed = 3.2000000000000006
MS-1-1-0-0-2 : Energy Consumed = 3.2000000000000006
MS-1-1-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-1-1-0-0 : Energy Consumed = 441.6173999999938
MS-1-1-0-1-0 : Energy Consumed = 3.2000000000000006
MS-1-1-0-1-1 : Energy Consumed = 3.2000000000000006
MS-1-1-0-1-2 : Energy Consumed = 3.2000000000000006
MS-1-1-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-1-1-0-1 : Energy Consumed = 441.6173999999938
MS-1-1-0-2-0 : Energy Consumed = 3.2000000000000006
MS-1-1-0-2-1 : Energy Consumed = 3.2000000000000006
MS-1-1-0-2-2 : Energy Consumed = 3.2000000000000006
MS-1-1-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-1-1-0-2 : Energy Consumed = 441.6173999999938
esp8266-1-1-0 : Energy Consumed = 816.7225900000026
fogNode-1-1 : Energy Consumed = 7375.50000000003
cameraDevice-1-2-0 : Energy Consumed = 3.2000000000000006
MS-1-2-0-0-0 : Energy Consumed = 3.2000000000000006
MS-1-2-0-0-1 : Energy Consumed = 3.2000000000000006
MS-1-2-0-0-2 : Energy Consumed = 3.2000000000000006
MS-1-2-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-1-2-0-0 : Energy Consumed = 441.6173999999938
MS-1-2-0-1-0 : Energy Consumed = 3.2000000000000006
MS-1-2-0-1-1 : Energy Consumed = 3.2000000000000006
MS-1-2-0-1-2 : Energy Consumed = 3.2000000000000006
MS-1-2-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-1-2-0-1 : Energy Consumed = 441.6173999999938
MS-1-2-0-2-0 : Energy Consumed = 3.2000000000000006
MS-1-2-0-2-1 : Energy Consumed = 3.2000000000000006
MS-1-2-0-2-2 : Energy Consumed = 3.2000000000000006
MS-1-2-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-1-2-0-2 : Energy Consumed = 441.6173999999938
esp8266-1-2-0 : Energy Consumed = 816.7225900000026
fogNode-1-2 : Energy Consumed = 7375.50000000003
cameraDevice-1-3-0 : Energy Consumed = 3.2000000000000006
MS-1-3-0-0-0 : Energy Consumed = 3.2000000000000006
MS-1-3-0-0-1 : Energy Consumed = 3.2000000000000006
MS-1-3-0-0-2 : Energy Consumed = 3.2000000000000006
MS-1-3-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-1-3-0-0 : Energy Consumed = 441.6173999999938
MS-1-3-0-1-0 : Energy Consumed = 3.2000000000000006
MS-1-3-0-1-1 : Energy Consumed = 3.2000000000000006
MS-1-3-0-1-2 : Energy Consumed = 3.2000000000000006
MS-1-3-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-1-3-0-1 : Energy Consumed = 441.6173999999938
MS-1-3-0-2-0 : Energy Consumed = 3.2000000000000006
MS-1-3-0-2-1 : Energy Consumed = 3.2000000000000006
MS-1-3-0-2-2 : Energy Consumed = 3.2000000000000006
MS-1-3-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-1-3-0-2 : Energy Consumed = 441.6173999999938
esp8266-1-3-0 : Energy Consumed = 816.7225900000026
fogNode-1-3 : Energy Consumed = 7375.50000000003
hub-1 : Energy Consumed = 6322.209999999976
cameraDevice-2-0-0 : Energy Consumed = 3.2000000000000006
MS-2-0-0-0-0 : Energy Consumed = 3.2000000000000006
MS-2-0-0-0-1 : Energy Consumed = 3.2000000000000006
MS-2-0-0-0-2 : Energy Consumed = 3.2000000000000006
MS-2-0-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-2-0-0-0 : Energy Consumed = 441.6173999999938
MS-2-0-0-1-0 : Energy Consumed = 3.2000000000000006
MS-2-0-0-1-1 : Energy Consumed = 3.2000000000000006
MS-2-0-0-1-2 : Energy Consumed = 3.2000000000000006
MS-2-0-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-2-0-0-1 : Energy Consumed = 441.6173999999938
MS-2-0-0-2-0 : Energy Consumed = 3.2000000000000006
MS-2-0-0-2-1 : Energy Consumed = 3.2000000000000006
MS-2-0-0-2-2 : Energy Consumed = 3.2000000000000006
MS-2-0-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-2-0-0-2 : Energy Consumed = 441.6173999999938
esp8266-2-0-0 : Energy Consumed = 816.7225900000026
fogNode-2-0 : Energy Consumed = 7375.50000000003
cameraDevice-2-1-0 : Energy Consumed = 3.2000000000000006
MS-2-1-0-0-0 : Energy Consumed = 3.2000000000000006
MS-2-1-0-0-1 : Energy Consumed = 3.2000000000000006
MS-2-1-0-0-2 : Energy Consumed = 3.2000000000000006
MS-2-1-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-2-1-0-0 : Energy Consumed = 441.6173999999938
MS-2-1-0-1-0 : Energy Consumed = 3.2000000000000006
MS-2-1-0-1-1 : Energy Consumed = 3.2000000000000006
MS-2-1-0-1-2 : Energy Consumed = 3.2000000000000006
MS-2-1-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-2-1-0-1 : Energy Consumed = 441.6173999999938
MS-2-1-0-2-0 : Energy Consumed = 3.2000000000000006
MS-2-1-0-2-1 : Energy Consumed = 3.2000000000000006
MS-2-1-0-2-2 : Energy Consumed = 3.2000000000000006
MS-2-1-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-2-1-0-2 : Energy Consumed = 441.6173999999938
esp8266-2-1-0 : Energy Consumed = 816.7225900000026
fogNode-2-1 : Energy Consumed = 7375.50000000003
cameraDevice-2-2-0 : Energy Consumed = 3.2000000000000006
MS-2-2-0-0-0 : Energy Consumed = 3.2000000000000006
MS-2-2-0-0-1 : Energy Consumed = 3.2000000000000006
MS-2-2-0-0-2 : Energy Consumed = 3.2000000000000006
MS-2-2-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-2-2-0-0 : Energy Consumed = 441.6173999999938
MS-2-2-0-1-0 : Energy Consumed = 3.2000000000000006
MS-2-2-0-1-1 : Energy Consumed = 3.2000000000000006
MS-2-2-0-1-2 : Energy Consumed = 3.2000000000000006
MS-2-2-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-2-2-0-1 : Energy Consumed = 441.6173999999938
MS-2-2-0-2-0 : Energy Consumed = 3.2000000000000006
MS-2-2-0-2-1 : Energy Consumed = 3.2000000000000006
MS-2-2-0-2-2 : Energy Consumed = 3.2000000000000006
MS-2-2-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-2-2-0-2 : Energy Consumed = 441.6173999999938
esp8266-2-2-0 : Energy Consumed = 816.7225900000026
fogNode-2-2 : Energy Consumed = 7375.50000000003
cameraDevice-2-3-0 : Energy Consumed = 3.2000000000000006
MS-2-3-0-0-0 : Energy Consumed = 3.2000000000000006
MS-2-3-0-0-1 : Energy Consumed = 3.2000000000000006
MS-2-3-0-0-2 : Energy Consumed = 3.2000000000000006
MS-2-3-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-2-3-0-0 : Energy Consumed = 441.6173999999938
MS-2-3-0-1-0 : Energy Consumed = 3.2000000000000006
MS-2-3-0-1-1 : Energy Consumed = 3.2000000000000006
MS-2-3-0-1-2 : Energy Consumed = 3.2000000000000006
MS-2-3-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-2-3-0-1 : Energy Consumed = 441.6173999999938
MS-2-3-0-2-0 : Energy Consumed = 3.2000000000000006
MS-2-3-0-2-1 : Energy Consumed = 3.2000000000000006
MS-2-3-0-2-2 : Energy Consumed = 3.2000000000000006
MS-2-3-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-2-3-0-2 : Energy Consumed = 441.6173999999938
esp8266-2-3-0 : Energy Consumed = 816.7225900000026
fogNode-2-3 : Energy Consumed = 7375.50000000003
hub-2 : Energy Consumed = 6322.209999999976
cameraDevice-3-0-0 : Energy Consumed = 3.2000000000000006
MS-3-0-0-0-0 : Energy Consumed = 3.2000000000000006
MS-3-0-0-0-1 : Energy Consumed = 3.2000000000000006
MS-3-0-0-0-2 : Energy Consumed = 3.2000000000000006
MS-3-0-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-3-0-0-0 : Energy Consumed = 441.6173999999938
MS-3-0-0-1-0 : Energy Consumed = 3.2000000000000006
MS-3-0-0-1-1 : Energy Consumed = 3.2000000000000006
MS-3-0-0-1-2 : Energy Consumed = 3.2000000000000006
MS-3-0-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-3-0-0-1 : Energy Consumed = 441.6173999999938
MS-3-0-0-2-0 : Energy Consumed = 3.2000000000000006
MS-3-0-0-2-1 : Energy Consumed = 3.2000000000000006
MS-3-0-0-2-2 : Energy Consumed = 3.2000000000000006
MS-3-0-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-3-0-0-2 : Energy Consumed = 441.6173999999938
esp8266-3-0-0 : Energy Consumed = 816.7225900000026
fogNode-3-0 : Energy Consumed = 7375.50000000003
cameraDevice-3-1-0 : Energy Consumed = 3.2000000000000006
MS-3-1-0-0-0 : Energy Consumed = 3.2000000000000006
MS-3-1-0-0-1 : Energy Consumed = 3.2000000000000006
MS-3-1-0-0-2 : Energy Consumed = 3.2000000000000006
MS-3-1-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-3-1-0-0 : Energy Consumed = 441.6173999999938
MS-3-1-0-1-0 : Energy Consumed = 3.2000000000000006
MS-3-1-0-1-1 : Energy Consumed = 3.2000000000000006
MS-3-1-0-1-2 : Energy Consumed = 3.2000000000000006
MS-3-1-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-3-1-0-1 : Energy Consumed = 441.6173999999938
MS-3-1-0-2-0 : Energy Consumed = 3.2000000000000006
MS-3-1-0-2-1 : Energy Consumed = 3.2000000000000006
MS-3-1-0-2-2 : Energy Consumed = 3.2000000000000006
MS-3-1-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-3-1-0-2 : Energy Consumed = 441.6173999999938
esp8266-3-1-0 : Energy Consumed = 816.7225900000026
fogNode-3-1 : Energy Consumed = 7375.50000000003
cameraDevice-3-2-0 : Energy Consumed = 3.2000000000000006
MS-3-2-0-0-0 : Energy Consumed = 3.2000000000000006
MS-3-2-0-0-1 : Energy Consumed = 3.2000000000000006
MS-3-2-0-0-2 : Energy Consumed = 3.2000000000000006
MS-3-2-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-3-2-0-0 : Energy Consumed = 441.6173999999938
MS-3-2-0-1-0 : Energy Consumed = 3.2000000000000006
MS-3-2-0-1-1 : Energy Consumed = 3.2000000000000006
MS-3-2-0-1-2 : Energy Consumed = 3.2000000000000006
MS-3-2-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-3-2-0-1 : Energy Consumed = 441.6173999999938
MS-3-2-0-2-0 : Energy Consumed = 3.2000000000000006
MS-3-2-0-2-1 : Energy Consumed = 3.2000000000000006
MS-3-2-0-2-2 : Energy Consumed = 3.2000000000000006
MS-3-2-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-3-2-0-2 : Energy Consumed = 441.6173999999938
esp8266-3-2-0 : Energy Consumed = 816.7225900000026
fogNode-3-2 : Energy Consumed = 7375.50000000003
cameraDevice-3-3-0 : Energy Consumed = 3.2000000000000006
MS-3-3-0-0-0 : Energy Consumed = 3.2000000000000006
MS-3-3-0-0-1 : Energy Consumed = 3.2000000000000006
MS-3-3-0-0-2 : Energy Consumed = 3.2000000000000006
MS-3-3-0-0-3 : Energy Consumed = 3.2000000000000006
espMS-3-3-0-0 : Energy Consumed = 441.6173999999938
MS-3-3-0-1-0 : Energy Consumed = 3.2000000000000006
MS-3-3-0-1-1 : Energy Consumed = 3.2000000000000006
MS-3-3-0-1-2 : Energy Consumed = 3.2000000000000006
MS-3-3-0-1-3 : Energy Consumed = 3.2000000000000006
espMS-3-3-0-1 : Energy Consumed = 441.6173999999938
MS-3-3-0-2-0 : Energy Consumed = 3.2000000000000006
MS-3-3-0-2-1 : Energy Consumed = 3.2000000000000006
MS-3-3-0-2-2 : Energy Consumed = 3.2000000000000006
MS-3-3-0-2-3 : Energy Consumed = 3.2000000000000006
espMS-3-3-0-2 : Energy Consumed = 441.6173999999938
esp8266-3-3-0 : Energy Consumed = 816.7225900000026
fogNode-3-3 : Energy Consumed = 7375.50000000003
hub-3 : Energy Consumed = 6322.209999999976
Cost of execution in cloud = 0.0
Total network usage = 98352.0

Process finished with exit code 0
```

## Simulations Data
https://drive.google.com/drive/folders/1lDcNaUyOv6cWJtfZtGI9LQ1e-rYsPpJC?usp=sharing
