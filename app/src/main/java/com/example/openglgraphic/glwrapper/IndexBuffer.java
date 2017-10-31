package com.example.openglgraphic.glwrapper;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class IndexBuffer {

    private static final int SHORT_SIZE = Short.SIZE/Byte.SIZE;

    private int mMax;
    private ShortBuffer mIndices;

    private short drawTrinangleOrder[] = { 0, 1, 2};
    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 };

    private int mIndexCount;

    public IndexBuffer(int max){
        // max size of float
        mMax = max;
        mIndexCount = 0;

        ByteBuffer byte_buffer  = ByteBuffer.allocateDirect(max * SHORT_SIZE);
        byte_buffer.order(ByteOrder.nativeOrder());

        mIndices = byte_buffer.asShortBuffer();
    }

    public void bind(){
        int[] buffer = new int[1];
        GLES20.glGenBuffers(1, buffer, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffer[0]);


        mIndices.put(drawOrder);
        mIndexCount = mIndices.position();
        mIndices.rewind();

        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER,mIndexCount * SHORT_SIZE,mIndices,GLES20.GL_STATIC_DRAW);
    }

    public int count(){
        return mIndexCount;
    }
}
