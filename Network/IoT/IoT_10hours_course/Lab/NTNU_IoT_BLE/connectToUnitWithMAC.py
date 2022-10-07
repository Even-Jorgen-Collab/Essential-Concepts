from network import Bluetooth
import ubinascii
import struct
import pycom
import time
bt = Bluetooth()
bt.start_scan(20)

# starts scanning and stop after
pycom.heartbeat(False)
pycom.rgbled(0xFF0000)  # Red
while bt.isscanning():
    adv = bt.get_adv()
    if adv and ubinascii.hexlify(adv.mac) == b'bc25f01ab56e':
        pycom.rgbled(0x0000FF)  # Blue
        adv_manuf = bt.resolve_adv_data(adv.data,bt.ADV_NAME_CMPL)
        print('\nName of device: ' , str(adv_manuf))
        data_manuf = ubinascii.hexlify(bt.resolve_adv_data (adv.data,bt.ADV_MANUFACTURER_DATA))
        press=data_manuf[12:16]
        press2 = ubinascii.unhexlify(press)
        press3=(struct.unpack("<H",press2)[0])/16
        print('\nPressure: ' + str(press3) + ' hPa\n')
    print('\nSearching...')
    time.sleep(1)

pycom.rgbled(0x00FF00)  # Green
