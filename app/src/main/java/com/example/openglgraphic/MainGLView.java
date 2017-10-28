package com.example.openglgraphic;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;


public class MainGLView extends GLSurfaceView {

    private MainRenderer mRenderer;

    public MainGLView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);

        //Use Emulator Setting
        setEGLConfigChooser(8 , 8, 8, 8, 16, 0);

        mRenderer = new MainRenderer(context);
        setRenderer(mRenderer);
    }
}
