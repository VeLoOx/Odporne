package pl.reader.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CrcClient {
	private final int polymonial = 0x8408;
	private final int presetValue = 0;

	public int getCrc16(int[] values) throws IOException {
		byte[] tmp = integersToBytes(values);
		 /*for (int i=0; i < tmp.length; i++)
	     {
	         System.out.println(i + ": " + tmp[i]);
	     }*/
		int tmpCRC = presetValue;
		for (int i = 0; i < tmp.length; i++) {
			tmpCRC ^= tmp[i] & 0xFF;
			for (int j = 0; j < 8; j++) {
				if ((tmpCRC & 1) != 0) {
					tmpCRC = (tmpCRC >>> 1) ^ polymonial;
				} else {
					tmpCRC = tmpCRC >>> 1;
				}
			}
		}
		//System.out.println("current crc value " + tmpCRC);
		tmpCRC = tmpCRC & 0xFFFF;
		//System.out.println("current crc value after 0xFFFF " + tmpCRC);
		return switchBytes(tmpCRC);
	}

	private int switchBytes(int n) {
		return (((n & 0xFF00) >> 8) + ((n & 0xFF) << 8));
	}

	private byte[] integersToBytes(int[] values) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		for (int i = 0; i < values.length; ++i) {
			dos.writeInt(values[i]);
		}

		return baos.toByteArray();
	}
	
}
