package net.minecraft.src;

//This class is not developped by almosted.

public class EncoderException extends Exception
{

    private static final long serialVersionUID = 1L;

    public EncoderException()
    {
    }

    public EncoderException(String message)
    {
        super(message);
    }

    public EncoderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EncoderException(Throwable cause)
    {
        super(cause);
    }
}
