'''
from network import Bluetooth
import pycom
import ubinascii
import time
pycom.heartbeat(False)
pycom.rgbled(0xFF0000)  # Red
bt = Bluetooth()
bt.start_scan(-1)
while bt.isscanning():
   adv = bt.get_adv()
   if adv:
        adr_manuf = bt.resolve_adv_data(adv.data, bt.ADV_NAME_CMPL)
        adr_mac = ubinascii.hexlify(adv.mac)
        addr_type = bt.resolve_adv_data(adv.data, bt.SCAN_RSP)
        adv_type = bt.resolve_adv_data(adv.data, bt.PUBLIC_ADDR)
        data_type = bt.resolve_adv_data(adv.data, bt.ADV_NAME_CMPL)
        if (adr_manuf == None):
            print("Searching..")
        else:
            print('\nName of device   : ', str(adr_manuf))
            print(40*'-')
            print('MAC address      : ', adr_mac)
            print('Address type     : ', addr_type)
            print('Advert type      : ', adv_type)
            print('Data type        : ', data_type)
   time.sleep(1)
pycom.rgbled(0x00FF00)  # Green

'''



from network import LoRa
from network import Bluetooth
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


bt = Bluetooth()
bt.start_scan(-1)

# starts scanning and stop after
pycom.heartbeat(False)
pycom.rgbled(0xFF0000)  # Red
i = 0
while bt.isscanning():
   adv = bt.get_adv()
   if adv and ubinascii.hexlify(adv.mac) == b'd0f01843e444':
       pycom.rgbled(0x0000FF)  # Blue
       adv_manuf = bt.resolve_adv_data(adv.data,bt.ADV_NAME_CMPL)
       print('\nName of device: ' , str(adv_manuf))
       data_manuf = ubinascii.hexlify(bt.resolve_adv_data (adv.data,bt.ADV_MANUFACTURER_DATA))
       press=data_manuf[12:16]
       press2 = ubinascii.unhexlify(press)
       press3=(struct.unpack("<H",press2)[0])/16
       print('Pressure: ' + str(press3) + ' hPa')
       print("Session:" + str(i))

       user_data = struct.pack(">i",int(round(press3)))

       try:
           s.send(user_data)
           print('sent: pressure:{}'.format(int(round(press3))))

       except OSError as e:
           if e.args[0] == 11:
               print('EAGAIN (11) error! Probably duty cycle exceeded.')

        # make the socket non-blocking
        # (because if there's no data received it will block forever...)
       s.setblocking(False)

   #print('Searching session ' + str(i) + '...')
   i += 1
pycom.rgbled(0x00FF00)  # Green
