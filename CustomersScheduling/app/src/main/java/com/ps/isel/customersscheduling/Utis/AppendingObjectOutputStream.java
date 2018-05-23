package com.ps.isel.customersscheduling.Utis;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Colapso on 23/05/18.
 */

public class AppendingObjectOutputStream extends ObjectOutputStream
{
    public AppendingObjectOutputStream(OutputStream out) throws IOException
    {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
