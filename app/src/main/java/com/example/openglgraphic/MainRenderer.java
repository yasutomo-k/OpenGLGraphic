package com.example.openglgraphic;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.openglgraphic.glwrapper.ArrayBuffer;
import com.example.openglgraphic.glwrapper.IndexBuffer;
import com.example.openglgraphic.glwrapper.Program;

import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MainRenderer implements GLSurfaceView.Renderer {

    private Program mProgram;
    private ArrayBuffer mArrayBuffer;
    private IndexBuffer mIndexBuffer;

    private int mPositionHandle;


    public MainRenderer(Context context){

        //load shader resources
        Resources resources = context.getResources();
        InputStream vertex_stream = resources.openRawResource(R.raw.vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.color_fragment);


        mProgram = new Program(vertex_stream, fragment_stream);
        mArrayBuffer = new ArrayBuffer(100);
        mIndexBuffer = new IndexBuffer(100);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        //compile shader
        mProgram.build();
        mProgram.enable();
        mArrayBuffer.bind();
        mIndexBuffer.bind();

        mPositionHandle = mProgram.getAttributeLocation("a_Position");

        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, 0);
        GLES20.glEnableVertexAttribArray(mPositionHandle);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mIndexBuffer.count(), GLES20.GL_UNSIGNED_SHORT, 0);
    }
}
