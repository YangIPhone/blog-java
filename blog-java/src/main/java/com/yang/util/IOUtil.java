package com.yang.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
	private  IOUtil() {}
	
	public static void inToOut(InputStream in,OutputStream out) throws IOException
	{
		byte [] bs=new byte[1024];
		int i=0;
		while((i=in.read(bs))!=-1)
		{
			out.write(bs,0,i);
		}
	}
	
	public static void closeStream(InputStream in,OutputStream out)
	{
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			in=null;
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out=null;
		}
	}
}
