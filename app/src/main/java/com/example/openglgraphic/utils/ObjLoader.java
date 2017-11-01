package com.example.openglgraphic.utils;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjLoader {

    private static final int COORDS_PER_VERTEX = 3;
    private List<String> verticesList;
    private List<String> facesList;

    private float[] vertices;
    private short[] indices;

    public ObjLoader(InputStream obj_stream){
        Scanner scanner = new Scanner(obj_stream);

        verticesList = new ArrayList<>();
        facesList = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("v ")) {
                // Add vertex line to list of vertices
                verticesList.add(line);
            } else if(line.startsWith("f ")) {
                // Add face line to faces list
                facesList.add(line);
            }
        }

        scanner.close();
        getVertext();
        getIndex();
    }

    public void getVertext(){
        vertices = new float[verticesList.size() * COORDS_PER_VERTEX];
        int count = 0;
        for(String vertex: verticesList){
            String coords[] = vertex.split(" ");
            vertices[count + 0] = Float.parseFloat(coords[1]);
            vertices[count + 1] = Float.parseFloat(coords[2]);
            vertices[count + 2] = Float.parseFloat(coords[3]);
            count = count + COORDS_PER_VERTEX;
        }

    }

    public void getIndex(){
        indices =  new short[facesList.size() * COORDS_PER_VERTEX];
        int count = 0;
        for(String face: facesList) {
            String vertexIndices[] = face.split(" ");
            short x = Short.parseShort(vertexIndices[1]);
            short y = Short.parseShort(vertexIndices[2]);
            short z = Short.parseShort(vertexIndices[3]);
            indices[count + 0] = ((short)(x - 1));
            indices[count + 1] = ((short)(y - 1));
            indices[count + 2] = ((short)(z - 1));
            count = count + COORDS_PER_VERTEX;
        }

    }

    public float[] export(){
        return vertices;
    }

    public short[] export_index(){
        return indices;
    }
}
