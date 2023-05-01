# iFogSim-Simulations-for-Fog-Based-Surviliance-Architecture
Simulations are crucial for analyzing device performance and identifying energy-efficient, low-latency options in IoT architecture design. We used the iFogSim simulation tool to optimize our fog-based IoT architecture and evaluate the performance of different devices and their interactions. Our evaluation included Raspberry Pi models and devices such as NVIDIA Jetson Xavier NX2, Intel NUC 11 Performance Kit, LattePanda Delta, and ASUS PN50 Barebone Mini PC. By adjusting parameters such as MIPS, RAM, bandwidth, busy power, and idle power, we identified the most suitable devices for our use case and determined the most energy-efficient devices with the lowest latency. A generic input for a simulation task in iFogSim is as follow,
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

Table below lists the specifications of Raspberry Pi models, and specifications of other devices.
| Devices | NodeMCU | RPi2 | RPi3 | RPi3B+ | RPi3A+ | RPi4B | Jetson NX | NUC 11 | LP Delta | PN50 |
| ------- | ------- | ---- | ---- | ------ | ------ | ----- | --------- | ------ | -------- | ---- |
| MIPS | 80 | 298.7 | 460.9 | 526.7 | 536.23 | 2037.3 | 36288 | 44000 | 3500 | 20000 |
| RAM | 4 | 512 | 1024 | 1024 | 512 | 4096/8192 | 16384 | 65536 | 4096 | 32768 | 
| UpBw | - | 24.4 | 49.2 | 97.6 | 93.7 | 114 | 600 | 2400 | 1000 | 2400 |
| DwBw | 2 | 17 | 17 | 62.45 | 62.45 | 62.45 | - | - | - | - |
| Busy Pw | 0.41 | 5.9 | 5.9 | 6.4 | 5.4 | 7.6 | 30 | 40 | 15 | 60 |
| Idle Pw | 0.0016 | 2.1 | 2.1 | 2.9 | 1.2 | 3.4 | 10 | 10 | 5 | 10 | 
