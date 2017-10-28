package com.example.openglgraphic.glwrapper;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ArrayBuffer {

    private static final int FLOAT_SIZE = Float.SIZE/Byte.SIZE;
    private FloatBuffer mVertices;
    private int mMax;

    private float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f }; // top right

    private float triangleCoords[] = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    public ArrayBuffer(int max){
        // max size of float
        mMax = max;

        ByteBuffer byte_buffer  = ByteBuffer.allocateDirect(max * FLOAT_SIZE);
        byte_buffer.order(ByteOrder.nativeOrder());

        mVertices = byte_buffer.asFloatBuffer();
    }

    public void bind(){
        int[] vertex = new int[1];
        GLES20.glGenBuffers(1, vertex, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex[0]);


        mVertices.put(triangleCoords);
        int size = mVertices.position();
        mVertices.rewind();

        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, FLOAT_SIZE * size,mVertices,GLES20.GL_STATIC_DRAW );


    }
}
