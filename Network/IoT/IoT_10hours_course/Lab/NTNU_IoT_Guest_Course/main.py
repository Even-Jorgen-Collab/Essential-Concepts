from network import LoRa
import socket
import time
import pycom
import struct
import ubinascii
import machine
import uos
import math

pycom.heartbeat(False)
pycom.rgbled(0x000000)

# Initialise LoRa in LORAWAN mode.
# Please pick the region that matches where you are using the device:
# Asia = LoRa.AS923
# Australia = LoRa.AU915
# Europe = LoRa.EU868
# United States = LoRa.US915
lora = LoRa(mode=LoRa.LORAWAN, region=LoRa.EU868)

# create an OTAA authentication parameters, change them to the provided credentials
app_eui = ubinascii.unhexlify('0000000000000005')
#app_key = ubinascii.unhexlify('086FA1F54294991B1D489DA433685099')
app_key = ubinascii.unhexlify('536A6BDE9A05D22987C1013E9B5C7B8D')
#uncomment to use LoRaWAN application provided dev_eui
#dev_eui = ubinascii.unhexlify('70B3D549938EA1EE')

# Uncomment for US915 / AU915 & Pygate
# for i in range(0,8):
#     lora.remove_channel(i)
# for i in range(16,65):
#     lora.remove_channel(i)
# for i in range(66,72):
#     lora.remove_channel(i)

# join a network using OTAA (Over the Air Activation)
#uncomment below to use LoRaWAN application provided dev_eui
lora.join(activation=LoRa.OTAA, auth=(app_eui, app_key), timeout=0)
#lora.join(activation=LoRa.OTAA, auth=(dev_eui, app_eui, app_key), timeout=0)

# wait until the module has joined the network
while not lora.has_joined():
    time.sleep(2.5)
    print('Not yet joined...')

print('Joined')

pycom.rgbled(0x0000ff)
time.sleep(0.5)
pycom.rgbled(0x000000)
time.sleep(0.1)
pycom.rgbled(0x0000ff)
time.sleep(0.5)
pycom.rgbled(0x000000)

# create a LoRa socket
s = socket.socket(socket.AF_LORA, socket.SOCK_RAW)

# set the LoRaWAN data rate
s.setsockopt(socket.SOL_LORA, socket.SO_DR, 5)

# make the socket blocking
# (waits for the data to be sent and for the 2 receive windows to expire)
s.setblocking(True)


i=0
while True:
    # Functions to be monitored
    sinewave=int(256*math.sin(i))
    cosinewave=int(256*math.cos(i))
    expfunction=float(math.exp(1+(i/10000)))

    # Packs them in separate structs, packing them together fused them into one variable

    user_data = struct.pack(">iif",sinewave,cosinewave,expfunction)

    try:
        s.send(user_data)
        print('sent: sinewave:{}'.format(sinewave))
        print('sent: cosinewave:{}'.format(cosinewave))
        print('sent: expfunction:{}'.format(expfunction))

    except OSError as e:
        if e.args[0] == 11:
            print('EAGAIN (11) error! Probably duty cycle exceeded.')

    pycom.rgbled(0x007f00)
    time.sleep(0.2)
    pycom.rgbled(0x000000)

    # make the socket non-blocking
    # (because if there's no data received it will block forever...)
    s.setblocking(False)

    # get any data received (if any...)
    data = s.recv(64)
    print(data)
    time.sleep(5)
    i=i+1
