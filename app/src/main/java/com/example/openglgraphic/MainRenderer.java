package com.example.openglgraphic;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.openglgraphic.glwrapper.Program;

import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MainRenderer implements GLSurfaceView.Renderer {

    private Program mProgram;

    public MainRenderer(Context context){
        Resources resources = context.getResources();

        InputStream vertex_stream = resources.openRawResource(R.raw.point_vertex);
        InputStream fragment_stream = resources.openRawResource(R.raw.color_fragment);

        mProgram = new Program(vertex_stream, fragment_stream);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mProgram.build();
        mProgram.enable();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1);
    }
}
