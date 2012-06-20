package net.minecraft.src;

//This class is not developped by almosted.

import java.io.UnsupportedEncodingException;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static byte[] getBytesIso8859_1(String string)
    {
        return getBytesUnchecked(string, "ISO-8859-1");
    }

    public static byte[] getBytesUsAscii(String string)
    {
        return getBytesUnchecked(string, "US-ASCII");
    }

    public static byte[] getBytesUtf16(String string)
    {
        return getBytesUnchecked(string, "UTF-16");
    }

    public static byte[] getBytesUtf16Be(String string)
    {
        return getBytesUnchecked(string, "UTF-16BE");
    }

    public static byte[] getBytesUtf16Le(String string)
    {
        return getBytesUnchecked(string, "UTF-16LE");
    }

    public static byte[] getBytesUtf8(String string)
    {
        return getBytesUnchecked(string, "UTF-8");
    }

    public static byte[] getBytesUnchecked(String string, String charsetName)
    {
    	byte[] s = null;
        if(string == null)
        {
            return null;
        }
        try {
			s = string.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e)
    {
        return new IllegalStateException((new StringBuilder()).append(charsetName).append(": ").append(e).toString());
    }

    public static String newString(byte bytes[], String charsetName)
    {
    	String s = null;
        if(bytes == null)
        {
            return null;
        }
        try {
			s = new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
    }

    public static String newStringIso8859_1(byte bytes[])
    {
        return newString(bytes, "ISO-8859-1");
    }

    public static String newStringUsAscii(byte bytes[])
    {
        return newString(bytes, "US-ASCII");
    }

    public static String newStringUtf16(byte bytes[])
    {
        return newString(bytes, "UTF-16");
    }

    public static String newStringUtf16Be(byte bytes[])
    {
        return newString(bytes, "UTF-16BE");
    }

    public static String newStringUtf16Le(byte bytes[])
    {
        return newString(bytes, "UTF-16LE");
    }

    public static String newStringUtf8(byte bytes[])
    {
        return newString(bytes, "UTF-8");
    }
}
