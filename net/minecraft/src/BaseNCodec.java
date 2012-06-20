package net.minecraft.src;

// Referenced classes of package org.apache.commons.codec.binary:
//            StringUtils
//This class is not developped by almosted.

public abstract class BaseNCodec
{

    public static final int MIME_CHUNK_SIZE = 76;
    public static final int PEM_CHUNK_SIZE = 64;
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    protected static final byte PAD_DEFAULT = 61;
    protected final byte PAD = 61;
    private final int unencodedBlockSize;
    private final int encodedBlockSize;
    protected final int lineLength;
    private final int chunkSeparatorLength;
    protected byte buffer[];
    protected int pos;
    private int readPos;
    protected boolean eof;
    protected int currentLinePos;
    protected int modulus;

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength)
    {
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        this.lineLength = lineLength <= 0 || chunkSeparatorLength <= 0 ? 0 : (lineLength / encodedBlockSize) * encodedBlockSize;
        this.chunkSeparatorLength = chunkSeparatorLength;
    }

    boolean hasData()
    {
        return buffer != null;
    }

    int available()
    {
        return buffer == null ? 0 : pos - readPos;
    }

    protected int getDefaultBufferSize()
    {
        return 8192;
    }

    private void resizeBuffer()
    {
        if(buffer == null)
        {
            buffer = new byte[getDefaultBufferSize()];
            pos = 0;
            readPos = 0;
        } else
        {
            byte b[] = new byte[buffer.length * 2];
            System.arraycopy(buffer, 0, b, 0, buffer.length);
            buffer = b;
        }
    }

    protected void ensureBufferSize(int size)
    {
        if(buffer == null || buffer.length < pos + size)
        {
            resizeBuffer();
        }
    }

    int readResults(byte b[], int bPos, int bAvail)
    {
        if(buffer != null)
        {
            int len = Math.min(available(), bAvail);
            System.arraycopy(buffer, readPos, b, bPos, len);
            readPos += len;
            if(readPos >= pos)
            {
                buffer = null;
            }
            return len;
        } else
        {
            return eof ? -1 : 0;
        }
    }

    protected static boolean isWhiteSpace(byte byteToCheck)
    {
        switch(byteToCheck)
        {
        case 9: // '\t'
        case 10: // '\n'
        case 13: // '\r'
        case 32: // ' '
            return true;
        }
        return false;
    }

    private void reset()
    {
        buffer = null;
        pos = 0;
        readPos = 0;
        currentLinePos = 0;
        modulus = 0;
        eof = false;
    }

    public Object encode(Object pObject)
        throws EncoderException
    {
        if(!(pObject instanceof byte[]))
        {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        } else
        {
            return encode((byte[])(byte[])pObject);
        }
    }

    public String encodeToString(byte pArray[])
    {
        return StringUtils.newStringUtf8(encode(pArray));
    }

    public Object decode(Object pObject)
        throws DecoderException
    {
        if(pObject instanceof byte[])
        {
            return decode((byte[])(byte[])pObject);
        }
        if(pObject instanceof String)
        {
            return decode((String)pObject);
        } else
        {
            throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
        }
    }

    public byte[] decode(String pArray)
    {
        return decode(StringUtils.getBytesUtf8(pArray));
    }

    public byte[] decode(byte pArray[])
    {
        reset();
        if(pArray == null || pArray.length == 0)
        {
            return pArray;
        } else
        {
            decode(pArray, 0, pArray.length);
            decode(pArray, 0, -1);
            byte result[] = new byte[pos];
            readResults(result, 0, result.length);
            return result;
        }
    }

    public byte[] encode(byte pArray[])
    {
        reset();
        if(pArray == null || pArray.length == 0)
        {
            return pArray;
        } else
        {
            encode(pArray, 0, pArray.length);
            encode(pArray, 0, -1);
            byte buf[] = new byte[pos - readPos];
            readResults(buf, 0, buf.length);
            return buf;
        }
    }

    public String encodeAsString(byte pArray[])
    {
        return StringUtils.newStringUtf8(encode(pArray));
    }

    abstract void encode(byte abyte0[], int i, int j);

    abstract void decode(byte abyte0[], int i, int j);

    protected abstract boolean isInAlphabet(byte byte0);

    public boolean isInAlphabet(byte arrayOctet[], boolean allowWSPad)
    {
        for(int i = 0; i < arrayOctet.length; i++)
        {
            if(!isInAlphabet(arrayOctet[i]) && (!allowWSPad || arrayOctet[i] != 61 && !isWhiteSpace(arrayOctet[i])))
            {
                return false;
            }
        }

        return true;
    }

    public boolean isInAlphabet(String basen)
    {
        return isInAlphabet(StringUtils.getBytesUtf8(basen), true);
    }

    protected boolean containsAlphabetOrPad(byte arrayOctet[])
    {
        if(arrayOctet == null)
        {
            return false;
        }
        byte arr$[] = arrayOctet;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            byte element = arr$[i$];
            if(61 == element || isInAlphabet(element))
            {
                return true;
            }
        }

        return false;
    }

    public long getEncodedLength(byte pArray[])
    {
        long len = (long)(((pArray.length + unencodedBlockSize) - 1) / unencodedBlockSize) * (long)encodedBlockSize;
        if(lineLength > 0)
        {
            len += (((len + (long)lineLength) - 1L) / (long)lineLength) * (long)chunkSeparatorLength;
        }
        return len;
    }
}
