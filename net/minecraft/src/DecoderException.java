package net.minecraft.src;

//This class is not developped by almosted.

public class DecoderException extends Exception
{

    private static final long serialVersionUID = 1L;

    public DecoderException()
    {
    }

    public DecoderException(String message)
    {
        super(message);
    }

    public DecoderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DecoderException(Throwable cause)
    {
        super(cause);
    }
}
